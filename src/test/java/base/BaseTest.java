package base;

import configuration.BrowserConfig;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import webdriver.DriverFactory;
import webdriver.DriverManager;

public class BaseTest {

    private static final String AVIASALES_URL = "https://www.aviasales.by/";

    @BeforeSuite(description = "Set up browser driver")
    public void setUp() {
        DriverManager.setDriver(DriverFactory.getDriver(BrowserConfig.getType()));
    }

    @BeforeMethod(description = "Set up base page url")
    public void setUpURL() {
        DriverManager.getDriver().get(AVIASALES_URL);
    }

    @AfterMethod(description = "Quit driver")
    public void tearDown() {
        DriverManager.quitDriver();
    }
}