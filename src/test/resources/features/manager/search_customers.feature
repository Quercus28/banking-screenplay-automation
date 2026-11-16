@manager @HU-03
Feature: HU-03 - View and search customers
  As a bank manager,
  I want to view the customer list and search for a specific customer,
  so that I can quickly check their data and the accounts they have open.

  Background:
    Given the manager has registered the customers
      | first_name | last_name | post_code |
      | Ana        | QAPrueba  | QA0001    |
      | Luis       | QAExplora | QA0002    |
    And the manager has opened an account in "Dollar" for the customer "Ana QAPrueba"
    And the manager has opened an account in "Rupee" for the customer "Luis QAExplora"
    And the manager goes to the "Customers" tab

  @CA-HU03-01 @EXP-021 @happy-path
  Scenario: View the customer list
    Then the customer list has the columns "First Name", "Last Name", "Post Code", "Account Number" and "Delete Customer"
    And the list shows 7 customers
    And every customer has a "Delete" button

  @CA-HU03-02 @EXP-022 @happy-path
  Scenario: Search for a customer by first name
    When the manager searches for "Harry" in the customer list
    Then the list only shows the customer "Harry" "Potter" with post code "E725JB" and accounts "1004 1005 1006"

  @CA-HU03-02 @EXP-023 @happy-path
  Scenario: Search for a customer by last name
    When the manager searches for "QAExplora" in the customer list
    Then the list shows 1 customer
    And the list shows the customer "Luis" "QAExplora" with post code "QA0002"

  @CA-HU03-03 @EXP-024 @alternative
  Scenario: Search with a partial match
    When the manager searches for "ott" in the customer list
    Then the list shows the customers
      | Harry Potter       |
      | Neville Longbottom |

  @CA-HU03-04 @EXP-025 @alternative
  Scenario Outline: Search by post code and by account number
    When the manager searches for "<text>" in the customer list
    Then the list only shows the customer "<customer>"

    Examples:
      | text   | customer     |
      | E725JB | Harry Potter |
      | 1004   | Harry Potter |

  @CA-HU03-05 @EXP-026 @negative
  Scenario: Search for a customer that does not exist
    When the manager searches for "ZZZNoExiste" in the customer list
    Then the list shows 0 customers

  @CA-HU03-06 @EXP-027 @alternative @HAL-12
  Scenario: Sort the list by columns
    When the manager sorts the list by the "First Name" column
    Then the list is sorted by "First Name" in descending order
    When the manager sorts the list by the "First Name" column
    Then the list is sorted by "First Name" in ascending order
    When the manager sorts the list by the "Post Code" column
    Then the list is sorted by "Post Code" in descending order
