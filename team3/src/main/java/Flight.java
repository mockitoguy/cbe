import java.util.ArrayList;

public class Flight {

    String number;
    ArrayList<Seat> seats = new ArrayList<Seat>();

    public Flight setFlightNo(String sampleFlightName) {
        number = sampleFlightName;
        return this;
    }
}
