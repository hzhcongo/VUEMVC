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

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
//        driver.quit();
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

    @And("I have added 'apple' todo item")
    public void iHaveAddedTodoItem() {
        iSeeAnInputField();
        iSelectTheInputField();
        iEnterTodoItem("apple");
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

    @And("I edit the todo item to 'orange'")
    public void iEditTheTodoItemToOrange() {
        WebElement editInputField = driver.findElement(By.cssSelector(".todo .edit"));

        // To clear todo item
        while (editInputField.getAttribute("value").length() != 0) {
            editInputField.sendKeys(Keys.BACK_SPACE);
        }
        editInputField.sendKeys("orange");
        editInputField.sendKeys(Keys.ENTER);
    }

    @Then("I see the todo item updated to 'orange'")
    public void iSeeTheTodoItemUpdatedToOrange() throws AssertionError {
        if (!driver.findElement(By.cssSelector(".todo label")).getText().equals("orange")) {
            throw new AssertionError( "Todo item is updated wrongly");
        }
    }
}
