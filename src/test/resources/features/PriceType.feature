@PriceTypeMaster
Feature: PriceTypeMaster
Validation depends on the Business logic implemented for the Price Type Master The Validation is set in a way that, once the Price Type Master Data is fed into the 
system with the required Data such as Price Type Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke
    Scenario: Test if Price Type master screen opens
    When  Delete data initially from database in Price Type
    Given A web browser is on the Price Type master page
    When  New Price Type button is clicked on page
    Then  A popup with Price Type should be displayed when clicked
    And   A popup with submit button has shown in Price Type page
    
    
    
    @Integration
    Scenario Outline: Validating the Price Type master with valid  data
    Given A web browser is on the Price Type master page
    And   New Price Type button is clicked
    Then 	Entering the data in "<Price Type Code>","<Price Name>" text box in Price Type
    When 	Click on submit button in Price Type page
    Then 	A popop with message successfully added should be displayed in Price Type page
    Then  The entered data is available in Price Type table
  
    Examples: 
      | Price Type Code  | Price Name  |
      | price a          | price 1     |                
   
      
    @Integration 
    Scenario Outline:  Validating the Price Type master with duplicate data
    Given A web browser is on the Price Type master page
    And   Set data in Price Type table
    And   New Price Type button is clicked
    Then 	Entering the duplicate data in "<Price Type Code>","<Price Name>" text box in Price Type
    When 	Click on submit button in Price Type page
    Then 	A popop with message already exists should be displayed in Price Type page
    
    
    
    
    Examples: 
      | Price Type Code  | Price Name |
      | price b          | price 2  |
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Price Type master with blank data
    Given A web browser is on the Price Type master page
    And  	New Price Type button is clicked
    Then 	Entering the blank data in "<Price Type Code>","<Price Name>" text box in Price Type
    When  Click on submit button in Price Type page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Price Type page
    
    
    
    Examples: 
      |Price Type Code|Price Name|
      |               |          |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Price Type Master Page
     Given A web browser is on the Price Type master page
     And   Set data in Price Type table
     And   New Price Type button is clicked
     And   Clicking the close button in Price Type
     Then  Checking the functionality of edit button and by clicking in Price Type
     Then  Checking the update button has shown in the Price Type page
     Then  The edited data should be available in database of Price Type
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Price Type Master Page
     Given A web browser is on the Price Type master page
     And   Set data in Price Type table
     Then  Checking the functionality of delete button by clicking in Price Type
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Price Type
     Then  Verifying that the entered data was deleted in database of Price Type
     
     
       @Integration
     Scenario: Testing search box and Export button in Price Type Master
       Given  A web browser is on the Price Type master page 
       And   Set data in Price Type table
       Then   Searching the Price Type code by sending keys
       And    The text in the search box should be equal to values in the Price Type table
       Then   Clicking Export button in Price Type master page
  
  
  