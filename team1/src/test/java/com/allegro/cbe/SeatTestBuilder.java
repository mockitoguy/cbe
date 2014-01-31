package com.allegro.cbe;

public class SeatTestBuilder {

    private String number;
    private float price;
    private boolean booked;

    private int seatCounter = 1;

    private SeatTestBuilder() {
        number = "" + seatCounter++;
        price = 10f;
    }

    public static SeatTestBuilder seat() {
        return new SeatTestBuilder();
    }

    public SeatTestBuilder withNumber(String number) {
        this.number = number;
        return this;
    }

    public SeatTestBuilder withPrice(float price) {
        this.price = price;
        return this;
    }

    public Seat build() {
        Seat seat = new Seat(number, price);

        if (booked) {
            seat.book();
        }

        return seat;
    }

    public SeatTestBuilder book() {
        booked = true;
        return this;
    }
}
