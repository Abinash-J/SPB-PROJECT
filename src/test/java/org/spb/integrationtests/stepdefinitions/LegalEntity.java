package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.LegalEntityMasterPom;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class LegalEntity {
	private static String url = "legalentity";
	  BaseClass base = new BaseClass();
	  LegalEntityMasterPom legal=new LegalEntityMasterPom();
	  
	  private String legalEntityCode;
	  
	  private String legalEntityName;
	  public LegalEntity(BaseClass base) {
	    this.base = base;
	  }
	@Given("A web browser is on the LegalEntity page")
	public void aWebBrowserIsOnTheLegalEntityPage() throws InterruptedException {
		BaseClass.driver.get(BaseClass.baseURL + url);
		Thread.sleep(2000);
	}

    @When("New Legal Entity button is clicked")
    public void newLegalEntityButtonIsClicked() {
    	base.WaitUntil(legal.getNewLegalEntity());
    	base.click(legal.getNewLegalEntity());
	    
    }

	@Then("A popup with id as entity-modal should be displayed with Submit button in LegalEntityPage")
	public void aPopupWithIdAsEntityModalShouldBeDisplayed() throws InterruptedException {
		base.implicitwait();
	    Thread.sleep(2000);
	    String text = BaseClass.driver.switchTo().activeElement().getAttribute("id");
	    assertEquals(text,"entity-modal");
	    base.implicitwait();
	    Thread.sleep(1000);
	    String submittext = legal.getSubmit_LEM().getText();
	    assertEquals("Submit",submittext);
	}
//valid data
	@Then("Entering valid {string} and {string} in the LegalEntity form input box")
	public void enteringValidAndInTheLegalEntityFormInputBox(String legal_entity_code, String legal_entity_name) throws InterruptedException {
		    this.legalEntityCode=legal_entity_code;
	        this.legalEntityName=legal_entity_name;
	        base.itemsToBeDeleted.add(legal_entity_code);
	        base.implicitwait();
	        Thread.sleep(1000);
	        base.selectClass(legal.getSelectGroupCode());
	        base.selectByVisibleText("IN111");
	        base.WaitUntil(legal.getLegalEntityCode());  
	        base.sendkeys(legal.getLegalEntityCode(),legal_entity_code);
	        base.sendkeys(legal.getLegalName(),legal_entity_name);
	
	}
	
	@Then("Click the LegalEntity-submit button")
	public void clickTheLegalEntitySubmitButton() {
		    base.WaitUntil(legal.getSubmit_LEM());
	        base.click(legal.getSubmit_LEM());
	}

	@Then("The entered data should be available in LegalEntity table")
	public void theEnteredDataShouldBeAvailableInLegalEntityTable() throws InterruptedException {
	     base.databaseWaitTime();
	     Thread.sleep(3000);
		HashMap<String, String> uniqueRowInTable =
	     DBUtilities.findUniqueRowInTable("legal_entity", "entity_code", this.legalEntityCode);
		 assertEquals(this.legalEntityCode,uniqueRowInTable.get("entity_code"));
	      assertEquals(this.legalEntityName, uniqueRowInTable.get("name"));
	}
//Duplicate data
	@Given("Set data in LegalEntity table")
	public void setDataInLegalEntityTable() {
	        HashMap<String, String> findUniqueRowInTable = DBUtilities.findUniqueRowInTable("org_group","group_code", "IN111");
	        String org_group_id = findUniqueRowInTable.get("id");
		    base.databaseWaitTime();
		    LinkedHashMap<String, String> values = new LinkedHashMap<>();
	        Date date = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	        String currentDateTime = format.format(date);
	        values.put("entity_code","002");
	        values.put("name","Paper");
	        values.put("org_group_id", org_group_id);
	        values.put("active","0");
	        values.put("deleted","0");
	        values.put("created_by","Integration Test");
	        values.put("updated_by","Integration Test");
	        values.put("created_on",(currentDateTime));
	        values.put("updated_on",(currentDateTime));
	        base.databaseWaitTime();
	        DBUtilities.insertIntoTable("legal_entity", values);
	}

	@Then("Entering duplicate data in {string} and {string} in the LegalEntity form input box")
	public void enteringDuplicateDataInAndInTheLegalEntityFormInputBox(String legal_entity_code, String legal_entity_name) {
		 this.legalEntityCode=legal_entity_code;
	        this.legalEntityName=legal_entity_name;
	        base.itemsToBeDeleted.add(legal_entity_code);
	        base.implicitwait();
	        base.WaitUntilVisible(legal.getSelectGroupCode());
	        base.selectClass(legal.getSelectGroupCode());
	        base.selectByVisibleText("IN111");
	        base.WaitUntil(legal.getLegalEntityCode());  
	        base.sendkeys(legal.getLegalEntityCode(), legal_entity_code);
	        base.implicitwait();
	        base.sendkeys(legal.getLegalName(), legal_entity_name);
	}

	@Then("A popup with message already exists data should be displayed for adding duplicate LegalEntityCode")
	public void aPopupWithMessageAlreadyExistsDataShouldBeDisplayedForAddingDuplicateLegalEntityCode() throws InterruptedException {
		Thread.sleep(3000);
		base.implicitwait();
		WebElement errormsg = BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		String errormessage = errormsg.getText();
		assertEquals("Duplicate Entry",errormessage);
	}
//blank data
    @Then("Entering blank data in {string} and {string} in the LegalEntity form input box")
	public void enteringBlankDataInTheLegalEntityFormInputBox(String legal_entity_code, String legal_entity_name) throws InterruptedException {
    	   this.legalEntityCode=legal_entity_code;
	        this.legalEntityName=legal_entity_name;
	        Thread.sleep(2000);
	        base.WaitUntil(legal.getLegalEntityCode());  
	        base.sendkeys(legal.getLegalEntityCode(), legal_entity_code);
	        base.sendkeys(legal.getLegalName(), legal_entity_name);
	}

	@Then("A popup with message please enter all mandatory fields for LegalEntity should be displayed")
	public void aPopupWithMessagePleaseEnterAllMandatoryFieldsForLegalEntityShouldBeDisplayed() throws InterruptedException {
		Thread.sleep(3000);
		base.implicitwait();
		WebElement errormsg = BaseClass.driver.findElement(By.xpath("//div[@class='row']//span[@id='errorMsg']"));
		Thread.sleep(1000);
		String errormessage = errormsg.getText();
		base.databaseWaitTime();
		assertEquals(errormessage, "Please Enter all mandatory fields");
	}
//Edit icon

	@Then("Clicking the LegalEntity form close button")
	public void clickingTheLegalEntityFormCloseButton() {
    System.out.println("Checking legal entity form close button");
	    base.implicitwait();
	    base.click(legal.getLEMClose());
	}

	@Then("Checking the functionality of LegalEntity edit button by clicking")
	public void checkingTheFunctionalityOfLegalEntityEditButtonByClicking() {
    System.out.println("Checking legal entity edit button");
		base.implicitwait();
		base.click(legal.getEditIcon());
	    base.WaitUntilVisible(legal.getSelectGroupCode());
	    base.selectClass(legal.getSelectGroupCode());
	    base.selectByVisibleText("IN111");
		base.WaitUntilVisible(legal.getLegalEntityCode());
		legal.getLegalEntityCode().clear();
		base.sendkeys(legal.getLegalEntityCode(),"003");
		base.WaitUntilVisible(legal.getLegalName());
		legal.getLegalName().clear();
		base.sendkeys(legal.getLegalName(),"Legal01");
		base.implicitwait();
	    base.click(legal.getSubmit_LEM());
	}
	@Then("The edited data should be available in database of legalentity")
	public void the_edited_data_should_be_available_in_database_of_legalentity() throws InterruptedException {
		 base.databaseWaitTime();
	     Thread.sleep(3000);
		 HashMap<String, String> uniqueRowInTable =
	     DBUtilities.findUniqueRowInTable("legal_entity", "entity_code","003");
		 assertEquals("003",uniqueRowInTable.get("entity_code"));
	      assertEquals("Legal01", uniqueRowInTable.get("name"));
	}
//delete icon after edited 002 to 003
	@Then("Checking the functionality of delete icon button in LegalEntityMaster Page")
	public void checkingTheFunctionalityOfDeleteIconButtonInLegalEntityMasterPage() throws InterruptedException {
		 base.implicitwait();
		 base.databaseWaitTime();
	     base.click(legal.getDeleteIcon());
	    
	}
	
   
	@Then("Clicking delete button and a popup deleted sucessfully should be displayed in LegalEntity page")
	public void clickingDeleteButtonAndAPopupDeletedSucessfullyShouldBeDisplayedInLegalEntityPage() throws InterruptedException {
		base.implicitwait();
		Thread.sleep(1000);
		String delete = BaseClass.driver.switchTo().activeElement().getAttribute("id");
		assertEquals(delete, "delete-modal");
		base.click(legal.getPressDelete());
		WebElement deletedSuccess = BaseClass.driver.findElement(By.xpath("//div[@class='modal-body']//span[@id='errorMsg']"));
		String deletedMessage = deletedSuccess.getText();
		assertEquals(deletedMessage, "Deleted Successfully");
		Thread.sleep(1000);
		HashMap<String, String> uniqueRowInTable =
				DBUtilities.findUniqueRowInTable("legal_entity", "entity_code", "003");
		assertEquals("1",uniqueRowInTable.get("deleted"));
		DBUtilities.deleteFromTable("legal_entity","entity_code","003");
		
		
	}
	
//Search box
	@Then("Searching the legal code by sending keys")
	public void searchLegalCode() {
		base.refreshApp(BaseClass.baseURL+url);
		base.WaitUntilVisible(legal.getSearchLegal());
		base.sendkeys(legal.getSearchLegal(),"002");
	}
	
	@Then("The text in the search box should be equal to values in the table")
	public void	theTextinTheSearchBoxEqulstoValueInTable() throws InterruptedException {
		Thread.sleep(1000);
		assertEquals(legal.getSearchCheck().getText(),"002");
		base.databaseWaitTime();
		DBUtilities.deleteFromTable("legal_entity","entity_code","002");
		
	}
//Export button
	@Then("Clicking Export button in legalentity page")
	public void	clickingExportbutton() throws InterruptedException {
     	base.WaitUntil(legal.getExport());
     	base.click(legal.getExport());
     	
	}
	
	
   
			
   }
