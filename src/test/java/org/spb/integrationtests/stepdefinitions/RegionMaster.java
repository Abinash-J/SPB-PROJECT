package org.spb.integrationtests.stepdefinitions;
import static org.junit.Assert.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.RegionMasterPom;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegionMaster {
	private static String url = "region";
	  BaseClass base = new BaseClass();
	  RegionMasterPom reg =new RegionMasterPom();
	  private String regCode;
	  private String regName;
	  public RegionMaster(BaseClass base) {
	    this.base = base;
	  }
	  
	@Given("A web browser is on the RegionMaster page")
	public void a_web_browser_is_on_the_region_master_page()  {
		BaseClass.driver.get(BaseClass.baseURL + url);
	}

	@When("Add new region button is clicked")
	public void add_new_region_button_is_clicked() {
		 base.WaitUntil(reg.getNewRegion());
		 base.click(reg.getNewRegion());
	}

	@Then("Popup with title as Region should be displayed with Submit button in Region Page")
	public void popup_with_title_as_region_should_be_displayed_with_submit_button_in_region_page() throws InterruptedException {
		 base.implicitwait();
		    Thread.sleep(3000);
		    WebElement region=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
		    String regPopup = region.getText();
	        assertEquals(regPopup,"Region");
	        base.implicitwait();
	        String submittext = reg.getRegionSubmit().getText();
	        assertEquals("Submit",submittext);
	}
//Valid data
	@Then("Entering valid {string} and {string} in the input box in RegionPage")
	public void entering_valid_and_in_the_input_box_in_region_page(String reg_code, String reg_name) {
		this.regCode=reg_code;
	    this.regName=reg_name;
	    base.itemsToBeDeleted.add(reg_code);
	    base.implicitwait();
	    base.sendkeys(reg.getRegionCode(),reg_code);
	    base.WaitUntilVisible(reg.getRegionName());
	    base.sendkeys(reg.getRegionName(),reg_name);
	    base.WaitUntilVisible(reg.getRegionSubmit());
	}

	@Then("Click the submit button in Region form")
	public void click_the_submit_button_in_region_form() {
	    base.click(reg.getRegionSubmit());
	}

	@Then("A Popup with saved successfully should be displayed should be displayed in RegionMaster Page")
	public void a_popup_with_saved_successfully_should_be_displayed_should_be_displayed_in_region_master_page() throws InterruptedException {
		Thread.sleep(2000);
		  base.implicitwait();
		  String validData = BaseClass.driver.switchTo().activeElement().getAttribute("id");
	        assertEquals(validData,"showAlert");
	}

	@Then("The entered data should be available in Region table")
	public void the_entered_data_should_be_available_in_region_table() {
		base.databaseWaitTime();
		HashMap<String, String> uniqueRowInTable =
	     DBUtilities.findUniqueRowInTable("region", "region_code", this.regCode);
	      assertEquals(this.regCode, uniqueRowInTable.get("region_code"));
	      assertEquals(this.regName, uniqueRowInTable.get("region_name"));
	}
	
//invalid
	@Given("Set data in Region table")
	public void set_data_in_region_table() {
		base.databaseWaitTime();
        String regionCode="KL";
        LinkedHashMap<String, String> value = new LinkedHashMap<>();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentDateTime = format.format(date);
        value.put("region_code",regionCode);
        value.put("region_name","Kerala");
        value.put("active","0");
        value.put("deleted","0");
        value.put("created_by","Integration Test");
        value.put("updated_by","Integration Test");
        value.put("created_on",(currentDateTime));
        value.put("updated_on",(currentDateTime));
        DBUtilities.insertIntoTable("region",value);
	}

	@Then("Entering duplicate data in {string} and {string} in the input box in RegionForm")
	public void entering_duplicate_data_in_and_in_the_input_box_in_region_form(String reg_code, String reg_name) {
		this.regCode=reg_code;
	    this.regName=reg_name;
	    base.itemsToBeDeleted.add(reg_code);
	    base.implicitwait();
	    base.sendkeys(reg.getRegionCode(),reg_code);
	    base.WaitUntilVisible(reg.getRegionName());
	    base.sendkeys(reg.getRegionName(),reg_name);
	    base.WaitUntilVisible(reg.getRegionSubmit());
	}

	@Then("A popup with message already exists data should be displayed in RegionPage")
	public void a_popup_with_message_already_exists_data_should_be_displayed_in_region_page() throws InterruptedException {
		Thread.sleep(3000);
		WebElement errormsg = BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		String errormessage = errormsg.getText();
		assertEquals("Duplicate Entry",errormessage);
	}

	@Then("Entering {string} and {string} in the input box with blank data in RegionForm")
	public void entering_and_in_the_input_box_with_blank_data_in_region_form(String reg_code, String reg_name) {
		this.regCode=reg_code;
	    this.regName=reg_name;
	    base.itemsToBeDeleted.add(reg_code);
	    base.implicitwait();
	    base.sendkeys(reg.getRegionCode(),reg_code);
	    base.WaitUntilVisible(reg.getRegionName());
	    base.sendkeys(reg.getRegionName(),reg_name);
	    base.WaitUntilVisible(reg.getRegionSubmit());
	}

	@Then("A popup with message please enter all mandatory fields should be displayed in RegionPage")
	public void a_popup_with_message_please_enter_all_mandatory_fields_should_be_displayed_in_region_page() throws InterruptedException {
		Thread.sleep(3000);
		base.implicitwait();
		WebElement errormsg = BaseClass.driver.findElement(By.xpath("//div[@class='row']//span[@id='errorMsg']"));
		String errormessage = errormsg.getText();
		assertEquals(errormessage, "Please Enter all mandatory fields");
	}
//Edit
	@Then("Clicking the close button in RegionForm")
	public void clicking_the_close_button_in_region_form() {
		 base.implicitwait();
		 base.click(reg.getRegionClose());
	}

	@Then("Checking the functionality of edit button by clicking in RegionPage")
	public void checking_the_functionality_of_edit_button_by_clicking_in_region_page() throws InterruptedException {
		base.refreshApp(BaseClass.baseURL+url);  
		base.WaitUntil(reg.getEdit_RM());
	    base.click(reg.getEdit_RM());
	    base.WaitUntilVisible(reg.getRegionCode());
	    reg.getRegionCode().clear();
	    base.sendkeys(reg.getRegionCode(),"Kr");
	    base.WaitUntilVisible(reg.getRegionName());
	    reg.getRegionName().clear();
	    base.sendkeys(reg.getRegionName(),"Karnataka");
	}

	@Then("A Popup with title as Region should be displayed with update button in Region Page")
	public void a_popup_with_title_as_region_should_be_displayed_with_update_button_in_region_page() throws InterruptedException {
		base.implicitwait();
		Thread.sleep(3000);
		String submittext = reg.getRegionSubmit().getText();
        assertEquals(submittext,"Update");
        base.WaitUntilVisible(reg.getRegionSubmit());
	    base.click(reg.getRegionSubmit());
	}

	@Then("The edited data should be available in database of RegionMaster")
	public void the_edited_data_should_be_available_in_database_of_region_master() throws InterruptedException {
		 base.databaseWaitTime();
	     Thread.sleep(3000);
		 HashMap<String, String> uniqueRowInTable =
	     DBUtilities.findUniqueRowInTable("region", "region_code","Kr");
		 assertEquals("Kr",uniqueRowInTable.get("region_code"));
	}
//Delete
	@Then("Checking the functionality of delete icon button in RegionMaster Page")
	public void checking_the_functionality_of_delete_icon_button_in_region_master_page() throws InterruptedException {
		 base.implicitwait();
	     base.click(reg.getDelete_RM());
	     Thread.sleep(2000);
	     base.implicitwait();
	     base.implicitwait();
	     String delete = BaseClass.driver.switchTo().activeElement().getAttribute("id");
	     assertEquals(delete, "delete-modal");
	}

	@Then("Clicking delete button and a popup deleted sucessfully should be displayed in RegionPage")
	public void clicking_delete_button_and_a_popup_deleted_sucessfully_should_be_displayed_in_region_page() throws InterruptedException {
		Thread.sleep(2000);
		base.click(reg.getPress_Delete());
	     base.databaseWaitTime();
	     
	     WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	     String deletesuccess = dltsccsflymsg.getText();
	          assertEquals(deletesuccess,"Deleted Successfully"); 
	}

	@Then("Verifying that the enter data was deleted in region database")
	public void verifying_that_the_enter_data_was_deleted_in_region_database() throws InterruptedException {
		Thread.sleep(2000);
		HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("region","region_code","KR");
		Thread.sleep(2000);
       assertEquals("1",uniqueRowInTable.get("deleted"));
       base.databaseWaitTime();
	   DBUtilities.deleteFromTable("region","region_code","KR");
	}
//Search
	@Then("Searching the Region code by sending keys")
	public void searching_the_region_code_by_sending_keys() {
		base.refreshApp(BaseClass.baseURL+url);
		base.WaitUntilVisible(reg.getSearchReg());
		base.sendkeys(reg.getSearchReg(),"KL");
	}

	@Then("The text in the search box should be equal to values in the Regiontable")
	public void the_text_in_the_search_box_should_be_equal_to_values_in_the_regiontable() throws InterruptedException {
		Thread.sleep(1000);
		assertEquals(reg.getSearchCheck().getText(),"KL");
		base.databaseWaitTime();
		DBUtilities.deleteFromTable("region","region_code","KL");
	}

	@Then("Clicking Export button in RegionMaster page")
	public void clicking_export_button_in_region_master_page() {
	    base.WaitUntil(reg.getExport());
	    base.click(reg.getExport());
	}

}
