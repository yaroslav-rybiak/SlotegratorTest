Feature: Authorizing as guest, registering a new player, getting player data

  @API
  Scenario: Authorizing as guest, registering a new player, getting player data

    When I send guest token request
    Then Status code is 200
    And Server response has guest token
    When I send registration request
    Then Status code is 201
    And Server registration response matches the documentation
    When I log in as the registered player
    Then Status code is 200
    And Server response has registration token
    When I send own profile data request
    Then Status code is 200
    And Server profile response matches the documentation
    When I send other profile data request
    Then Status code is 404