package org.ridewise.strategy.matching;

import org.ridewise.model.Driver;
import org.ridewise.model.Rider;

import java.util.Comparator;
import java.util.List;

public class NearestDriverStrategy implements RideMatchingStrategy {
    @Override
    public Driver findDriver(Rider rider, List<Driver> drivers) {
        return drivers.stream()
                .filter(Driver::isAvailable)
                .min(Comparator.comparingDouble(driver ->
                        driver.getCurrentLocation().distanceTo(rider.getLocation())))
                .orElse(null);
    }
}
