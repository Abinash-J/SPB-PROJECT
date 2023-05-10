package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;

public class FinancialCalendarPOM {

	
	public FinancialCalendarPOM() {
		PageFactory.initElements(BaseClass.driver,this);

}
	  @FindBy(xpath="//a[@class='btn btn-primary font-weight-bolder']")
	   private WebElement New_FinancialCalendar;
	   @FindBy(xpath="//select[@id='fincalcode']")
	   private WebElement FinancialCal_Code;
	   @FindBy(xpath="//input[@id='prefix']")
	   private WebElement Prefix;
	   @FindBy(xpath="//input[@id='type']")
	   private WebElement Type;
	   @FindBy(xpath="//input[@id='year']")
	   private WebElement Year;
	   @FindBy(xpath="//input[@id='quarter']")
	   private WebElement Quarter;
	   @FindBy(xpath="//input[@id='num']")
	   private WebElement Num;
	   @FindBy(xpath="//input[@id='from_date']")
	   private WebElement FromDate;
	   @FindBy(xpath="//input[@id='to_date']")
	   private WebElement ToDate;
	   @FindBy(xpath="//input[@id='period_name']")
	   private WebElement PeriodName;
	   @FindBy(xpath="//input[@id='adjusting_yes']")
	   private WebElement AdjustingYes;
	   @FindBy(xpath="//input[@id='adjusting_no']")
	   private WebElement AdjustingNo;
	   
	   @FindBy(xpath="(//button[@class='btn btn-primary mr-2'])[1]")
	   private WebElement Submit;
	   @FindBy(xpath="//button[@class='btn btn-light-primary font-weight-bolder dropdown-toggle']")
	   private WebElement Export;
	   @FindBy(xpath="(//span[@class='navi-text'])[1]")
	   private WebElement Print;
	   @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[1]")
	   private WebElement closeButton;
	   
	   @FindBy(xpath="(//i[@class='la la-edit'])[1]")
	   private WebElement EditIcon;

	   @FindBy(xpath="(//i[@class='la la-trash'])[1]")
	   private WebElement DeleteIcon;
	   @FindBy(xpath="//button[contains(text(),'Delete')]")
	   private WebElement DeleteButtonPopup;
	   //search
	   @FindBy(xpath="//input[@class='form-control form-control-sm']")
	   private WebElement SearchButton;
	   @FindBy(xpath="(//table//td)[1]")
	   private WebElement SearchCheck;
	   
	   
	   
	   
	public WebElement getNew_FinancialCalendar() {
		return New_FinancialCalendar;
	}
	public WebElement getFinancialCal_Code() {
		return FinancialCal_Code;
	}
	public WebElement getPrefix() {
		return Prefix;
	}
	public WebElement getType() {
		return Type;
	}
	public WebElement getYear() {
		return Year;
	}
	public WebElement getQuarter() {
		return Quarter;
	}
	public WebElement getNum() {
		return Num;
	}
	public WebElement getFromDate() {
		return FromDate;
	}
	public WebElement getToDate() {
		return ToDate;
	}
	public WebElement getPeriodName() {
		return PeriodName;
	}
	public WebElement getAdjustingYes() {
		return AdjustingYes;
	}
	public WebElement getAdjustingNo() {
		return AdjustingNo;
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
