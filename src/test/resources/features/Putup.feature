Feature: Putup Master
Validation depends on the Business logic implemented for the Putup MasterThe Validation is set in a way that, once a Putup Master’s
Data is fed into the systemWith the required Data such as “Putup Code and   Description” No provision must be facilitated for adding the set of Data into the system
 
  @Smoke
  Scenario: Test if PutupMaster screen opens
     Given A web browser is on the PutupMaster page
     When  Add new putup button is clicked
     Then  Popup with title as PutupMaster should be displayed with Submit button in Department Page
     
  @Integration
  Scenario Outline: Validating business logic of PutupMaster with suitable test data
     Given A web browser is on the PutupMaster page
     When  Add new putup button is clicked
     Then  Entering valid "<PutupCode>" and "<Description>" in the input box in PutupMasterPage
     And   Click the submit button in Putup form
     Then  A Popup with saved successfully should be displayed should be displayed in PutupMaster Page
     Then  The entered data should be available in PutupMaster table
     
    
    Examples: 
      |PutupCode|Description|
      |Sheets|Standard packing of 500 sheets|
      |A4Sheets|Package of 200 Sheets|                      

   @Integration
  Scenario Outline: Validating business logic of PutupMaster with duplicate test data added before the scenario execution
      Given A web browser is on the PutupMaster page
      And   Set data in PutupMaster table
      When  Add new putup button is clicked
      Then  Entering duplicate data in "<PutupCode>" and "<Description>" in the input box in PutupForm
      And   Click the submit button in Putup form
      Then  A popup with message already exists data should be displayed in PutupPage
     
       Examples:
      |PutupCode|Description|
      |A3|300 sheets|                      
      
     
  @Integration
  Scenario Outline: Validating business logic of PutupMaster with blank test data
      Given A web browser is on the PutupMaster page
      And   Add new putup button is clicked
      Then  Entering "<PutupCode>" and "<Description>" in the input box with blank data in PutupForm
      And   Click the submit button in Putup form
      Then  A popup with message please enter all mandatory fields should be displayed in PutupForm
      
      Examples:
      |PutupCode|Description|
      | |  |
      
  @Integration
  Scenario: Testing the functionality of Edit button in PutupMaster page
     Given A web browser is on the PutupMaster page
     And   Set data in PutupMaster table
     When  Add new putup button is clicked
     Then  Clicking the close button in PutupForm
     Then  Checking the functionality of edit button by clicking in PutupMasterPage
     Then  A Popup with title as Department should be displayed with update button in PutupMaster Page
     Then  The edited data should be available in database of PutupMaster
     
    @Integration  
   Scenario: Testing the functionalities of Delete button in PutupMaster page
     Given A web browser is on the PutupMaster page
     Then  Checking the functionality of delete icon button in PutupMaster Page
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in PutupMasterPage
     Then  Verifying that the enter data was deleted in Putup database
   
   @Integration
     Scenario: Testing search box and Export button in PutupMaster
       Given A web browser is on the PutupMaster page 
       And Set data in PutupMaster table
       Then Searching the Putup code by sending keys
       And The text in the search box should be equal to values in the Putuptable
       Then Clicking Export button in PutupMaster page
      
