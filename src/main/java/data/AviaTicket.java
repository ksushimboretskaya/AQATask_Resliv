package data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AviaTicket {

    private String price;
    private RouteDetails routeDetailsTo;
    private RouteDetails routeDetailsFrom;
}