Feature: Adding new match to scoreboard

  Scenario: Add new match to scoreboard
    Given Two teams "Poland" and "United Kingdom"
    When I create create New Match
    Then current result of that match is "Poland 0 - United Kindgom 0"
