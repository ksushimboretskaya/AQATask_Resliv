package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import webdriver.DriverManager;

import java.util.ArrayList;
import java.util.logging.Logger;

public class BasePage {

    private final Logger logger = Logger.getLogger("Base page logger");
    protected WebDriver driver;

    public BasePage() {
        driver = DriverManager.getDriver();
        PageFactory.initElements(this.driver, this);
    }

    public void switchToOpenTab() {
        ArrayList<String> openTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(0)).close();
        logger.info("Successfully closed the previous page");
        driver.switchTo().window(openTabs.get(1));
        logger.info("Successfully opened a new page");
    }
}