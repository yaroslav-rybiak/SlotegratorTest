Feature: Players table is available

  @UI
  Scenario: Open players page with list table of players

    Given I logged in at dashboard home page
    When I click on the "Users" tab in menus
    Then Sub-menu "Users" is displayed
    When I click on the "Players" sub-tab
    Then I am at the players page
    And The table with the players is loaded
