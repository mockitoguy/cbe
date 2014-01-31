import spock.lang.Specification

class FlightManagerSpec extends Specification {

    def "should find cheapeset seat price for a given flight"() {
        given:
        FlightManager flightManager = new FlightManager()
        def flight = new Flight("LOT-123")
        def cheapestSeat = new Seat(2,1)
        flight.addSeat(cheapestSeat)
        flight.addSeat(new Seat(10,2))
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
                seats = [new Seat(1, 1), new Seat(2,2), new Seat(3,3)]
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

    def "should return list of flights between a given origin and destination"() {
        given:
        Flight flight1 = new FlightBuilder("LOT-123").from("WAW").to("AMS").on("2014-02-04").build();
        Flight flight2 = new FlightBuilder("LOT-124").from("WAW").to("AMS").on("2014-02-05").build();
        Flight flight3 = new FlightBuilder("LOT-125").from("XXX").to("YYY").on("2014-02-04").build();

        FlightManager flightManager = new FlightManager()
        flightManager.addFlight(flight1)
        flightManager.addFlight(flight2)
        flightManager.addFlight(flight3)

        when:
        def foundFlights = flightManager.findFlightsBetween("WAW", "AMS");

        then:
        foundFlights.each {
            assert it.origin == "WAW"
            assert it.destination == "AMS"
            assert it.date
        }
    }

    def "should return list of flights from a given origin"() {
        given:
        Flight flight1 = new FlightBuilder("LOT-123").from("WAW").build()
        Flight flight2 = new FlightBuilder("LOT-345").from("WAW").build()
        Flight flight3 = new FlightBuilder("KLM-999").from("AMS").build()

        FlightManager flightManager = new FlightManager()
        flightManager.addFlight(flight1)
        flightManager.addFlight(flight2)
        flightManager.addFlight(flight3)

        when:
        Iterable<Flight> foundFlights = flightManager.findFlightsFrom("WAW")

        then:
        foundFlights.contains(flight1)
        foundFlights.contains(flight2)

    }

}
