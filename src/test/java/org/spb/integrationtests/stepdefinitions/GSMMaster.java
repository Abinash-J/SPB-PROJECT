

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
import org.spb.integrationtests.pageobjectmodel.GSMMasterPOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class GSMMaster {
	  private static String url = "gsms";
	  BaseClass base = new BaseClass();
	  GSMMasterPOM gsm= new GSMMasterPOM();
	  private String gsmCode;
	  private String gsmDesc;

	  public GSMMaster(BaseClass base) {
	    this.base = base;
	    
		 
	  }
	  
//scenario
	  
	  @When("Delete data initially from database in GSM")
	  public void deleteDataInitiallyFromDatabaseInGSM() {
		  /*DBUtilities.deleteFromTable("gsm","gsm_code","5060");
		  DBUtilities.deleteFromTable("gsm","gsm_code","6070");
		  DBUtilities.deleteFromTable("gsm","gsm_code","7080");*/
		  
	  }

	  
	
	  @Given("A web browser is on the GSM master page")
	  public void aWebBrowserIsOnTheGSMMasterpage() {
		  BaseClass.driver.get(BaseClass.baseURL + url);
		  
	  }

	  @When("New GSM button is clicked on page")
	  public void newGSMButtonIsClickedOnPage() {
		  base.click(gsm.getNew_GSM());
		    base.WaitUntilVisible(gsm.getGSM_Code());
	  }

	  @Then("A popup with GSM should be displayed when clicked")
	  public void aPopupWithGSMShouldBeDisplayedWhenClicked(){
		    base.implicitwait();
		    WebElement gsmpg=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
		    String gsmPopup = gsmpg.getText();
	        assertEquals("GSM",gsmPopup);
		      
	  }
	  
	  @Then("A popup with submit button has shown in GSM page")
	  public void aPopupWithSubmitButtonHasShownInGSMpage() {
		  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String submit = submitbtn.getText();
          assertEquals("Submit",submit);
	  }
	  
//scenario outline valid data


	  @And("New GSM button is clicked")
	  public void newGSMButtonIsClicked() {
		  base.click(gsm.getNew_GSM());
		    base.WaitUntilVisible(gsm.getGSM_Code());
	  }

	  @Then("Entering the data in {string},{string} text box in GSM")
	  public void enteringTheDataInTextBoxInGSM(String gsm_code, String gsm_desc){
		  base.implicitwait();
		  this.gsmCode=gsm_code;
	      this.gsmDesc=gsm_desc;
	      base.itemsToBeDeleted.add(gsm_code);
	      base.sendkeys(gsm.getGSM_Code(), gsm_code);
	      base.WaitUntil(gsm.getDescription());
	      base.sendkeys(gsm.getDescription(), gsm_desc);
	      base.WaitUntil(gsm.getSubmit());
	  }
	  

	  @When("Click on submit button in create GSM page")
	  public void ClickOnSubmitButtonInCreateGSMPPage() {
		  base.click(gsm.getSubmit());
		  
	  }

	  @Then("A popop with message successfully added should be displayed in GSM page")
	  public void aPopopWithMessageSuccessfullyAddedShouldBeDisplayedInGSMPage() throws InterruptedException{
		 Thread.sleep(2000);
		  base.implicitwait();
		  String validData = BaseClass.driver.switchTo().activeElement().getAttribute("id");
	        assertEquals("showAlert",validData);
	  }
	  

	  @Then("The entered data is available in GSM table")
	  public void theEnteredDataIsAvailableInGSMTable() throws InterruptedException {
		  base.implicitwait();
		  HashMap<String, String> uniqueRowInTable =
			        DBUtilities.findUniqueRowInTable("gsm", "gsm_code", this.gsmCode);
			    assertEquals(this.gsmDesc, uniqueRowInTable.get("description"));
		  }  
	  

	  
//scenario outline duplicate data
	 

	  @And("Set data in GSM table")
	  public void setDataInGSMTable() {
	  	LinkedHashMap<String, String> values = new LinkedHashMap<>();
	  	Date date = new Date();
	  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	  	String currentDateTime = format.format(date);
	  	values.put("gsm_code","6070");
	  	values.put("description","6070 GSM");
	  	values.put("active","0");
	  	values.put("deleted","0");
	  	values.put("created_by","Integration Test");
	  	values.put("updated_by","Integration Test");
	  	values.put("created_on",(currentDateTime));
	  	values.put("updated_on",(currentDateTime));
	  	DBUtilities.insertIntoTable("gsm", values);
	  	
	  }
	  
	  @Then("Entering the duplicate data in {string},{string} text box in GSM")
	  public void enteringDuplicateDataInTextBoxInGSM(String gsm_code, String gsm_desc){
		  base.implicitwait();
		  this.gsmCode=gsm_code;
	      this.gsmDesc=gsm_desc;
	      base.itemsToBeDeleted.add(gsm_code);
	     
	      base.sendkeys(gsm.getGSM_Code(), gsm_code);
	      base.WaitUntil(gsm.getDescription());
	      base.sendkeys(gsm.getDescription(), gsm_desc);
	      base.WaitUntil(gsm.getSubmit());
	  }
	  

	

	  @Then("A popop with message already exists should be displayed in GSM page")
	  public void aPopupWithMessageAlreadyExixtsShouldBeDislplayedInGSMPage() throws InterruptedException{
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		      String errormessage = errmsg.getText();
	        assertEquals("Already exist",errormessage);
	  }
	  

	

	  
//scenario outline blank data
	 

	  @Then("Entering the blank data in {string},{string} text box in GSM")
	  public void enteringTheBlankDataInTextBoxInGSM(String gsm_code, String gsm_desc){
		  base.implicitwait();
		  this.gsmCode=gsm_code;
	      this.gsmDesc=gsm_desc;
	      base.itemsToBeDeleted.add(gsm_code);
	      base.sendkeys(gsm.getGSM_Code(), gsm_code);
	      base.WaitUntil(gsm.getDescription());
	      base.sendkeys(gsm.getDescription(), gsm_desc);
	      base.WaitUntil(gsm.getSubmit());
	  }
	  

	 

	  @Then("A popop with message Please enter all mandatory fields should be displayed in GSM page")
	  public void aPopupWithMessagePleaseEnterAllMandatoryFieldsShouldBeDisplayedInGSMPage() throws InterruptedException{
		  Thread.sleep(2000);   
		  base.implicitwait();
			  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[3]"));
		      String blankmessage = blnkmsg.getText();
	        assertEquals("Please Enter all mandatory fields",blankmessage);
	      
	  }
	  

	

	  
//scenario functionalities
	  @Given("A web browser is on the GSMMaster page")
	  public void aWebBrowserIsOntheGSMMasterPage() {
		  BaseClass.driver.get(BaseClass.baseURL + url);
	  }

	
	  

	  @And("Clicking the close button in GSM page")
	  public void clickingTheCloseButtonInGSMPage() {
		  base.WaitUntil(gsm.getcloseButton());
		  base.click(gsm.getcloseButton());
	  }

	  @Then("Checking the functionality of edit button by clicking in GSM page")
	  public void checkingTheFunctionalityOfEditButtonByClickingInGSMPage() throws InterruptedException {
		  base.implicitwait();
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(2000);
		  base.WaitUntil(gsm.getSearchButton());
		  base.sendkeys(gsm.getSearchButton(),"6070");
		  base.WaitUntilVisible(gsm.getEditIcon());
		  base.click(gsm.getEditIcon());
		  
		  base.WaitUntilVisible(gsm.getGSM_Code());
		  gsm.getGSM_Code().clear();
		  base.sendkeys(gsm.getGSM_Code(), "7080");
		  base.WaitUntilVisible(gsm.getDescription());
		  gsm.getDescription().clear();
		  base.sendkeys(gsm.getDescription(), "7080 gsm");
		  base.click(gsm.getSubmit());
		  
		  
		  
	  }
	  
	  @Then("Checking the update button has shown in the GSM page")
	  public void checkingTheUpdateButtonHasShownInGSMPage() throws InterruptedException {
		  
		  base.click(gsm.getEditIcon());
		  Thread.sleep(2000);
		  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String update = updatebtn.getText();
          assertEquals("Update",update);
          base.click(gsm.getSubmit());
          
	  }
	  
	  @Then("The edited data should be available in database of GSM")
		public void theEditedDataShouldBeAvailableIndatabaseOfGSM() throws InterruptedException {
			 base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("gsm", "gsm_code","7080");
			 assertEquals("7080",uniqueRowInTable.get("gsm_code"));
		     
		}

	  
	  @Then("Checking the functionality of delete button by clicking in GSM page")
	  public void checkingTheFunctionalityOfDeleteButtonByClickingInGSMPage() throws InterruptedException {
		  base.implicitwait();
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(2000);
		  base.WaitUntil(gsm.getSearchButton());
		  base.sendkeys(gsm.getSearchButton(),"6070");
		  base.click(gsm.getDeleteIcon());
		  Thread.sleep(2000);
		  
		  
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in GSM page")
	  public void clickingDeleteButtonAndAPopupDeletedSuccessfullyShouldBeDisplayedInGSMPage() throws InterruptedException{
		  base.WaitUntil(gsm.getDeleteIcon());
		  base.click(gsm.getDeleteIcon());
		  Thread.sleep(2000);
		  base.implicitwait();
//delete icon popup	
		  String delete=BaseClass.driver.switchTo().activeElement().getAttribute("id");
		  assertEquals("delete-modal",delete);
//deleted msg popup			  
		  base.click(gsm.getDeleteButtonPopup());
		  Thread.sleep(2000);
	      WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
        
	       
	  }
	  
	  @Then("Verifying that the entered data was deleted in database of GSM")
		public void verifyingThatTheEnteredDataWasDeletedInDatabaseOfGSM() throws InterruptedException {
			Thread.sleep(2000);
			base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("gsm","gsm_code","7080");
		       assertEquals("1",uniqueRowInTable.get("deleted"));
		       DBUtilities.deleteFromTable("gsm","gsm_code","7080");
		}


	//search box
		@Then("Searching the GSM code by sending keys")
		public void searchGSMCode() throws InterruptedException {
			base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.click(gsm.getSearchButton());
			base.sendkeys(gsm.getSearchButton(),"6070");
		}

		@Then("The text in the search box should be equal to values in the GSM table")
		public void	theTextinTheSearchBoxEqulstoValueInTable() throws InterruptedException {
			Thread.sleep(1000);
			assertEquals(gsm.getSearchCheck().getText(),"6070");
			base.databaseWaitTime();
			DBUtilities.deleteFromTable("gsm","gsm_code","6070");
			
		}
	//Export button
		@Then("Clicking Export button in GSM master page")
		public void	clickingExportbutton() throws InterruptedException {
		 	base.WaitUntil(gsm.getExport());
		 	base.click(gsm.getExport());
		 	
		}



}
		  
		  


 
	  

