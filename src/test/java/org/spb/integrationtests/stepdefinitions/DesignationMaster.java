package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.DesignationMasterPom;

import org.spb.integrationtests.utils.DBUtilities;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DesignationMaster {
	 private static String url = "designation";
	  BaseClass base = new BaseClass();
	  
	  DesignationMasterPom designation=new DesignationMasterPom();
	  
	  private String designationCode;
	  
	  private String designationName;
	  
	  public DesignationMaster(BaseClass base) {
	    this.base = base;
	  }
	
//Scenario
	@Given("A web browser is on the DesignationMaster page")
	public void aWebBrowserIsOnTheDesignationMasterPage() throws InterruptedException {
		BaseClass.driver.get(BaseClass.baseURL + url);
		Thread.sleep(2000);
	}

	@When("Add new designation button is clicked")
	public void addNewDesignationButtonIsClicked() {
	    base.WaitUntil(designation.getNew_Designation());
	    base.click(designation.getNew_Designation());
    System.out.println("Clicked new designation master");
	}

	@Then("Popup with id as entity-modal should be displayed in Designation page with Submit button")
	public void popupWithIdAsEntityModalShouldBeDisplayedInDesignationPage() throws InterruptedException {
		
		base.implicitwait();
	    Thread.sleep(2000);
	    String text = BaseClass.driver.switchTo().activeElement().getAttribute("id");
	    assertEquals(text,"entity-modal");
	    base.implicitwait();
	    Thread.sleep(1000);
	    String submittext = designation.getSubmit_DSGN().getText();
	    assertEquals("Submit",submittext);
	}
	
//Valid
	@Then("Entering valid {string} and {string} in the input box of DesignationMaster form")
	public void enteringValidAndInTheInputBoxOfDesgnationMasterForm(String designation_code, String designation_name) {
		   this.designationCode=designation_code;
	        this.designationName=designation_name;
	        base.itemsToBeDeleted.add(designation_code);
	        base.WaitUntilVisible(designation.getDepartment());
	        base.selectClass(designation.getDepartment());
	        base.selectByVisibleText("MFG");
	        base.WaitUntil(designation.getDesignation_code());  
	        base.sendkeys(designation.getDesignation_code(), designation_code);
	        base.sendkeys(designation.getDesignation_Name(),designation_name);
	}

	@Then("Click the submit button for submitting the DesignationMaster Form")
	public void clickTheSubmitButtonForSubmittingTheDesignationMasterForm() {
		    base.WaitUntilVisible(designation.getSubmit_DSGN());
	        base.click(designation.getSubmit_DSGN());
	}

	@Then("The entered data should be available in Designation table")
	public void theEnteredDataShouldBeAvailableInDesignationTable() throws InterruptedException {
		 base.databaseWaitTime();
	     Thread.sleep(3000);
		 HashMap<String, String> uniqueRowInTable =
	     DBUtilities.findUniqueRowInTable("designation", "designation_code", this.designationCode);
	      assertEquals(this.designationName, uniqueRowInTable.get("designation_name"));
	}
//Duplicate
	
	@Given("Set data in designation table")
	public void setDataInDesignationTable() {
		 base.databaseWaitTime();
		
	      HashMap<String, String> findUniqueRowInTable = DBUtilities.findUniqueRowInTable("department","department_code", "MFG");
	        String department_id = findUniqueRowInTable.get("id");
			System.out.println("ID is: " + department_id);
		
		base.databaseWaitTime();
		    LinkedHashMap<String, String> values = new LinkedHashMap<>();
	        Date date = new Date();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
	        String currentDateTime = format.format(date);
	        values.put("designation_code","Opr");
	        values.put("designation_name","Operator");
	        values.put("department_id",department_id);
	        values.put("active","0");
	        values.put("deleted","0");
	        values.put("created_by","Integration Test");
	        values.put("updated_by","Integration Test");
	        values.put("created_on",(currentDateTime));
	        values.put("updated_on",(currentDateTime));
	        base.databaseWaitTime();
	        DBUtilities.insertIntoTable("designation",values);
	}

	@Then("Entering duplicate {string} and {string} in the input box of DesignationMaster form")
	public void enteringDuplicateAndInTheInputBoxOfDesgnationMasterForm(String designation_code, String designation_name) {
		   this.designationCode=designation_code;
	        this.designationName=designation_name;
	        base.itemsToBeDeleted.add(designation_code);
	        base.WaitUntilVisible(designation.getDepartment());
	        base.databaseWaitTime();
	        base.selectClass(designation.getDepartment());
	        base.selectByVisibleText("MFG");
	        base.WaitUntil(designation.getDesignation_code());  
	        base.sendkeys(designation.getDesignation_code(), designation_code);
	        base.sendkeys(designation.getDesignation_Name(),designation_name);
	    
	}

	@Then("A popup with message already exists data should be displayed in DesignationMaster page")
	public void aPopupWithMessageAlreadyExistsDataShouldBeDisplayedInDesignationMasterPage() throws InterruptedException {
		
		Thread.sleep(3000);
		base.implicitwait();
		WebElement errormsg = BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		String errormessage = errormsg.getText();
		assertEquals("Duplicate Entry",errormessage);
	}
//blank datas
	 @Then("Entering {string} and {string} in the input box with blank data in DesignationMaster form")
     public void enteringBlankDataInTheDesignationFormInputBox(String designation_code, String designation_name) throws InterruptedException {
		   this.designationCode=designation_code;
	        this.designationName=designation_name;
	        base.WaitUntilVisible(designation.getDepartment());
	        base.WaitUntil(designation.getDesignation_code());  
	        base.sendkeys(designation.getDesignation_code(), designation_code);
	        base.sendkeys(designation.getDesignation_Name(),designation_name);
	
	 }
	@Then("A popup with message please enter all mandatory fields should be displayed in DesignationMaster page")
	public void aPopupWithMessagePleaseEnterAllMandatoryFieldsShouldBeDisplayedInDesignationMasterPage() throws InterruptedException {
		
		Thread.sleep(3000);
		base.implicitwait();
		WebElement errormsg = BaseClass.driver.findElement(By.xpath("//div[@class='row']//span[@id='errorMsg']"));
		String errormessage = errormsg.getText();
		assertEquals(errormessage, "Please Enter all mandatory fields");
	}
//Functionality
	
	@Then("Clicking the close button of Designationform")
	public void clickingTheCloseButtonOfDesignationForm() {
		
        base.implicitwait();
	    base.click(designation.getDesignationClose());
	}
     
	@Then("Checking the functionality of Designation edit button by clicking")
	public void checkingTheFunctionalityOfDesignationEditButtonByClicking() {
		    base.implicitwait();
		    base.refreshApp(BaseClass.baseURL+url);
	        base.click(designation.getDesignationEditIcon());
	        base.selectClass(designation.getDepartment());
	        base.selectByVisibleText("MFG");
	        base.WaitUntil(designation.getDesignation_code());
	        designation.getDesignation_code().clear();
	        base.sendkeys(designation.getDesignation_code(),"Mgr");
	        designation.getDesignation_Name().clear();
	        base.sendkeys(designation.getDesignation_Name(),"Manager");
	        base.implicitwait();
	        base.databaseWaitTime();
	        base.click(designation.getSubmit_DSGN());
	}
	@Then("The edited data should be available in database of DesignationMaster")
	public void theEditedDataShouldBeAvailableIndatabaseOfDesignationMaster() throws InterruptedException {
		 base.databaseWaitTime();
	     Thread.sleep(3000);
		 HashMap<String, String> uniqueRowInTable =
	     DBUtilities.findUniqueRowInTable("designation","designation_code","Mgr");
		 assertEquals("Mgr",uniqueRowInTable.get("designation_code"));
	     
	}

//Delete Button	after edit from opr to Mgr
	
	@Then("Checking the functionality of delete icon button in DesignationMaster Page")
	public void checkingTheFunctionalityOfDeleteIconButtonInDesignationMasterPagee() throws InterruptedException {
		  base.implicitwait();
		  base.databaseWaitTime();
	      base.click(designation.getDesignationDeleteIcon());
	      Thread.sleep(2000);
	}

	@Then("Clicking delete button and a popup deleted sucessfully should be displayed in Designation page")
	public void clickingDeleteButtonAndA_popupDeletedSucessfullyShouldBeDisplayedInDesignationPage() throws InterruptedException {
		  base.implicitwait();
	      String delete = BaseClass.driver.switchTo().activeElement().getAttribute("id");
	      assertEquals(delete, "delete-modal");
	      base.click(designation.getDesignationPressDelete());
	      Thread.sleep(3000);
		     WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		     String deletesuccess = dltsccsflymsg.getText();
		          assertEquals(deletesuccess,"Deleted Successfully");
	      
	      HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("designation","designation_code","Mgr");
	      assertEquals("1",uniqueRowInTable.get("deleted"));
	      base.databaseWaitTime();
	      DBUtilities.deleteFromTable("designation","designation_code","Mgr");
	     
	}
	//search box
		@Then("Searching the Designationcode by sending keys")
		public void searchDesignationCode() {
			base.refreshApp(BaseClass.baseURL+url);
			base.WaitUntilVisible(designation.getSearchCode());
			base.sendkeys(designation.getSearchCode(),"Opr");
		}

		@Then("The text in the search box should be equal to values in the Designationtable")
		public void	theTextinTheSearchBoxEqulstoValueInTable() throws InterruptedException {
			Thread.sleep(1000);
			assertEquals(designation.getSearchCheck().getText(),"Opr");
			base.databaseWaitTime();
			DBUtilities.deleteFromTable("designation","designation_code","Opr");
			
		}
	//Export button
		@Then("Clicking Export button in DesignationMaster page")
		public void	clickingExportbutton() throws InterruptedException {
		 	base.WaitUntil(designation.getExport_DSGN());
		 	base.click(designation.getExport_DSGN());
		 	
		}

}
