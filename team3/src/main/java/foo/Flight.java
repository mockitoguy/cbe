package foo;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by pawel.zawistowski on 31.01.14.
 */
public class Flight {
    private float[] seatPrices;
    private int bookingCounter = 0;

    public Flight(int availableSeats, float seatPrice) {
        this.seatPrices = new float[availableSeats];
        Arrays.fill(this.seatPrices, seatPrice);
    }

    public Flight(float... prices) {
        seatPrices = prices;
    }

    public int getAvailableSeats() {
        return seatPrices.length-bookingCounter;
    }

    public float getCheapestSeat() {
        float min = Float.MAX_VALUE;
        for (float s : seatPrices){
            min = Math.min(s,min);
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

        for (int i = bookingCounter; i < seatPrices.length; i++){
            avg += seatPrices[i];
        }

        return (float)avg/(seatPrices.length-bookingCounter);
    }
}
