@CustomerTypeMaster
Feature: CustomerTypeMaster
Validation depends on the Business logic implemented for the Customer Type Master The Validation is set in a way that, once the Customer Type Master Data is fed into the 
system with the required Data such as Customer Type Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Customer Type master screen opens
    When  Delete data initially from database in Customer Type
    Given A web browser is on the Customer Type master page
    When  New Customer Type button is clicked on page
    Then  A popup with Customer Type should be displayed when clicked
    And   A popup with submit button has shown in Customer Type page
    
    
    
    @Integration
    Scenario Outline: Validating the Customer Type master with valid  data
    Given A web browser is on the Customer Type master page
    And   New Customer Type button is clicked
    Then 	Entering the data in "<Customer Type Code>","<Description>" text box in Customer Type
    When 	Click on submit button in Customer Type page
    Then 	A popop with message successfully added should be displayed in Customer Type page
    Then  The entered data is available in Customer Type table
  
    Examples: 
      | Customer Type Code  | Description            |
      | A001                |Agasthiyar Publications |             
   
      
    @Integration 
    Scenario Outline:  Validating the Customer Type master with duplicate data
    Given A web browser is on the Customer Type master page
    And   Set data in Customer Type table
    And   New Customer Type button is clicked
    Then 	Entering the duplicate data in "<Customer Type Code>","<Description>" text box in Customer Type
    When 	Click on submit button in Customer Type page
    Then 	A popop with message already exists should be displayed in Customer Type page
    
    
    
    
    Examples: 
      | Customer Type Code  | Description            |
      | D002                |Dheena Publications     | 
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Customer Type master with blank data
    Given A web browser is on the Customer Type master page
    And  	New Customer Type button is clicked
    Then 	Entering the blank data in "<Customer Type Code>","<Description>" text box in Customer Type
    When  Click on submit button in Customer Type page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Customer Type page
    
    
    
    Examples: 
      |Customer Type Code|Description|
      |                  |           |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Customer Type Master Page
     Given A web browser is on the Customer Type master page
     And   Set data in Customer Type table
     And   New Customer Type button is clicked
     And   Clicking the close button in Customer Type
     Then  Checking the functionality of edit button and by clicking in Customer Type
     Then  Checking the update button has shown in the Customer Type page
     Then  The edited data should be available in database of Customer Type
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Customer Type Master Page
     Given A web browser is on the Customer Type master page
     And   Set data in Customer Type table
     Then  Checking the functionality of delete button by clicking in Customer Type
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Customer Type
     Then  Verifying that the entered data was deleted in database of Customer Type
     
     
   @Integration
     Scenario: Testing search box and Export button in Customer Type Master
       Given  A web browser is on the Customer Type master page 
       And   Set data in Customer Type table
       Then   Searching the Customer Type code by sending keys
       And    The text in the search box should be equal to values in the Customer Type table
       Then   Clicking Export button in Customer Type master page
  
  