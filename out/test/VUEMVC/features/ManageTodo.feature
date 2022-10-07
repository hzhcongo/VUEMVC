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
    And I have added 'apple' todo item
    When I hover over the added todo item to click on X icon
    Then I see the todo item deleted

  Scenario: Edit todo item
    Given I am on the todo list page
    And I have added 'apple' todo item
    When I double-click on the todo item
    And I edit the todo item to 'orange'
    Then I see the todo item updated to 'orange'

  Scenario: Bulk complete todo items
    Given I am on the todo list page
    And I have added 2 todo items
    When I select button at left of input field
    Then I see all todo items are marked completed

  Scenario: Bulk uncomplete todo items
    Given I am on the todo list page
    And I have 2 todo items complete
    When I select button at left of input field
    Then I see all todo items are not marked completed

  Scenario: Complete single todo item
    Given I am on the todo list page
    And I have added 'apple' todo item
    When I select checkbox at left of todo item
    Then I see todo item is marked completed

  Scenario: Uncomplete single todo item
    Given I am on the todo list page
    And I have 'apple' todo item as completed
    When I select checkbox at left of todo item
    Then I see todo item is marked active

#  Scenario: Bulk delete all todo items
#    Given I am on the todo list page
#    And I have added 2 todo items ('apple', 'orange')
#    When I select button at left of input field
#    And I see 'Clear completed' appear
#    And I select 'Clear completed'
#    Then I see all todo items deleted
#
#  Scenario: Filter active todo items
#    Given I am on the todo list page
#    And I have 1 todo item active ('apple')
#    And I have 1 todo item completed ('orange')
#    When I select "Active"
#    Then I see only 1 todo item ('apple')
#
#  Scenario: Filter completed todo items
#    Given I am on the todo list page
#    And I have 1 todo item active ('apple')
#    And I have 1 todo item completed ('orange')
#    When I select "Completed"
#    Then I see only 1 todo item ('orange')