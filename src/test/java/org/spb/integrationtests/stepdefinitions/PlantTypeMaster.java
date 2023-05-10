package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.PlantTypeMasterPOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlantTypeMaster {
	private static String url = "planttype";
	  BaseClass base = new BaseClass();
	  PlantTypeMasterPOM planttype= new PlantTypeMasterPOM();
	  private String planttypeCode;
	  private String planttypeDesc;

	  public PlantTypeMaster(BaseClass base) {
	    this.base = base;
	  }

//scenario:test screen opens
	  
	  @When("Delete data initially from database in Plant Type")
	  public void deleteDataInitiallyFromDatabaseInPlantType() {
		  DBUtilities.deleteFromTable("plant_type","plant_type_code","11");
		  DBUtilities.deleteFromTable("plant_type","plant_type_code","12");
		  DBUtilities.deleteFromTable("plant_type","plant_type_code","13");
		  
	  }

	  
	  
	  
	  @Given("A web browser is on the Plant Type master page")
	  public void aWebBrowserIsOnThePlantTypeMasterPage() {
		  BaseClass.driver.get(BaseClass.baseURL + url);
		  //base.maximumPageLoadTime();
	  }

	  @When("New plant type button is clicked on page")
	  public void newPlantTypeButtonIsClickedOnPage() {
	      base.click(planttype.getNew_PlantType());
	      base.WaitUntilVisible(planttype.getPlantType_Code());
	  }

	  @Then("A popup with plant type should be displayed when clicked")
	  public void aPopupWithPlantTypeShouldBeDisplayedWhenClicked() {
	     base.implicitwait();
	     WebElement planttypepage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
		  String planttypepopup = planttypepage.getText();
	      assertEquals("Plant Type Master",planttypepopup);
	  }
	  
	  @Then("A popup with submit button has shown in plant type page")
	  public void aPopupWithSubmitButtonHasShownInPlantTypePage() {
		  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String submit = submitbtn.getText();
          assertEquals("Submit",submit);
	  }
	  
//scenario outline: valid data

	  @Given("New plant type button is clicked")
	  public void newPlantTypeButtonIsClicked() {
		  base.click(planttype.getNew_PlantType());
	      base.WaitUntilVisible(planttype.getPlantType_Code());
	  }

	  @Then("Entering the data in {string},{string} text box in plant type")
	  public void enteringTheDataInTextBoxInPlantType(String plant_type_code, String plant_type_desc) {
		  base.implicitwait();
		  this.planttypeCode=plant_type_code;
	      this.planttypeDesc=plant_type_desc;
	      base.itemsToBeDeleted.add(plant_type_code);
	      base.sendkeys(planttype.getPlantType_Code(), plant_type_code);
	      base.WaitUntil(planttype.getDescription());
	      base.sendkeys(planttype.getDescription(), plant_type_desc);
	      base.WaitUntil(planttype.getSubmit());
	  }

	  @When("Click on submit button in plant type page")
	  public void clickOnSubmitButtonInPlantTypePage() {
		  base.click(planttype.getSubmit());
		  
	  }

	  @Then("A popop with message successfully added should be displayed in plant type page")
	  public void aPopupWithMessageSuccessfullyAddedShouldBeDisplayedInPlantTypePage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String successmsg = validdata.getText();
        assertEquals("Saved Successfully",successmsg);
	  }

	  @Then("The entered data is available in plant type table")
	  public void theEnteredDataIsAvailableInPlantTypeTable() {
		  base.implicitwait();
		  HashMap<String, String> uniqueRowInTable =
			        DBUtilities.findUniqueRowInTable("plant_type", "plant_type_code", this.planttypeCode);
			    assertEquals(this.planttypeDesc, uniqueRowInTable.get("description"));
		    
	  }
	  
	  
	  
//sccenario outline: duplicate data
	  
	  @Given("Set data in plant type table")
	  public void setDataInPlantTypeTable() {
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("plant_type_code","12");
		  	values.put("description","Warehouse");
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	DBUtilities.insertIntoTable("plant_type", values);
	  }

	  @Then("Entering the duplicate data in {string},{string} text box in plant type")
	  public void enteringTheDuplilcateDataInTetxtBoxInPlantType(String plant_type_code, String plant_type_desc) {
		  base.implicitwait();
		  this.planttypeCode=plant_type_code;
	      this.planttypeDesc=plant_type_desc;
	      base.itemsToBeDeleted.add(plant_type_code);
	      base.sendkeys(planttype.getPlantType_Code(), plant_type_code);
	      base.WaitUntil(planttype.getDescription());
	      base.sendkeys(planttype.getDescription(), plant_type_desc);
	      base.WaitUntil(planttype.getSubmit());
	  }

	  @Then("A popop with message already exists should be displayed in plant type page")
	  public void aPopupWithMessageAlreadyExistsShouldBeDisplayedInPlantTypepage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		   String errormessage = errmsg.getText();
	       assertEquals("Already exists",errormessage);
	  }

	
//scenario outline:blank data
	  
	  @Then("Entering the blank data in {string},{string} text box in plant type")
	  public void enteringTheBlankDadtaInTextBoxInPlantType(String plant_type_code, String plant_type_desc) {
		  base.implicitwait();
		  this.planttypeCode=plant_type_code;
	      this.planttypeDesc=plant_type_desc;
	      base.itemsToBeDeleted.add(plant_type_code);
	      base.sendkeys(planttype.getPlantType_Code(), plant_type_code);
	      base.WaitUntil(planttype.getDescription());
	      base.sendkeys(planttype.getDescription(), plant_type_desc);
	      base.WaitUntil(planttype.getSubmit());
	  }

	  @Then("A popop with message Please enter all mandatory fields should be displayed in plant type page")
	  public void aPopupWithMessagePleaseEnterAllMandatoryFieldsShouldBeDisplayedInPlantTypePage() throws InterruptedException {
		  Thread.sleep(2000);   
		  base.implicitwait();
			  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		      String blankmessage = blnkmsg.getText();
	        assertEquals("Please Enter all mandatory fields",blankmessage);
	  }

	  
	  
//scenario outline:Testing functionalities
	  @Then("Clicking the close button in plant type")
	  public void clickingTheCloseButtonInPlantType() {
	     base.WaitUntilVisible(planttype.getCloseButton());
	     base.click(planttype.getCloseButton());
	  }
//edit
	  @Then("Checking the functionality of edit button by clicking in plant type")
	  public void checkingTheFunctionalityOfEditButtonByClickingInPlantType() {
		  base.implicitwait();
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(planttype.getSearchButton(),"12");
		  base.WaitUntilVisible(planttype.getEditIcon());
		  base.click(planttype.getEditIcon());
		  
		  base.WaitUntilVisible(planttype.getPlantType_Code());
		  planttype.getPlantType_Code().clear();
		  base.sendkeys(planttype.getPlantType_Code(), "13");
		  base.WaitUntilVisible(planttype.getDescription());
		  planttype.getDescription().clear();
		  base.sendkeys(planttype.getDescription(), "cmo");
		  base.click(planttype.getSubmit());
		  
	  }
	  
	  @Then("Checking the update button has shown in the plant type page")
	  public void checkingTheUpdateButtonHasShownInPlantTypePage() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(planttype.getSearchButton(),"13");
		  base.click(planttype.getEditIcon());
		  Thread.sleep(2000);
		  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String update = updatebtn.getText();
          //assertEquals("Update",update);
          base.click(planttype.getSubmit());
          
	  }
	  
	  @Then("The edited data should be available in database of plant type")
		public void theEditedDataShouldBeAvailableIndatabaseOfPlantType() throws InterruptedException {
			 base.databaseWaitTime();
		     Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("plant_type", "plant_type_code","13");
			 assertEquals("13",uniqueRowInTable.get("plant_type_code"));
		     
		}

//delete 
	  @Then("Checking the functionality of delete button by clicking in plant type")
	  public void checkingTheFunctionalityOfDeleteButtonByClickingInPlantType() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  base.implicitwait();
		  base.sendkeys(planttype.getSearchButton(),"12");
		  base.click(planttype.getDeleteIcon());
		  Thread.sleep(2000);
		  
	  }

	  
	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in plant type")
	  public void clickingDeleteButtonAndAPopupDeletedScuccessfullyShouldBeDisplayedInPlantType() throws InterruptedException {
		  base.WaitUntil(planttype.getDeleteIcon());
		  base.click(planttype.getDeleteIcon());
		  Thread.sleep(2000);
		  base.implicitwait();
//delete icon popup 
		  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
	      String dltmessage = deletemsg.getText();
          assertEquals("Are you Sure ?",dltmessage);
//delete msg popup        
          base.click(planttype.getDeleteButtonPopup());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
        
	       
	  }

	  @Then("Verifying that the entered data was deleted in database of plant type")
		public void verifyingThatTheEnteredDataWasDeletedInDatabaseOfPlantType() throws InterruptedException {
			Thread.sleep(2000);
			base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("plant_type","plant_type_code","12");
	       assertEquals("1",uniqueRowInTable.get("deleted"));
	       DBUtilities.deleteFromTable("plant_type","plant_type_code","12");
		}
	  
	  
	//search box
		@Then("Searching the Plant Type code by sending keys")
		public void searchingThePlantTypeCodeBySendingKeys() throws InterruptedException {
			base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.click(planttype.getSearchButton());
			base.sendkeys(planttype.getSearchButton(),"12");
		}

		@Then("The text in the search box should be equal to values in the Plant Type table")
		public void	theTextInTheSearchBoxShouldBeEqualToValuesInThePlantTypeTable() throws InterruptedException {
			Thread.sleep(1000);
			assertEquals(planttype.getSearchCheck().getText(),"12");
			base.databaseWaitTime();
			DBUtilities.deleteFromTable("plant_type","plant_type_code","12");
			
		}
	//Export button
		@Then("Clicking Export button in Plant Type master page")
		public void	clickingExportButtonInPlantTypeMasterPage() throws InterruptedException {
		 	base.WaitUntil(planttype.getExport());
		 	base.click(planttype.getExport());
		 	
		}



}
		  
		  

	  

