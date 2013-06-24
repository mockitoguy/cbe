import java.math.BigDecimal;
import java.math.RoundingMode;
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
    private Map<Integer, BigDecimal> nonBookedSeats = new HashMap<Integer, BigDecimal>();

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }


    public void addNewReservation(Reservation reservation) {
        this.listOfReservation.add(reservation);
        nonBookedSeats.remove(reservation.getSeat());

    }

    public List<Reservation> getListOfReservation() {
        return listOfReservation;
    }

    public void addSeat(int seatsNumber, BigDecimal flightPrice) {
        seats.put(seatsNumber, flightPrice);
        nonBookedSeats.put(seatsNumber, flightPrice);
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

    /**
     * znalezc miejsca ktore nie sa zarezerwowane
     * liczymy srednia
     *
     * @return
     */
    public BigDecimal getAveragePriceOfSeat() {
        BigDecimal sum = new BigDecimal(0);
        Integer count = 0;
        for (BigDecimal price : nonBookedSeats.values()) {
            sum = sum.add(price);
            count++;
        }
        BigDecimal result = sum.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);
        return result;
    }
}
