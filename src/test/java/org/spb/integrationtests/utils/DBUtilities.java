package org.spb.integrationtests.utils;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class DBUtilities {
  static Logger logger = LogManager.getLogger(DBUtilities.class);

  public static boolean insertIntoTable(String tableName, LinkedHashMap<String, String> values) {
    try (Connection connection = C3p0DataSource.getConnection()) {
//      Connection connection = ;
      StringBuilder sql = new StringBuilder();
      sql.append("INSERT INTO spb_schema." + tableName + " (")
          .append(values.keySet().stream().collect(Collectors.joining(",")))
          .append(")")
          .append(" VALUES (")
          .append(
              values.values().stream()
                  .map(plain -> '"' + StringEscapeUtils.escapeJava(plain) + '"')
                  .collect(Collectors.joining(",")))
          .append(")");
      System.out.println("SQL is:" + sql);
      boolean execute = connection.createStatement().execute(sql.toString());
      connection.close();
    } catch (SQLException throwables) {
      logger.error(throwables.getMessage());
      return false;
    }
    return true;
  }
  public static boolean deleteFromTable(String tableName, String lookupKey, String lookupValue){
      try {
          Connection connection = C3p0DataSource.getConnection();
          StringBuilder sql = new StringBuilder();
          sql.append("DELETE FROM spb_schema.")
                  .append(tableName)
                  .append(" WHERE ")
                  .append(lookupKey)
                  .append("=")
                  .append("\"")
                  .append(lookupValue)
                  .append("\"");
          System.out.println("SQL is:" + sql);
          boolean execute = connection.createStatement().execute(sql.toString());
          connection.close();
      } catch (SQLException throwables) {
          logger.error(throwables.getMessage());
          return false;
      }
      return true;
  }
    public static HashMap<String, String> findUniqueRowInTable(String tableName, String lookupUniqueKey, String lookupValue){
        HashMap<String, String> values = new HashMap<>();
        try {
            Connection connection = C3p0DataSource.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT * FROM spb_schema.")
                    .append(tableName)
                    .append(" WHERE ")
                    .append(lookupUniqueKey)
                    .append("=")
                    .append("\"")
                    .append(lookupValue)
                    .append("\"");
            System.out.println("SQL is:" + sql);
            ResultSet resultSet = connection.createStatement().executeQuery(sql.toString());
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int colCount=1;
            boolean isFirstRow=true;
            while(resultSet.next() && isFirstRow){
                while(colCount<=resultSetMetaData.getColumnCount()){
                    values.put(resultSetMetaData.getColumnName(colCount),resultSet.getString(colCount));
                    colCount++;
                }
                isFirstRow=false;
            }
            connection.close();
            
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
            throwables.printStackTrace();
        }
        return values;
    }
    public static int getNumberOfRows(String tableName, String keyName, String keyValue){
      int recordCount=0;
        try {
            Connection connection = C3p0DataSource.getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT count(*) as recordCount FROM spb_schema.")
                    .append(tableName)
                    .append(" WHERE ")
                    .append(keyName)
                    .append("=")
                    .append("\"")
                    .append(keyValue)
                    .append("\"");
            System.out.println("SQL is:" + sql);
            ResultSet resultSet = connection.createStatement().executeQuery(sql.toString());
            while(resultSet.next()){
                String count = resultSet.getString("recordCount");
                if(count!=null){
                    recordCount=Integer.parseInt(count);
                }
            }
            
            connection.close();
        
        } catch (SQLException throwables) {
            logger.error(throwables.getMessage());
        
        }
        return recordCount;    }
}
