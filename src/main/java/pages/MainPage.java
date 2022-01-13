package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainPage extends BasePage {

    @FindBy(xpath = "(//input[contains(@class, 'autocomplete__input')])[1]")
    private WebElement departureCity;

    @FindBy(xpath = "(//input[contains(@class, 'autocomplete__input')])[2]")
    private WebElement destinationCity;

    @FindBy(xpath = "//select[contains(@class,'calendar-caption__select')]")
    private WebElement tripMonthAndYear;

    @FindBy(xpath = "//*[contains(@class,'trip-duration__input-wrapper --departure')]")
    private WebElement departureDate;

    @FindBy(xpath = "//*[contains(@class,'trip-duration__input-wrapper --return')]")
    private WebElement arrivalDate;

    @FindBy(xpath = "//*[contains(@class, 'additional-fields --avia')]")
    private WebElement additionalFields;

    @FindBy(xpath = "//*[contains(@class,'additional-fields__passenger-value')]")
    private WebElement amountOfAdults;

    @FindBy(xpath = "(//*[contains(@class,'additional-fields__passenger-control --increment')])[1]")
    private WebElement adultsControlIncrement;

    @FindBy(xpath = "(//*[contains(@class,'additional-fields__passenger-control --increment')])[2]")
    private WebElement childrenControlIncrement;

    @FindBy(xpath = "//*[contains(@class,'additional-fields__done-button')]")
    private WebElement submitAdditionalButton;

    @FindBy(xpath = "//button[contains(@type,'submit')]")
    private WebElement searchTicketsButton;

    @FindBy(xpath = "//*[contains(@class,'prediction-advice__title')]")
    private WebElement preconditionAdviceTittle;

    private final static String MONTH_AND_YEAR_PATTERN = "yyyy-MM";
    private final static String AMOUNT_OF_ADULTS = "1";

    public MainPage() {
        super();
    }

    public MainPage chooseDepartureCountry(String nameOfDepartureCountry) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(departureCity)).click();
        departureCity.sendKeys(Keys.CONTROL + "A");
        departureCity.sendKeys(Keys.DELETE);
        departureCity.sendKeys(nameOfDepartureCountry);
        return new MainPage();
    }

    public MainPage chooseDestinationCountry(String nameOfArrivalCountry) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(destinationCity)).click();
        destinationCity.sendKeys(nameOfArrivalCountry);
        return new MainPage();
    }

    public MainPage chooseDepartureDate(LocalDate todayDate, Integer incrementForDepartureDate) {
        webDriverWait.until(ExpectedConditions.visibilityOf(departureDate)).click();
        chooseMonthAndYear(todayDate, MONTH_AND_YEAR_PATTERN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createDayLocator(todayDate.plusDays(incrementForDepartureDate)
                .getDayOfMonth()))).click();
        return new MainPage();
    }

    public MainPage chooseArrivalDate(LocalDate todayDate, Integer incrementForArrivalDate) {
        webDriverWait.until(ExpectedConditions.visibilityOf(arrivalDate)).click();
        chooseMonthAndYear(todayDate, MONTH_AND_YEAR_PATTERN);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(createDayLocator(todayDate.plusDays(incrementForArrivalDate)
                .getDayOfMonth()))).click();
        return new MainPage();
    }

    public void chooseMonthAndYear(LocalDate date, String datePattern) {
        new Select(tripMonthAndYear).selectByValue(date.format(DateTimeFormatter.ofPattern(datePattern)));
    }

    public WebElement createDayLocator(int date) {
        return driver.findElement(By.xpath("//*[contains(@class,'calendar-day__date') and text()=" + date + "]"));
    }

    public MainPage chooseAmountOfPassengers() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(additionalFields)).click();
        if (!amountOfAdults.getText().equals(AMOUNT_OF_ADULTS)) {
            adultsControlIncrement.click();
        }
        childrenControlIncrement.click();
        if (submitAdditionalButton.isDisplayed()) {
            submitAdditionalButton.click();
        }
        return new MainPage();
    }

    public MainPage submitSearchTickets() {
        webDriverWait.until(ExpectedConditions.visibilityOf(searchTicketsButton)).click();
        switchToResultsPageTab();
        webDriverWait.until(ExpectedConditions.visibilityOf(preconditionAdviceTittle));
        return this;
    }

    private void switchToResultsPageTab() {
        ArrayList<String> openTabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(openTabs.get(0)).close();
        driver.switchTo().window(openTabs.get(1));
    }
}