Feature: Match Score modification and modifications validation

  Scenario: Update match score
    Given new scoreboard
    When I add match to scoreboard "Poland" - "United Kingdom"
    And Update game score "Poland" - "United Kingdom" 1 1
    Then Match "Poland" - "United Kingdom" result is "Poland 1 - United Kingdom 1"

  Scenario: Invalid score update
    Given new scoreboard
    When I add match to scoreboard "Poland" - "United Kingdom"
    And Update game score "Poland" - "United Kingdom" 1 1
    And Update game score "Poland" - "United Kingdom" 0 1
    Then It should throw an "IllegalArgumentException" - "Illegal score update"