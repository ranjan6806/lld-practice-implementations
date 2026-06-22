package elevator.model;

import elevator.enums.Direction;
import elevator.enums.ElevatorState;
import elevator.observer.ElevatorObserver;

public class CabinDisplay implements ElevatorObserver {
    private final int elevatorId;

    private int currentFloor;
    private Direction direction;
    private ElevatorState state;

    public CabinDisplay(int elevatorId) {
        this.elevatorId = elevatorId;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
    }

    public void onElevatorStatusChanged(ElevatorStatus status) {
        this.currentFloor = status.getCurrentFloor();
        this.direction = status.getDirection();
        this.state = status.getState();
    }

    public void show() {
        System.out.printf("Elevator=%d Floor=%d Direction=%s State=%s\n", elevatorId, currentFloor, direction, state);
    }
}
