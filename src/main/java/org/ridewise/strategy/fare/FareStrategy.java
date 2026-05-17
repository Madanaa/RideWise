package org.ridewise.strategy.fare;

import org.ridewise.model.Ride;

public interface FareStrategy {
    double calculateFare(Ride ride);
}
