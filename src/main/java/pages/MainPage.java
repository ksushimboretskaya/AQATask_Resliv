package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.WaitUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class MainPage extends BasePage {

    @FindBy(xpath = "(//input[contains(@class, 'autocomplete__input')])[1]")
    private WebElement departureCityInputField;

    @FindBy(xpath = "(//input[contains(@class, 'autocomplete__input')])[2]")
    private WebElement destinationCityInputField;

    @FindBy(xpath = "//select[contains(@class,'calendar-caption__select')]")
    private WebElement tripMonthAndYearSelectElement;

    @FindBy(xpath = "//*[contains(@class,'trip-duration__input-wrapper --departure')]")
    private WebElement departureDateSelectElement;

    @FindBy(xpath = "//*[contains(@class,'trip-duration__input-wrapper --return')]")
    private WebElement returnDepartureDateSelectElement;

    @FindBy(xpath = "//*[contains(@class, 'additional-fields --avia')]")
    private WebElement additionalFieldsSelectElements;

    @FindBy(xpath = "(//*[contains(@class,'additional-fields__passenger-value')])[1]")
    private WebElement amountOfAdultsLabel;

    @FindBy(xpath = "(//*[contains(@class,'additional-fields__passenger-value')])[2]")
    private WebElement amountsOfChildrenLabel;

    @FindBy(xpath = "(//*[contains(@class,'additional-fields__passenger-control --increment')])[1]")
    private WebElement adultsControlIncrementButton;

    @FindBy(xpath = "(//*[contains(@class,'additional-fields__passenger-control --increment')])[2]")
    private WebElement childrenControlIncrementButton;

    @FindBy(xpath = "//*[contains(@class,'additional-fields__done-button')]")
    private WebElement submitAdditionalButton;

    @FindBy(xpath = "//button[contains(@type,'submit')]")
    private WebElement searchTicketsButton;

    @FindBy(xpath = "//*[contains(@class,'prediction-advice__title')]")
    private WebElement preconditionAdviceTittle;

    private final static String MONTH_AND_YEAR_PATTERN = "yyyy-MM";
    private final static Logger logger = Logger.getLogger("Main page logger");

    public MainPage() {
        super();
    }

    public MainPage chooseDepartureCountry(String nameOfDepartureCity) {
        WaitUtils.waitForVisibility(departureCityInputField);
        departureCityInputField.click();
        logger.info("Successfully clicked on the departure city input field");
        departureCityInputField.sendKeys(Keys.CONTROL + "A");
        departureCityInputField.sendKeys(Keys.DELETE);
        logger.info("Successfully cleared the departure city input field");
        departureCityInputField.sendKeys(nameOfDepartureCity);
        logger.info(String.format("Departure city name {%s} entered successfully", nameOfDepartureCity));
        return new MainPage();
    }

    public MainPage chooseDestinationCountry(String nameOfDestinationCity) {
        WaitUtils.waitForVisibility(destinationCityInputField);
        destinationCityInputField.click();
        logger.info("Successfully clicked on the destination city input field");
        destinationCityInputField.sendKeys(nameOfDestinationCity);
        logger.info(String.format("Destination city name {%s} entered successfully", nameOfDestinationCity));
        return new MainPage();
    }

    public MainPage setDepartureDate(LocalDate todayDate, LocalDate departureDate) {
        WaitUtils.waitForVisibility(departureDateSelectElement);
        departureDateSelectElement.click();
        logger.info("Successfully clicked on the departure date select element");
        chooseMonthAndYear(todayDate, MONTH_AND_YEAR_PATTERN);
        logger.info("Successfully selected month and year");
        WebElement departureDateElement = createDateWebElement(departureDate.getDayOfMonth());
        logger.info("Successfully created departure date web element");
        WaitUtils.waitForVisibility(departureDateElement);
        departureDateElement.click();
        logger.info("Successfully clicked on the departure date");
        return new MainPage();
    }

    public MainPage setReturnDepartureDate(LocalDate todayDate, LocalDate returnDepartureDate) {
        WaitUtils.waitForVisibility(returnDepartureDateSelectElement);
        returnDepartureDateSelectElement.click();
        logger.info("Successfully clicked on the return departure date select element");
        chooseMonthAndYear(todayDate, MONTH_AND_YEAR_PATTERN);
        logger.info("Successfully selected month and year");
        WebElement returnDepartureDateElement = createDateWebElement(returnDepartureDate.getDayOfMonth());
        logger.info("Successfully created return departure date web element");
        WaitUtils.waitForVisibility(returnDepartureDateElement);
        returnDepartureDateElement.click();
        logger.info("Successfully clicked on the return departure date");
        return new MainPage();
    }

    private void chooseMonthAndYear(LocalDate date, String datePattern) {
        new Select(tripMonthAndYearSelectElement).selectByValue(date.format(DateTimeFormatter.ofPattern(datePattern)));
    }

    private WebElement createDateWebElement(int date) {
        return driver.findElement(By.xpath("//*[contains(@class,'calendar-day__date') and text()=" + date + "]"));
    }

    public MainPage setAmountOfPassengers(String amountOfAdults, String amountOfChildren) {
        WaitUtils.waitForVisibility(additionalFieldsSelectElements);
        additionalFieldsSelectElements.click();
        logger.info("Successfully clicked on the additional fields select elements");
        while (!amountOfAdultsLabel.getText().equals(amountOfAdults)) {
            adultsControlIncrementButton.click();
        }
        logger.info(String.format("Successfully set amount od adults {%s}", amountOfAdults));
        while (!amountsOfChildrenLabel.getText().equals(amountOfChildren)) {
            childrenControlIncrementButton.click();
        }
        logger.info(String.format("Successfully set amount od children {%s}", amountOfChildren));
        if (submitAdditionalButton.isDisplayed()) {
            submitAdditionalButton.click();
        }
        logger.info("Successfully clicked on submit additional fields button");
        return new MainPage();
    }

    public MainPage submitSearchTickets() {
        WaitUtils.waitForVisibility(searchTicketsButton);
        searchTicketsButton.click();
        logger.info("Successfully clicked on search tickets button");
        switchToOpenTab();
        WaitUtils.waitForVisibility(preconditionAdviceTittle);
        return this;
    }
}