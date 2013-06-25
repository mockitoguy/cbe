package com.example;

import java.math.BigDecimal;

public class Seat {
    private Integer number;
    private BigDecimal price;
    private boolean available;

    public Seat() {
    }

    public Seat(Integer seatNumber, BigDecimal price, boolean available) {
        this.number = seatNumber;
        this.price = price;
        this.available = available;
    }

    public Integer getNumber() {
        return number;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
