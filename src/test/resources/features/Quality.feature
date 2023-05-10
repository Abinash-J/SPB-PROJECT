@QualityMaster
Feature: QualityMaster
Validation depends on the Business logic implemented for the Quality  Master The Validation is set in a way that, once the Quality  Master Data is fed into the 
system with the required Data such as Quality  Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Quality  master screen opens
    When  Delete data initially from database in Quality 
    Given A web browser is on the Quality  master page
    When  New Quality  button is clicked on page
    Then  A popup with Quality  should be displayed when clicked
    And   A popup with submit button has shown in Quality  page
    
    
    
    @Integration
    Scenario Outline: Validating the Quality  master with valid  data
    Given A web browser is on the Quality  master page
    And   New Quality  button is clicked
    Then 	Entering the data in "<Quality  Code>","<Description>" text box in Quality 
    When 	Click on submit button in Quality  page
    Then 	A popop with message successfully added should be displayed in Quality  page
    Then  The entered data is available in Quality  table
  
    Examples: 
      |Quality  Code |Description  |
      | QC06         | Quality6    |
   
      
    @Integration 
    Scenario Outline:  Validating the Quality  master with duplicate data
    Given A web browser is on the Quality  master page
    And   Set data in Quality  table
    And   New Quality  button is clicked
    Then 	Entering the duplicate data in "<Quality  Code>","<Description>" text box in Quality 
    When 	Click on submit button in Quality  page
    Then 	A popop with message already exists should be displayed in Quality  page
    
    
    
    
    Examples: 
      |Quality  Code |Description  |
      | QC07         | Quality7    |
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Quality  master with blank data
    Given A web browser is on the Quality  master page
    And   Set data in Quality  table
    And  	New Quality  button is clicked
    Then 	Entering the blank data in "<Quality  Code>","<Description>" text box in Quality 
    When  Click on submit button in Quality  page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Quality  page
    
    
    
    Examples: 
     |Quality  Code |Description  |
     |              |             |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Quality  Master Page
     Given A web browser is on the Quality  master page
     And   Set data in Quality  table
     And   New Quality  button is clicked
     And   Clicking the close button in Quality 
     Then  Checking the functionality of edit button and by clicking in Quality 
     Then  Checking the update button has shown in the Quality  page
     Then  The edited data should be available in database of Quality 
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Quality  Master Page
     Given A web browser is on the Quality  master page
     And   Set data in Quality  table
     Then  Checking the functionality of delete button by clicking in Quality 
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Quality 
     Then  Verifying that the entered data was deleted in database of Quality 
     
     
   @Integration
     Scenario: Testing search box and Export button in Quality  Master
       Given  A web browser is on the Quality  master page 
       And   Set data in Quality  table
       Then   Searching the Quality  code by sending keys
       And    The text in the search box should be equal to values in the Quality  table
       Then   Clicking Export button in Quality  master page
  
  