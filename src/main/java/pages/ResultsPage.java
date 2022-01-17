package pages;

import com.google.common.base.CharMatcher;
import data.AviaTicket;
import data.RouteDetails;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitUtils;

import java.util.ArrayList;
import java.util.List;

@Log4j2
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

    public ResultsPage() {
        super();
    }

    private List<AviaTicket> fetchAviaTicketsDetails() {
        WaitUtils.waitForVisibilityOfAllElements(ticketElement);
        List<AviaTicket> aviaTicketsList = new ArrayList<>();
        for (int i = 0; i < ticketElement.size(); i++) {
            AviaTicket aviaTicket = new AviaTicket();
            aviaTicket.setPrice(CharMatcher.DIGIT.retainFrom(ticketsPriceLabel.get(i).getText()));
            aviaTicket.setRouteDetailsTo(new RouteDetails(departureCityLabel.get(i).getText(),
                    destinationCityLabel.get(i).getText(), departureDateLabel.get(i).getText()));
            aviaTicket.setRouteDetailsFrom(new RouteDetails(returnDepartureCityLabel.get(i).getText(),
                    returnDestinationCityLabel.get(i).getText(), returnDepartureDateLabel.get(i).getText()));
            aviaTicketsList.add(aviaTicket);
        }
        log.info("Successfully retrieved data about tickets");
        return aviaTicketsList;
    }

    public List<String> getDepartureCityNameList() {
        WaitUtils.waitForVisibilityOfAllElements(departureCityLabel);
        List<AviaTicket> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> departureCityNamesList = new ArrayList<>();
        for (AviaTicket aviaTickets : aviaTicketsList) {
            departureCityNamesList.add(aviaTickets.getRouteDetailsTo().getDepartureCityName());
        }
        log.info("Successfully retrieved departure city names");
        return departureCityNamesList;
    }

    public List<String> getDestinationCityNameList() {
        WaitUtils.waitForVisibilityOfAllElements(destinationCityLabel);
        List<AviaTicket> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> destinationCityNamesList = new ArrayList<>();
        for (AviaTicket aviaTickets : aviaTicketsList) {
            destinationCityNamesList.add(aviaTickets.getRouteDetailsTo().getDestinationCityName());
        }
        log.info("Successfully retrieved destination city names");
        return destinationCityNamesList;
    }

    public List<String> getDepartureDate() {
        WaitUtils.waitForVisibilityOfAllElements(departureDateLabel);
        List<AviaTicket> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> departureDateList = new ArrayList<>();
        for (AviaTicket aviaTickets : aviaTicketsList) {
            departureDateList.add(aviaTickets.getRouteDetailsTo().getDepartureDate());
        }
        log.info("Successfully retrieved departure date");
        return departureDateList;
    }

    public List<String> getReturnDepartureCityNameList() {
        WaitUtils.waitForVisibilityOfAllElements(returnDepartureCityLabel);
        List<AviaTicket> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> returnDepartureCityNamesList = new ArrayList<>();
        for (AviaTicket aviaTickets : aviaTicketsList) {
            returnDepartureCityNamesList.add(aviaTickets.getRouteDetailsFrom().getDepartureCityName());
        }
        log.info("Successfully retrieved return departure city names");
        return returnDepartureCityNamesList;
    }

    public List<String> getReturnDestinationCityNameList() {
        WaitUtils.waitForVisibilityOfAllElements(departureCityLabel);
        List<AviaTicket> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> returnDestinationCityNamesList = new ArrayList<>();
        for (AviaTicket aviaTickets : aviaTicketsList) {
            returnDestinationCityNamesList.add(aviaTickets.getRouteDetailsFrom().getDestinationCityName());
        }
        log.info("Successfully retrieved return destination city names");
        return returnDestinationCityNamesList;
    }

    public List<String> getReturnDepartureDate() {
        WaitUtils.waitForVisibilityOfAllElements(returnDepartureDateLabel);
        List<AviaTicket> aviaTicketsList = fetchAviaTicketsDetails();
        List<String> returnDepartureDateList = new ArrayList<>();
        for (AviaTicket aviaTickets : aviaTicketsList) {
            returnDepartureDateList.add(aviaTickets.getRouteDetailsFrom().getDepartureDate());
        }
        log.info("Successfully retrieved return departure date");
        return returnDepartureDateList;
    }

    public List<Integer> getTicketPricesList() {
        WaitUtils.waitForVisibilityOfAllElements(ticketsPriceLabel);
        List<AviaTicket> aviaTicketsList = fetchAviaTicketsDetails();
        List<Integer> ticketPriceList = new ArrayList<>();
        for (AviaTicket aviaTickets : aviaTicketsList) {
            ticketPriceList.add(Integer.parseInt(aviaTickets.getPrice()));
        }
        log.info("Successfully retrieved ticket prices");
        return ticketPriceList;
    }
}