package pl.allegro.tdd;

public class SeatBuilder {
  private boolean booked = false;
  private int number = 0;
  private double price = 0;
  
  public SeatBuilder booked(boolean booked) {
    this.booked = booked;
    
    return this;
  }
  
  public SeatBuilder withNumber(int number) {
    this.number = number;
    
    return this;
  }
  
  public SeatBuilder withPrice(double price) {
    this.price = price;
    
    return this;
  }
  
  public Seat build() {
    Seat seat = new Seat();
    
    seat.setBooked(booked);
    seat.setNumber(number);
    seat.setPrice(price);
    
    return seat;
  }
}
