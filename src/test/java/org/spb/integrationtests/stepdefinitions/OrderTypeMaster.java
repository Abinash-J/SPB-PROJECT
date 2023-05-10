package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.OrderTypePOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class OrderTypeMaster {


	private static String url = "ordertype";
	  BaseClass base = new BaseClass();
	  OrderTypePOM odrtyp= new OrderTypePOM();
	  private String ordertypeCode;
	  private String ordertypeDesc;
	  private String ordertypeCat;

	  public OrderTypeMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens
	  @When("Delete data initially from database in Order Type")
	  public void deletedatainitiallyfromdatabaseinordertype() {
		  //DBUtilities.deleteFromTable("plant","plant_code","2222");
		  //DBUtilities.deleteFromTable("order_type","order_type","ORDER_01");
	  }

	  @Given("A web browser is on the Order Type  master page")
	  public void awebbrowserisontheordertypemasterpage() {
		  BaseClass.driver.get(BaseClass.baseURL + url);
	  }

	  @When("New Order Type  button is clicked on page")
	  public void newordertypebuttonisclickedonpage() {
		  base.click(odrtyp.getNew_OrderType());
	      base.WaitUntilVisible(odrtyp.getOrderType_Code());
	  }

	  @Then("A popup with Order Type  should be displayed when clicked")
	  public void apopupwithordertypeshouldbedisplayedwhenclicked() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		   WebElement orderTypePage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
	       String orderTypePopup = orderTypePage.getText();
	       assertEquals("Order Type",orderTypePopup);
	  }

	  @Then("A popup with submit button has shown in Order Type  page")
	  public void apopupwithsubmitbuttonhasshowninordertypepage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String submit = submitbtn.getText();
          assertEquals("Submit",submit);
	  }

//scenario outline: valid data
	  
	  @Given("New Order Type  button is clicked")
	  public void newordertypebuttonisclicked() {
		  base.click(odrtyp.getNew_OrderType());
	      //base.WaitUntilVisible(odrtyp.getOrderType_Code());
	  }

	  @Then("Entering the data in {string},{string},{string} text box in Order Type")
	  public void enteringthedataintextboxinordertype(String order_type_code, String order_type_desc, String order_type_cat) {
		  base.implicitwait();
		  this.ordertypeCode=order_type_code;
		  this.ordertypeDesc=order_type_desc;
	      this.ordertypeCat=order_type_cat;
	      
	      base.itemsToBeDeleted.add(order_type_code);
	      
	      base.WaitUntil(odrtyp.getOrderType_Code());
	      base.sendkeys(odrtyp.getOrderType_Code(), order_type_code);
	      
	      base.WaitUntil(odrtyp.getDescription());
	      base.sendkeys(odrtyp.getDescription(), order_type_desc);
	      
	      base.WaitUntil(odrtyp.getOrderCategory());
	      base.sendkeys(odrtyp.getOrderCategory(), order_type_cat);
	      
	      base.selectClass(odrtyp.getPlant_dropdown());
	      base.selectByVisibleText("2222");
	      base.WaitUntil(odrtyp.getSubmit());
	     
	  }

	  @When("Click on submit button in Order Type  page")
	  public void clickonsubmitbuttoninordertypepage() {
	      base.click(odrtyp.getSubmit());
	  }

	  @Then("A popop with message successfully added should be displayed in Order Type  page")
	  public void apopopwithmessagesuccessfullyaddedshouldbedisplayedinordertypepage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String successmsg = validdata.getText();
          assertEquals("Saved Successfully",successmsg);
	  }

	  @Then("The entered data is available in Order Type  table")
	  public void theentereddataisavailableinordertypetable() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  HashMap<String, String> uniqueRowInTable =
			        DBUtilities.findUniqueRowInTable("order_type","order_type",this.ordertypeCode);
			    assertEquals(this.ordertypeDesc, uniqueRowInTable.get("description"));
	  }

//scenario outline: duplicate data
	  
	  @Given("Set data in Order Type  table")
	  public void setdatainordertypetable() {
          base.databaseWaitTime();
		  
			
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("order_type","Order_Type7");
		  	values.put("description","Order_Desc7");
		  	values.put("order_category","Order_Cat7");
		  	
		  	values.put("plant_code","2222");
		  	
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	DBUtilities.insertIntoTable("order_type", values);
	  }

	  @Then("Entering the duplicate data in {string},{string},{string} text box in Order Type")
	  public void enteringtheduplicatedataintextboxinordertype(String order_type_code, String order_type_desc, String order_type_cat) {
		  base.implicitwait();
		  this.ordertypeCode=order_type_code;
		  this.ordertypeDesc=order_type_desc;
	      this.ordertypeCat=order_type_cat;
	      
	      base.itemsToBeDeleted.add(order_type_code);
	      
	      base.WaitUntil(odrtyp.getOrderType_Code());
	      base.sendkeys(odrtyp.getOrderType_Code(), order_type_code);
	      
	      base.WaitUntil(odrtyp.getDescription());
	      base.sendkeys(odrtyp.getDescription(), order_type_desc);
	      
	      base.WaitUntil(odrtyp.getOrderCategory());
	      base.sendkeys(odrtyp.getOrderCategory(), order_type_cat);
	      
	      base.selectClass(odrtyp.getPlant_dropdown());
	      base.selectByVisibleText("2222");
	      base.WaitUntil(odrtyp.getSubmit());
	  }

	  @Then("A popop with message already exists should be displayed in Order Type  page")
	  public void apopopwithmessagealreadyexistsshouldbedisplayedinordertypepage() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  String errormessage = errmsg.getText();
	      assertEquals("Already exist",errormessage);
	  }

//scenario outline:blank data
	  
	  @Then("Entering the blank data in {string},{string},{string} text box in Order Type")
	  public void enteringtheblankdataintextboxinordertype(String order_type_code, String order_type_desc, String order_type_cat) {
		  base.implicitwait();
		  this.ordertypeCode=order_type_code;
		  this.ordertypeDesc=order_type_desc;
	      this.ordertypeCat=order_type_cat;
	      
	      base.itemsToBeDeleted.add(order_type_code);
	      
	      base.WaitUntil(odrtyp.getOrderType_Code());
	      base.sendkeys(odrtyp.getOrderType_Code(), order_type_code);
	      
	      base.WaitUntil(odrtyp.getDescription());
	      base.sendkeys(odrtyp.getDescription(), order_type_desc);
	      
	      base.WaitUntil(odrtyp.getOrderCategory());
	      base.sendkeys(odrtyp.getOrderCategory(), order_type_cat);
	      
	      base.selectClass(odrtyp.getPlant_dropdown());
	      base.selectByVisibleText("2222");
	      base.WaitUntil(odrtyp.getSubmit());
	  }

	  @Then("A popop with message Please enter all mandatory fields should be displayed in Order Type  page")
	  public void apopopwithmessagepleaseenterallmandatoryfieldsshouldbedisplayedinordertypepage() throws InterruptedException {
		  Thread.sleep(2000);   
		  base.implicitwait();
		  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String blankmessage = blnkmsg.getText();
	      assertEquals("Please Enter all mandatory fields",blankmessage);
	  }

//scenario outline:testing functionality of edit button
	  
	  @Given("Clicking the close button in Order Type")
	  public void clickingtheclosebuttoninordertype() {
		  base.WaitUntilVisible(odrtyp.getCloseButton());
		  base.click(odrtyp.getCloseButton());
	  }

//edit
	  @Then("Checking the functionality of edit button and by clicking in Order Type")
	  public void checkingthefunctionalityofeditbuttonandbyclickinginordertype() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(odrtyp.getSearchButton(),"Order_Type7");
		  base.WaitUntilVisible(odrtyp.getEditIcon());
		  base.click(odrtyp.getEditIcon());
		  
		  base.WaitUntilVisible(odrtyp.getOrderType_Code());
		  odrtyp.getOrderType_Code().clear();
		  base.sendkeys(odrtyp.getOrderType_Code(), "order_type8");
		  
		  base.WaitUntilVisible(odrtyp.getDescription());
		  odrtyp.getDescription().clear();
		  base.sendkeys(odrtyp.getDescription(), "order_desc8");
		  
		  base.WaitUntilVisible(odrtyp.getOrderCategory());
		  odrtyp.getOrderCategory().clear();
		  base.sendkeys(odrtyp.getOrderCategory(), "order_cat8");
		  base.click(odrtyp.getSubmit());
	  }

	  @Then("Checking the update button has shown in the Order Type  page")
	  public void checkingtheupdatebuttonhasshownintheordertypepage() throws InterruptedException {
		   
			
			base.refreshApp(BaseClass.baseURL+url);
			Thread.sleep(2000);
			  base.sendkeys(odrtyp.getSearchButton(),"order_type8");
			  base.WaitUntilVisible(odrtyp.getEditIcon());
			  base.click(odrtyp.getEditIcon());
			  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
		      String update = updatebtn.getText();
	          assertEquals("Update",update);
	          
	          base.click(odrtyp.getCloseButton());
	  }

	  @Then("The edited data should be available in database of Order Type")
	  public void theediteddatashouldbeavailableindatabaseofordertype() throws InterruptedException {
		  base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("order_type", "order_type", "order_type8");
			 assertEquals("order_type8",uniqueRowInTable.get("order_type"));
	  }
	  
//scenario outline: testing functionality of delete button
	  
	  @Then("Checking the functionality of delete button by clicking in Order Type")
	  public void checkingthefunctionalityofdeletebuttonbyclickinginordertype() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  base.implicitwait();
		  Thread.sleep(2000);
		  base.sendkeys(odrtyp.getSearchButton(),"Order_Type7");
		  base.WaitUntil(odrtyp.getDeleteIcon());
		  base.click(odrtyp.getDeleteIcon());
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Order Type")
	  public void clickingdeletebuttonandapopupdeletedsucessfullyshouldbedisplayedinordertype() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
//delete icon popup 
		  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
	      String dltmessage = deletemsg.getText();
          assertEquals("Are you Sure ?",dltmessage);
//delete msg popup        
          base.click(odrtyp.getDeleteButtonPopup());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
	  }

	  @Then("Verifying that the entered data was deleted in database of Order Type")
	  public void verifyingthattheentereddatawasdeletedindatabaseofordertype() throws InterruptedException {
		  Thread.sleep(2000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("order_type","order_type","order_type7");
			assertEquals("1",uniqueRowInTable.get("deleted"));
			DBUtilities.deleteFromTable("order_type","order_type","order_type7");
	  }

//search box
	  
	  @Then("Searching the Order Type  code by sending keys")
	  public void searchingtheordertypecodebysendingkeys() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.click(odrtyp.getSearchButton());
			base.sendkeys(odrtyp.getSearchButton(),"Order_Type7");
	  }

	  @Then("The text in the search box should be equal to values in the Order Type  table")
	  public void thetextinthesearchboxshouldbeequaltovaluesintheordertypetable() throws InterruptedException {
		  Thread.sleep(1000);
			assertEquals(odrtyp.getSearchCheck().getText(),"Order_Type7");
			base.databaseWaitTime();
			DBUtilities.deleteFromTable("order_type","order_type","Order_Type7");
	  }

	  @Then("Clicking Export button in Order Type  master page")
	  public void clickingexportbuttoninordertypemasterpage() {
		  base.WaitUntil(odrtyp.getExport());
		 	base.click(odrtyp.getExport());
		 	
		 	//DBUtilities.deleteFromTable("plant","plant_code","2222");
		 	
	  }


}
