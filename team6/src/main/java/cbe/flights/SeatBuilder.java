package cbe.flights;

public class SeatBuilder {
    private Flight flight;
    private double price;
    private SeatClass seatClass;

    public SeatBuilder setFlight(Flight flight) {
        this.flight = flight;
        return this;
    }

    public SeatBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public SeatBuilder setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
        return this;
    }

    public Seat createSeat() {
        return new Seat(price, seatClass);
    }
    public Seat buildEconomySeat() {
        return new Seat(price, SeatClass.ECONOMY);
    }
    public Seat buildBusinessSeat() {
        return new Seat(price, SeatClass.BUSINESS);
    }
}