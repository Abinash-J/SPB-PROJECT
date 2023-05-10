@IndentorMaster
Feature: Indentor Master
Validation depends on the Business logic implemented for the Indentor MasterThe Validation 
is set in a way that, once an Indentor Data is fed into the system With the required Data such as Indentor Code
 and other required Details-No provision must be facilitated for adding the same set of Data into the system
 
  @Smoke
  Scenario: Test if Indentor Master screen opens
     When  Delete data initially from database in Indentor 
     Given A web browser is on the Indentor Master page
     When  Add new Indentor button is clicked
     Then  Popup with title as Indentor Master should be displayed with Submit button in Indentor Page
     
     #(1)valid data AddressUseType=Bill To & OutsideProcess=Yes
  @Integration    
  Scenario Outline: Validating business logic of Indentor Master with valid test data1
     Given A web browser is on the Indentor Master page
     When  Add new Indentor button is clicked
     Then  Entering valid data for set of "<IndentorCode>","<IndentorName>","<LegacyIndentorCode>","<IndentorShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" in the input box for IndentorForm
     And   Click the submit button in Indentor form
     Then  A Popup with saved successfully should be displayed in Indentor Master Page
     Then  The entered data should be available in Indentor table 
     
    
    Examples: 
       |IndentorCode|IndentorName|LegacyIndentorCode|IndentorShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |INDC23|Stockist|LIC2|IND_SHT_NM|02-03-2015|Bill To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|ATFGH7891K|33ATFgh7891LKAZ|321234568TN|
 
 
   #(2)valid data AddressUseType=Ship To & OutsideProcess=No
  @Integration    
  Scenario Outline: Validating business logic of Indentor Master with valid test data2
     Given A web browser is on the Indentor Master page
     When  Add new Indentor button is clicked
     Then  Entering valid data for set of "<IndentorCode>","<IndentorName>","<LegacyIndentorCode>","<IndentorShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" in the input box for IndentorForm
     And   Click the submit button in Indentor form
     Then  A Popup with saved successfully should be displayed in Indentor Master Page
     Then  The entered data should be available in Indentor table 
     
    
    Examples: 
       |IndentorCode|IndentorName|LegacyIndentorCode|IndentorShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |INDC23|Stockist|LIC3|IND_SHT_NM|02-03-2015|Ship To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|ATFGH7891K|33ATFgh7891LKAZ|321234568TN|
 
 
      
      #(3)duplicate data 
      @Integration
   Scenario Outline: Validating business logic of Indentor Master with duplicate test for Indentor code,TIN,PAN,GST data added before the scenario execution
      Given A web browser is on the Indentor Master page
      Then Set data in Indentor table
      When  Add new Indentor button is clicked
      Then  Entering duplicate data for set of "<IndentorCode>","<IndentorName>","<LegacyIndentorCode>","<IndentorShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" in the input box for IndentorForm
      And   Click the submit button in Indentor form
      Then The enterd data should not be available in Indentor Database for duplicate data
      Then  A popup with message already exists data should be displayed in Indentor Page
      
     
       Examples:
       |IndentorCode|IndentorName|LegacyIndentorCode|IndentorShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |INDC24|Stockist|LIC4|IND_SHT_NM|02-03-2015|Ship To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|ATFGH7891K|33ATFgh7891LKAZ|321234568TN|

  #(4)boundary value analysis with less & more values
  @Integration
  Scenario Outline: Boundary Value Analysis and Equivalence class Partition for Indentor Master in PAN,GST and TIN
       Given A web browser is on the Indentor Master page
       Then   Set data in Indentor table
       When  Add new Indentor button is clicked
       Then Entering data for "<IndentorCode>","<IndentorName>","<LegacyIndentorCode>","<IndentorShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" in which Gst,pan,Tin with less and more characters than normal             
       And Click the submit button in Indentor form
       Then The enterd data should not be available in Indentor Database for BVA test
       
       Examples:
       |IndentorCode|IndentorName|LegacyIndentorCode|IndentorShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |INDC_BV1|Stockist|LIC4|IND_SHT_NM|02-03-2015|Ship To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|ATFGH7891KNN|33ATFgh7891LKAZNN|321234568TNNN|
       |INDC_BV2|Stockist|LIC4|IND_SHT_NM|02-03-2015|Ship To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|ATFGH789|33ATFgh7891LK|321234568|
  
   
    #(5) entering invalid data for PAN,GST,TIN
    @Integration
  Scenario Outline: Entering invalid data for PAN,GST,TIN
       Given A web browser is on the Indentor Master page
       Then   Set data in Indentor table
       When  Add new Indentor button is clicked
       Then Entering invalid data for "<IndentorCode>","<IndentorName>","<LegacyIndentorCode>","<IndentorShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" with different characters and alphanumeric
       And Click the submit button in Indentor form
       Then The enterd data should not be available in Indentor Database for invalid character data
       
        Examples:
        |IndentorCode|IndentorName|LegacyIndentorCode|IndentorShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
        |INDC24|Stockist|LIC4|IND_SHT_NM|02-03-2015|Ship To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|1234567890|123456789012345|12345678901|
        
   
     #(6) Blank data
    @Integration
  Scenario Outline: Validating business logic of Indentor Master with blank test data
      Given A web browser is on the Indentor Master page
      Then   Set data in Indentor table
      When   Add new Indentor button is clicked
      Then  Entering blank data in "<IndentorCode>","<IndentorName>","<LegacyIndentorCode>","<IndentorShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>"  in the input box with blank data in Indentor Form
      And   Click the submit button in Indentor form
      Then  All input box should show red mark in Indentor Master form
      Then The enterd data should not be available in Indentor Database for blank data
      
      
      Examples:
       |IndentorCode|IndentorName|LegacyIndentorCode|IndentorShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |            |            |                  |                 |               |              |    |            |    |    |        |     |   |       |        |      |                |                   |    |              |              |                |   |   |   |
       |            |            |                  |                 | 02-03-2015    |              |    |            |    |    |        |     |   |       |        |      |                |                   |    |              |              |                |   |   |   |
     
     
      #(7) edit button
  @Integration
  Scenario Outline: Testing the functionality of Edit button in Indentor Master page
     Given A web browser is on the Indentor Master page
     Then   Set data in Indentor table
     When  Add new Indentor button is clicked
     Then  Clicking the close button in Indentor Form
     Then  Checking the functionality of edit button in "<IndentorCode>","<IndentorName>","<LegacyIndentorCode>","<IndentorShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" by clicking in Indentor Page
     Then  The edited data should be available in database of Indentor Master
      
      
      Examples:
       |IndentorCode|IndentorName|LegacyIndentorCode|IndentorShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |edt24|E-Stockist|E-LIC4|E-IND_SHT_NM|02-03-2016|Ship To|E-D45|E-Seshalaya|E-CoimbatoreRoad |E-Pallipalayam|India|Tamil Nadu|Chennai|638047|GMT+5.30|SouthAsia|2366897|546489|e-sheshalaya@gmail.com|e-alternate@gmail.com|7837387389|8747847848|e-ATFGH7891K|e-33ATFgh7891LKAZ|e-321234568TN|
      
      
     
     #(8) delete button
    @Integration  
   Scenario: Testing the functionality of Delete button in Indentor Master page
     Given A web browser is on the Indentor Master page
     Then   Set data in Indentor table
     Then  Checking the functionality of delete icon in Indentor Master Page
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Indentor Page
     Then  Verifying that the edited data was deleted in Indentor database
   
   #(9) search box
    @Integration
     Scenario: Testing search box and Export button in Indentor Master
       Given A web browser is on the Indentor Master page 
       Then Set data in Indentor table
       Then Searching the Indentor code by sending keys
       And The text in the search box should be equal to values in the Indentor table
       Then Clicking Export button in Indentor Master page
      