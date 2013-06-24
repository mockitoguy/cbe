package pl.tdd.service;

import java.util.Comparator;

/**
 * User: pcierpiatka
 */
public class SeatPriceComparator implements Comparator<Seat> {

    @Override
    public int compare(Seat seat1, Seat seat2) {
        return (int)(seat1.getPrice() - seat2.getPrice());
    }
}
