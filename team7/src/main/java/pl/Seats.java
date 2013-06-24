package pl;

import java.util.HashMap;
import java.util.Map;

public class Seats {
  private Map<Integer, Integer> priceForSeats = new HashMap<Integer, Integer>();

  public static Seats seats() {
    return new Seats();
  }

  public Seats forPrice(int price, int seatsNo) {
    priceForSeats.put(price, seatsNo);
    return this;
  }

  public int count() {
    int freeSeatsCount = 0;
    for (int no : priceForSeats.values()) {
      freeSeatsCount += no;
    }
    return freeSeatsCount;
  }

  public int cheapestAvailableSeatPrice() {
    Integer lowestPrice = null;
    for (Map.Entry<Integer, Integer> seatGroup : this.priceForSeats.entrySet()) {
      if (seatGroup.getValue() > 0) lowestPrice = seatGroup.getKey();
    }
    return lowestPrice;
  }
}
