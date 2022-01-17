package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RouteDetails {

    private String departureCityName;
    private String destinationCityName;
    private String departureDate;
}
