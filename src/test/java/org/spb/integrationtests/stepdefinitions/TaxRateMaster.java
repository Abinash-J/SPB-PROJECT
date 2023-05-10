package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;

import org.hamcrest.core.IsEqual;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Rule;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.TaxRatePOM;
import org.spb.integrationtests.utils.DBUtilities;

import com.codeborne.selenide.ex.PageObjectException;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class TaxRateMaster {

	@Rule
	public ErrorCollector collector = new ErrorCollector();
	 
	//org.hamcrest.Matcher<Boolean> matchesTrue = IsEqual.equalTo(true);
	//org.hamcrest.Matcher<Boolean> matchesTrue = org.hamcrest.core.IsEqual.equalTo(true);
	
	private static String url = "taxrate";
	  BaseClass base = new BaseClass();
	  TaxRatePOM taxrate= new TaxRatePOM();
	  private String taxrateCode;
	  private String taxrateDesc;
	  private String Rate;

	  public TaxRateMaster(BaseClass base) {
	    this.base = base;
	   
	    
	  }
	
	  
	    
//scenario: test screen opens
	  
	  
	  @When("Delete data initially from database in Tax  Rate")
	  public void deletedatainitiallyfromdatabaseintaxrate() throws Throwable{
		  DBUtilities.deleteFromTable("tax_rate","tax_code","VAT-D");
		  DBUtilities.deleteFromTable("tax_rate","tax_code","VAT-E");
	  }

	  @Given("A web browser is on the Tax  Rate master page")
	  public void awebbrowserisonthetaxratemasterpage() throws InterruptedException {
		  Thread.sleep(1000);
		  BaseClass.driver.get(BaseClass.baseURL + url);
			
	  }

	  @When("New Tax  Rate button is clicked on page")
	  public void newtaxratebuttonisclickedonpage() throws InterruptedException {
		  base.waitAndClick(taxrate.getNew_TaxRate());
		  Thread.sleep(1000);
	  }

	  @Then("A popup with Tax  Rate should be displayed when clicked")
	  public void apopupwithtaxrateshouldbedisplayedwhenclicked() throws InterruptedException {
		  
			
	        Thread.sleep(3000);
		    WebElement taxRateTitle=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
		    base.implicitwait();
		    assertEquals("Tax Rate",taxRateTitle.getText());
		    /*try {
		    //assertEquals("TaxRate",taxRateTitle.getText());
		    	
		    
		    }
		    catch (Exception e) {
				collector.addError(e);
			}
		    
		    
		    collector.checkThat("TaxRate", equalTo(taxRateTitle.getText()));
		    */
		    
		   
	  }

	  @And("A popup with submit button has shown in Tax  Rate page")
	  public void apopupwithsubmitbuttonhasshownintaxratepage() {
		  assertEquals("Submit",taxrate.getSubmit().getText());
		 /*try {
		  //assertEquals("Submit",taxrate.getSubmit().getText());
		  
		  
		  }
		    catch (Exception e) {
				collector.addError(e);
			}
		 
		  
		  //collector.checkThat("FAILURE", "Submit".equals(taxrate.getSubmit().getText()), matchesTrue);
		 collector.checkThat("Submit", equalTo(taxrate.getSubmit().getText()));*/
		  
		  
		 
		  
	  }
	  
	  
//scenario outline:valid data
	  
	  @And("New Tax  Rate button is clicked")
	  public void newtaxratebuttonisclicked() {
		  base.waitAndClick(taxrate.getNew_TaxRate());
	  }

	  @Then("Entering the data in {string},{string},{string},{string},{string},{string} text box in Tax  Rate")
	  public void enteringthedataintextboxintaxrate(String tax_code, String tax_type, String description, String rate, String effective_from, String effective_to) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.taxrateCode=tax_code;
		    this.taxrateDesc=description;
		    this.Rate=rate;
		    
		    base.itemsToBeDeleted.add(tax_code);
		    base.implicitwait();
	        // 
	       base.waitAndSendKeys(taxrate.getTax_Code(), tax_code);
	       base.selectClass(taxrate.getTax_Type());
	       base.selectByVisibleText(tax_type);
	       
	       base.waitAndSendKeys(taxrate.getDescription(), description);
	       base.waitAndSendKeys(taxrate.getRate(), rate);
	       base.waitAndSendKeys(taxrate.getEffectiveFrom(), effective_from);
	       Thread.sleep(1000);
	       base.waitAndSendKeys(taxrate.getEffectiveTo(), effective_to);
	       
	  }

	  @When("Click on submit button in Tax  Rate page")
	  public void clickonsubmitbuttonintaxratepage() {
		  base.waitAndClick(taxrate.getSubmit());
	  }

	  @Then("A popop with message successfully added should be displayed in Tax  Rate page")
	  public void apopopwithmessagesuccessfullyaddedshouldbedisplayedintaxratepage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  assertEquals("Saved Successfully",validdata.getText());
	  }

	  @Then("The entered data is available in Tax  Rate table")
	  public void theentereddataisavailableintaxratetable() {
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
		    DBUtilities.findUniqueRowInTable("tax_rate","tax_code",this.taxrateCode);
		    assertEquals(this.taxrateCode, uniqueRowInTable.get("tax_code"));
	  }
	  
// scenario outline duplicate data
	  
	  @And("Set data in Tax  Rate table")
	  public void setdataintaxratetable() {
		  base.databaseWaitTime();
		  
			
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("tax_code","VAT-D");
		  	values.put("tax_type","Inter State");
		  	values.put("description","VAT desc");
		  	values.put("rate","6.00");
		  	values.put("effective_from","2021-07-29");
		  	values.put("effective_to","2021-12-11");
		  	
		  	
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	
		  	DBUtilities.insertIntoTable("tax_rate", values);
	  }

	  @Then("Entering the duplicate data in {string},{string},{string},{string},{string},{string} text box in Tax  Rate")
	  public void enteringtheduplicatedataintextboxintaxrate(String tax_code, String tax_type, String description, String rate, String effective_from, String effective_to) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.taxrateCode=tax_code;
		    this.taxrateDesc=description;
		    this.Rate=rate;
		    
		    base.itemsToBeDeleted.add(tax_code);
		    base.implicitwait();
	        // 
	       base.waitAndSendKeys(taxrate.getTax_Code(), tax_code);
	       base.selectClass(taxrate.getTax_Type());
	       base.selectByVisibleText(tax_type);
	       
	       base.waitAndSendKeys(taxrate.getDescription(), description);
	       base.waitAndSendKeys(taxrate.getRate(), rate);
	       base.waitAndSendKeys(taxrate.getEffectiveFrom(), effective_from);
	       Thread.sleep(1000);
	       base.waitAndSendKeys(taxrate.getEffectiveTo(), effective_to);
	  }

	  @Then("A popop with message already exists should be displayed in Tax  Rate page")
	  public void apopopwithmessagealreadyexistsshouldbedisplayedintaxratepage() throws InterruptedException {
		  Thread.sleep(2000);
			WebElement errormsg = BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
			String errormessage = errormsg.getText();
			assertEquals("Already exixts",errormessage);
	  }

	  @Then("The entered data should not be available in Tax  Rate table for duplicate data")
	  public void theentereddatashouldnotbeavailableintaxratetableforduplicatedata() {
		  base.databaseWaitTime();
		  assertEquals(0,DBUtilities.getNumberOfRows("tax_rate","tax_code",this.taxrateCode));
	  }


// scenario outline:invalid data
	  @Then("Entering the invalid data in {string},{string},{string},{string},{string},{string} text box in Tax  Rate")
	  public void enteringtheinvaliddataintextboxintaxrate(String tax_code, String tax_type, String description, String rate, String effective_from, String effective_to) throws InterruptedException {
		  Thread.sleep(2000);
		  
		    this.taxrateCode=tax_code;
		    this.taxrateDesc=description;
		    this.Rate=rate;
		    
		    base.itemsToBeDeleted.add(tax_code);
		    base.implicitwait();
	        // 
	       base.waitAndSendKeys(taxrate.getTax_Code(), tax_code);
	       base.selectClass(taxrate.getTax_Type());
	       base.selectByVisibleText(tax_type);
	       
	       base.waitAndSendKeys(taxrate.getDescription(), description);
	       base.waitAndSendKeys(taxrate.getRate(), rate);
	       base.waitAndSendKeys(taxrate.getEffectiveFrom(), effective_from);
	       Thread.sleep(1000);
	       base.waitAndSendKeys(taxrate.getEffectiveTo(), effective_to);
	  }



	  @Then("The entered data should not be available in Tax  Rate table for invalid data")
	  public void theentereddatashouldnotbeavailableintaxratetableforinvaliddata() {
          //base.alertgettext();
		  
		  base.databaseWaitTime();
		  assertEquals(0,DBUtilities.getNumberOfRows("tax_rate","tax_code",this.taxrateCode));
	  }
	  
//scenario outline:blank data
	  
	  @Then("Entering the blank data in {string},{string},{string},{string},{string},{string} text box in Tax  Rate")
	  public void enteringtheblankdataintextboxintaxrate(String tax_code, String tax_type, String description, String rate, String effective_from, String effective_to) throws InterruptedException {
		  Thread.sleep(2000);
		  
		    this.taxrateCode=tax_code;
		    this.taxrateDesc=description;
		    this.Rate=rate;
		    
		    base.itemsToBeDeleted.add(tax_code);
		    base.implicitwait();
	        // 
	       base.waitAndSendKeys(taxrate.getTax_Code(), tax_code);
	       base.selectClass(taxrate.getTax_Type());
	       base.selectByVisibleText(tax_type);
	       
	       base.waitAndSendKeys(taxrate.getDescription(), description);
	       base.waitAndSendKeys(taxrate.getRate(), rate);
	       base.waitAndSendKeys(taxrate.getEffectiveFrom(), effective_from);
	       Thread.sleep(1000);
	       base.waitAndSendKeys(taxrate.getEffectiveTo(), effective_to);
	  }

	  @Then("A popop with message Please enter all mandatory fields should be displayed in Tax  Rate page")
	  public void apopopwithmessagepleaseenterallmandatoryfieldsshouldbedisplayedintaxratepage() throws InterruptedException {
		  Thread.sleep(2000);   
		  base.implicitwait();
		  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String blankmessage = blnkmsg.getText();
	      assertEquals("Please Enter all mandatory fields",blankmessage);
	  }

	  @Then("The entered data should not be available in Tax  Rate table for blank data")
	  public void theentereddatashouldnotbeavailableintaxratetableforblankdata() {
		  base.databaseWaitTime();
		  assertEquals(0,DBUtilities.getNumberOfRows("tax_rate","tax_code",this.taxrateCode));
	  }

//scenario outline:edit functionality
	  @Given("Clicking the close button in Tax  Rate")
	  public void clickingtheclosebuttonintaxrate() {
		  base.implicitwait();
	      base.waitAndClick(taxrate.getCloseButton());
	  }

	  @Then("Checking the functionality of edit button in {string},{string},{string},{string},{string},{string} by clicking in Tax  Rate")
	  public void checkingthefunctionalityofeditbuttoninbyclickingintaxrate(String tax_code, String tax_type, String description, String rate, String effective_from, String effective_to) throws InterruptedException {
		  Thread.sleep(3000);
		  base.refreshApp(BaseClass.baseURL+url);
		    this.taxrateCode=tax_code;
		    this.taxrateDesc=description;
		    this.Rate=rate;
		    
		    base.itemsToBeDeleted.add(tax_code);
		    base.implicitwait();
		    base.waitAndSendKeys(taxrate.getSearchButton(), "VAT-D");
		    Thread.sleep(1000);
		    base.waitAndClick(taxrate.getEditIcon());
	        // 
		    
	       base.clearAndSendKeys(taxrate.getTax_Code(), tax_code);
	       base.selectClass(taxrate.getTax_Type());
	       base.selectByVisibleText(tax_type);
	       
	       base.clearAndSendKeys(taxrate.getDescription(), description);
	       base.clearAndSendKeys(taxrate.getRate(), rate);
	       base.clearAndSendKeys(taxrate.getEffectiveFrom(), effective_from);
	       Thread.sleep(1000);
	       base.clearAndSendKeys(taxrate.getEffectiveTo(), effective_to);
	       base.javaScriptClick(taxrate.getSubmit());
	  }

	  @Then("Checking the update button has shown in the Tax  Rate page")
	  public void checkingtheupdatebuttonhasshowninthetaxratepage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.refreshApp(BaseClass.baseURL+url);
		  
		  base.waitAndSendKeys(taxrate.getSearchButton(), "VAT-E");
		  base.waitAndClick(taxrate.getEditIcon());
		  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String update = updatebtn.getText();
          //assertEquals("Update",update);
          base.waitAndClick(taxrate.getCloseButton());
	  }

	  @Then("The edited data should be available in database of Tax  Rate")
	  public void theediteddatashouldbeavailableindatabaseoftaxrate() {
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
		    DBUtilities.findUniqueRowInTable("tax_rate","tax_code","VAT-E");
	  }
	  
//scenario outline: testing functionality of delete button
	  
	  @Then("Checking the functionality of delete icon by clicking in Tax  Rate")
	  public void checkingthefunctionalityofdeleteiconbyclickingintaxrate() throws InterruptedException {
		  Thread.sleep(2000);
		  base.refreshApp(BaseClass.baseURL+url);
		  
		  base.waitAndSendKeys(taxrate.getSearchButton(), "VAT-D");
		  Thread.sleep(200);
		  base.waitAndClick(taxrate.getDeleteIcon());
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Tax  Rate")
	  public void clickingdeletebuttonandapopupdeletedsucessfullyshouldbedisplayedintaxrate() throws InterruptedException {
		  Thread.sleep(2000);
			//delete icon popup 
			  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
		      String dltmessage = deletemsg.getText();
	          assertEquals("Are you Sure ?",dltmessage);
		  
		  //delete msg popup        
	          
	          
        base.javaScriptClick(taxrate.getDeleteButtonPopup());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
        assertEquals("Deleted Successfully",deletesuccess);
	  }

	  @Then("Verifying that the entered data was deleted in database of Tax  Rate")
	  public void verifyingthattheentereddatawasdeletedindatabaseoftaxrate() throws InterruptedException {
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("tax_rate","tax_code","VAT-D");
		   base.databaseWaitTime();
	       assertEquals("1",uniqueRowInTable.get("deleted"));
	       DBUtilities.findUniqueRowInTable("tax_rate","tax_code","VAT-D");
	  }
	  
//scenario: searching functionality
	  
	  @Then("Searching the Tax Code by sending keys")
	  public void searchingthefinancialcalcodebysendingkeys() throws InterruptedException {
		  Thread.sleep(2000);
		  base.refreshApp(BaseClass.baseURL+url);
			
			
			base.waitAndClick(taxrate.getSearchButton());
			base.waitAndSendKeys(taxrate.getSearchButton(), "VAT-D");
	  }

	  @Then("The text in the search box should be equal to values in the Tax  Rate table")
	  public void thetextinthesearchboxshouldbeequaltovaluesinthetaxratetable() throws InterruptedException {
		  Thread.sleep(1000);
			assertEquals("VAT-D",taxrate.getSearchCheck().getText());
			base.databaseWaitTime();
	  }

	  @Then("Clicking Export button in Tax  Rate master page")
	  public void clickingexportbuttonintaxratemasterpage() {
		  base.waitAndClick(taxrate.getExport());
	  }
	  
}
