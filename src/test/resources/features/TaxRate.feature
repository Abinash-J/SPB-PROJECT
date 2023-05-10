@TaxRateMaster
Feature: TaxRateMaster
Validation depends on the Business logic implemented for the Tax  Rate Master The Validation is set in a way that, once the Tax  Rate Master Data is fed into the 
system with the required Data such as Tax Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke1  
    Scenario: Test if Tax  Rate master screen opens
    When  Delete data initially from database in Tax  Rate
    Given A web browser is on the Tax  Rate master page
    When  New Tax  Rate button is clicked on page
    Then  A popup with Tax  Rate should be displayed when clicked
    And   A popup with submit button has shown in Tax  Rate page
    
    
    #valid data
    @Integration
    Scenario Outline: Validating the Tax  Rate master with valid  data
    Given A web browser is on the Tax  Rate master page
    And   New Tax  Rate button is clicked
    Then 	Entering the data in "<Tax Code>","<Tax Type>","<Description>","<Rate>","<Effective From>","<Effective To>" text box in Tax  Rate
    When 	Click on submit button in Tax  Rate page
    Then 	A popop with message successfully added should be displayed in Tax  Rate page
    Then  The entered data is available in Tax  Rate table
  
    Examples: 
      | Tax Code   |Tax Type    | Description   |Rate   |Effective From|Effective To|
      | VAT-V      |Inter State | VAT desc      |6.00   |29-07-2021    |11-12-2021  | 
      | VAT-V      |Intra State | VAT desc      |12.00  |29-07-2021    |11-12-2021  |
      | VAT-V      |Export      | VAT desc      |0.10   |29-07-2021    |11-12-2021  |             
   
     
     #duplicate data 
    @Integration 
    Scenario Outline:  Validating the Tax  Rate master with duplicate data
    Given A web browser is on the Tax  Rate master page
    And   Set data in Tax  Rate table
    And   New Tax  Rate button is clicked
    Then 	Entering the duplicate data in "<Tax Code>","<Tax Type>","<Description>","<Rate>","<Effective From>","<Effective To>" text box in Tax  Rate
    When 	Click on submit button in Tax  Rate page
    Then 	A popop with message already exists should be displayed in Tax  Rate page
    Then  The entered data should not be available in Tax  Rate table for duplicate data
    
    
     
    Examples: 
      | Tax Code   |Tax Type    | Description   |Rate   |Effective From|Effective To|
      | VAT-D      |Inter State | VAT desc      |6.00   |29-07-2021    |11-12-2021  |  
      
     
     # invalid data validation for date and rate
    @Integration 
    Scenario Outline:  Validating the Tax  Rate master with invalid data by using future date for Effective From & past date for Effective To
    Given A web browser is on the Tax  Rate master page
    And   Set data in Tax  Rate table
    And   New Tax  Rate button is clicked
    Then 	Entering the invalid data in "<Tax Code>","<Tax Type>","<Description>","<Rate>","<Effective From>","<Effective To>" text box in Tax  Rate
    When 	Click on submit button in Tax  Rate page
    Then  The entered data should not be available in Tax  Rate table for invalid data
    
     
    Examples: 
      | Tax Code   |Tax Type    | Description   |Rate   |Effective From|Effective To|
      | VAT-I      |Inter State | VAT desc      |6.00   |11-12-2021    |29-07-2021  |  
      
     #blank data 
     @Integration 
    Scenario Outline:  Validating the Tax  Rate master with blank data
    Given A web browser is on the Tax  Rate master page
    And  	New Tax  Rate button is clicked
    Then 	Entering the blank data in "<Tax Code>","<Tax Type>","<Description>","<Rate>","<Effective From>","<Effective To>" text box in Tax  Rate
    When  Click on submit button in Tax  Rate page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Tax  Rate page
    Then  The entered data should not be available in Tax  Rate table for blank data
    
    
    
    Examples: 
      | Tax Code |Tax Type    | Description   |Rate   |Effective From|Effective To|
      |          |            |               |       |              |            |
     
     #edit functionality 
   @Integration   
  Scenario Outline: Testing the functionality of edit button in Tax  Rate Master Page
     Given A web browser is on the Tax  Rate master page
     And   Set data in Tax  Rate table
     And   New Tax  Rate button is clicked
     And   Clicking the close button in Tax  Rate
     Then  Checking the functionality of edit button in "<Tax Code>","<Tax Type>","<Description>","<Rate>","<Effective From>","<Effective To>" by clicking in Tax  Rate
     Then  Checking the update button has shown in the Tax  Rate page
     Then  The edited data should be available in database of Tax  Rate
     
     Examples: 
      | Tax Code   |Tax Type    | Description     |Rate   |Effective From|Effective To|
      | VAT-E      |Inter State | VAT-E desc      |6.00   |29-07-2021    |11-12-2021  |
     
  #delete functionality
  @Integration
   Scenario: Testing the functionality of delete button in Tax  Rate Master Page
     Given A web browser is on the Tax  Rate master page
     And   Set data in Tax  Rate table
     Then  Checking the functionality of delete icon by clicking in Tax  Rate
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Tax  Rate
     Then  Verifying that the entered data was deleted in database of Tax  Rate
     
     #search functionality
   @Integration
     Scenario: Testing search box and Export button in Tax  Rate Master
       Given  A web browser is on the Tax  Rate master page 
       And    Set data in Tax  Rate table
       Then   Searching the Tax Code by sending keys
       And    The text in the search box should be equal to values in the Tax  Rate table
       Then   Clicking Export button in Tax  Rate master page
  
  