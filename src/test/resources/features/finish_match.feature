Feature:  Finish match currently in progress. This removes a match from the scoreboard.

  Scenario: finish match and verify if it is removed from scoreboard
    Given new scoreboard
    When I add match to scoreboard "Poland" - "United Kingdom"
    And Finish match on scoreboard "Poland" - "United Kingdom"
    Then scoreboard size is 0


  Scenario: try to remove match that is not on a scoreboard
    Given new scoreboard
    When I add match to scoreboard "Poland" - "United Kingdom"
    And Finish match on scoreboard "Venezuela" - "Turkey"
    Then It should throw an "IllegalArgumentException" - "No such match on scoreboard"
