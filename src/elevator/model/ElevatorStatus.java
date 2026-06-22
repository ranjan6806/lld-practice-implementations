package elevator.model;

import elevator.enums.Direction;
import elevator.enums.ElevatorState;

public class ElevatorStatus {
    private final int elevatorId;
    private final int currentFloor;
    private final Direction direction;
    private final ElevatorState state;

    public ElevatorStatus(int elevatorId, int currentFloor, Direction direction, ElevatorState state) {
        this.elevatorId = elevatorId;
        this.currentFloor = currentFloor;
        this.direction = direction;
        this.state = state;
    }

    public int getElevatorId() {
        return elevatorId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }
}
