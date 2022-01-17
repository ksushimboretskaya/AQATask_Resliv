package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class DriverFactory {

    private static final int IMPLICITLY_WAIT = 10;
    private static final int PAGE_LOAD_TIMEOUT = 40;
    private static final String CHROMEDRIVER_EXE_PATH = "src/main/resources/chromedriver.exe";
    private static final Logger logger = Logger.getLogger("Driver factory logger");

    public static WebDriver getDriver(BrowserType type) {
        WebDriver driver;
        switch (type) {
            case CHROME:
                driver = getChromeDriver();
                break;
            default:
                throw new IllegalArgumentException("Invalid browser name" + type);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        logger.info(type + " browser started");
        return driver;
    }

    private static ChromeDriver getChromeDriver() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, CHROMEDRIVER_EXE_PATH);
        return new ChromeDriver();
    }

    public enum BrowserType {CHROME}
}