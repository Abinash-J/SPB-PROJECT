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
import org.spb.integrationtests.pageobjectmodel.FormFactorPOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FormFactorMaster {

	private static String url = "formfactor";
	  BaseClass base = new BaseClass();
	  FormFactorPOM form= new FormFactorPOM();
	  private String formfactorCode;
	  private String formfactorDesc;

	  public FormFactorMaster(BaseClass base) {
	    this.base = base;
	  }

//scenario: test screen opens
	  
	  @When("Delete data initially from database in Form Factor")
	  public void deleteDataInitiallyFromDatabaseInFormFactor() {
		 /* DBUtilities.deleteFromTable("form_factor","form_factor","N");
		  DBUtilities.deleteFromTable("form_factor","form_factor","R");
		  DBUtilities.deleteFromTable("form_factor","form_factor","rl");
		  DBUtilities.deleteFromTable("form_factor","form_factor","S");*/
		  DBUtilities.deleteFromTable("form_factor","form_factor","FF1");
		  DBUtilities.deleteFromTable("form_factor","form_factor","FF4");
		  DBUtilities.deleteFromTable("form_factor","form_factor","FF2");
		  DBUtilities.deleteFromTable("form_factor","form_factor","ff3");
		  
		  
		  
	  }
	  
	  
	  @Given("A web browser is on the Form Factor master page")
	  public void aWebBrowserIsOnTheFormFactorMasterPage() {
		  BaseClass.driver.get(BaseClass.baseURL + url);
	  }

	  @When("New form factor button is clicked on page")
	  public void newFormFactorButtonIsClickedOnPage() {
		  base.click(form.getNew_FormFactor());
	      base.WaitUntilVisible(form.getFormFactor_Code());
	  }

	  @Then("A popup with form factor should be displayed when clicked")
	  public void aPopupWithFormFactorshouldbeDisplayedWhenClicked() {
		  base.implicitwait();
		   WebElement formfactorpage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
	       String formfactorpopup = formfactorpage.getText();
	       assertEquals("Form Factor",formfactorpopup);
	  }

	  @Then("A popup with submit button has shown in form factor page")
	  public void aPopupWithSubmitButtonHasShownInFormFactorpage() {
		  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String submit = submitbtn.getText();
          assertEquals("Submit",submit);
	  }

//scenario outline: valid data
	  
	  @Given("New form factor button is clicked")
	  public void newFormFactorButtonIsClicked() {
		  base.click(form.getNew_FormFactor());
	      base.WaitUntilVisible(form.getFormFactor_Code());
	  }

	  @Then("Entering the data in {string},{string} text box in form factor")
	  public void enteringTheDataInTextBoxInFormFactor(String form_factor_code, String form_factor_desc) {
		  base.implicitwait();
		  this.formfactorCode=form_factor_code;
	      this.formfactorDesc=form_factor_desc;
	      base.itemsToBeDeleted.add(form_factor_code);
	      base.sendkeys(form.getFormFactor_Code(), form_factor_code);
	      base.WaitUntil(form.getDescription());
	      base.sendkeys(form.getDescription(), form_factor_desc);
	      base.WaitUntilVisible(form.getSubmit());
	      base.click(form.getSubmit());
	  }

	  @When("Click on submit button in form factor page")
	  public void clickOnSubmitbuttonInFormFactorPage() {
	      base.click(form.getSubmit());
	  }

	  @Then("A popop with message successfully added should be displayed in form factor page")
	  public void aPopupWithMessageSuccessfullyAddedShouldBeDisplayedInFormFactorPage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String successmsg = validdata.getText();
          assertEquals("Saved Successfully",successmsg);
	  }

	  @Then("The entered data is available in form factor table")
	  public void theEnteredDataIsAvailableInFormFactorTable() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  HashMap<String, String> uniqueRowInTable =
			        DBUtilities.findUniqueRowInTable("form_factor","form_factor",this.formfactorCode);
			    assertEquals(this.formfactorDesc, uniqueRowInTable.get("description"));
	  }

//scenario outline: duplicate data
	  
	  @Given("Set data in form factor table")
	  public void setDataInFormFactorTable() {
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("form_factor","FF2");
		  	values.put("description","desc");
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	DBUtilities.insertIntoTable("form_factor", values);
	  }

	  @Then("Entering the duplicate data in {string},{string} text box in form factor")
	  public void enteringTheDuplicateDataInTextBoxInFormFactor(String form_factor_code, String form_factor_desc) {
		  base.implicitwait();
		  this.formfactorCode=form_factor_code;
	      this.formfactorDesc=form_factor_desc;
	      base.itemsToBeDeleted.add(form_factor_code);
	      base.sendkeys(form.getFormFactor_Code(), form_factor_code);
	      base.WaitUntil(form.getDescription());
	      base.sendkeys(form.getDescription(), form_factor_desc);
	      base.WaitUntilVisible(form.getSubmit());
	      base.click(form.getSubmit());
	  }

	  @Then("A popop with message already exists should be displayed in form factor page")
	  public void aPopupWithMessageAlreadyExistsShouldBeDisplayedInFormFactorPage() throws InterruptedException {
		  
		  base.implicitwait();
		  Thread.sleep(2000);
		  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  String errormessage = errmsg.getText();
	      assertEquals("Already exist",errormessage);
	  }

//scenario outline:blank data
	  
	  @Then("Entering the blank data in {string},{string} text box in form factor")
	  public void enteringTheBlankDataInTextBoxInFormFactor(String form_factor_code, String form_factor_desc) {
		  base.implicitwait();
		  this.formfactorCode=form_factor_code;
	      this.formfactorDesc=form_factor_desc;
	      base.itemsToBeDeleted.add(form_factor_code);
	      base.sendkeys(form.getFormFactor_Code(), form_factor_code);
	      base.WaitUntil(form.getDescription());
	      base.sendkeys(form.getDescription(), form_factor_desc);
	      base.WaitUntilVisible(form.getSubmit());
	      base.click(form.getSubmit());
	  }

	  @Then("A popop with message Please enter all mandatory fields should be displayed in form factor page")
	  public void aPopupWithMessagePleaseEnterAllMandatoryFieldsshouldBeDisplayedInFormFactorPage() throws InterruptedException {
		  Thread.sleep(2000);   
		  base.implicitwait();
		  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String blankmessage = blnkmsg.getText();
	      assertEquals("Please Enter all mandatory fields",blankmessage);
	  }

//scenario outline:testing functionality of edit button
	  
	  @Then("Clicking the close button in form factor")
	  public void clickingTheCloseButtonInFormFactor() {
		  base.WaitUntilVisible(form.getCloseButton());
		     base.click(form.getCloseButton());
	  }
	  
//edit
	  @Then("Checking the functionality of edit button and by clicking in form factor")
	  public void checkingTheFunctionalityOfEditButtonAndByClickingInFormFactor() throws InterruptedException {
		  base.implicitwait();
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(2000);
		  base.sendkeys(form.getSearchButton(),"FF2");
		  base.WaitUntilVisible(form.getEditIcon());
		  base.click(form.getEditIcon());
		  
		  base.WaitUntilVisible(form.getFormFactor_Code());
		  form.getFormFactor_Code().clear();
		  base.sendkeys(form.getFormFactor_Code(), "ff3");
		  base.WaitUntilVisible(form.getDescription());
		  form.getDescription().clear();
		  base.sendkeys(form.getDescription(), "desc3");
		  base.WaitUntilVisible(form.getSubmit());
	      base.click(form.getSubmit());
	  }

	  @Then("Checking the update button has shown in the form factor page")
	  public void checkingTheUpdateButtonHasShownInFormFactorPage() throws InterruptedException {
		  
		  
		  Thread.sleep(2000);
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(2000);
		  base.sendkeys(form.getSearchButton(),"ff3");
		  base.WaitUntilVisible(form.getEditIcon());
		  base.click(form.getEditIcon());
		  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String update = updatebtn.getText();
          //assertEquals("Update",update);
          base.click(form.getCloseButton());
          
	  }
	  
	  @Then("The edited data should be available in database of form factor")
		public void theEditedDataShouldBeAvailableIndatabaseOfFormFactor() throws InterruptedException {
			 base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("form_factor", "form_factor","ff3");
			 assertEquals("ff3",uniqueRowInTable.get("form_factor"));
		     
		}

//scenario outline: testing functionality of delete button
	  
	  @Then("Checking the functionality of delete button by clicking in form factor")
	  public void checkingTheFunctionalityOfDeleteButtonByClickingInFormFactor() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  base.implicitwait();
		  Thread.sleep(2000);
		  base.sendkeys(form.getSearchButton(),"FF2");
		  base.WaitUntil(form.getDeleteIcon());
		  base.click(form.getDeleteIcon());
		  
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in form factor")
	  public void clickingDeleteButtonAndAPopupDeletedSuccessfullyShouldBeDisplayedInFormFactor() throws InterruptedException {
		  
		  Thread.sleep(2000);
		  base.implicitwait();
//delete icon popup 
		  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
	      String dltmessage = deletemsg.getText();
          assertEquals("Are you Sure ?",dltmessage);
//delete msg popup        
          base.click(form.getDeleteButtonPopup());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
        
	       
	  }


	  @Then("Verifying that the entered data was deleted in database of form factor")
		public void verifyingThatTheEnteredDataWasDeletedInDatabaseOfFormFactor() throws InterruptedException {
			//Thread.sleep(2000);
			base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("form_factor","form_factor","ff3");
			assertEquals("1",uniqueRowInTable.get("deleted"));
			DBUtilities.deleteFromTable("form_factor","form_factor","ff3");
//set data deletion if editing data not editing			
			//DBUtilities.deleteFromTable("form_factor","form_factor","gh");
			
		}
	  
	//search box
			@Then("Searching the Form Factor code by sending keys")
			public void searchingTheFormFactorCodeBySendingKeys() throws InterruptedException {
				base.refreshApp(BaseClass.baseURL+url);
				//base.WaitUntilVisible(gsm.getSearchButton());
				Thread.sleep(2000);
				base.click(form.getSearchButton());
				base.sendkeys(form.getSearchButton(),"FF2");
			}

			@Then("The text in the search box should be equal to values in the Form Factor table")
			public void	theTextInTheSearchBoxShouldBeEqualToValuesInTheFormFactorTable() throws InterruptedException {
				Thread.sleep(1000);
				assertEquals(form.getSearchCheck().getText(),"FF2");
				base.databaseWaitTime();
				DBUtilities.deleteFromTable("form_factor","form_factor","FF2");
				
			}
		//Export button
			@Then("Clicking Export button in Form Factor master page")
			public void	clickingExportButtonInFormFactorMasterPage() throws InterruptedException {
			 	base.WaitUntil(form.getExport());
			 	base.click(form.getExport());
			 	
			}
	
	
	
	
	
}
