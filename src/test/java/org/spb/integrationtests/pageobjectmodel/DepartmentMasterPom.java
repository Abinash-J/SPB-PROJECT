package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;

public class DepartmentMasterPom {
	
	public DepartmentMasterPom() {
		PageFactory.initElements(BaseClass.driver,this);

}
	
	//Employee_Setup-->Department_Master
	   
	   @FindBy(xpath="//a[@class='btn btn-primary font-weight-bolder']")
	   private WebElement New_Department;
	   @FindBy(xpath="(//input[@id='department_code'])[1]")
	   private WebElement Department_code;
	   @FindBy(xpath="(//input[@id='department_name'])[1]")
	   private WebElement Department_Description;
	   @FindBy(xpath="(//button[@class='btn btn-primary mr-2'])[1]")
	   private WebElement Submit_DM;
	   @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[1]")
	   private WebElement Close_DM;
	   @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bolder dropdown-toggle'])[1]")
	   private WebElement Export_DM;
	   @FindBy(xpath="(//span[@class='navi-text'])[1]")
	   private WebElement Print_DM;
	   @FindBy (xpath="//div[@class='btn-group ']//i[@class='la la-edit']")
	   private WebElement Edit_DM;
	   @FindBy (xpath="//div[@class='btn-group ']//i[@class='la la-trash']")
	   private WebElement Delete_DM;
	   @FindBy(xpath="(//div[@class='modal-content']//button[@class='btn btn-light-primary font-weight-bold'])[2]")
	   private WebElement Press_Delete;
	   @FindBy(xpath="//input[@class='form-control form-control-sm']")
	   private WebElement SearchDept;
       @FindBy(xpath="(//table//td)[1]")
	   private WebElement SearchCheck;
	
	
	public WebElement getNew_Department() {
		return New_Department;
	}
	public WebElement getDepartment_code() {
		return Department_code;
	}
	public WebElement getDepartment_Description() {
		return Department_Description;
	}
	public WebElement getSubmit_DM() {
		return Submit_DM;
	}
	public WebElement getClose_DM() {
		return Close_DM;
	}
	public WebElement getExport_DM() {
		return Export_DM;
	}
	public WebElement getPrint_DM() {
		return Print_DM;
	}
	public WebElement getEdit_DM() {
		return Edit_DM;
	}
	public WebElement getDelete_DM() {
		return Delete_DM;
	}
	public WebElement getPress_Delete() {
		return Press_Delete;
	}
	public WebElement getSearchDept() {
        return SearchDept;
     }
   public WebElement getSearchCheck() {
        return SearchCheck;
     }

	
	
}
