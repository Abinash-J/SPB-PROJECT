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
import org.spb.integrationtests.pageobjectmodel.ItemTypePOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class ItemTypeMaster {

	private static String url = "itemtype";
	  BaseClass base = new BaseClass();
	  ItemTypePOM ittyp= new ItemTypePOM();
	  private String itemtypeCode;
	  private String itemtypeDesc;

	  public ItemTypeMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens
	  
	  @When("Delete data initially from database in Item Type")
	  public void deleteDataInitiallyFromDatabaseInItemType() {
		  DBUtilities.deleteFromTable("item_type","item_type_code","win");
		  DBUtilities.deleteFromTable("item_type","item_type_code","WRP");
		  DBUtilities.deleteFromTable("item_type","item_type_code","fig");
		  DBUtilities.deleteFromTable("item_type","item_type_code","fg");
		  
	  }
	  
	  
	  
	  
		@Given("A web browser is on the Item Type master page")
		public void aWebBrowserIsOnTheItemTypeMasterPage() {
			BaseClass.driver.get(BaseClass.baseURL + url);
		}
		
		@When("New Item Type button is clicked on page")
		public void newItemTypeButtonIsClickedOnPage() {
			base.click(ittyp.getNew_ItemType());
		      base.WaitUntilVisible(ittyp.getItemType_Code());
		}
		
		@Then("A popup with Item Type should be displayed when clicked")
		public void aPopupWithItemTypeShouldBeDisplayedWhenClicked() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
			   WebElement itemtypepagepage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
		       String itemtypepopup = itemtypepagepage.getText();
		       assertEquals("Item Type",itemtypepopup);
		       
		}
		
		@Then("A popup with submit button has shown in Item Type page")
		public void aPopupWithSubmitButtonHasShownInItemTypePage() throws InterruptedException {
			 Thread.sleep(2000);
			  base.implicitwait();
			  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
		      String submit = submitbtn.getText();
	          assertEquals("Submit",submit);
		      
		}
		
//scenario outline: valid data
		
		@Given("New Item Type button is clicked")
		public void newItemTypeButtonIsClicked() {
			base.click(ittyp.getNew_ItemType());
		      base.WaitUntilVisible(ittyp.getItemType_Code());
		}
		
		@Then("Entering the data in {string},{string} text box in Item Type")
		public void enteringTheDataInTextBoxInItemType(String item_type_code, String item_type_desc) {
			base.implicitwait();
			  this.itemtypeCode=item_type_code;
		      this.itemtypeDesc=item_type_desc;
		      base.itemsToBeDeleted.add(item_type_code);
		      base.sendkeys(ittyp.getItemType_Code(), item_type_code);
		      base.WaitUntil(ittyp.getDescription());
		      base.sendkeys(ittyp.getDescription(), item_type_desc);
		      base.WaitUntil(ittyp.getSubmit());
		}
		
		@When("Click on submit button in Item Type page")
		public void clickOnSubmitButtonInItemTypePage() {
		    base.click(ittyp.getSubmit());
		}
		
		@Then("A popop with message successfully added should be displayed in Item Type page")
		public void aPopopWithMessageSuccessfullyAddedShouldBeDisplayedInItemTypePage() throws InterruptedException {
			 Thread.sleep(2000);
			  base.implicitwait();
			  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		      String successmsg = validdata.getText();
	          assertEquals("Saved Successfully",successmsg);
		}
		
		@Then("The entered data is available in Item Type table")
		public void theEnteredDataIsAvailableInItemTypeTable() throws InterruptedException {
			 base.implicitwait();
			  Thread.sleep(2000);
			  HashMap<String, String> uniqueRowInTable =
				        DBUtilities.findUniqueRowInTable("item_type","item_type_code",this.itemtypeCode);
				    assertEquals(this.itemtypeDesc, uniqueRowInTable.get("description"));
		}
		
//scenario outline: duplicate data
		
		@Given("Set data in Item Type table")
		public void setDataInItemTypeTable() {
			LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("item_type_code","WRP");
		  	values.put("description","wrapper");
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	DBUtilities.insertIntoTable("item_type", values);
		}
		
		@Then("Entering the duplicate data in {string},{string} text box in Item Type")
		public void enteringTheDuplicateDataInTextBoxInItemtype(String item_type_code, String item_type_desc) {
			base.implicitwait();
			  this.itemtypeCode=item_type_code;
		      this.itemtypeDesc=item_type_desc;
		      base.itemsToBeDeleted.add(item_type_code);
		      base.sendkeys(ittyp.getItemType_Code(), item_type_code);
		      base.WaitUntil(ittyp.getDescription());
		      base.sendkeys(ittyp.getDescription(), item_type_desc);
		      base.WaitUntil(ittyp.getSubmit());
		}
		
		@Then("A popop with message already exists should be displayed in Item Type page")
		public void aPopopWithMessageAlreadyExistsShouldBeDisplayedInItemTypePage() throws InterruptedException {
			base.implicitwait();
			  Thread.sleep(2000);
			  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
			  String errormessage = errmsg.getText();
		      assertEquals("Already exist",errormessage);
		}
		
		
//scenario outline:blank data
		
		@Then("Entering the blank data in {string},{string} text box in Item Type")
		public void enteringTheBlankDataInTextBoxInItemType(String item_type_code, String item_type_desc) {
			base.implicitwait();
			  this.itemtypeCode=item_type_code;
		      this.itemtypeDesc=item_type_desc;
		      base.itemsToBeDeleted.add(item_type_code);
		      base.sendkeys(ittyp.getItemType_Code(), item_type_code);
		      base.WaitUntil(ittyp.getDescription());
		      base.sendkeys(ittyp.getDescription(), item_type_desc);
		      base.WaitUntil(ittyp.getSubmit());
		}
		
		@Then("A popop with message Please enter all mandatory fields should be displayed in Item Type page")
		public void aPopopWithMessagePleaseEnterAllMandatoryFieldsShouldBeDisplayedInItemTypePage() throws InterruptedException {
			 Thread.sleep(2000);   
			  base.implicitwait();
			  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		      String blankmessage = blnkmsg.getText();
		      assertEquals("Please Enter all mandatory fields",blankmessage);
		}
		
//scenario outline:testing functionality of edit button
		
		@Given("Clicking the close button in Item Type")
		public void clickingTheCloseButtonInItemType() {
			base.WaitUntilVisible(ittyp.getCloseButton());
		     base.click(ittyp.getCloseButton());
		}
		
//edit
		@Then("Checking the functionality of edit button and by clicking in Item Type")
		public void checkingTheFunctionalityOfEditButtonAndByClickingInItem_type() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
			  base.refreshApp(BaseClass.baseURL+url);
			  base.sendkeys(ittyp.getSearchButton(),"WRP");
			  base.WaitUntilVisible(ittyp.getEditIcon());
			  base.click(ittyp.getEditIcon());
			  
			  base.WaitUntilVisible(ittyp.getItemType_Code());
			  ittyp.getItemType_Code().clear();
			  base.sendkeys(ittyp.getItemType_Code(), "fig");
			  base.WaitUntilVisible(ittyp.getDescription());
			  ittyp.getDescription().clear();
			  base.sendkeys(ittyp.getDescription(), "finishedgood");
			  base.click(ittyp.getSubmit());
		}
		
		@Then("Checking the update button has shown in the Item Type page")
		public void checkingTheUpdateButtonHasShownInTheItemTypePage() throws InterruptedException {
			 Thread.sleep(2000);
			 base.refreshApp(BaseClass.baseURL+url);
			 base.sendkeys(ittyp.getSearchButton(),"WRP");
			  base.WaitUntilVisible(ittyp.getEditIcon());
			  base.click(ittyp.getEditIcon());
			  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
		      String update = updatebtn.getText();
	          //assertEquals("Update",update);
	          base.click(ittyp.getCloseButton());
		}
		
		@Then("The edited data should be available in database of Item Type")
		public void theEditedDataShouldBeAvailableInDatabaseOfItemType() throws InterruptedException {
			 base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("item_type", "item_type_code","fig");
			 assertEquals("fig",uniqueRowInTable.get("item_type_code"));
		}
		
//scenario outline: testing functionality of delete button
		
		@Then("Checking the functionality of delete button by clicking in Item Type")
		public void checkingTheFunctionalityOfDeleteButtonByClickingInItemType() throws InterruptedException {
			base.refreshApp(BaseClass.baseURL+url);
			  base.implicitwait();
			  Thread.sleep(2000);
			  base.sendkeys(ittyp.getSearchButton(),"WRP");
			  base.WaitUntil(ittyp.getDeleteIcon());
			  base.click(ittyp.getDeleteIcon());
		}
		
		@Then("Clicking delete button and a popup deleted sucessfully should be displayed in Item Type")
		public void clickingDeleteButtonAndAPopupDeletedSucessfullyShouldBeDisplayedInItemType() throws InterruptedException {
			Thread.sleep(2000);
			  base.implicitwait();
	//delete icon popup 
			  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
		      String dltmessage = deletemsg.getText();
	          assertEquals("Are you Sure ?",dltmessage);
	//delete msg popup        
	          base.click(ittyp.getDeleteButtonPopup());
			  Thread.sleep(2000);
			  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		      String deletesuccess = dltsccsflymsg.getText();
	          assertEquals("Deleted Successfully",deletesuccess);
	        
		}
		
		@Then("Verifying that the entered data was deleted in database of Item Type")
		public void verifying_that_the_entered_data_was_deleted_in_database_of_item_type() throws InterruptedException {
			Thread.sleep(2000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("item_type","item_type_code","fig");
			assertEquals("1",uniqueRowInTable.get("deleted"));
			DBUtilities.deleteFromTable("item_type","item_type_code","fig");
		}
			
		
		//search box
				@Then("Searching the Item Type code by sending keys")
				public void searchingTheItemTypeCodeBySendingKeys() throws InterruptedException {
					base.refreshApp(BaseClass.baseURL+url);
					//base.WaitUntilVisible(gsm.getSearchButton());
					Thread.sleep(2000);
					base.click(ittyp.getSearchButton());
					base.sendkeys(ittyp.getSearchButton(),"WRP");
				}

				@Then("The text in the search box should be equal to values in the Item Type table")
				public void	theTextInTheSearchBoxShouldBeEqualToValuesInTheItemTypeTable() throws InterruptedException {
					Thread.sleep(1000);
					assertEquals(ittyp.getSearchCheck().getText(),"WRP");
					base.databaseWaitTime();
					DBUtilities.deleteFromTable("item_type","item_type_code","WRP");
					
				}
			//Export button
				@Then("Clicking Export button in Item Type master page")
				public void	clickingExportButtonInItemTypeMasterPage() throws InterruptedException {
				 	base.WaitUntil(ittyp.getExport());
				 	base.click(ittyp.getExport());
				 	
				}

			
	
	
	
	
}
