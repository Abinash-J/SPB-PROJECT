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
import org.spb.integrationtests.pageobjectmodel.ItemTypePOM;
import org.spb.integrationtests.pageobjectmodel.PaymentTermsPOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PaymentTermsMaster {

	
	private static String url = "paymentterms";
	  BaseClass base = new BaseClass();
	  PaymentTermsPOM pymnt= new PaymentTermsPOM();
	  private String paymenttermsCode;
	  private String paymenttermsDesc;

	  public PaymentTermsMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens
	
	  @When("Delete data initially from database in Payment Terms")
	  public void deleteDataInitiallyFromDatabaseInPaymentTerms() {
		  DBUtilities.deleteFromTable("payment_terms","payment_name","PN 1");
		  DBUtilities.deleteFromTable("payment_terms","payment_name","PN 2");
		  DBUtilities.deleteFromTable("payment_terms","payment_name","pn 3");
	  }

	  @Given("A web browser is on the Payment Terms master page")
	  public void aWebBrowserIsOnThePaymentTermsMasterPage() {
		  BaseClass.driver.get(BaseClass.baseURL + url);
	  }

	  @When("New Payment Terms button is clicked on page")
	  public void newPaymentTermsButtonIsClickedOnPage() {
			base.click(pymnt.getNew_PaymentTerms());
		      base.WaitUntilVisible(pymnt.getPaymentTerms_Code());
	  }

	  @Then("A popup with Payment Terms should be displayed when clicked")
	  public void aPopupWithPaymentTermsShouldBeDisplayedWhenClicked() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		   WebElement PaymentTermsPage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
	       String PaymentTermsPopup = PaymentTermsPage.getText();
	       assertEquals("Payment Terms",PaymentTermsPopup);;
	  }

	  @Then("A popup with submit button has shown in Payment Terms page")
	  public void aPopupWithSubmitButtonHasShownInPaymentTermsPage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String submit = submitbtn.getText();
          assertEquals("Submit",submit);
	  }

//scenario outline: valid data
	  
	  @Given("New Payment Terms button is clicked")
	  public void newPaymentTermsButtonIsClicked() {
		  base.click(pymnt.getNew_PaymentTerms());
	      base.WaitUntilVisible(pymnt.getPaymentTerms_Code());
	  }

	  @Then("Entering the data in {string},{string} text box in Payment Terms")
	  public void enteringTheDataInTextBoxInPaymentTerms(String payment_terms_code, String payment_terms_desc) {
		  base.implicitwait();
		  this.paymenttermsCode=payment_terms_code;
	      this.paymenttermsDesc=payment_terms_desc;
	      base.itemsToBeDeleted.add(payment_terms_code);
	      base.sendkeys(pymnt.getPaymentTerms_Code(), payment_terms_code);
	      base.WaitUntil(pymnt.getDescription());
	      base.sendkeys(pymnt.getDescription(), payment_terms_desc);
	      base.WaitUntil(pymnt.getSubmit());
	  }

	  @When("Click on submit button in Payment Terms page")
	  public void clickOnSubmitButtonInPaymentTermsPage() {
	      base.click(pymnt.getSubmit());
	  }

	  @Then("A popop with message successfully added should be displayed in Payment Terms page")
	  public void aPopopWithMessageSuccessfullyAddedShouldBeDisplayedInPaymentTermsPage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String successmsg = validdata.getText();
          assertEquals("Saved Successfully",successmsg);
	  }

	  @Then("The entered data is available in Payment Terms table")
	  public void theEnteredDataIsAvailableInPaymentTermsTable() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  HashMap<String, String> uniqueRowInTable =
			        DBUtilities.findUniqueRowInTable("payment_terms","payment_name",this.paymenttermsCode);
			    assertEquals(this.paymenttermsDesc, uniqueRowInTable.get("description"));
	  }
	  
//scenario outline: duplicate data
	  @Given("Set data in Payment Terms table")
	  public void setDataInPaymentTermsTable() {
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("payment_name","PN 2");
		  	values.put("description","payment name 2");
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	DBUtilities.insertIntoTable("payment_terms", values);
	  }

	  @Then("Entering the duplicate data in {string},{string} text box in Payment Terms")
	  public void enteringTheDuplicateDataInTextBoxInPaymentTerms(String payment_terms_code, String payment_terms_desc) {
		  base.implicitwait();
		  this.paymenttermsCode=payment_terms_code;
	      this.paymenttermsDesc=payment_terms_desc;
	      base.itemsToBeDeleted.add(payment_terms_code);
	      base.sendkeys(pymnt.getPaymentTerms_Code(), payment_terms_code);
	      base.WaitUntil(pymnt.getDescription());
	      base.sendkeys(pymnt.getDescription(), payment_terms_desc);
	      base.WaitUntil(pymnt.getSubmit());
	      base.click(pymnt.getSubmit());
	  }

	  @Then("A popop with message already exists should be displayed in Payment Terms page")
	  public void aPopopWithMessageAlreadyExistsShouldBeDisplayedInPaymentTermsPage() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  String errormessage = errmsg.getText();
	      assertEquals("Already exist",errormessage);
	  }

//scenario outline:blank data
	  
	  @Then("Entering the blank data in {string},{string} text box in Payment Terms")
	  public void enteringTheBlankDataInTextBoxInPaymentTerms(String payment_terms_code, String payment_terms_desc) {
		  base.implicitwait();
		  this.paymenttermsCode=payment_terms_code;
	      this.paymenttermsDesc=payment_terms_desc;
	      base.itemsToBeDeleted.add(payment_terms_code);
	      base.sendkeys(pymnt.getPaymentTerms_Code(), payment_terms_code);
	      base.WaitUntil(pymnt.getDescription());
	      base.sendkeys(pymnt.getDescription(), payment_terms_desc);
	      base.WaitUntil(pymnt.getSubmit());
	      base.click(pymnt.getSubmit());
	  }

	  @Then("A popop with message Please enter all mandatory fields should be displayed in Payment Terms page")
	  public void aPopopWithMessagePleaseEnterAllMandatoryFieldsShouldBeDisplayedInPaymentTermsPage() throws InterruptedException {
		  Thread.sleep(2000);   
		  base.implicitwait();
		  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String blankmessage = blnkmsg.getText();
	      assertEquals("Please Enter all mandatory fields",blankmessage);
	  }

//scenario outline:testing functionality of edit button
	  
	  @Given("Clicking the close button in Payment Terms")
	  public void clickingTheCloseButtonInPaymentTerms() {
		  base.WaitUntilVisible(pymnt.getCloseButton());
		     base.click(pymnt.getCloseButton());
	  }
	  
//edit

	  @Then("Checking the functionality of edit button and by clicking in Payment Terms")
	  public void checkingtheFunctionalityOfEditButtonAndByClickingInPaymentTerms() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(pymnt.getSearchButton(),"PN 2");
		  base.WaitUntilVisible(pymnt.getEditIcon());
		  base.click(pymnt.getEditIcon());
		  
		  base.WaitUntilVisible(pymnt.getPaymentTerms_Code());
		  pymnt.getPaymentTerms_Code().clear();
		  base.sendkeys(pymnt.getPaymentTerms_Code(), "pn 3");
		  base.WaitUntilVisible(pymnt.getDescription());
		  pymnt.getDescription().clear();
		  base.sendkeys(pymnt.getDescription(), "payment name 3");
		  base.click(pymnt.getSubmit());
	  }

	  @Then("Checking the update button has shown in the Payment Terms page")
	  public void checkingTheUpdateButtonHasShownInThePaymentTermsPage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(pymnt.getSearchButton(),"pn 3");
		  base.WaitUntilVisible(pymnt.getEditIcon());
		  base.click(pymnt.getEditIcon());
		  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String update = updatebtn.getText();
          assertEquals("Update",update);
          base.click(pymnt.getCloseButton());
	  }

	  @Then("The edited data should be available in database of Payment Terms")
	  public void theEditedDataShouldBeAvailableInDatabaseOfPaymentTerms() throws InterruptedException {
		  base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("payment_terms", "payment_name","pn 3");
			 assertEquals("pn 3",uniqueRowInTable.get("payment_name"));
	  }

	  
//scenario outline: testing functionality of delete button
	  
	  @Then("Checking the functionality of delete button by clicking in Payment Terms")
	  public void checkingTheFunctionalityOfDeleteButtonByClickingInPaymentTerms() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  base.implicitwait();
		  Thread.sleep(2000);
		  base.sendkeys(pymnt.getSearchButton(),"PN 2");
		  base.WaitUntil(pymnt.getDeleteIcon());
		  base.click(pymnt.getDeleteIcon());
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Payment Terms")
	  public void clickingDeleteButtonAndAPopupDeletedSucessfullyShouldBeDisplayedInPaymentTerms() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
//delete icon popup 
		  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
	      String dltmessage = deletemsg.getText();
          assertEquals("Are you Sure ?",dltmessage);
//delete msg popup        
          base.click(pymnt.getDeleteButtonPopup());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
	  }

	  @Then("Verifying that the entered data was deleted in database of Payment Terms")
	  public void verifyingThatTheEnteredDataWasDeletedIndatabaseOfPaymentTerms() throws InterruptedException {
		  Thread.sleep(2000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("payment_terms","payment_name","pn 3");
			assertEquals("1",uniqueRowInTable.get("deleted"));
			DBUtilities.deleteFromTable("payment_terms","payment_name","pn 3");
	  }

//search box
	  
	  @Then("Searching the Payment Terms code by sending keys")
	  public void searchingThePaymentTermsCodeBySendingKeys() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.click(pymnt.getSearchButton());
			base.sendkeys(pymnt.getSearchButton(),"PN 2");
	  }

	  @Then("The text in the search box should be equal to values in the Payment Terms table")
	  public void theTextInTheSearchBoxShouldBeEqualToValuesInThePaymentTermsTable() throws InterruptedException {
		  Thread.sleep(1000);
			assertEquals(pymnt.getSearchCheck().getText(),"PN 2");
			base.databaseWaitTime();
			DBUtilities.deleteFromTable("payment_terms","payment_name","PN 2");
	  }

	  @Then("Clicking Export button in Payment Terms master page")
	  public void clickingExportButtonInPaymentTermsMasterPage() {
		  base.WaitUntil(pymnt.getExport());
		 	base.click(pymnt.getExport());
	  }

	
	
}
