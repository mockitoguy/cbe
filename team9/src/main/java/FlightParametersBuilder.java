/**
 * Created with IntelliJ IDEA.
 * User: rafal.gurzkowski
 * Date: 24.06.13
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class FlightParametersBuilder {

    private Flight mFlight;

    public FlightParametersBuilder() {
        mFlight = new Flight();
    }

    public FlightParametersBuilder addSeat(int price) {
        mFlight.addSeat(new SeatParameters(price));
        return this;
    }

    public FlightParametersBuilder addSeat(int price, boolean reserved) {
        SeatParameters seatParameters = new SeatParameters(price);
        seatParameters.setIsReserved(reserved);
        mFlight.addSeat(seatParameters);

        return this;
    }

    public Flight build() {
        return mFlight;
    }
}
