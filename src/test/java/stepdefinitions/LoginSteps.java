package stepdefinitions;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.SecurePage;
import utils.ConfigReader;
import utils.DriverFactory;

import java.util.Properties;

public class LoginSteps {

    private static final Logger log = LogManager.getLogger(LoginSteps.class);

    WebDriver driver;
    LoginPage loginPage;
    SecurePage securePage;
    Properties prop;

    @Given("user is on login page")
    public void user_is_on_login_page() {

        log.info("Launching browser");

        driver = DriverFactory.getDriver();
        prop = ConfigReader.getProperties();
        log.info("Navigating to login page");
        driver.get(prop.getProperty("url"));

        loginPage = new LoginPage(driver);
    }

    @When("user enters valid username and password")
    public void user_enters_valid_credentials() {

        loginPage.enterUsername(prop.getProperty("username"));
        loginPage.enterPassword(prop.getProperty("password"));
    }

    @When("user enters invalid username and password")
    public void user_enters_invalid_credentials() {

        loginPage.enterUsername("wrongUser");
        loginPage.enterPassword("wrongPassword");
    }

    @When("clicks login button")
    public void clicks_login_button() {
        log.info("Clicking login button");
        loginPage.clickLogin();
    }

    @Then("user should see dashboard")
    public void user_should_see_dashboard() {
        log.info("Verifying successful login");
        securePage = new SecurePage(driver);

        Assert.assertTrue(
                securePage.getMessage().contains("You logged into a secure area"));
    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {

        Assert.assertTrue(
                loginPage.getErrorMessage().contains("Your username is invalid"));
    }

    @Given("user is logged into application")
    public void user_is_logged_into_application() {

        driver = DriverFactory.getDriver();
        prop = ConfigReader.getProperties();

        driver.get(prop.getProperty("url"));

        loginPage = new LoginPage(driver);

        loginPage.enterUsername(prop.getProperty("username"));
        loginPage.enterPassword(prop.getProperty("password"));
        loginPage.clickLogin();

        securePage = new SecurePage(driver);
    }

    @When("user clicks logout button")
    public void user_clicks_logout_button() {

        securePage.clickLogout();
    }

    @Then("user should be redirected to login page")
    public void user_should_be_redirected_to_login_page() {

        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) {

        log.info("Entering username");
        loginPage.enterUsername(username);
        log.info("Entering password");
        loginPage.enterPassword(password);

    }
}