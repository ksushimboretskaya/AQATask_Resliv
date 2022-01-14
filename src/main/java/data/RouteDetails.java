package data;

public class RouteDetails {
    private String departureCityName;
    private String destinationCityName;
    private String departureDate;

    public RouteDetails(String departureCityName, String destinationCityName, String departureDate) {
        this.departureCityName = departureCityName;
        this.destinationCityName = destinationCityName;
        this.departureDate = departureDate;
    }

    public String getDepartureCityName() {
        return departureCityName;
    }

    public void setDepartureCityName(String departureCityName) {
        this.departureCityName = departureCityName;
    }

    public String getDestinationCityName() {
        return destinationCityName;
    }

    public void setDestinationCityName(String destinationCityName) {
        this.destinationCityName = destinationCityName;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
}
