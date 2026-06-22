package elevator.model;

import elevator.enums.Direction;

public class Display {
    private final int elevatorId;
    private int currentFloor;
    private Direction currentDirection;

    public Display(int elevatorId) {
        this.elevatorId = elevatorId;
        this.currentDirection = Direction.IDLE;
    }

    public void onElevatorStateChanged(int floor, Direction direction) {
        this.currentFloor = floor;
        this.currentDirection = direction;
    }
}
