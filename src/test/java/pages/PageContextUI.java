package pages;

import org.openqa.selenium.WebDriver;

public class PageContextUI {

    private final WebDriver driver;
    private final LoginPage loginPage;
    private final SecurePage securePage;
    private final DashboardPage dashboardPage;

    public PageContextUI(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
        this.securePage = new SecurePage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public SecurePage getSecurePage() {
        return securePage;
    }

    public DashboardPage getDashboardPage() {
        return dashboardPage;
    }
}
