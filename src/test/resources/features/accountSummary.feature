Feature: Account Summary Functionality Testing

  Background: Login
    Given the user is on the login page
    And User logins with username "username" and password "password"


  Scenario: Checking account summary page features
    And "Account Summary" page should be displayed

    And the page should have following account types
      | Cash Accounts       |
      | Investment Accounts |
      | Credit Accounts     |
      | Loan Accounts       |

    Then "Credit Accounts" table must have following columns
      | Account     |
      | Credit Card |
      | Balance     |
