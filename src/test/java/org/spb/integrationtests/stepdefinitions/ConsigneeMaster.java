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
import org.spb.integrationtests.pageobjectmodel.ConsigneePOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ConsigneeMaster {

	
	private static String url = "consignee";
	  BaseClass base = new BaseClass();
	  ConsigneePOM consignee= new ConsigneePOM();
	  private String consigneeCode;
	  
	  private String pan;
	  private String gst;
	  private String tin;
	  
	  public ConsigneeMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens
	  
	  @When("Delete data initially from database in Consignee")
	  public void deletedatainitiallyfromdatabaseinconsignee() {
		  
		 DBUtilities.deleteFromTable("consignee", "date_established", "2015-03-02");
		 //DBUtilities.deleteFromTable("consignee", "consignee_code", "edt24");
		  
		  
			   
		 
		 /*DBUtilities.deleteFromTable("consignee","customer_code","CSTC24");
		 DBUtilities.deleteFromTable("consignee","indentor_code","INDC25");*/
		 
		 
		 
		 DBUtilities.deleteFromTable("consignee","consignee_code","CNSC24");
		 DBUtilities.deleteFromTable("customer","customer_code","CSTC24");
		 DBUtilities.deleteFromTable("customer_type", "customer_type_code", "D25");
		 DBUtilities.deleteFromTable("indentor", "indentor_code", "INDC25");
		 
		 DBUtilities.deleteFromTable("indentor_type", "indentor_type_code", "IND021");
	  }

	  @Given("A web browser is on the Consignee Master page")
	  public void awebbrowserisontheconsigneemasterpage() throws InterruptedException {
		  BaseClass.driver.get(BaseClass.baseURL + url);
			Thread.sleep(2000);
	  }

	  @When("Add new Consignee button is clicked")
	  public void addnewconsigneebuttonisclicked() {
		  base.waitAndClick(consignee.getNewConsignee());
	  }

	  @Then("Popup with title as Consignee Master should be displayed with Submit button in Consignee Page")
	  public void popupwithtitleasconsigneemastershouldbedisplayedwithsubmitbuttoninconsigneepage() throws InterruptedException {
		  base.implicitwait();
			
	        Thread.sleep(3000);
		    WebElement ConsigneeTitle=BaseClass.driver.findElement(By.xpath("//h3[@class='card-title']"));
		    base.implicitwait();
		    assertEquals("Consignee Master",ConsigneeTitle.getText());
		    assertEquals("Submit",consignee.getSubmit().getText());
	  }
	  
//scenario outline:valid data1              valid data               valid data          valid data           valid data
	  
	  @Then("Entering valid data for set of {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} in the input box for ConsigneeForm")
	  public void enteringvaliddataforsetofintheinputboxforconsigneeform(String consignee_code, String consignee_name, String legacy_consignee_code, String consignee_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.consigneeCode=consignee_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(consignee_code);
		    base.implicitwait();
	        // customer info
	       base.waitAndSendKeys(consignee.getConsigneeCode(), consignee_code);
	       base.waitAndSendKeys(consignee.getConsigneeName(), consignee_name);
	       base.waitAndSendKeys(consignee.getLegacyConsigneeCode(), legacy_consignee_code);
	       base.selectClass(consignee.getCustomerCode());
	       base.selectByVisibleText("CSTC24");
	       base.selectClass(consignee.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.waitAndSendKeys(consignee.getConsigneeShortName(), consignee_short_name);
	       base.waitAndSendKeys(consignee.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(consignee.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(consignee.getAddress1Flat(), flat);
		   base.waitAndSendKeys(consignee.getAddress2Building(), building_name);
		   base.waitAndSendKeys(consignee.getAddress3Road(), road);
		   base.waitAndSendKeys(consignee.getAddress4Area(), area);

		   Thread.sleep(3000);
		   base.waitAndClick(consignee.getCountry());
		   base.waitAndClick(consignee.getCountryValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getState());
		   base.waitAndClick(consignee.getStateValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getCity());
		   base.waitAndClick(consignee.getCityValueClick());
	       
		   base.waitAndSendKeys(consignee.getPincode(), pincode);
		   base.waitAndSendKeys(consignee.getTimezone(), timezone);
		   base.waitAndSendKeys(consignee.getRegion(), region);
		   base.javaScriptClick(consignee.getOutsideProcessYes());
		   // Contact details
		   base.waitAndSendKeys(consignee.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(consignee.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(consignee.getEmail(), email);
		   base.waitAndSendKeys(consignee.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(consignee.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(consignee.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(consignee.getPAN(), pan);
		   base.waitAndSendKeys(consignee.getGST(), gst);
		   base.waitAndSendKeys(consignee.getTIN(), tin);
	  }

	  @Then("Click the submit button in Consignee form")
	  public void clickthesubmitbuttoninconsigneeform() {
		  base.waitAndClick(consignee.getSubmit());
	  }

	  @Then("A Popup with saved successfully should be displayed in Consignee Master Page")
	  public void apopupwithsavedsuccessfullyshouldbedisplayedinconsigneemasterpage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  assertEquals("Saved Successfully",validdata.getText());
	  }

	  @Then("The entered data should be available in Consignee table")
	  public void theentereddatashouldbeavailableinconsigneetable() {
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
		    DBUtilities.findUniqueRowInTable("consignee","consignee_code",this.consigneeCode);
		    assertEquals(this.consigneeCode, uniqueRowInTable.get("consignee_code"));
		     assertEquals(this.pan, uniqueRowInTable.get("pan_no"));
			 assertEquals(this.gst, uniqueRowInTable.get("gst"));
			 assertEquals(this.tin, uniqueRowInTable.get("tin_no"));
	  }
	  
// scenario outline duplicate data
	  
	  @Then("Set data in Consignee table")
	  public void setdatainconsigneetable() {
          base.databaseWaitTime();
		  
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
	        Date date = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	        String currentDateTime = format.format(date);
	        values.put("consignee_code","CNSC24");
	        values.put("consignee_name","Ram");
	        values.put("customer_code","CSTC24");
	        values.put("indentor_code","INDC25");
	        
	        
	        values.put("legacy_consignee_code","LCNSC4");
	        values.put("type","Ship To");
	        values.put("date_established","2015-03-02");
	        values.put("short_name","CNS_SHT_NM");
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
	        
	        DBUtilities.insertIntoTable("consignee",values);
	  }

	  @Then("Entering duplicate data for set of {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} in the input box for ConsigneeForm")
	  public void enteringduplicatedataforsetofintheinputboxforconsigneeform(String consignee_code, String consignee_name, String legacy_consignee_code, String consignee_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.consigneeCode=consignee_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(consignee_code);
		    base.implicitwait();
	        // customer info
	       base.waitAndSendKeys(consignee.getConsigneeCode(), consignee_code);
	       base.waitAndSendKeys(consignee.getConsigneeName(), consignee_name);
	       base.waitAndSendKeys(consignee.getLegacyConsigneeCode(), legacy_consignee_code);
	       base.selectClass(consignee.getCustomerCode());
	       base.selectByVisibleText("CSTC24");
	       base.selectClass(consignee.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.waitAndSendKeys(consignee.getConsigneeShortName(), consignee_short_name);
	       base.waitAndSendKeys(consignee.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(consignee.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(consignee.getAddress1Flat(), flat);
		   base.waitAndSendKeys(consignee.getAddress2Building(), building_name);
		   base.waitAndSendKeys(consignee.getAddress3Road(), road);
		   base.waitAndSendKeys(consignee.getAddress4Area(), area);

		   Thread.sleep(3000);
		   base.waitAndClick(consignee.getCountry());
		   base.waitAndClick(consignee.getCountryValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getState());
		   base.waitAndClick(consignee.getStateValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getCity());
		   base.waitAndClick(consignee.getCityValueClick());
	       
		   base.waitAndSendKeys(consignee.getPincode(), pincode);
		   base.waitAndSendKeys(consignee.getTimezone(), timezone);
		   base.waitAndSendKeys(consignee.getRegion(), region);
		   base.javaScriptClick(consignee.getOutsideProcessYes());
		   // Contact details
		   base.waitAndSendKeys(consignee.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(consignee.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(consignee.getEmail(), email);
		   base.waitAndSendKeys(consignee.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(consignee.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(consignee.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(consignee.getPAN(), pan);
		   base.waitAndSendKeys(consignee.getGST(), gst);
		   base.waitAndSendKeys(consignee.getTIN(), tin);
	  }

	  @Then("The enterd data should not be available in Consignee Database for duplicate data")
	  public void theenterddatashouldnotbeavailableinconsigneedatabaseforduplicatedata() {
		  base.databaseWaitTime();
		    assertEquals(1,DBUtilities.getNumberOfRows("consignee","consignee_code",this.consigneeCode));
		    assertEquals(1,DBUtilities.getNumberOfRows("consignee","pan_no",this.pan));
		    assertEquals(1,DBUtilities.getNumberOfRows("consignee","gst",this.gst));
	  }

	  @Then("A popup with message already exists data should be displayed in Consignee Page")
	  public void apopupwithmessagealreadyexistsdatashouldbedisplayedinconsigneepage() throws InterruptedException {
		  Thread.sleep(3000);
			WebElement errormsg = BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
			String errormessage = errormsg.getText();
			assertEquals("Already exixts",errormessage);
	  }
	  
//scenario outline: Boundary value analysis
	  
	  @Then("Entering data for {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} in which Gst,pan,Tin with less and more characters than normal in Consignee Form")
	  public void enteringdataforinwhichgstpantinwithlessandmorecharactersthannormalinconsigneeform(String consignee_code, String consignee_name, String legacy_consignee_code, String consignee_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.consigneeCode=consignee_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(consignee_code);
		    base.implicitwait();
	        // customer info
	       base.waitAndSendKeys(consignee.getConsigneeCode(), consignee_code);
	       base.waitAndSendKeys(consignee.getConsigneeName(), consignee_name);
	       base.waitAndSendKeys(consignee.getLegacyConsigneeCode(), legacy_consignee_code);
	       base.selectClass(consignee.getCustomerCode());
	       base.selectByVisibleText("CSTC24");
	       base.selectClass(consignee.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.waitAndSendKeys(consignee.getConsigneeShortName(), consignee_short_name);
	       base.waitAndSendKeys(consignee.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(consignee.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(consignee.getAddress1Flat(), flat);
		   base.waitAndSendKeys(consignee.getAddress2Building(), building_name);
		   base.waitAndSendKeys(consignee.getAddress3Road(), road);
		   base.waitAndSendKeys(consignee.getAddress4Area(), area);

		   Thread.sleep(3000);
		   base.waitAndClick(consignee.getCountry());
		   base.waitAndClick(consignee.getCountryValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getState());
		   base.waitAndClick(consignee.getStateValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getCity());
		   base.waitAndClick(consignee.getCityValueClick());
	       
		   base.waitAndSendKeys(consignee.getPincode(), pincode);
		   base.waitAndSendKeys(consignee.getTimezone(), timezone);
		   base.waitAndSendKeys(consignee.getRegion(), region);
		   base.javaScriptClick(consignee.getOutsideProcessYes());
		   // Contact details
		   base.waitAndSendKeys(consignee.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(consignee.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(consignee.getEmail(), email);
		   base.waitAndSendKeys(consignee.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(consignee.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(consignee.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(consignee.getPAN(), pan);
		   base.waitAndSendKeys(consignee.getGST(), gst);
		   base.waitAndSendKeys(consignee.getTIN(), tin);
	  }

	  @Then("The enterd data should not be available in Consignee Database for BVA test")
	  public void theenterddatashouldnotbeavailableinconsigneedatabaseforbvatest() {
		  base.databaseWaitTime();
		    assertEquals(0,DBUtilities.getNumberOfRows("consignee","consignee_code",this.consigneeCode));
		    assertEquals(0,DBUtilities.getNumberOfRows("consignee","pan_no",this.pan));
		    assertEquals(0,DBUtilities.getNumberOfRows("consignee","gst",this.gst));
	  }
	  
//scenario outline: invalid data
	  
	  @Then("Entering invalid data for {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} with different characters and alphanumeric in Consignee Form")
	  public void enteringinvaliddataforwithdifferentcharactersandalphanumericinconsigneeform(String consignee_code, String consignee_name, String legacy_consignee_code, String consignee_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.consigneeCode=consignee_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(consignee_code);
		    base.implicitwait();
	        // customer info
	       base.waitAndSendKeys(consignee.getConsigneeCode(), consignee_code);
	       base.waitAndSendKeys(consignee.getConsigneeName(), consignee_name);
	       base.waitAndSendKeys(consignee.getLegacyConsigneeCode(), legacy_consignee_code);
	       base.selectClass(consignee.getCustomerCode());
	       base.selectByVisibleText("CSTC24");
	       base.selectClass(consignee.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.waitAndSendKeys(consignee.getConsigneeShortName(), consignee_short_name);
	       base.waitAndSendKeys(consignee.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(consignee.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(consignee.getAddress1Flat(), flat);
		   base.waitAndSendKeys(consignee.getAddress2Building(), building_name);
		   base.waitAndSendKeys(consignee.getAddress3Road(), road);
		   base.waitAndSendKeys(consignee.getAddress4Area(), area);

		   Thread.sleep(3000);
		   base.waitAndClick(consignee.getCountry());
		   base.waitAndClick(consignee.getCountryValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getState());
		   base.waitAndClick(consignee.getStateValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getCity());
		   base.waitAndClick(consignee.getCityValueClick());
	       
		   base.waitAndSendKeys(consignee.getPincode(), pincode);
		   base.waitAndSendKeys(consignee.getTimezone(), timezone);
		   base.waitAndSendKeys(consignee.getRegion(), region);
		   base.javaScriptClick(consignee.getOutsideProcessYes());
		   // Contact details
		   base.waitAndSendKeys(consignee.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(consignee.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(consignee.getEmail(), email);
		   base.waitAndSendKeys(consignee.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(consignee.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(consignee.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(consignee.getPAN(), pan);
		   base.waitAndSendKeys(consignee.getGST(), gst);
		   base.waitAndSendKeys(consignee.getTIN(), tin);
	  }

	  @Then("The enterd data should not be available in Consignee Database for invalid character data")
	  public void theenterddatashouldnotbeavailableinconsigneedatabaseforinvalidcharacterdata() {
		  base.databaseWaitTime();
		    assertEquals(0,DBUtilities.getNumberOfRows("consignee","consignee_code",this.consigneeCode));
		    assertEquals(0,DBUtilities.getNumberOfRows("consignee","pan_no",this.pan));
		    assertEquals(0,DBUtilities.getNumberOfRows("consignee","gst",this.gst));
	  }
	  
//scenario outline: blank data
	  
	  @Then("Entering blank data in {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}  in the input box with blank data in Consignee Form")
	  public void enteringblankdatainintheinputboxwithblankdatainconsigneeform(String consignee_code, String consignee_name, String legacy_consignee_code, String consignee_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.consigneeCode=consignee_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(consignee_code);
		    base.implicitwait();
	        // customer info
	       base.waitAndSendKeys(consignee.getConsigneeCode(), consignee_code);
	       base.waitAndSendKeys(consignee.getConsigneeName(), consignee_name);
	       base.waitAndSendKeys(consignee.getLegacyConsigneeCode(), legacy_consignee_code);
	       base.selectClass(consignee.getCustomerCode());
	       base.selectByVisibleText("CSTC24");
	       base.selectClass(consignee.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.waitAndSendKeys(consignee.getConsigneeShortName(), consignee_short_name);
	       base.waitAndSendKeys(consignee.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(consignee.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(consignee.getAddress1Flat(), flat);
		   base.waitAndSendKeys(consignee.getAddress2Building(), building_name);
		   base.waitAndSendKeys(consignee.getAddress3Road(), road);
		   base.waitAndSendKeys(consignee.getAddress4Area(), area);

		   Thread.sleep(3000);
		   base.waitAndClick(consignee.getCountry());
		   base.waitAndClick(consignee.getCountryValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getState());
		   base.waitAndClick(consignee.getStateValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getCity());
		   base.waitAndClick(consignee.getCityValueClick());
	       
		   base.waitAndSendKeys(consignee.getPincode(), pincode);
		   base.waitAndSendKeys(consignee.getTimezone(), timezone);
		   base.waitAndSendKeys(consignee.getRegion(), region);
		   base.javaScriptClick(consignee.getOutsideProcessYes());
		   // Contact details
		   base.waitAndSendKeys(consignee.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(consignee.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(consignee.getEmail(), email);
		   base.waitAndSendKeys(consignee.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(consignee.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(consignee.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(consignee.getPAN(), pan);
		   base.waitAndSendKeys(consignee.getGST(), gst);
		   base.waitAndSendKeys(consignee.getTIN(), tin);
	  }

	  @Then("All input box should show red mark in Consignee Master form")
	  public void allinputboxshouldshowredmarkinconsigneemasterform() throws InterruptedException {
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

	  @Then("The enterd data should not be available in Consignee Database for blank data")
	  public void theenterddatashouldnotbeavailableinconsigneedatabaseforblankdata() {
		  base.databaseWaitTime();
		    assertNotEquals(0,DBUtilities.getNumberOfRows("consignee","date_established","2015-03-02"));
	  }
	  
//scenario outline: edit functionality
	  
	  @Then("Clicking the close button in Consignee Form")
	  public void clickingtheclosebuttoninconsigneeform() {
		  base.implicitwait();
	      base.waitAndClick(consignee.getCancel());
	  }

	  @Then("Checking the functionality of edit button in {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} by clicking in Consignee Page")
	  public void checkingthefunctionalityofeditbuttoninbyclickinginconsigneepage(String consignee_code, String consignee_name, String legacy_consignee_code, String consignee_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(4000);
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(4000);
		  //base.refreshApp(BaseClass.baseURL+url);
		  
		  base.waitAndSendKeys(consignee.getSearchButton(), "CNSC24");
		  base.waitAndClick(consignee.getEditIcon());
		  
		  this.consigneeCode=consignee_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(consignee_code);
		    base.implicitwait();
	        // Indentor info
	       base.clearAndSendKeys(consignee.getConsigneeCode(), consignee_code);
	       base.clearAndSendKeys(consignee.getConsigneeName(), consignee_name);
	       base.clearAndSendKeys(consignee.getLegacyConsigneeCode(), legacy_consignee_code);
	       base.selectClass(consignee.getCustomerCode());
	       base.selectByVisibleText("CSTC24");
	       base.selectClass(consignee.getIndentorCode());
	       base.selectByVisibleText("INDC25");
	       base.clearAndSendKeys(consignee.getConsigneeShortName(), consignee_short_name);
	       base.clearAndSendKeys(consignee.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(consignee.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.clearAndSendKeys(consignee.getAddress1Flat(), flat);
		   base.clearAndSendKeys(consignee.getAddress2Building(), building_name);
		   base.clearAndSendKeys(consignee.getAddress3Road(), road);
		   base.clearAndSendKeys(consignee.getAddress4Area(), area);

		   Thread.sleep(3000);
		   base.waitAndClick(consignee.getCountry());
		   base.waitAndClick(consignee.getCountryValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getState());
		   base.waitAndClick(consignee.getStateValueClick());
		   Thread.sleep(1000);
		   base.waitAndClick(consignee.getCity());
		   base.waitAndClick(consignee.getCityValueClick());
	       
		   base.clearAndSendKeys(consignee.getPincode(), pincode);
		   base.clearAndSendKeys(consignee.getTimezone(), timezone);
		   base.clearAndSendKeys(consignee.getRegion(), region);
		   base.javaScriptClick(consignee.getOutsideprocessNo());
		   // Contact details
		   base.clearAndSendKeys(consignee.getPrimaryTelephone(), primary_telephone);
		   base.clearAndSendKeys(consignee.getSecondaryTelephone(), secondary_telephone);
		   base.clearAndSendKeys(consignee.getEmail(), email);
		   base.clearAndSendKeys(consignee.getEmailAlternate(), email_alternate);
		   base.clearAndSendKeys(consignee.getPrimaryContact(), primary_contact);
		   base.clearAndSendKeys(consignee.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.clearAndSendKeys(consignee.getPAN(), pan);
		   base.clearAndSendKeys(consignee.getGST(), gst);
		   base.clearAndSendKeys(consignee.getTIN(), tin);
		   //base.waitAndClick(indentor.getSubmit());
		   //base.waitAndClick(indentor.getSubmit());
		   base.javaScriptClick(consignee.getSubmit());
		  
	  }

	  @Then("The edited data should be available in database of Consignee Master")
	  public void theediteddatashouldbeavailableindatabaseofconsigneemaster() {
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
		    DBUtilities.findUniqueRowInTable("consignee","consignee_code","edt24");
			base.databaseWaitTime();
			base.databaseWaitTime();
		    assertEquals("edt24", uniqueRowInTable.get("consignee_code"));
		     assertEquals("e-ATFGH7891K", uniqueRowInTable.get("pan_no"));
			 assertEquals("e-33ATFgh7891LKAZ", uniqueRowInTable.get("gst"));
			 assertEquals("e-321234568TN", uniqueRowInTable.get("tin_no"));
	  }
	  
//scenario:delete functionality
	  
	  @Then("Checking the functionality of delete icon in Consignee Master Page")
	  public void checkingthefunctionalityofdeleteiconinconsigneemasterpage() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(2000);
		  base.waitAndSendKeys(consignee.getSearchButton(), "CNSC24");
		  
		  base.waitAndClick(consignee.getDeleteIcon());
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Consignee Page")
	  public void clickingdeletebuttonandapopupdeletedsucessfullyshouldbedisplayedinconsigneepage() throws InterruptedException {
		  Thread.sleep(2000);
			//delete icon popup 
			  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[1]"));
		      String dltmessage = deletemsg.getText();
	          assertEquals("Are you Sure ?",dltmessage);
		  
		  //delete msg popup        
      base.waitAndClick(consignee.getDeletebutton());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
      assertEquals("Deleted Successfully",deletesuccess);
	  }

	  @Then("Verifying that the entered data was deleted in Consignee database")
	  public void verifyingthattheentereddatawasdeletedinconsigneedatabase() throws InterruptedException {
		  Thread.sleep(1000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("consignee","consignee_code","edt24");
		   Thread.sleep(1000);
	       assertEquals("1",uniqueRowInTable.get("deleted"));
	       //DBUtilities.findUniqueRowInTable("customer","customer_code","edt24");
	  }
	  
//scenario: search functionality
	  
	  @Then("Searching the Consignee code by sending keys")
	  public void searchingtheconsigneecodebysendingkeys() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.waitAndClick(consignee.getSearchButton());
			base.waitAndSendKeys(consignee.getSearchButton(), "CNSC24");
	  }

	  @Then("The text in the search box should be equal to values in the Consignee table")
	  public void thetextinthesearchboxshouldbeequaltovaluesintheconsigneetable() throws InterruptedException {
		  Thread.sleep(1000);
			assertEquals(consignee.getSearchCheck().getText(),"CNSC24");
			base.databaseWaitTime();
	  }

	  @Then("Clicking Export button in Consignee Master page")
	  public void clickingexportbuttoninconsigneemasterpage() {
		  base.waitAndClick(consignee.getExport());
	  }

	  
	  
}
