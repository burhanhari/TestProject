@Regression
Feature: Login functionality

  @Smoke
  Scenario: Successful login

    Given user is on login page
    When user enters valid username and password
    And clicks login button
    Then user should see dashboard

  @Regression
  Scenario Outline: Invalid login attempts

    Given user is on login page
    When user enters username "<username>" and password "<password>"
    And clicks login button
    Then error message should be displayed

    Examples:
      | username   | password   |
      | wrongUser1 | wrongPass1 |
      | wrongUser2 | wrongPass2 |
      | admin      | wrongPass3 |

  @Regression
  Scenario: Logout functionality

    Given user is logged into application
    When user clicks logout button
    Then user should be redirected to login page