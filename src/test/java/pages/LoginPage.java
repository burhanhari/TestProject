package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By username = By.name("username");
    private final By password = By.name("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String user) {
        sendKeys(username, user);
    }

    public void enterPassword(String pass) {
        sendKeys(password, pass);
    }

    public void clickLogin() {
        clickElement(loginBtn);
    }

}
