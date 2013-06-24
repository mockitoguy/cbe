import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * @author: cameleeck
 * Date: 24.06.13
 * Time: 11:07
 */
public class Flight {
    private String flightNumber;
    private String destination;
    private String origin;
    private Date flightDate;
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

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (!destination.equals(flight.destination)) return false;
        if (flightDate != null ? !flightDate.equals(flight.flightDate) : flight.flightDate != null) return false;
        if (flightNumber != null ? !flightNumber.equals(flight.flightNumber) : flight.flightNumber != null)
            return false;
        if (!origin.equals(flight.origin)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = flightNumber != null ? flightNumber.hashCode() : 0;
        result = 31 * result + destination.hashCode();
        result = 31 * result + origin.hashCode();
        result = 31 * result + (flightDate != null ? flightDate.hashCode() : 0);
        return result;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }
}
