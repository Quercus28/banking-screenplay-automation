@customer @HU-12
Feature: HU-12 - Reset an account's history
  As a customer,
  I want to reset the history of one of my accounts,
  so that it has no transactions and I can start a new record.

  @CA-HU12-01 @EXP-055 @alternative @HAL-10
  Scenario: Reset transactions with Reset
    Given the customer "Ana QAPrueba" has an account in "Dollar"
    And the customer has made the transactions
      | type    | amount |
      | deposit | 700    |
    When the customer views the account transactions
    And the user clicks the "Reset" button
    Then the history shows no transactions
    When the user clicks the "Back" button
    Then the account balance is 0
