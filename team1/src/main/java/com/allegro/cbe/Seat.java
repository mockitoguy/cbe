package com.allegro.cbe;

class Seat {

    private final String number;
    private final float price;
    private boolean booked = false;

    Seat(String number, float price) {
        this.number = number;
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public float getPrice() {
        return price;
    }

    public void book() {
        booked = true;
    }

    public boolean isAvailable() {
        return booked == false;
    }
}
