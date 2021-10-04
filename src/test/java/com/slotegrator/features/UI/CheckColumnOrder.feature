Feature: Column ordering

  @UI
  Scenario: Check elements ordering in the player table

    Given I logged in at dashboard home page
    When I am at the players page
    And The table with the players is loaded
    When I click on the column "E-mail"
    Then Data in column "E-mail" will be ordered "asc"
