package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;



public class PutupMasterPom {
	public PutupMasterPom() {
		PageFactory.initElements(BaseClass.driver,this);
	}
	   @FindBy(xpath="//a[@class='btn btn-primary font-weight-bolder']")
	   private WebElement NewPutup;
	   @FindBy(id="putup_code")
	   private WebElement PutupCode;
	   @FindBy(id="putup_desc")
	   private WebElement PutupDescription;
	   @FindBy(id="putup_numOfSheets")
	   private WebElement NumberofSheets;
	   @FindBy(xpath="(//button[@class='btn btn-primary mr-2'])[1]")
	   private WebElement PutupSubmit;
	   @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[1]")
	   private WebElement PutupClose;
	   @FindBy(xpath="//button[@class='btn btn-light-primary font-weight-bolder dropdown-toggle']")
	   private WebElement PutupExport ;
	   @FindBy (xpath="//div[@class='btn-group ']//i[@class='la la-edit']")
	   private WebElement Edit_Putup;
	   @FindBy (xpath="//table//tr//td[7]//div//a[@title='Delete']")
	   private WebElement Delete_Putup;
	   @FindBy(xpath="(//div[@class='modal-content']//button[@class='btn btn-light-primary font-weight-bold'])[2]")
	   private WebElement Press_Delete;
	   @FindBy(xpath="//input[@class='form-control form-control-sm']")
	   private WebElement SearchPutup;
	   @FindBy(xpath="//tbody//tr[1]//td[1]")
	   private WebElement SearchCheck;
	   
	   public WebElement getNewPutup() {
		return NewPutup;
	}
	   public WebElement getPutupCode() {
		return PutupCode;
	}
	   public WebElement getPutupDescription() {
		return PutupDescription;
	}
	   public WebElement getNumberofSheets() {
		return NumberofSheets;
	}
	   public WebElement getPutupSubmit() {
		return PutupSubmit;
	}
	   public WebElement getPutupClose() {
		return PutupClose;
	}
	   public WebElement getPutupExport() {
		return PutupExport;
	}
	   public WebElement getEdit_Putup() {
		return Edit_Putup;
	}
	  public WebElement getDelete_Putup() {
		return Delete_Putup;
	}
	  public WebElement getPress_Delete() {
		return Press_Delete;
	}
	  public WebElement getSearchPutup() {
  	        return SearchPutup;
    }
	public WebElement getSearchCheck() {
	        return SearchCheck;
    }
	  
       

}
