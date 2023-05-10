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
import org.spb.integrationtests.pageobjectmodel.CustomerTypePOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CustomerTypeMaster {


	private static String url = "customertype";
	  BaseClass base = new BaseClass();
	  CustomerTypePOM csttyp= new CustomerTypePOM();
	  private String customertypeCode;
	  private String customertypeDesc;

	  public CustomerTypeMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens

		@When("Delete data initially from database in Customer Type")
		public void deleteDataInitiallyFromDatabaseInCustomerType() {
			DBUtilities.deleteFromTable("customer_type","customer_type_code","A001");
			DBUtilities.deleteFromTable("customer_type","customer_type_code","D002");
			DBUtilities.deleteFromTable("customer_type","customer_type_code","r003");
			DBUtilities.deleteFromTable("customer_type","customer_type_code","as");
			DBUtilities.deleteFromTable("customer_type","customer_type_code","Dis");
			DBUtilities.deleteFromTable("customer_type","customer_type_code","r003");
			
			
		}
		
		@Given("A web browser is on the Customer Type master page")
		public void aWebBrowserisOnTheCustomerTypeMasterPage() {
			BaseClass.driver.get(BaseClass.baseURL + url);
		}
		
		@When("New Customer Type button is clicked on page")
		public void newCustomerTypeButtonIsClickedOnPage() {
			base.click(csttyp.getNew_CustomerType());
		      base.WaitUntilVisible(csttyp.getCustomerType_Code());
		}
		
		@Then("A popup with Customer Type should be displayed when clicked")
		public void aPopupWithCustomerTypeShouldBeDisplayedWhenClicked() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
			   WebElement CustomerTypePage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
		       String CustomerTypePopup = CustomerTypePage.getText();
		       assertEquals("Customer Type",CustomerTypePopup);
		}
		
		@Then("A popup with submit button has shown in Customer Type page")
		public void aPopupWithSubmitButtonHasShownInCustomerTypePage() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
			  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
		      String submit = submitbtn.getText();
	          assertEquals("Submit",submit);
		}
		
//scenario outline: valid data
		
		@Given("New Customer Type button is clicked")
		public void newCustomerTypeButtonIsClicked() {
			base.click(csttyp.getNew_CustomerType());
		      base.WaitUntilVisible(csttyp.getCustomerType_Code());
		}
		
		@Then("Entering the data in {string},{string} text box in Customer Type")
		public void enteringTheDataInTextBoxInCustomerType(String customer_type_code, String customer_type_desc) {
			base.implicitwait();
			  this.customertypeCode=customer_type_code;
		      this.customertypeDesc=customer_type_desc;
		      base.itemsToBeDeleted.add(customer_type_code);
		      base.sendkeys(csttyp.getCustomerType_Code(), customer_type_code);
		      base.WaitUntil(csttyp.getDescription());
		      base.sendkeys(csttyp.getDescription(), customer_type_desc);
		      base.WaitUntil(csttyp.getSubmit());
		}
		
		@When("Click on submit button in Customer Type page")
		public void clickOnSubmitButtonInCustomerTypePage() {
		    base.click(csttyp.getSubmit());
		}
		
		@Then("A popop with message successfully added should be displayed in Customer Type page")
		public void aPopopWithMessageSuccessfullyAddedShouldBeDisplayedInCustomerTypePage() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
			  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		      String successmsg = validdata.getText();
	          assertEquals("Saved Successfully",successmsg);
		}
		
		@Then("The entered data is available in Customer Type table")
		public void theEnteredDataIsAvailableInCustomerTypeTable() throws InterruptedException {
			 base.implicitwait();
			  Thread.sleep(2000);
			  HashMap<String, String> uniqueRowInTable =
				        DBUtilities.findUniqueRowInTable("customer_type","customer_type_code",this.customertypeCode);
				    assertEquals(this.customertypeDesc, uniqueRowInTable.get("description"));
		}
		
//scenario outline: duplicate data
		
		@Given("Set data in Customer Type table")
		public void setDataInCustomerTypeTable() {
			LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("customer_type_code","D002");
		  	values.put("description","Dheena Publications");
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	DBUtilities.insertIntoTable("customer_type", values);
		}
		
		@Then("Entering the duplicate data in {string},{string} text box in Customer Type")
		public void enteringTheDuplicateDataInTextBoxInCustomerType(String customer_type_code, String customer_type_desc) {
			base.implicitwait();
			  this.customertypeCode=customer_type_code;
		      this.customertypeDesc=customer_type_desc;
		      base.itemsToBeDeleted.add(customer_type_code);
		      base.sendkeys(csttyp.getCustomerType_Code(), customer_type_code);
		      base.WaitUntil(csttyp.getDescription());
		      base.sendkeys(csttyp.getDescription(), customer_type_desc);
		      base.WaitUntil(csttyp.getSubmit());
		      base.click(csttyp.getSubmit());
		}
		
		@Then("A popop with message already exists should be displayed in Customer Type page")
		public void aPopopWithMessageAlreadyExistsShouldBeDisplayedInCustomerTypePage() throws InterruptedException {
			base.implicitwait();
			  Thread.sleep(2000);
			  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
			  String errormessage = errmsg.getText();
		      assertEquals("Already exist",errormessage);
		}
		
		//scenario outline:blank data
		
		@Then("Entering the blank data in {string},{string} text box in Customer Type")
		public void enteringTheBlankDataInTextBoxInCustomerType(String customer_type_code, String customer_type_desc) {
			base.implicitwait();
			  this.customertypeCode=customer_type_code;
		      this.customertypeDesc=customer_type_desc;
		      base.itemsToBeDeleted.add(customer_type_code);
		      base.sendkeys(csttyp.getCustomerType_Code(), customer_type_code);
		      base.WaitUntil(csttyp.getDescription());
		      base.sendkeys(csttyp.getDescription(), customer_type_desc);
		      base.WaitUntil(csttyp.getSubmit());
		      base.click(csttyp.getSubmit());
		}
		
		@Then("A popop with message Please enter all mandatory fields should be displayed in Customer Type page")
		public void aPopopWithMessagePleaseEnterAllMandatoryFieldsShouldBeDisplayedInCustomerTypePage() throws InterruptedException {
			Thread.sleep(2000);   
			  base.implicitwait();
			  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		      String blankmessage = blnkmsg.getText();
		      assertEquals("Please Enter all mandatory fields",blankmessage);
		}
		
//scenario outline:testing functionality of edit button
		
		@Given("Clicking the close button in Customer Type")
		public void clickingTheCloseButtonInCustomerType() {
			base.WaitUntilVisible(csttyp.getCloseButton());
		     base.click(csttyp.getCloseButton());
		}
		
//edit
		
		@Then("Checking the functionality of edit button and by clicking in Customer Type")
		public void checkingTheFunctionalityOfEditButtonAndByClickingInCustomerType() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
			  base.refreshApp(BaseClass.baseURL+url);
			  base.sendkeys(csttyp.getSearchButton(),"D002");
			  base.WaitUntilVisible(csttyp.getEditIcon());
			  base.click(csttyp.getEditIcon());
			  
			  base.WaitUntilVisible(csttyp.getCustomerType_Code());
			  csttyp.getCustomerType_Code().clear();
			  base.sendkeys(csttyp.getCustomerType_Code(), "r003");
			  base.WaitUntilVisible(csttyp.getDescription());
			  csttyp.getDescription().clear();
			  base.sendkeys(csttyp.getDescription(), "ram publications");
			  base.click(csttyp.getSubmit());
		}
		
		@Then("Checking the update button has shown in the Customer Type page")
		public void checkingTheUpdateButtonHasShownInTheCustomerTypePage() throws InterruptedException {
			base.refreshApp(BaseClass.baseURL+url);
			Thread.sleep(2000);
			base.sendkeys(csttyp.getSearchButton(),"D002");
			base.WaitUntilVisible(csttyp.getEditIcon());
			  base.click(csttyp.getEditIcon());
			  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
		      String update = updatebtn.getText();
	          assertEquals("Submit",update);
	          base.click(csttyp.getCloseButton());
		}
		
		@Then("The edited data should be available in database of Customer Type")
		public void theEditedDataShouldBeAvailableInDatabaseOfCustomerType() throws InterruptedException {
			base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("customer_type", "customer_type_code","r003");
			 assertEquals("r003",uniqueRowInTable.get("customer_type_code"));
		}
		
		//scenario outline: testing functionality of delete button
		
		@Then("Checking the functionality of delete button by clicking in Customer Type")
		public void checkingTheFunctionalityOfDeleteButtonByClickingInCustomerType() throws InterruptedException {
			base.refreshApp(BaseClass.baseURL+url);
			  base.implicitwait();
			  Thread.sleep(2000);
			  base.sendkeys(csttyp.getSearchButton(),"D002");
			  base.WaitUntil(csttyp.getDeleteIcon());
			  base.click(csttyp.getDeleteIcon());
		}
		
		@Then("Clicking delete button and a popup deleted sucessfully should be displayed in Customer Type")
		public void clickingDeleteButtonAndAPopupDeletedSucessfullyShouldBeDisplayedInCustomerType() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
	//delete icon popup 
			  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
		      String dltmessage = deletemsg.getText();
	          assertEquals("Are you Sure ?",dltmessage);
	//delete msg popup        
	          base.click(csttyp.getDeleteButtonPopup());
			  Thread.sleep(2000);
			  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		      String deletesuccess = dltsccsflymsg.getText();
	          assertEquals("Deleted Successfully",deletesuccess);
		}
		
		@Then("Verifying that the entered data was deleted in database of Customer Type")
		public void verifyingThatTheEnteredDataWasDeletedInDatabaseOfCustomerType() throws InterruptedException {
			Thread.sleep(2000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("customer_type","customer_type_code","D002");
			assertEquals("1",uniqueRowInTable.get("deleted"));
			DBUtilities.deleteFromTable("customer_type","customer_type_code","D002");
		}
		
		//search box
		
		@Then("Searching the Customer Type code by sending keys")
		public void searchingTheCustomerTypeCodeBySendingKeys() throws InterruptedException {
			base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.click(csttyp.getSearchButton());
			base.sendkeys(csttyp.getSearchButton(),"D002");
		}
		
		@Then("The text in the search box should be equal to values in the Customer Type table")
		public void theTextInTheSearchBoxShouldBeEqualToValuesInTheCustomerTypeTable() throws InterruptedException {
			Thread.sleep(1000);
			assertEquals(csttyp.getSearchCheck().getText(),"D002");
			base.databaseWaitTime();
			DBUtilities.deleteFromTable("customer_type","customer_type_code","D002");
		}
		
		@Then("Clicking Export button in Customer Type master page")
		public void clickingExportButtonInCustomerTypeMasterPage() {
			base.WaitUntil(csttyp.getExport());
		 	base.click(csttyp.getExport());
		}
	
}
