package elevator.system;

import elevator.controller.ElevatorController;
import elevator.enums.Direction;
import elevator.model.Elevator;
import elevator.model.Floor;
import elevator.strategy.DispatchStrategy;

import java.util.ArrayList;
import java.util.List;

public class ElevatorSystem {
    private final List<Elevator> elevators;
    private final List<ElevatorController> controllers;
    private final List<Floor> floors;
    private final DispatchStrategy dispatchStrategy;

    public ElevatorSystem(int elevatorCount, int totalFloors, DispatchStrategy dispatchStrategy) {
        this.dispatchStrategy = dispatchStrategy;
        this.elevators = new ArrayList<>();
        this.controllers = new ArrayList<>();
        this.floors = new ArrayList<>();

        initializeFloors(totalFloors);
        initializeElevators(elevatorCount);
        registerFloorDisplays();
    }

    private void initializeFloors(int totalFloors) {
        for (int floor = 1; floor <= totalFloors; floor++) {
            floors.add(new Floor(floor));
        }
    }

    private void initializeElevators(int elevatorCount) {
        for (int id = 1; id <= elevatorCount; id++) {
            Elevator elevator = new Elevator(id);
            ElevatorController controller = new ElevatorController(elevator);
            elevators.add(elevator);
            controllers.add(controller);

            Thread thread = new Thread(controller, "Elevator - " + id);
            thread.start();
        }
    }

    private void registerFloorDisplays() {
        for (Floor floor : floors) {
            for (Elevator elevator : elevators) {
                elevator.addObserver(floor.getDisplay());
            }
        }
    }

    public void requestElevator(int floor, Direction direction) {
        Elevator elevator = dispatchStrategy.selectElevator(elevators, floor, direction);
        elevator.addExternalRequest(floor, direction);
    }

    public void selectFloor(int elevatorId, int destinationFloor) {
        Elevator elevator = elevators.stream().filter(e -> e.getId() == elevatorId)
                .findFirst()
                .orElseThrow();

        elevator.addInternalRequest(destinationFloor);
    }

    public void shutdown() {
        for (ElevatorController controller : controllers) {
            controller.stop();
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}
