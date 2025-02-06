Feature: Adding new match to scoreboard

  Scenario: Verify match result after creation
    Given two teams "Poland" and "United Kingdom"
    When we create new match
    Then current result of that match is "Poland 0 - United Kingdom 0"

  Scenario: No existing matches for home team allowed.
    Given new scoreboard
    When we add match to scoreboard "Poland" - "United Kingdom"
    And we add match to scoreboard "Poland" - "Turkey"
    Then it should throw an "IllegalArgumentException" - "Home team is already playing another match"

  Scenario: No existing matches for away team allowed.
    Given new scoreboard
    When we add match to scoreboard "Poland" - "United Kingdom"
    And we add match to scoreboard "Turkey" - "United Kingdom"
    Then it should throw an "IllegalArgumentException" - "Away team is already playing another match"

  Scenario: Verify scoreboard size
    Given new scoreboard
    When we add match to scoreboard "Poland" - "United Kingdom"
    And we add match to scoreboard "Turkey" - "Uruguay"
    And we add match to scoreboard "Italy" - "Venezuela"
    Then scoreboard size is 3

  Scenario: Verify if homa and away teams are not the same team
    Given two teams "Poland" and "Poland"
    When we create new match
    Then it should throw an "IllegalArgumentException" - "Same home and away team"

  Scenario Outline: Verify if all required parameters are provided
    Given two teams "<home>" and "<away>"
    When we create new match
    Then it should throw an "NullPointerException" - "Required parameter is missing"
    Examples:
      | home   | away   |
      | null   | null   |
      | Poland | null   |
      | null   | Poland |

  Scenario Outline: Verify if all required parameters are not blank
    Given two teams "<home>" and "<away>"
    When we create new match
    Then it should throw an "IllegalArgumentException" - "Required parameter is blank"
    Examples:
      | home   | away   |
      |        |        |
      | Poland |        |
      |        | Poland |

  Scenario: Multithreaded scoreboard modification
    Given new scoreboard
    When Multithreaded scoreboard modification
    Then scoreboard size is 1
    And Row 0 Result: "Poland 0 - Turkey 0"