package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecurePage extends BasePage {
    public SecurePage(WebDriver driver) {
        super(driver);
    }

    By logoutBtn = By.cssSelector(".button.secondary.radius");
    By successMsg = By.id("flash");

    public void clickLogout() {
        click(logoutBtn);
    }

    public String getMessage() {
        return getText(successMsg);
    }


}
