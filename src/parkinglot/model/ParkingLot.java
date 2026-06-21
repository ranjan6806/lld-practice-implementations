package parkinglot.model;

import java.util.List;

public class ParkingLot {
    private final List<ParkingFloor> floors;

    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public void displayAvailability() {
        for (ParkingFloor floor : floors) {
            System.out.println("Floor " + floor.getFloorNumber() + " -> " + floor.getAvailability());
        }
    }
}
