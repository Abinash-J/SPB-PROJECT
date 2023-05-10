package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.InventoryTransTypePOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InventoryTransTypeMaster {

	private static String url = "inventorytranstypes";
	  BaseClass base = new BaseClass();
	  InventoryTransTypePOM invtrnstyp= new InventoryTransTypePOM();
	  private String inventorytranstypeCode;
	  private String inventorytranstypeName;
	  private String inventorytranstypeDesc;

	  public InventoryTransTypeMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens
	
	  @When("Delete data initially from database in Inventory Trans Type")
	  public void deletedatainitiallyfromdatabaseininventorytranstype() {
		  DBUtilities.deleteFromTable("inventory_trans_type","trans_type","Type A");
		  DBUtilities.deleteFromTable("inventory_trans_type","trans_type","Type B");
		  DBUtilities.deleteFromTable("inventory_trans_type","trans_type","type c");
	  }

	  @Given("A web browser is on the Inventory Trans Type master page")
	  public void awebbrowserisontheinventorytranstypemasterpage() {
		  BaseClass.driver.get(BaseClass.baseURL + url);
	  }

	  @When("New Inventory Trans Type button is clicked on page")
	  public void newinventorytranstypebuttonisclickedonpage() {
		  base.click(invtrnstyp.getNew_InventoryTransType());
	      base.WaitUntilVisible(invtrnstyp.getInventoryTransType_Code());
	  }

	  @Then("A popup with Inventory Trans Type should be displayed when clicked")
	  public void apopupwithinventorytranstypeshouldbedisplayedwhenclicked() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		   WebElement InventoryTransTypeTypePage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
	       String InventoryTransTypePopup = InventoryTransTypeTypePage.getText();
	       assertEquals("Inventory Trans Type",InventoryTransTypePopup);
	       
	       
	  }

	  @Then("A popup with submit button has shown in Inventory Trans Type page")
	  public void apopupwithsubmitbuttonhasshownininventorytranstypepage() throws InterruptedException {
		 
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String submit = submitbtn.getText();
          assertEquals("Submit",submit);
          
	  }

//scenario outline: valid data
	  
	  @Given("New Inventory Trans Type button is clicked")
	  public void newinventorytranstypebuttonisclicked() {
		  base.click(invtrnstyp.getNew_InventoryTransType());
	      base.WaitUntilVisible(invtrnstyp.getInventoryTransType_Code());
	  }

	  @Then("Entering the data in {string},{string},{string} text box in Inventory Trans Type")
	  public void enteringthedataintextboxininventorytranstype(String inventory_trans_type_code, String inventory_trans_type_name, String inventory_trans_type_desc) {
		  base.implicitwait();
		  this.inventorytranstypeCode=inventory_trans_type_code;
		  this.inventorytranstypeName=inventory_trans_type_code;
	      this.inventorytranstypeDesc=inventory_trans_type_desc;
	      base.itemsToBeDeleted.add(inventory_trans_type_code);
	      base.sendkeys(invtrnstyp.getInventoryTransType_Code(), inventory_trans_type_code);
	      
	      base.sendkeys(invtrnstyp.getTrans_Name(), inventory_trans_type_name);
	      base.WaitUntil(invtrnstyp.getDescription());
	      base.sendkeys(invtrnstyp.getDescription(), inventory_trans_type_desc);
	      base.WaitUntil(invtrnstyp.getSubmit());
	  }

	  @When("Click on submit button in Inventory Trans Type page")
	  public void clickonsubmitbuttonininventorytranstypepage() {
	      base.click(invtrnstyp.getSubmit());
	  }

	  @Then("A popop with message successfully added should be displayed in Inventory Trans Type page")
	  public void apopopwithmessagesuccessfullyaddedshouldbedisplayedininventorytranstypepage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String successmsg = validdata.getText();
          assertEquals("Saved Successfully",successmsg);
	  }

	  @Then("The entered data is available in Inventory Trans Type table")
	  public void theentereddataisavailableininventorytranstypetable() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  HashMap<String, String> uniqueRowInTable =
			        DBUtilities.findUniqueRowInTable("inventory_trans_type","trans_type",this.inventorytranstypeCode);
			    assertEquals(this.inventorytranstypeDesc, uniqueRowInTable.get("trans_description"));
	  }

//scenario outline: duplicate data
	  
	  @Given("Set data in Inventory Trans Type table")
	  public void setdataininventorytranstypetable() {
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("trans_type","Type B");
		  	values.put("trans_name","TypeB");
		  	values.put("trans_description","desc2");
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	DBUtilities.insertIntoTable("inventory_trans_type", values);
	  }

	  @Then("Entering the duplicate data in {string},{string},{string} text box in Inventory Trans Type")
	  public void enteringtheduplicatedataintextboxininventorytranstype(String inventory_trans_type_code, String inventory_trans_type_desc, String inventory_trans_type_name) {
		  base.implicitwait();
		  this.inventorytranstypeCode=inventory_trans_type_code;
		  this.inventorytranstypeName=inventory_trans_type_code;
	      this.inventorytranstypeDesc=inventory_trans_type_desc;
	      base.itemsToBeDeleted.add(inventory_trans_type_code);
	      base.sendkeys(invtrnstyp.getInventoryTransType_Code(), inventory_trans_type_code);
	      
	      base.sendkeys(invtrnstyp.getTrans_Name(), inventory_trans_type_name);
	      base.WaitUntil(invtrnstyp.getDescription());
	      base.sendkeys(invtrnstyp.getDescription(), inventory_trans_type_desc);
	      base.WaitUntil(invtrnstyp.getSubmit());
	  }

	  @Then("A popop with message already exists should be displayed in Inventory Trans Type page")
	  public void apopopwithmessagealreadyexistsshouldbedisplayedininventorytranstypepage() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  String errormessage = errmsg.getText();
	      assertEquals("Already exist",errormessage);
	  }

//scenario outline:blank data
	  
	  @Then("Entering the blank data in {string},{string},{string} text box in Inventory Trans Type")
	  public void enteringtheblankdataintextboxininventorytranstype(String inventory_trans_type_code, String inventory_trans_type_desc, String inventory_trans_type_name) {
		  base.implicitwait();
		  this.inventorytranstypeCode=inventory_trans_type_code;
		  this.inventorytranstypeName=inventory_trans_type_code;
	      this.inventorytranstypeDesc=inventory_trans_type_desc;
	      base.itemsToBeDeleted.add(inventory_trans_type_code);
	      base.sendkeys(invtrnstyp.getInventoryTransType_Code(), inventory_trans_type_code);
	      
	      base.sendkeys(invtrnstyp.getTrans_Name(), inventory_trans_type_name);
	      base.WaitUntil(invtrnstyp.getDescription());
	      base.sendkeys(invtrnstyp.getDescription(), inventory_trans_type_desc);
	      base.WaitUntil(invtrnstyp.getSubmit());
	  }

	  @Then("A popop with message Please enter all mandatory fields should be displayed in Inventory Trans Type page")
	  public void apopopwithmessagepleaseenterallmandatoryfieldsshouldbedisplayedininventorytranstypepage() throws InterruptedException {
		  Thread.sleep(2000);   
		  base.implicitwait();
		  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String blankmessage = blnkmsg.getText();
	      assertEquals("Please Enter all mandatory fields",blankmessage);
	  }

//scenario outline:testing functionality of edit button
	  
	  @Given("Clicking the close button in Inventory Trans Type")
	  public void clickingtheclosebuttonininventorytranstype() {
		  base.WaitUntilVisible(invtrnstyp.getCloseButton());
		     base.click(invtrnstyp.getCloseButton());
	  }
//edit
	  @Then("Checking the functionality of edit button and by clicking in Inventory Trans Type")
	  public void checkingthefunctionalityofeditbuttonandbyclickingininventorytranstype() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(invtrnstyp.getSearchButton(),"Type B");
		  base.WaitUntilVisible(invtrnstyp.getEditIcon());
		  base.click(invtrnstyp.getEditIcon());
		  
		  base.WaitUntilVisible(invtrnstyp.getInventoryTransType_Code());
		  invtrnstyp.getInventoryTransType_Code().clear();
		  base.sendkeys(invtrnstyp.getInventoryTransType_Code(), "type c");
		  
		  base.WaitUntilVisible(invtrnstyp.getTrans_Name());
		  invtrnstyp.getTrans_Name().clear();
		  base.sendkeys(invtrnstyp.getTrans_Name(), "typec ");
		  
		  base.WaitUntilVisible(invtrnstyp.getDescription());
		  invtrnstyp.getDescription().clear();
		  base.sendkeys(invtrnstyp.getDescription(), "desc3");
		  base.click(invtrnstyp.getSubmit());
	  }

	  @Then("Checking the update button has shown in the Inventory Trans Type page")
	  public void checkingtheupdatebuttonhasshownintheinventorytranstypepage() throws InterruptedException {
		  base.click(invtrnstyp.getEditIcon()); 
			Thread.sleep(2000);
			base.refreshApp(BaseClass.baseURL+url);
			  base.sendkeys(invtrnstyp.getSearchButton(),"type c");
			  base.WaitUntilVisible(invtrnstyp.getEditIcon());
			  base.click(invtrnstyp.getEditIcon());
			  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
		      String update = updatebtn.getText();
	          assertEquals("Update",update);
	          
	          base.click(invtrnstyp.getCloseButton());
	  }

	  @Then("The edited data should be available in database of Inventory Trans Type")
	  public void theediteddatashouldbeavailableindatabaseofinventorytranstype() throws InterruptedException {
		  base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("inventory_trans_type", "trans_type", "type c");
			 assertEquals("type c",uniqueRowInTable.get("trans_type"));
	  }

//scenario outline: testing functionality of delete button
	  
	  @Then("Checking the functionality of delete button by clicking in Inventory Trans Type")
	  public void checkingthefunctionalityofdeletebuttonbyclickingininventorytranstype() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  base.implicitwait();
		  Thread.sleep(2000);
		  base.sendkeys(invtrnstyp.getSearchButton(),"Type B");
		  base.WaitUntil(invtrnstyp.getDeleteIcon());
		  base.click(invtrnstyp.getDeleteIcon());
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Inventory Trans Type")
	  public void clickingdeletebuttonandapopupdeletedsucessfullyshouldbedisplayedininventorytranstype() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
//delete icon popup 
		  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
	      String dltmessage = deletemsg.getText();
          assertEquals("Are you Sure ?",dltmessage);
//delete msg popup        
          base.click(invtrnstyp.getDeleteButtonPopup());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
	  }

	  @Then("Verifying that the entered data was deleted in database of Inventory Trans Type")
	  public void verifyingthattheentereddatawasdeletedindatabaseofinventorytranstype() throws InterruptedException {
		  Thread.sleep(2000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("inventory_trans_type","trans_type","type c");
			assertEquals("1",uniqueRowInTable.get("deleted"));
			DBUtilities.deleteFromTable("inventory_trans_type","trans_type","type c");
	  }

//search box  
	  
	  @Then("Searching the Trans Type by sending keys")
	  public void searchingthetranstypebysendingkeys() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.click(invtrnstyp.getSearchButton());
			base.sendkeys(invtrnstyp.getSearchButton(),"Type B");
	  }

	  @Then("The text in the search box should be equal to values in the Inventory Trans Type table")
	  public void thetextinthesearchboxshouldbeequaltovaluesintheinventorytranstypetable() throws InterruptedException {
		  Thread.sleep(1000);
			assertEquals(invtrnstyp.getSearchCheck().getText(),"Type B");
			base.databaseWaitTime();
			DBUtilities.deleteFromTable("inventory_trans_type","trans_type","Type B");
	  }

	  @Then("Clicking Export button in Inventory Trans Type master page")
	  public void clickingexportbuttonininventorytranstypemasterpage() {
		  base.WaitUntil(invtrnstyp.getExport());
		 	base.click(invtrnstyp.getExport());
	  }
	
	
	
}
