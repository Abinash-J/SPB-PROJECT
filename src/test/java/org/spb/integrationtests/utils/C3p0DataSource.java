package org.spb.integrationtests.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3p0DataSource {
    private Logger logger = LogManager.getLogger(C3p0DataSource.class);
        
        private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        private static ThreadLocal<ComboPooledDataSource> datasources = new ThreadLocal<>();
        private static ComboPooledDataSource getDS(){
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            try {
                CloudUtilities c = new CloudUtilities();
                String connectionString = c.getSecret("qa/db/integration");
                ObjectMapper mapper = new ObjectMapper();
                ConnectionPOJO connectionPOJO = mapper.readValue(connectionString,ConnectionPOJO.class);
                StringBuilder jdbcUrl = new StringBuilder();
                jdbcUrl.append("jdbc:mysql://")
                        .append(connectionPOJO.getHost())
                        .append(":")
                        .append(connectionPOJO.getPort())
                        .append("/")
                        .append("spb_schema");
                System.out.println(jdbcUrl);
                cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
                cpds.setJdbcUrl(jdbcUrl.toString());
                cpds.setUser(connectionPOJO.getUsername());
                cpds.setPassword(connectionPOJO.getPassword());
                cpds.setAutoCommitOnClose(true);
                cpds.setMaxPoolSize(5);
            } catch (JsonProcessingException | PropertyVetoException e) {
                e.printStackTrace();
        
        
            }
            datasources.set(cpds);
            return cpds;
        }
/*        static {
            try {
                CloudUtilities c = new CloudUtilities();
                String connectionString = c.getSecret("qa/db/integration");
                ObjectMapper mapper = new ObjectMapper();
                ConnectionPOJO connectionPOJO = mapper.readValue(connectionString,ConnectionPOJO.class);
                StringBuilder jdbcUrl = new StringBuilder();
                jdbcUrl.append("jdbc:mysql://")
                        .append(connectionPOJO.getHost())
                        .append(":")
                        .append(connectionPOJO.getPort())
                        .append("/")
                        .append("spb_schema");
                System.out.println(jdbcUrl);
                cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
                cpds.setJdbcUrl(jdbcUrl.toString());
                cpds.setUser(connectionPOJO.getUsername());
                cpds.setPassword(connectionPOJO.getPassword());
                cpds.setAutoCommitOnClose(true);
                cpds.setMaxPoolSize(20);
            } catch (PropertyVetoException e) {
                // handle the exception
            } catch (JsonProcessingException ignored) {
            
    
    
            }
        }*/
        
        public static Connection getConnection() throws SQLException {
            if(datasources.get()==null){
                getDS();
            }
            
            return datasources.get().getConnection();
        }
        
        private C3p0DataSource(){}
    
}
