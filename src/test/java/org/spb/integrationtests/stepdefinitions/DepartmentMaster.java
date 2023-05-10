package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.DepartmentMasterPom;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DepartmentMaster {
	private static String url = "department";
	  BaseClass base = new BaseClass();
	  DepartmentMasterPom dept =new DepartmentMasterPom();
	  private String deptCode;
	  private String deptName;
	  public DepartmentMaster(BaseClass base) {
	    this.base = base;
	  }
	
//Scenario
	@Given("A web browser is on the DepartmentMaster page")
	public void aWebBrowserIsOnTheDepartmentMasterPage() throws InterruptedException {
		BaseClass.driver.get(BaseClass.baseURL + url);
		Thread.sleep(2000);
	}

	@When("Add new department button is clicked")
	public void addNewDepartmentButtonIsClicked() {
		 base.WaitUntil(dept.getNew_Department());
		 base.click(dept.getNew_Department());
	}

	@Then("Popup with title as Department should be displayed with Submit button in Department Page")
	public void popupWithTitleAsDepartmentShouldBeDisplayedWithSubmitButtonInDepartmenPage() throws InterruptedException {
		     base.implicitwait();
		    Thread.sleep(3000);
		    WebElement department=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
		    String deptPopup = department.getText();
	        assertEquals(deptPopup,"Department");
	        base.implicitwait();
	        String submittext = dept.getSubmit_DM().getText();
	        assertEquals("Submit",submittext);
	}
//Valid data
	@Then("Entering valid {string} and {string} in the input box in DepartmentPage")
	public void enteringValidAndInTheInputBoxInDepartmentPage(String dept_code, String dept_name) {
		this.deptCode=dept_code;
	    this.deptName=dept_name;
	    base.itemsToBeDeleted.add(dept_code);
	    base.implicitwait();
	    base.sendkeys(dept.getDepartment_code(),dept_code);
	    base.WaitUntilVisible(dept.getDepartment_Description());
	    base.sendkeys(dept.getDepartment_Description(),dept_name);
	    base.WaitUntilVisible(dept.getSubmit_DM());
	}

	@Then("Click the submit button in Department form")
	public void clickTheSubmitButtonInDepartmentForm() {
		base.click(dept.getSubmit_DM());
	}

	@Then("A Popup with saved successfully should be displayed should be displayed in DepartmentMaster Page")
	public void aPopupWithSavedSuccessfullyShouldDisplayedShouldBeDisplayedInDepartmentMasterPage() throws InterruptedException {
		Thread.sleep(2000);
		  base.implicitwait();
		  String validData = BaseClass.driver.switchTo().activeElement().getAttribute("id");
	        assertEquals(validData,"showAlert");
	}

	@Then("The entered data should be available in Department table")
	public void theEnteredDataShouldBeAvailableInDepartmentTable() {
		base.databaseWaitTime();
		HashMap<String, String> uniqueRowInTable =
	     DBUtilities.findUniqueRowInTable("department", "department_code", this.deptCode);
	      assertEquals(this.deptCode, uniqueRowInTable.get("department_code"));
	      assertEquals(this.deptName, uniqueRowInTable.get("department_name"));
	}
//Duplicate
	@Given("Set data in Department table")
	public void setDataInDepartmentTable() {
		base.databaseWaitTime();
        String departmentCode="MR";
        LinkedHashMap<String, String> value = new LinkedHashMap<>();
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        String currentDateTime = format.format(date);
        value.put("department_code",departmentCode);
        value.put("department_name","Marketing");
        value.put("active","0");
        value.put("deleted","0");
        value.put("created_by","Integration Test");
        value.put("updated_by","Integration Test");
        value.put("created_on",(currentDateTime));
        value.put("updated_on",(currentDateTime));
        DBUtilities.insertIntoTable("department",value);
	}

	@Then("Entering duplicate data in {string} and {string} in the input box in DepartmentForm")
	public void enteringDuplicateDataInAndInTheInputBoxInDepartmentForm(String dept_code, String dept_name) {
		this.deptCode=dept_code;
	    this.deptName=dept_name;
	    base.itemsToBeDeleted.add(dept_code);
	    base.implicitwait();
	    base.sendkeys(dept.getDepartment_code(),dept_code);
	    base.WaitUntilVisible(dept.getDepartment_Description());
	    base.sendkeys(dept.getDepartment_Description(),dept_name);
	    base.WaitUntilVisible(dept.getSubmit_DM());
	}

	@Then("A popup with message already exists data should be displayed in DepartmentPage")
	public void aPopupWithMessageAlreadyExistsDataShouldBeDisplayedInDepartmentPage() throws InterruptedException {
		Thread.sleep(3000);
		WebElement errormsg = BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		String errormessage = errormsg.getText();
		assertEquals("Duplicate Entry",errormessage);
	}

//Blank	

	@Then("Entering {string} and {string} in the input box with blank data in DepartmentForm")
	public void enteringAndInTheInputBoxWithBlankDataInDepartmentForm(String dept_code, String dept_name) {
		this.deptCode=dept_code;
	    this.deptName=dept_name;
	    base.implicitwait();
	    base.sendkeys(dept.getDepartment_code(),dept_code);
	    base.WaitUntilVisible(dept.getDepartment_Description());
	    base.sendkeys(dept.getDepartment_Description(),dept_name);
	    base.WaitUntilVisible(dept.getSubmit_DM());
	}

	@Then("A popup with message please enter all mandatory fields should be displayed in DepartmentPage")
	public void aPopupWithMessagePleaseEnterAllMandatoryFieldsShouldBeDisplayedInDepartmentPage() throws InterruptedException {
		Thread.sleep(3000);
		base.implicitwait();
		WebElement errormsg = BaseClass.driver.findElement(By.xpath("//div[@class='row']//span[@id='errorMsg']"));
		String errormessage = errormsg.getText();
		assertEquals(errormessage, "Please Enter all mandatory fields");
	}
//Edit
	@Then("Clicking the close button in DepartmentForm")
	public void clickingTheCloseButtonInDepartmentForm() {
	    base.implicitwait();
	    base.click(dept.getClose_DM());
	}
	
	@Then("Checking the functionality of edit button by clicking in DepartmentPage")
	public void checkingTheFunctionalityOfEditButtonByClickingInDepartmentPage() {
		base.refreshApp(BaseClass.baseURL+url);  
		base.WaitUntil(dept.getEdit_DM());
	    base.click(dept.getEdit_DM());
	    base.WaitUntilVisible(dept.getDepartment_code());
	    dept.getDepartment_code().clear();
	    base.sendkeys(dept.getDepartment_code(),"Sls");
	    base.WaitUntilVisible(dept.getDepartment_Description());
	    dept.getDepartment_Description().clear();
	    base.sendkeys(dept.getDepartment_Description(),"Sales");
	    
	}

	@Then("A Popup with title as Department should be displayed with update button in Department Page")
	public void aPopupWithTitleAsDepartmentShouldBeDisplayedWithUpdateButtonInDepartmentPage() throws InterruptedException {
		   base.implicitwait();
		   Thread.sleep(3000);
		   String submittext = dept.getSubmit_DM().getText();
	        assertEquals(submittext,"Update");
	        base.WaitUntilVisible(dept.getSubmit_DM());
		    base.click(dept.getSubmit_DM());
	}
	@Then("The edited data should be available in database of DepartmentMaster")
	public void theEditedDataShouldBeAvailableIndatabaseOfDepartmentMaster() throws InterruptedException {
		 base.databaseWaitTime();
	     Thread.sleep(3000);
		 HashMap<String, String> uniqueRowInTable =
	     DBUtilities.findUniqueRowInTable("department", "department_code","Sls");
		 assertEquals("Sls",uniqueRowInTable.get("depatment_code"));
	     
	}
//Delete
	@Then("Checking the functionality of delete icon button in DepartmentMaster Page")
	public void checkingtTheFunctionalityOfDeleteIconButtonInDepartmentMasterPage() throws InterruptedException {
		base.refreshApp(BaseClass.baseURL+url); 
		 base.implicitwait();
	     base.click(dept.getDelete_DM());
	     Thread.sleep(2000);
	     base.implicitwait();
	     base.implicitwait();
	     String delete = BaseClass.driver.switchTo().activeElement().getAttribute("id");
	     assertEquals(delete, "delete-modal");
	}

	@Then("Clicking delete button and a popup deleted sucessfully should be displayed in DepartmentPage")
	public void clickingDeleteButtonAndAPopupDeletedSucessfullyShouldBeDisplayedInDepartmentPage() throws InterruptedException {
		 base.click(dept.getPress_Delete());
	     base.databaseWaitTime();
	     Thread.sleep(2000);
	     WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	     String deletesuccess = dltsccsflymsg.getText();
	          assertEquals(deletesuccess,"Deleted Successfully");
	}

	@Then("Verifying that the enter data was deleted in department database")
	public void verifyingThatTheEnterDataWasDeletedInDepartmentDatabase() throws InterruptedException {
		Thread.sleep(2000);
		HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("department","department_code","Sls");
       assertEquals("1",uniqueRowInTable.get("deleted"));
	   DBUtilities.deleteFromTable("department","department_code","SLS");
	}
	
//search box
	@Then("Searching the Department code by sending keys")
	public void searchDepartmentCode() {
		base.refreshApp(BaseClass.baseURL+url);
		base.WaitUntilVisible(dept.getSearchDept());
		base.sendkeys(dept.getSearchDept(),"MR");
	}

	@Then("The text in the search box should be equal to values in the Departmenttable")
	public void	theTextinTheSearchBoxEqulstoValueInTable() throws InterruptedException {
		Thread.sleep(1000);
		assertEquals(dept.getSearchCheck().getText(),"MR");
		base.databaseWaitTime();
		DBUtilities.deleteFromTable("department","department_code","MR");
		
	}
//Export button
	@Then("Clicking Export button in DepartmentMaster page")
	public void	clickingExportbutton() throws InterruptedException {
	 	base.WaitUntil(dept.getExport_DM());
	 	base.click(dept.getExport_DM());
	 	
	}

}
