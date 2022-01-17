package pages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.KeyboardUtils;
import utils.WaitUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Log4j2
public class MainPage extends BasePage {

    private final static String MONTH_AND_YEAR_PATTERN = "yyyy-MM";

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

    public MainPage() {
        super();
    }

    public MainPage chooseDepartureCountry(String nameOfDepartureCity) {
        WaitUtils.waitForVisibility(departureCityInputField);
        departureCityInputField.click();
        log.info("Successfully clicked on the departure city input field");

        KeyboardUtils.clearTheField(departureCityInputField);
        log.info("Successfully cleared the departure city input field");

        departureCityInputField.sendKeys(nameOfDepartureCity);
        log.info("Departure city name {} entered successfully", nameOfDepartureCity);
        return new MainPage();
    }

    public MainPage chooseDestinationCountry(String nameOfDestinationCity) {
        WaitUtils.waitForVisibility(destinationCityInputField);
        destinationCityInputField.click();
        log.info("Successfully clicked on the destination city input field");

        destinationCityInputField.sendKeys(nameOfDestinationCity);
        log.info("Destination city name {} entered successfully", nameOfDestinationCity);
        return new MainPage();
    }

    public MainPage setDepartureDate(LocalDate todayDate, LocalDate departureDate) {
        WaitUtils.waitForVisibility(departureDateSelectElement);
        departureDateSelectElement.click();
        log.info("Successfully clicked on the departure date select element");

        chooseMonthAndYear(todayDate, MONTH_AND_YEAR_PATTERN);
        log.info("Successfully selected month and year");

        WebElement departureDateElement = getDateWebElement(departureDate.getDayOfMonth());
        log.info("Successfully created departure date web element");

        WaitUtils.waitForVisibility(departureDateElement);
        departureDateElement.click();
        log.info("Successfully clicked on the departure date");
        return new MainPage();
    }

    public MainPage setReturnDepartureDate(LocalDate todayDate, LocalDate returnDepartureDate) {
        WaitUtils.waitForVisibility(returnDepartureDateSelectElement);
        returnDepartureDateSelectElement.click();
        log.info("Successfully clicked on the return departure date select element");

        chooseMonthAndYear(todayDate, MONTH_AND_YEAR_PATTERN);
        log.info("Successfully selected month and year");

        WebElement returnDepartureDateElement = getDateWebElement(returnDepartureDate.getDayOfMonth());
        log.info("Successfully created return departure date web element");

        WaitUtils.waitForVisibility(returnDepartureDateElement);
        returnDepartureDateElement.click();
        log.info("Successfully clicked on the return departure date");
        return new MainPage();
    }

    private void chooseMonthAndYear(LocalDate date, String datePattern) {
        new Select(tripMonthAndYearSelectElement).selectByValue(date.format(DateTimeFormatter.ofPattern(datePattern)));
    }

    private WebElement getDateWebElement(int date) {
        return driver.findElement(By.xpath("//*[contains(@class,'calendar-day__date') and text()=" + date + "]"));
    }

    public MainPage setAmountOfPassengers(String amountOfAdults, String amountOfChildren) {
        WaitUtils.waitForVisibility(additionalFieldsSelectElements);
        additionalFieldsSelectElements.click();
        log.info("Successfully clicked on the additional fields select elements");

        while (amountOfAdultsLabel.getText().equals(amountOfAdults)) {
            adultsControlIncrementButton.click();
        }
        log.info(String.format("Successfully set amount od adults {%s}", amountOfAdults));
        while (amountsOfChildrenLabel.getText().equals(amountOfChildren)) {
            childrenControlIncrementButton.click();
        }
        log.info(String.format("Successfully set amount od children {%s}", amountOfChildren));

        if (submitAdditionalButton.isDisplayed()) {
            submitAdditionalButton.click();
        }
        log.info("Successfully clicked on submit additional fields button");
        return new MainPage();
    }

    public ResultsPage submitSearchTickets() {
        WaitUtils.waitForVisibility(searchTicketsButton);
        searchTicketsButton.click();
        log.info("Successfully clicked on search tickets button");

        switchToOpenTab();
        WaitUtils.waitForVisibility(preconditionAdviceTittle);
        return new ResultsPage();
    }
}