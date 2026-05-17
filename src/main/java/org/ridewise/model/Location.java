package org.ridewise.model;

public class Location {
    private final double x;
    private final double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Location other) {
        double xDifference = x - other.x;
        double yDifference = y - other.y;
        return Math.sqrt((xDifference * xDifference) + (yDifference * yDifference));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
