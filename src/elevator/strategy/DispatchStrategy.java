package elevator.strategy;

import elevator.enums.Direction;
import elevator.model.Elevator;

import java.util.List;

public interface DispatchStrategy {
    Elevator selectElevator(List<Elevator> elevators, int floor, Direction direction);
}
