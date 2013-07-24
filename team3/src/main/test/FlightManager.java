import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FlightManager {

    public String flight;
    public Integer avaibleSeat;
    public HashMap<String, Integer> flightList = new HashMap<>();
    public double SeatPrice;
    public ArrayList<Seat> ListSeat = new ArrayList<Seat>();

    public void addFlight(String flight, int numberOfSeats) {
        try {
            if (!flightList.containsKey(flight))
                flightList.put(flight, numberOfSeats);

        } catch (Exception e) {
            // TODO: handle exception
            System.err.print("jest juz ddodany" + e.getMessage());
        }

    }

    public Integer getEvaibleSeat(String flight) {

        return flightList.get(flight);

    }

    public void addSeatPrice(String flight, String seatSymbol, double price) {
        // TODO Auto-generated method stub
        Seat seat = new Seat();
        seat.setFlight(flight);
        seat.setPrice(price);
        seat.setSeatSymbol(seatSymbol);
        ListSeat.add(seat);
    }

    public Double getCheapestSeat(String flight) {
        ArrayList<Double> priceList = new ArrayList<Double>();
        for (int i = 0; i < ListSeat.size(); i++) {
            if (ListSeat.get(i).getFlight().equals(flight)) {
                priceList.add(ListSeat.get(i).getPrice());
            }
        }
        int index = minIndex(priceList);
        return priceList.get(index);
    }

    public static int minIndex(ArrayList<Double> priceList) {
        return priceList.indexOf(Collections.min(priceList));
    }

    public boolean bookSeat(String flight, String SeatSymbol) {

        for (int j = 0; j < ListSeat.size(); j++) {

            if (ListSeat.get(j).getFlight().equals(flight)) {
                if (ListSeat.get(j).getSeatSymbol().equals(SeatSymbol)) {
                    if (ListSeat.get(j).isBook() == true) {
                        System.out.print("nie uda³o sie zabookowaæ");
                    } else {
                        ListSeat.get(j).setBook(true);
                        return true;
                    }

                }
            }

        }
        return false;

    }

    public boolean VerifiteBookSeat(String flight, String SeatSymbol) {
        Boolean book = false;
        for (int j = 0; j < ListSeat.size(); j++) {
            if (ListSeat.get(j).getFlight().equals(flight)) {
                if (ListSeat.get(j).getSeatSymbol().equals(SeatSymbol)) {

                    book = bookSeat(flight, SeatSymbol);
                }
            }
        }
        return book;

    }

    public double showAverage(String flight) {

        int count = 0;
        double sum = 0;
        for (int j = 0; j < ListSeat.size(); j++) {
            if (ListSeat.get(j).getFlight().equals(flight)) {
                if (ListSeat.get(j).isBook() == false) {

                    sum = sum + ListSeat.get(j).getPrice();
                    count = count + 1;
                }
            }
        }
        return sum / count;
    }
}
