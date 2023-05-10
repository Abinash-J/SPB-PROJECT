package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;



public class ColorMaster {
	

	public ColorMaster() {
		PageFactory.initElements(BaseClass.driver,this);
	}
	
	@FindBy(xpath="//a[@class='btn btn-primary font-weight-bolder']")
	private WebElement NewColour;

  @FindBy(xpath ="//input[@id='color_code']")
    private WebElement ColourCode;

	@FindBy(xpath="(//div[@class='modal-content']//input[@id='color_desc'])[1]")
	private WebElement ColourDesc;
	
	@FindBy(xpath="(//div[@class='modal-content']//button[@class='btn btn-primary mr-2'])[1]")
	private WebElement SubmitColourCreation;
	
	@FindBy(xpath="(//div[@class='modal-content']//button[@class='btn btn-light-primary font-weight-bold'])[1]")
	private WebElement CloseCreateColour;
	
	@FindBy(xpath="//button[@class='btn btn-light-primary font-weight-bolder dropdown-toggle']")
	private WebElement Export;
	
	@FindBy(xpath="(//div[@class='dropdown-menu dropdown-menu-sm dropdown-menu-right show']//a)[1]")
	private WebElement PrintinExport;
	
	@FindBy(xpath="(//div[@class='dropdown-menu dropdown-menu-sm dropdown-menu-right show']//a)[2]")
	private WebElement Copy;
	
	@FindBy(xpath="(//div[@class='dropdown-menu dropdown-menu-sm dropdown-menu-right show']//a)[3]")
	private WebElement Excel;
	
	@FindBy(xpath="(//div[@class='dropdown-menu dropdown-menu-sm dropdown-menu-right show']//a)[4]")
	private WebElement Csv;
	
	@FindBy(xpath="(//div[@class='dropdown-menu dropdown-menu-sm dropdown-menu-right show']//a)[5]")
	private WebElement Pdf;
	
	@FindBy(xpath="//i[@class='la la-edit']")
	private WebElement EditIcon;
	
	@FindBy(xpath="//a//i[@class='la la-trash']")
	private WebElement DeleteIcon;
	
	@FindBy(xpath="(//div[@class='modal-content'])[2]//button[@class='btn btn-light-primary font-weight-bold']")
	private WebElement PressDelete;
    
	 @FindBy(xpath="//input[@class='form-control form-control-sm']")
	 private WebElement SearchColor;
	    
	 @FindBy(xpath="//tbody//tr[1]//td[1]")
	 private WebElement SearchCheck;
	

	public WebElement getNewColour() {
		return NewColour;
	}

	public WebElement getColourCode() {
		return ColourCode;
	}

	public WebElement getColourDesc() {
		return ColourDesc;
	}

	public WebElement getSubmitColourCreation() {
		return SubmitColourCreation;
	}

	public WebElement getCloseCreateColour() {
		return CloseCreateColour;
	}

	public WebElement getExport() {
		return Export;
	}

	public WebElement getPrintinExport() {
		return PrintinExport;
	}

	public WebElement getCopy() {
		return Copy;
	}

	public WebElement getExcel() {
		return Excel;
	}

	public WebElement getCsv() {
		return Csv;
	}

	public WebElement getPdf() {
		return Pdf;
	}
	public WebElement getEditIcon() {
		return EditIcon;
	}
	public WebElement getDeleteIcon() {
		return DeleteIcon;
	}
	public WebElement getPressDelete() {
		return PressDelete;
	}
	public WebElement getSearchColor() {
	        return SearchColor;
    }
	public WebElement getSearchCheck() {
	        return SearchCheck;
    }
}



