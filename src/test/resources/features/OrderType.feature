@OrderTypeMaster
Feature: OrderTypeMaster
Validation depends on the Business logic implemented for the Order Type  Master The Validation is set in a way that, once the Order Type  Master Data is fed into the 
system with the required Data such as Order Type  Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Order Type  master screen opens
    When  Delete data initially from database in Order Type 
    Given A web browser is on the Order Type  master page
    When  New Order Type  button is clicked on page
    Then  A popup with Order Type  should be displayed when clicked
    And   A popup with submit button has shown in Order Type  page
    
    
    
    @Integration
    Scenario Outline: Validating the Order Type  master with valid  data
    Given A web browser is on the Order Type  master page
    And   New Order Type  button is clicked
    Then 	Entering the data in "<Order Type  Code>","<Description>","<Order Category>" text box in Order Type 
    When 	Click on submit button in Order Type  page
    Then 	A popop with message successfully added should be displayed in Order Type  page
    Then  The entered data is available in Order Type  table
  
    Examples: 
      |Order Type  Code |Description  |Order Category|
      | Order_Type6     | Order_Desc6 | Order_Cat6   |
   
      
    @Integration 
    Scenario Outline:  Validating the Order Type  master with duplicate data
    Given A web browser is on the Order Type  master page
    And   Set data in Order Type  table
    And   New Order Type  button is clicked
    Then 	Entering the duplicate data in "<Order Type  Code>","<Description>","<Order Category>" text box in Order Type 
    When 	Click on submit button in Order Type  page
    Then 	A popop with message already exists should be displayed in Order Type  page
    
    
    
    
    Examples: 
       |Order Type  Code |Description  |Order Category|
       | Order_Type7     | Order_Desc7 | Order_Cat7   |
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Order Type  master with blank data
    Given A web browser is on the Order Type  master page
    And   Set data in Order Type  table
    And  	New Order Type  button is clicked
    Then 	Entering the blank data in "<Order Type  Code>","<Description>","<Order Category>" text box in Order Type 
    When  Click on submit button in Order Type  page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Order Type  page
    
    
    
    Examples: 
      |Order Type  Code |Description  |Order Category|
      |                 |             |              |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Order Type  Master Page
     Given A web browser is on the Order Type  master page
     And   Set data in Order Type  table
     And   New Order Type  button is clicked
     And   Clicking the close button in Order Type 
     Then  Checking the functionality of edit button and by clicking in Order Type 
     Then  Checking the update button has shown in the Order Type  page
     Then  The edited data should be available in database of Order Type 
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Order Type  Master Page
     Given A web browser is on the Order Type  master page
     And   Set data in Order Type  table
     Then  Checking the functionality of delete button by clicking in Order Type 
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Order Type 
     Then  Verifying that the entered data was deleted in database of Order Type 
     
     
   @Integration
     Scenario: Testing search box and Export button in Order Type  Master
       Given  A web browser is on the Order Type  master page 
       And   Set data in Order Type  table
       Then   Searching the Order Type  code by sending keys
       And    The text in the search box should be equal to values in the Order Type  table
       Then   Clicking Export button in Order Type  master page
  
  