package pl.allegro.tdd;

class Seat {
  private boolean booked = false;
  private int number;
  private double price;

  public boolean isBooked() {
    return booked;
  }

  public void setBooked(boolean booked) {
    this.booked = booked;
  }
  
  public int getNumber() {
    return number;
  }

  public double getPrice() {
    return price;
  }
  
  void setNumber(int number) {
    this.number = number;
  }

  void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Seat{" + "number=" + number + ", price=" + price + '}';
  }
  
  

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 61 * hash + this.number;
    hash = 61 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    
    if (getClass() != obj.getClass()) {
      return false;
    }
    
    final Seat other = (Seat) obj;
    
    if (this.number != other.number) {
      return false;
    }
    
    return true;
  }
}
