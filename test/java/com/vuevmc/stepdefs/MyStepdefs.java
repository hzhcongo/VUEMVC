package com.vuevmc.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MyStepdefs {
    public WebDriver driver;
    public WebElement inputField;
    public WebElement todoItem;
    public String todoItemToInput;
    public WebElement deleteIcon;
    public WebElement selectionToggler;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Given("I am on the todo list page")
    public void iAmOnTheTodoListPage() {
        driver.get("https://todomvc.com/examples/vue/");
    }

    @And("I see an input field")
    public void iSeeAnInputField() throws AssertionError {
        inputField = driver.findElement(By.cssSelector(".new-todo"));

        if(!inputField.isDisplayed()) {
            throw new AssertionError("Input field is found but not displayed");
        }
    }

    @When("I select the input field")
    public void iSelectTheInputField() {
        inputField.click();
    }

    @And("I enter a {} item in the input field")
    public void iEnterTodoItem(String todo) {
        todoItemToInput = todo;
        inputField.sendKeys(todoItemToInput);
    }

    @And("I press Enter")
    public void iPressEnter() {
        inputField.sendKeys(Keys.ENTER);
    }

    @Then("I should see new todo item added")
    public void iShouldSeeNewThingAddedAsTodo() throws AssertionError {
        todoItem = driver.findElement(By.cssSelector(".todo label"));
        if (!todoItem.getText().equals(todoItemToInput)) {
            throw new AssertionError("Added todo item is incorrect");
        }
    }

    @And("I should see input field being empty")
    public void iShouldSeeInputFieldBeingEmpty() throws AssertionError {
        if(!inputField.getAttribute("value").equals("")) {
            throw new AssertionError("Input field is not empty.");
        }
    }

    @And("I have added {string} todo item")
    public void iHaveAddedTodoItem(String item) {
        iSeeAnInputField();
        iSelectTheInputField();
        iEnterTodoItem(item);
        iPressEnter();
        iShouldSeeNewThingAddedAsTodo();
    }

    @When("I hover over the added todo item to click on X icon")
    public void iHoverOverTheAddedTodoItemToClickOnXIcon() throws AssertionError{
        todoItem = driver.findElement(By.cssSelector(".todo label"));

        Actions action = new Actions(driver);
        action.moveToElement(todoItem).perform();

        deleteIcon = driver.findElement(By.cssSelector(".destroy"));
        deleteIcon.click();
    }

    @Then("I see the todo item deleted")
    public void iSeeTheTodoItemDeleted() throws AssertionError{
        if (driver.findElements(By.cssSelector(".todo label")).size() > 0) {
            throw new AssertionError("Todo item is not deleted");
        }
    }

    @When("I double-click on the todo item")
    public void iDoubleClickOnTheTodoItem() {
        Actions act = new Actions(driver);
        act.doubleClick(todoItem).perform();
    }

    @And("I edit the todo item to {string}")
    public void iEditTheTodoItemToNewItem(String newItem) {
        WebElement editInputField = driver.findElement(By.cssSelector(".todo .edit"));

        // To clear todo item
        while (editInputField.getAttribute("value").length() != 0) {
            editInputField.sendKeys(Keys.BACK_SPACE);
        }
        editInputField.sendKeys(newItem);
        editInputField.sendKeys(Keys.ENTER);
    }

    @Then("I see the todo item updated to {string}")
    public void iSeeTheTodoItemUpdatedToOrange(String newItem) throws AssertionError {
        if (!driver.findElement(By.cssSelector(".todo label")).getText().equals(newItem)) {
            throw new AssertionError( "Todo item is updated wrongly");
        }
    }

    @And("I have added {int} todo items")
    public void iHaveAddedTodoItems(int count) {
        while (count != 0) {
            iHaveAddedTodoItem("apple");
            count--;
        }
    }

    @When("I select button at left of input field")
    public void iSelectButtonAtLeftOfInputField() {
        selectionToggler = driver.findElement(By.cssSelector("label[for=toggle-all]"));
        selectionToggler.click();
    }

    @Then("I see all todo items are marked completed")
    public void iSeeAllTodoItemsAreMarkedCompleted() throws AssertionError {
        if (driver.findElements(By.cssSelector(".todo.completed")).size() != 2) {
            throw new AssertionError("All todo items are not marked completed");
        }
    }

    @And("I have {int} todo items complete")
    public void iHaveTodoItemsComplete(int count) {
        iHaveAddedTodoItems(count);
        iSelectButtonAtLeftOfInputField();
        iSeeAllTodoItemsAreMarkedCompleted();
    }

    @Then("I see all todo items are not marked completed")
    public void iSeeAllTodoItemsAreNotMarkedCompleted() throws AssertionError {
        if (driver.findElements(By.cssSelector(".todo.completed")).size() != 0) {
            throw new AssertionError("All todo items are not marked completed");
        }
    }

    @When("I select checkbox at left of todo item")
    public void iSelectCheckboxAtLeftOfTodoItem() {
        driver.findElement(By.cssSelector(".view .toggle")).click();
    }

    @Then("I see todo item is marked completed")
    public void iSeeTodoItemIsMarkedCompleted() {
        if (driver.findElements(By.cssSelector(".todo.completed")).size() != 1) {
            throw new AssertionError("Todo item is not marked completed");
        }
    }

    @And("I have {string} todo item as completed")
    public void iHaveTodoItemAsCompleted(String item) {
        iHaveAddedTodoItem(item);
        iSelectCheckboxAtLeftOfTodoItem();
        iSeeTodoItemIsMarkedCompleted();
    }

    @Then("I see todo item is marked active")
    public void iSeeTodoItemIsMarkedActive() {
        if (driver.findElements(By.cssSelector(".todo.completed")).size() != 0) {
            throw new AssertionError("Todo item is not marked active");
        }
    }
}
