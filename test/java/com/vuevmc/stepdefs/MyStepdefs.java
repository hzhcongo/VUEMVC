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

public class MyStepdefs {
    public WebDriver driver;
    public WebElement inputField;
    public WebElement todoItem;
    public String todoItemToInput;


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
    public void iSeeAnInputField() {
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
    public void iShouldSeeNewThingAddedAsTodo() {
        todoItem = driver.findElement(By.cssSelector(".todo label"));
        if (!todoItem.getText().equals(todoItemToInput)) {
            throw new AssertionError("Added todo item is incorrect");
        }
    }

    @And("I should see input field being empty")
    public void iShouldSeeInputFieldBeingEmpty() {
        if(!inputField.getAttribute("value").equals("")) {
            throw new AssertionError("Input field is not empty.");
        }
    }
}
