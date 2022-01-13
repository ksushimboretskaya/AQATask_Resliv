package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.DriverManager;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    public BasePage() {
        driver = DriverManager.getDriver();
        webDriverWait = new WebDriverWait(driver, 35);
        PageFactory.initElements(this.driver, this);
    }
}