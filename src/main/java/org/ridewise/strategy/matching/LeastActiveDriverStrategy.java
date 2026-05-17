package org.ridewise.strategy.matching;

import org.ridewise.model.Driver;
import org.ridewise.model.Rider;

import java.util.Comparator;
import java.util.List;

public class LeastActiveDriverStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        return drivers.stream()
                .filter(Driver::isAvailable)
                .min(Comparator
                        .comparingInt(Driver::getCompletedRideCount)
                        .thenComparingDouble(driver ->
                                driver.getCurrentLocation().distanceTo(rider.getLocation())))
                .orElse(null);
    }
}
