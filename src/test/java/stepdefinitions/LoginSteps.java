package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.ConfigReader;
import utils.DriverFactory;

import java.util.Properties;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;
    Properties prop;

    @Given("user launches the browser")
    public void user_launches_browser() {
        driver = DriverFactory.getDriver();
        prop = ConfigReader.getProperties();
    }

    @Given("user navigates to login page")
    public void user_navigates_to_login_page() {
        driver.get(prop.getProperty("url"));
        loginPage = new LoginPage(driver);
    }

    @When("user enters username and password")
    public void user_enters_username_and_password() {
        loginPage.enterUsername(prop.getProperty("username"));
        loginPage.enterPassword(prop.getProperty("password"));
    }

    @When("clicks on login button")
    public void clicks_on_login_button() {

        loginPage.clickLogin();
    }

    @Then("user should see successful login message")
    public void user_should_see_successful_login_message() {

        String message = loginPage.getSuccessMessage();

        Assert.assertTrue(message.contains("You logged into a secure area"));

        DriverFactory.quitDriver();
    }
}
