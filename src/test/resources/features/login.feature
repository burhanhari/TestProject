Feature: Login functionality

  Scenario: Successful login

    Given user launches the browser
    And user navigates to login page
    When user enters username and password
    And clicks on login button
    Then user should see successful login message