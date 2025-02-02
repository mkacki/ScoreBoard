Feature: Adding new match to scoreboard

  Scenario: Verify match result after creation
    Given Two teams "Poland" and "United Kingdom"
    When I create create new match
    Then current result of that match is "Poland 0 - United Kingdom 0"

  Scenario: Verify if new match doesn't consist home team that is already playing another match
    Given new scoreboard
    When I add match to scoreboard "Poland" - "United Kingdom"
    And I add match to scoreboard "Poland" - "Turkey"
    Then It should throw an "IllegalArgumentException" - "Home team already playing another match"

  Scenario: Verify if new match doesn't consist away team that is already playing another match
    Given new scoreboard
    When I add match to scoreboard "Poland" - "United Kingdom"
    And I add match to scoreboard "Turkey" - "United Kingdom"
    Then It should throw an "IllegalArgumentException" - "Away team already playing another match"

  Scenario: Verify scoreboard size
    Given new scoreboard
    When I add match to scoreboard "Poland" - "United Kingdom"
    And I add match to scoreboard "Turkey" - "Uruguay"
    And I add match to scoreboard "Italy" - "Venezuela"
    Then scoreboard size is 3