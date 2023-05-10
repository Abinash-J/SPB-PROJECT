package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;

public class ConsigneePOM {

	

	public ConsigneePOM() {
		PageFactory.initElements(BaseClass.driver,this);

}
	//Indentor Master
	
	 @FindBy(xpath="//a[@class='btn btn-primary font-weight-bolder']")    
	   private WebElement NewConsignee;                                     
	  
	 @FindBy(xpath="//input[@id='consignee_code']")
	   private WebElement ConsigneeCode;                                     
	 
	 @FindBy(xpath="//input[@id='consignee_name']")
	   private WebElement ConsigneeName;                                         
	   
	 @FindBy(xpath="//input[@id='legacy_consignee_code']")
	   private WebElement LegacyConsigneeCode;
	   
	 //foreign key
	 @FindBy(xpath="//select[@id='customer_code']")
	   private WebElement CustomerCode;
	 
	  //foreign key 
	 @FindBy(xpath="//select[@id='indentor_code']")
	   private WebElement IndentorCode;
	 
	 @FindBy(xpath="//input[@id='consignee_short_name']")
	   private WebElement ConsigneeShortName;
	  
	 @FindBy(xpath="//input[@id='customer_date_established']")
	   private WebElement DateEstablished;
	 
//Address
	 
	 @FindBy(xpath="//select[@id='address_type']")
	   private WebElement AddressUseTypeDropDown;
	   
	 @FindBy(xpath="//input[@id='address1']")
	   private WebElement Address1Flat;
	  
	 @FindBy(xpath="//input[@id='address2']")
	   private WebElement Address2Building;
	  
	 @FindBy(xpath="//input[@id='address3']")
	   private WebElement Address3Road;
	   
	 @FindBy(xpath="//input[@id='area']")
	   private WebElement Address4Area;
	 
	 @FindBy(xpath="//select[@id='countryId']")
	   private WebElement Country;
	 
	 @FindBy(xpath="//select[@id='stateId']']")
	   private WebElement State;
	  
	 @FindBy(xpath="//select[@id='cityId']")
	   private WebElement City;
	  
	 //innner value click
	 @FindBy(xpath="//option[contains(text(),'India')]")
	   private WebElement CountryValueClick;
	 
	 @FindBy(xpath="//option[contains(text(),'Tamil Nadu')]")
	   private WebElement StateValueClick;
	  
	 @FindBy(xpath="//option[contains(text(),'Tirunelveli')]")
	   private WebElement CityValueClick;
	 
	 
	 @FindBy(xpath="//input[@id='pincode_create']")
	   private WebElement Pincode;
	 
	 @FindBy(xpath="//input[@id='timezone']")
	   private WebElement Timezone;
	  
	 @FindBy(xpath="//input[@id='indentor_region']")
	   private WebElement Region;
	   
	 @FindBy(xpath="(//input[@id='indentor_outside_process'])[1]")
	   private WebElement OutsideProcessYes;
	 
	 @FindBy(xpath="(//input[@id='indentor_outside_process'])[2]")
	   private WebElement OutsideprocessNo;
	 
//contact details
	 
	 @FindBy(xpath="//input[@id='telephone']")
	   private WebElement PrimaryTelephone;
	 
	 @FindBy(xpath="//input[@id='telephoneAlt']")
	   private WebElement SecondaryTelephone;
	 
	 @FindBy(xpath="//input[@id='email']")
	   private WebElement Email;
	 
	 @FindBy(xpath="//input[@id='emailAlt']")
	   private WebElement EmailAlternate;
	 
	 @FindBy(xpath="//input[@id='contact']")
	   private WebElement PrimaryContact;
	 
	 @FindBy(xpath="//input[@id='contactAlt']")
	   private WebElement SecondaryContact;
	 
//Tax information
	 @FindBy(xpath="//input[@id='pan']")
	   private WebElement PAN;
	 
	 @FindBy(xpath="//input[@id='gst']")
	   private WebElement GST;
	 
	 @FindBy(xpath="//input[@id='tin']")
	   private WebElement TIN;

//buttons
	 @FindBy(xpath="(//button[@class='btn btn-primary mr-2'])[2]")
	   private WebElement Submit;
	 
	 @FindBy(xpath="//a[contains(text(),'Cancel')]")
	   private WebElement Cancel;
	 
	 @FindBy(xpath="//a[contains(text(),'Back')]")
	   private WebElement Back;
	 
//edit icons,buttons delete search,error chk
	 
	 @FindBy(xpath="//button[@class='btn btn-light-primary font-weight-bolder dropdown-toggle']")
	   private WebElement Export ;
	   @FindBy (xpath="(//div[@class='btn-group ']//i[@class='la la-edit'])[1]")
	   private WebElement EditIcon;
	   @FindBy (xpath="(//i[@class='la la-trash'])[1]")
	   private WebElement DeleteIcon;
	   @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[1]")
	   private WebElement Deletebutton;
	   @FindBy(xpath="//input[@class='form-control form-control-sm']")
	   private WebElement SearchButton;
     @FindBy(xpath="(//table//tr[1]//td)[1]")
	   private WebElement SearchCheck;
     @FindBy(xpath="(//div[@class='invalid-feedback'])[15]")
	   private WebElement ValidMailaddresserror;
     
//error messages for all 
     
     @FindBy(xpath="//div[contains(text(),'Please enter Consignee Code')]")
	   private WebElement ConsigneeCodeError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter Consignee Name')]")
	   private WebElement ConsigneeNameError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter Legacy Consignee Code')]")
	   private WebElement LegacyConsigneeCodeError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter Consignee Short Name')]")
	   private WebElement ConsigneeShortNameError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter your Date Established')]")
	   private WebElement DateEstablishedError;
	 
	 @FindBy(xpath="//body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[4]/div[2]/div[1]")
	   private WebElement FlatError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter Building Name / Village')]")
	   private WebElement BuildingError;
	 
	 //Please enter your Road / Street / Lane 
	 @FindBy(xpath="//body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[5]/div[1]/div[2]")
	   private WebElement RoadError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter your city')]")
	   private WebElement CityError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter your pincode')]")
	   private WebElement PincodeError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter your time zone')]")
	   private WebElement TimeZoneError;
	 
	 // Please enter your Region
	 @FindBy(xpath="//div[contains(text(),'Please enter your Region')]")
	   private WebElement RegionError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter valid primary telephone')]")
	   private WebElement PrimaryTelephoneError;
	 
	 // Please enter valid primary telephone
	 @FindBy(xpath="//body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[9]/div[2]/div[1]/div[2]")
	   private WebElement SecondaryTelephoneError;
     
	 // Please enter valid primary contact
	 @FindBy(xpath="//div[contains(text(),'Please enter valid primary contact')]")
	   private WebElement PrimaryContactError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter your PAN')]")
	   private WebElement PANError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter your GSTIN')]")
	   private WebElement GSTError;
	 
	 @FindBy(xpath="//div[contains(text(),'Please enter your TIN Number')]")
	   private WebElement TINError;
	 
	 
     
     
     
     
     
     
     
     
     
     
     
	public WebElement getNewConsignee() {
		return NewConsignee;
	}
	public WebElement getConsigneeCode() {
		return ConsigneeCode;
	}
	public WebElement getConsigneeName() {
		return ConsigneeName;
	}
	public WebElement getLegacyConsigneeCode() {
		return LegacyConsigneeCode;
	}
	public WebElement getCustomerCode() {
		return CustomerCode;
	}
	public WebElement getIndentorCode() {
		return IndentorCode;
	}
	public WebElement getConsigneeShortName() {
		return ConsigneeShortName;
	}
	public WebElement getDateEstablished() {
		return DateEstablished;
	}
	public WebElement getAddressUseTypeDropDown() {
		return AddressUseTypeDropDown;
	}
	public WebElement getAddress1Flat() {
		return Address1Flat;
	}
	public WebElement getAddress2Building() {
		return Address2Building;
	}
	public WebElement getAddress3Road() {
		return Address3Road;
	}
	public WebElement getAddress4Area() {
		return Address4Area;
	}
	public WebElement getCountry() {
		return Country;
	}
	public WebElement getState() {
		return State;
	}
	public WebElement getCity() {
		return City;
	}
	
	
	public WebElement getCountryValueClick() {
		return CountryValueClick;
	}
	public WebElement getStateValueClick() {
		return StateValueClick;
	}
	public WebElement getCityValueClick() {
		return CityValueClick;
	}
	
	
	public WebElement getPincode() {
		return Pincode;
	}
	public WebElement getTimezone() {
		return Timezone;
	}
	public WebElement getRegion() {
		return Region;
	}
	public WebElement getOutsideProcessYes() {
		return OutsideProcessYes;
	}
	public WebElement getOutsideprocessNo() {
		return OutsideprocessNo;
	}
	public WebElement getPrimaryTelephone() {
		return PrimaryTelephone;
	}
	public WebElement getSecondaryTelephone() {
		return SecondaryTelephone;
	}
	public WebElement getEmail() {
		return Email;
	}
	public WebElement getEmailAlternate() {
		return EmailAlternate;
	}
	public WebElement getPrimaryContact() {
		return PrimaryContact;
	}
	public WebElement getSecondaryContact() {
		return SecondaryContact;
	}
	public WebElement getPAN() {
		return PAN;
	}
	public WebElement getGST() {
		return GST;
	}
	public WebElement getTIN() {
		return TIN;
	}
	public WebElement getSubmit() {
		return Submit;
	}
	public WebElement getCancel() {
		return Cancel;
	}
	public WebElement getBack() {
		return Back;
	}
	public WebElement getExport() {
		return Export;
	}
	public WebElement getEditIcon() {
		return EditIcon;
	}
	public WebElement getDeleteIcon() {
		return DeleteIcon;
	}
	public WebElement getDeletebutton() {
		return Deletebutton;
	}
	public WebElement getSearchButton() {
		return SearchButton;
	}
	public WebElement getSearchCheck() {
		return SearchCheck;
	}
	public WebElement getValidMailaddresserror() {
		return ValidMailaddresserror;
	}
	
	
	
	public WebElement getConsigneeCodeError() {
		return ConsigneeCodeError;
	}
	public WebElement getConsigneeNameError() {
		return ConsigneeNameError;
	}
	public WebElement getLegacyConsigneeCodeError() {
		return LegacyConsigneeCodeError;
	}
	public WebElement getConsigneeShortNameError() {
		return ConsigneeShortNameError;
	}
	public WebElement getDateEstablishedError() {
		return DateEstablishedError;
	}
	public WebElement getFlatError() {
		return FlatError;
	}
	public WebElement getBuildingError() {
		return BuildingError;
	}
	public WebElement getRoadError() {
		return RoadError;
	}
	public WebElement getCityError() {
		return CityError;
	}
	public WebElement getPincodeError() {
		return PincodeError;
	}
	public WebElement getTimeZoneError() {
		return TimeZoneError;
	}
	public WebElement getRegionError() {
		return RegionError;
	}
	public WebElement getPrimaryTelephoneError() {
		return PrimaryTelephoneError;
	}
	public WebElement getSecondaryTelephoneError() {
		return SecondaryTelephoneError;
	}
	public WebElement getPrimaryContactError() {
		return PrimaryContactError;
	}
	public WebElement getPANError() {
		return PANError;
	}
	public WebElement getGSTError() {
		return GSTError;
	}
	public WebElement getTINError() {
		return TINError;
	}
	 
	 
	 
	 
	 
	 
}




