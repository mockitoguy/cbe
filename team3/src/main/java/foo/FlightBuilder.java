package foo;

import java.util.Date;

public class FlightBuilder {
    private String from;
    private float[] prices;
    private String to;
    private String flightNumber;
    private Date date;

    public FlightBuilder from(String from) {
        this.from = from;
        return this;
    }

    public FlightBuilder withPrices(float... prices) {
        this.prices = prices;
        return this;
    }

    public Flight build() {
        return new Flight(flightNumber, from, to, date, prices);
    }

    public FlightBuilder to(String to) {
        this.to = to;
        return this;
    }

    public FlightBuilder withNumber(String number) {
        this.flightNumber = number;
        return this;
    }

    public FlightBuilder scheduled(Date date) {
        this.date = date;
        return this;
    }

}