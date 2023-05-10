@ItemTypeMaster
Feature: ItemTypeMaster
Validation depends on the Business logic implemented for the Item Type Master The Validation is set in a way that, once the Item Type Master Data is fed into the 
system with the required Data such as Item Type Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Item Type master screen opens
    When  Delete data initially from database in Item Type
    Given A web browser is on the Item Type master page
    When  New Item Type button is clicked on page
    Then  A popup with Item Type should be displayed when clicked
    And   A popup with submit button has shown in Item Type page
    
    
    
    @Integration
    Scenario Outline: Validating the Item Type master with valid  data
    Given A web browser is on the Item Type master page
    And   New Item Type button is clicked
    Then 	Entering the data in "<Item Type Code>","<Description>" text box in Item Type
    When 	Click on submit button in Item Type page
    Then 	A popop with message successfully added should be displayed in Item Type page
    Then  The entered data is available in Item Type table
  
    Examples: 
      | Item Type Code  | Description  |
      | win             | workinprogress|                
   
      
    @Integration 
    Scenario Outline:  Validating the Item Type master with duplicate data
    Given A web browser is on the Item Type master page
    And   Set data in Item Type table
    And   New Item Type button is clicked
    Then 	Entering the duplicate data in "<Item Type Code>","<Description>" text box in Item Type
    When 	Click on submit button in Item Type page
    Then 	A popop with message already exists should be displayed in Item Type page
    
    
    
    
    Examples: 
      | Item Type Code  | Description |
      | WRP              | wrapper    | 
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Item Type master with blank data
    Given A web browser is on the Item Type master page
    And  	New Item Type button is clicked
    Then 	Entering the blank data in "<Item Type Code>","<Description>" text box in Item Type
    When  Click on submit button in Item Type page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Item Type page
    
    
    
    Examples: 
      |Item Type Code|Description|
      |              |           |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Item Type Master Page
     Given A web browser is on the Item Type master page
     And   Set data in Item Type table
     And   New Item Type button is clicked
     And   Clicking the close button in Item Type
     Then  Checking the functionality of edit button and by clicking in Item Type
     Then  Checking the update button has shown in the Item Type page
     Then  The edited data should be available in database of Item Type
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Item Type Master Page
     Given A web browser is on the Item Type master page
     And   Set data in Item Type table
     Then  Checking the functionality of delete button by clicking in Item Type
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Item Type
     Then  Verifying that the entered data was deleted in database of Item Type
     
     
   @Integration
     Scenario: Testing search box and Export button in Item Type Master
       Given  A web browser is on the Item Type master page 
       And   Set data in Item Type table
       Then   Searching the Item Type code by sending keys
       And    The text in the search box should be equal to values in the Item Type table
       Then   Clicking Export button in Item Type master page
  
  