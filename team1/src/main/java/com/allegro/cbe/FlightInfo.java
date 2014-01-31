package com.allegro.cbe;

import com.google.common.base.Preconditions;

import java.util.Objects;

/**
 * @author Rafal Glowinski
 * @since 31.01.14
 */
public class FlightInfo {

    private final String flightNumber;
    private final String date;

    public FlightInfo(final String flightNumber) {
        this.flightNumber = flightNumber;
        this.date = "";
    }

    public FlightInfo(final String flightNumber, final String date) {
        Preconditions.checkArgument(flightNumber != null, "Flight number cannot be null.");
        Preconditions.checkArgument(date != null, "Flight date cannot be null.");

        this.flightNumber = flightNumber;
        this.date = date;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightNumber, date);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final FlightInfo other = (FlightInfo) obj;
        return Objects.equals(this.flightNumber, other.flightNumber)
                && Objects.equals(this.date, other.date);
    }
}
