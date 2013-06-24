/**
 * Created with IntelliJ IDEA.
 * User: rafal.gurzkowski
 * Date: 24.06.13
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class FlightParametersBuilder {

    private FlightParameters mFlightParameters;

    public FlightParametersBuilder() {
        mFlightParameters = new FlightParameters();
    }

    public FlightParametersBuilder addSeat(int price) {
        mFlightParameters.addSeat(new SeatParameters(price));
        return this;
    }

    public FlightParametersBuilder addSeat(int price, boolean reserved) {
        SeatParameters seatParameters = new SeatParameters(price);
        seatParameters.setIsReserved(reserved);
        mFlightParameters.addSeat(seatParameters);

        return this;
    }

    public FlightParameters build() {
        return mFlightParameters;
    }
}
