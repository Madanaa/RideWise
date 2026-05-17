package org.ridewise;

import org.ridewise.model.Driver;
import org.ridewise.model.FareReceipt;
import org.ridewise.model.Location;
import org.ridewise.model.Ride;
import org.ridewise.model.Rider;
import org.ridewise.model.VehicleType;
import org.ridewise.service.DriverService;
import org.ridewise.service.RideService;
import org.ridewise.service.RiderService;
import org.ridewise.strategy.fare.DefaultFareStrategy;
import org.ridewise.strategy.matching.NearestDriverStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final RiderService riderService = new RiderService();
    private final DriverService driverService = new DriverService();
    private final RideService rideService = new RideService(
            riderService,
            driverService,
            new NearestDriverStrategy(),
            new DefaultFareStrategy()
    );

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");
            try {
                switch (choice) {
                    case 1 -> addRider();
                    case 2 -> addDriver();
                    case 3 -> viewAvailableDrivers();
                    case 4 -> requestRide();
                    case 5 -> completeRide();
                    case 6 -> viewRides();
                    case 7 -> running = false;
                    default -> System.out.println("Invalid option. Try again.");
                }
            } catch (IllegalArgumentException | IllegalStateException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("RideWise - Modular Ride-Sharing System");
        System.out.println("1. Add Rider");
        System.out.println("2. Add Driver");
        System.out.println("3. View Available Drivers");
        System.out.println("4. Request Ride");
        System.out.println("5. Complete Ride");
        System.out.println("6. View Rides");
        System.out.println("7. Exit");
    }

    private void addRider() {
        String name = readText("Rider name: ");
        Location location = readLocation("Rider location");
        Rider rider = riderService.registerRider(name, location);
        System.out.println("Registered rider: " + rider);
    }

    private void addDriver() {
        String name = readText("Driver name: ");
        Location location = readLocation("Driver location");
        VehicleType vehicleType = readVehicleType();
        Driver driver = driverService.registerDriver(name, location, vehicleType);
        System.out.println("Registered driver: " + driver);
    }

    private void viewAvailableDrivers() {
        List<Driver> drivers = driverService.getAvailableDrivers();
        if (drivers.isEmpty()) {
            System.out.println("No available drivers.");
            return;
        }
        drivers.forEach(System.out::println);
    }

    private void requestRide() {
        int riderId = readInt("Rider id: ");
        double distance = readDouble("Distance in km: ");
        Ride ride = rideService.requestRide(riderId, distance);
        System.out.println("Ride assigned: " + ride);
    }

    private void completeRide() {
        int rideId = readInt("Ride id: ");
        FareReceipt receipt = rideService.completeRide(rideId);
        System.out.println("Ride completed. Fare receipt: " + receipt);
    }

    private void viewRides() {
        List<Ride> rides = rideService.getAllRides();
        if (rides.isEmpty()) {
            System.out.println("No rides found.");
            return;
        }
        rides.forEach(System.out::println);
    }

    private VehicleType readVehicleType() {
        while (true) {
            String input = readText("Vehicle type (BIKE, AUTO, CAR): ");
            try {
                return VehicleType.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid vehicle type.");
            }
        }
    }

    private Location readLocation(String label) {
        double x = readDouble(label + " x-coordinate: ");
        double y = readDouble(label + " y-coordinate: ");
        return new Location(x, y);
    }

    private String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isBlank()) {
                return input;
            }
            System.out.println("Input cannot be empty.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readText(prompt));
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                double value = Double.parseDouble(readText(prompt));
                if (value >= 0) {
                    return value;
                }
                System.out.println("Enter a non-negative number.");
            } catch (NumberFormatException exception) {
                System.out.println("Enter a valid number.");
            }
        }
    }
}
