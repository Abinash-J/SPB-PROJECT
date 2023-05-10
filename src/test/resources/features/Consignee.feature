@ConsigneeMaster
Feature: Consignee Master
Validation depends on the Business logic implemented for the Consignee MasterThe Validation 
is set in a way that, once an Consignee Data is fed into the system With the required Data such as Consignee Code
 and other required Details-No provision must be facilitated for adding the same set of Data into the system
 
  @Smoke
  Scenario: Test if Consignee Master screen opens
     When  Delete data initially from database in Consignee 
     Given A web browser is on the Consignee Master page
     When  Add new Consignee button is clicked
     Then  Popup with title as Consignee Master should be displayed with Submit button in Consignee Page
     
     #(1)valid data AddressUseType=Bill To,Ship To & OutsideProcess=Yes,No
  @Integration    
  Scenario Outline: Validating business logic of Consignee Master with valid test data
     Given A web browser is on the Consignee Master page
     When  Add new Consignee button is clicked
     Then  Entering valid data for set of "<ConsigneeCode>","<ConsigneeName>","<LegacyConsigneeCode>","<ConsigneeShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" in the input box for ConsigneeForm
     And   Click the submit button in Consignee form
     Then  A Popup with saved successfully should be displayed in Consignee Master Page
     Then  The entered data should be available in Consignee table 
     
    
    Examples: 
       |ConsigneeCode|ConsigneeName|LegacyConsigneeCode|ConsigneeShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |CNSC23|Ram|LCNSC2|CNS_SHT_NM|02-03-2015|Bill To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|ATFGH7891K|33ATFgh7891LKAZ|321234568TN|
       |CNSC23|Ram|LCNSC3|CNS_SHT_NM|02-03-2015|Ship To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|ATFGH7891K|33ATFgh7891LKAZ|321234568TN|
 
  
 
      
      #(3)duplicate data 
      @Integration
   Scenario Outline: Validating business logic of Consignee Master with duplicate test for Consignee code,TIN,PAN,GST data added before the scenario execution
      Given A web browser is on the Consignee Master page
      Then Set data in Consignee table
      When  Add new Consignee button is clicked
      Then  Entering duplicate data for set of "<ConsigneeCode>","<ConsigneeName>","<LegacyConsigneeCode>","<ConsigneeShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" in the input box for ConsigneeForm
      And   Click the submit button in Consignee form
      Then The enterd data should not be available in Consignee Database for duplicate data
      Then  A popup with message already exists data should be displayed in Consignee Page
      
     
       Examples:
       |ConsigneeCode|ConsigneeName|LegacyConsigneeCode|ConsigneeShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |CNSC24|Ram|LCNSC4|CNS_SHT_NM|02-03-2015|Ship To|D45|Seshalaya|CoimbatoreRoad |Pallipalayam|India|Tamil Nadu|Tirunelveli|638047|GMT+5.30|SouthAsia|2366897|546489|sheshalaya@gmail.com|alternate@gmail.com|7837387389|8747847848|ATFGH7891K|33ATFgh7891LKAZ|321234568TN|

  #(4)boundary value analysis with less & more values
  @Integration
  Scenario Outline: Boundary Value Analysis and Equivalence class Partition for Consignee Master in PAN,GST and TIN
       Given A web browser is on the Consignee Master page
       Then Set data in Consignee table
       When  Add new Consignee button is clicked
       Then Entering data for "<ConsigneeCode>","<ConsigneeName>","<LegacyConsigneeCode>","<ConsigneeShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" in which Gst,pan,Tin with less and more characters than normal in Consignee Form             
       And Click the submit button in Consignee form
       Then The enterd data should not be available in Consignee Database for BVA test
       
       Examples:
       |ConsigneeCode|ConsigneeName|LegacyConsigneeCode|ConsigneeShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road           |Area        |Country|State     |City       |PinCode|TimeZone|Region   |PrimaryTelephone|SecondaryTelephone|Email               |EmailAlternate     |PrimaryContact|SecondaryContact|PAN         |GST              |TIN          | 
       |CNSC_BV1    |Ram         |LCNSC4              |CNS_SHT_NM       |02-03-2015      |Ship To       |D45|Seshalaya   |CoimbatoreRoad |Pallipalayam|India  |Tamil Nadu|Tirunelveli|638047 |GMT+5.30|SouthAsia|2366897         |546489            |sheshalaya@gmail.com|alternate@gmail.com|7837387389    |8747847848      |ATFGH7891KNN|33ATFgh7891LKAZNN|321234568TNNN|
       |CNSC_BV2    |Ram         |LCNSC4              |CNS_SHT_NM       |02-03-2015      |Ship To       |D45|Seshalaya   |CoimbatoreRoad |Pallipalayam|India  |Tamil Nadu|Tirunelveli|638047 |GMT+5.30|SouthAsia|2366897         |546489            |sheshalaya@gmail.com|alternate@gmail.com|7837387389    |8747847848      |ATFGH789    |33ATFgh7891LK    |321234568    |
  
   
    #(5) entering invalid data for PAN,GST,TIN
    @Integration
  Scenario Outline: Entering invalid data for PAN,GST,TIN in Consignee Form
       Given A web browser is on the Consignee Master page
       Then Set data in Consignee table
       When  Add new Consignee button is clicked
       Then Entering invalid data for "<ConsigneeCode>","<ConsigneeName>","<LegacyConsigneeCode>","<ConsigneeShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" with different characters and alphanumeric in Consignee Form
       And Click the submit button in Consignee form
       Then The enterd data should not be available in Consignee Database for invalid character data
       
        Examples:
        |ConsigneeCode|ConsigneeName|LegacyConsigneeCode|ConsigneeShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road           |Area        |Country|State     |City       |PinCode|TimeZone|Region   |PrimaryTelephone|SecondaryTelephone|Email               |EmailAlternate     |PrimaryContact|SecondaryContact|PAN       |GST            |TIN        | 
        |CNSC24        |Ram         |LCNSC4              |CNS_SHT_NM       |02-03-2015     |Ship To        |D45|Seshalaya   |CoimbatoreRoad |Pallipalayam|India  |Tamil Nadu|Tirunelveli|638047 |GMT+5.30|SouthAsia|2366897         |546489            |sheshalaya@gmail.com|alternate@gmail.com|7837387389    |8747847848      |1234567890|123456789012345|12345678901|
        
   
     #(6) Blank data
    @Integration
  Scenario Outline: Validating business logic of Consignee Master with blank test data
      Given A web browser is on the Consignee Master page
      Then Set data in Consignee table
      When   Add new Consignee button is clicked
      Then  Entering blank data in "<ConsigneeCode>","<ConsigneeName>","<LegacyConsigneeCode>","<ConsigneeShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>"  in the input box with blank data in Consignee Form
      And   Click the submit button in Consignee form
      Then  All input box should show red mark in Consignee Master form
      Then The enterd data should not be available in Consignee Database for blank data
      
      
      Examples:
       |ConsigneeCode|ConsigneeName|LegacyConsigneeCode|ConsigneeShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |            |            |                  |                 |               |              |    |            |    |    |        |     |   |       |        |      |                |                   |    |              |              |                |   |   |   |
       |            |            |                  |                 | 02-03-2015    |              |    |            |    |    |        |     |   |       |        |      |                |                   |    |              |              |                |   |   |   |
     
     
      #(7) edit button
  @Integration
  Scenario Outline: Testing the functionality of Edit button in Consignee Master page
     Given A web browser is on the Consignee Master page
     Then   Set data in Consignee table
     When  Add new Consignee button is clicked
     Then  Clicking the close button in Consignee Form
     Then  Checking the functionality of edit button in "<ConsigneeCode>","<ConsigneeName>","<LegacyConsigneeCode>","<ConsigneeShortName>","<DateEstablished>","<AddressUseType>","<Flat>","<BuildingName>","<Road>","<Area>","<Country>","<State>","<City>","<PinCode>","<TimeZone>","<Region>","<PrimaryTelephone>","<SecondaryTelephone>","<Email>","<EmailAlternate>","<PrimaryContact>","<SecondaryContact>","<PAN>","<GST>","<TIN>" by clicking in Consignee Page
     Then  The edited data should be available in database of Consignee Master
      
      
      Examples:
       |ConsigneeCode|ConsigneeName|LegacyConsigneeCode|ConsigneeShortName|DateEstablished|AddressUseType|Flat|BuildingName|Road|Area|Country|State|City|PinCode|TimeZone|Region|PrimaryTelephone|SecondaryTelephone|Email|EmailAlternate|PrimaryContact|SecondaryContact|PAN|GST|TIN| 
       |edt24|E-Ram|E-LCNSC4|E-CNS_SHT_NM|02-03-2016|Ship To|E-D45|E-Seshalaya|E-CoimbatoreRoad |E-Pallipalayam|India|Tamil Nadu|Chennai|638047|GMT+5.30|SouthAsia|2366897|546489|e-sheshalaya@gmail.com|e-alternate@gmail.com|7837387389|8747847848|e-ATFGH7891K|e-33ATFgh7891LKAZ|e-321234568TN|
      
      
     
     #(8) delete button
    @Integration  
   Scenario: Testing the functionality of Delete button in Consignee Master page
     Given A web browser is on the Consignee Master page
     Then   Set data in Consignee table
     Then  Checking the functionality of delete icon in Consignee Master Page
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in Consignee Page
     Then  Verifying that the entered data was deleted in Consignee database
   
   #(9) search box
    @Integration
     Scenario: Testing search box and Export button in Consignee Master
       Given A web browser is on the Consignee Master page 
       Then Set data in Consignee table
       Then Searching the Consignee code by sending keys
       And The text in the search box should be equal to values in the Consignee table
       Then Clicking Export button in Consignee Master page
      