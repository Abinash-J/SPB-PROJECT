@GSMMaster
Feature: GSMMaster
Validation depends on the Business logic implemented for the GSM Master The Validation is set in a way that, once the GSM Master Data is fed into the 
system with the required Data such as GSM Code and Description No provision must be facilitated for adding the same set of Data into the system.
  
    @Smoke 
    Scenario: Test if GSM master screen opens
    When  Delete data initially from database in GSM
    Given A web browser is on the GSM master page
    When  New GSM button is clicked on page
    Then  A popup with GSM should be displayed when clicked
    And   A popup with submit button has shown in GSM page
    
    
    
    @Integration
    Scenario Outline: Validating the GSM master with valid  data
    Given A web browser is on the GSM master page
    And   New GSM button is clicked
    Then 	Entering the data in "<GSM Code>","<Description>" text box in GSM 
    When 	Click on submit button in create GSM page
    Then 	A popop with message successfully added should be displayed in GSM page
    Then  The entered data is available in GSM table
  
    Examples: 
      | GSM Code  | Description |
      | 5060        |  5060 GSM     |   
   
      
    @Integration  
    Scenario Outline:  Validating the GSM master with duplicate data
    Given A web browser is on the GSM master page
    And   Set data in GSM table
    And   New GSM button is clicked
    Then 	Entering the duplicate data in "<GSM Code>","<Description>" text box in GSM 
    When 	Click on submit button in create GSM page
    Then 	A popop with message already exists should be displayed in GSM page
    
    
    
    Examples: 
      | GSM Code  | Description |
      | 6070        |  6070 GSM     |
     
     
      
    @Integration   
    Scenario Outline:  Validating the GSM master with blank data
    Given A web browser is on the GSM master page
    And  	New GSM button is clicked
    Then 	Entering the blank data in "<GSM Code>","<Description>" text box in GSM
    When  Click on submit button in create GSM page
    Then 	A popop with message Please enter all mandatory fields should be displayed in GSM page
    
    
    
    Examples: 
      |GSM Code|Description|
      |        |           |
      
   @Integration   
   Scenario: Testing the functionality of edit button in GSMMaster Page
     Given A web browser is on the GSM master page
     And   Set data in GSM table
     And   New GSM button is clicked
     Then  A popup with GSM should be displayed when clicked
     And   Clicking the close button in GSM page
     Then  Checking the functionality of edit button by clicking in GSM page
     Then  Checking the update button has shown in the GSM page
     Then  The edited data should be available in database of GSM
     
     
     
     @Integration
     Scenario: Testing the functionality of delete button in GSMMaster Page
     Given A web browser is on the GSM master page
     And   Set data in GSM table
     Then  Checking the functionality of delete button by clicking in GSM page
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in GSM page
     Then  Verifying that the entered data was deleted in database of GSM
     
     @Integration
     Scenario: Testing search box and Export button in GSMMaster
       Given  A web browser is on the GSM master page 
       And    Set data in GSM table
       Then   Searching the GSM code by sending keys
       And    The text in the search box should be equal to values in the GSM table
       Then   Clicking Export button in GSM master page
  
  
      