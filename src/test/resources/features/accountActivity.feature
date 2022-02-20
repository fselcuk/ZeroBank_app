Feature: Account Activity Page Functionality Testing

  Background: Login
    Given the user is on the login page
    And User logins with username "username" and password "password"
    Then "Account Summary" page should be displayed

    @wip
  Scenario: Account Activity Page functionality
    Given the user navigates to "Account Activity" page
    Then the page title should be "Zero - Account Activity"
    And In the account drop down default option should be "Savings"
    And Account drop down should have following options
      | Savings     |
      | Checking    |
      | Savings     |
      | Loan        |
      | Credit Card |
      | Brokerage   |
    Then Transactions table should have following column names
      | Date        |
      | Description |
      | Deposit     |
      | Withdrawal  |
