@FinancialCalendarMaster
Feature: FinancialCalendarMaster
Validation depends on the Business logic implemented for the Financial Calendar Master The Validation is set in a way that, once the Financial Calendar Master Data is fed into the 
system with the required Data such as Financial Cal  Code and Description No provision must be facilitated for adding the same set of Data into the system.

    @Smoke  
    Scenario: Test if Financial Calendar master screen opens
    When  Delete data initially from database in Financial Calendar
    Given A web browser is on the Financial Calendar master page
    When  New Financial Calendar button is clicked on page
    Then  A popup with Financial Calendar should be displayed when clicked
    And   A popup with submit button has shown in Financial Calendar page
    
    
    #valid
    @Integration
    Scenario Outline: Validating the Financial Calendar master with valid  data
    Given A web browser is on the Financial Calendar master page
    And   New Financial Calendar button is clicked
    Then 	Entering the data in "<Financial Cal Code>","<Prefix>","<Type>","<Year>","<Quarter>","<Num>","<From Date>","<To Date>","<Period Name>" text box in Financial Calendar
    When 	Click on submit button in Financial Calendar page
    Then 	A popop with message successfully added should be displayed in Financial Calendar page
    Then  The entered data is available in Financial Calendar table
  
    Examples: 
      | Financial Cal Code  | Prefix |Type |Year |Quarter     |Num  |From Date  |To Date       |Period Name|
      |1901                 |Current |year |2021 |2           | 33  |02-03-2021 |11-12-2021    | September |              
   
      #duplicate
    @Integration 
    Scenario Outline:  Validating the Financial Calendar master with duplicate data
    Given A web browser is on the Financial Calendar master page
    And   Set data in Financial Calendar table
    And   New Financial Calendar button is clicked
    Then 	Entering the duplicate data in "<Financial Cal Code>","<Prefix>","<Type>","<Year>","<Quarter>","<Num>","<From Date>","<To Date>","<Period Name>" text box in Financial Calendar
    When 	Click on submit button in Financial Calendar page
    Then 	A popop with message already exists should be displayed in Financial Calendar page
    Then  The entered data should not be available in Financial Calendar table for duplicate data
    
    
    Examples: 
      | Financial Cal Code  | Prefix |Type |Year |Quarter     |Num  |From Date  |To Date       |Period Name|
      |1901                 |Current |year |2021 |2           | 33  |02-03-2021 |11-12-2021    | September |  
  
     
     #invalid data,using characters for year,quarter,num & changing from to to date in reverse order
       @Integration 
    Scenario Outline:  Validating the Financial Calendar master with invalid data by using future date for From Date & past date for To Date & (using alphabets in year,quarter,num)
    Given A web browser is on the Financial Calendar master page
    And   Set data in Financial Calendar table
    And   New Financial Calendar button is clicked
    Then 	Entering the invalid data in "<Financial Cal Code>","<Prefix>","<Type>","<Year>","<Quarter>","<Num>","<From Date>","<To Date>","<Period Name>" text box in Financial Calendar
    When 	Click on submit button in Financial Calendar page
    Then  The entered data should not be available in Financial Calendar table for invalid data
    
      
    Examples: 
      | Financial Cal Code  | Prefix |Type |Year     |Quarter     |Num   |From Date  |To Date       |Period Name|
      |1901                 |Current |year |Year     | Quarter    | Num  |02-03-2021 |11-12-2021    | September |
      |1901                 |Current |year |2021     |2           | 33   |11-12-2021 | 02-03-2021   | September |
       
      
      #blank data
     @Integration 
    Scenario Outline:  Validating the Financial Calendar master with blank data
    Given A web browser is on the Financial Calendar master page
    And  	New Financial Calendar button is clicked
    Then 	Entering the blank data in "<Financial Cal Code>","<Prefix>","<Type>","<Year>","<Quarter>","<Num>","<From Date>","<To Date>","<Period Name>" text box in Financial Calendar
    When  Click on submit button in Financial Calendar page
    Then 	A popop with message Please enter all mandatory fields should be displayed in Financial Calendar page
    Then  The enterd data should not be available in Financial Calendar Database for blank data
    
    
    Examples: 
      | Financial Cal Code  | Prefix |Type |Year |Quarter     |Num  |From Date  |To Date       |Period Name|
      |1901                 |        |     |     |            |     |           |              | September |
    
    
    #edit functionality  
   @Integration   
  Scenario Outline: Testing the functionality of edit button in Financial Calendar Master Page
     Given A web browser is on the Financial Calendar master page
     And   Set data in Financial Calendar table
     And   New Financial Calendar button is clicked
     And   Clicking the close button in Financial Calendar
     Then  Checking the functionality of edit button in "<Financial Cal Code>","<Prefix>","<Type>","<Year>","<Quarter>","<Num>","<From Date>","<To Date>","<Period Name>" by clicking in Financial Calendar
     Then  Checking the update button has shown in the Financial Calendar page
     Then  The edited data should be available in database of Financial Calendar
     
     
     Examples: 
      | Financial Cal Code  | Prefix   |Type   |Year |Quarter     |Num  |From Date  |To Date       |Period Name |
      |1901                 |E-Current |E-year |2020 |3           | 44  |03-04-2021 |10-11-2021    |E-September |              
  
  #delete data
  @Integration
   Scenario: Testing the functionality of delete button in Financial Calendar Master Page
     Given A web browser is on the Financial Calendar master page
     And   Set data in Financial Calendar table
     Then  Checking the functionality of delete icon by clicking in Financial Calendar
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Financial Calendar
     Then  Verifying that the entered data was deleted in database of Financial Calendar
     
     #search functionality
   @Integration
     Scenario: Testing search box and Export button in Financial Calendar Master
       Given  A web browser is on the Financial Calendar master page 
       And    Set data in Financial Calendar table
       Then   Searching the Financial Cal  Code by sending keys
       And    The text in the search box should be equal to values in the Financial Calendar table
       Then   Clicking Export button in Financial Calendar master page
  
  