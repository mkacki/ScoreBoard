Feature: Match Score modification and modifications validation

  Scenario: Update match score
    Given new scoreboard
    When we add match to scoreboard "Poland" - "United Kingdom"
    And update game score "Poland" - "United Kingdom" 1 1
    Then match "Poland" - "United Kingdom" result is "Poland 1 - United Kingdom 1"

  Scenario: Invalid score update
    Given new scoreboard
    When we add match to scoreboard "Poland" - "United Kingdom"
    And update game score "Poland" - "United Kingdom" 1 1
    And update game score "Poland" - "United Kingdom" 0 1
    Then it should throw an "IllegalArgumentException" - "Illegal score update"