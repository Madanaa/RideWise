package org.ridewise.service;

import org.ridewise.model.Location;
import org.ridewise.model.Rider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RiderService {
    private final List<Rider> riders = new ArrayList<>();
    private int nextRiderId = 1;

    public Rider registerRider(String name, Location location) {
        Rider rider = new Rider(nextRiderId++, name, location);
        riders.add(rider);
        return rider;
    }

    public Optional<Rider> getRiderById(int id) {
        return riders.stream()
                .filter(rider -> rider.getId() == id)
                .findFirst();
    }

    public List<Rider> getAllRiders() {
        return List.copyOf(riders);
    }
}
