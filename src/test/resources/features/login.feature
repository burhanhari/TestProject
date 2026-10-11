Feature: Login functionality

  Scenario: Successful login
    Given user navigates to login page
    When user enters username and password
    And clicks on login button
    Then user should see the dashboard