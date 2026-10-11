package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class WebDriverSingleton {

    private static final WebDriverSingleton INSTANCE = new WebDriverSingleton();
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverSingleton() {
    }

    public static WebDriverSingleton getInstance() {
        return INSTANCE;
    }

    public WebDriver getDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver == null) {
            WebDriverManager.chromedriver().setup();
            currentDriver = new ChromeDriver();
            currentDriver.manage().window().maximize();
            driver.set(currentDriver);
        }
        return currentDriver;
    }

    public void quitDriver() {
        WebDriver currentDriver = driver.get();
        if (currentDriver != null) {
            try {
                currentDriver.quit();
            } finally {
                driver.remove();
            }
        }
    }
}
