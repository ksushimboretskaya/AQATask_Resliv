package testPages;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ResultsPage;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ResultsPageTest extends BaseTest {

    private static final String DATE_PATTERN = "dd MMM yyyy, E";

    @Test(priority = 1, description = "Verify departure city name")
    public void verifyDepartureCityName() {
        new MainPage().chooseDepartureCountry(DEPARTURE_CITY_NAME).chooseDestinationCountry(DESTINATION_CITY_NAME)
                .chooseDepartureDate(todayDate, INCREMENT_FOR_DEPARTURE_DATE).chooseArrivalDate(todayDate, INCREMENT_FOR_ARRIVAL_DATE)
                .chooseAmountOfPassengers().submitSearchTickets();
        for (WebElement actualDepartureCityName : new ResultsPage().getDepartureCityNameList()) {
            Assert.assertEquals(actualDepartureCityName.getText(), DEPARTURE_CITY_NAME, "The actual departure city name doesn't match expected");
        }
    }

    @Test(priority = 2, description = "Verify destination city name")
    public void verifyDestinationCityName() {
        new MainPage().chooseDepartureCountry(DEPARTURE_CITY_NAME).chooseDestinationCountry(DESTINATION_CITY_NAME)
                .chooseDepartureDate(todayDate, INCREMENT_FOR_DEPARTURE_DATE).chooseArrivalDate(todayDate, INCREMENT_FOR_ARRIVAL_DATE)
                .chooseAmountOfPassengers().submitSearchTickets();
        for (WebElement actualDestinationCityName : new ResultsPage().getDestinationCityNameList()) {
            Assert.assertEquals(actualDestinationCityName.getText(), DESTINATION_CITY_NAME, "The actual destination city name doesn't match expected");
        }
    }

    @Test(priority = 3, description = "Verify return departure city name")
    public void verifyReturnDepartureCityName() {
        new MainPage().chooseDepartureCountry(DEPARTURE_CITY_NAME).chooseDestinationCountry(DESTINATION_CITY_NAME)
                .chooseDepartureDate(todayDate, INCREMENT_FOR_DEPARTURE_DATE).chooseArrivalDate(todayDate, INCREMENT_FOR_ARRIVAL_DATE)
                .chooseAmountOfPassengers().submitSearchTickets();
        for (WebElement actualReturnDepartureCityName : new ResultsPage().getReturnDepartureCityNameList()) {
            Assert.assertEquals(actualReturnDepartureCityName.getText(), DESTINATION_CITY_NAME, "The actual return departure city name doesn't match expected");
        }
    }

    @Test(priority = 4, description = "Verify return destination city name")
    public void verifyReturnDestinationCityName() {
        new MainPage().chooseDepartureCountry(DEPARTURE_CITY_NAME).chooseDestinationCountry(DESTINATION_CITY_NAME)
                .chooseDepartureDate(todayDate, INCREMENT_FOR_DEPARTURE_DATE).chooseArrivalDate(todayDate, INCREMENT_FOR_ARRIVAL_DATE)
                .chooseAmountOfPassengers().submitSearchTickets();
        for (WebElement actualReturnDestinationCityName : new ResultsPage().getReturnDestinationCityNameList()) {
            Assert.assertEquals(actualReturnDestinationCityName.getText(), DEPARTURE_CITY_NAME, "The actual return destination city name doesn't match expected");
        }
    }

    @Test(priority = 5, description = "Verify departure date")
    public void verifyDepartureDate() {
        new MainPage().chooseDepartureCountry(DEPARTURE_CITY_NAME).chooseDestinationCountry(DESTINATION_CITY_NAME)
                .chooseDepartureDate(todayDate, INCREMENT_FOR_DEPARTURE_DATE).chooseArrivalDate(todayDate, INCREMENT_FOR_ARRIVAL_DATE)
                .chooseAmountOfPassengers().submitSearchTickets();
        String expectedDepartureDate = todayDate.plusDays(INCREMENT_FOR_DEPARTURE_DATE).format(DateTimeFormatter.ofPattern(DATE_PATTERN)).toLowerCase().replace(".", "");
        for (WebElement actualDepartureDate : new ResultsPage().getDepartureDate()) {
            Assert.assertEquals(actualDepartureDate.getText(), expectedDepartureDate, "The actual departure date doesn't match expected");
        }
    }

    @Test(priority = 6, description = "Verify return departure date")
    public void verifyReturnDepartureDate() {
        new MainPage().chooseDepartureCountry(DEPARTURE_CITY_NAME).chooseDestinationCountry(DESTINATION_CITY_NAME)
                .chooseDepartureDate(todayDate, INCREMENT_FOR_DEPARTURE_DATE).chooseArrivalDate(todayDate, INCREMENT_FOR_ARRIVAL_DATE)
                .chooseAmountOfPassengers().submitSearchTickets();
        String expectedReturnDepartureDate = todayDate.plusDays(INCREMENT_FOR_ARRIVAL_DATE).format(DateTimeFormatter.ofPattern(DATE_PATTERN)).toLowerCase().replace(".", "");
        for (WebElement actualReturnDepartureDate : new ResultsPage().getReturnDepartureDate()) {
            Assert.assertEquals(actualReturnDepartureDate.getText(), expectedReturnDepartureDate, "The actual return departure date doesn't match expected");
        }
    }

    @Test(priority = 7, description = "Verify the sorting of ticket prices")
    public void verifyTheSortingOfTicketPrices() {
        new MainPage().chooseDepartureCountry(DEPARTURE_CITY_NAME).chooseDestinationCountry(DESTINATION_CITY_NAME)
                .chooseDepartureDate(todayDate, INCREMENT_FOR_DEPARTURE_DATE).chooseArrivalDate(todayDate, INCREMENT_FOR_ARRIVAL_DATE)
                .chooseAmountOfPassengers().submitSearchTickets();
        List<String> actualTicketsPriceList = new ResultsPage().getTicketPricesList();
        List<String> expectedTicketsPriceSortedList = actualTicketsPriceList.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(actualTicketsPriceList, expectedTicketsPriceSortedList, "The price on the page doesn't sorted from min to max");
    }
}