package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import webdriver.DriverManager;

import java.util.List;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 40;

    private static WebDriverWait createWait(int timeOut) {
        return new WebDriverWait(DriverManager.getDriver(), timeOut);
    }

    public static void waitForVisibility(WebElement webElement) {
        waitForVisibility(webElement, DEFAULT_TIMEOUT);
    }

    public static void waitForVisibilityOfAllElements(List<WebElement> webElement) {
        waitForVisibilityOfAllElements(webElement, DEFAULT_TIMEOUT);
    }

    public static void waitForVisibility(WebElement webElement, int wantedTimeOut) {
        createWait(wantedTimeOut).until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitForVisibilityOfAllElements(List<WebElement> webElement, int wantedTimeOut) {
        createWait(wantedTimeOut).until(ExpectedConditions.visibilityOfAllElements(webElement));
    }
}