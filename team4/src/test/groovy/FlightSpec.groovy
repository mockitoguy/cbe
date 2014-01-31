import spock.lang.Specification

/**
 * Created with IntelliJ IDEA.
 * User: rafal.myslek
 * Date: 31.01.2014
 * Time: 13:55
 * To change this template use File | Settings | File Templates.
 */
class FlightSpec extends Specification {

    def "should book seat on a given flight"() {
        given:
        Flight flight = new FlightBuilder("LOT-123")
                .withSeat(new Seat(1,2))
                .withSeat(new Seat(13,3))
                .build()

        when:
        def seat = flight.bookSeat(2)

        then:
        !flight.isSeatAvailable(seat.seatId)
    }

    def "should return average price of non booked seats in given flight"() {
        given:
        Flight flight = new FlightBuilder("LOT-123")
                .withSeat(10)
                .withSeat(30)
                .withBookedSeat(40)
                .build()

        when:
        def averagePrice = flight.getAveragePriceOfNonBookedSeats()

        then:
        averagePrice == 20
    }

}
