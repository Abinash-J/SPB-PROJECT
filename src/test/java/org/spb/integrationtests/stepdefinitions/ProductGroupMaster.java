package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertAll;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertSame;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.ProductGroupPOM;
import org.spb.integrationtests.utils.DBUtilities;

import com.codeborne.selenide.ex.SoftAssertionError;
import com.codeborne.selenide.junit.SoftAsserts;
import com.codeborne.selenide.junit5.SoftAssertsExtension;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductGroupMaster {
	
	
	private static String url = "productgroup";
	  BaseClass base = new BaseClass();
	  ProductGroupPOM product= new ProductGroupPOM();
	  private String productgroupCode;
	  private String productgroupDesc;

	  public ProductGroupMaster(BaseClass base) {
	    this.base = base;
	  }
	  
//scenario: test screen opens
	  
	  @When("Delete data initially from database in Product Group")
	  public void deleteDataInitiallyFromDatabaseInProductGroup() {
		  
		  
		  DBUtilities.deleteFromTable("product_group","product_group_code","PG001");
		  DBUtilities.deleteFromTable("product_group","product_group_code","PG006");
		  DBUtilities.deleteFromTable("product_group","product_group_code","pg003");
	  }
	  
	  
	  
	  
	  @Given("A web browser is on the Product Group master page")
	  public void aWebBrowserIsOnTheProductGroupMasterPage() {
		  BaseClass.driver.get(BaseClass.baseURL + url);
	  }

	  @When("New Product Group button is clicked on page")
	  public void newProductGroupButtonIsClickedOnPage() {
		  base.click(product.getNew_ProductGroup());
	      base.WaitUntilVisible(product.getProductgroup_Code());
	  }

	  @Then("A popup with Product Group should be displayed when clicked")
	  public void aPopupWithProductGroupShouldBeDisplayedWhenClicked() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		   WebElement productgrouppage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
	       String productgrouppopup = productgrouppage.getText();
	       assertEquals("Product Group Master",productgrouppopup);
	            
	  }

	  @Then("A popup with submit button has shown in Product Group page")
	  public void aPopupWithSubmitButtonHasShownInProductGroupPage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String submit = submitbtn.getText();
	      assertEquals("Submit",submit);
	      
	    /*  
	      WebElement productgrouppage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
	      assertAll("Product Group Popup Page title & submit button ",
	    		    () -> assertEquals("Product Group", productgrouppage.getText()),
	    		    () -> assertEquals("Submit", submitbtn.getText())
	    		   
	    		);*/
	  
	  }

//scenario outline: valid data
	  
	  @Given("New Product Group button is clicked")
	  public void newProductGroupButtonIsClicked() {
		  base.click(product.getNew_ProductGroup());
	      base.WaitUntilVisible(product.getProductgroup_Code());
	  }

	  @Then("Entering the data in {string},{string} text box in Product Group")
	  public void enteringTheDataInTextBoxInProductGroup(String product_group_code, String product_group_desc) {
		  base.implicitwait();
		  this.productgroupCode=product_group_code;
	      this.productgroupDesc=product_group_desc;
	      base.itemsToBeDeleted.add(product_group_code);
	      base.sendkeys(product.getProductgroup_Code(), product_group_code);
	      base.WaitUntil(product.getDescription());
	      base.sendkeys(product.getDescription(), product_group_desc);
	      base.WaitUntil(product.getSubmit());
	  }

	  @When("Click on submit button in Product Group page")
	  public void clickOnSubmitButtonInProductGroupPage() {
	      base.click(product.getSubmit());
	  }

	  @Then("A popop with message successfully added should be displayed in Product Group page")
	  public void aPopopWithMessageSuccessfullyAddedShouldBeDisplayedInProductGroupPage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String successmsg = validdata.getText();
          assertEquals("Saved Successfully",successmsg);
	  }

	  @Then("The entered data is available in Product Group table")
	  public void theEnteredDataIsAvailableInProductGroupTable() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  HashMap<String, String> uniqueRowInTable =
			        DBUtilities.findUniqueRowInTable("product_group","product_group_code",this.productgroupCode);
			    assertEquals(this.productgroupDesc, uniqueRowInTable.get("description"));
	  }

//scenario outline: duplicate data
	  
	  @Given("Set data in Product Group table")
	  public void setDataInProductGroupTable() {
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("product_group_code","PG006");
		  	values.put("description","orange");
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	DBUtilities.insertIntoTable("product_group", values);
	  }

	  @Then("Entering the duplicate data in {string},{string} text box in Product Group")
	  public void enteringTheDuplicateDataInTextBoxInProductGroup(String product_group_code, String product_group_desc) throws InterruptedException {
		 
		  base.implicitwait();
		  Thread.sleep(2000);
		  this.productgroupCode=product_group_code;
	      this.productgroupDesc=product_group_desc;
	      base.itemsToBeDeleted.add(product_group_code);
	      base.sendkeys(product.getProductgroup_Code(), product_group_code);
	      base.WaitUntil(product.getDescription());
	      base.sendkeys(product.getDescription(), product_group_desc);
	      base.WaitUntil(product.getSubmit());
	  }

	  @Then("A popop with message already exists should be displayed in Product Group page")
	  public void aPopopWithMessageAlreadyExistsShouldBeDisplayedInProductGroupPage() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  String errormessage = errmsg.getText();
	      assertEquals("Already exist",errormessage);
	  }

//scenario outline:blank data
	  
	  @Then("Entering the blank data in {string},{string} text box in Product Group")
	  public void enteringTheBlankDataInTextBoxInProductGroup(String product_group_code, String product_group_desc) {
		  base.implicitwait();
		  this.productgroupCode=product_group_code;
	      this.productgroupDesc=product_group_desc;
	      base.itemsToBeDeleted.add(product_group_code);
	      base.sendkeys(product.getProductgroup_Code(), product_group_code);
	      base.WaitUntil(product.getDescription());
	      base.sendkeys(product.getDescription(), product_group_desc);
	      base.WaitUntil(product.getSubmit());
	  }

	  @Then("A popop with message Please enter all mandatory fields should be displayed in Product Group page")
	  public void aPopopWithMessagePleaseEnterAllMandatoryFieldsShouldBeDisplayedInProductGroupPage() throws InterruptedException {
		  Thread.sleep(2000);   
		  base.implicitwait();
		  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String blankmessage = blnkmsg.getText();
	      assertEquals("Please Enter all mandatory fields",blankmessage);
	  }

//scenario outline:testing functionality of edit button
	  
	  @Then("Clicking the close button in Product Group")
	  public void clickingTheCloseButtonInProductGroup() {
		  base.WaitUntilVisible(product.getCloseButton());
		     base.click(product.getCloseButton());
	  }
//edit
	  @Then("Checking the functionality of edit button and by clicking in Product Group")
	  public void checkingTheFunctionalityOfEditButtonAndByClickingInProductGroup() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(product.getSearchButton(),"PG006");
		  base.WaitUntilVisible(product.getEditIcon());
		  base.click(product.getEditIcon());
		  
		  base.WaitUntilVisible(product.getProductgroup_Code());
		  product.getProductgroup_Code().clear();
		  base.sendkeys(product.getProductgroup_Code(), "pg003");
		  base.WaitUntilVisible(product.getDescription());
		  product.getDescription().clear();
		  base.sendkeys(product.getDescription(), "red");
		  base.click(product.getSubmit());
		  
		  
	  }

	  @Then("Checking the update button has shown in the Product Group page")
	  public void checkingTheUpdateButtonHasShownInTheProductGroupPage() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  Thread.sleep(2000);
		  base.sendkeys(product.getSearchButton(),"pg003");
		  base.WaitUntilVisible(product.getEditIcon());
		  base.click(product.getEditIcon());
		  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String update = updatebtn.getText();
         // assertEquals("Update",update);
          base.click(product.getCloseButton());
          
		
          
	  }
	  
	  @Then("The edited data should be available in database of Product Group")
		public void theEditedDataShouldBeAvailableIndatabaseOfProductGroup() throws InterruptedException {
			 base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("product_group", "product_group_code","PG006");
			 assertEquals("PG006",uniqueRowInTable.get("product_group_code"));
		     
		}

//scenario outline: testing functionality of delete button
	  
	  @Then("Checking the functionality of delete button by clicking in Product Group")
	  public void checkingTheFunctionalityOfDeleteButtonByClickingInProductGroup() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  base.implicitwait();
		  Thread.sleep(2000);
		  base.sendkeys(product.getSearchButton(),"PG006");
		  base.WaitUntil(product.getDeleteIcon());
		  base.click(product.getDeleteIcon());
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Product Group")
	  public void clickingDeleteButtonAndAPopupDeletedSucessfullyShouldBeDisplayedInProductGroup() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
//delete icon popup 
		  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
	      String dltmessage = deletemsg.getText();
          assertEquals("Are you Sure ?",dltmessage);
//delete msg popup        
          base.click(product.getDeleteButtonPopup());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
        
	       
	  }

	  @Then("Verifying that the entered data was deleted in database of Product Group")
		public void verifyingThatTheEnteredDataWasDeletedInDatabaseOfProductGroup() throws InterruptedException {
			Thread.sleep(2000);
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("product_group","product_group_code","pg003");
			assertEquals("1",uniqueRowInTable.get("deleted"));
			DBUtilities.deleteFromTable("product_group","product_group_code","pg003");
		}
	  
	  
	  
	//search box
			@Then("Searching the Product Group code by sending keys")
			public void searchingTheProductGroupCodeBySendingKeys() throws InterruptedException {
				base.refreshApp(BaseClass.baseURL+url);
				//base.WaitUntilVisible(gsm.getSearchButton());
				Thread.sleep(2000);
				base.click(product.getSearchButton());
				base.sendkeys(product.getSearchButton(),"PG006");
			}

			@Then("The text in the search box should be equal to values in the Product Group table")
			public void	theTextInTheSearchBoxShouldBeEqualToValuesInTheProductGroupTable() throws InterruptedException {
				Thread.sleep(1000);
				assertEquals(product.getSearchCheck().getText(),"PG006");
				base.databaseWaitTime();
				DBUtilities.deleteFromTable("product_group","product_group_code","PG006");
				
			}
		//Export button
			@Then("Clicking Export button in Product Group master page")
			public void	clickingExportButtonInProductGroupMasterPage() throws InterruptedException {
			 	base.WaitUntil(product.getExport());
			 	base.click(product.getExport());
			 	
			}
	  
	  
	  
	  
	  
	  
	  }

	  
	  
