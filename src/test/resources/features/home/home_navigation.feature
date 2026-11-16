@home
Feature: XYZ Bank home and navigation
  As an XYZ Bank user
  I want to access the application and navigate between its modules
  So that I can use the customer or manager features

  @EXP-001 @happy-path
  Scenario: The bank home screen loads
    Given the visitor opens the bank application
    Then the page title is "XYZ Bank"
    And the "Home", "Customer Login" and "Bank Manager Login" options are displayed
    And the "Logout" button is not displayed

  @EXP-002 @happy-path @HAL-16
  Scenario: Log in as bank manager
    Given the visitor opens the bank application
    When the user logs in as bank manager
    Then the application route is "#/manager"
    And the "Add Customer", "Open Account" and "Customers" options are displayed

  @EXP-004 @alternative
  Scenario: Go back to the home screen with the Home button
    Given the manager is on the "Open Account" tab
    When the user clicks the "Home" button
    Then the application route is "#/login"
    And the "Customer Login" and "Bank Manager Login" options are displayed
    When the user goes back with the browser Back button
    Then the application route is "#/manager/openAccount"

  # Each route goes in its own scenario: in a scenario outline, Serenity accumulates the failures of
  # the previous rows in the last one and the report would not show the real result of each route.
  # The "#/account" route is checked in customer/logout.feature (CA-HU07-03).
  @EXP-005 @negative @defect @HAL-01
  Scenario: Direct access to the manager route without a session
    Given the visitor has a new browser session
    When the user opens the route "#/manager/addCust" directly
    Then the user is redirected to the home screen or gets an unauthorized access message

  @EXP-005 @negative @defect @HAL-01
  Scenario: Direct access to the transactions route without a session
    Given the visitor has a new browser session
    When the user opens the route "#/listTx" directly
    Then the user is redirected to the home screen or gets an unauthorized access message

  @EXP-006 @alternative
  Scenario: Data persists after reloading and stays isolated between sessions
    Given the manager has registered the customers
      | first_name | last_name | post_code |
      | Ana        | QAPrueba  | QA0001    |
    And the manager goes to the "Customers" tab
    When the user reloads the page
    And the manager searches for "QAPrueba" in the customer list
    Then the list shows 1 customer
    When the manager opens the application in a new browser session
    And the manager searches for "QAPrueba" in the customer list
    Then the list shows 0 customers
