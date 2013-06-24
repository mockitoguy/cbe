import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: cameleeck
 * Date: 24.06.13
 * Time: 11:07
 */
public class Flight {
    private String flightNumber;
    private Map<Integer, BigDecimal> seats = new HashMap<Integer, BigDecimal>();
    private List<Reservation> listOfReservation = new ArrayList<Reservation>();

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }


    public void addNewReservation(Reservation reservation) {
        this.listOfReservation.add(reservation);
    }

    public List<Reservation> getListOfReservation() {
        return listOfReservation;
    }

    public void addSeat(int seatsNumber, BigDecimal flightPrice) {
        seats.put(seatsNumber, flightPrice);

    }

    public BigDecimal getCheapestSeat() {
        BigDecimal cheapestSeat = new BigDecimal(453534);
        for (BigDecimal price : seats.values()) {

            if (price.compareTo(cheapestSeat) == -1
                    ) {
                cheapestSeat = price;
            }
        }
        return cheapestSeat;
    }

}
