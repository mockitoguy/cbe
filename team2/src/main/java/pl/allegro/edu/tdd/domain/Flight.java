package pl.allegro.edu.tdd.domain;

import pl.allegro.edu.tdd.Place;

public class Flight {

  private String no;

  private Place origin;

  public Flight(String no) {
    this.no = no;
  }

  public Place getOrigin() {
    return origin;
  }

  public void setOrigin(Place origin) {
    this.origin = origin;
  }

  private Place destination;

  public String getNo() {
    return no;
  }

  public void setNo(String no) {
    this.no = no;
  }
}
