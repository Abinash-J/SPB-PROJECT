@PaymentTermsMaster
Feature: PaymentTermsMaster
Validation depends on the Business logic implemented for the Payment Terms Master The Validation is set in a way that, once the Payment Terms Master Data is fed into the 
system with the required Data such as Payment Terms Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Payment Terms master screen opens
    When  Delete data initially from database in Payment Terms
    Given A web browser is on the Payment Terms master page
    When  New Payment Terms button is clicked on page
    Then  A popup with Payment Terms should be displayed when clicked
    And   A popup with submit button has shown in Payment Terms page
    
    
    
    @Integration
    Scenario Outline: Validating the Payment Terms master with valid  data
    Given A web browser is on the Payment Terms master page
    And   New Payment Terms button is clicked
    Then 	Entering the data in "<Payment Name>","<Description>" text box in Payment Terms
    When 	Click on submit button in Payment Terms page
    Then 	A popop with message successfully added should be displayed in Payment Terms page
    Then  The entered data is available in Payment Terms table
  
    Examples: 
      | Payment Name     | Description  |
      | PN 1             | payment name 1|                
   
      
    @Integration 
    Scenario Outline:  Validating the Payment Terms master with duplicate data
    Given A web browser is on the Payment Terms master page
    And   Set data in Payment Terms table
    And   New Payment Terms button is clicked
    Then 	Entering the duplicate data in "<Payment Name>","<Description>" text box in Payment Terms
    When 	Click on submit button in Payment Terms page
    Then 	A popop with message already exists should be displayed in Payment Terms page
    
    
    
    
    Examples: 
      | Payment Name  | Description       |
      | PN 2          | payment name 2    | 
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Payment Terms master with blank data
    Given A web browser is on the Payment Terms master page
    And  	New Payment Terms button is clicked
    Then 	Entering the blank data in "<Payment Name>","<Description>" text box in Payment Terms
    When  Click on submit button in Payment Terms page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Payment Terms page
    
    
    
    Examples: 
      |Payment Name|Description|
      |            |           |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Payment Terms Master Page
     Given A web browser is on the Payment Terms master page
     And   Set data in Payment Terms table
     And   New Payment Terms button is clicked
     And   Clicking the close button in Payment Terms
     Then  Checking the functionality of edit button and by clicking in Payment Terms
     Then  Checking the update button has shown in the Payment Terms page
     Then  The edited data should be available in database of Payment Terms
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Payment Terms Master Page
     Given A web browser is on the Payment Terms master page
     And   Set data in Payment Terms table
     Then  Checking the functionality of delete button by clicking in Payment Terms
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Payment Terms
     Then  Verifying that the entered data was deleted in database of Payment Terms
     
     
   @Integration
     Scenario: Testing search box and Export button in Payment Terms Master
       Given  A web browser is on the Payment Terms master page 
       And   Set data in Payment Terms table
       Then   Searching the Payment Terms code by sending keys
       And    The text in the search box should be equal to values in the Payment Terms table
       Then   Clicking Export button in Payment Terms master page
  
  