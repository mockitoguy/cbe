package pl.allegro.tdd;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class MyAssertions {
  
  private Collection collection = null;

  private MyAssertions(Collection collection) {
    this.collection = collection;
  }
  
  public static MyAssertions assertThat(Collection collection) {
    return new MyAssertions(collection);
  }
  
  public boolean containsFlights(String... flightNumbers) {
    List<String> fn = Arrays.asList(flightNumbers);
    
    for (Iterator it = collection.iterator(); it.hasNext();) {
      Flight flight = (Flight)it.next();
      if (!fn.contains(flight.getFlightNumber())) {
        return false;
      }
    }
    
    return true;
  }
}
