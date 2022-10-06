# VUEMVC

## How to run the tests
1. Download IntelliJ project
2. Open IntelliJ project on IntelliJ
3. Press "Run"
4. See terminal for test results

## US
In order to remember the things I want to do, as a ToDo MVC user,
I want to manage my todo list

## Features tested
- Add
Given
Page loaded

    When
        Enter "randomised unicode" in text input
        And
        Press ENTER

    Then
        Text input is empty
        And
        Total number of todo items increased by 1
        Last todo item is "randomised unicode"

- Delete

- Check

- Uncheck

- Clear completed

- View all

- Filter active

- Filter completed

- Select all

// Permutate?