package org.ridewise.service;

import org.ridewise.model.Driver;
import org.ridewise.model.Location;
import org.ridewise.model.VehicleType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DriverService {
    private final List<Driver> drivers = new ArrayList<>();
    private int nextDriverId = 1;

    public Driver registerDriver(String name, Location currentLocation, VehicleType vehicleType) {
        Driver driver = new Driver(nextDriverId++, name, currentLocation, vehicleType);
        drivers.add(driver);
        return driver;
    }

    public Optional<Driver> getDriverById(int id) {
        return drivers.stream()
                .filter(driver -> driver.getId() == id)
                .findFirst();
    }

    public void updateAvailability(int driverId, boolean available) {
        Driver driver = getDriverById(driverId)
                .orElseThrow(() -> new IllegalArgumentException("Driver not found."));
        driver.setAvailable(available);
    }

    public List<Driver> getAvailableDrivers() {
        return drivers.stream()
                .filter(Driver::isAvailable)
                .toList();
    }

    public List<Driver> getAllDrivers() {
        return List.copyOf(drivers);
    }
}
