package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.FinancialCalendarPOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class FinancialCalendarMaster {

	
	private static String url = "financialcalendar";
	  BaseClass base = new BaseClass();
	  FinancialCalendarPOM fic= new FinancialCalendarPOM();
	  private String financialCalCode;
	  //private String taxrateDesc;

	  public FinancialCalendarMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens

	  @When("Delete data initially from database in Financial Calendar")
	  public void delete_data_initially_from_database_in_financial_calendar() {
		  DBUtilities.deleteFromTable("fin_cal","fin_cal_code","1901");
		  DBUtilities.deleteFromTable("fin_cal_type","calendar_code","1901");
	  }

	  @Given("A web browser is on the Financial Calendar master page")
	  public void a_web_browser_is_on_the_financial_calendar_master_page() throws InterruptedException {
		  BaseClass.driver.get(BaseClass.baseURL + url);
			Thread.sleep(2000);
	  }

	  @When("New Financial Calendar button is clicked on page")
	  public void new_financial_calendar_button_is_clicked_on_page() {
		  base.waitAndClick(fic.getNew_FinancialCalendar());
	  }

	  @Then("A popup with Financial Calendar should be displayed when clicked")
	  public void a_popup_with_financial_calendar_should_be_displayed_when_clicked() throws InterruptedException {
		  base.implicitwait();
			
	        Thread.sleep(3000);
		    WebElement financialCalendarTitle=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
		    base.implicitwait();
		    assertEquals("Financial Calendar",financialCalendarTitle.getText());
		    
	  }

	  @Then("A popup with submit button has shown in Financial Calendar page")
	  public void a_popup_with_submit_button_has_shown_in_financial_calendar_page() {
		  
		  assertEquals("Submit",fic.getSubmit().getText());
	  }
	  
//scenario outline:valid data
	  
	  @Given("New Financial Calendar button is clicked")
	  public void new_financial_calendar_button_is_clicked() {
		  base.waitAndClick(fic.getNew_FinancialCalendar());
	  }

	  @Then("Entering the data in {string},{string},{string},{string},{string},{string},{string},{string},{string} text box in Financial Calendar")
	  public void entering_the_data_in_text_box_in_financial_calendar(String financial_cal_code, String prefix, String type, String year, String quarter, String num, String from_date, String to_date, String period_name) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.financialCalCode=financial_cal_code;
		    
		    base.itemsToBeDeleted.add(financial_cal_code);
		    base.implicitwait();
	        // 
	       
	       base.selectClass(fic.getFinancialCal_Code());
	       base.selectByVisibleText("1901");
	       
	       base.waitAndSendKeys(fic.getPrefix(), prefix);
	       base.waitAndSendKeys(fic.getType(), type);
	       base.waitAndSendKeys(fic.getYear(), year);
	       base.waitAndSendKeys(fic.getQuarter(), quarter);
	       base.waitAndSendKeys(fic.getNum(), num);
	       base.waitAndSendKeys(fic.getFromDate(), from_date);
	       base.waitAndSendKeys(fic.getToDate(), to_date);
	       base.javaScriptClick(fic.getAdjustingYes());
	       
		   
	  }

	  @When("Click on submit button in Financial Calendar page")
	  public void click_on_submit_button_in_financial_calendar_page() {
		  base.waitAndClick(fic.getSubmit());
	  }

	  @Then("A popop with message successfully added should be displayed in Financial Calendar page")
	  public void a_popop_with_message_successfully_added_should_be_displayed_in_financial_calendar_page() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  assertEquals("Saved Successfully",validdata.getText());
	  }

	  @Then("The entered data is available in Financial Calendar table")
	  public void the_entered_data_is_available_in_financial_calendar_table() {
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
		    DBUtilities.findUniqueRowInTable("fin_cal","fin_cal_code",this.financialCalCode);
		    assertEquals(this.financialCalCode, uniqueRowInTable.get("fin_cal_code"));
	  }

// scenario outline duplicate data
	  
	  @Given("Set data in Financial Calendar table")
	  public void set_data_in_financial_calendar_table() {
		  base.databaseWaitTime();
		  
			
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("fin_cal_code","1901");
		  	values.put("prefix","Current");
		  	values.put("type","year");
		  	values.put("year","2021");
		  	values.put("quarter","2");
		  	values.put("num","33");
		  	
		  	values.put("from_date","2021-03-02");
		  	values.put("to_date","2021-12-11");
		  	values.put("period_name","September");
		  	values.put("adjusting","0");
		  	
		  	
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	
		  	DBUtilities.insertIntoTable("fin_cal", values);
	  }
	  
	  
	  @Then("Entering the duplicate data in {string},{string},{string},{string},{string},{string},{string},{string},{string} text box in Financial Calendar")
	  public void entering_the_duplicate_data_in_text_box_in_financial_calendar(String financial_cal_code, String prefix, String type, String year, String quarter, String num, String from_date, String to_date, String period_name) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.financialCalCode=financial_cal_code;
		    
		    base.itemsToBeDeleted.add(financial_cal_code);
		    base.implicitwait();
	        // 
	       
	       base.selectClass(fic.getFinancialCal_Code());
	       base.selectByVisibleText("1901");
	       
	       base.waitAndSendKeys(fic.getPrefix(), prefix);
	       base.waitAndSendKeys(fic.getType(), type);
	       base.waitAndSendKeys(fic.getYear(), year);
	       base.waitAndSendKeys(fic.getQuarter(), quarter);
	       base.waitAndSendKeys(fic.getNum(), num);
	       base.waitAndSendKeys(fic.getFromDate(), from_date);
	       base.waitAndSendKeys(fic.getToDate(), to_date);
	       base.javaScriptClick(fic.getAdjustingNo());
	  }

	  @Then("A popop with message already exists should be displayed in Financial Calendar page")
	  public void a_popop_with_message_already_exists_should_be_displayed_in_financial_calendar_page() throws InterruptedException {
		  Thread.sleep(3000);
			WebElement errormsg = BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
			String errormessage = errormsg.getText();
			assertEquals("Already exixts",errormessage);
	  }

	  @Then("The entered data should not be available in Financial Calendar table for duplicate data")
	  public void the_entered_data_should_not_be_available_in_financial_calendar_table_for_duplicate_data() {
		  base.databaseWaitTime();
		  assertEquals(0,DBUtilities.getNumberOfRows("fin_cal","fin_cal_code",this.financialCalCode));
	  }
	  
// scenario outline:invalid data	  
	  
	  @Then("Entering the invalid data in {string},{string},{string},{string},{string},{string},{string},{string},{string} text box in Financial Calendar")
	  public void entering_the_invalid_data_in_text_box_in_financial_calendar(String financial_cal_code, String prefix, String type, String year, String quarter, String num, String from_date, String to_date, String period_name) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.financialCalCode=financial_cal_code;
		    
		    base.itemsToBeDeleted.add(financial_cal_code);
		    base.implicitwait();
	        // 
	       
	       base.selectClass(fic.getFinancialCal_Code());
	       base.selectByVisibleText("1901");
	       
	       base.waitAndSendKeys(fic.getPrefix(), prefix);
	       base.waitAndSendKeys(fic.getType(), type);
	       base.waitAndSendKeys(fic.getYear(), year);
	       base.waitAndSendKeys(fic.getQuarter(), quarter);
	       base.waitAndSendKeys(fic.getNum(), num);
	       base.waitAndSendKeys(fic.getFromDate(), from_date);
	       base.waitAndSendKeys(fic.getToDate(), to_date);
	       base.javaScriptClick(fic.getAdjustingNo());
	  }
	  
	  

	  @Then("The entered data should not be available in Financial Calendar table for invalid data")
	  public void the_entered_data_should_not_be_available_in_financial_calendar_table_for_invalid_data() {
		  
		  base.alertgettext();
		  
		  base.databaseWaitTime();
		  assertEquals(0,DBUtilities.getNumberOfRows("fin_cal","fin_cal_code",this.financialCalCode));
	  }

//scenario outline:blank data
	  
	  @Then("Entering the blank data in {string},{string},{string},{string},{string},{string},{string},{string},{string} text box in Financial Calendar")
	  public void entering_the_blank_data_in_text_box_in_financial_calendar(String financial_cal_code, String prefix, String type, String year, String quarter, String num, String from_date, String to_date, String period_name) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.financialCalCode=financial_cal_code;
		    
		    base.itemsToBeDeleted.add(financial_cal_code);
		    base.implicitwait();
	        // 
	       
	       base.selectClass(fic.getFinancialCal_Code());
	       base.selectByVisibleText("1901");
	       
	       base.waitAndSendKeys(fic.getPrefix(), prefix);
	       base.waitAndSendKeys(fic.getType(), type);
	       base.waitAndSendKeys(fic.getYear(), year);
	       base.waitAndSendKeys(fic.getQuarter(), quarter);
	       base.waitAndSendKeys(fic.getNum(), num);
	       base.waitAndSendKeys(fic.getFromDate(), from_date);
	       base.waitAndSendKeys(fic.getToDate(), to_date);
	       base.javaScriptClick(fic.getAdjustingNo());
	  }

	  @Then("A popop with message Please enter all mandatory fields should be displayed in Financial Calendar page")
	  public void a_popop_with_message_please_enter_all_mandatory_fields_should_be_displayed_in_financial_calendar_page() throws InterruptedException {
		  Thread.sleep(2000);   
		  base.implicitwait();
		  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String blankmessage = blnkmsg.getText();
	      assertEquals("Please Enter all mandatory fields",blankmessage);
	  }
	  
	  @Then("The enterd data should not be available in Financial Calendar Database for blank data")
	  public void theenterddatashouldnotbeavailableinfinancialcalendardatabaseforblankdata() {
		  base.databaseWaitTime();
		    assertEquals(0,DBUtilities.getNumberOfRows("fin_cal","fin_cal_code",this.financialCalCode));
	  }
	  
//scenario outline:edit functionality
	  
	  @Given("Clicking the close button in Financial Calendar")
	  public void clicking_the_close_button_in_financial_calendar() {
		  base.implicitwait();
	      base.waitAndClick(fic.getCloseButton());
	  }

	  @Then("Checking the functionality of edit button in {string},{string},{string},{string},{string},{string},{string},{string},{string} by clicking in Financial Calendar")
	  public void checking_the_functionality_of_edit_button_in_by_clicking_in_financial_calendar(String financial_cal_code, String prefix, String type, String year, String quarter, String num, String from_date, String to_date, String period_name) throws InterruptedException {
		  
		  Thread.sleep(4000);
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(3000);
		
		  base.waitAndSendKeys(fic.getSearchButton(), "1901");
		  base.javaScriptClick(fic.getEditIcon());
		    this.financialCalCode=financial_cal_code;
		    
		    base.itemsToBeDeleted.add(financial_cal_code);
		    base.implicitwait();
	        // 
	       
	       base.selectClass(fic.getFinancialCal_Code());
	       base.selectByVisibleText("1901");
	       
	       base.clearAndSendKeys(fic.getPrefix(), prefix);
	       base.clearAndSendKeys(fic.getType(), type);
	       base.clearAndSendKeys(fic.getYear(), year);
	       base.clearAndSendKeys(fic.getQuarter(), quarter);
	       base.clearAndSendKeys(fic.getNum(), num);
	       base.clearAndSendKeys(fic.getFromDate(), from_date);
	       base.clearAndSendKeys(fic.getToDate(), to_date);
	       base.javaScriptClick(fic.getAdjustingYes());
	       base.javaScriptClick(fic.getSubmit());
	  }

	  @Then("Checking the update button has shown in the Financial Calendar page")
	  public void checking_the_update_button_has_shown_in_the_financial_calendar_page() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(2000);
		  base.waitAndSendKeys(fic.getSearchButton(), "1901");
		  base.waitAndClick(fic.getEditIcon());
		  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String update = updatebtn.getText();
          assertEquals("Update",update);
          base.waitAndClick(fic.getCloseButton());
	  }

	  @Then("The edited data should be available in database of Financial Calendar")
	  public void the_edited_data_should_be_available_in_database_of_financial_calendar() {
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
		    DBUtilities.findUniqueRowInTable("fin_cal","fin_cal_code","1901");
	  }
	  
//scenario outline: testing functionality of delete button
	  
	  @Then("Checking the functionality of delete icon by clicking in Financial Calendar")
	  public void checking_the_functionality_of_delete_icon_by_clicking_in_financial_calendar() throws InterruptedException {
		  Thread.sleep(7000);
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(7000);
		  base.waitAndSendKeys(fic.getSearchButton(), "1901"); 
		  
		  base.javaScriptClick(fic.getDeleteIcon());
	        
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Financial Calendar")
	  public void clicking_delete_button_and_a_popup_deleted_sucessfully_should_be_displayed_in_financial_calendar() throws InterruptedException {
		  Thread.sleep(2000);
			//delete icon popup 
			  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
		      String dltmessage = deletemsg.getText();
	          assertEquals("Are you Sure ?",dltmessage);
		  
		  //delete msg popup        
	          
	          
          base.javaScriptClick(fic.getDeleteButtonPopup());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
	  }

	  @Then("Verifying that the entered data was deleted in database of Financial Calendar")
	  public void verifying_that_the_entered_data_was_deleted_in_database_of_financial_calendar() throws InterruptedException {
		  Thread.sleep(1000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("consignee","consignee_code","1901");
		   Thread.sleep(1000);
	       assertEquals("1",uniqueRowInTable.get("deleted"));
	  }

//scenario: searching functionality
	  
	  @Then("Searching the Financial Cal  Code by sending keys")
	  public void searching_the_financial_cal_code_by_sending_keys() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.waitAndClick(fic.getSearchButton());
			base.waitAndSendKeys(fic.getSearchButton(), "1901");
	  }

	  @Then("The text in the search box should be equal to values in the Financial Calendar table")
	  public void the_text_in_the_search_box_should_be_equal_to_values_in_the_financial_calendar_table() throws InterruptedException {
		  Thread.sleep(1000);
			assertEquals("1901",fic.getSearchCheck().getText());
			base.databaseWaitTime();
	  }

	  @Then("Clicking Export button in Financial Calendar master page")
	  public void clicking_export_button_in_financial_calendar_master_page() {
		  base.waitAndClick(fic.getExport());
	  }

}
