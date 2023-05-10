Feature: Color Master
Validation depends on the Business logic implemented for the Color Master The Validation is set in a way that, once the Color Master Data is fed into the 
system with the required Data such as Color Code and Color Name No provision must be facilitated for adding the same set of Data into the system.
 
   @Smoke
  Scenario: Test if ColorMaster screen opens
     Given A web browser is on the ColorMaster page
     When  Add new color button is clicked
     Then  Popup with id as entity-modal should be displayed with Submit button
  @Integration
  Scenario Outline: Validating business logic of ColorMaster with suitable test data
     Given A web browser is on the ColorMaster page
     When  Add new color button is clicked
     Then  Entering valid "<ColorCode>" and "<ColorDescription>" in the input box
     And   Click the submit button
     Then  The entered data should be available in color table
    
    Examples: 
      |ColorCode|ColorDescription|
      |RD|Red|                      

   @Integration
  Scenario Outline: Validating business logic of ColorMaster with duplicate test data added before the scenario execution
      Given A web browser is on the ColorMaster page
      And   Set data in color table
      When  Add new color button is clicked
      Then  Entering duplicate data in "<ColorCode>" and "<ColorDescription>" in the input box
      And   Click the submit button
      Then  A popup with message already exists data should be displayed
      Then  The entered data should be available in color table
      
      Examples:
      |ColorCode|ColorDescription|
      |BK|Black|                      
      
     
  @Integration
  Scenario Outline: Validating business logic of ColorMaster with blank test data
      Given A web browser is on the ColorMaster page
      And   Add new color button is clicked
      Then  Entering "<ColorCode>" and "<ColorDescription>" in the input box with blank data
      And   Click the submit button 
      Then  A popup with message please enter all mandatory fields should be displayed
      
      Examples:
      |ColorCode|ColorDescription|
      | |  |
  
   @Integration
  Scenario: Testing the functionality of Edit button in ColorMaster page
     Given A web browser is on the ColorMaster page
     And   Set data in color table
     When  Add new color button is clicked
     Then  Clicking the close button
     Then  Checking the functionality of edit button by clicking
     Then  The edited data should be available in database of ColorMaster
    @Integration  
   Scenario: Testing the functionalities of Delete button in ColorMaster page
     Given A web browser is on the ColorMaster page
     Then  Checking the functionality of delete icon button in ColorMaster Page
     Then  Clicking delete button and a popup deleted sucessfully should be displayed
     
    @Integration
        Scenario: Testing search box and Export button in ColorMaster
       Given A web browser is on the ColorMaster page 
       And Set data in color table
       Then Searching the Color code by sending keys
       And The text in the search box should be equal to values in the Colortable
       Then Clicking Export button in ColorMaster page
      
     
                           
     
     
     
      
    

      
    
   
