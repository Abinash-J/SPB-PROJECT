package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.IndentorTypePOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class IndentorTypeMaster {

	private static String url = "indentortype";
	  BaseClass base = new BaseClass();
	  IndentorTypePOM indentorType= new IndentorTypePOM();
	  private String indentortypeCode;
	  private String indentortypeDesc;

	  public IndentorTypeMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens

		@When("Delete data initially from database in Indentor Type")
		public void deletedatainitiallyfromdatabaseinindentortype() {
			DBUtilities.deleteFromTable("indentor_type","indentor_type_code","IND04");
			DBUtilities.deleteFromTable("indentor_type","indentor_type_code","IND02");
			DBUtilities.deleteFromTable("indentor_type","indentor_type_code","ind03");
			//DBUtilities.deleteFromTable("indentor_type","indentor_type_code","IND03");
		}
		
		@Given("A web browser is on the Indentor Type master page")
		public void awebbrowserisontheindentortypemasterpage() {
			BaseClass.driver.get(BaseClass.baseURL + url);
		}
		
		@When("New Indentor Type button is clicked on page")
		public void newindentortypebuttonisclickedonpage() {
			base.click(indentorType.getNew_IndentorType());
		      base.WaitUntilVisible(indentorType.getIndentorType_Code());
		}
		
		@Then("A popup with Indentor Type should be displayed when clicked")
		public void apopupwithindentortypeshouldbedisplayedwhenclicked() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
			   WebElement indentorTypePage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
		       String indentortypePopup = indentorTypePage.getText();
		       assertEquals("Indentor Type",indentortypePopup);
		}
		
		@Then("A popup with submit button has shown in Indentor Type page")
		public void apopupwithsubmitbuttonhasshowninindentortypepage() throws InterruptedException {
			 Thread.sleep(2000);
			  base.implicitwait();
			  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
		      String submit = submitbtn.getText();
	          assertEquals("Submit",submit);
		}
		
//scenario outline: valid data
		
		@Given("New Indentor Type button is clicked")
		public void newindentortypebuttonisclicked() {
			base.click(indentorType.getNew_IndentorType());
		      base.WaitUntilVisible(indentorType.getIndentorType_Code());
		}
		
		@Then("Entering the data in {string},{string} text box in Indentor Type")
		public void enteringthedataintextboxinindentortype(String indentor_type_code, String indendor_type_desc) {
			base.implicitwait();
			  this.indentortypeCode=indentor_type_code;
		      this.indentortypeDesc=indendor_type_desc;
		      base.itemsToBeDeleted.add(indentor_type_code);
		      base.sendkeys(indentorType.getIndentorType_Code(), indentor_type_code);
		      base.WaitUntil(indentorType.getDescription());
		      base.sendkeys(indentorType.getDescription(), indendor_type_desc);
		      base.WaitUntil(indentorType.getSubmit());
		      
		}
		
		@When("Click on submit button in Indentor Type page")
		public void clickonsubmitbuttoninindentortypepage() {
		    base.click(indentorType.getSubmit());
		}
		
		@Then("A popop with message successfully added should be displayed in Indentor Type page")
		public void apopopwithmessagesuccessfullyaddedshouldbedisplayedinindentortypepage() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
			  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		      String successmsg = validdata.getText();
	          assertEquals("Saved Successfully",successmsg);
		}
		
		@Then("The entered data is available in Indentor Type table")
		public void theentereddataisavailableinindentortypetable() throws InterruptedException {
			base.implicitwait();
			  Thread.sleep(2000);
			  HashMap<String, String> uniqueRowInTable =
				        DBUtilities.findUniqueRowInTable("indentor_type","indentor_type_code",this.indentortypeCode);
				    assertEquals(this.indentortypeDesc, uniqueRowInTable.get("description"));
		}
		
//scenario outline: duplicate data
		
		@Given("Set data in Indentor Type table")
		public void setdatainindentortypetable() {
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
		
		@Then("Entering the duplicate data in {string},{string} text box in Indentor Type")
		public void enteringtheduplicatedataintextboxinindentortype(String indentor_type_code, String indendor_type_desc) {
			base.implicitwait();
			  this.indentortypeCode=indentor_type_code;
		      this.indentortypeDesc=indendor_type_desc;
		      base.itemsToBeDeleted.add(indentor_type_code);
		      base.sendkeys(indentorType.getIndentorType_Code(), indentor_type_code);
		      base.WaitUntil(indentorType.getDescription());
		      base.sendkeys(indentorType.getDescription(), indendor_type_desc);
		      base.WaitUntil(indentorType.getSubmit());
		      
		}
		
		@Then("A popop with message already exists should be displayed in Indentor Type page")
		public void apopopwithmessagealreadyexistsshouldbedisplayedinindentortypepage() throws InterruptedException {
			base.implicitwait();
			  Thread.sleep(2000);
			  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
			  String errormessage = errmsg.getText();
		      assertEquals("Already exist",errormessage);
		}
		
//scenario outline:blank data
		
		@Then("Entering the blank data in {string},{string} text box in Indentor Type")
		public void enteringtheblankdataintextboxinindentortype(String indentor_type_code, String indendor_type_desc) {
			base.implicitwait();
			  this.indentortypeCode=indentor_type_code;
		      this.indentortypeDesc=indendor_type_desc;
		      base.itemsToBeDeleted.add(indentor_type_code);
		      base.sendkeys(indentorType.getIndentorType_Code(), indentor_type_code);
		      base.WaitUntil(indentorType.getDescription());
		      base.sendkeys(indentorType.getDescription(), indendor_type_desc);
		      base.WaitUntil(indentorType.getSubmit());
		      
		}
		
		@Then("A popop with message Please enter all mandatory fields should be displayed in Indentor Type page")
		public void apopopwithmessagepleaseenterallmandatoryfieldsshouldbedisplayedinindentortypepage() throws InterruptedException {
			 Thread.sleep(2000);   
			  base.implicitwait();
			  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		      String blankmessage = blnkmsg.getText();
		      assertEquals("Please Enter all mandatory fields",blankmessage);
		}
		
//scenario outline:testing functionality of edit button
		
		@Given("Clicking the close button in Indentor Type")
		public void clickingtheclosebuttoninindentortype() {
			base.WaitUntilVisible(indentorType.getCloseButton());
		     base.click(indentorType.getCloseButton());
		}
		
//edit
		@Then("Checking the functionality of edit button and by clicking in Indentor Type")
		public void checkingthefunctionalityofeditbuttonandbyclickinginindentortype() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
			  base.refreshApp(BaseClass.baseURL+url);
			  base.sendkeys(indentorType.getSearchButton(),"IND02");
			  base.WaitUntilVisible(indentorType.getEditIcon());
			  base.click(indentorType.getEditIcon());
			  
			  base.WaitUntilVisible(indentorType.getIndentorType_Code());
			  indentorType.getIndentorType_Code().clear();
			  base.sendkeys(indentorType.getIndentorType_Code(), "ind03");
			  base.WaitUntilVisible(indentorType.getDescription());
			  indentorType.getDescription().clear();
			  base.sendkeys(indentorType.getDescription(), "indentor3");
			  base.click(indentorType.getSubmit());
		}
		
		@Then("Checking the update button has shown in the Indentor Type page")
		public void checkingtheupdatebuttonhasshownintheindentortypepage() throws InterruptedException {
			 
			Thread.sleep(2000);
			base.refreshApp(BaseClass.baseURL+url);
			base.sendkeys(indentorType.getSearchButton(),"IND02");
			  base.WaitUntilVisible(indentorType.getEditIcon());
			  base.click(indentorType.getEditIcon());
			  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
		      String update = updatebtn.getText();
	          //assertEquals("Update",update);
	          base.click(indentorType.getCloseButton());
		}
		
		@Then("The edited data should be available in database of Indentor Type")
		public void theediteddatashouldbeavailableindatabaseofindentortype() throws InterruptedException {
			 base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("indentor_type", "indentor_type_code","ind03");
			 assertEquals("ind03",uniqueRowInTable.get("indentor_type_code"));
		}
		
//scenario outline: testing functionality of delete button
		
		@Then("Checking the functionality of delete button by clicking in Indentor Type")
		public void checkingthefunctionalityofdeletebuttonbyclickinginindentortype() throws InterruptedException {
			base.refreshApp(BaseClass.baseURL+url);
			  base.implicitwait();
			  Thread.sleep(2000);
			  base.sendkeys(indentorType.getSearchButton(),"IND02");
			  base.WaitUntil(indentorType.getDeleteIcon());
			  base.click(indentorType.getDeleteIcon());
		}
		
		@Then("Clicking delete button and a popup deleted sucessfully should be displayed in Indentor Type")
		public void clickingdeletebuttonandapopupdeletedsucessfullyshouldbedisplayedinindentortype() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
	//delete icon popup 
			  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
		      String dltmessage = deletemsg.getText();
	          assertEquals("Are you Sure ?",dltmessage);
	//delete msg popup        
	          base.click(indentorType.getDeleteButtonPopup());
			  Thread.sleep(2000);
			  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		      String deletesuccess = dltsccsflymsg.getText();
	          assertEquals("Deleted Successfully",deletesuccess);
		}
		
		@Then("Verifying that the entered data was deleted in database of Indentor Type")
		public void verifyingthattheentereddatawasdeletedindatabaseofindentortype() throws InterruptedException {
			Thread.sleep(2000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("indentor_type","indentor_type_code","ind03");
			assertEquals("1",uniqueRowInTable.get("deleted"));
			DBUtilities.deleteFromTable("indentor_type","indentor_type_code","ind03");
			DBUtilities.deleteFromTable("indentor_type","indentor_type_code","IND03");
		}
		
//search box
		
		@Then("Searching the Indentor Type code by sending keys")
		public void searchingtheindentortypecodebysending_keys() throws InterruptedException {
			base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.click(indentorType.getSearchButton());
			base.sendkeys(indentorType.getSearchButton(),"IND02");
		}
		
		@Then("The text in the search box should be equal to values in the Indentor Type table")
		public void thetextinthesearchboxshouldbeequaltovaluesintheindentortypetable() throws InterruptedException {
			Thread.sleep(1000);
			assertEquals(indentorType.getSearchCheck().getText(),"IND02");
			base.databaseWaitTime();
			DBUtilities.deleteFromTable("indentor_type","indentor_type_code","IND02");
		}
		
		@Then("Clicking Export button in Indentor Type master page")
		public void clickingexportbuttoninindentortypemasterpage() {
			base.WaitUntil(indentorType.getExport());
		 	base.click(indentorType.getExport());
		 	
		}

}
