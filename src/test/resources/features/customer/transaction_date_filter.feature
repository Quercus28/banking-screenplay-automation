@customer @HU-11
Feature: HU-11 - Filter transactions by date
  As a customer,
  I want to filter my transactions by a date and time range,
  so that I only review the transactions of a specific period.

  # CA-HU11-01 (filter a period with preloaded transactions) is checked in
  # customer/transactions.feature (EXP-054).

  @CA-HU11-02 @EXP-052 @negative
  Scenario: Date filter without results
    Given the customer "Hermoine Granger" has logged in
    And the user selects the account "1001"
    When the customer views the account transactions
    And the customer filters the transactions from "2015-01-01T01:00" to "2015-01-01T23:00"
    Then the history shows no transactions

  @CA-HU11-03 @EXP-051 @alternative
  Scenario: Filter transactions by date range
    Given the customer "Ana QAPrueba" has an account in "Dollar"
    And the customer has made the transactions
      | type    | amount |
      | deposit | 1000   |
      | deposit | 500    |
    And one minute later the customer made a withdrawal of 200
    When the customer views the account transactions
    Then the history shows 3 transactions
    When the customer filters the transactions from the minute of the withdrawal to now
    Then the history shows the transactions
      | amount | type  |
      | 200    | Debit |

  @CA-HU11-04 @EXP-051 @negative @defect @HAL-09
  Scenario: Filter transactions with an end date in the future
    Given the customer "Ana QAPrueba" has an account in "Dollar"
    And the customer has made the transactions
      | type    | amount |
      | deposit | 1000   |
    When the customer views the account transactions
    And the customer sets the filter end date to 30 minutes in the future
    Then the user is told that the date range is not allowed
