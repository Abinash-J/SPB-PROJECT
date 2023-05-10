Feature: DesignationMaster
Validation depends on the Business logic implemented for the Designation Master
The Validation is set in a way that, once an Employees Designation Data is fed into the system with the required Data such as Designation Code and Designation Name
No provision must be facilitated for adding the same set of Data into the system

   @Smoke
  Scenario: Test if DesignationMaster screen opens
     Given A web browser is on the DesignationMaster page
     When  Add new designation button is clicked
     Then  Popup with id as entity-modal should be displayed in Designation page with Submit button

   @Integration
   Scenario Outline: Validating business logic of DesignationMaster with suitable test data
     Given A web browser is on the DesignationMaster page
      When  Add new designation button is clicked
     Then  Entering valid "<DesignationCode>" and "<DesignationName>" in the input box of DesignationMaster form
     And   Click the submit button for submitting the DesignationMaster Form
     Then  The entered data should be available in Designation table
    
    Examples: 
      |DesignationCode|DesignationName|
      |Mgr|Manager| 
      
   
  @Integration
  Scenario Outline: Validating business logic of DesignationMaster with duplicate test data added before the scenario execution
      Given A web browser is on the DesignationMaster page
      And   Set data in designation table
      When  Add new designation button is clicked
      Then  Entering duplicate "<DesignationCode>" and "<DesignationName>" in the input box of DesignationMaster form
      And   Click the submit button for submitting the DesignationMaster Form
      Then  A popup with message already exists data should be displayed in DesignationMaster page
    
    Examples:
      |DesignationCode|DesignationName|
      |Opr|Operator|
   
  @Integration
  Scenario Outline: Validating business logic of DesignationMaster with blank test data
      Given A web browser is on the DesignationMaster page
      When  Add new designation button is clicked
      Then  Entering "<DesignationCode>" and "<DesignationName>" in the input box with blank data in DesignationMaster form
      And   Click the submit button for submitting the DesignationMaster Form 
      Then  A popup with message please enter all mandatory fields should be displayed in DesignationMaster page
      
      Examples:
      |DesignationCode|DesignationName|
      | |  |
      
  @Integration
  Scenario: Testing the functionality of Edit button in DesignationMaster page
     Given A web browser is on the DesignationMaster page
     And Set data in designation table
     When  Add new designation button is clicked
     Then  Clicking the close button of Designationform
     Then  Checking the functionality of Designation edit button by clicking
      Then  The edited data should be available in database of DesignationMaster
     
   @Integration 
     Scenario: Testing the functionality of Delete button in DesignationMaster page
     Given A web browser is on the DesignationMaster page
     Then  Checking the functionality of delete icon button in DesignationMaster Page
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Designation page
     
       @Integration
     Scenario: Testing search box and Export button in DesignationMaster
       Given A web browser is on the DesignationMaster page 
       And Set data in designation table
       Then Searching the Designationcode by sending keys
       And The text in the search box should be equal to values in the Designationtable
       Then Clicking Export button in DesignationMaster page
    
    
