Feature: Get a summary of matches in progress ordered by their total score. The matches with the
  same total score will be returned ordered by the most recently started match in the
  scoreboard.

  Scenario:
    Given new scoreboard
    When I add match to scoreboard "Mexico" - "Canada"
    And Update game score "Mexico" - "Canada" 0 5
    And I add match to scoreboard "Spain" - "Brazil"
    And Update game score "Spain" - "Brazil" 10 2
    And I add match to scoreboard "Germany" - "France"
    And Update game score "Germany" - "France" 2 2
    And I add match to scoreboard "Uruguay" - "Italy"
    And Update game score "Uruguay" - "Italy" 6 6
    And I add match to scoreboard "Argentina" - "Australia"
    And Update game score "Argentina" - "Australia" 3 1
    Then "Scoreboard correctly ordered by total score and most recently started"

