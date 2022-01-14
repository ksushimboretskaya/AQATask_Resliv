package data;

public class AviaTickets {

    private String price;
    private RouteDetails routeDetailsTo;
    private RouteDetails routeDetailsFrom;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public RouteDetails getRouteDetailsTo() {
        return routeDetailsTo;
    }

    public void setRouteDetailsTo(RouteDetails routeDetailsTo) {
        this.routeDetailsTo = routeDetailsTo;
    }

    public RouteDetails getRouteDetailsFrom() {
        return routeDetailsFrom;
    }

    public void setRouteDetailsFrom(RouteDetails routeDetailsFrom) {
        this.routeDetailsFrom = routeDetailsFrom;
    }
}