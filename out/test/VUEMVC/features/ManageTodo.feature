Feature: Manage todo
  I can manage my todo list

  Scenario Outline: Add different kinds of todo items
    Given I am on the todo list page
    And I see an input field
    When I select the input field
    And I enter a <todo> item in the input field
    And I press Enter
    Then I should see new todo item added
    And I should see input field being empty

    Examples:
      | todo |
      | fruit |
      | Fruit |
      | Fruits!! |
      | 1 |
      | 1 fruit |
      | 2 IPhones @ Orchard |

  Scenario: Delete single todo item
    Given I am on the todo list page
    And I have added 1 todo item
    When I hover over the added todo item to click on X icon
    Then I see the todo item deleted

#  Scenario: Bulk delete multiple todo items
#    Given I am on the todo list page
#    And I have added 2 todo items
#    When I select button at left of input field
#    And I see 'Clear completed' appear
#    And I select 'Clear completed'
#    Then I see the todo item deleted
