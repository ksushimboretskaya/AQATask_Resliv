package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class ResultsPage extends BasePage {

    @FindBy(xpath = "(//*[@class ='segment-route__body']/*[@class ='segment-route__endpoint origin']/*[@class = 'segment-route__city'])[position() mod 2 = 1]")
    private List<WebElement> departureCity;

    @FindBy(xpath = "(//*[@class ='segment-route__body']/*[@class ='segment-route__endpoint destination']/*[@class = 'segment-route__city'])[position() mod 2 = 1]")
    private List<WebElement> destinationCity;

    @FindBy(xpath = "(//*[@class ='segment-route__body']/*[@class ='segment-route__endpoint origin']/*[@class = 'segment-route__city'])[position() mod 2 = 0]")
    private List<WebElement> returnDepartureCity;

    @FindBy(xpath = "(//*[@class ='segment-route__body']/*[@class ='segment-route__endpoint destination']/*[@class = 'segment-route__city'])[position() mod 2 = 0]")
    private List<WebElement> returnDestinationCity;

    @FindBy(xpath = "(//*[@class ='segment-route__body']/*[@class ='segment-route__endpoint origin']/*[@class = 'segment-route__date'])[position() mod 2 = 1]")
    private List<WebElement> departureDate;

    @FindBy(xpath = "(//*[@class ='segment-route__body']/*[@class ='segment-route__endpoint origin']/*[@class = 'segment-route__date'])[position() mod 2 = 0]")
    private List<WebElement> returnDepartureDate;

    @FindBy(xpath = "//*[@data-test-id='ticket-desktop']//*[@data-test-id='price']")
    private List<WebElement> ticketsPrice;

    public ResultsPage() {
        super();
    }

    public List<WebElement> getDepartureCityNameList() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(departureCity));
        return departureCity;
    }

    public List<WebElement> getDestinationCityNameList() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(destinationCity));
        return destinationCity;
    }

    public List<WebElement> getReturnDepartureCityNameList() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(returnDepartureCity));
        return returnDepartureCity;
    }

    public List<WebElement> getReturnDestinationCityNameList() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(returnDestinationCity));
        return returnDestinationCity;
    }

    public List<WebElement> getDepartureDate() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(departureDate));
        return departureDate;
    }

    public List<WebElement> getReturnDepartureDate() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(returnDepartureDate));
        return returnDepartureDate;
    }

    public List<String> getTicketPricesList() {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElements(ticketsPrice));
        return ticketsPrice.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}