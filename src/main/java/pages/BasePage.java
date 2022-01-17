package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import webdriver.DriverManager;

import java.util.ArrayList;

@Log4j2
public class BasePage {

    protected WebDriver driver;

    public BasePage() {
        driver = DriverManager.getDriver();
        PageFactory.initElements(this.driver, this);
    }

    protected void switchToOpenTab() {
        ArrayList<String> openTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(0)).close();
        log.info("Successfully closed the previous page");
        driver.switchTo().window(openTabs.get(1));
        log.info("Successfully opened a new page");
    }
}