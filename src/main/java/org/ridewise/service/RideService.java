package org.ridewise.service;

import org.ridewise.model.Driver;
import org.ridewise.model.FareReceipt;
import org.ridewise.model.Ride;
import org.ridewise.model.RideStatus;
import org.ridewise.model.Rider;
import org.ridewise.strategy.fare.FareStrategy;
import org.ridewise.strategy.matching.RideMatchingStrategy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RideService {
    private final RiderService riderService;
    private final DriverService driverService;
    private final RideMatchingStrategy rideMatchingStrategy;
    private final FareStrategy fareStrategy;
    private final List<Ride> rides = new ArrayList<>();
    private int nextRideId = 1;

    public RideService(
            RiderService riderService,
            DriverService driverService,
            RideMatchingStrategy rideMatchingStrategy,
            FareStrategy fareStrategy
    ) {
        this.riderService = riderService;
        this.driverService = driverService;
        this.rideMatchingStrategy = rideMatchingStrategy;
        this.fareStrategy = fareStrategy;
    }

    public Ride requestRide(int riderId, double distance) {
        Rider rider = riderService.getRiderById(riderId)
                .orElseThrow(() -> new IllegalArgumentException("Rider not found."));
        List<Driver> availableDrivers = driverService.getAvailableDrivers();
        Driver driver = rideMatchingStrategy.findDriver(rider, availableDrivers);
        if (driver == null) {
            throw new IllegalStateException("No available drivers.");
        }

        Ride ride = new Ride(nextRideId++, rider, distance);
        ride.assignDriver(driver);
        driver.setAvailable(false);
        rides.add(ride);
        return ride;
    }

    public FareReceipt completeRide(int rideId) {
        Ride ride = getRideById(rideId)
                .orElseThrow(() -> new IllegalArgumentException("Ride not found."));
        if (ride.getStatus() != RideStatus.ASSIGNED) {
            throw new IllegalStateException("Only assigned rides can be completed.");
        }

        ride.complete();
        Driver driver = ride.getDriver();
        driver.incrementCompletedRideCount();
        driver.setAvailable(true);

        double amount = fareStrategy.calculateFare(ride);
        return new FareReceipt(ride.getId(), amount, LocalDateTime.now());
    }

    public Optional<Ride> getRideById(int rideId) {
        return rides.stream()
                .filter(ride -> ride.getId() == rideId)
                .findFirst();
    }

    public List<Ride> getAllRides() {
        return List.copyOf(rides);
    }
}
