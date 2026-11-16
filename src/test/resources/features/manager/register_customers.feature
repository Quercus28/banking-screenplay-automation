@manager @HU-01
Feature: HU-01 - Register customers
  As a bank manager,
  I want to register a new customer with their first name, last name and post code,
  so that they are enrolled in the bank and an account can be opened for them.

  Background:
    Given the manager is on the "Add Customer" tab

  @CA-HU01-01 @EXP-007 @happy-path
  Scenario: Register a customer with valid data
    When the manager registers a customer with first name "Ana", last name "QAPrueba" and post code "QA0001"
    Then the alert "Customer added successfully with customer id :" is displayed followed by the assigned id
    And the registration form is cleared
    And the customer "Ana" "QAPrueba" with post code "QA0001" appears in the list without an account number

  @CA-HU01-01 @EXP-008 @alternative
  Scenario: Register a second customer with a different first and last name
    Given the manager has registered a customer with first name "Ana", last name "QAPrueba" and post code "QA0001"
    When the manager registers a customer with first name "Luis", last name "QAExplora" and post code "QA0002"
    Then the assigned id follows the previous customer's id
    And the registration form is cleared

  @CA-HU01-02 @EXP-009 @negative
  Scenario: Submit the Add Customer form completely empty
    When the manager submits the registration form without filling in any field
    Then the field "First Name" shows the required field validation
    And no alert is displayed
    And no new customer is registered

  @CA-HU01-03 @EXP-010 @negative
  Scenario Outline: Leave a required field empty
    When the manager registers a customer with first name "<first_name>", last name "<last_name>" and post code "<post_code>"
    Then the field "<missing_field>" shows the required field validation
    And no new customer is registered

    Examples:
      | first_name | last_name | post_code | missing_field |
      | Carla      | QAOmision |           | Post Code     |
      | Carla      |           | QA0003    | Last Name     |

  @CA-HU01-04 @EXP-011 @alternative @HAL-08
  Scenario: Register a customer with special characters
    When the manager registers a customer with first name "José-María", last name "O'Brien Ñúñez" and post code "@#$%&*"
    Then the alert "Customer added successfully with customer id :" is displayed followed by the assigned id
    And the customer "José-María" "O'Brien Ñúñez" with post code "@#$%&*" appears in the list without an account number

  @EXP-012 @negative @defect @HAL-08
  Scenario: Register a customer with a numeric first and last name
    When the manager registers a customer with first name "12345", last name "67890" and post code "00000"
    Then the system rejects the registration because of an invalid name format
    And no new customer is registered

  @CA-HU01-07 @EXP-013 @negative @defect @HAL-06
  Scenario: Submit fields that contain only blank spaces
    When the manager registers a customer with first name "   ", last name "   " and post code "   "
    Then the system reports that the fields are required or invalid
    And the alert "Please check the details. Customer may be duplicate." is not displayed
    And no new customer is registered

  @CA-HU01-05 @EXP-014 @negative
  Scenario: Register a duplicate customer with the same data
    Given the manager has registered a customer with first name "Ana", last name "QAPrueba" and post code "QA0001"
    When the manager registers a customer with first name "Ana", last name "QAPrueba" and post code "QA0001"
    Then the alert "Please check the details. Customer may be duplicate." is displayed
    And searching for "QAPrueba" in the customer list returns 1 customer

  @CA-HU01-06 @EXP-015 @alternative
  Scenario: Namesake with a different post code
    Given the manager has registered a customer with first name "Ana", last name "QAPrueba" and post code "QA0001"
    When the manager registers a customer with first name "Ana", last name "QAPrueba" and post code "QA9999"
    Then the alert "Customer added successfully with customer id :" is displayed followed by the assigned id

  @EXP-015 @alternative @defect @HAL-07
  Scenario: Duplicate with different capitalization
    Given the manager has registered a customer with first name "Ana", last name "QAPrueba" and post code "QA0001"
    When the manager registers a customer with first name "ana", last name "qaprueba" and post code "QA0001"
    Then the alert "Please check the details. Customer may be duplicate." is displayed
    And searching for "prueba" in the customer list returns 1 customer
