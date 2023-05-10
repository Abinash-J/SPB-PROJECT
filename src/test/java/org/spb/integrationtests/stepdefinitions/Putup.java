package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.PutupMasterPom;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Putup {
	private static String url = "putup";
	  BaseClass base = new BaseClass();
	  PutupMasterPom putup = new PutupMasterPom();
	  private String putupCode;
	  private String putupDesc;
	  public Putup(BaseClass base) {
	    this.base = base;
	  }
	@Given("A web browser is on the PutupMaster page")
	public void a_web_browser_is_on_the_putup_master_page() throws InterruptedException {
		BaseClass.driver.get(BaseClass.baseURL + url);
		Thread.sleep(2000);
	
	}
	
	@When("Add new putup button is clicked")
	public void add_new_putup_button_is_clicked() {
		base.WaitUntil(putup.getNewPutup());
		 base.click(putup.getNewPutup());
	}

	@Then("Popup with title as PutupMaster should be displayed with Submit button in Department Page")
	public void popup_with_title_as_putup_master_should_be_displayed_with_submit_button_in_department_page() throws InterruptedException {
		  base.implicitwait();
		    Thread.sleep(3000);
		    WebElement putupMaster=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
		    String putupPopup = putupMaster.getText();
	        assertEquals(putupPopup,"Putup Master");
	        base.implicitwait();
	        String submittext = putup.getPutupSubmit().getText();
	        assertEquals("Submit",submittext);
	}

//valid	

	@Then("Entering valid {string} and {string} in the input box in PutupMasterPage")
	public void entering_valid_and_in_the_input_box_in_putup_master_page(String putup_code, String putup_desc) {
		    this.putupCode=putup_code;
		    this.putupDesc=putup_desc;
		    base.itemsToBeDeleted.add(putup_code);
		    base.implicitwait();
		    base.sendkeys(putup.getPutupCode(),putup_code);
		    base.WaitUntilVisible(putup.getPutupDescription());
		    base.sendkeys(putup.getPutupDescription(),putup_desc);
		    base.WaitUntilVisible(putup.getPutupSubmit());
	}

	@Then("Click the submit button in Putup form")
	public void click_the_submit_button_in_putup_form() {
	        base.click(putup.getPutupSubmit());
	}

	@Then("A Popup with saved successfully should be displayed should be displayed in PutupMaster Page")
	public void a_popup_with_saved_successfully_should_be_displayed_should_be_displayed_in_putup_master_page() throws InterruptedException {
		Thread.sleep(2000);
		base.implicitwait();
		String validData = BaseClass.driver.switchTo().activeElement().getAttribute("id");
	    assertEquals(validData,"showAlert");
	}

	@Then("The entered data should be available in PutupMaster table")
	public void the_entered_data_should_be_available_in_putup_master_table() {
		base.databaseWaitTime();
		HashMap<String, String> uniqueRowInTable =
	     DBUtilities.findUniqueRowInTable("putup", "putup_code", this.putupCode);
	      assertEquals(this.putupCode, uniqueRowInTable.get("putup_code"));
	      assertEquals(this.putupDesc, uniqueRowInTable.get("description"));
	}
//duplicate
	@Given("Set data in PutupMaster table")
	public void set_data_in_putup_master_table() {
		base.databaseWaitTime();
        String putupCode="A3";
        LinkedHashMap<String, String> value = new LinkedHashMap<>();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentDateTime = format.format(date);
        value.put("putup_code",putupCode);
        value.put("description","300 sheets");
        value.put("active","0");
        value.put("deleted","0");
        value.put("created_by","Integration Test");
        value.put("updated_by","Integration Test");
        value.put("created_on",(currentDateTime));
        value.put("updated_on",(currentDateTime));
        DBUtilities.insertIntoTable("putup",value);
	}

	@Then("Entering duplicate data in {string} and {string} in the input box in PutupForm")
	public void entering_duplicate_data_in_and_in_the_input_box_in_putup_form(String putup_code, String putup_desc) {
		    this.putupCode=putup_code;
		    this.putupDesc=putup_desc;
		    base.itemsToBeDeleted.add(putup_code);
		    base.implicitwait();
		    base.sendkeys(putup.getPutupCode(),putup_code);
		    base.WaitUntilVisible(putup.getPutupDescription());
		    base.sendkeys(putup.getPutupDescription(),putup_desc);
		    base.WaitUntilVisible(putup.getPutupSubmit());
	}

	@Then("A popup with message already exists data should be displayed in PutupPage")
	public void a_popup_with_message_already_exists_data_should_be_displayed_in_putup_page() throws InterruptedException {
		Thread.sleep(3000);
		WebElement errormsg = BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		String errormessage = errormsg.getText();
		assertEquals("Duplicate Entry",errormessage);
	}
//blank
	@Then("Entering {string} and {string} in the input box with blank data in PutupForm")
	public void entering_and_in_the_input_box_with_blank_data_in_putup_form(String putup_code, String putup_desc) {
		 this.putupCode=putup_code;
		    this.putupDesc=putup_desc;
		    base.itemsToBeDeleted.add(putup_code);
		    base.implicitwait();
		    base.sendkeys(putup.getPutupCode(),putup_code);
		    base.WaitUntilVisible(putup.getPutupDescription());
		    base.sendkeys(putup.getPutupDescription(),putup_desc);
		    base.WaitUntilVisible(putup.getPutupSubmit());
	}

	@Then("A popup with message please enter all mandatory fields should be displayed in PutupForm")
	public void a_popup_with_message_please_enter_all_mandatory_fields_should_be_displayed_in_putup_form() throws InterruptedException {
		Thread.sleep(3000);
		base.implicitwait();
		WebElement errormsg = BaseClass.driver.findElement(By.xpath("//div[@class='row']//span[@id='errorMsg']"));
		String errormessage = errormsg.getText();
		assertEquals(errormessage, "Please Enter all mandatory fields");
	}
//edit
	@Then("Clicking the close button in PutupForm")
	public void clicking_the_close_button_in_putup_form() {
		 base.implicitwait();
		    base.click(putup.getPutupClose());
	}

	@Then("Checking the functionality of edit button by clicking in PutupMasterPage")
	public void checking_the_functionality_of_edit_button_by_clicking_in_putup_master_page() {
		base.refreshApp(BaseClass.baseURL+url);  
		base.WaitUntil(putup.getEdit_Putup());
	    base.click(putup.getEdit_Putup());
	    base.WaitUntil(putup.getPutupCode());
	    putup.getPutupCode().clear();
	    base.sendkeys(putup.getPutupCode(),"aa7");
	    base.WaitUntilVisible(putup.getPutupDescription());
	    putup.getPutupDescription().clear();
	    base.sendkeys(putup.getPutupDescription(),"100 Sheets");
	    base.WaitUntilVisible(putup.getPutupSubmit());
	    
	}

	@Then("A Popup with title as Department should be displayed with update button in PutupMaster Page")
	public void a_popup_with_title_as_department_should_be_displayed_with_update_button_in_putup_master_page() throws InterruptedException {
		   base.implicitwait();
		   Thread.sleep(3000);
	        String submittext = putup.getPutupSubmit().getText();
	        assertEquals("Update",submittext);
	        base.click(putup.getPutupSubmit());
	}
	@Then("The edited data should be available in database of PutupMaster")
	public void the_edited_data_should_be_available_in_database_of_putupmaster() throws InterruptedException {
		 base.databaseWaitTime();
	     Thread.sleep(3000);
		 HashMap<String, String> uniqueRowInTable =
	     DBUtilities.findUniqueRowInTable("putup", "putup_code","aa7");
		 assertEquals("aa7",uniqueRowInTable.get("putup_code"));
	}
//delete
	@Then("Checking the functionality of delete icon button in PutupMaster Page")
	public void checking_the_functionality_of_delete_icon_button_in_putup_master_page() throws InterruptedException {
		base.refreshApp(BaseClass.baseURL+url); 
		 base.implicitwait();
		 Thread.sleep(2000);
	     base.click(putup.getDelete_Putup());
	     Thread.sleep(2000);
	     base.implicitwait();
	     base.implicitwait();
	     String delete = BaseClass.driver.switchTo().activeElement().getAttribute("id");
	     assertEquals(delete, "delete-modal");
	}

	@Then("Clicking delete button and a popup deleted sucessfully should be displayed in PutupMasterPage")
	public void clicking_delete_button_and_a_popup_deleted_sucessfully_should_be_displayed_in_putup_master_page() throws InterruptedException {
		 base.click(putup.getPress_Delete());
	     base.databaseWaitTime();
	     Thread.sleep(2000);
	     WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	     String deletesuccess = dltsccsflymsg.getText();
	          assertEquals(deletesuccess,"Deleted Successfully");
	}

	@Then("Verifying that the enter data was deleted in Putup database")
	public void verifying_that_the_enter_data_was_deleted_in_putup_database() throws InterruptedException {
		Thread.sleep(2000);
		HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("putup","putup_code","aa7");
       assertEquals("1",uniqueRowInTable.get("deleted"));
	   DBUtilities.deleteFromTable("putup","putup_code","A3");
	}

	//search box
	@Then("Searching the Putup code by sending keys")
	public void searchPutupCode() {
		base.refreshApp(BaseClass.baseURL+url);
		base.WaitUntilVisible(putup.getSearchPutup());
		base.sendkeys(putup.getSearchPutup(),"A3");
	}

	@Then("The text in the search box should be equal to values in the Putuptable")
	public void	theTextinTheSearchBoxEqulstoValueInTable() throws InterruptedException {
		Thread.sleep(1000);
		assertEquals(putup.getSearchCheck().getText(),"A3");
		base.databaseWaitTime();
		DBUtilities.deleteFromTable("putup","putup_code","A3");
		
	}
	//Export button
	@Then("Clicking Export button in PutupMaster page")
	public void	clickingExportbutton() throws InterruptedException {
	 	base.WaitUntil(putup.getPutupExport());
	 	base.click(putup.getPutupExport());
	 	
	}

}
