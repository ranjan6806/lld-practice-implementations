package parkinglot.strategy.spotallocationstrategy;

import parkinglot.model.ParkingFloor;
import parkinglot.model.ParkingSpot;
import parkinglot.model.Vehicle;

import java.util.List;

public class BestFitStrategy implements SpotAllocationStrategy {
    public ParkingSpot findSpot(Vehicle vehicle, List<ParkingFloor> floors) {
        ParkingSpot bestSpot = null;

        for (ParkingFloor floor : floors) {
            for (ParkingSpot spot : floor.getSpots()) {
                if (!spot.canFitVehicle(vehicle)) {
                    continue;
                }

                if (bestSpot == null) {
                    bestSpot = spot;
                    continue;
                }

                if (spot.getSize().ordinal() < bestSpot.getSize().ordinal()) {
                    bestSpot = spot;
                }
            }
        }

        return bestSpot;
    }
}
