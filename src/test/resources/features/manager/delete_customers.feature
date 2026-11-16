@manager @HU-04
Feature: HU-04 - Delete customers
  As a bank manager,
  I want to delete a customer,
  so that they are no longer listed in the bank and can no longer access their accounts.

  Background:
    Given the manager has registered the customers
      | first_name | last_name | post_code |
      | Ana        | QAPrueba  | QA0001    |
      | Luis       | QAExplora | QA0002    |
    And the manager has opened an account in "Dollar" for the customer "Ana QAPrueba"
    And the manager has opened an account in "Rupee" for the customer "Luis QAExplora"
    And the manager goes to the "Customers" tab

  @CA-HU04-01 @CA-HU04-02 @EXP-028 @happy-path @HAL-11
  Scenario: Delete a test customer
    When the manager deletes the customer "Luis QAExplora"
    Then the list shows 6 customers
    And the customer "Luis QAExplora" does not appear in the customer dropdown of "Open Account"
    And the customer "Luis QAExplora" does not appear in the customer dropdown of "Customer Login"
