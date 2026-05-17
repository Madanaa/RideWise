package org.ridewise.model;

public class Driver {
    private final int id;
    private final String name;
    private final VehicleType vehicleType;
    private Location currentLocation;
    private boolean available;
    private int completedRideCount;

    public Driver(int id, String name, Location currentLocation, VehicleType vehicleType) {
        this.id = id;
        this.name = name;
        this.currentLocation = currentLocation;
        this.vehicleType = vehicleType;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getCompletedRideCount() {
        return completedRideCount;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void incrementCompletedRideCount() {
        completedRideCount++;
    }

    @Override
    public String toString() {
        return "Driver{id=" + id + ", name='" + name + "', currentLocation=" + currentLocation
                + ", vehicleType=" + vehicleType + ", available=" + available
                + ", completedRideCount=" + completedRideCount + "}";
    }
}
