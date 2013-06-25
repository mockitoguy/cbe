package pl.allegro.edu.tdd.domain;

public class Flight {
  private String no;
  private int availableSeats;

  public Flight(String no, int availableSeats) {
    this.no = no;
    this.availableSeats = availableSeats;
  }

  public int getAvailableSeats() {
    return availableSeats;
  }

  public void setAvailableSeats(int availableSeats) {
    this.availableSeats = availableSeats;
  }

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }
}
