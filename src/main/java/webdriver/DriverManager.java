package webdriver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private static final DriverFactory.BrowserType DEFAULT_DRIVER = DriverFactory.BrowserType.CHROME;

    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            threadLocalDriver.set(DriverFactory.getDriver(DEFAULT_DRIVER));
        }
        return threadLocalDriver.get();
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

    public static void quitDriver() {
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            threadLocalDriver.remove();
        }
    }
}