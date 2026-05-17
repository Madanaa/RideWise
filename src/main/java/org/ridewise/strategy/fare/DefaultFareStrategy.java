package org.ridewise.strategy.fare;

import org.ridewise.model.Ride;
import org.ridewise.model.VehicleType;

public class DefaultFareStrategy implements FareStrategy {
    private static final double BASE_FARE = 30.0;
    private static final double RATE_PER_KM = 12.0;

    @Override
    public double calculateFare(Ride ride) {
        return (BASE_FARE + (ride.getDistance() * RATE_PER_KM)) * vehicleMultiplier(ride);
    }

    protected double vehicleMultiplier(Ride ride) {
        VehicleType vehicleType = ride.getDriver().getVehicleType();
        return switch (vehicleType) {
            case BIKE -> 0.8;
            case AUTO -> 1.0;
            case CAR -> 1.4;
        };
    }
}
