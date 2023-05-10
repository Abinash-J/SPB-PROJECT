@IndentorTypeMaster
Feature: IndentorTypeMaster
Validation depends on the Business logic implemented for the Indentor Type Master The Validation is set in a way that, once the Indentor Type Master Data is fed into the 
system with the required Data such as Indentor Type Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Indentor Type master screen opens
    When  Delete data initially from database in Indentor Type
    Given A web browser is on the Indentor Type master page
    When  New Indentor Type button is clicked on page
    Then  A popup with Indentor Type should be displayed when clicked
    And   A popup with submit button has shown in Indentor Type page
    
    
    
    @Integration
    Scenario Outline: Validating the Indentor Type master with valid  data
    Given A web browser is on the Indentor Type master page
    And   New Indentor Type button is clicked
    Then 	Entering the data in "<Indentor Type Code>","<Description>" text box in Indentor Type
    When 	Click on submit button in Indentor Type page
    Then 	A popop with message successfully added should be displayed in Indentor Type page
    Then  The entered data is available in Indentor Type table
  
    Examples: 
      | Indentor Type Code  | Description  |
      | IND04                | Indentor4|                
   
      
    @Integration 
    Scenario Outline:  Validating the Indentor Type master with duplicate data
    Given A web browser is on the Indentor Type master page
    And   Set data in Indentor Type table
    And   New Indentor Type button is clicked
    Then 	Entering the duplicate data in "<Indentor Type Code>","<Description>" text box in Indentor Type
    When 	Click on submit button in Indentor Type page
    Then 	A popop with message already exists should be displayed in Indentor Type page
    
    
    
    
    Examples: 
      | Indentor Type Code  | Description |
      | IND02                | Indentor2|
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Indentor Type master with blank data
    Given A web browser is on the Indentor Type master page
    And  	New Indentor Type button is clicked
    Then 	Entering the blank data in "<Indentor Type Code>","<Description>" text box in Indentor Type
    When  Click on submit button in Indentor Type page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Indentor Type page
    
    
    
    Examples: 
      |Indentor Type Code|Description|
      |              |           |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Indentor Type Master Page
     Given A web browser is on the Indentor Type master page
     And   Set data in Indentor Type table
     And   New Indentor Type button is clicked
     And   Clicking the close button in Indentor Type
     Then  Checking the functionality of edit button and by clicking in Indentor Type
     Then  Checking the update button has shown in the Indentor Type page
     Then  The edited data should be available in database of Indentor Type
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Indentor Type Master Page
     Given A web browser is on the Indentor Type master page
     And   Set data in Indentor Type table
     Then  Checking the functionality of delete button by clicking in Indentor Type
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Indentor Type
     Then  Verifying that the entered data was deleted in database of Indentor Type
     
     
   @Integration
     Scenario: Testing search box and Export button in Indentor Type Master
       Given  A web browser is on the Indentor Type master page 
       And   Set data in Indentor Type table
       Then   Searching the Indentor Type code by sending keys
       And    The text in the search box should be equal to values in the Indentor Type table
       Then   Clicking Export button in Indentor Type master page
  
  