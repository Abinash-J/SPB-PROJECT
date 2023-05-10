package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;


public class RegionMasterPom {
	public RegionMasterPom() {
		PageFactory.initElements(BaseClass.driver,this);
	}
	   @FindBy(xpath="//a[@class='btn btn-primary font-weight-bolder']")
	   private WebElement NewRegion;
	   @FindBy(id="region_code")
	   private WebElement RegionCode;
	   @FindBy(id="region_name")
	   private WebElement RegionName;
	   @FindBy(xpath="(//button[@class='btn btn-primary mr-2'])[1]")
	   private WebElement RegionSubmit;
	   @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[1]")
	   private WebElement RegionClose;
	   @FindBy(xpath="//button[@class='btn btn-light-primary font-weight-bolder dropdown-toggle']")
	   private WebElement Export ;
	   @FindBy (xpath="//div[@class='btn-group ']//i[@class='la la-edit']")
	   private WebElement Edit_RM;
	   @FindBy (xpath="//div[@class='btn-group ']//i[@class='la la-trash']")
	   private WebElement Delete_RM;
	   @FindBy(xpath="(//div[@class='modal-content']//button[@class='btn btn-light-primary font-weight-bold'])[2]")
	   private WebElement Press_Delete;
	   @FindBy(xpath="//input[@class='form-control form-control-sm']")
	   private WebElement SearchReg;
       @FindBy(xpath="(//table//td)[1]")
	   private WebElement SearchCheck;
	public WebElement getNewRegion() {
		return NewRegion;
	}
	public WebElement getRegionCode() {
		return RegionCode;
	}
	public WebElement getRegionName() {
		return RegionName;
	}
	public WebElement getRegionSubmit() {
		return RegionSubmit;
	}
	public WebElement getRegionClose() {
		return RegionClose;
	}
	public WebElement getExport() {
		return Export;
	}
	public WebElement getEdit_RM() {
		return Edit_RM;
	}
	public WebElement getDelete_RM() {
		return Delete_RM;
	}
	public WebElement getPress_Delete() {
		return Press_Delete;
	}
	public WebElement getSearchReg() {
		return SearchReg;
	}
	public WebElement getSearchCheck() {
		return SearchCheck;
	}
	
	   

}
