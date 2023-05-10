package org.spb.integrationtests.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.spb.integrationtests.BaseClass;
import org.spb.integrationtests.pageobjectmodel.QualityPOM;
import org.spb.integrationtests.utils.DBUtilities;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class QualityMaster {

	private static String url = "quality";
	  BaseClass base = new BaseClass();
	  QualityPOM qlty= new QualityPOM();
	  private String qualityCode;
	  private String qualityDesc;

	  public QualityMaster(BaseClass base) {
	    this.base = base;
	  }
	
	   
//scenario: test screen opens
	  @When("Delete data initially from database in Quality")
	  public void deletedatainitiallyfromdatabaseinquality() {
		
		  DBUtilities.deleteFromTable("quality","quality_code","QC06");
		  DBUtilities.deleteFromTable("quality","quality_code","QC07");
		  DBUtilities.deleteFromTable("quality","quality_code","qc08");
		  DBUtilities.deleteFromTable("quality","quality_code","QC08");
		  DBUtilities.deleteFromTable("quality","quality_code","Q001");
		  DBUtilities.deleteFromTable("product_group","product_group_code","PG25");
		  
	  }

	  @Given("A web browser is on the Quality  master page")
	  public void awebbrowserisonthequalitymasterpage() {
		  BaseClass.driver.get(BaseClass.baseURL + url);
		  base.alertaccept();
		  base.alertaccept();
	  }

	  @When("New Quality  button is clicked on page")
	  public void newqualitybuttonisclickedonpage() {
		  base.click(qlty.getNew_Quality());
	      base.WaitUntilVisible(qlty.getQuality_Code());
	  }

	  @Then("A popup with Quality  should be displayed when clicked")
	  public void apopupwithqualityshouldbedisplayedwhenclicked() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		   WebElement qualityPage=BaseClass.driver.findElement(By.xpath("//h5[@id='modalTitle']"));
	       String qualityPopup = qualityPage.getText();
	       assertEquals("Quality Master",qualityPopup);
	  }

	  

	  @Then("A popup with submit button has shown in Quality  page")
	  public void apopupwithsubmitbuttonhasshowninqualitypage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement submitbtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String submit = submitbtn.getText();
          assertEquals("Submit",submit);
	  }

//scenario outline: valid data
	  
	  @Given("New Quality  button is clicked")
	  public void newqualitybuttonisclicked() {
		  base.alertaccept();
		  base.alertaccept();
		  base.WaitUntil(qlty.getNew_Quality());
		  base.click(qlty.getNew_Quality());
	      base.WaitUntilVisible(qlty.getQuality_Code());
	  }

	  @Then("Entering the data in {string},{string} text box in Quality")
	  public void enteringthedataintextboxinquality(String quality_code, String quality_desc) {
		  base.implicitwait();
		  this.qualityCode=quality_code;
	      this.qualityDesc=quality_desc;
	      base.itemsToBeDeleted.add(quality_code);
	      base.WaitUntil(qlty.getQuality_Code());
	      base.sendkeys(qlty.getQuality_Code(), quality_code);
	      base.WaitUntil(qlty.getDescription());
	      base.sendkeys(qlty.getDescription(), quality_desc);
	      base.selectClass(qlty.getProduct_Group_Code_dropdown());
	      base.selectByVisibleText("PG25");
	      base.WaitUntil(qlty.getSubmit());
	  }

	  @When("Click on submit button in Quality  page")
	  public void clickonsubmitbuttoninqualitypage() {
	      base.click(qlty.getSubmit());
	  }

	  @Then("A popop with message successfully added should be displayed in Quality  page")
	  public void apopopwithmessagesuccessfullyaddedshouldbedisplayedinqualitypage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
		  WebElement validdata=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String successmsg = validdata.getText();
          assertEquals("Saved Successfully",successmsg);
	  }

	  @Then("The entered data is available in Quality  table")
	  public void theentereddataisavailableinqualitytable() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  HashMap<String, String> uniqueRowInTable =
			        DBUtilities.findUniqueRowInTable("quality","quality_code",this.qualityCode);
			    assertEquals(this.qualityDesc, uniqueRowInTable.get("description"));
	  }

//scenario outline: duplicate data
	  
	  @Given("Set data in Quality  table")
	  public void setdatainqualitytable() {
		  base.databaseWaitTime();
		  
		  HashMap<String, String> findUniqueRowInTable = DBUtilities.findUniqueRowInTable("product_group","product_group_code", "PG25");
	        String product_groupID = findUniqueRowInTable.get("id");
			System.out.println("ID is: " + product_groupID);
			
		  LinkedHashMap<String, String> values = new LinkedHashMap<>();
		  	Date date = new Date();
		  	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
		  	String currentDateTime = format.format(date);
		  	values.put("quality_code","QC07");
		  	values.put("description","Quality7");
		  	values.put("product_group_id",product_groupID);
		  	
		  	values.put("active","0");
		  	values.put("deleted","0");
		  	values.put("created_by","Integration Test");
		  	values.put("updated_by","Integration Test");
		  	values.put("created_on",(currentDateTime));
		  	values.put("updated_on",(currentDateTime));
		  	DBUtilities.insertIntoTable("quality", values);
	  }

	  @Then("Entering the duplicate data in {string},{string} text box in Quality")
	  public void enteringtheduplicatedataintextboxinquality(String quality_code, String quality_desc) {
		  base.implicitwait();
		  this.qualityCode=quality_code;
	      this.qualityDesc=quality_desc;
	      base.itemsToBeDeleted.add(quality_code);
	      base.WaitUntil(qlty.getQuality_Code());
	      base.sendkeys(qlty.getQuality_Code(), quality_code);
	      base.WaitUntil(qlty.getDescription());
	      base.sendkeys(qlty.getDescription(), quality_desc);
	      base.selectClass(qlty.getProduct_Group_Code_dropdown());
	      base.selectByVisibleText("PG25");
	      base.WaitUntil(qlty.getSubmit());
	  }

	  @Then("A popop with message already exists should be displayed in Quality  page")
	  public void apopopwithmessagealreadyexistsshouldbedisplayedinqualitypage() throws InterruptedException {
		  base.implicitwait();
		  Thread.sleep(2000);
		  WebElement errmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
		  String errormessage = errmsg.getText();
	      assertEquals("Already exist",errormessage);
	  }

//scenario outline:blank data
	  
	  @Then("Entering the blank data in {string},{string} text box in Quality")
	  public void enteringtheblankdataintextboxinquality(String quality_code, String quality_desc) {
		  base.implicitwait();
		  this.qualityCode=quality_code;
	      this.qualityDesc=quality_desc;
	      base.itemsToBeDeleted.add(quality_code);
	      base.WaitUntil(qlty.getQuality_Code());
	      base.sendkeys(qlty.getQuality_Code(), quality_code);
	      base.WaitUntil(qlty.getDescription());
	      base.sendkeys(qlty.getDescription(), quality_desc);
	      base.selectClass(qlty.getProduct_Group_Code_dropdown());
	      base.selectByVisibleText("PG25");
	      base.WaitUntil(qlty.getSubmit());
	  }

	  @Then("A popop with message Please enter all mandatory fields should be displayed in Quality  page")
	  public void apopopwithmessagepleaseenterallmandatoryfieldsshouldbedisplayedinqualitypage() throws InterruptedException {
		  Thread.sleep(2000);   
		  base.implicitwait();
		  WebElement blnkmsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String blankmessage = blnkmsg.getText();
	      assertEquals("Please Enter all mandatory fields",blankmessage);
	  }

//scenario outline:testing functionality of edit button
	  
	  @Given("Clicking the close button in Quality")
	  public void clickingtheclosebuttoninquality() {
		  base.alert();
		  base.alertaccept();
		  base.WaitUntilVisible(qlty.getCloseButton());
		     base.click(qlty.getCloseButton());
	  }

//edit
	  @Then("Checking the functionality of edit button and by clicking in Quality")
	  public void checkingthefunctionalityofeditbuttonandbyclickinginquality() throws InterruptedException {
		  base.alertaccept();
		  base.alertaccept();
		  Thread.sleep(2000);
		  base.implicitwait();
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(qlty.getSearchButton(),"QC07");
		  base.WaitUntilVisible(qlty.getEditIcon());
		  base.click(qlty.getEditIcon());
		  
		  base.WaitUntilVisible(qlty.getQuality_Code());
		  qlty.getQuality_Code().clear();
		  base.sendkeys(qlty.getQuality_Code(), "qc08");
		  base.WaitUntilVisible(qlty.getDescription());
		  qlty.getDescription().clear();
		  base.sendkeys(qlty.getDescription(), "quality8");
		  base.click(qlty.getSubmit());
	  }

	  @Then("Checking the update button has shown in the Quality  page")
	  public void checkingtheupdatebuttonhasshowninthequalitypage() throws InterruptedException {
		  Thread.sleep(2000);
		  base.refreshApp(BaseClass.baseURL+url);
		  base.sendkeys(qlty.getSearchButton(),"qc08");
		  base.WaitUntilVisible(qlty.getEditIcon());
		  base.click(qlty.getEditIcon());
		  WebElement updatebtn=BaseClass.driver.findElement(By.xpath("(//button[@class='btn btn-primary mr-2'])[1]"));
	      String update = updatebtn.getText();
          //assertEquals("Update",update);
	      base.click(qlty.getCloseButton());
          
	  }

	  @Then("The edited data should be available in database of Quality")
	  public void theediteddatashouldbeavailableindatabaseofquality() throws InterruptedException {
		  base.databaseWaitTime();
		     //Thread.sleep(2000);
			 HashMap<String, String> uniqueRowInTable =
		     DBUtilities.findUniqueRowInTable("quality", "quality_code","qc08");
			 assertEquals("qc08",uniqueRowInTable.get("quality_code"));
	  }

//scenario outline: testing functionality of delete button
	  
	  @Then("Checking the functionality of delete button by clicking in Quality")
	  public void checkingthefunctionalityofdeletebuttonbyclickinginquality() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
		  base.implicitwait();
		  Thread.sleep(2000);
		  base.sendkeys(qlty.getSearchButton(),"QC07");
		  base.WaitUntil(qlty.getDeleteIcon());
		  base.click(qlty.getDeleteIcon());
	  }

	  @Then("Clicking delete button and a popup deleted sucessfully should be displayed in Quality")
	  public void clickingdeletebuttonandapopupdeletedsucessfullyshouldbedisplayedinquality() throws InterruptedException {
		  Thread.sleep(2000);
		  base.implicitwait();
//delete icon popup 
		  WebElement deletemsg=BaseClass.driver.findElement(By.xpath("(//div[@class='modal-body'])[2]"));
	      String dltmessage = deletemsg.getText();
          assertEquals("Are you Sure ?",dltmessage);
//delete msg popup        
          base.click(qlty.getDeleteButtonPopup());
		  Thread.sleep(2000);
		  WebElement dltsccsflymsg=BaseClass.driver.findElement(By.xpath("//span[@id='errorMsg']"));
	      String deletesuccess = dltsccsflymsg.getText();
          assertEquals("Deleted Successfully",deletesuccess);
	  }

	  @Then("Verifying that the entered data was deleted in database of Quality")
	  public void verifyingthattheentereddatawasdeletedindatabaseofquality() throws InterruptedException {
		  Thread.sleep(2000);
		  base.databaseWaitTime();
			HashMap<String, String> uniqueRowInTable =
					DBUtilities.findUniqueRowInTable("quality","quality_type_code","qc08");
			assertEquals("1",uniqueRowInTable.get("deleted"));
			DBUtilities.deleteFromTable("quality","quality_code","qc08");
			
	  }

//search box
	  
	  @Then("Searching the Quality  code by sending keys")
	  public void searchingthequalitycodebysendingkeys() throws InterruptedException {
		  base.refreshApp(BaseClass.baseURL+url);
			//base.WaitUntilVisible(gsm.getSearchButton());
			Thread.sleep(2000);
			base.click(qlty.getSearchButton());
			base.sendkeys(qlty.getSearchButton(),"QC07");
	  }

	  @Then("The text in the search box should be equal to values in the Quality  table")
	  public void thetextinthesearchboxshouldbeequaltovaluesinthequalitytable() throws InterruptedException {
		  Thread.sleep(1000);
			assertEquals(qlty.getSearchCheck().getText(),"QC07");
			base.databaseWaitTime();
			DBUtilities.deleteFromTable("quality","quality_code","QC07");
	  }

	  @Then("Clicking Export button in Quality  master page")
	  public void clickingexportbuttoninqualitymasterpage() {
		  base.WaitUntil(qlty.getExport());
		 	base.click(qlty.getExport());
		 	
		 	DBUtilities.deleteFromTable("product_group","product_group_code","PG25");
		 	
	  }
}
