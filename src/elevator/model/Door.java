package elevator.model;

import elevator.enums.DoorState;

public class Door {
    private DoorState state;

    public Door() {
        state = DoorState.OPEN;
    }

    public void open() {
        state = DoorState.OPEN;
    }

    public void close() {
        state = DoorState.CLOSED;
    }

    public boolean isOpen() {
        return state == DoorState.OPEN;
    }

    public DoorState getState() {
        return state;
    }
}
