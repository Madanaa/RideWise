package org.ridewise.strategy.matching;

import org.ridewise.model.Driver;
import org.ridewise.model.Rider;

import java.util.List;

public interface RideMatchingStrategy {
    Driver findDriver(Rider rider, List<Driver> drivers);
}
