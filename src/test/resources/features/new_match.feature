Feature: Adding new match to scoreboard

  Scenario: Verify match result after creation
    Given Two teams "Poland" and "United Kingdom"
    When I create create new match
    Then current result of that match is "Poland 0 - United Kindgom 0"
