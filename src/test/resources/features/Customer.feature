@CustomerMaster
Feature: Customer Master
Validation depends on the Business logic implemented for the Customer MasterThe Validation 
is set in a way that, once an Customer Data is fed into the system With the required Data such as Customer Code
 and other required Details-No provision must be facilitated for adding the same set of Data into the system
 
  @Smoke
  Scenario: Test if Customer Master screen opens
     When  Delete data initially from database in Customer 
     Given A web browser is on the Customer Master page
     When  Add new Customer button is clicked
     Then  Popup with title as Customer Master should be displayed with Submit button in Customer Page
     
     #(1)valid data AddressUseType=Bill To,Ship To & OutsideProcess=Yes,No
  @Integration    
  Scenario Outline: Validating business logic of Customer Master with valid test data
     Given A web browser is on the Customer Master page
     When  Add new Customer button is clicked
     Then  Entering valid data for set of "<CustomerCode>","<CustomerName>","<LegacyCustomerCode>","<CustomerShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" in the input box for CustomerForm
     And   Click the submit button in Customer form
     Then  A Popup with saved successfully should be displayed in Customer Master Page
     Then  The entered data should be available in Customer table 
     
    
    Examples: 
       |CustomerCode|CustomerName|LegacyCustomerCode|CustomerShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |CSTC23|Ram|LCC2|CST_SHT_NM|02-03-2015|Bill To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|ATFGH7891K|33ATFgh7891LKAZ|321234568TN|
       |CSTC23|Ram|LCC3|CST_SHT_NM|02-03-2015|Ship To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|ATFGH7891K|33ATFgh7891LKAZ|321234568TN|
 
  
 
      
      #(3)duplicate data 
      @Integration
   Scenario Outline: Validating business logic of Customer Master with duplicate test for Customer code,TIN,PAN,GST data added before the scenario execution
      Given A web browser is on the Customer Master page
      Then Set data in Customer table
      When  Add new Customer button is clicked
      Then  Entering duplicate data for set of "<CustomerCode>","<CustomerName>","<LegacyCustomerCode>","<CustomerShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" in the input box for CustomerForm
      And   Click the submit button in Customer form
      Then The enterd data should not be available in Customer Database for duplicate data
      Then  A popup with message already exists data should be displayed in Customer Page
      
     
       Examples:
       |CustomerCode|CustomerName|LegacyCustomerCode|CustomerShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |CSTC24|Ram|LCC4|CST_SHT_NM|02-03-2015|Ship To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|ATFGH7891K|33ATFgh7891LKAZ|321234568TN|

  #(4)boundary value analysis with less & more values
  @Integration
  Scenario Outline: Boundary Value Analysis and Equivalence class Partition for Customer Master in PAN,GST and TIN
       Given A web browser is on the Customer Master page
       Then Set data in Customer table
       When  Add new Customer button is clicked
       Then Entering data for "<CustomerCode>","<CustomerName>","<LegacyCustomerCode>","<CustomerShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" in which Gst,pan,Tin with less and more characters than normal in Customer Form             
       And Click the submit button in Customer form
       Then The enterd data should not be available in Customer Database for BVA test
       
       Examples:
       |CustomerCode|CustomerName|LegacyCustomerCode|CustomerShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road           |Area        |Country|State     |City       |PinCode|TimeZone|Region   |PrimaryTelephone|SecondaryTelephone|Email               |EmailAlternate     |PrimaryContact|SecondaryContact|PAN         |GST              |TIN          | 
       |CSTC_BV1    |Ram         |LCC4              |CST_SHT_NM       |02-03-2015      |Ship To       |D45|Seshalaya   |CoimbatoreRoad |Pallipalayam|India  |Tamil Nadu|Tirunelveli|638047 |GMT+5.30|SouthAsia|2366897         |546489            |sheshalaya@gmail.com|alternate@gmail.com|7837387389    |8747847848      |ATFGH7891KNN|33ATFgh7891LKAZNN|321234568TNNN|
       |CSTC_BV2    |Ram         |LCC4              |CST_SHT_NM       |02-03-2015      |Ship To       |D45|Seshalaya   |CoimbatoreRoad |Pallipalayam|India  |Tamil Nadu|Tirunelveli|638047 |GMT+5.30|SouthAsia|2366897         |546489            |sheshalaya@gmail.com|alternate@gmail.com|7837387389    |8747847848      |ATFGH789    |33ATFgh7891LK    |321234568    |
  
   
    #(5) entering invalid data for PAN,GST,TIN
    @Integration
  Scenario Outline: Entering invalid data for PAN,GST,TIN in Customer Form
       Given A web browser is on the Customer Master page
       Then Set data in Customer table
       When  Add new Customer button is clicked
       Then Entering invalid data for "<CustomerCode>","<CustomerName>","<LegacyCustomerCode>","<CustomerShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" with different characters and alphanumeric in Customer Form
       And Click the submit button in Customer form
       Then The enterd data should not be available in Customer Database for invalid character data
       
        Examples:
        |CustomerCode|CustomerName|LegacyCustomerCode|CustomerShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road           |Area        |Country|State     |City       |PinCode|TimeZone|Region   |PrimaryTelephone|SecondaryTelephone|Email               |EmailAlternate     |PrimaryContact|SecondaryContact|PAN       |GST            |TIN        | 
        |CSTC24      |Ram         |LCC4              |CST_SHT_NM       |02-03-2015     |Ship To        |D45|Seshalaya   |CoimbatoreRoad |Pallipalayam|India  |Tamil Nadu|Tirunelveli|638047 |GMT+5.30|SouthAsia|2366897         |546489            |sheshalaya@gmail.com|alternate@gmail.com|7837387389    |8747847848      |1234567890|123456789012345|12345678901|
        
   
     #(6) Blank data
    @Integration
  Scenario Outline: Validating business logic of Customer Master with blank test data
      Given A web browser is on the Customer Master page
      Then Set data in Customer table
      When   Add new Customer button is clicked
      Then  Entering blank data in "<CustomerCode>","<CustomerName>","<LegacyCustomerCode>","<CustomerShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>"  in the input box with blank data in Customer Form
      And   Click the submit button in Customer form
      Then  All input box should show red mark in Customer Master form
      Then The enterd data should not be available in Customer Database for blank data
      
      
      Examples:
       |CustomerCode|CustomerName|LegacyCustomerCode|CustomerShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |            |            |                  |                 |               |              |    |            |    |    |        |     |   |       |        |      |                |                   |    |              |              |                |   |   |   |
       |            |            |                  |                 | 02-03-2015    |              |    |            |    |    |        |     |   |       |        |      |                |                   |    |              |              |                |   |   |   |
     
     
      #(7) edit button
  @Integration
  Scenario Outline: Testing the functionality of Edit button in Customer Master page
     Given A web browser is on the Customer Master page
     Then   Set data in Customer table
     When  Add new Customer button is clicked
     Then  Clicking the close button in Customer Form
     Then  Checking the functionality of edit button in "<CustomerCode>","<CustomerName>","<LegacyCustomerCode>","<CustomerShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" by clicking in Customer Page
     Then  The edited data should be available in database of Customer Master
      
      
      Examples:
       |CustomerCode|CustomerName|LegacyCustomerCode|CustomerShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |edt24|E-Ram|E-LCC4|E-IND_SHT_NM|02-03-2016|Ship To|E-D45|E-Seshalaya|E-CoimbatoreRoad |E-Pallipalayam|India|Tamil Nadu|Chennai|638047|GMT+5.30|SouthAsia|2366897|546489|e-sheshalaya@gmail.com|e-alternate@gmail.com|7837387389|8747847848|e-ATFGH7891K|e-33ATFgh7891LKAZ|e-321234568TN|
      
      
     
     #(8) delete button
    @Integration  
   Scenario: Testing the functionality of Delete button in Customer Master page
     Given A web browser is on the Customer Master page
     Then Set data in Customer table
     Then  Checking the functionality of delete icon in Customer Master Page
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Customer Page
     Then  Verifying that the entered data was deleted in Customer database
   
   #(9) search box
    @Integration
     Scenario: Testing search box and Export button in Customer Master
       Given A web browser is on the Customer Master page 
       Then Set data in Customer table
       Then Searching the Customer code by sending keys
       And The text in the search box should be equal to values in the Customer table
       Then Clicking Export button in Customer Master page
      