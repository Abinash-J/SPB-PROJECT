@FormFactorMaster
Feature: FormFactorMaster
Validation depends on the Business logic implemented for the Form Factor Master The Validation is set in a way that, once the Form Factor Master Data is fed into the 
system with the required Data such as Form Factor Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Form Factor master screen opens
    When  Delete data initially from database in Form Factor
    Given A web browser is on the Form Factor master page
    When  New form factor button is clicked on page
    Then  A popup with form factor should be displayed when clicked
    And   A popup with submit button has shown in form factor page
    
    
    
    @Integration
    Scenario Outline: Validating the Form Factor master with valid  data
    Given A web browser is on the Form Factor master page
    And   New form factor button is clicked
    Then 	Entering the data in "<Form Factor Code>","<Description>" text box in form factor
    When 	Click on submit button in form factor page
    Then 	A popop with message successfully added should be displayed in form factor page
    Then  The entered data is available in form factor table
  
    Examples: 
      | Form Factor Code  | Description  |
      | FF4               | desc1        |                
   
      
    @Integration 
    Scenario Outline:  Validating the Form Factor master with duplicate data
    Given A web browser is on the Form Factor master page
    And   Set data in form factor table
    And   New form factor button is clicked
    Then 	Entering the duplicate data in "<Form Factor Code>","<Description>" text box in form factor
    When 	Click on submit button in form factor page
    Then 	A popop with message already exists should be displayed in form factor page
    
    
    
    
    Examples: 
      | Form Factor Code  | Description |
      | FF2                 | desc2        |
     
     
      
      
     @Integration 
    Scenario Outline:  Validating the Form Factor master with blank data
    Given A web browser is on the Form Factor master page
    And  	New form factor button is clicked
    Then 	Entering the blank data in "<Form Factor Code>","<Description>" text box in form factor
    When  Click on submit button in form factor page
    Then 	A popop with message Please enter all mandatory fields should be displayed in form factor page
    
    
    
    Examples: 
      |Form Factor Code|Description|
      |                |           |
      
   @Integration   
  Scenario: Testing the functionality of edit button in Form Factor Master Page
     Given A web browser is on the Form Factor master page
     And   Set data in form factor table
     And   New form factor button is clicked
     And   Clicking the close button in form factor
     Then  Checking the functionality of edit button and by clicking in form factor
     Then  Checking the update button has shown in the form factor page
     Then  The edited data should be available in database of form factor
     
  
  @Integration
   Scenario: Testing the functionality of delete button in Form Factor Master Page
     Given A web browser is on the Form Factor master page
     And   Set data in form factor table
     Then  Checking the functionality of delete button by clicking in form factor
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in form factor
     Then  Verifying that the entered data was deleted in database of form factor
     
       @Integration
     Scenario: Testing search box and Export button in Form Factor Master
       Given  A web browser is on the Form Factor master page 
       And   Set data in form factor table
       Then   Searching the Form Factor code by sending keys
       And    The text in the search box should be equal to values in the Form Factor table
       Then   Clicking Export button in Form Factor master page
  
  