Feature: CreateBoard

  Scenario Outline: create new board
    Given I am in Trello main menu
    When I want create the new boards:
      | some  |
      | misha |
    Then The new board is created

    Examples:
      | boardName |
      | aa        |
      | bb        |
