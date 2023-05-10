@InventoryTransTypeMaster
Feature: InventoryTransTypeMaster
Validation depends on the Business logic implemented for the Inventory Trans Type Master The Validation is set in a way that, once the Inventory Trans Type Master Data is fed into the 
system with the required Data such as Trans Type and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Inventory Trans Type master screen opens
    When  Delete data initially from database in Inventory Trans Type
    Given A web browser is on the Inventory Trans Type master page
    When  New Inventory Trans Type button is clicked on page
    Then  A popup with Inventory Trans Type should be displayed when clicked
    And   A popup with submit button has shown in Inventory Trans Type page
    
    
    
    @Integration
    Scenario Outline: Validating the Inventory Trans Type master with valid  data
    Given A web browser is on the Inventory Trans Type master page
    And   New Inventory Trans Type button is clicked
    Then 	Entering the data in "<Trans Type>","<Trans Name>","<Description>" text box in Inventory Trans Type
    When 	Click on submit button in Inventory Trans Type page
    Then 	A popop with message successfully added should be displayed in Inventory Trans Type page
    Then  The entered data is available in Inventory Trans Type table
  
    Examples: 
      | Trans Type  |Trans Name     | Description  |
      | Type A         |TypeA       | desc1         |            
   
      
    @Integration 
    Scenario Outline:  Validating the Inventory Trans Type master with duplicate data
    Given A web browser is on the Inventory Trans Type master page
    And   Set data in Inventory Trans Type table
    And   New Inventory Trans Type button is clicked
    Then 	Entering the duplicate data in "<Trans Type>","<Trans Name>","<Description>" text box in Inventory Trans Type
    When 	Click on submit button in Inventory Trans Type page
    Then 	A popop with message already exists should be displayed in Inventory Trans Type page
    
    
    
    
    Examples: 
      | Trans Type  |Trans Name     | Description  |
      | Type B      |TypeB          | desc2         | 
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Inventory Trans Type master with blank data
    Given A web browser is on the Inventory Trans Type master page
    And  	New Inventory Trans Type button is clicked
    Then 	Entering the blank data in "<Trans Type>","<Trans Name>","<Description>" text box in Inventory Trans Type
    When  Click on submit button in Inventory Trans Type page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Inventory Trans Type page
    
    
    
    Examples: 
      | Trans Type  |Trans Name| Description  |
      |              |           |            |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Inventory Trans Type Master Page
     Given A web browser is on the Inventory Trans Type master page
     And   Set data in Inventory Trans Type table
     And   New Inventory Trans Type button is clicked
     And   Clicking the close button in Inventory Trans Type
     Then  Checking the functionality of edit button and by clicking in Inventory Trans Type
     Then  Checking the update button has shown in the Inventory Trans Type page
     Then  The edited data should be available in database of Inventory Trans Type
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Inventory Trans Type Master Page
     Given A web browser is on the Inventory Trans Type master page
     And   Set data in Inventory Trans Type table
     Then  Checking the functionality of delete button by clicking in Inventory Trans Type
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Inventory Trans Type
     Then  Verifying that the entered data was deleted in database of Inventory Trans Type
     
     
   @Integration
     Scenario: Testing search box and Export button in Inventory Trans Type Master
       Given  A web browser is on the Inventory Trans Type master page 
       And   Set data in Inventory Trans Type table
       Then   Searching the Trans Type by sending keys
       And    The text in the search box should be equal to values in the Inventory Trans Type table
       Then   Clicking Export button in Inventory Trans Type master page
  
  