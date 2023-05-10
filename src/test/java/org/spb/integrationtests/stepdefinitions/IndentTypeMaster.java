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
import org.spb.integrationtests.pageobjectmodel.IndentTypePOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class IndentTypeMaster {

	private static String url = "indenttype";
	  BaseClass base = new BaseClass();
	  IndentTypePOM indtyp= new IndentTypePOM();
	  private String indenttypeCode;
	  private String indenttypeDesc;

	  public IndentTypeMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens
	
	  @When("Delete data initially from database in Indent Type")
	  public void deleteDataInitiallyFromDatabaseInIndent_type() {
		  DBUtilities.deleteFromTable("indent_type","indent_type_name","CL");
		  DBUtilities.deleteFromTable("indent_type","indent_type_name","DM");
		  DBUtilities.deleteFromTable("indent_type","indent_type_name","st");
	  }

	  @Given("A web browser is on the Indent Type master page")
	  public void aWebBrowserIsOnTheIndentTypeMasterPage() {
		  BaseClass.driver.get(BaseClass.baseURL + url);
	  }

	  @When("New Indent Type button is clicked on page")
	  public void newIndentTypeButtonIsClickedOnPage() {
		  base.click(indtyp.getNew_IndentType());
	      base.WaitUntilVisible(indtyp.getIndentType_Code());
	  }

	  @Then("A popup with Indent Type should be displayed when clicked")
	  public void aPopupWithIndentTypeShouldBeDisplayedWhenClicked() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		   WebElement IndentTypePage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
	       String IndentTypePopup = IndentTypePage.getText();
	       assertEquals("Indent Type",IndentTypePopup);
	       base.click(indtyp.getCloseButton());
	       
	           
	  }
	  
	  @Then("The Indent Type Code should be displayed in popup page")
	  public void theIndentTypeCodeShouldBeDisplayedInPopupPage() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  base.click(indtyp.getNew_IndentType());
		  Thread.sleep(2000);
		  base.implicitwait();
		   WebElement IndentTypeCode=BaseClass.driver.findElement(By.xpath("//*[@id='entityForm']/div/div[1]/label"));
	       String IndentTypeCodeTxt = IndentTypeCode.getText();
	       assertEquals("Indent Type Code",IndentTypeCodeTxt);
	       base.click(indtyp.getCloseButton());
	       
	       
	           
	  }
	  

	  @Then("A popup with submit button has shown in Indent Type page")
	  public void aPopupWithSubmitButtonHasShownInIndentTypePage() throws InterruptedException {
		  
		  base.refreshApp(BaseClass.baseURL+url);
		  base.click(indtyp.getNew_IndentType());
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String submit = submitbtn.getText();
          assertEquals("Submit",submit);
          base.click(indtyp.getCloseButton());
          
	  }

//scenario outline: valid data
	  
	  @Given("New Indent Type button is clicked")
	  public void newIndentTypeButtonIsClicked() {
		  base.click(indtyp.getNew_IndentType());
	      base.WaitUntilVisible(indtyp.getIndentType_Code());
	  }

	  @Then("Entering the data in {string},{string} text box in Indent Type")
	  public void enteringTheDataInTextBoxInIndentType(String indent_type_code, String indent_type_desc) {
		  base.implicitwait();
		  this.indenttypeCode=indent_type_code;
	      this.indenttypeDesc=indent_type_desc;
	      base.itemsToBeDeleted.add(indent_type_code);
	      base.sendkeys(indtyp.getIndentType_Code(), indent_type_code);
	      base.WaitUntil(indtyp.getDescription());
	      base.sendkeys(indtyp.getDescription(), indent_type_desc);
	      base.WaitUntil(indtyp.getSubmit());
	  }

	  @When("Click on submit button in Indent Type page")
	  public void clickOnSubmitButtonInIndentTypePage() {
	     base.click(indtyp.getSubmit());
	  }

	  @Then("A popop with message successfully added should be displayed in Indent Type page")
	  public void aPopopWithMessageSuccessfullyAddedShouldBeDisplayedInIndentTypePage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String successmsg = validdata.getText();
          assertEquals("Saved Successfully",successmsg);
	  }

	  @Then("The entered data is available in Indent Type table")
	  public void theEnteredDataIsAvailableInIndentTypeTable() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  HashMap<String, String> uniqueRowInTable =
			        DBUtilities.findUniqueRowInTable("indent_type","indent_type_name",this.indenttypeCode);
			    assertEquals(this.indenttypeDesc, uniqueRowInTable.get("description"));
	  }

//scenario outline: duplicate data
	  
	  @Given("Set data in Indent Type table")
	  public void setDataInIndentTypeTable() {
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("indent_type_name","DM");
		  	values.put("description","Domestic");
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	DBUtilities.insertIntoTable("indent_type", values);
	  }

	  @Then("Entering the duplicate data in {string},{string} text box in Indent Type")
	  public void enteringTheDuplicateDataInTextBoxInIndentType(String indent_type_code, String indent_type_desc) {
		  base.implicitwait();
		  this.indenttypeCode=indent_type_code;
	      this.indenttypeDesc=indent_type_desc;
	      base.itemsToBeDeleted.add(indent_type_code);
	      base.sendkeys(indtyp.getIndentType_Code(), indent_type_code);
	      base.WaitUntil(indtyp.getDescription());
	      base.sendkeys(indtyp.getDescription(), indent_type_desc);
	      base.WaitUntil(indtyp.getSubmit());
	      base.click(indtyp.getSubmit());
	  }

	  @Then("A popop with message already exists should be displayed in Indent Type page")
	  public void aPopopWithMessageAlreadyExistsShouldBeDisplayedInIndentTypePage() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  String errormessage = errmsg.getText();
	      assertEquals("Already exist",errormessage);
	  }
	  
//scenario outline:blank data
	  
	  @Then("Entering the blank data in {string},{string} text box in Indent Type")
	  public void enteringTheBlankDataInTextBoxInIndentType(String indent_type_code, String indent_type_desc) {
		  base.implicitwait();
		  this.indenttypeCode=indent_type_code;
	      this.indenttypeDesc=indent_type_desc;
	      base.itemsToBeDeleted.add(indent_type_code);
	      base.sendkeys(indtyp.getIndentType_Code(), indent_type_code);
	      base.WaitUntil(indtyp.getDescription());
	      base.sendkeys(indtyp.getDescription(), indent_type_desc);
	      base.WaitUntil(indtyp.getSubmit());
	      base.click(indtyp.getSubmit());
	  }

	  @Then("A popop with message Please enter all mandatory fields should be displayed in Indent Type page")
	  public void aPopopWithMessagePleaseEnterAllMandatoryFieldsShouldBeDisplayedInIndentTypePage() throws InterruptedException {
		  Thread.sleep(2000);   
		  base.implicitwait();
		  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String blankmessage = blnkmsg.getText();
	      assertEquals("Please Enter all mandatory fields",blankmessage);
	  }

//scenario outline:testing functionality of edit button
	  
	  @Given("Clicking the close button in Indent Type")
	  public void clickingTheCloseButtonInIndentType() {
		  base.WaitUntilVisible(indtyp.getCloseButton());
		     base.click(indtyp.getCloseButton());
	  }

//edit
	  
	  @Then("Checking the functionality of edit button and by clicking in Indent Type")
	  public void checkingTheFunctionalityOfEditButtonAndByClickingInIndentType() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(indtyp.getSearchButton(),"DM");
		  base.WaitUntilVisible(indtyp.getEditIcon());
		  base.click(indtyp.getEditIcon());
		  
		  base.WaitUntilVisible(indtyp.getIndentType_Code());
		  indtyp.getIndentType_Code().clear();
		  base.sendkeys(indtyp.getIndentType_Code(), "st");
		  base.WaitUntilVisible(indtyp.getDescription());
		  indtyp.getDescription().clear();
		  base.sendkeys(indtyp.getDescription(), "stock");
		  base.click(indtyp.getSubmit());
	  }

	  @Then("Checking the update button has shown in the Indent Type page")
	  public void checkingTheUpdateButtonHasShownInTheIndentTypePage() throws InterruptedException {
		  Thread.sleep(1000);
		  base.refreshApp(BaseClass.baseURL+url);
		  
		  base.click(indtyp.getEditIcon()); 
		Thread.sleep(2000);
		  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String update = updatebtn.getText();
          assertEquals("Update",update);
          base.click(indtyp.getCloseButton());
          
	  }

	  @Then("The edited data should be available in database of Indent Type")
	  public void theEditedDataShouldBeAvailableInDatabaseOfIndentType() throws InterruptedException {
		  base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("indent_type", "indent_type_name","st");
			 assertEquals("st",uniqueRowInTable.get("indent_type_name"));
	  }

	//scenario outline: testing functionality of delete button
	  
	  @Then("Checking the functionality of delete button by clicking in Indent Type")
	  public void checkingTheFunctionalityOfDeleteButtonByClickingInIndentType() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  base.implicitwait();
		  Thread.sleep(2000);
		  base.sendkeys(indtyp.getSearchButton(),"DM");
		  base.WaitUntil(indtyp.getDeleteIcon());
		  base.click(indtyp.getDeleteIcon());
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Indent Type")
	  public void clickingDeleteButtonAndApopupDeletedSucessfullyShouldBeDisplayedInIndentType() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
//delete icon popup 
		  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
	      String dltmessage = deletemsg.getText();
          assertEquals("Are you Sure ?",dltmessage);
//delete msg popup        
          base.click(indtyp.getDeleteButtonPopup());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
	  }

	  @Then("Verifying that the entered data was deleted in database of Indent Type")
	  public void verifyingThatTheEnteredDataWasDeletedInDatabaseOfindentType() throws InterruptedException {
		  Thread.sleep(2000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("indent_type","indent_type_name","st");
			assertEquals("1",uniqueRowInTable.get("deleted"));
			DBUtilities.deleteFromTable("indent_type","indent_type_name","st");
	  }

	//search box
	  
	  @Then("Searching the Indent Type code by sending keys")
	  public void searchingTheIndentTypeCodeBySendingKeys() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.click(indtyp.getSearchButton());
			base.sendkeys(indtyp.getSearchButton(),"DM");
	  }

	  @Then("The text in the search box should be equal to values in the Indent Type table")
	  public void theTextinTheSearchBoxShouldBeEqualToValuesInTheIndentTypetable() throws InterruptedException {
		  Thread.sleep(1000);
			assertEquals(indtyp.getSearchCheck().getText(),"DM");
			base.databaseWaitTime();
			DBUtilities.deleteFromTable("indent_type","indent_type_name","DM");
	  }

	  @Then("Clicking Export button in Indent Type master page")
	  public void clickingExportbuttonInIndentTypeMasterPage() {
		  base.WaitUntil(indtyp.getExport());
		 	base.click(indtyp.getExport());
	  }

	
}
