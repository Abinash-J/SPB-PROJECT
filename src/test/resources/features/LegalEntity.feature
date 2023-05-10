@legal
Feature: Legal Entity Master
  Legal entity is a master table. It references org_group table. The orgGroupId field
  references the id in org_group table.
  The major criteria with which the Legal Entity deals is the Validation for the Group Code, Legal Entity Code and Legal Name so the desired Manufactured product does not undergo complications.
  Validation depends on the Business logic implemented for the Legal Entity MasterThe Validation is set in a way that, once the Legal Entity Data is fed into the system with the required Data such as Group Code, Legal Entity and Legal Name No provision must be facilitated for adding the same set of Data into the system.

  @Smoke
    Scenario: Test if legal entity screen opens
    Given A web browser is on the LegalEntity page
    When  New Legal Entity button is clicked
    Then  A popup with id as entity-modal should be displayed with Submit button in LegalEntityPage

   @Integration
    Scenario Outline: Validating business logic of LegalEntityMaster with suitable test data
     Given A web browser is on the LegalEntity page
     When  New Legal Entity button is clicked
     Then  Entering valid "<LegalEntityCode>" and "<LegalEntityName>" in the LegalEntity form input box
     And   Click the LegalEntity-submit button
     Then  The entered data should be available in LegalEntity table
    
    Examples:
      |LegalEntityCode|LegalEntityName|
      |001|PaperManufacturing|
      |Abc01|paper|
      
      
      
   @Integration   
      Scenario Outline: Validating business logic of LegalEntityMaster with duplicate test data added before the scenario execution
      Given A web browser is on the LegalEntity page
      And   Set data in LegalEntity table
      When  New Legal Entity button is clicked
      Then  Entering duplicate data in "<LegalEntityCode>" and "<LegalEntityName>" in the LegalEntity form input box
      And   Click the LegalEntity-submit button
      Then  A popup with message already exists data should be displayed for adding duplicate LegalEntityCode
      
   Examples:
     | LegalEntityCode |LegalEntityName|
     |002|Paper|
      
  @Integration    
   Scenario Outline: Validating business logic of LegalEntityMaster with blank test data
     Given  A web browser is on the LegalEntity page
      And   New Legal Entity button is clicked
      Then  Entering blank data in "<LegalEntityCode>" and "<LegalEntityName>" in the LegalEntity form input box
      And   Click the LegalEntity-submit button
      Then  A popup with message please enter all mandatory fields for LegalEntity should be displayed
      
      Examples:
      |LegalEntityCode|LegalEntityName|
      |               |               |
      
   @Integration
  Scenario: Testing the functionality of Edit button in LegalEntityMaster page
     Given A web browser is on the LegalEntity page
#     And   Set data in LegalEntity table
     When  New Legal Entity button is clicked
     Then  Clicking the LegalEntity form close button
     Then  Checking the functionality of LegalEntity edit button by clicking
     Then  The edited data should be available in database of legalentity
    
   @Integration
     Scenario: Testing the functionality of Delete button in LegalEntityMaster page
     Given A web browser is on the LegalEntity page
     And   Set data in LegalEntity table
     Then  Checking the functionality of delete icon button in LegalEntityMaster Page
     Then  Clicking delete button and a popup deleted sucessfully should be displayed in LegalEntity page
     
  @Integration
  
       Scenario: Testing search box and Export button in legalentity
       Given A web browser is on the LegalEntity page 
       And Set data in LegalEntity table
       Then Searching the legal code by sending keys
       And The text in the search box should be equal to values in the table
       Then Clicking Export button in legalentity page
   
      
    
  
  





