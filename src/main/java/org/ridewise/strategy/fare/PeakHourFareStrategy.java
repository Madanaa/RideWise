package org.ridewise.strategy.fare;

import org.ridewise.model.Ride;

import java.time.LocalTime;

public class PeakHourFareStrategy extends DefaultFareStrategy {
    private static final double PEAK_MULTIPLIER = 1.5;

    @Override
    public double calculateFare(Ride ride) {
        double fare = super.calculateFare(ride);
        return isPeakHour(LocalTime.now()) ? fare * PEAK_MULTIPLIER : fare;
    }

    private boolean isPeakHour(LocalTime time) {
        int hour = time.getHour();
        return (hour >= 8 && hour < 10) || (hour >= 18 && hour < 21);
    }
}
