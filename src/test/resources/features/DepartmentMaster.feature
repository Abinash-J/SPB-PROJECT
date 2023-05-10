@l
Feature: Department Master
Validation depends on the Business logic implemented for the Department MasterThe Validation is set in a way that, once the Department’s Data is 
fed into the system with the required Data such as “Department Name and its relevant Functioning” No provision must be facilitated for adding the same 
set of Data into the system.
 
  @Smoke
  Scenario: Test if Department Master screen opens
     Given A web browser is on the DepartmentMaster page
     When  Add new department button is clicked
     Then  Popup with title as Department should be displayed with Submit button in Department Page
     
  @Integration
  Scenario Outline: Validating business logic of DepartmentMaster with suitable test data
     Given A web browser is on the DepartmentMaster page
     When  Add new department button is clicked
     Then  Entering valid "<DepartmentCode>" and "<DepartmentName>" in the input box in DepartmentPage
     And   Click the submit button in Department form
     Then  A Popup with saved successfully should be displayed should be displayed in DepartmentMaster Page
     Then  The entered data should be available in Department table
     
    
    Examples: 
      |DepartmentCode|DepartmentName|
      |MFG|Manufacturing|
      |SL01|Sales|                      

   @Integration
  Scenario Outline: Validating business logic of DepartmentMaster with duplicate test data added before the scenario execution
      Given A web browser is on the DepartmentMaster page
      And   Set data in Department table
      When  Add new department button is clicked
      Then  Entering duplicate data in "<DepartmentCode>" and "<DepartmentName>" in the input box in DepartmentForm
      And   Click the submit button in Department form
      Then  A popup with message already exists data should be displayed in DepartmentPage
     
       Examples:
      |DepartmentCode|DepartmentName|
      |MR|Marketing|                      
      
     
  @Integration
  Scenario Outline: Validating business logic of DepartmentMaster with blank test data
      Given A web browser is on the DepartmentMaster page
      And   Add new department button is clicked
      Then  Entering "<DepartmentCode>" and "<DepartmentName>" in the input box with blank data in DepartmentForm
      And   Click the submit button in Department form
      Then  A popup with message please enter all mandatory fields should be displayed in DepartmentPage
      
      Examples:
      |DepartmentCode|DepartmentName|
      | |  |
      
  @Integration
  Scenario: Testing the functionality of Edit button in DepartmentMaster page
     Given A web browser is on the DepartmentMaster page
     And   Set data in Department table
     When  Add new department button is clicked
     Then  Clicking the close button in DepartmentForm
     Then  Checking the functionality of edit button by clicking in DepartmentPage
     Then  A Popup with title as Department should be displayed with update button in Department Page
     Then  The edited data should be available in database of DepartmentMaster
     
    @Integration  
   Scenario: Testing the functionalities of Delete button in DepartmentMaster page
     Given A web browser is on the DepartmentMaster page
     Then  Checking the functionality of delete icon button in DepartmentMaster Page
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in DepartmentPage
     Then  Verifying that the enter data was deleted in department database
   
    @Integration
     Scenario: Testing search box and Export button in DepartmentMaster
       Given A web browser is on the DepartmentMaster page 
       And Set data in Department table
       Then Searching the Department code by sending keys
       And The text in the search box should be equal to values in the Departmenttable
       Then Clicking Export button in DepartmentMaster page
      
