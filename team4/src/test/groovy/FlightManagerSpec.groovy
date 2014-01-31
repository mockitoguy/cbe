import spock.lang.Specification

class FlightManagerSpec extends Specification {

    def "should find cheapeset seat price for a given flight"() {
        given:
        FlightManager flightManager = new FlightManager()
        def flight = new Flight("LOT-123")
        def cheapestSeat = new Seat(2)
        flight.addSeat(cheapestSeat)
        flight.addSeat(new Seat(10))
        flightManager.addFlight(flight)

        when:
        def foundSeatPrice = flightManager.findCheapestSeatPrice("LOT-123")

        then:
        foundSeatPrice == 2
    }

    def "should know available seats for flight"() {
        given:
        FlightManager flightManager = new FlightManager().with {
            it.flights = ['LOT-123': new Flight().with {
                seats = [new Seat(1), new Seat(2), new Seat(3)]
                it
            }]
            it
        }

        when:
        int availableSeats = flightManager.getAvailableSeats('LOT-123')

        then:
        availableSeats == 3
    }

    def "should throw flight not found exception"() {
        given:
        FlightManager flightManager = new FlightManager()

        when:
        flightManager.getAvailableSeats("nonExistingFlight")

        then:
        thrown FlighNotFoundException
    }

}
