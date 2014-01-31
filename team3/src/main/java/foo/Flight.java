package foo;

import java.util.Date;

/**
 * Created by pawel.zawistowski on 31.01.14.
 */
public class Flight {
    private final String from;
    private final String to;
    private final float[] seatPrices;
    private final Date date;
    private final String flightNumber;
    private int bookingCounter = 0;

    Flight(String flightNumber, String from, String to, Date date, float... prices) {
        this.flightNumber = flightNumber;
        this.from = from;
        this.to = to;
        this.date = date;
        this.seatPrices = prices;
    }

    public static FlightBuilder withSeatPrices(float... searPrices) {
        return new FlightBuilder().withPrices(searPrices);
    }

    public static FlightBuilder from(String origin) {
        return new FlightBuilder().from(origin);
    }

    public static FlightBuilder to(String destination) {
        return new FlightBuilder().to(destination);
    }

    public static FlightBuilder withNumber(String flightNumber) {
        return new FlightBuilder().withNumber(flightNumber);
    }

    public Date getDate() {
        return date;
    }

    public int getAvailableSeats() {
        return seatPrices.length - bookingCounter;
    }

    public float getCheapestSeat() {
        float min = Float.MAX_VALUE;
        for (float s : seatPrices) {
            min = Math.min(s, min);
        }
        return min;
    }

    public int bookSeat() {
        if (bookingCounter < seatPrices.length)
            return ++bookingCounter;
        else
            throw new NoSeatsAvailableException();
    }

    public float getAverageFreeSeatPrice() {
        float avg = 0.0f;

        for (int i = bookingCounter; i < seatPrices.length; i++) {
            avg += seatPrices[i];
        }

        return avg / (seatPrices.length - bookingCounter);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getNumber() {
        return flightNumber;
    }
}
