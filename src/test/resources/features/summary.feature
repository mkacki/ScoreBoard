Feature: Get a summary of matches in progress ordered by their total score. The matches with the
  same total score will be returned ordered by the most recently started match in the
  scoreboard.

  Scenario:
    Given new scoreboard
    When we add match to scoreboard "Mexico" - "Canada"
    And update game score "Mexico" - "Canada" 0 5
    And we add match to scoreboard "Spain" - "Brazil"
    And update game score "Spain" - "Brazil" 10 2
    And we add match to scoreboard "Germany" - "France"
    And update game score "Germany" - "France" 2 2
    And we add match to scoreboard "Uruguay" - "Italy"
    And update game score "Uruguay" - "Italy" 6 6
    And we add match to scoreboard "Argentina" - "Australia"
    And update game score "Argentina" - "Australia" 3 1
    Then scoreboard correctly sorted by total score and most recent match

