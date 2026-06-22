package elevator.model;

import elevator.enums.Direction;
import elevator.enums.ElevatorState;
import elevator.enums.RequestType;
import elevator.observer.ElevatorObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Elevator {
    private final int id;
    private final Door door;
    private final CabinDisplay cabinDisplay;
    private final PriorityQueue<Request> upRequests;
    private final PriorityQueue<Request> downRequests;
    private final List<ElevatorObserver> observers;

    private int currentFloor;
    private Direction direction;
    private ElevatorState state;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 0;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.door = new Door();

        this.cabinDisplay = new CabinDisplay(id);
        this.upRequests = new PriorityQueue<>((r1, r2) -> Integer.compare(r1.getFloor(), r2.getFloor()));
        this.downRequests = new PriorityQueue<>((r1, r2) -> Integer.compare(r2.getFloor(), r1.getFloor()));

        this.observers = new ArrayList<>();
        addObserver(cabinDisplay);
    }

    public void addExternalRequest(int floor, Direction direction) {
        Request request = new Request(RequestType.EXTERNAL, direction, floor);
        if (direction == Direction.UP) {
            upRequests.offer(request);
        } else {
            downRequests.offer(request);
        }
    }

    public void addInternalRequest(int destinationFloor) {
        Direction requestDirection = destinationFloor > currentFloor ? Direction.UP : Direction.DOWN;
        Request request = new Request(RequestType.INTERNAL, requestDirection, destinationFloor);
        if (requestDirection == Direction.UP) {
            upRequests.offer(request);
        } else {
            downRequests.offer(request);
        }
    }

    public Request getNextUpStop() {
        return upRequests.peek();
    }

    public Request getNextDownStop() {
        return downRequests.peek();
    }

    public Request pollNextUpStop() {
        return upRequests.poll();
    }

    public Request pollNextDownStop() {
        return downRequests.poll();
    }

    public boolean hasRequests() {
        return !upRequests.isEmpty() || !downRequests.isEmpty();
    }

    public void notifyObserver() {
        ElevatorStatus status = getStatus();
        for (ElevatorObserver observer : observers) {
            observer.onElevatorStatusChanged(status);
        }
    }

    public ElevatorStatus getStatus() {
        return new ElevatorStatus(id, currentFloor, direction, state);
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    public int getId() {
        return id;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public Door getDoor() {
        return door;
    }

    public void openDoor() {
        door.open();
    }

    public void closeDoor() {
        door.close();
    }

    public CabinDisplay getCabinDisplay() {
        return cabinDisplay;
    }
}
