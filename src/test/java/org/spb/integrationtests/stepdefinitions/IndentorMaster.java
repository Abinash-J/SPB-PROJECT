package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.IndentorPOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class IndentorMaster {

	
	private static String url = "indentor";
	  BaseClass base = new BaseClass();
	  IndentorPOM indentor= new IndentorPOM();
	  private String indentorCode;
	  //private String IndentorName;
	  //private String LegacyIndentorCode;

	  private String pan;
	  private String gst;
	  private String tin;
	  
	  public IndentorMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens
	  
	  
	  @When("Delete data initially from database in Indentor")
	  public void deletedatainitiallyfromdatabaseinindentor() {
		  
		  DBUtilities.deleteFromTable("indentor_type", "date_established", "2021-06-30");
		  DBUtilities.deleteFromTable("indentor_type", "date_established", "2015-03-02");
		  
		  DBUtilities.deleteFromTable("indentor", "indentor_code", "INDC24");
		  DBUtilities.deleteFromTable("indentor", "indentor_code", "edt24");
		  DBUtilities.deleteFromTable("indentor", "indentor_code", "eedt24");
		  DBUtilities.deleteFromTable("indentor_type", "indentor_type_code", "IND02");
	  }
	  
	
	  @Given("A web browser is on the Indentor Master page")
	  public void awebbrowserisontheindentormasterpage() throws InterruptedException {
		  BaseClass.driver.get(BaseClass.baseURL + url);
			Thread.sleep(2000);
	  }

	  @When("Add new Indentor button is clicked")
	  public void addnewindentorbuttonisclicked() {
	      base.waitAndClick(indentor.getNewIndentor());
	  }

	  @Then("Popup with title as Indentor Master should be displayed with Submit button in Indentor Page")
	  public void popupwithtitleasindentormastershouldbedisplayedwithsubmitbuttoninindentorpage() throws InterruptedException {
		  base.implicitwait();
			
	        Thread.sleep(3000);
		    WebElement IndentorTitle=BaseClass.driver.findElement(By.xpath("//h3[@class='card-title']"));
		    base.implicitwait();
		    assertEquals("Indentor Master",IndentorTitle.getText());
		    assertEquals("Submit",indentor.getSubmit().getText());
	  }

//scenario outline:valid data1              valid data               valid data          valid data           valid data
	  
	  @Then("Entering valid data for set of {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} in the input box for IndentorForm")
	  public void enteringvaliddataforsetofintheinputboxforindentorform(String indentor_code, String indentor_name, String legacy_indentor_code, String indentor_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(3000);
		  
		    this.indentorCode=indentor_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(indentor_code);
		    base.implicitwait();
	        // Indentor info
	       base.waitAndSendKeys(indentor.getIndentorCode(), indentor_code);
	       base.waitAndSendKeys(indentor.getIndentorName(), indentor_name);
	       base.waitAndSendKeys(indentor.getLegacyIndentorCode(), legacy_indentor_code);
	       base.selectClass(indentor.getIndentorType());
	       base.selectByVisibleText("IND02");
	       base.waitAndSendKeys(indentor.getIndentorShortName(), indentor_short_name);
	       base.waitAndSendKeys(indentor.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(indentor.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(indentor.getAddress1Flat(), flat);
		   base.waitAndSendKeys(indentor.getAddress2Building(), building_name);
		   base.waitAndSendKeys(indentor.getAddress3Road(), road);
		   base.waitAndSendKeys(indentor.getAddress4Area(), area);

		   base.waitAndClick(indentor.getCountry());
		   base.waitAndClick(indentor.getCountryValueClick());
		   base.waitAndClick(indentor.getState());
		   base.waitAndClick(indentor.getStateValueClick());
		   base.waitAndClick(indentor.getCity());
		   base.waitAndClick(indentor.getCityValueClick());
	       
		   base.waitAndSendKeys(indentor.getPincode(), pincode);
		   base.waitAndSendKeys(indentor.getTimezone(), timezone);
		   base.waitAndSendKeys(indentor.getRegion(), region);
		   base.javaScriptClick(indentor.getOutsideProcessYes());
		   // Contact details
		   base.waitAndSendKeys(indentor.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(indentor.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(indentor.getEmail(), email);
		   base.waitAndSendKeys(indentor.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(indentor.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(indentor.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(indentor.getPAN(), pan);
		   base.waitAndSendKeys(indentor.getGST(), gst);
		   base.waitAndSendKeys(indentor.getTIN(), tin);
		   
	    
		    
	  }

	  @Then("Click the submit button in Indentor form")
	  public void clickthesubmitbuttoninindentorform() {
	      base.waitAndClick(indentor.getSubmit());
	  }

	  @Then("A Popup with saved successfully should be displayed in Indentor Master Page")
	  public void apopupwithsavedsuccessfullyshouldbedisplayedinindentormasterpage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  assertEquals("Saved Successfully",validdata.getText());
	  }

	  @Then("The entered data should be available in Indentor table")
	  public void theentereddatashouldbeavailableinindentortable() {
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
		    DBUtilities.findUniqueRowInTable("indentor","indentor_code",this.indentorCode);
		    assertEquals(this.indentorCode, uniqueRowInTable.get("indentor_code"));
		     assertEquals(this.pan, uniqueRowInTable.get("pan_no"));
			 assertEquals(this.gst, uniqueRowInTable.get("gst"));
			 assertEquals(this.tin, uniqueRowInTable.get("tin_no"));
	  }

// scenario outline duplicate data
	  
	  @Then("Set data in Indentor table")
	  public void setdatainindentortable() {
		  base.databaseWaitTime();
		  
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
	        Date date = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	        String currentDateTime = format.format(date);
	        values.put("indentor_code","INDC24");
	        values.put("indentor_name","Stockist");
	        values.put("indentor_type","IND02");
	        values.put("legacy_ind_code","LIC4");
	        values.put("type","Ship To");
	        values.put("date_established","2015-03-02");
	        values.put("short_name","IND_SHT_NM");
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
	        
	        DBUtilities.insertIntoTable("indentor",values);
		}
	  

	  
	  @Then("Entering duplicate data for set of {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} in the input box for IndentorForm")
	  public void enteringduplicatedataforsetofintheinputboxforindentorform(String indentor_code, String indentor_name, String legacy_indentor_code, String indentor_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  
		  Thread.sleep(3000);
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(3000);
		  
		  this.indentorCode=indentor_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(indentor_code);
		    base.implicitwait();
	       // Indentor info
	       base.waitAndSendKeys(indentor.getIndentorCode(), indentor_code);
	       base.waitAndSendKeys(indentor.getIndentorName(), indentor_name);
	       base.waitAndSendKeys(indentor.getLegacyIndentorCode(), legacy_indentor_code);
	       base.selectClass(indentor.getIndentorType());
	       base.selectByVisibleText("IND02");
	       base.waitAndSendKeys(indentor.getIndentorShortName(), indentor_short_name);
	       base.waitAndSendKeys(indentor.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(indentor.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(indentor.getAddress1Flat(), flat);
		   base.waitAndSendKeys(indentor.getAddress2Building(), building_name);
		   base.waitAndSendKeys(indentor.getAddress3Road(), road);
		   base.waitAndSendKeys(indentor.getAddress4Area(), area);
		   
		   Thread.sleep(2000);
		  /* base.WaitUntil(indentor.getCountry());
		   //base.waitAndClick(indentor.getCountry());
		   base.selectClass(indentor.getCountry());
	       base.selectByVisibleText(country);
		   
		   
		   Thread.sleep(2000);
		   //WebDriverWait wait = new WebDriverWait(driver,30);
		   //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'COMPOSE')]")));
		   
		   base.WaitUntil(indentor.getState());
		   //base.waitAndClick(indentor.getState());
	       base.selectClass(indentor.getState());
	       base.selectByVisibleText(state);
	       
	       
	       base.WaitUntil(indentor.getCity());
	       
	       //base.waitAndClick(indentor.getCity());
	       base.selectClass(indentor.getCity());
	       base.selectByVisibleText(city);*/
		   
		   base.waitAndClick(indentor.getCountry());
		   base.waitAndClick(indentor.getCountryValueClick());
		   base.waitAndClick(indentor.getState());
		   base.waitAndClick(indentor.getStateValueClick());
		   base.waitAndClick(indentor.getCity());
		   base.waitAndClick(indentor.getCityValueClick());
	       
	       
		   base.waitAndSendKeys(indentor.getPincode(), pincode);
		   base.waitAndSendKeys(indentor.getTimezone(), timezone);
		   base.waitAndSendKeys(indentor.getRegion(), region);
		   base.javaScriptClick(indentor.getOutsideprocessNo());
		   // Contact details
		   base.waitAndSendKeys(indentor.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(indentor.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(indentor.getEmail(), email);
		   base.waitAndSendKeys(indentor.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(indentor.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(indentor.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(indentor.getPAN(), pan);
		   base.waitAndSendKeys(indentor.getGST(), gst);
		   base.waitAndSendKeys(indentor.getTIN(), tin);
		   
		   
	  }

	  @Then("The enterd data should not be available in Indentor Database for duplicate data")
	  public void theenterddatashouldnotbeavailableinindentordatabaseforduplicatedata() {
		  base.databaseWaitTime();
		    assertEquals(1,DBUtilities.getNumberOfRows("indentor","indentor_code",this.indentorCode));
		    assertEquals(1,DBUtilities.getNumberOfRows("indentor","pan_no",this.pan));
		    assertEquals(1,DBUtilities.getNumberOfRows("indentor","gst",this.gst));
	  }

	  
	  @Then("A popup with message already exists data should be displayed in Indentor Page")
	  public void apopupwithmessagealreadyexistsdatashouldbedisplayedinindentorpage() throws InterruptedException {
		  Thread.sleep(3000);
			WebElement errormsg = BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
			String errormessage = errormsg.getText();
			assertEquals("Already exixts",errormessage);
	  }

//scenario outline: Boundary value analysis
	  
	  @Then("Entering data for {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} in which Gst,pan,Tin with less and more characters than normal")
	  public void enteringdataforinwhichgstpantinwithlessandmorecharactersthannormal(String indentor_code, String indentor_name, String legacy_indentor_code, String indentor_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
	      
		  this.indentorCode=indentor_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(indentor_code);
		    base.implicitwait();
	        // Indentor info
	       base.waitAndSendKeys(indentor.getIndentorCode(), indentor_code);
	       base.waitAndSendKeys(indentor.getIndentorName(), indentor_name);
	       base.waitAndSendKeys(indentor.getLegacyIndentorCode(), legacy_indentor_code);
	       base.selectClass(indentor.getIndentorType());
	       base.selectByVisibleText("IND02");
	       base.waitAndSendKeys(indentor.getIndentorShortName(), indentor_short_name);
	       base.waitAndSendKeys(indentor.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(indentor.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(indentor.getAddress1Flat(), flat);
		   base.waitAndSendKeys(indentor.getAddress2Building(), building_name);
		   base.waitAndSendKeys(indentor.getAddress3Road(), road);
		   base.waitAndSendKeys(indentor.getAddress4Area(), area);

		   base.waitAndClick(indentor.getCountry());
		   base.waitAndClick(indentor.getCountryValueClick());
		   base.waitAndClick(indentor.getState());
		   base.waitAndClick(indentor.getStateValueClick());
		   base.waitAndClick(indentor.getCity());
		   base.waitAndClick(indentor.getCityValueClick());
	       
		   base.waitAndSendKeys(indentor.getPincode(), pincode);
		   base.waitAndSendKeys(indentor.getTimezone(), timezone);
		   base.waitAndSendKeys(indentor.getRegion(), region);
		   base.javaScriptClick(indentor.getOutsideprocessNo());
		   // Contact details
		   base.waitAndSendKeys(indentor.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(indentor.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(indentor.getEmail(), email);
		   base.waitAndSendKeys(indentor.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(indentor.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(indentor.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(indentor.getPAN(), pan);
		   base.waitAndSendKeys(indentor.getGST(), gst);
		   base.waitAndSendKeys(indentor.getTIN(), tin);
		   
		   
	  }

	  @Then("The enterd data should not be available in Indentor Database for BVA test")
	  public void theenterddatashouldnotbeavailableinindentordatabaseforbvatest() {
			base.databaseWaitTime();
		    assertEquals(0,DBUtilities.getNumberOfRows("indentor","indentor_code",this.indentorCode));
		    assertEquals(0,DBUtilities.getNumberOfRows("indentor","pan_no",this.pan));
		    assertEquals(0,DBUtilities.getNumberOfRows("indentor","gst",this.gst));
	  }
	  
//scenario outline: invalid data
	  
	  @Then("Entering invalid data for {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} with different characters and alphanumeric")
	  public void enteringinvaliddataforwithdifferentcharactersandalphanumeric(String indentor_code, String indentor_name, String legacy_indentor_code, String indentor_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
	      
		  this.indentorCode=indentor_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(indentor_code);
		    base.implicitwait();
	        // Indentor info
	       base.waitAndSendKeys(indentor.getIndentorCode(), indentor_code);
	       base.waitAndSendKeys(indentor.getIndentorName(), indentor_name);
	       base.waitAndSendKeys(indentor.getLegacyIndentorCode(), legacy_indentor_code);
	       base.selectClass(indentor.getIndentorType());
	       base.selectByVisibleText("IND02");
	       base.waitAndSendKeys(indentor.getIndentorShortName(), indentor_short_name);
	       base.waitAndSendKeys(indentor.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(indentor.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(indentor.getAddress1Flat(), flat);
		   base.waitAndSendKeys(indentor.getAddress2Building(), building_name);
		   base.waitAndSendKeys(indentor.getAddress3Road(), road);
		   base.waitAndSendKeys(indentor.getAddress4Area(), area);

		   base.waitAndClick(indentor.getCountry());
		   base.waitAndClick(indentor.getCountryValueClick());
		   base.waitAndClick(indentor.getState());
		   base.waitAndClick(indentor.getStateValueClick());
		   base.waitAndClick(indentor.getCity());
		   base.waitAndClick(indentor.getCityValueClick());
	       
		   base.waitAndSendKeys(indentor.getPincode(), pincode);
		   base.waitAndSendKeys(indentor.getTimezone(), timezone);
		   base.waitAndSendKeys(indentor.getRegion(), region);
		   base.javaScriptClick(indentor.getOutsideprocessNo());
		   // Contact details
		   base.waitAndSendKeys(indentor.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(indentor.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(indentor.getEmail(), email);
		   base.waitAndSendKeys(indentor.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(indentor.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(indentor.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(indentor.getPAN(), pan);
		   base.waitAndSendKeys(indentor.getGST(), gst);
		   base.waitAndSendKeys(indentor.getTIN(), tin);
		   
		   
	  }

	  @Then("The enterd data should not be available in Indentor Database for invalid character data")
	  public void theenterddatashouldnotbeavailableinindentordatabaseforinvalidcharacterdata() {
	      
		  base.databaseWaitTime();
		    assertEquals(0,DBUtilities.getNumberOfRows("indentor","indentor_code",this.indentorCode));
		    assertEquals(0,DBUtilities.getNumberOfRows("indentor","pan_no",this.pan));
		    assertEquals(0,DBUtilities.getNumberOfRows("indentor","gst",this.gst));
	  }
	  
//scenario outline: blank data
	  
	  @Then("Entering blank data in {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}  in the input box with blank data in Indentor Form")
	  public void enteringblankdatainintheinputboxwithblankdatainindentorform(String indentor_code, String indentor_name, String legacy_indentor_code, String indentor_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
	      
		  this.indentorCode=indentor_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(indentor_code);
		    base.implicitwait();
	        // Indentor info
	       base.waitAndSendKeys(indentor.getIndentorCode(), indentor_code);
	       base.waitAndSendKeys(indentor.getIndentorName(), indentor_name);
	       base.waitAndSendKeys(indentor.getLegacyIndentorCode(), legacy_indentor_code);
	       base.selectClass(indentor.getIndentorType());
	       base.selectByVisibleText("IND02");
	       base.waitAndSendKeys(indentor.getIndentorShortName(), indentor_short_name);
	       base.waitAndSendKeys(indentor.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(indentor.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.waitAndSendKeys(indentor.getAddress1Flat(), flat);
		   base.waitAndSendKeys(indentor.getAddress2Building(), building_name);
		   base.waitAndSendKeys(indentor.getAddress3Road(), road);
		   base.waitAndSendKeys(indentor.getAddress4Area(), area);

		   base.waitAndClick(indentor.getCountry());
		   base.waitAndClick(indentor.getCountryValueClick());
		   base.waitAndClick(indentor.getState());
		   base.waitAndClick(indentor.getStateValueClick());
		   base.waitAndClick(indentor.getCity());
		   base.waitAndClick(indentor.getCityValueClick());
	       
		   base.waitAndSendKeys(indentor.getPincode(), pincode);
		   base.waitAndSendKeys(indentor.getTimezone(), timezone);
		   base.waitAndSendKeys(indentor.getRegion(), region);
		   base.javaScriptClick(indentor.getOutsideprocessNo());
		   // Contact details
		   base.waitAndSendKeys(indentor.getPrimaryTelephone(), primary_telephone);
		   base.waitAndSendKeys(indentor.getSecondaryTelephone(), secondary_telephone);
		   base.waitAndSendKeys(indentor.getEmail(), email);
		   base.waitAndSendKeys(indentor.getEmailAlternate(), email_alternate);
		   base.waitAndSendKeys(indentor.getPrimaryContact(), primary_contact);
		   base.waitAndSendKeys(indentor.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.waitAndSendKeys(indentor.getPAN(), pan);
		   base.waitAndSendKeys(indentor.getGST(), gst);
		   base.waitAndSendKeys(indentor.getTIN(), tin);
		   
		  
	  }

	  

	  @Then("All input box should show red mark in Indentor Master form")
	  public void allinputboxshouldshowredmarkinindentormasterform() throws InterruptedException {
		  
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
	  
	  @Then("The enterd data should not be available in Indentor Database for blank data")
	  public void theenterddatashouldnotbeavailableinindentordatabaseforblankdata() {
	      
		  base.databaseWaitTime();
		    assertEquals(0,DBUtilities.getNumberOfRows("indentor","indentor_code",this.indentorCode));
		    
	  }

//scenario outline: edit functionality
	  
	  @Then("Clicking the close button in Indentor Form")
	  public void clickingtheclosebuttoninindentorform() {
		 
		  base.implicitwait();
	      base.waitAndClick(indentor.getCancel());
	  }
//edit
	  @Then("Checking the functionality of edit button in {string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string},{string} by clicking in Indentor Page")
	  public void checkingthefunctionalityofeditbuttoninbyclickinginindentorpage(String indentor_code, String indentor_name, String legacy_indentor_code, String indentor_short_name, String date_established, String address_use_type, String flat, String building_name, String road, String area, String country, String state, String city, String pincode, String timezone, String region, String primary_telephone, String secondary_telephone, String email, String email_alternate, String primary_contact, String secondary_contact, String pan, String gst, String tin) throws InterruptedException {
		  Thread.sleep(4000);
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(4000);
		  //base.refreshApp(BaseClass.baseURL+url);
		  base.waitAndSendKeys(indentor.getSearchButton(), "INDC24");
		  base.waitAndClick(indentor.getEditIcon());
		  
		  this.indentorCode=indentor_code;
		    this.pan=pan;
		    this.gst=gst;
		    this.tin=tin;
		    base.itemsToBeDeleted.add(indentor_code);
		    base.implicitwait();
	        // Indentor info
	       base.clearAndSendKeys(indentor.getIndentorCode(), indentor_code);
	       base.clearAndSendKeys(indentor.getIndentorName(), indentor_name);
	       base.clearAndSendKeys(indentor.getLegacyIndentorCode(), legacy_indentor_code);
	       base.selectClass(indentor.getIndentorType());
	       base.selectByVisibleText("IND02");
	       base.clearAndSendKeys(indentor.getIndentorShortName(), indentor_short_name);
	       base.clearAndSendKeys(indentor.getDateEstablished(), date_established);
		   // Address 
	       //base.waitAndSendKeys(indentor.getAddressUseTypeDropDown(), address_use_type);
	       base.selectClass(indentor.getAddressUseTypeDropDown());
	       base.selectByVisibleText(address_use_type);
		   base.clearAndSendKeys(indentor.getAddress1Flat(), flat);
		   base.clearAndSendKeys(indentor.getAddress2Building(), building_name);
		   base.clearAndSendKeys(indentor.getAddress3Road(), road);
		   base.clearAndSendKeys(indentor.getAddress4Area(), area);

		   base.waitAndClick(indentor.getCountry());
		   base.waitAndClick(indentor.getCountryValueClick());
		   base.waitAndClick(indentor.getState());
		   base.waitAndClick(indentor.getStateValueClick());
		   base.waitAndClick(indentor.getCity());
		   base.waitAndClick(indentor.getCityValueClick());
	       
		   base.clearAndSendKeys(indentor.getPincode(), pincode);
		   base.clearAndSendKeys(indentor.getTimezone(), timezone);
		   base.clearAndSendKeys(indentor.getRegion(), region);
		   base.javaScriptClick(indentor.getOutsideprocessNo());
		   // Contact details
		   base.clearAndSendKeys(indentor.getPrimaryTelephone(), primary_telephone);
		   base.clearAndSendKeys(indentor.getSecondaryTelephone(), secondary_telephone);
		   base.clearAndSendKeys(indentor.getEmail(), email);
		   base.clearAndSendKeys(indentor.getEmailAlternate(), email_alternate);
		   base.clearAndSendKeys(indentor.getPrimaryContact(), primary_contact);
		   base.clearAndSendKeys(indentor.getSecondaryContact(), secondary_contact);
		   // Tax information
		   base.clearAndSendKeys(indentor.getPAN(), pan);
		   base.clearAndSendKeys(indentor.getGST(), gst);
		   base.clearAndSendKeys(indentor.getTIN(), tin);
		   //base.waitAndClick(indentor.getSubmit());
		   //base.waitAndClick(indentor.getSubmit());
		   base.javaScriptClick(indentor.getSubmit());
	  }

	  @Then("The edited data should be available in database of Indentor Master")
	  public void theediteddatashouldbeavailableindatabaseofindentormaster() {
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
		    DBUtilities.findUniqueRowInTable("indentor","indentor_code","edt24");
			base.databaseWaitTime();
			base.databaseWaitTime();
		    assertEquals("edt24", uniqueRowInTable.get("indentor_code"));
		     assertEquals("e-ATFGH7891K", uniqueRowInTable.get("pan_no"));
			 assertEquals("e-33ATFgh7891LKAZ", uniqueRowInTable.get("gst"));
			 assertEquals("e-321234568TN", uniqueRowInTable.get("tin_no"));
	  }
	  
//scenario:delete functionality
	  
	  @Then("Checking the functionality of delete icon in Indentor Master Page")
	  public void checkingthefunctionalityofdeleteiconinindentormasterpage() throws InterruptedException {
		  
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(2000);
		  base.waitAndSendKeys(indentor.getSearchButton(), "INDC24");
		  base.waitAndClick(indentor.getDeleteIcon());
		 
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Indentor Page")
	  public void clickingdeletebuttonandapopupdeletedsucessfullyshouldbedisplayedinindentorpage() throws InterruptedException {
		  Thread.sleep(2000);
			//delete icon popup 
			  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[1]"));
		      String dltmessage = deletemsg.getText();
	          assertEquals("Are you Sure ?",dltmessage);
		  
		  //delete msg popup        
          base.waitAndClick(indentor.getDeletebutton());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
	  }

	  @Then("Verifying that the edited data was deleted in Indentor database")
	  public void verifyingthattheediteddatawasdeletedinindentordatabase() throws InterruptedException {
		  Thread.sleep(1000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("indentor","indentor_code","edt24");
		   Thread.sleep(1000);
	       assertEquals("1",uniqueRowInTable.get("deleted"));
	       DBUtilities.findUniqueRowInTable("indentor","indentor_code","edt24");
	  }
	  
//scenario: search functionality
	  
	  @Then("Searching the Indentor code by sending keys")
	  public void searchingtheindentorcodebysendingkeys() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.waitAndClick(indentor.getSearchButton());
			base.waitAndSendKeys(indentor.getSearchButton(), "INDC24");
	  }

	  @Then("The text in the search box should be equal to values in the Indentor table")
	  public void thetextinthesearchboxshouldbeequaltovaluesintheindentortable() throws InterruptedException {
		  Thread.sleep(1000);
			assertEquals(indentor.getSearchCheck().getText(),"INDC24");
			base.databaseWaitTime();
	  }

	  @Then("Clicking Export button in Indentor Master page")
	  public void clickingexportbuttoninindentormasterpage() {
	      base.waitAndClick(indentor.getExport());
	  }

	  
	
}
