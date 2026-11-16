@customer @HU-07
Feature: HU-07 - Log out
  As a customer,
  I want to log out,
  so that nobody else can see my information or make transactions with my accounts from the same computer.

  @CA-HU07-01 @EXP-032 @happy-path
  Scenario: The customer logs out
    Given the customer "Harry Potter" has logged in
    When the customer logs out
    Then the application route is "#/customer"
    And the customer dropdown shows the option "---Your Name---"
    And the "Logout" and "Login" buttons are not displayed

  @CA-HU07-02 @EXP-033 @negative @defect @HAL-02
  Scenario: Go back to the account with the Back button after logging out
    Given the customer "Harry Potter" has logged in
    And the customer has logged out
    When the user goes back with the browser Back button
    Then the account information is not displayed
    And the user cannot make transactions without logging in again

  @CA-HU07-03 @EXP-005 @negative @defect @HAL-01
  Scenario: Direct access to the customer account route without a session
    Given the visitor has a new browser session
    When the user opens the route "#/account" directly
    Then the user is redirected to the home screen or gets an unauthorized access message
    And no unprocessed templates such as "{{user}}" are displayed
