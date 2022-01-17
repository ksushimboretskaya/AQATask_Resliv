package testPages;

import base.BaseTest;
import listener.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.MainPage;
import pages.ResultsPage;
import utils.DateUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Listeners({TestListener.class})
public class ResultsPageTest extends BaseTest {

    private static final String DATE_PATTERN = "dd MMM yyyy, E";
    private static final String MOSCOW_CITY = "Москва";
    private static final String SANKT_PETERBURG_CITY = "Санкт-Петербург";
    private final static String AMOUNT_OF_ADULTS = "1";
    private final static String AMOUNT_OF_CHILDREN = "1";
    private final LocalDate TODAY_DATE = DateUtils.getTheDateRelativeToTodayPlusTheNumberOfDay(0);
    private final LocalDate TOMORROW_DATE = DateUtils.getTheDateRelativeToTodayPlusTheNumberOfDay(1);
    private final LocalDate DAY_AFTER_TOMORROW_DATE = DateUtils.getTheDateRelativeToTodayPlusTheNumberOfDay(2);

    private final SoftAssert softAssert = new SoftAssert();

    @Test(priority = 1, description = "Verify departure and destination city name")
    public void verifyCityName() {
        new MainPage().chooseDepartureCountry(MOSCOW_CITY)
                .chooseDestinationCountry(SANKT_PETERBURG_CITY)
                .setDepartureDate(TODAY_DATE, TOMORROW_DATE)
                .setReturnDepartureDate(TODAY_DATE, DAY_AFTER_TOMORROW_DATE)
                .setAmountOfPassengers(AMOUNT_OF_ADULTS, AMOUNT_OF_CHILDREN)
                .submitSearchTickets();

        ResultsPage resultsPage = new ResultsPage();
        List<String> departureCityNamesList = resultsPage.getDepartureCityNameList();
        List<String> destinationCityNameList = resultsPage.getDestinationCityNameList();
        List<String> returnDepartureCityNameList = resultsPage.getReturnDepartureCityNameList();
        List<String> returnDestinationCityNameList = resultsPage.getReturnDestinationCityNameList();

        softAssert.assertTrue(departureCityNamesList.stream().allMatch(departureCityName -> departureCityName.equals(MOSCOW_CITY)),
                String.format("The actual departure city names list {%s} doesn't match expected", departureCityNamesList.toArray()));
        softAssert.assertTrue(destinationCityNameList.stream().allMatch(destinationCityName -> destinationCityName.equals(SANKT_PETERBURG_CITY)),
                String.format("The actual destination city names list {%s} doesn't match expected", destinationCityNameList.toArray()));
        softAssert.assertTrue(returnDepartureCityNameList.stream().allMatch(returnDepartureCityName -> returnDepartureCityName.equals(SANKT_PETERBURG_CITY)),
                String.format("The actual return departure city names list {%s} doesn't match expected", returnDepartureCityNameList.toArray()));
        softAssert.assertTrue(returnDestinationCityNameList.stream().allMatch(returnDestinationCityName -> returnDestinationCityName.equals(MOSCOW_CITY)),
                String.format("The actual return destination city names list {%s} doesn't match expected", returnDestinationCityNameList.toArray()));
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify departure and return departure date")
    public void verifyDepartureAndReturnDepartureDate() {
        new MainPage().chooseDepartureCountry(MOSCOW_CITY)
                .chooseDestinationCountry(SANKT_PETERBURG_CITY)
                .setDepartureDate(TODAY_DATE, TOMORROW_DATE)
                .setReturnDepartureDate(TODAY_DATE, DAY_AFTER_TOMORROW_DATE)
                .setAmountOfPassengers(AMOUNT_OF_ADULTS, AMOUNT_OF_CHILDREN)
                .submitSearchTickets();

        ResultsPage resultsPage = new ResultsPage();
        List<String> departureDateList = resultsPage.getDepartureDate();
        List<String> returnDepartureDateList = resultsPage.getReturnDepartureDate();
        String expectedDepartureDate = DateUtils.formatDateToPattern(TOMORROW_DATE, DATE_PATTERN);
        String expectedReturnDepartureDate = DateUtils.formatDateToPattern(DAY_AFTER_TOMORROW_DATE, DATE_PATTERN);

        softAssert.assertTrue(departureDateList.stream().allMatch(departureDate -> departureDate.equals(expectedDepartureDate)),
                String.format("The actual departure date list {%s} doesn't match expected", departureDateList.toArray()));
        softAssert.assertTrue(returnDepartureDateList.stream().allMatch(returnDepartureDate -> returnDepartureDate.equals(expectedReturnDepartureDate)),
                String.format("The actual return departure date list {%s} doesn't match expected", returnDepartureDateList.toArray()));
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Verify the sorting of ticket prices")
    public void verifyTheSortingOfTicketPrices() {
        new MainPage().chooseDepartureCountry(MOSCOW_CITY)
                .chooseDestinationCountry(SANKT_PETERBURG_CITY)
                .setDepartureDate(TODAY_DATE, TOMORROW_DATE)
                .setReturnDepartureDate(TODAY_DATE, DAY_AFTER_TOMORROW_DATE)
                .setAmountOfPassengers(AMOUNT_OF_ADULTS, AMOUNT_OF_CHILDREN)
                .submitSearchTickets();

        ResultsPage resultsPage = new ResultsPage();
        List<Integer> actualTicketsPriceList = resultsPage.getTicketPricesList();
        List<Integer> expectedTicketsPriceList = resultsPage.getTicketPricesList();
        expectedTicketsPriceList.sort(Comparator.naturalOrder());

        softAssert.assertTrue(actualTicketsPriceList.equals(expectedTicketsPriceList),
                String.format("The actual price list {%s} doesn't match expected", actualTicketsPriceList.toArray()));
        softAssert.assertAll();
    }
}