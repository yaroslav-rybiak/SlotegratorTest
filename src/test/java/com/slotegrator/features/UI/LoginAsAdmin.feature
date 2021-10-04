Feature: Admin login functionality

  @UI
  Scenario: Logging into admin panel using valid credentials

    Given I am at the login page
    When I log in as "admin1" user
    And I click the Sign in button
    Then I should be at the dashboard home page
    And I should be logged as "admin1" user
