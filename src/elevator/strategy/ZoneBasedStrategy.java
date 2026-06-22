package elevator.strategy;

import elevator.enums.Direction;
import elevator.model.Elevator;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ZoneBasedStrategy implements DispatchStrategy {
    private final Map<Integer, Integer> zoneAssignment;

    public ZoneBasedStrategy(int totalFloors, int numElevators) {
        zoneAssignment = new ConcurrentHashMap<>();
        int zoneSize = (int) Math.ceil((double) totalFloors / numElevators);
        int startFloor = 1;

        for (int elevatorId = 1; elevatorId <= numElevators; elevatorId++) {

            int endFloor = Math.min(totalFloors, startFloor + zoneSize - 1);
            for (int floor = startFloor; floor <= endFloor; floor++) {
                zoneAssignment.put(floor, elevatorId);
            }

            startFloor = endFloor + 1;
        }
    }

    public Elevator selectElevator(List<Elevator> elevators, int floor, Direction requestedDirection) {
        Integer elevatorId = zoneAssignment.get(floor);
        if (elevatorId == null) {
            throw new IllegalArgumentException("Invalid floor");
        }

        return elevators.stream().filter(e -> e.getId() == elevatorId)
                .findFirst()
                .orElseThrow();
    }
}
