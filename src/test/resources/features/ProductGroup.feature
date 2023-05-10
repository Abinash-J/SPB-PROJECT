@ProductGroupMaster
Feature: ProductGroupMaster
Validation depends on the Business logic implemented for the Product Group Master The Validation is set in a way that, once the Product Group Master Data is fed into the 
system with the required Data such as Product Group Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Product Group master screen opens
    When  Delete data initially from database in Product Group
    Given A web browser is on the Product Group master page
    When  New Product Group button is clicked on page
    Then  A popup with Product Group should be displayed when clicked
    And   A popup with submit button has shown in Product Group page
    
    
    
    @Integration
    Scenario Outline: Validating the Product Group master with valid  data
    Given A web browser is on the Product Group master page
    And   New Product Group button is clicked
    Then 	Entering the data in "<Product Group Code>","<Description>" text box in Product Group
    When 	Click on submit button in Product Group page
    Then 	A popop with message successfully added should be displayed in Product Group page
    Then  The entered data is available in Product Group table
  
    Examples: 
      | Product Group Code  | Description  |
      | PG001               | Super White  |                
   
      
    @Integration 
    Scenario Outline:  Validating the Product Group master with duplicate data
    Given A web browser is on the Product Group master page
    And   Set data in Product Group table
    And   New Product Group button is clicked
    Then 	Entering the duplicate data in "<Product Group Code>","<Description>" text box in Product Group
    When 	Click on submit button in Product Group page
    Then 	A popop with message already exists should be displayed in Product Group page
    
    
    
    
    Examples: 
      | Product Group Code  | Description |
      | PG006               | orange      |
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Product Group master with blank data
    Given A web browser is on the Product Group master page
    And  	New Product Group button is clicked
    Then 	Entering the blank data in "<Product Group Code>","<Description>" text box in Product Group
    When  Click on submit button in Product Group page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Product Group page
    
    
    
    Examples: 
      |Product Group Code|Description|
      |                  |           |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Product Group Master Page
     Given A web browser is on the Product Group master page
     And   Set data in Product Group table
     And   New Product Group button is clicked
     And   Clicking the close button in Product Group
     Then  Checking the functionality of edit button and by clicking in Product Group
     Then  Checking the update button has shown in the Product Group page
     Then  The edited data should be available in database of Product Group
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Product Group Master Page
     Given A web browser is on the Product Group master page
     And   Set data in Product Group table
     Then  Checking the functionality of delete button by clicking in Product Group
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Product Group
     Then  Verifying that the entered data was deleted in database of Product Group
     
     
      @Integration
     Scenario: Testing search box and Export button in Product Group Master
       Given  A web browser is on the Product Group master page 
       And   Set data in Product Group table
       Then   Searching the Product Group code by sending keys
       And    The text in the search box should be equal to values in the Product Group table
       Then   Clicking Export button in Product Group master page
  
  