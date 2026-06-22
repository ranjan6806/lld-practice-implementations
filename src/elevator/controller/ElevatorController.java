package elevator.controller;

import elevator.enums.Direction;
import elevator.enums.ElevatorState;
import elevator.model.Elevator;
import elevator.model.Request;

public class ElevatorController implements Runnable {
    private final Elevator elevator;
    private volatile boolean running;

    public ElevatorController(Elevator elevator) {
        this.elevator = elevator;
        this.running = true;
    }

    public void run() {
        while (running) {
            processRequests();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void processRequests() {
        if (!elevator.hasRequests()) {
            elevator.setDirection(Direction.IDLE);
            elevator.setState(ElevatorState.IDLE);
            return;
        }

        if (elevator.getDirection() == Direction.IDLE) {
            if (elevator.getNextUpStop() != null) {
                elevator.setDirection(Direction.UP);
            } else {
                elevator.setDirection(Direction.DOWN);
            }
        }

        if (elevator.getDirection() == Direction.UP) {
            moveUp();
        } else {
            moveDown();
        }
    }

    private void moveDown() {
        Request request = elevator.pollNextDownStop();
        // no more request
        if (request == null) {
            // not on ground floor then move to ground floor
            if (elevator.getNextDownStop() != null) {
                elevator.setDirection(Direction.DOWN);
            } else {
                // already on ground floor
                elevator.setDirection(Direction.IDLE);
            }
            return;
        }

        elevator.setState(ElevatorState.MOVING_DOWN);
        serveFloor(request.getFloor());

        if (elevator.getNextDownStop() == null && elevator.getNextUpStop() != null) {
            elevator.setDirection(Direction.UP);
        }
    }

    private void moveUp() {
        Request request = elevator.pollNextUpStop();

        // no more request
        if (request == null) {
            // not on ground floor then move to ground floor
            if (elevator.getNextDownStop() != null) {
                elevator.setDirection(Direction.DOWN);
            } else {
                // already on ground floor
                elevator.setDirection(Direction.IDLE);
            }
            return;
        }

        elevator.setState(ElevatorState.MOVING_UP);
        serveFloor(request.getFloor());

        if (elevator.getNextUpStop() == null && elevator.getNextDownStop() != null) {
            elevator.setDirection(Direction.DOWN);
        }
    }

    private void serveFloor(int floor) {
        elevator.setCurrentFloor(floor);
        elevator.notifyObserver();
        elevator.openDoor();
        elevator.setState(ElevatorState.DOOR_OPEN);
        elevator.notifyObserver();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        elevator.closeDoor();
    }

    public void stop() {
        running = false;
    }
}
