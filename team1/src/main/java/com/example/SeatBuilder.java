package com.example;

import com.sun.xml.internal.bind.v2.runtime.JAXBContextImpl;

import java.math.BigDecimal;

public class SeatBuilder {

    private Seat seat = new Seat();

    public SeatBuilder withNumber(Integer seatNumber) {
        seat.setNumber(seatNumber);
        return this;
    }

    public SeatBuilder available() {
        seat.setAvailable(true);
        return this;
    }

    public Seat build() {
        return seat;
    }

    public SeatBuilder booked() {
        seat.setAvailable(false);
        return this;
    }

    public SeatBuilder withPrice(String price) {
        seat.setPrice(new BigDecimal(price));
        return this;
    }
}
