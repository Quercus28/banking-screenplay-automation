@customer @HU-10
Feature: HU-10 - View transactions
  As a customer,
  I want to see my account's transaction history,
  so that I can review the deposits and withdrawals I have made.

  @CA-HU10-01 @CA-HU10-02 @EXP-049 @happy-path
  Scenario: View the transaction history
    Given the customer "Ana QAPrueba" has an account in "Dollar"
    And the customer has made the transactions
      | type       | amount |
      | deposit    | 1000   |
      | deposit    | 500    |
      | withdrawal | 300    |
      | withdrawal | 5000   |
    When the customer views the account transactions
    Then the application route is "#/listTx"
    And the history shows the transactions
      | amount | type   |
      | 1000   | Credit |
      | 500    | Credit |
      | 300    | Debit  |
    And the date filters and the "Back" and "Reset" buttons are displayed

  @CA-HU10-03 @CA-HU06-02 @CA-HU02-02 @EXP-050 @alternative
  Scenario: View the transactions of an account without transactions
    Given the customer "Ana QAPrueba" has an account in "Pound"
    When the customer views the account transactions
    Then the history shows no transactions
    And the date filters and the "Reset" button are not displayed
    And the "Back" button is displayed

  @CA-HU10-04 @EXP-053 @alternative @HAL-12
  Scenario: Sort transactions by Date-Time
    Given the customer "Ana QAPrueba" has an account in "Dollar"
    And the customer has made the transactions
      | type       | amount |
      | deposit    | 1000   |
      | deposit    | 500    |
      | withdrawal | 300    |
    When the customer views the account transactions
    And the customer sorts the transactions by "Date-Time"
    Then the transactions are sorted by date in descending order
    When the customer sorts the transactions by "Date-Time"
    Then the transactions are sorted by date in ascending order

  @CA-HU10-05 @CA-HU11-01 @EXP-054 @alternative
  Scenario: Navigate with Back and the scroll controls
    Given the customer "Hermoine Granger" has logged in
    And the user selects the account "1001"
    When the customer views the account transactions
    Then the ">" control is displayed but not the "<" and "Top" controls
    When the user clicks the ">" control
    Then the "<" and "Top" controls are displayed
    When the user clicks the "Top" control
    Then the table goes back to the top of the history
    When the customer filters the transactions from "2015-01-01T00:00" to "2015-01-03T23:59"
    Then the history shows 6 transactions
    When the user clicks the "Back" button
    Then the application route is "#/account"
    And the selected account is "1001"
