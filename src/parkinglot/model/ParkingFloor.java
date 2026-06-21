package parkinglot.model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ParkingFloor {
    private final int floorNumber;
    private final List<ParkingSpot> spots;

    public ParkingFloor(int floorNumber, List<ParkingSpot> spots) {
        this.floorNumber = floorNumber;
        this.spots = spots;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public Map<VehicleSize, Integer> getAvailability() {
        Map<VehicleSize, Integer> availability = new EnumMap<>(VehicleSize.class);
        for (VehicleSize size : VehicleSize.values()) {
            availability.put(size, 0);
        }

        for (ParkingSpot spot : spots) {
            if (spot.isAvailable()) {
                availability.put(spot.getSize(), availability.get(spot.getSize()) + 1);
            }
        }

        return availability;
    }
}
