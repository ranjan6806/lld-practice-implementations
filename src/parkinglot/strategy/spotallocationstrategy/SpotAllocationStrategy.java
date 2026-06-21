package parkinglot.strategy.spotallocationstrategy;

import parkinglot.model.ParkingFloor;
import parkinglot.model.ParkingSpot;
import parkinglot.model.Vehicle;

import java.util.List;

public interface SpotAllocationStrategy {
    ParkingSpot findSpot(Vehicle vehicle, List<ParkingFloor> floors);
}
