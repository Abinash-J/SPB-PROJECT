package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;

public class QualityPOM {

	
	public QualityPOM() {
		PageFactory.initElements(BaseClass.driver,this);

}
	  @FindBy(xpath="//a[@class='btn btn-primary font-weight-bolder']")
	   private WebElement New_Quality;
	   @FindBy(xpath="//input[@id='quality_code']")
	   private WebElement Quality_Code;
	   @FindBy(xpath="//input[@id='quality_desc']")
	   private WebElement Description;
	   @FindBy(xpath="//select[@id='productGroup']")
	   private WebElement Product_Group_Code_dropdown;
	   @FindBy(xpath="(//button[@class='btn btn-primary mr-2'])[1]")
	   private WebElement Submit;
	   @FindBy(xpath="//button[@class='btn btn-light-primary font-weight-bolder dropdown-toggle']")
	   private WebElement Export;
	   @FindBy(xpath="(//span[@class='navi-text'])[1]")
	   private WebElement Print;
	   @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[1]")
	   private WebElement closeButton;
	   
	   //   //td[contains(text(),'Q002')]
	   @FindBy(xpath="//i[@class='la la-edit']")
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
	   
	   
	   
	public WebElement getNew_Quality() {
		return New_Quality;
	}
	public WebElement getQuality_Code() {
		return Quality_Code;
	}
	public WebElement getDescription() {
		return Description;
	}
	public WebElement getProduct_Group_Code_dropdown() {
		return Product_Group_Code_dropdown;
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
	public WebElement getCloseButton() {
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
