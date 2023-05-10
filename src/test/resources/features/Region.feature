Feature: Region Master
Validation depends on the Business logic implemented for the Region MasterThe Validation is set in a way that, 
once a Particular Product’s Region related Data is fed into the system with the required Data such as “Region Code and Region Name” 
No provision must be facilitated for adding the same set of Data into the system
 
  @Smoke
  Scenario: Test if Region Master screen opens
     Given A web browser is on the RegionMaster page
     When  Add new region button is clicked
     Then  Popup with title as Region should be displayed with Submit button in Region Page
     
  @Integration
  Scenario Outline: Validating business logic of RegionMaster with suitable test data
     Given A web browser is on the RegionMaster page
     When  Add new region button is clicked
     Then  Entering valid "<RegionCode>" and "<RegionName>" in the input box in RegionPage
     And   Click the submit button in Region form
     Then  A Popup with saved successfully should be displayed should be displayed in RegionMaster Page
     Then  The entered data should be available in Region table
     
    
    Examples: 
      |RegionCode|RegionName|
      |TN|TamilNadu|
      |KA01|Karnataka|                      

   @Integration
  Scenario Outline: Validating business logic of RegionMaster with duplicate test data added before the scenario execution
      Given A web browser is on the RegionMaster page
      And   Set data in Region table
      When  Add new region button is clicked
      Then  Entering duplicate data in "<RegionCode>" and "<RegionName>" in the input box in RegionForm
      And   Click the submit button in Region form
      Then  A popup with message already exists data should be displayed in RegionPage
     
       Examples:
      |RegionCode|RegionName|
      |KL|Kerala|                      
      
     
  @Integration
  Scenario Outline: Validating business logic of RegionMaster with blank test data
      Given A web browser is on the RegionMaster page
      And   Add new region button is clicked
      Then  Entering "<RegionCode>" and "<RegionName>" in the input box with blank data in RegionForm
      And   Click the submit button in Region form
      Then  A popup with message please enter all mandatory fields should be displayed in RegionPage
      
      Examples:
      |RegionCode|RegionName|
      | |  |
      
  @Integration
  Scenario: Testing the functionality of Edit button in RegionMaster page
     Given A web browser is on the RegionMaster page
     And   Set data in Region table
     When  Add new region button is clicked
     Then  Clicking the close button in RegionForm
     Then  Checking the functionality of edit button by clicking in RegionPage
     Then  A Popup with title as Region should be displayed with update button in Region Page
     Then  The edited data should be available in database of RegionMaster
     
    @Integration  
   Scenario: Testing the functionalities of Delete button in RegionMaster page
     Given A web browser is on the RegionMaster page
     Then  Checking the functionality of delete icon button in RegionMaster Page
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in RegionPage
     Then  Verifying that the enter data was deleted in region database
   
    @Integration
     Scenario: Testing search box and Export button in RegionMaster
       Given A web browser is on the RegionMaster page 
       And Set data in Region table
       Then Searching the Region code by sending keys
       And The text in the search box should be equal to values in the Regiontable
       Then Clicking Export button in RegionMaster page
      

