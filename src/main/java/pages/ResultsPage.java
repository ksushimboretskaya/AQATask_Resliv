package pages;

import data.AviaTickets;
import data.RouteDetails;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ResultsPage extends BasePage {

    @FindBy(xpath = "//*[@data-test-id='ticket-desktop']")
    private List<WebElement> ticketElement;

    @FindBy(xpath = "(//*[@class ='segment-route__endpoint origin']/*[@class = 'segment-route__city'])[position() mod 2 = 1]")
    private List<WebElement> departureCityLabel;

    @FindBy(xpath = "(//*[@class ='segment-route__endpoint destination']/*[@class = 'segment-route__city'])[position() mod 2 = 1]")
    private List<WebElement> destinationCityLabel;

    @FindBy(xpath = "(//*[@class ='segment-route__endpoint origin']/*[@class = 'segment-route__city'])[position() mod 2 = 0]")
    private List<WebElement> returnDepartureCityLabel;

    @FindBy(xpath = "(//*[@class ='segment-route__endpoint destination']/*[@class = 'segment-route__city'])[position() mod 2 = 0]")
    private List<WebElement> returnDestinationCityLabel;

    @FindBy(xpath = "(//*[@class ='segment-route__endpoint origin']/*[@class = 'segment-route__date'])[position() mod 2 = 1]")
    private List<WebElement> departureDateLabel;

    @FindBy(xpath = "(//*[@class ='segment-route__endpoint origin']/*[@class = 'segment-route__date'])[position() mod 2 = 0]")
    private List<WebElement> returnDepartureDateLabel;

    @FindBy(xpath = "//*[@data-test-id='ticket-desktop']//*[@data-test-id='price']")
    private List<WebElement> ticketsPriceLabel;

    private final AviaTickets aviaTicket = new AviaTickets();
    private final Logger logger = Logger.getLogger("Results page logger");

    public ResultsPage() {
        super();
    }

    private List<AviaTickets> fetchAviaTicketsDetails() {
        WaitUtils.waitForVisibilityOfAllElements(ticketElement);
        List<AviaTickets> aviaTicketsList = new ArrayList<>();
        for (int i = 0; i < ticketElement.size(); i++) {
            aviaTicket.setPrice(ticketsPriceLabel.get(i).getText());
            aviaTicket.setRouteDetailsTo(new RouteDetails(departureCityLabel.get(i).getText(), destinationCityLabel.get(i).getText(),
                    departureDateLabel.get(i).getText()));
            aviaTicket.setRouteDetailsFrom(new RouteDetails(returnDepartureCityLabel.get(i).getText(), returnDestinationCityLabel.get(i).getText(),
                    returnDepartureDateLabel.get(i).getText()));
            aviaTicketsList.add(aviaTicket);
        }
        logger.info("Successfully retrieved data about tickets");
        return aviaTicketsList;
    }

    public List<String> getDepartureCityNameList() {
        WaitUtils.waitForVisibilityOfAllElements(departureCityLabel);
        List<AviaTickets> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> departureCityNamesList = new ArrayList<>();
        for (AviaTickets aviaTickets : aviaTicketsList) {
            departureCityNamesList.add(aviaTickets.getRouteDetailsTo().getDepartureCityName());
        }
        logger.info("Successfully retrieved departure city names");
        return departureCityNamesList;
    }

    public List<String> getDestinationCityNameList() {
        WaitUtils.waitForVisibilityOfAllElements(destinationCityLabel);
        List<AviaTickets> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> destinationCityNamesList = new ArrayList<>();
        for (AviaTickets aviaTickets : aviaTicketsList) {
            destinationCityNamesList.add(aviaTickets.getRouteDetailsTo().getDestinationCityName());
        }
        logger.info("Successfully retrieved destination city names");
        return destinationCityNamesList;
    }

    public List<String> getDepartureDate() {
        WaitUtils.waitForVisibilityOfAllElements(departureDateLabel);
        List<AviaTickets> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> departureDateList = new ArrayList<>();
        for (AviaTickets aviaTickets : aviaTicketsList) {
            departureDateList.add(aviaTickets.getRouteDetailsTo().getDepartureDate());
        }
        logger.info("Successfully retrieved departure date");
        return departureDateList;
    }

    public List<String> getReturnDepartureCityNameList() {
        WaitUtils.waitForVisibilityOfAllElements(returnDepartureCityLabel);
        List<AviaTickets> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> returnDepartureCityNamesList = new ArrayList<>();
        for (AviaTickets aviaTickets : aviaTicketsList) {
            returnDepartureCityNamesList.add(aviaTickets.getRouteDetailsFrom().getDepartureCityName());
        }
        logger.info("Successfully retrieved return departure city names");
        return returnDepartureCityNamesList;
    }

    public List<String> getReturnDestinationCityNameList() {
        WaitUtils.waitForVisibilityOfAllElements(departureCityLabel);
        List<AviaTickets> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> returnDestinationCityNamesList = new ArrayList<>();
        for (AviaTickets aviaTickets : aviaTicketsList) {
            returnDestinationCityNamesList.add(aviaTickets.getRouteDetailsFrom().getDestinationCityName());
        }
        logger.info("Successfully retrieved return destination city names");
        return returnDestinationCityNamesList;
    }

    public List<String> getReturnDepartureDate() {
        WaitUtils.waitForVisibilityOfAllElements(returnDepartureDateLabel);
        List<AviaTickets> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> returnDepartureDateList = new ArrayList<>();
        for (AviaTickets aviaTickets : aviaTicketsList) {
            returnDepartureDateList.add(aviaTickets.getRouteDetailsFrom().getDepartureDate());
        }
        logger.info("Successfully retrieved return departure date");
        return returnDepartureDateList;
    }

    public List<String> getTicketPricesList() {
        WaitUtils.waitForVisibilityOfAllElements(ticketsPriceLabel);
        List<AviaTickets> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> ticketPriceList = new ArrayList<>();
        for (AviaTickets aviaTickets : aviaTicketsList) {
            ticketPriceList.add(aviaTickets.getPrice());
        }
        logger.info("Successfully retrieved ticket prices");
        return ticketPriceList;
    }
}