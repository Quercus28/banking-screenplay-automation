@customer @HU-06
Feature: HU-06 - View the balance of each account
  As a customer,
  I want to switch between my accounts,
  so that I know the balance and currency of each one before making a transaction.

  # CA-HU06-02 (a transaction does not affect the other accounts) is checked in
  # customer/transactions.feature (EXP-050).

  @CA-HU06-01 @EXP-030 @alternative
  Scenario Outline: Switch between the customer's accounts
    Given the customer "Harry Potter" has logged in
    When the user selects the account "<account>"
    Then the account "<account>" shows balance 0 and currency "<currency>"

    Examples:
      | account | currency |
      | 1005    | Pound    |
      | 1006    | Rupee    |
