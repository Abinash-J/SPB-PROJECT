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
import org.spb.integrationtests.pageobjectmodel.CustomerPOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CustomerMaster {

	
	private static String url = "customer";
	  BaseClass base = new BaseClass();
	  CustomerPOM customer= new CustomerPOM();
	  private String customerCode;
	  
	  private String pan;
	  private String gst;
	  private String tin;
	  
	  public CustomerMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens
	  
	  @When("Delete data initially from database in Customer")
	  public void delete_data_initially_from_database_in_customer() {
	      
		  
		  DBUtilities.deleteFromTable("customer", "date_established", "2015-03-02");
		  
		  DBUtilities.deleteFromTable("customer", "customer_code", "CSTC24");
		  DBUtilities.deleteFromTable("customer", "customer_code", "cs");
		  
		  DBUtilities.deleteFromTable("customer_type", "customer_type_code", "D25");
		  DBUtilities.deleteFromTable("indentor", "indentor_code", "edt24");
		  DBUtilities.deleteFromTable("indentor", "indentor_code", "INDC24");
		  DBUtilities.deleteFromTable("indentor_type", "indentor_type_code", "IND021");
		  DBUtilities.deleteFromTable("indentor_type", "indentor_type_code", "IND02");
		  
		  //base.itemsToBeDeleted.add("IND02");
		    /*base.itemsToBeDeleted.forEach(
			        lc -> {
			          DBUtilities.deleteFromTable("indentor_type", "indentor_type_code",lc);
			        });*/
	  }

	  @Given("A web browser is on the Customer Master page")
	  public void a_web_browser_is_on_the_customer_master_page() throws InterruptedException {
		  BaseClass.driver.get(BaseClass.baseURL + url);
			Thread.sleep(2000);
	  }

	  @When("Add new Customer button is clicked")
	  public void add_new_customer_button_is_clicked() {
		  base.waitAndClick(customer.getNewCustomer());
	  }

	  @Then("Popup with title as Customer Master should be displayed with Submit button in Customer Page")
	  public void popup_with_title_as_customer_master_should_be_displayed_with_submit_button_in_customer_page() throws InterruptedException {
		  base.implicitwait();
			
	        Thread.sleep(3000);
		    WebElement CustomerTitle=BaseClass.driver.findElement(By.xpath("//h3[@class='card-title']"));
		    base.implicitwait();
		    assertEquals("Customer Master",CustomerTitle.getText());
		    assertEquals("Submit",customer.getSubmit().getText());
	  }
	  
//scenario outline:valid data1              valid data               valid data          valid data           valid data
	  
	  @Then("Entering valid data for set of {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} in the input box for CustomerForm")
	  public void entering_valid_data_for_set_of_in_the_input_box_for_customer_form(String customer_code, String customer_name, String legacy_customer_code, String customer_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.customerCode=customer_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(customer_code);
		    base.implicitwait();
	        // customer info
	       base.waitAndSendKeys(customer.getCustomerCode(), customer_code);
	       base.waitAndSendKeys(customer.getCustomerName(), customer_name);
	       base.waitAndSendKeys(customer.getLegacyCustomerCode(), legacy_customer_code);
	       base.selectClass(customer.getCustomerType());
	       base.selectByVisibleText("D25");
	       base.selectClass(customer.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.waitAndSendKeys(customer.getCustomerShortName(), customer_short_name);
	       base.waitAndSendKeys(customer.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(customer.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(customer.getAddress1Flat(), flat);
		   base.waitAndSendKeys(customer.getAddress2Building(), building_name);
		   base.waitAndSendKeys(customer.getAddress3Road(), road);
		   base.waitAndSendKeys(customer.getAddress4Area(), area);

		   base.waitAndClick(customer.getCountry());
		   base.waitAndClick(customer.getCountryValueClick());
		   base.waitAndClick(customer.getState());
		   base.waitAndClick(customer.getStateValueClick());
		   base.waitAndClick(customer.getCity());
		   base.waitAndClick(customer.getCityValueClick());
	       
		   base.waitAndSendKeys(customer.getPincode(), pincode);
		   base.waitAndSendKeys(customer.getTimezone(), timezone);
		   base.waitAndSendKeys(customer.getRegion(), region);
		   base.javaScriptClick(customer.getOutsideProcessYes());
		   // Contact details
		   base.waitAndSendKeys(customer.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(customer.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(customer.getEmail(), email);
		   base.waitAndSendKeys(customer.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(customer.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(customer.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(customer.getPAN(), pan);
		   base.waitAndSendKeys(customer.getGST(), gst);
		   base.waitAndSendKeys(customer.getTIN(), tin);
		   
	  }

	  @Then("Click the submit button in Customer form")
	  public void click_the_submit_button_in_customer_form() {
		  base.waitAndClick(customer.getSubmit());
	  }

	  @Then("A Popup with saved successfully should be displayed in Customer Master Page")
	  public void a_popup_with_saved_successfully_should_be_displayed_in_customer_master_page() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  assertEquals("Saved Successfully",validdata.getText());
	  }

	  @Then("The entered data should be available in Customer table")
	  public void the_entered_data_should_be_available_in_customer_table() {
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
		    DBUtilities.findUniqueRowInTable("customer","customer_code",this.customerCode);
		    assertEquals(this.customerCode, uniqueRowInTable.get("customer_code"));
		     assertEquals(this.pan, uniqueRowInTable.get("pan_no"));
			 assertEquals(this.gst, uniqueRowInTable.get("gst"));
			 assertEquals(this.tin, uniqueRowInTable.get("tin_no"));
	  }
	  
// scenario outline duplicate data
	  
	  @Then("Set data in Customer table")
	  public void set_data_in_customer_table() {
          base.databaseWaitTime();
		  
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
	        Date date = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	        String currentDateTime = format.format(date);
	        values.put("customer_code","CSTC24");
	        values.put("customer_name","Ram");
	        values.put("customer_type","D25");
	        values.put("indentor_code","INDC25");
	        //in db it shows ind nly
	        values.put("legacy_ind_code","LCC4");
	        values.put("type","Ship To");
	        values.put("date_established","2015-03-02");
	        values.put("short_name","CST_SHT_NM");
	        values.put("address_1","D45");
	        values.put("address_2","Seshalaya");
	        values.put("address_3","CoimbatoreRoad");
	        values.put("area","Pallipalayam");
	        values.put("city","Tirunelveli");
	        values.put("state","Tamil Nadu");
	        values.put("country","India");
	        values.put("pincode","638047");
	        values.put("telephone","2366897");
	        values.put("telephone_alt","546489");
	        values.put("time_zone","GMT+5.30");
	        
	        values.put("email","sheshalaya@gmail.com");
	        values.put("email_alt","alternate@gmail.com");
	        values.put("contact","7837387389");
	        values.put("contact_alt","8747847848");
	        
	        values.put("pan_no","ATFGH7891K");
	        values.put("gst","33ATFgh7891LKAZ");
	        values.put("tin_no","321234568TN");
	        values.put("region","southAsia");
	        values.put("outside_process","0");
	        
	        values.put("active","0");
	        values.put("deleted","0");
	        values.put("created_by","Integration Test");
	        values.put("updated_by","Integration Test");
	        values.put("created_on",(currentDateTime));
	        values.put("updated_on",(currentDateTime));
	        
	        DBUtilities.insertIntoTable("customer",values);
	  }

	  @Then("Entering duplicate data for set of {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} in the input box for CustomerForm")
	  public void entering_duplicate_data_for_set_of_in_the_input_box_for_customer_form(String customer_code, String customer_name, String legacy_customer_code, String customer_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.customerCode=customer_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(customer_code);
		    base.implicitwait();
	        // customer info
	       base.waitAndSendKeys(customer.getCustomerCode(), customer_code);
	       base.waitAndSendKeys(customer.getCustomerName(), customer_name);
	       base.waitAndSendKeys(customer.getLegacyCustomerCode(), legacy_customer_code);
	       base.selectClass(customer.getCustomerType());
	       base.selectByVisibleText("D25");
	       base.selectClass(customer.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.waitAndSendKeys(customer.getCustomerShortName(), customer_short_name);
	       base.waitAndSendKeys(customer.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(customer.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(customer.getAddress1Flat(), flat);
		   base.waitAndSendKeys(customer.getAddress2Building(), building_name);
		   base.waitAndSendKeys(customer.getAddress3Road(), road);
		   base.waitAndSendKeys(customer.getAddress4Area(), area);

		   base.waitAndClick(customer.getCountry());
		   base.waitAndClick(customer.getCountryValueClick());
		   base.waitAndClick(customer.getState());
		   base.waitAndClick(customer.getStateValueClick());
		   base.waitAndClick(customer.getCity());
		   base.waitAndClick(customer.getCityValueClick());
	       
		   base.waitAndSendKeys(customer.getPincode(), pincode);
		   base.waitAndSendKeys(customer.getTimezone(), timezone);
		   base.waitAndSendKeys(customer.getRegion(), region);
		   base.javaScriptClick(customer.getOutsideProcessYes());
		   // Contact details
		   base.waitAndSendKeys(customer.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(customer.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(customer.getEmail(), email);
		   base.waitAndSendKeys(customer.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(customer.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(customer.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(customer.getPAN(), pan);
		   base.waitAndSendKeys(customer.getGST(), gst);
		   base.waitAndSendKeys(customer.getTIN(), tin);
	  }

	  @Then("The enterd data should not be available in Customer Database for duplicate data")
	  public void the_enterd_data_should_not_be_available_in_customer_database_for_duplicate_data() {
		  base.databaseWaitTime();
		    assertEquals(1,DBUtilities.getNumberOfRows("customer","customer_code",this.customerCode));
		    assertEquals(1,DBUtilities.getNumberOfRows("customer","pan_no",this.pan));
		    assertEquals(1,DBUtilities.getNumberOfRows("customer","gst",this.gst));
	  }

	  @Then("A popup with message already exists data should be displayed in Customer Page")
	  public void a_popup_with_message_already_exists_data_should_be_displayed_in_customer_page() throws InterruptedException {
		  Thread.sleep(3000);
			WebElement errormsg = BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
			String errormessage = errormsg.getText();
			assertEquals("Already exixts",errormessage);
	  }
	  
//scenario outline: Boundary value analysis
	  
	  @Then("Entering data for {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} in which Gst,pan,Tin with less and more characters than normal in Customer Form")
	  public void enteringdataforinwhichgstpantinwithlessandmorecharactersthannormalincustomerform(String customer_code, String customer_name, String legacy_customer_code, String customer_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  
		  
		    this.customerCode=customer_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(customer_code);
		    base.implicitwait();
	        // customer info
	       base.waitAndSendKeys(customer.getCustomerCode(), customer_code);
	       base.waitAndSendKeys(customer.getCustomerName(), customer_name);
	       base.waitAndSendKeys(customer.getLegacyCustomerCode(), legacy_customer_code);
	       base.selectClass(customer.getCustomerType());
	       base.selectByVisibleText("D25");
	       base.selectClass(customer.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.waitAndSendKeys(customer.getCustomerShortName(), customer_short_name);
	       base.waitAndSendKeys(customer.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(customer.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(customer.getAddress1Flat(), flat);
		   base.waitAndSendKeys(customer.getAddress2Building(), building_name);
		   base.waitAndSendKeys(customer.getAddress3Road(), road);
		   base.waitAndSendKeys(customer.getAddress4Area(), area);

		   base.waitAndClick(customer.getCountry());
		   base.waitAndClick(customer.getCountryValueClick());
		   base.waitAndClick(customer.getState());
		   base.waitAndClick(customer.getStateValueClick());
		   base.waitAndClick(customer.getCity());
		   base.waitAndClick(customer.getCityValueClick());
	       
		   base.waitAndSendKeys(customer.getPincode(), pincode);
		   base.waitAndSendKeys(customer.getTimezone(), timezone);
		   base.waitAndSendKeys(customer.getRegion(), region);
		   base.javaScriptClick(customer.getOutsideProcessYes());
		   // Contact details
		   base.waitAndSendKeys(customer.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(customer.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(customer.getEmail(), email);
		   base.waitAndSendKeys(customer.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(customer.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(customer.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(customer.getPAN(), pan);
		   base.waitAndSendKeys(customer.getGST(), gst);
		   base.waitAndSendKeys(customer.getTIN(), tin);
	  }

	  @Then("The enterd data should not be available in Customer Database for BVA test")
	  public void theenterddatashouldnotbeavailableincustomerdatabaseforbvatest() {
		  base.databaseWaitTime();
		    assertEquals(0,DBUtilities.getNumberOfRows("customer","customer_code",this.customerCode));
		    assertEquals(0,DBUtilities.getNumberOfRows("customer","pan_no",this.pan));
		    assertEquals(0,DBUtilities.getNumberOfRows("customer","gst",this.gst));
	  }
	  
//scenario outline: invalid data
	  
	  @Then("Entering invalid data for {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} with different characters and alphanumeric in Customer Form")
	  public void entering_invalid_data_for_with_different_characters_and_alphanumeric_in_customer_form(String customer_code, String customer_name, String legacy_customer_code, String customer_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(2000);
		  
		    this.customerCode=customer_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(customer_code);
		    base.implicitwait();
	        // customer info
	       base.waitAndSendKeys(customer.getCustomerCode(), customer_code);
	       base.waitAndSendKeys(customer.getCustomerName(), customer_name);
	       base.waitAndSendKeys(customer.getLegacyCustomerCode(), legacy_customer_code);
	       base.selectClass(customer.getCustomerType());
	       base.selectByVisibleText("D25");
	       base.selectClass(customer.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.waitAndSendKeys(customer.getCustomerShortName(), customer_short_name);
	       base.waitAndSendKeys(customer.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(customer.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(customer.getAddress1Flat(), flat);
		   base.waitAndSendKeys(customer.getAddress2Building(), building_name);
		   base.waitAndSendKeys(customer.getAddress3Road(), road);
		   base.waitAndSendKeys(customer.getAddress4Area(), area);

		   base.waitAndClick(customer.getCountry());
		   base.waitAndClick(customer.getCountryValueClick());
		   base.waitAndClick(customer.getState());
		   base.waitAndClick(customer.getStateValueClick());
		   base.waitAndClick(customer.getCity());
		   base.waitAndClick(customer.getCityValueClick());
	       
		   base.waitAndSendKeys(customer.getPincode(), pincode);
		   base.waitAndSendKeys(customer.getTimezone(), timezone);
		   base.waitAndSendKeys(customer.getRegion(), region);
		   base.javaScriptClick(customer.getOutsideProcessYes());
		   // Contact details
		   base.waitAndSendKeys(customer.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(customer.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(customer.getEmail(), email);
		   base.waitAndSendKeys(customer.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(customer.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(customer.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(customer.getPAN(), pan);
		   base.waitAndSendKeys(customer.getGST(), gst);
		   base.waitAndSendKeys(customer.getTIN(), tin);
	  }

	  @Then("The enterd data should not be available in Customer Database for invalid character data")
	  public void the_enterd_data_should_not_be_available_in_customer_database_for_invalid_character_data() {
		  base.databaseWaitTime();
		    assertEquals(0,DBUtilities.getNumberOfRows("customer","customer_code",this.customerCode));
		    assertEquals(0,DBUtilities.getNumberOfRows("customer","pan_no",this.pan));
		    assertEquals(0,DBUtilities.getNumberOfRows("customer","gst",this.gst));
	  }
	  
//scenario outline: blank data
	  
	  @Then("Entering blank data in {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}  in the input box with blank data in Customer Form")
	  public void entering_blank_data_in_in_the_input_box_with_blank_data_in_customer_form(String customer_code, String customer_name, String legacy_customer_code, String customer_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) {
		  
		  this.customerCode=customer_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(customer_code);
		    base.implicitwait();
	        // customer info
	       base.waitAndSendKeys(customer.getCustomerCode(), customer_code);
	       base.waitAndSendKeys(customer.getCustomerName(), customer_name);
	       base.waitAndSendKeys(customer.getLegacyCustomerCode(), legacy_customer_code);
	       base.selectClass(customer.getCustomerType());
	       base.selectByVisibleText("D25");
	       base.selectClass(customer.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.waitAndSendKeys(customer.getCustomerShortName(), customer_short_name);
	       base.waitAndSendKeys(customer.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(customer.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(customer.getAddress1Flat(), flat);
		   base.waitAndSendKeys(customer.getAddress2Building(), building_name);
		   base.waitAndSendKeys(customer.getAddress3Road(), road);
		   base.waitAndSendKeys(customer.getAddress4Area(), area);

		   base.waitAndClick(customer.getCountry());
		   base.waitAndClick(customer.getCountryValueClick());
		   base.waitAndClick(customer.getState());
		   base.waitAndClick(customer.getStateValueClick());
		   base.waitAndClick(customer.getCity());
		   base.waitAndClick(customer.getCityValueClick());
	       
		   base.waitAndSendKeys(customer.getPincode(), pincode);
		   base.waitAndSendKeys(customer.getTimezone(), timezone);
		   base.waitAndSendKeys(customer.getRegion(), region);
		   base.javaScriptClick(customer.getOutsideProcessYes());
		   // Contact details
		   base.waitAndSendKeys(customer.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(customer.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(customer.getEmail(), email);
		   base.waitAndSendKeys(customer.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(customer.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(customer.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(customer.getPAN(), pan);
		   base.waitAndSendKeys(customer.getGST(), gst);
		   base.waitAndSendKeys(customer.getTIN(), tin);
	  }

	  @Then("All input box should show red mark in Customer Master form")
	  public void all_input_box_should_show_red_mark_in_customer_master_form() throws InterruptedException {
		  Thread.sleep(3000);
			base.implicitwait();
			
			//mandatory fields error
			String regionError = BaseClass.driver.findElement(By.xpath("//div[contains(text(),'Please enter your Region')]")).getText();
			assertEquals("Please enter your Region",regionError);
			
			String primaryTelephoneError = BaseClass.driver.findElement(By.xpath("//div[contains(text(),'Please enter valid primary telephone')]")).getText();
			assertEquals("Please enter valid primary telephone",primaryTelephoneError);
			
			String primaryContactError = BaseClass.driver.findElement(By.xpath("//div[contains(text(),'Please enter valid primary contact')]")).getText();
			assertEquals("Please enter valid primary contact",primaryContactError);
			
	  }

	  @Then("The enterd data should not be available in Customer Database for blank data")
	  public void the_enterd_data_should_not_be_available_in_customer_database_for_blank_data() {
		  base.databaseWaitTime();
		    assertNotEquals(0,DBUtilities.getNumberOfRows("customer","date_established","2015-03-02"));
		    
	  }
	  
//scenario outline: edit functionality
	  
	  @Then("Clicking the close button in Customer Form")
	  public void clicking_the_close_button_in_customer_form() {
		  base.implicitwait();
	      base.waitAndClick(customer.getCancel());
	  }
//edit
	  @Then("Checking the functionality of edit button in {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} by clicking in Customer Page")
	  public void checking_the_functionality_of_edit_button_in_by_clicking_in_customer_page(String customer_code, String customer_name, String legacy_customer_code, String customer_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(4000);
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(4000);
		  //base.refreshApp(BaseClass.baseURL+url);
		  base.waitAndSendKeys(customer.getSearchButton(), "CSTC24");
		  base.waitAndClick(customer.getEditIcon());
		  
		  this.customerCode=customer_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(customer_code);
		    base.implicitwait();
	        // Indentor info
	       base.clearAndSendKeys(customer.getCustomerCode(), customer_code);
	       base.clearAndSendKeys(customer.getCustomerName(), customer_name);
	       base.clearAndSendKeys(customer.getLegacyCustomerCode(), legacy_customer_code);
	       base.selectClass(customer.getCustomerType());
	       base.selectByVisibleText("D25");
	       base.selectClass(customer.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.clearAndSendKeys(customer.getCustomerShortName(), customer_short_name);
	       base.clearAndSendKeys(customer.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(customer.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.clearAndSendKeys(customer.getAddress1Flat(), flat);
		   base.clearAndSendKeys(customer.getAddress2Building(), building_name);
		   base.clearAndSendKeys(customer.getAddress3Road(), road);
		   base.clearAndSendKeys(customer.getAddress4Area(), area);

		   base.waitAndClick(customer.getCountry());
		   base.waitAndClick(customer.getCountryValueClick());
		   base.waitAndClick(customer.getState());
		   base.waitAndClick(customer.getStateValueClick());
		   base.waitAndClick(customer.getCity());
		   base.waitAndClick(customer.getCityValueClick());
	       
		   base.clearAndSendKeys(customer.getPincode(), pincode);
		   base.clearAndSendKeys(customer.getTimezone(), timezone);
		   base.clearAndSendKeys(customer.getRegion(), region);
		   base.javaScriptClick(customer.getOutsideprocessNo());
		   // Contact details
		   base.clearAndSendKeys(customer.getPrimaryTelephone(), primary_telephone);
		   base.clearAndSendKeys(customer.getSecondaryTelephone(), secondary_telephone);
		   base.clearAndSendKeys(customer.getEmail(), email);
		   base.clearAndSendKeys(customer.getEmailAlternate(), email_alternate);
		   base.clearAndSendKeys(customer.getPrimaryContact(), primary_contact);
		   base.clearAndSendKeys(customer.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.clearAndSendKeys(customer.getPAN(), pan);
		   base.clearAndSendKeys(customer.getGST(), gst);
		   base.clearAndSendKeys(customer.getTIN(), tin);
		   //base.waitAndClick(indentor.getSubmit());
		   //base.waitAndClick(indentor.getSubmit());
		   base.javaScriptClick(customer.getSubmit());
		  
		    
	  }

	  @Then("The edited data should be available in database of Customer Master")
	  public void the_edited_data_should_be_available_in_database_of_customer_master() {
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
		    DBUtilities.findUniqueRowInTable("customer","customer_code","edt24");
			base.databaseWaitTime();
			base.databaseWaitTime();
		    assertEquals("edt24", uniqueRowInTable.get("customer_code"));
		     assertEquals("e-ATFGH7891K", uniqueRowInTable.get("pan_no"));
			 assertEquals("e-33ATFgh7891LKAZ", uniqueRowInTable.get("gst"));
			 assertEquals("e-321234568TN", uniqueRowInTable.get("tin_no"));
	  }

//scenario:delete functionality
	  
	  @Then("Checking the functionality of delete icon in Customer Master Page")
	  public void checking_the_functionality_of_delete_icon_in_customer_master_page() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(2000);
		  base.waitAndSendKeys(customer.getSearchButton(), "CSTC24");
		  base.waitAndClick(customer.getDeleteIcon());
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Customer Page")
	  public void clicking_delete_button_and_a_popup_deleted_sucessfully_should_be_displayed_in_customer_page() throws InterruptedException {
		  Thread.sleep(2000);
			//delete icon popup 
			  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[1]"));
		      String dltmessage = deletemsg.getText();
	          assertEquals("Are you Sure ?",dltmessage);
		  
		  //delete msg popup        
        base.waitAndClick(customer.getDeletebutton());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
        assertEquals("Deleted Successfully",deletesuccess);
	  }

	  @Then("Verifying that the entered data was deleted in Customer database")
	  public void verifying_that_the_entered_data_was_deleted_in_customer_database() throws InterruptedException {
		  Thread.sleep(1000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("customer","customer_code","CSTC24");
		   Thread.sleep(1000);
	       assertEquals("1",uniqueRowInTable.get("deleted"));
	       DBUtilities.findUniqueRowInTable("customer","customer_code","CSTC24");
	  }
	  
//scenario: search functionality
	  
	  @Then("Searching the Customer code by sending keys")
	  public void searching_the_customer_code_by_sending_keys() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.waitAndClick(customer.getSearchButton());
			base.waitAndSendKeys(customer.getSearchButton(), "CSTC24");
	  }

	  @Then("The text in the search box should be equal to values in the Customer table")
	  public void the_text_in_the_search_box_should_be_equal_to_values_in_the_customer_table() throws InterruptedException {
		  Thread.sleep(1000);
			assertEquals(customer.getSearchCheck().getText(),"CSTC24");
			base.databaseWaitTime();
	  }

	  @Then("Clicking Export button in Customer Master page")
	  public void clicking_export_button_in_customer_master_page() {
		  base.waitAndClick(customer.getExport());
	  }
	
}
