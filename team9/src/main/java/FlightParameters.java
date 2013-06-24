import java.util.ArrayList;
import java.util.List;

public class FlightParameters {
    private List<SeatParameters> mSeats;

    public FlightParameters(int seatsCount) {
        mSeats = new ArrayList<SeatParameters>(seatsCount);
        initEmptySeats(mSeats, seatsCount);
    }

    public FlightParameters() {
        mSeats = new ArrayList<SeatParameters>();
    }

    public void addSeat(SeatParameters seatParameters) {
        mSeats.add(seatParameters);
    }

    public int getSeatsCount() {
        int available = 0;

        for (SeatParameters seatParameters : mSeats) {
            if (!seatParameters.isIsReserved()) {
                available++;
            }
        }
        return available;
    }

    private void initEmptySeats(List<SeatParameters> seatsList, int seatsCount) {
        for (int i = 0; i < seatsCount; i++) {
            seatsList.add(new SeatParameters(0));
        }
    }

    public int getCheapestFreeSeat() throws NoFreeSeatsException {
        int cheapestPrice = Integer.MAX_VALUE;
        boolean isAnySeatAvailable = false;

        for (SeatParameters seatParameters : mSeats) {
            if (!seatParameters.isIsReserved()) {
                if (seatParameters.getPrice() < cheapestPrice) {
                    cheapestPrice = seatParameters.getPrice();
                    isAnySeatAvailable = true;
                }
            }
        }

        if (!isAnySeatAvailable) {
            throw new NoFreeSeatsException();
        }

        return  cheapestPrice;
    }

    public boolean reserveSeat(int seatNumber) throws SeatNotFoundException {
        if (seatNumber >= mSeats.size() || seatNumber < 0) {
            throw new SeatNotFoundException();
        }

        SeatParameters seatParameters = mSeats.get(seatNumber);

        if (seatParameters.isIsReserved()) {
            return false;
        } else {
            seatParameters.setIsReserved(true);
            return true;
        }
    }

    public int getAvgPrice() throws NoFreeSeatsException {
        int avgPrice = 0;
        boolean isAnySeatAvailable = false;

        for (SeatParameters seatParameters : mSeats) {
            if (!seatParameters.isIsReserved()) {
                avgPrice += seatParameters.getPrice();
                isAnySeatAvailable = true;
            }
        }

        if (!isAnySeatAvailable) {
            throw new NoFreeSeatsException();
        }

        return avgPrice/mSeats.size();
    }
}
