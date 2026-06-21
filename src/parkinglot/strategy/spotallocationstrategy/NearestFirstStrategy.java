package parkinglot.strategy.spotallocationstrategy;

import parkinglot.model.ParkingFloor;
import parkinglot.model.ParkingSpot;
import parkinglot.model.Vehicle;

import java.util.List;

public class NearestFirstStrategy implements SpotAllocationStrategy {
    public ParkingSpot findSpot(Vehicle vehicle, List<ParkingFloor> floors) {
        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpots()) {
                if (spot.canFitVehicle(vehicle)) {
                    return spot;
                }
            }
        }

        return null;
    }
}
