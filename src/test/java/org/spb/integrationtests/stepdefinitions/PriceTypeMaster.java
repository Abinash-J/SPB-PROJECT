package org.spb.integrationtests.stepdefinitions;


import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.PriceTypePOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PriceTypeMaster {

	
	private static String url = "pricetype";
	  BaseClass base = new BaseClass();
	  PriceTypePOM pricetp= new PriceTypePOM();
	  private String pricetypeCode;
	  private String pricetypeDesc;

	  public PriceTypeMaster(BaseClass base) {
	    this.base = base;
	  }
	  
//scenario: test screen opens
	  
	  @When("Delete data initially from database in Price Type")
	  public void deleteDataInitiallyFromDatabaseInPriceType() {
		  DBUtilities.deleteFromTable("price_type","price_code","price a");
		  DBUtilities.deleteFromTable("price_type","price_code","price b");
		  DBUtilities.deleteFromTable("price_type","price_code","price c");
		  
		  
		  
	  }
	  
	  
	  
	  @Given("A web browser is on the Price Type master page")
	  public void a_web_browser_is_on_the_price_type_master_page() {
		  BaseClass.driver.get(BaseClass.baseURL + url);
	  }

	  @When("New Price Type button is clicked on page")
	  public void new_price_type_button_is_clicked_on_page() {
		  base.click(pricetp.getNew_PriceType());
	      base.WaitUntilVisible(pricetp.getPriceType_Code());
	  }

	  @Then("A popup with Price Type should be displayed when clicked")
	  public void a_popup_with_price_type_should_be_displayed_when_clicked() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		   WebElement pricetypepage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
	       String pricetypepopup = pricetypepage.getText();
	       assertEquals("Price Type",pricetypepopup);
	       
	  }

	  @Then("A popup with submit button has shown in Price Type page")
	  public void a_popup_with_submit_button_has_shown_in_price_type_page() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String submit = submitbtn.getText();
          assertEquals("Submit",submit);
	      
	  }

	  
//scenario outline: valid data
	  
	  @Given("New Price Type button is clicked")
	  public void new_price_type_button_is_clicked() {
		  base.click(pricetp.getNew_PriceType());
	      base.WaitUntilVisible(pricetp.getPriceType_Code());
	  }

	  @Then("Entering the data in {string},{string} text box in Price Type")
	  public void entering_the_data_in_text_box_in_price_type(String price_type_code, String price_type_desc) {
		  base.implicitwait();
		  this.pricetypeCode=price_type_code;
	      this.pricetypeDesc=price_type_desc;
	      base.itemsToBeDeleted.add(price_type_code);
	      base.sendkeys(pricetp.getPriceType_Code(), price_type_code);
	      base.WaitUntil(pricetp.getDescription());
	      base.sendkeys(pricetp.getDescription(), price_type_desc);
	      base.WaitUntil(pricetp.getSubmit());
	  }

	  @When("Click on submit button in Price Type page")
	  public void click_on_submit_button_in_price_type_page() {
		  base.click(pricetp.getSubmit());
	  }

	  @Then("A popop with message successfully added should be displayed in Price Type page")
	  public void a_popop_with_message_successfully_added_should_be_displayed_in_price_type_page() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String successmsg = validdata.getText();
          assertEquals("Saved Successfully",successmsg);
	  }

	  @Then("The entered data is available in Price Type table")
	  public void the_entered_data_is_available_in_price_type_table() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  HashMap<String, String> uniqueRowInTable =
			        DBUtilities.findUniqueRowInTable("price_type","price_code",this.pricetypeCode);
			    assertEquals(this.pricetypeDesc, uniqueRowInTable.get("price_name"));
	  }

//scenario outline: duplicate data
	  
	  @Given("Set data in Price Type table")
	  public void set_data_in_price_type_table() {
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("price_code","price b");
		  	values.put("price_name","price 2");
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	DBUtilities.insertIntoTable("price_type", values);
	  }

	  @Then("Entering the duplicate data in {string},{string} text box in Price Type")
	  public void entering_the_duplicate_data_in_text_box_in_price_type(String price_type_code, String price_type_desc) {
		  base.implicitwait();
		  this.pricetypeCode=price_type_code;
	      this.pricetypeDesc=price_type_desc;
	      base.itemsToBeDeleted.add(price_type_code);
	      base.sendkeys(pricetp.getPriceType_Code(), price_type_code);
	      base.WaitUntil(pricetp.getDescription());
	      base.sendkeys(pricetp.getDescription(), price_type_desc);
	      base.WaitUntil(pricetp.getSubmit());
	  }

	  @Then("A popop with message already exists should be displayed in Price Type page")
	  public void a_popop_with_message_already_exists_should_be_displayed_in_price_type_page() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  String errormessage = errmsg.getText();
	      assertEquals("Already exist",errormessage);
	  }
	  
	  
//scenario outline:blank data
	  
	  @Then("Entering the blank data in {string},{string} text box in Price Type")
	  public void entering_the_blank_data_in_text_box_in_price_type(String price_type_code, String price_type_desc) {
		  base.implicitwait();
		  this.pricetypeCode=price_type_code;
	      this.pricetypeDesc=price_type_desc;
	      base.itemsToBeDeleted.add(price_type_code);
	      base.sendkeys(pricetp.getPriceType_Code(), price_type_code);
	      base.WaitUntil(pricetp.getDescription());
	      base.sendkeys(pricetp.getDescription(), price_type_desc);
	      base.WaitUntil(pricetp.getSubmit());
	  }

	  @Then("A popop with message Please enter all mandatory fields should be displayed in Price Type page")
	  public void a_popop_with_message_please_enter_all_mandatory_fields_should_be_displayed_in_price_type_page() throws InterruptedException {
		  Thread.sleep(2000);   
		  base.implicitwait();
		  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String blankmessage = blnkmsg.getText();
	      assertEquals("Please Enter all mandatory fields",blankmessage);
	  }
	  
//scenario outline:testing functionality of edit button
	  
	  @Given("Clicking the close button in Price Type")
	  public void clicking_the_close_button_in_price_type() {
		  
		     base.click(pricetp.getCloseButton());
	  }
	  
//edit
	  
	  @Then("Checking the functionality of edit button and by clicking in Price Type")
	  public void checking_the_functionality_of_edit_button_and_by_clicking_in_price_type() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(pricetp.getSearchButton(),"price b");
		  base.WaitUntilVisible(pricetp.getEditIcon());
		  base.click(pricetp.getEditIcon());
		  
		  base.WaitUntilVisible(pricetp.getPriceType_Code());
		  pricetp.getPriceType_Code().clear();
		  base.sendkeys(pricetp.getPriceType_Code(), "price c");
		  base.WaitUntilVisible(pricetp.getDescription());
		  pricetp.getDescription().clear();
		  base.sendkeys(pricetp.getDescription(), "price 3");
		  base.click(pricetp.getSubmit());
	  }

	  @Then("Checking the update button has shown in the Price Type page")
	  public void checking_the_update_button_has_shown_in_the_price_type_page() throws InterruptedException {
		  
		  Thread.sleep(2000);
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(pricetp.getSearchButton(),"price c");
		  base.WaitUntilVisible(pricetp.getEditIcon());
		  base.click(pricetp.getEditIcon());
		  
		  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String update = updatebtn.getText();
          assertEquals("Submit",update);
          base.click(pricetp.getCloseButton());;
          
	  }

	  @Then("The edited data should be available in database of Price Type")
	  public void the_edited_data_should_be_available_in_database_of_price_type() throws InterruptedException {
		  base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("price_type", "price_code","price c");
			 assertEquals("price c",uniqueRowInTable.get("price_code"));
	  }

//scenario outline: testing functionality of delete button
	  
	  @Then("Checking the functionality of delete button by clicking in Price Type")
	  public void checking_the_functionality_of_delete_button_by_clicking_in_price_type() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  base.implicitwait();
		  Thread.sleep(2000);
		  base.sendkeys(pricetp.getSearchButton(),"price b");
		  base.WaitUntil(pricetp.getDeleteIcon());
		  base.click(pricetp.getDeleteIcon());
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Price Type")
	  public void clicking_delete_button_and_a_popup_deleted_sucessfully_should_be_displayed_in_price_type() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
//delete icon popup 
		  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
	      String dltmessage = deletemsg.getText();
          assertEquals("Are you Sure ?",dltmessage);
//delete msg popup        
          base.click(pricetp.getDeleteButtonPopup());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
        
	  }

	  @Then("Verifying that the entered data was deleted in database of Price Type")
	  public void verifying_that_the_entered_data_was_deleted_in_database_of_price_type() throws InterruptedException {
		  Thread.sleep(2000);
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("price_type","price_code","price c");
			assertEquals("1",uniqueRowInTable.get("deleted"));
			DBUtilities.deleteFromTable("price_type","price_code","price c");
	  }

	
	//search box
			@Then("Searching the Price Type code by sending keys")
			public void searchingThePriceTypeCodeBySendingKeys() throws InterruptedException {
				base.refreshApp(BaseClass.baseURL+url);
				//base.WaitUntilVisible(gsm.getSearchButton());
				Thread.sleep(2000);
				base.click(pricetp.getSearchButton());
				base.sendkeys(pricetp.getSearchButton(),"price b");
			}

			@Then("The text in the search box should be equal to values in the Price Type table")
			public void	theTextInTheSearchBoxShouldBeEqualToValuesInThePriceTypeTable() throws InterruptedException {
				Thread.sleep(1000);
				assertEquals(pricetp.getSearchCheck().getText(),"price b");
				base.databaseWaitTime();
				DBUtilities.deleteFromTable("price_type","price_code","price b");
				
			}
		//Export button
			@Then("Clicking Export button in Price Type master page")
			public void	clickingExportButtonInPriceTypeMasterPage() throws InterruptedException {
			 	base.WaitUntil(pricetp.getExport());
			 	base.click(pricetp.getExport());
			 	
			}
	
	
}
