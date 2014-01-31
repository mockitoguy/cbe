package com.allegro.cbe;

import com.google.common.base.Function;

public class FlightToFlightInfoTranfsorm implements Function<Flight, FlightInfo> {

    @Override
    public FlightInfo apply(Flight flight) {
        return new FlightInfo(flight.getFlightNumber(), flight.getDate());
    }
}
