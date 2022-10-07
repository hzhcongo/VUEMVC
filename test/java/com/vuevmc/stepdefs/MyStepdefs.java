package com.vuevmc.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyStepdefs {
    public WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://todomvc.com/examples/vue/");
    }

    @After
    public void quit() {
        driver.quit();
    }

    @And("I see an input field")
    public void iSeeAnInputField() {
    }

    @When("I select the input field")
    public void iSelectTheInputField() {
    }

    @And("I enter <todo>")
    public void iEnterTodo() {
    }

    @And("I press Enter")
    public void iPressEnter() {
    }

    @Then("I should see new thing added into the todo list")
    public void iShouldSeeNewThingAddedIntoTheTodoList() {
    }
}
