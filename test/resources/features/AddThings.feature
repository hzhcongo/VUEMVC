Feature: Add things
  I can add new things to the todo list

  Scenario Outline: Add different kinds of things
    Given I see an input field
    When I select the input field
    And I enter <todo>
    And I press Enter
    Then I should see new thing added into the todo list


    Examples:
      | todo |
      | fruit |
      | Fruit |
      | Fruits!! |
      | 1 |
      | 1 fruit |
      | 2 IPhones @ Orchard |