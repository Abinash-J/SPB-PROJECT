package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;



public class GSMMasterPOM {

	
	public GSMMasterPOM() {
		PageFactory.initElements(BaseClass.driver,this);

}
	
	   @FindBy(xpath="//a[@class='btn btn-primary font-weight-bolder']")
	   private WebElement New_GSM;
	   @FindBy(xpath="//input[@id='code']")
	   private WebElement GSM_Code;
	   @FindBy(xpath="//input[@id='des']")
	   private WebElement Description;
	   @FindBy(xpath="(//button[@class='btn btn-primary mr-2'])[1]")
	   private WebElement Submit;
	   @FindBy(xpath="//button[@class='btn btn-light-primary font-weight-bolder dropdown-toggle']")
	   private WebElement Export;
	   @FindBy(xpath="(//span[@class='navi-text'])[1]")
	   private WebElement Print;
	   @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[1]")
	   private WebElement closeButton;
	   
	   @FindBy(xpath="(//i[@class='la la-edit'][1])")
	   private WebElement EditIcon;

	   @FindBy(xpath="(//i[@class='la la-trash'])[1]")
	   private WebElement DeleteIcon;
	   @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[2]")
	   private WebElement DeleteButtonPopup;
	  //search
	   @FindBy(xpath="//input[@class='form-control form-control-sm']")
	   private WebElement SearchButton;
	   @FindBy(xpath="(//table//td)[1]")
	   private WebElement SearchCheck;
	   
	   
	   
	   
	   
	   
	   
	public WebElement getNew_GSM() {
		return New_GSM;
	}
	public WebElement getGSM_Code() {
		return GSM_Code;
	}
	public WebElement getDescription() {
		return Description;
	}
	public WebElement getSubmit() {
		return Submit;
	}
	public WebElement getExport() {
		return Export;
	}
	public WebElement getPrint() {
		return Print;
	}
	public WebElement getcloseButton() {
		return closeButton;
	}
	public WebElement getEditIcon() {
		return EditIcon;
	}
	 public WebElement getDeleteIcon() {
		return DeleteIcon;
	}
	public WebElement getDeleteButtonPopup() {
		return DeleteButtonPopup;
	}
	 public WebElement getSearchButton() {
			return SearchButton;
		}
		public WebElement getSearchCheck() {
			return SearchCheck;
		}
	   
	
	
	
}
