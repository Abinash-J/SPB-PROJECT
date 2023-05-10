package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;


public class DesignationMasterPom {
	
	public DesignationMasterPom() {
		
		PageFactory.initElements(BaseClass.driver,this);

      }
       @FindBy(xpath="//a[@class='btn btn-primary font-weight-bolder']")
	   private WebElement New_Designation;
       
	   
	   @FindBy(xpath="//div[@class='col-lg-4']//select[@id='department']")
	   private WebElement Department;
	   
	   @FindBy(id="designation_code")
	   private WebElement Designation_code;
	   
	   @FindBy(xpath="//input[@id='designation_name']")
	   private WebElement Designation_Name;
	   
	   @FindBy(xpath="(//button[@class='btn btn-primary mr-2'])[1]")
	   private WebElement Submit_DSGN;
	   
	   @FindBy(xpath="//button[@class='btn btn-light-primary font-weight-bolder dropdown-toggle']")
	   private WebElement Export_DSGN;
	   
	   @FindBy(xpath="(//span[@class='nav-text'])[1]")
	   private WebElement Print_DSGN;
	   
	   @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[1]")
	   private WebElement designationClose;
	   
	   @FindBy(xpath="//i[@class='la la-edit']")
	   private WebElement designationEditIcon;
		
	   @FindBy(xpath="//a//i[@class='la la-trash']")
	   private WebElement designationDeleteIcon;
		
	   @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[2]")
	   private WebElement designationPressDelete;
	   
	   @FindBy(xpath="//input[@class='form-control form-control-sm']")
	   private WebElement SearchCode;
	   
       @FindBy(xpath="//table//tr//td[2]")
	   private WebElement SearchCheck;

	public WebElement getNew_Designation() {
		return New_Designation;
	}

	public WebElement getDepartment() {
		return Department;
	}

	public WebElement getDesignation_code() {
		return Designation_code;
	}

	public WebElement getDesignation_Name() {
		return Designation_Name;
	}

	public WebElement getSubmit_DSGN() {
		return Submit_DSGN;
	}

	public WebElement getExport_DSGN() {
		return Export_DSGN;
	}

	public WebElement getPrint_DSGN() {
		return Print_DSGN;
	}

	public WebElement getDesignationClose() {
		return designationClose;
	}

	public WebElement getDesignationEditIcon() {
		return designationEditIcon;
	}

	public WebElement getDesignationDeleteIcon() {
		return designationDeleteIcon;
	}

	public WebElement getDesignationPressDelete() {
		return designationPressDelete;
	}
	public WebElement getSearchCode() {
        return SearchCode;
     }
     public WebElement getSearchCheck() {
        return SearchCheck;
     }
	   
	   

	}
