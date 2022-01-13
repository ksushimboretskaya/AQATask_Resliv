package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import webdriver.DriverFactory;
import webdriver.DriverManager;

import java.time.LocalDate;

public class BaseTest {

    public static final String AVIASALES_URL = "https://www.aviasales.by/";
    protected static final String DEPARTURE_CITY_NAME = "Москва";
    protected static final String DESTINATION_CITY_NAME = "Санкт-Петербург";
    protected final static Integer INCREMENT_FOR_DEPARTURE_DATE = 1;
    protected final static Integer INCREMENT_FOR_ARRIVAL_DATE = 2;
    protected final LocalDate todayDate = LocalDate.now();

    @BeforeSuite(description = "Set up browser driver")
    public void setUp() {
        DriverManager.setDriver(DriverFactory.getDriver());
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