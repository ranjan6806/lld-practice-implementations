package elevator.model;

import elevator.observer.ElevatorObserver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FloorDisplay implements ElevatorObserver {
    private final int floorNumber;
    private final Map<Integer, ElevatorStatus> elevatorStatuses;

    public FloorDisplay(int floorNumber) {
        this.floorNumber = floorNumber;
        this.elevatorStatuses = new ConcurrentHashMap<>();
    }

    public void onElevatorStatusChanged(ElevatorStatus status) {
        elevatorStatuses.put(status.getElevatorId(), status);
    }

    public void show() {
        System.out.println("Floor Display: " + floorNumber);

        elevatorStatuses.values().forEach(e -> System.out.printf("Elevator=%d Floor=%d Direction=%s%n", e.getElevatorId(), e.getCurrentFloor(), e.getDirection()));
    }
}
