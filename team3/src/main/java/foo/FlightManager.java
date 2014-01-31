package foo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pawel.zawistowski on 31.01.14.
 */
public class FlightManager {
    private Map<String, Flight> flightInfos = new HashMap<String, Flight>();

    public int getAvailableSeats(String flightCode) {
        return getFlightInfo(flightCode).getAvailableSeats();
    }

    public void addFlight(String flightCode, Flight fi) {
        flightInfos.put(flightCode, fi);
    }

    public float getCheapestSeat(String flightCode) {
        return getFlightInfo(flightCode).getCheapestSeat();
    }

    Flight getFlightInfo(String flightCode) {
        Flight info;
        if (flightInfos.containsKey(flightCode)) {
            info = flightInfos.get(flightCode);
        } else {
            throw new FlightNotFoundException();
        }
        return info;
    }

    public int bookSeat(String flightNumber) {
        return getFlightInfo(flightNumber).bookSeat();
    }
}
