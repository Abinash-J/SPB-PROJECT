package org.spb.integrationtests.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.spb.integrationtests.BaseClass;




public class LegalEntityMasterPom {
    
    public LegalEntityMasterPom() {
        
        PageFactory.initElements(BaseClass.driver,this);
    }
    
    @FindBy(xpath="//div[@class='card-header']//a[@class='btn btn-primary font-weight-bolder']")
    private WebElement NewLegalEntity;
    
    @FindBy(id="groupCode")
    private WebElement selectGroupCode;
    
    @FindBy(id="legEntity_legEntitycode")
    private WebElement LegalEntityCode;
    
    @FindBy(id="legEntity_name")
    private WebElement LegalName;
    
    @FindBy(xpath="(//button[@class='btn btn-primary mr-2'])[1]")
    
    private WebElement Submit_LEM;
    
    @FindBy(xpath="(//button[@class='btn btn-light-primary font-weight-bold'])[1]")
    private WebElement LEMClose;
    
    @FindBy(xpath="//button[@class='btn btn-light-primary font-weight-bolder dropdown-toggle']")
    private WebElement Export;
    
    @FindBy(xpath = "//table[@id='kt_datatable_new']/tbody/tr/td[9]//div//a[@title='Delete']")
    private WebElement DeleteIcon;
    
    @FindBy(xpath="(//div[@class='modal-content'])[2]//button[@class='btn btn-primary mr-2']")
    private WebElement PressDelete;
    
    @FindBy(xpath="//div[@class='btn-group ']//i[@class='la la-edit']")
    private WebElement EditIcon;
    
    @FindBy(xpath="//input[@class='form-control form-control-sm']")
    private WebElement SearchLegal;
    
    @FindBy(xpath="//tbody//tr[1]//td[3]")
    private WebElement SearchCheck;
    
    public WebElement getNewLegalEntity() {
        return NewLegalEntity;
    }
    
    public WebElement getSelectGroupCode() {
        return selectGroupCode;
    }
    
    public WebElement getLegalEntityCode() {
        return LegalEntityCode;
    }
    
    public WebElement getLegalName() {
        return LegalName;
    }
    
    public WebElement getSubmit_LEM() {
        return Submit_LEM;
    }
    
    public WebElement getLEMClose() {
        return LEMClose;
    }
    
    public WebElement getExport() {
        return Export;
    }
    
    public WebElement getDeleteIcon() {
        return DeleteIcon;
    }
    
    public WebElement getPressDelete() {
        return PressDelete;
    }
    
    public WebElement getEditIcon() {
        return EditIcon;
    }
    public WebElement getSearchLegal() {
        return SearchLegal;
    }
    public WebElement getSearchCheck() {
        return SearchCheck;
    }
    
    
    
    
    
    
}
