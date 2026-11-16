@manager @HU-02
Feature: HU-02 - Open accounts
  As a bank manager,
  I want to open an account for a customer in the currency they choose,
  so that they can start depositing and withdrawing money.

  # CA-HU02-02 (a new account starts with a zero balance) is checked in
  # customer/deposits.feature (EXP-035) and customer/transactions.feature (EXP-050).

  Background:
    Given the manager has registered the customers
      | first_name | last_name | post_code |
      | Ana        | QAPrueba  | QA0001    |
      | Luis       | QAExplora | QA0002    |
    And the manager goes to the "Open Account" tab

  @CA-HU02-01 @EXP-016 @happy-path
  Scenario: Open a Dollar account for a test customer
    Then the currency dropdown offers the options "Dollar", "Pound" and "Rupee"
    When the manager opens an account in "Dollar" for the customer "Ana QAPrueba"
    Then the alert "Account created successfully with account Number :" is displayed followed by the assigned account number
    And the customer and currency dropdowns go back to their empty option

  @CA-HU02-03 @EXP-017 @alternative
  Scenario: Open a second account in another currency for the same customer
    Given the manager has opened an account in "Dollar" for the customer "Ana QAPrueba"
    When the manager opens an account in "Pound" for the customer "Ana QAPrueba"
    Then the assigned account number follows the previous account number
    And the customer "Ana QAPrueba" has 2 accounts in the customer list

  @CA-HU02-03 @EXP-018 @alternative
  Scenario: Open a Rupee account for another customer and check the association
    Given the manager has opened an account in "Dollar" for the customer "Ana QAPrueba"
    When the manager opens an account in "Rupee" for the customer "Luis QAExplora"
    Then the alert "Account created successfully with account Number :" is displayed followed by the assigned account number
    And each account appears next to its customer in the customer list
      | customer       | accounts |
      | Ana QAPrueba   | 1        |
      | Luis QAExplora | 1        |

  @CA-HU02-04 @EXP-019 @negative
  Scenario: Process an account without selecting a customer
    When the manager processes an account in "Dollar" without selecting a customer
    Then the field "Customer" shows the required selection validation
    And no alert is displayed
    And no new account is created

  @CA-HU02-05 @EXP-020 @negative
  Scenario: Process an account without selecting a currency
    When the manager processes an account for the customer "Luis QAExplora" without selecting a currency
    Then the field "Currency" shows the required selection validation
    And no alert is displayed
    And no new account is created
