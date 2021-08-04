Feature: SortingCards

  Scenario Outline: sort cards in the list
    Given I am in the Trello main menu
    When I want to sort cards in the list on the "newboard111" board:

    Then The cards are sorted

    Examples:
      | boardName |
      | newboard111        |
