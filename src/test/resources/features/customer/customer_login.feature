@customer @HU-05
Feature: HU-05 - Log in as a customer
  As a customer,
  I want to log in to the bank by selecting my name,
  so that I can check my accounts and make transactions on them.

  @CA-HU05-01 @EXP-029 @happy-path
  Scenario: An existing customer logs in
    Given the visitor opens the bank application
    When the user logs in as the customer "Harry Potter"
    Then the application route is "#/account"
    And the welcome message "Welcome Harry Potter !!" is displayed
    And the account dropdown offers the accounts "1004", "1005" and "1006"
    And the account "1004" shows balance 0 and currency "Dollar"
    And the "Logout", "Transactions", "Deposit" and "Withdrawl" options are displayed

  @CA-HU05-02 @EXP-003 @negative
  Scenario: Enter the customer module without selecting a name
    Given the visitor opens the bank application
    When the user opens the customer login without selecting a name
    Then the customer dropdown lists the preloaded customers
      | Hermoine Granger   |
      | Harry Potter       |
      | Ron Weasly         |
      | Albus Dumbledore   |
      | Neville Longbottom |
    And the "Login" button is not displayed
    When the user selects the customer "Harry Potter"
    Then the "Login" button is displayed
    When the user goes back to the option "---Your Name---"
    Then the "Login" button is not displayed

  @CA-HU05-03 @EXP-031 @alternative
  Scenario: A customer without accounts logs in
    Given the manager has registered the customers
      | first_name | last_name   | post_code |
      | Sofia      | QASinCuenta | QA0004    |
    When the user logs in as the customer "Sofia QASinCuenta"
    Then the welcome message "Welcome Sofia QASinCuenta !!" is displayed
    And the message "Please open an account with us." is displayed
    And the "Transactions", "Deposit" and "Withdrawl" options are not displayed

  @EXP-034 @alternative
  Scenario: Use the Home button during the customer session
    Given the customer "Harry Potter" has logged in
    When the user clicks the "Home" button
    Then the application route is "#/login"
    And the "Logout" button is not displayed
    When the user opens the customer login without selecting a name
    Then the customer dropdown shows the option "---Your Name---"
