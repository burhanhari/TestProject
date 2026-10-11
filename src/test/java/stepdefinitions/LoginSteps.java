package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java8.En;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.PageContextUI;
import utils.PropertiesLoader;
import utils.WebDriverSingleton;

public class LoginSteps implements En {
    WebDriver driver;
    PageContextUI pageContextUI;

    public LoginSteps() {
        Given("user navigates to login page", () -> {
            pageContextUI.getDriver().get(PropertiesLoader.getProperty("url"));
        });

        When("user enters username and password", () -> {
            pageContextUI.getLoginPage().enterUsername(PropertiesLoader.getProperty("username"));
            pageContextUI.getLoginPage().enterPassword(PropertiesLoader.getProperty("password"));
        });

        When("clicks on login button", () -> pageContextUI.getLoginPage().clickLogin());

        Then("user should see the dashboard",
                () -> Assert.assertTrue(pageContextUI.getDashboardPage().isDisplayed()));
    }

    @Before
    public void setUp() {
        driver = WebDriverSingleton.getInstance().getDriver();
        pageContextUI = new PageContextUI(driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed() && driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Failure Screenshot");
            }
        } finally {
            WebDriverSingleton.getInstance().quitDriver();
        }
    }
}
