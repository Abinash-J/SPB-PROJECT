@IndentTypeMaster
Feature: IndentTypeMaster
Validation depends on the Business logic implemented for the Indent Type Master The Validation is set in a way that, once the Indent Type Master Data is fed into the 
system with the required Data such as Indent Type Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Indent Type master screen opens
    When  Delete data initially from database in Indent Type
    Given A web browser is on the Indent Type master page
    When  New Indent Type button is clicked on page
    Then  A popup with Indent Type should be displayed when clicked
    Then  The Indent Type Code should be displayed in popup page
    And   A popup with submit button has shown in Indent Type page
    
    
    
    @Integration
    Scenario Outline: Validating the Indent Type master with valid  data
    Given A web browser is on the Indent Type master page
    And   New Indent Type button is clicked
    Then 	Entering the data in "<Indent Type Code>","<Description>" text box in Indent Type
    When 	Click on submit button in Indent Type page
    Then 	A popop with message successfully added should be displayed in Indent Type page
    Then  The entered data is available in Indent Type table
  
    Examples: 
      | Indent Type Code  | Description  |
      | CL                | Clearance    |               
   
      
    @Integration 
    Scenario Outline:  Validating the Indent Type master with duplicate data
    Given A web browser is on the Indent Type master page
    And   Set data in Indent Type table
    And   New Indent Type button is clicked
    Then 	Entering the duplicate data in "<Indent Type Code>","<Description>" text box in Indent Type
    When 	Click on submit button in Indent Type page
    Then 	A popop with message already exists should be displayed in Indent Type page
    
    
    
    
    Examples: 
      | Indent Type Code  | Description |
      | DM                | Domestic    | 
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Indent Type master with blank data
    Given A web browser is on the Indent Type master page
    And  	New Indent Type button is clicked
    Then 	Entering the blank data in "<Indent Type Code>","<Description>" text box in Indent Type
    When  Click on submit button in Indent Type page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Indent Type page
    
    
    
    Examples: 
      |Indent Type Code|Description|
      |                |           |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Indent Type Master Page
     Given A web browser is on the Indent Type master page
     And   Set data in Indent Type table
     And   New Indent Type button is clicked
     And   Clicking the close button in Indent Type
     Then  Checking the functionality of edit button and by clicking in Indent Type
     Then  Checking the update button has shown in the Indent Type page
     Then  The edited data should be available in database of Indent Type
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Indent Type Master Page
     Given A web browser is on the Indent Type master page
     And   Set data in Indent Type table
     Then  Checking the functionality of delete button by clicking in Indent Type
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Indent Type
     Then  Verifying that the entered data was deleted in database of Indent Type
     
     
   @Integration
     Scenario: Testing search box and Export button in Indent Type Master
       Given  A web browser is on the Indent Type master page 
       And   Set data in Indent Type table
       Then   Searching the Indent Type code by sending keys
       And    The text in the search box should be equal to values in the Indent Type table
       Then   Clicking Export button in Indent Type master page
  
  