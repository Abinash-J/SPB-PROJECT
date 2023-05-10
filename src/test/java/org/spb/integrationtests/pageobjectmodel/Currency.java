package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;



public class Currency {
	public Currency() {
		PageFactory.initElements(BaseClass.driver,this);
	}

	     // currency master
		@FindBy(xpath="(//a[@class='nav-link py-4 px-6'])[2]")
		private WebElement administration;
		@FindBy(xpath="(//span[@class='menu-text'])[1]")
		private WebElement setup;
		@FindBy(xpath="/html/body/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/div/ul/li/div/ul/li[7]/a/span[2]")
		private WebElement FinanceSetup;
		@FindBy(xpath="/html/body/div[2]/div/div/div[1]/div[2]/div/div/div/div[1]/div/ul/li/div/ul/li[7]/div/ul/li[1]/a/span")
		private WebElement CurrencyMaster;
		@FindBy(xpath="//a[@class='btn btn-primary font-weight-bolder']")
		private WebElement add;
		@FindBy(xpath="(//input[@id='currency_code'])[1]")
		private WebElement CurrencyCode;
		@FindBy(xpath="(//input[@id='currency_name'])[1]")
		private WebElement CurrencyName;
		@FindBy(xpath="//textarea[@id='currency_desc']")
		private WebElement CurrencyDescription;
		@FindBy(id="currency_territory")
		private WebElement CurrencyCalnder;
		@FindBy(xpath="//input[@id='currency_symbol']")
		private WebElement Symbol;
		@FindBy(xpath="//input[@id='currency_precision']")
		private WebElement Precision;
		@FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[1]")
		private WebElement Close;
		
		public WebElement getAdministration() {
			return administration;
		}
		public WebElement getSetup() {
			return setup;
		}
		public WebElement getFinanceSetup() {
			return FinanceSetup;
		}
		public WebElement getCurrencyMaster() {
			return CurrencyMaster;
		}
		public WebElement getAdd() {
			return add;
		}
		public WebElement getCurrencyCode() {
			return CurrencyCode;
		}
		public WebElement getCurrencyName() {
			return CurrencyName;
		}
		public WebElement getCurrencyDescription() {
			return CurrencyDescription;
		}
		public WebElement getCurrencyCalnder() {
			return CurrencyCalnder;
		}
		public WebElement getSymbol() {
			return Symbol;
		}
		public WebElement getPrecision() {
			return Precision;
		}
		public WebElement getClose() {
			return Close;
		}
		
	
}
