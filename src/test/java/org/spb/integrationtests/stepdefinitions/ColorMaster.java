package org.spb.integrationtests.stepdefinitions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.utils.DBUtilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class ColorMaster {
	private static String url = "colors";
	  BaseClass base = new BaseClass();
	  org.spb.integrationtests.pageobjectmodel.ColorMaster color =
	      new org.spb.integrationtests.pageobjectmodel.ColorMaster();
	  private String colorCode;
	  private String colorDesc;
	  public ColorMaster(BaseClass base) {
	    this.base = base;
	  }

//scenario
@Given("A web browser is on the ColorMaster page")
public void aWebBrowserIsOnTheColorMasterPage() {
//	base.maximumPageLoadTime();
	BaseClass.driver.get(BaseClass.baseURL + url);
}

@When("Add new color button is clicked")
public void addNewColorButtonIsClicked() {
	base.click(color.getNewColour());
    base.WaitUntilVisible(color.getColourCode());
}

@Then("Popup with id as entity-modal should be displayed with Submit button")
public void popupWithIdAsEntityModalShouldBeDisplayed() throws InterruptedException {
    base.implicitwait();
    Thread.sleep(2000);
    String text = BaseClass.driver.switchTo().activeElement().getAttribute("id");
    assertEquals(text,"entity-modal");
    base.implicitwait();
    Thread.sleep(1000);
    String submittext = color.getSubmitColourCreation().getText();
    assertEquals("Submit",submittext);
}

//Test with valid data
@Then("Entering valid {string} and {string} in the input box")
public void enteringValidAndInTheInputBox(String color_code,String color_desc) {
    this.colorCode=color_code;
    this.colorDesc=color_desc;
    base.itemsToBeDeleted.add(color_code);
    base.implicitwait();
    base.sendkeys(color.getColourCode(), color_code);
    base.WaitUntilVisible(color.getColourDesc());
    base.sendkeys(color.getColourDesc(), color_desc);
    base.WaitUntilVisible(color.getSubmitColourCreation());
   
}

@Then("Click the submit button")
public void clickTheSubmitButton() {
	base.click(color.getSubmitColourCreation());
}

@Then("The entered data should be available in color table")
public void theEnteredDataShouldBeAvailableInColorTable() throws InterruptedException {
	base.databaseWaitTime();
	HashMap<String, String> uniqueRowInTable =
     DBUtilities.findUniqueRowInTable("color", "color_code", this.colorCode);
      assertEquals(this.colorDesc, uniqueRowInTable.get("description"));
}

//Test with duplicate data
@Given("Set data in color table")
public void SetDataInColorTable() {
	base.databaseWaitTime();
	LinkedHashMap<String, String> values = new LinkedHashMap<>();
	Date date = new Date();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	String currentDateTime = format.format(date);
	values.put("color_code","BK");
	values.put("description","Black");
	values.put("active","0");
	values.put("deleted","0");
	values.put("created_by","Integration Test");
	values.put("updated_by","Integration Test");
	values.put("created_on",(currentDateTime));
	values.put("updated_on",(currentDateTime));
	DBUtilities.insertIntoTable("color", values);
	
}

@Then("Entering duplicate data in {string} and {string} in the input box")
public void enteringDuplicateDataInAndInTheInputBox(String color_code, String color_desc) {
	this.colorCode=color_code;
    this.colorDesc=color_desc;
    base.itemsToBeDeleted.add(color_code);
    base.implicitwait();
    base.WaitUntilVisible(color.getColourCode());
    base.sendkeys(color.getColourCode(), color_code);
    base.WaitUntilVisible(color.getColourDesc());
    base.sendkeys(color.getColourDesc(), color_desc);
    base.WaitUntilVisible(color.getSubmitColourCreation());
}

@Then("A popup with message already exists data should be displayed")
public void aPopupWithMessageAlreadyExistsDataShouldBeDisplayed() throws InterruptedException {
	Thread.sleep(3000);
	WebElement errormsg = BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	String errormessage = errormsg.getText();
    assertEquals("Duplicate Entry",errormessage);
}

//blank datas
@Then("Entering {string} and {string} in the input box with blank data")
public void enteringAndInTheInputBoxWithBlankData(String color_code, String color_desc) {
	this.colorCode=color_code;
    this.colorDesc=color_desc;
    base.itemsToBeDeleted.add(color_code);
    base.WaitUntil(color.getColourCode());
    base.sendkeys(color.getColourCode(), color_code);
    base.WaitUntilVisible(color.getColourDesc());
    base.sendkeys(color.getColourDesc(), color_desc);
    base.WaitUntilVisible(color.getSubmitColourCreation());
}


@Then("A popup with message please enter all mandatory fields should be displayed")
public void aPopupWithMessagePleaseEnterAllMandatoryFieldsShouldBeDisplayed() throws InterruptedException {
	Thread.sleep(3000);
	base.implicitwait();
	WebElement errormsg = BaseClass.driver.findElement(By.xpath("//div[@class='row']//span[@id='errorMsg']"));
	String errormessage = errormsg.getText();
	assertEquals(errormessage, "Please Enter all mandatory fields");
}

//Checking Functionalities of Edit buttons
@Then("Clicking the close button")
public void clickingTheCloseButton() {
	 base.implicitwait();
	 base.click(color.getCloseCreateColour());
}

@Then("Checking the functionality of edit button by clicking")
public void checkingTheFunctionalityOfEditButtonByClicking() throws InterruptedException {
	base.refreshApp(BaseClass.baseURL+url);  
	base.implicitwait();
    base.click(color.getEditIcon());
    base.WaitUntil(color.getColourCode());
    color.getColourCode().clear();
    base.sendkeys(color.getColourCode(),"RD");
    base.WaitUntilVisible(color.getColourDesc());
    color.getColourDesc().clear();
    base.sendkeys(color.getColourDesc(),"Red");
    base.WaitUntilVisible(color.getSubmitColourCreation());
    base.click(color.getSubmitColourCreation());
    
      
}
@Then("The edited data should be available in database of ColorMaster")
public void the_edited_data_should_be_available_in_database_of_colormaster() throws InterruptedException {
	 base.databaseWaitTime();
     Thread.sleep(3000);
	 HashMap<String, String> uniqueRowInTable =
     DBUtilities.findUniqueRowInTable("color", "color_code","RD");
	 assertEquals("RD",uniqueRowInTable.get("color_code"));
      assertEquals("Red", uniqueRowInTable.get("description"));
}
//Delete button(after edit froom BK to RD)

@Then("Checking the functionality of delete icon button in ColorMaster Page")
public void checkingTheFunctionalityOfDeleteButtonInColorMasterPage() throws InterruptedException {
	base.refreshApp(BaseClass.baseURL+url); 
	 base.implicitwait();
     base.click(color.getDeleteIcon());
     Thread.sleep(2000);
     base.implicitwait();
	
}
@Then("Clicking delete button and a popup deleted sucessfully should be displayed")
public void clickingDeleteButtonAndaPopupDeletedSucessfullyShouldBeDisplayed() throws InterruptedException {
	 base.implicitwait();
     base.click(color.getDeleteIcon());
     Thread.sleep(2000);
     base.implicitwait();
     String delete = BaseClass.driver.switchTo().activeElement().getAttribute("id");
     assertEquals(delete, "delete-modal");
     Thread.sleep(2000);
     base.click(color.getPressDelete());
     base.databaseWaitTime();
     Thread.sleep(2000);
     WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
     String deletesuccess = dltsccsflymsg.getText();
          assertEquals(deletesuccess,"Deleted Successfully");
      HashMap<String, String> uniqueRowInTable =
				DBUtilities.findUniqueRowInTable("color","color_code","RD");
     assertEquals("1",uniqueRowInTable.get("deleted"));
     DBUtilities.deleteFromTable("color","color_code","RD");
}
//search box
@Then("Searching the Color code by sending keys")
public void searchLegalCode() {
	base.refreshApp(BaseClass.baseURL+url);
	base.WaitUntilVisible(color.getSearchColor());
	base.sendkeys(color.getSearchColor(),"BK");
}

@Then("The text in the search box should be equal to values in the Colortable")
public void	theTextinTheSearchBoxEqulstoValueInTable() throws InterruptedException {
	Thread.sleep(1000);
	assertEquals(color.getSearchCheck().getText(),"BK");
	base.databaseWaitTime();
	DBUtilities.deleteFromTable("color","color_code","BK");
	
}
//Export button
@Then("Clicking Export button in ColorMaster page")
public void	clickingExportbutton() throws InterruptedException {
 	base.WaitUntil(color.getExport());
 	base.click(color.getExport());
 	
}

   
}



