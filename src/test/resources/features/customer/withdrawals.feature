@customer @HU-09
Feature: HU-09 - Withdraw money
  As a customer,
  I want to withdraw money from one of my accounts,
  so that I can use my balance when I need it.

  Background:
    Given the customer "Ana QAPrueba" has an account in "Dollar"

  @CA-HU09-01 @EXP-042 @happy-path
  Scenario: Valid withdrawal
    Given the account has a balance of 1850
    When the customer withdraws 300
    Then the message "Transaction successful" is displayed
    And the account balance is 1550

  @CA-HU09-03 @EXP-043 @negative
  Scenario: Withdrawal greater than the balance
    Given the account has a balance of 1550
    When the customer withdraws 2550
    Then the message "Transaction Failed. You can not withdraw amount more than the balance." is displayed
    And the account balance is 1550

  @CA-HU09-02 @CA-HU09-03 @EXP-044 @alternative
  Scenario: Withdraw the exact total balance and withdraw again with a zero balance
    Given the account has a balance of 1550
    When the customer withdraws 1550
    Then the message "Transaction successful" is displayed
    And the account balance is 0
    When the customer withdraws 1
    Then the message "Transaction Failed. You can not withdraw amount more than the balance." is displayed
    And the account balance is 0

  @CA-HU09-05 @EXP-045 @negative @defect @HAL-05
  Scenario: Zero withdrawal
    Given the account has a balance of 1550
    When the customer withdraws 0
    Then an invalid amount error message is displayed
    And the account balance is 1550

  @CA-HU09-06 @EXP-046 @negative @defect @HAL-05
  Scenario: Negative withdrawal
    Given the account has a balance of 1550
    When the customer withdraws -50
    Then an invalid amount error message is displayed
    And the account balance is 1550

  @CA-HU09-04 @EXP-047 @negative
  Scenario: Withdrawal with an empty field
    Given the account has a balance of 1550
    When the customer submits the withdrawal without an amount
    Then the amount field shows the required field validation
    And the account balance is 1550

  @EXP-048 @negative @HAL-15
  Scenario: Withdrawal with a decimal value
    Given the account has a balance of 1550
    When the customer withdraws 5.5
    Then the amount field shows the invalid value validation
    And the account balance is 1550

  @EXP-048 @negative @HAL-15
  Scenario: Withdrawal with non-numeric characters
    Given the account has a balance of 1550
    When the customer tries to type "xyz" as the withdrawal amount and submits it
    Then the amount field is empty
    And the amount field shows the required field validation
    And the account balance is 1550
