package org.spb.integrationtests;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.spb.integrationtests.utils.DBUtilities;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Properties;

public class Hooks extends BaseClass {
  private static boolean initializer = true;
  private static Properties props = null;
  private BaseClass base;

  public Hooks(BaseClass base) {

    this.base = base;
  }

  @Before(order = 4)
  public void printScenarioName(Scenario scenario) {
    System.out.println("Executing Scenario: " + scenario.getName());
  }

  @Before(order = 1)
  public void beforeAll() {
    if (initializer) {
      try (InputStream input =
          Hooks.class.getClassLoader().getResourceAsStream("config.properties")) {
        props = new Properties();
        props.load(input);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
      initializer = false;
    }
  }

  @Before(order = 2)
  public void setupBrowser() {
    System.out.println("setting up browser");
    WebDriver driver = null;
    if (DriverFactory.getDriver() == null) {
      if (StringUtils.compareIgnoreCase(props.getProperty("browser"), "chrome") == 0) {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        //        base.driver = new ChromeDriver();
      }
      if (StringUtils.compareIgnoreCase(props.getProperty("browser"), "edge") == 0) {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
      }
      base.setupDriver(driver);
      base.maxWindow();
      DriverFactory.addDriver(driver);
    }
  }

  @Before(order = 3)
  public void setupDB(Scenario scenario) {
    // LegalEntityMaster
    if (StringUtils.compareIgnoreCase(
            scenario.getName(),
            "Validating business logic of LegalEntityMaster with suitable test data")
        == 0) {
      base.databaseWaitTime();
      setupDBForForeignKeyInLegalEntity();
    }
    if (StringUtils.compareIgnoreCase(
            scenario.getName(),
            "Validating business logic of LegalEntityMaster with duplicate test data added before the scenario execution")
        == 0) {
      base.databaseWaitTime();
      setupDBForForeignKeyInLegalEntity();
    }
    if (StringUtils.compareIgnoreCase(
            scenario.getName(),
            "Testing the functionality of Edit button in LegalEntityMaster page")
        == 0) {
      base.databaseWaitTime();
      setupDBForForeignKeyInLegalEntity();
    }
    if (StringUtils.compareIgnoreCase(
            scenario.getName(), "Testing search box and Export button in legalentity")
        == 0) {
      base.databaseWaitTime();
      setupDBForForeignKeyInLegalEntity();
    }
    if (StringUtils.compareIgnoreCase(
            scenario.getName(),
            "Testing the functionality of Delete button in LegalEntityMaster page")
        == 0) {
      setupDBForForeignKeyInLegalEntity();
    }
    
    
    
    
  //quality(product group as foreign key)
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Quality  master with valid  data")==0){
    	setupDBforForeignKeyInQualityMaster();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Quality  master with duplicate data")==0){
    	setupDBforForeignKeyInQualityMaster();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Quality  master with blank data")==0){
    	setupDBforForeignKeyInQualityMaster();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Quality  Master Page")==0){
    	setupDBforForeignKeyInQualityMaster();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Quality  Master Page")==0){
    	setupDBforForeignKeyInQualityMaster();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Quality  Master")==0){
    	setupDBforForeignKeyInQualityMaster();
    }
    
//order type(plant is foreign key)                              //start line for (if) order type master foreign keys (1(3))
    
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Order Type  master with valid  data")==0){
    	setupDBWhereOrgGroupWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBsetupDBForMasterWhichAsLegalEntityAsForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantTypeWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantWasAForeignKeySH();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Order Type  master with duplicate data")==0){
    	setupDBWhereOrgGroupWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBsetupDBForMasterWhichAsLegalEntityAsForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantTypeWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantWasAForeignKeySH();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Order Type  master with blank data")==0){
    	setupDBWhereOrgGroupWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBsetupDBForMasterWhichAsLegalEntityAsForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantTypeWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantWasAForeignKeySH();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Order Type  Master Page")==0){
    	setupDBWhereOrgGroupWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBsetupDBForMasterWhichAsLegalEntityAsForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantTypeWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantWasAForeignKeySH();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Order Type  Master Page")==0){
    	setupDBWhereOrgGroupWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBsetupDBForMasterWhichAsLegalEntityAsForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantTypeWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantWasAForeignKeySH();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Order Type  Master")==0){
    	setupDBWhereOrgGroupWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBsetupDBForMasterWhichAsLegalEntityAsForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantTypeWasAForeignKeySH();
        base.databaseWaitTime();
        setupDBWherePlantWasAForeignKeySH();
    }
    
  
  //indentor (indentor type is a foreign key) before stmnt
    
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Indentor Master with valid test data1")==0){
    	setupDBWhereIndentorTypeWasAForeignKey();
        
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Indentor Master with valid test data2")==0){
    	setupDBWhereIndentorTypeWasAForeignKey();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Indentor Master with duplicate test for Indentor code,TIN,PAN,GST data added before the scenario execution")==0){
    	setupDBWhereIndentorTypeWasAForeignKey();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Boundary Value Analysis and Equivalence class Partition for Indentor Master in PAN,GST and TIN")==0){
    	setupDBWhereIndentorTypeWasAForeignKey();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Entering invalid data for PAN,GST,TIN")==0){
    	setupDBWhereIndentorTypeWasAForeignKey();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Indentor Master with blank test data")==0){
    	setupDBWhereIndentorTypeWasAForeignKey();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Edit button in Indentor Master page")==0){
    	setupDBWhereIndentorTypeWasAForeignKey();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Delete button in Indentor Master page")==0){
    	setupDBWhereIndentorTypeWasAForeignKey();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Indentor Master")==0){
    	setupDBWhereIndentorTypeWasAForeignKey();
    }
     
//customer (customer type,indentor[indentor type] is a foreign key) before stmnt
    
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Customer Master with valid test data")==0){
    	setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
     }

    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Customer Master with duplicate test for Customer code,TIN,PAN,GST data added before the scenario execution")==0){
    	setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
     }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Boundary Value Analysis and Equivalence class Partition for Customer Master in PAN,GST and TIN")==0){
    	setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
     }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Entering invalid data for PAN,GST,TIN in Customer Form")==0){
    	setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
    	base.databaseWaitTime();
     }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Customer Master with blank test data")==0){
    	setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
     }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Edit button in Customer Master page")==0){
    	setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Delete button in Customer Master page")==0){
    	setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
    }
    if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Customer Master")==0){
    	setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
     }
    
//consignee (has foreign key[customer[customer type,indentor type ,indentor]&indentor)  1(2[2]) before stmnt
    
  	if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Consignee Master with valid test data")==0){
  		setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereCustomerWasAForeignKey();
  	}
  	
  	if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Consignee Master with duplicate test for Consignee code,TIN,PAN,GST data added before the scenario execution")==0){
  		setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereCustomerWasAForeignKey();
  	}
  	if(StringUtils.compareIgnoreCase(scenario.getName(),"Boundary Value Analysis and Equivalence class Partition for Consignee Master in PAN,GST and TIN")==0){
  		setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereCustomerWasAForeignKey();
  	}
  	if(StringUtils.compareIgnoreCase(scenario.getName(),"Entering invalid data for PAN,GST,TIN in Consignee Form")==0){
  		setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereCustomerWasAForeignKey();
  	}
  	if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Consignee Master with blank test data")==0){
  		setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereCustomerWasAForeignKey();
  	}
  	if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Edit button in Consignee Master page")==0){
  		setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereCustomerWasAForeignKey();
  	}
  	
  	if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Delete button in Consignee Master page")==0){
  		setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereCustomerWasAForeignKey();
  	}
  	
  	if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Customer Master")==0){
  		setupDBWhereCustomerTypeWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereIndentorTypeWasAForeignKey1();
    	base.databaseWaitTime();
    	setupDBWhereIndentorWasAForeignKey();
    	base.databaseWaitTime();
    	setupDBWhereCustomerWasAForeignKey();
  	}  
    
  	
 // financial calendar before stmnt
 	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Financial Calendar master with valid  data")==0){
 		 setupDBWhereFinancialCalendarTypeWasAForeignKey();
 	  }
 	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Financial Calendar master with duplicate data")==0){
 		 setupDBWhereFinancialCalendarTypeWasAForeignKey();
 	  }
 	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Financial Calendar master with invalid data by using future date for From Date & past date for To Date & (using alphabets in year,quarter,num)")==0){
 		 setupDBWhereFinancialCalendarTypeWasAForeignKey();
 	  }
 	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Financial Calendar master with blank data")==0){
 		 setupDBWhereFinancialCalendarTypeWasAForeignKey();
 	  }
 	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Financial Calendar Master Page")==0){
 		 setupDBWhereFinancialCalendarTypeWasAForeignKey();
 	  }
 	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Financial Calendar Master Page")==0){
 		 setupDBWhereFinancialCalendarTypeWasAForeignKey();
 	  }
 	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Financial Calendar Master")==0){
 		 setupDBWhereFinancialCalendarTypeWasAForeignKey();
 	  }	
    
  }
  
  
  
  
  // setup for legalentity
  private void setupDBForForeignKeyInLegalEntity() {
    base.databaseWaitTime();
    String Org_Group_Code = "IN111";
    LinkedHashMap<String, String> values = new LinkedHashMap<>();
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentDateTime = format.format(date);
    values.put("group_code", Org_Group_Code);
    values.put("group_name", "IN Group Name");
    values.put("description", "Group for integration testing");
    values.put("active", "0");
    values.put("deleted", "0");
    values.put("created_by", "Integration Test");
    values.put("updated_by", "Integration Test");
    values.put("created_on", (currentDateTime));
    values.put("updated_on", (currentDateTime));
    DBUtilities.insertIntoTable("org_group", values);
  }
  // setup for designation entity
  private void setupDBForForeignKeyInDesignationMaster() {
    base.databaseWaitTime();
    String departmentCode = "MFG";
    LinkedHashMap<String, String> value = new LinkedHashMap<>();
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String currentDateTime = format.format(date);
    value.put("department_code", departmentCode);
    value.put("department_name", "Manufacturing");
    value.put("active", "0");
    value.put("deleted", "0");
    value.put("created_by", "Integration Test");
    value.put("updated_by", "Integration Test");
    value.put("created_on", (currentDateTime));
    value.put("updated_on", (currentDateTime));
    DBUtilities.insertIntoTable("department", value);
  }

  //quality(product group as foreign key)
  public void setupDBforForeignKeyInQualityMaster() {
	  base.databaseWaitTime();
	  
	  LinkedHashMap<String, String> values = new LinkedHashMap<>();
	  	Date date = new Date();
	  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	  	String currentDateTime = format.format(date);
	  	values.put("product_group_code","PG25");
	  	values.put("description","Black");
	  	values.put("active","0");
	  	values.put("deleted","0");
	  	values.put("created_by","Integration Test");
	  	values.put("updated_by","Integration Test");
	  	values.put("created_on",(currentDateTime));
	  	values.put("updated_on",(currentDateTime));
	  	DBUtilities.insertIntoTable("product_group", values);
  }
  
  //o. order(All setup for order type master (legal entity,org group,plant type & plant)   //start line for order type master foreign keys (1(3))
  
 
//ORGGroup As a foreignKey
  private void setupDBWhereOrgGroupWasAForeignKeySH() {
  	base.databaseWaitTime();
      String Org_Group_Code="I_IN111";
      LinkedHashMap<String, String> values = new LinkedHashMap<>();
      Date date = new Date();
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
      String currentDateTime = format.format(date);
      values.put("group_code",Org_Group_Code);
      values.put("group_name","I_IN Group Name");
      values.put("description", "G_Group for integration testing");
      values.put("active","0");
      values.put("deleted","0");
      values.put("created_by","Integration Test");
      values.put("updated_by","Integration Test");
      values.put("created_on",(currentDateTime));
      values.put("updated_on",(currentDateTime));
      DBUtilities.insertIntoTable("org_group", values);
 
}
  
  //legal entity as foreign key
  private void setupDBsetupDBForMasterWhichAsLegalEntityAsForeignKeySH() {
	  HashMap<String, String> findUniqueRowInTable = DBUtilities.findUniqueRowInTable("org_group","group_code", "I_IN111");
      String org_group_id = findUniqueRowInTable.get("id");
	    base.databaseWaitTime();
	    LinkedHashMap<String, String> values = new LinkedHashMap<>();
      Date date = new Date();
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
      String currentDateTime = format.format(date);
      values.put("entity_code","0002");
      values.put("name","P_Paper");
      values.put("org_group_id", org_group_id);
      values.put("active","0");
      values.put("deleted","0");
      values.put("created_by","Integration Test");
      values.put("updated_by","Integration Test");
      values.put("created_on",(currentDateTime));
      values.put("updated_on",(currentDateTime));
      base.databaseWaitTime();
      DBUtilities.insertIntoTable("legal_entity", values);

  }
  
 //PlantType as a foreign Key
  public void setupDBWherePlantTypeWasAForeignKeySH() {
	  LinkedHashMap<String, String> values = new LinkedHashMap<>();
	  	Date date = new Date();
	  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	  	String currentDateTime = format.format(date);
	  	values.put("plant_type_code","1_12");
	  	values.put("description","W_Warehouse");
	  	values.put("active","0");
	  	values.put("deleted","0");
	  	values.put("created_by","Integration Test");
	  	values.put("updated_by","Integration Test");
	  	values.put("created_on",(currentDateTime));
	  	values.put("updated_on",(currentDateTime));
	  	DBUtilities.insertIntoTable("plant_type", values);
  }
  
  
  
  
  
//o. order type(plant as foreign key)
  public void setupDBWherePlantWasAForeignKeySH() {
	    base.databaseWaitTime();
	    HashMap<String, String> findlegal = DBUtilities.findUniqueRowInTable("legal_entity","entity_code","0002");
	        String legalentityid = findlegal.get("id");
			System.out.println("ID is: " + legalentityid);
	    base.databaseWaitTime();
	    HashMap<String, String> findgroup = DBUtilities.findUniqueRowInTable("org_group","group_code","I_IN111");
	        String orggroupid = findgroup.get("id");
	        System.out.println("ID is: " + orggroupid);
	    base.databaseWaitTime();
		HashMap<String, String> findplanttype = DBUtilities.findUniqueRowInTable("plant_type","plant_type_code", "1_12");
	        String planttype =findplanttype .get("id");
	        System.out.println("ID is: " + planttype);
		base.databaseWaitTime();
      LinkedHashMap<String, String> value = new LinkedHashMap<>();
      Date date = new Date();
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
      String currentDateTime = format.format(date);
      value.put("plant_name","Tirunelveli");
      value.put("plant_code","2222");
      value.put("plant_type_code",planttype);
      value.put("description","TiruneleveliPlant");
      value.put("address_1","223Te");
      value.put("address_2","Seshalaya");
      value.put("address_3","CoimbatoreRoad");
      value.put("area","Pallipalayam");
      value.put("city","Tirunelveli");
      value.put("state","Tamil Nadu");
      value.put("country","India");
      value.put("pincode","638047");
      value.put("telephone","+2366897");
      value.put("telephone_alt","+546489");
      value.put("time_zone","GMT+5.30");
      value.put("telephone","+2366897");
      value.put("email","sheshalaya@gmail.com");
      value.put("email_alt","");
      value.put("contact","+917837387389");
      value.put("contact_alt","+918747847848");
      value.put("org_group_id", orggroupid);
      value.put("legal_entity_id", legalentityid);
      value.put("pan_no","ATFGH7891K");
      value.put("gst","33ATFgh7891LKAZ");
      value.put("tin_no","321234568TN");
      value.put("active","0");
      value.put("deleted","0");
      value.put("created_by","Integration Test");
      value.put("updated_by","Integration Test");
      value.put("created_on",(currentDateTime));
      value.put("updated_on",(currentDateTime));
      
      DBUtilities.insertIntoTable("plant",value);                          //finish line for order type master foreign keys (1(3))
	}
  
//setup db for indentor(indentor type is foreign key) (1(1))
  public void setupDBWhereIndentorTypeWasAForeignKey() {
		LinkedHashMap<String, String> values = new LinkedHashMap<>();
	  	Date date = new Date();
	  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	  	String currentDateTime = format.format(date);
	  	values.put("indentor_type_code","IND02");
	  	values.put("description","Indentor2");
	  	values.put("active","0");
	  	values.put("deleted","0");
	  	values.put("created_by","Integration Test");
	  	values.put("updated_by","Integration Test");
	  	values.put("created_on",(currentDateTime));
	  	values.put("updated_on",(currentDateTime));
	  	DBUtilities.insertIntoTable("indentor_type", values);
	}
  
//setup db for indentor(indentor type1 is foreign key) (1(1))
  public void setupDBWhereIndentorTypeWasAForeignKey1() {
		LinkedHashMap<String, String> values = new LinkedHashMap<>();
	  	Date date = new Date();
	  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	  	String currentDateTime = format.format(date);
	  	values.put("indentor_type_code","IND021");
	  	values.put("description","Indentor2");
	  	values.put("active","0");
	  	values.put("deleted","0");
	  	values.put("created_by","Integration Test");
	  	values.put("updated_by","Integration Test");
	  	values.put("created_on",(currentDateTime));
	  	values.put("updated_on",(currentDateTime));
	  	DBUtilities.insertIntoTable("indentor_type", values);
	}
  
//setup customer type db for customer master(customer type,indentor[indentor type] is foreign key) 1(2[1])
  public void setupDBWhereCustomerTypeWasAForeignKey() {
		LinkedHashMap<String, String> values = new LinkedHashMap<>();
	  	Date date = new Date();
	  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	  	String currentDateTime = format.format(date);
	  	values.put("customer_type_code","D25");
	  	values.put("description","Dheena Publications");
	  	values.put("active","0");
	  	values.put("deleted","0");
	  	values.put("created_by","Integration Test");
	  	values.put("updated_by","Integration Test");
	  	values.put("created_on",(currentDateTime));
	  	values.put("updated_on",(currentDateTime));
	  	DBUtilities.insertIntoTable("customer_type", values);
	}
 //set up db for indentor 
  public void setupDBWhereIndentorWasAForeignKey() {
	  base.databaseWaitTime();
	  
	  LinkedHashMap<String, String> values = new LinkedHashMap<>();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentDateTime = format.format(date);
        values.put("indentor_code","INDC25");
        values.put("indentor_name","Stockist");
        values.put("indentor_type","IND021");
        values.put("legacy_ind_code","LIC4");
        values.put("type","Ship To");
        values.put("date_established","2015-03-02");
        values.put("short_name","CST_SHT_NM");
        values.put("address_1","D45");
        values.put("address_2","Seshalaya");
        values.put("address_3","CoimbatoreRoad");
        values.put("area","Pallipalayam");
        values.put("city","Tirunelveli");
        values.put("state","Tamil Nadu");
        values.put("country","India");
        values.put("pincode","638047");
        values.put("telephone","2366897");
        values.put("telephone_alt","546489");
        values.put("time_zone","GMT+5.30");
        
        values.put("email","sheshalaya@gmail.com");
        values.put("email_alt","alternate@gmail.com");
        values.put("contact","7837387389");
        values.put("contact_alt","8747847848");
        
        values.put("pan_no","ATFGH7891K");
        values.put("gst","33ATFgh7891LKAZ");
        values.put("tin_no","321234568TN");
        values.put("region","southAsia");
        values.put("outside_process","0");
        
        values.put("active","0");
        values.put("deleted","0");
        values.put("created_by","Integration Test");
        values.put("updated_by","Integration Test");
        values.put("created_on",(currentDateTime));
        values.put("updated_on",(currentDateTime));
        
        DBUtilities.insertIntoTable("indentor",values);
	}
  
//set up db for customer
  public void setupDBWhereCustomerWasAForeignKey() {
      base.databaseWaitTime();
	  
	  LinkedHashMap<String, String> values = new LinkedHashMap<>();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentDateTime = format.format(date);
        values.put("customer_code","CSTC24");
        values.put("customer_name","Ram");
        values.put("customer_type","D25");
        values.put("indentor_code","INDC25");
        //in db it shows ind nly
        values.put("legacy_ind_code","LCC4");
        values.put("type","Ship To");
        values.put("date_established","2015-03-02");
        values.put("short_name","CST_SHT_NM");
        values.put("address_1","D45");
        values.put("address_2","Seshalaya");
        values.put("address_3","CoimbatoreRoad");
        values.put("area","Pallipalayam");
        values.put("city","Tirunelveli");
        values.put("state","Tamil Nadu");
        values.put("country","India");
        values.put("pincode","638047");
        values.put("telephone","2366897");
        values.put("telephone_alt","546489");
        values.put("time_zone","GMT+5.30");
        
        values.put("email","sheshalaya@gmail.com");
        values.put("email_alt","alternate@gmail.com");
        values.put("contact","7837387389");
        values.put("contact_alt","8747847848");
        
        values.put("pan_no","ATFGH7891K");
        values.put("gst","33ATFgh7891LKAZ");
        values.put("tin_no","321234568TN");
        values.put("region","southAsia");
        values.put("outside_process","0");
        
        values.put("active","0");
        values.put("deleted","0");
        values.put("created_by","Integration Test");
        values.put("updated_by","Integration Test");
        values.put("created_on",(currentDateTime));
        values.put("updated_on",(currentDateTime));
        
        DBUtilities.insertIntoTable("customer",values);
  }
//set up db for financial calendar type   bfre stmnt
	  public void setupDBWhereFinancialCalendarTypeWasAForeignKey() {
	      base.databaseWaitTime();
	  
	  LinkedHashMap<String, String> values = new LinkedHashMap<>();
	  Date date = new Date();
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	  String currentDateTime = format.format(date);
	  values.put("calendar_code","1901");
	  values.put("description","3rd Quarter");
	  values.put("active","0");
	  values.put("deleted","0");
	  values.put("created_by","Integration Test");
	  values.put("updated_by","Integration Test");
	  values.put("created_on",(currentDateTime));
	  values.put("updated_on",(currentDateTime));
	  DBUtilities.insertIntoTable("fin_cal_type",values);
	  }  
  
  
  // After scenario,        // After scenario            // After scenario         // After scenario       // After scenario
  
  @After(order = 1)
  public void closeDriver() {
    //    BaseClass.driver.close();
  }

  @After(order = 2)
  public void teardownDB(Scenario scenario) {
    // legalentity
    if (StringUtils.compareIgnoreCase(
            scenario.getName(),
            "Validating business logic of LegalEntityMaster with suitable test data")
        == 0) {
      base.databaseWaitTime();
      tearDownDBForLegalEntity();
    }
    if (StringUtils.compareIgnoreCase(
            scenario.getName(),
            "Validating business logic of LegalEntityMaster with duplicate test data added before the scenario execution")
        == 0) {
      base.databaseWaitTime();
      tearDownDBForLegalEntity();
    }
    if (StringUtils.compareIgnoreCase(
            scenario.getName(),
            "Testing the functionality of Delete button in LegalEntityMaster page")
        == 0) {
      base.databaseWaitTime();
      tearDownDBForLegalEntity();
    }
    if (StringUtils.compareIgnoreCase(
            scenario.getName(), "Testing search box and Export button in legalentity")
        == 0) {
      base.databaseWaitTime();
      tearDownDBForLegalEntity();
    }

   
  

  
//gsm
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the GSM master with valid  data")==0){
      tearDownDBForGSMMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the GSM master with duplicate data")==0){
      tearDownDBForGSMMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in GSMMaster Page")==0){
      tearDownDBForGSMMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in GSMMaster Page")==0){
      tearDownDBForGSMMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in GSMMaster")==0){
      tearDownDBForGSMMaster();
  }
//plant type
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Plant Type master with valid  data")==0){
      tearDownDBForPlantTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Plant Type master with duplicate data")==0){
      tearDownDBForPlantTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Plant Type Master Page")==0){
      tearDownDBForPlantTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Plant Type Master Page")==0){
      tearDownDBForPlantTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Plant Type Master")==0){
      tearDownDBForPlantTypeMaster();
  }
//form factor
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Form Factor master with valid  data")==0){
      tearDownDBForFormFactorMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Form Factor master with duplicate data")==0){
      tearDownDBForFormFactorMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Form Factor Master Page")==0){
      tearDownDBForFormFactorMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Form Factor Master Page")==0){
      tearDownDBForFormFactorMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Form Factor Master")==0){
      tearDownDBForFormFactorMaster();
  }
  
//product group
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Product Group master with valid  data")==0){
      tearDownDBForProductGroupMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Product Group master with duplicate data")==0){
      tearDownDBForProductGroupMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Product Group Master Page")==0){
      tearDownDBForProductGroupMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Product Group Master Page")==0){
      tearDownDBForProductGroupMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Product Group Master")==0){
      tearDownDBForProductGroupMaster();
  }
  
//item type
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Item Type master with valid  data")==0){
      tearDownDBForItemTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Item Type master with duplicate data")==0){
      tearDownDBForItemTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Item Type Master Page")==0){
      tearDownDBForItemTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Item Type Master Page")==0){
      tearDownDBForItemTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Item Type Master")==0){
      tearDownDBForItemTypeMaster();
  }
  
//price type
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Price Type master with valid  data")==0){
      tearDownDBForPriceTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Price Type master with duplicate data")==0){
      tearDownDBForPriceTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Price Type Master Page")==0){
      tearDownDBForPriceTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Price Type Master Page")==0){
      tearDownDBForPriceTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Price Type Master")==0){
      tearDownDBForPriceTypeMaster();
  }
  
//payment terms
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Payment Terms master with valid  data")==0){
      tearDownDBForPaymentTermsMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Payment Terms master with duplicate data")==0){
      tearDownDBForPaymentTermsMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Payment Terms Master Page")==0){
      tearDownDBForPaymentTermsMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Payment Terms Master Page")==0){
      tearDownDBForPaymentTermsMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Payment Terms Master")==0){
      tearDownDBForPaymentTermsMaster();
  }
  
//customer type
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Customer Type master with valid  data")==0){
      tearDownDBForCustomerTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Customer Type master with duplicate data")==0){
      tearDownDBForCustomerTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Customer Type Master Page")==0){
      tearDownDBForCustomerTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Customer Type Master Page")==0){
      tearDownDBForCustomerTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Customer Type Master")==0){
      tearDownDBForCustomerTypeMaster();
  }
//indent type
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Indent Type master with valid  data")==0){
      tearDownDBForIndentTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Indent Type master with duplicate data")==0){
      tearDownDBForIndentTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Indent Type Master Page")==0){
      tearDownDBForIndentTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Indent Type Master Page")==0){
      tearDownDBForIndentTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Indent Type Master")==0){
      tearDownDBForIndentTypeMaster();
  }
  
//inventory trans type
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Inventory Trans Type master with valid  data")==0){
      tearDownDBForInventoryTransTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Inventory Trans Type master with duplicate data")==0){
      tearDownDBForInventoryTransTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Inventory Trans Type Master Page")==0){
      tearDownDBForInventoryTransTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Inventory Trans Type Master Page")==0){
      tearDownDBForInventoryTransTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Inventory Trans Type Master")==0){
      tearDownDBForInventoryTransTypeMaster();
  }
  
//indentor type
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Indentor Type master with valid  data")==0){
      tearDownDBForIndentorTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Indentor Type master with duplicate data")==0){
      tearDownDBForIndentorTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Indentor Type Master Page")==0){
      tearDownDBForIndentorTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Indentor Type Master Page")==0){
      tearDownDBForIndentorTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Indentor Type Master")==0){
      tearDownDBForIndentorTypeMaster();
  }
  
//quality
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Quality  master with valid  data")==0){
      tearDownDBForQualityMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Quality  master with duplicate data")==0){
      tearDownDBForQualityMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Quality  master with blank data")==0){
      tearDownDBForQualityMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Quality  Master Page")==0){
      tearDownDBForQualityMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Quality  Master Page")==0){
      tearDownDBForQualityMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Quality  Master")==0){
      tearDownDBForQualityMaster();
  }
  
// order type
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Order Type  master with valid  data")==0){
      tearDownDBForOrderTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Order Type  master with duplicate data")==0){
      tearDownDBForOrderTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Order Type  master with blank data")==0){
      tearDownDBForOrderTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Order Type  Master Page")==0){
      tearDownDBForOrderTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Order Type  Master Page")==0){
      tearDownDBForOrderTypeMaster();
  }
  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Order Type  Master")==0){
      tearDownDBForOrderTypeMaster();
  }
  
//indentor
 if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Indentor Master with valid test data1")==0){
     tearDownDBForIndentorMaster();
 }
 if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Indentor Master with valid test data2")==0){
     tearDownDBForIndentorMaster();
 }
 if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Indentor Master with duplicate test for Indentor code,TIN,PAN,GST data added before the scenario execution")==0){
     tearDownDBForIndentorMaster();
 }
 
 if(StringUtils.compareIgnoreCase(scenario.getName(),"Boundary Value Analysis and Equivalence class Partition for Indentor Master in PAN,GST and TIN")==0){
     tearDownDBForIndentorMaster();
 }
 if(StringUtils.compareIgnoreCase(scenario.getName(),"Entering invalid data for PAN,GST,TIN")==0){
     tearDownDBForIndentorMaster();
 }
 if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Indentor Master with blank test data")==0){
     tearDownDBForIndentorMaster();
 }
 if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Edit button in Indentor Master page")==0){
     tearDownDBForIndentorMaster();
 }
 if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Delete button in Indentor Master page")==0){
     tearDownDBForIndentorMaster();
 }
 if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Indentor Master")==0){
     tearDownDBForIndentorMaster();
 }
 
//customer
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Customer Master with valid test data")==0){
	    tearDownDBForCustomerMaster();
	 }
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Customer Master with duplicate test for Customer code,TIN,PAN,GST data added before the scenario execution")==0){
	    tearDownDBForCustomerMaster();
	 }
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Boundary Value Analysis and Equivalence class Partition for Customer Master in PAN,GST and TIN")==0){
	    tearDownDBForCustomerMaster();
	 }
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Entering invalid data for PAN,GST,TIN in Customer Form")==0){
	    tearDownDBForCustomerMaster();
	 }
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Customer Master with blank test data")==0){
	    tearDownDBForCustomerMaster();
	 }
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Edit button in Customer Master page")==0){
	    tearDownDBForCustomerMaster();
	 }
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Delete button in Customer Master page")==0){
	    tearDownDBForCustomerMaster();
	 }
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Customer Master")==0){
	    tearDownDBForCustomerMaster();
	 }
  
	
//consignee
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Consignee Master with valid test data")==0){
	  tearDownDBForConsigneeMaster();
	}
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Consignee Master with duplicate test for Consignee code,TIN,PAN,GST data added before the scenario execution")==0){
	  tearDownDBForConsigneeMaster();
	}
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Boundary Value Analysis and Equivalence class Partition for Consignee Master in PAN,GST and TIN")==0){
	  tearDownDBForConsigneeMaster();
	}
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Entering invalid data for PAN,GST,TIN in Consignee Form")==0){
	  tearDownDBForConsigneeMaster();
	}
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating business logic of Consignee Master with blank test data")==0){
	  tearDownDBForConsigneeMaster();
	}
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Edit button in Consignee Master page")==0){
		  tearDownDBForConsigneeMaster();
	}
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of Delete button in Consignee Master page")==0){
	  tearDownDBForConsigneeMaster();
	}
	if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Customer Master")==0){
	  tearDownDBForConsigneeMaster();
	}  
  
//tax rate
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Tax  Rate master with valid  data")==0){
	      tearDownDBForTaxRateMaster();
	  }
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Tax  Rate master with duplicate data")==0){
		  tearDownDBForTaxRateMaster();
	  }
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Tax  Rate master with invalid data by using future date for Effective From & past date for Effective To")==0){
		  tearDownDBForTaxRateMaster();
	  }
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Tax  Rate Master Page")==0){
		  tearDownDBForTaxRateMaster();
	  }
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Tax  Rate Master Page")==0){
		  tearDownDBForTaxRateMaster();
	  }
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Tax  Rate Master")==0){
		  tearDownDBForTaxRateMaster();
	  }	
	  
// financial calendar
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Financial Calendar master with valid  data")==0){
	      tearDownDBForFinancialCalendarMaster();
	  }
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Financial Calendar master with duplicate data")==0){
		  tearDownDBForFinancialCalendarMaster();
	  }
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Financial Calendar master with invalid data by using future date for From Date & past date for To Date & (using alphabets in year,quarter,num)")==0){
		  tearDownDBForFinancialCalendarMaster();
	  }
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Validating the Financial Calendar master with blank data")==0){
		  tearDownDBForFinancialCalendarMaster();
	  }
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of edit button in Financial Calendar Master Page")==0){
		  tearDownDBForFinancialCalendarMaster();
	  }
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing the functionality of delete button in Financial Calendar Master Page")==0){
		  tearDownDBForFinancialCalendarMaster();
	  }
	  if(StringUtils.compareIgnoreCase(scenario.getName(),"Testing search box and Export button in Financial Calendar Master")==0){
		  tearDownDBForFinancialCalendarMaster();
	  }	

  
  }
  
  
  
  // LegalEntity
  private void tearDownDBForLegalEntity() {
    base.databaseWaitTime();
    String Org_Group_Code = "IN111";
    base.itemsToBeDeleted.forEach(
        lc -> {
          DBUtilities.deleteFromTable("legal_entity", "entity_code", lc);
        });
    DBUtilities.deleteFromTable("org_group", "group_code", Org_Group_Code);
  }
  
 
  
  
 
  
  //teardown db for gsm
  private void tearDownDBForGSMMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("gsm","gsm_code",lc);
      });
  }
  private void tearDownDBForPlantTypeMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("plant_type","plant_type_code",lc);
      });
  }
  private void tearDownDBForFormFactorMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("form_factor","form_factor",lc);
      });
  }
  private void tearDownDBForProductGroupMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("product_group","product_group_code",lc);
      });
  }
  private void tearDownDBForItemTypeMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("item_type","item_type_code",lc);
      });
  }
  private void tearDownDBForPriceTypeMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("price_type","price_code",lc);
      });
  }
  private void tearDownDBForPaymentTermsMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("payment_terms","payment_name",lc);
      });
  }
  private void tearDownDBForCustomerTypeMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("customer_type","customer_type_code",lc);
      });
  }
  private void tearDownDBForIndentTypeMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("indent_type","indent_type_name",lc);
      });
  }
  private void tearDownDBForInventoryTransTypeMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("inventory_trans_type","trans_type",lc);
      });
  }
  private void tearDownDBForIndentorTypeMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("indentor_type","indentor_type_code",lc);
      });
  }
  private void tearDownDBForQualityMaster(){
	  base.databaseWaitTime();
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("quality","quality_code",lc);
      });
      DBUtilities.deleteFromTable("product_group", "product_group_code", "PG25");
  }
  
//tear down db for order type(has foreign key) master
  
  private void tearDownDBForOrderTypeMaster(){
	  base.databaseWaitTime();
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("order_type","order_type",lc);
      });
      DBUtilities.deleteFromTable("plant", "plant_code", "2222");
      
      base.databaseWaitTime();
	    String Org_Group_Code = "I_IN111";
	    base.itemsToBeDeleted.add("0002");
	    base.itemsToBeDeleted.forEach(
	        lc -> {
	          DBUtilities.deleteFromTable("legal_entity", "entity_code",lc);
	        });
	    DBUtilities.deleteFromTable("org_group", "group_code", Org_Group_Code);
	    DBUtilities.deleteFromTable("plant_type","plant_type_code","1_12");
  }
  
  //tear down db for indentor(has foreign key) master
  private void tearDownDBForIndentorMaster()  {
	    base.itemsToBeDeleted.forEach(
	        lc -> {
	          DBUtilities.deleteFromTable("indentor","indentor_code", lc);
	        });
	    base.databaseWaitTime();
	    DBUtilities.deleteFromTable("indentor_type", "indentor_type_code", "IND02");
	    
	 }
  
  
  //tear down db for customer(has foreign key[customer type&indentor])  1(2[1])
  private void tearDownDBForCustomerMaster()  {
	    base.itemsToBeDeleted.forEach(
	        lc -> {
	          DBUtilities.deleteFromTable("customer","customer_code", lc);
	        });
	    base.databaseWaitTime();
	   /* base.itemsToBeDeleted.add("INDC24");
	    base.itemsToBeDeleted.forEach(
		        lc -> {
		          DBUtilities.deleteFromTable("indentor", "indentor_code",lc);
		        });*/
	    DBUtilities.deleteFromTable("indentor", "indentor_code", "INDC25");
	    base.databaseWaitTime();
	    /*base.itemsToBeDeleted.add("IND021");
	    base.itemsToBeDeleted.forEach(
		        lc -> {
		          DBUtilities.deleteFromTable("indentor_type", "indentor_type_code",lc);
		        });
	    base.databaseWaitTime();*/
	    DBUtilities.deleteFromTable("indentor_type", "indentor_type_code", "IND021");
	    DBUtilities.deleteFromTable("customer_type", "customer_type_code", "D25");
	    
	    
	 }
  
  //tear down db for consignee(has foreign key[customer[customer type,indentor type ,indentor]&indentor)  1(2[2])
  private void tearDownDBForConsigneeMaster()  {
	  base.databaseWaitTime();
	  
	  base.itemsToBeDeleted.forEach(
	        lc -> {
	          DBUtilities.deleteFromTable("consignee","consignee_code", lc);
	        });
	    base.databaseWaitTime();
	    DBUtilities.deleteFromTable("customer","customer_code", "CSTC24");
	    base.databaseWaitTime();
	    
	    base.itemsToBeDeleted.add("INDC25");
	    base.itemsToBeDeleted.forEach(
		        lc -> {
		          DBUtilities.deleteFromTable("indentor", "indentor_code", lc);
		        });
	    
	    //DBUtilities.deleteFromTable("indentor", "indentor_code", "INDC25");
	    base.databaseWaitTime();
	    
	    DBUtilities.deleteFromTable("indentor_type", "indentor_type_code", "IND021");
	    base.databaseWaitTime();
	    DBUtilities.deleteFromTable("customer_type", "customer_type_code", "D25");
	    
	    
	 }
  //tear down db for tax rate
  private void tearDownDBForTaxRateMaster(){
	  base.databaseWaitTime();
	  
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("tax_rate","tax_code",lc);
      });
      
  }

//tear down db for financial calendar
  private void tearDownDBForFinancialCalendarMaster(){
      base.itemsToBeDeleted.forEach(lc->{
          DBUtilities.deleteFromTable("fin_cal","fin_cal_code",lc);
      });
      DBUtilities.deleteFromTable("fin_cal_type","calendar_code","1901");
  }
  
  
  
  @AfterStep
  public void addScreenshot(Scenario scenario) {
    if (scenario.isFailed()) {
      WebDriver driver = DriverFactory.getDriver();
      final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
      scenario.attach(screenshot, "image/png", "image");
    }
  }
}
