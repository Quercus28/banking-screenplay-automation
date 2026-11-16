@customer @HU-08
Feature: HU-08 - Deposit money
  As a customer,
  I want to deposit money into one of my accounts,
  so that I increase the balance I have available.

  Background:
    Given the customer "Ana QAPrueba" has an account in "Dollar"

  @CA-HU08-01 @CA-HU02-02 @EXP-035 @happy-path
  Scenario: Valid deposit
    Given the account has a balance of 0
    When the customer deposits 1000
    Then the message "Deposit Successful" is displayed
    And the account balance is 1000
    And the amount field is empty

  @CA-HU08-02 @EXP-036 @alternative
  Scenario: Consecutive deposits
    Given the account has a balance of 1000
    When the customer deposits 500
    Then the message "Deposit Successful" is displayed
    And the account balance is 1500
    When the customer deposits 250
    Then the message "Deposit Successful" is displayed
    And the account balance is 1750

  @CA-HU08-04 @EXP-037 @negative @defect @HAL-03 @HAL-04
  Scenario: Zero deposit right after a successful deposit
    Given the account has a balance of 1500
    And the customer has just deposited 250 successfully
    When the customer deposits 0
    Then an invalid amount error message is displayed
    And the message "Deposit Successful" is not displayed
    And the account balance is 1750

  @CA-HU08-04 @EXP-037 @negative @defect @HAL-03
  Scenario: Zero deposit with a freshly opened form
    Given the account has a balance of 1750
    When the customer deposits 0
    Then an invalid amount error message is displayed
    And the account balance is 1750

  @CA-HU08-05 @EXP-038 @negative @defect @HAL-03
  Scenario: Negative deposit
    Given the account has a balance of 1750
    When the customer deposits -100
    Then an invalid amount error message is displayed
    And the account balance is 1750

  @CA-HU08-03 @EXP-039 @negative
  Scenario: Deposit with an empty field
    Given the account has a balance of 1750
    When the customer submits the deposit without an amount
    Then the amount field shows the required field validation
    And the account balance is 1750

  @EXP-040 @alternative @HAL-15
  Scenario: Deposit with a decimal value
    Given the account has a balance of 1750
    When the customer deposits 10.5
    Then the amount field shows the invalid value validation
    And the account balance is 1750

  @EXP-041 @negative @HAL-15
  Scenario: Deposit with non-numeric characters
    Given the account has a balance of 1750
    When the customer tries to type "abc" as the deposit amount and submits it
    Then the amount field is empty
    And the amount field shows the required field validation
    And the account balance is 1750

  @EXP-041 @alternative @HAL-15
  Scenario: Deposit with exponential notation
    Given the account has a balance of 1750
    When the customer tries to type "1e2" as the deposit amount and submits it
    Then the message "Deposit Successful" is displayed
    And the account balance is 1850
