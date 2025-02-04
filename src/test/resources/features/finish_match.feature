Feature:  Finish match currently in progress. This removes a match from the scoreboard.

  Scenario: finish match and verify if it is removed from scoreboard
    Given new scoreboard
    When we add match to scoreboard "Poland" - "United Kingdom"
    And finish match on scoreboard "Poland" - "United Kingdom"
    Then scoreboard size is 0


  Scenario: try to remove match that is not on a scoreboard
    Given new scoreboard
    When we add match to scoreboard "Poland" - "United Kingdom"
    And finish match on scoreboard "Venezuela" - "Turkey"
    Then it should throw an "IllegalArgumentException" - "No such match on scoreboard"

  Scenario: When match is finished both teams can play in another match
    Given new scoreboard
    When we add match to scoreboard "Venezuela" - "Turkey"
    And finish match on scoreboard "Venezuela" - "Turkey"
    And we add match to scoreboard "Venezuela" - "Turkey"
    Then  scoreboard size is 1