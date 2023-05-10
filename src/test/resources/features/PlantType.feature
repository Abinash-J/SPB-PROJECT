@PlantTypeMaster
Feature: PlantTypeMaster
Validation depends on the Business logic implemented for the PlantType Master The Validation is set in a way that, once the Plant Type Master Data is fed into the 
system with the required Data such as Plant Type Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Plant Type master screen opens
    When  Delete data initially from database in Plant Type
    Given A web browser is on the Plant Type master page
    When  New plant type button is clicked on page
    Then  A popup with plant type should be displayed when clicked
    And   A popup with submit button has shown in plant type page
    
    
    
    @Integration
    Scenario Outline: Validating the Plant Type master with valid  data
    Given A web browser is on the Plant Type master page
    And   New plant type button is clicked
    Then 	Entering the data in "<Plant Type Code>","<Description>" text box in plant type
    When 	Click on submit button in plant type page
    Then 	A popop with message successfully added should be displayed in plant type page
    Then  The entered data is available in plant type table
  
    Examples: 
      | Plant Type Code  | Description         |
      | 11               | Manufacturing Plant |   
   
      
    @Integration 
    Scenario Outline:  Validating the Plant Type master with duplicate data
    Given A web browser is on the Plant Type master page
    And   Set data in plant type table
    And   New plant type button is clicked
    Then 	Entering the duplicate data in "<Plant Type Code>","<Description>" text box in plant type
    When 	Click on submit button in plant type page
    Then 	A popop with message already exists should be displayed in plant type page
    
    
    
    
    Examples: 
      | Plant Type Code  | Description |
      | 12               | Warehouse   |
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Plant Type master with blank data
    Given A web browser is on the Plant Type master page
    And  	New plant type button is clicked
    Then 	Entering the blank data in "<Plant Type Code>","<Description>" text box in plant type
    When  Click on submit button in plant type page
    Then 	A popop with message Please enter all mandatory fields should be displayed in plant type page
    
    
    
    Examples: 
      |Plant Type Code|Description|
      |               |           |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Plant Type Master Page
     Given A web browser is on the Plant Type master page
     And   Set data in plant type table
     And   New plant type button is clicked
     Then  A popup with plant type should be displayed when clicked
     And   Clicking the close button in plant type
     Then  Checking the functionality of edit button by clicking in plant type
     Then  Checking the update button has shown in the plant type page
     Then  The edited data should be available in database of plant type
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Plant Type Master Page
     Given A web browser is on the Plant Type master page
     And   Set data in plant type table
     Then  Checking the functionality of delete button by clicking in plant type
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in plant type
     Then  Verifying that the entered data was deleted in database of plant type
  
  
   @Integration
     Scenario: Testing search box and Export button in Plant Type Master
       Given  A web browser is on the Plant Type master page 
       And   Set data in plant type table
       Then   Searching the Plant Type code by sending keys
       And    The text in the search box should be equal to values in the Plant Type table
       Then   Clicking Export button in Plant Type master page
  