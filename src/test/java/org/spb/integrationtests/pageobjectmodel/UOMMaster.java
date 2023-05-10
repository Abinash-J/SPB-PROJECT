package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;



public class UOMMaster {
	
	public UOMMaster() {
		PageFactory.initElements(BaseClass.driver,this);
	}
	

	@FindBy(xpath="//a[@class='btn btn-primary font-weight-bolder']")
	private WebElement NewUom;
	
	@FindBy(id="uom_code")
	private WebElement UOMCode;
	
	@FindBy(id="uom_desc")
	private WebElement UomDesc;
	
	@FindBy(xpath="(//button[@class='btn btn-primary mr-2'])[1]")
	private WebElement SubmitUom;
	
	@FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[1]")
	private WebElement CloseUom;
	
	@FindBy(xpath="//button[@class='btn btn-light-primary font-weight-bolder dropdown-toggle']")
	private WebElement ExportUOM;

	public WebElement getNewUom() {
		return NewUom;
	}

	public WebElement getUomCode() {
		return UOMCode;
	}

	public WebElement getUomDesc() {
		return UomDesc;
	}

	public WebElement getSubmitUom() {
		return SubmitUom;
	}

	public WebElement getCloseUom() {
		return CloseUom;
	}

	public WebElement getExportUOM() {
		return ExportUOM;
	}
	

}
