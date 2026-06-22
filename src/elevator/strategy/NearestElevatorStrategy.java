package elevator.strategy;

import elevator.enums.Direction;
import elevator.enums.ElevatorState;
import elevator.model.Elevator;

import java.util.List;

public class NearestElevatorStrategy implements DispatchStrategy {
    public Elevator selectElevator(List<Elevator> elevators, int floor, Direction requestDirection) {
        Elevator bestElevator = null;
        int bestScore = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.getState() == ElevatorState.OUT_OF_SERVICE) {
                continue;
            }

            int score = calculateScore(elevator, floor, requestDirection);
            if (score < bestScore) {
                bestElevator = elevator;
                bestScore = score;
            }
        }

        return bestElevator;
    }

    public int calculateScore(Elevator elevator, int requestedFloor, Direction requestDirection) {
        int distance = Math.abs(elevator.getCurrentFloor() - requestedFloor);
        if (elevator.getDirection() == Direction.IDLE) {
            return distance;
        }

        boolean movingTowardsRequest = elevator.getDirection() == requestDirection && ((requestDirection == Direction.UP && elevator.getCurrentFloor() <= requestedFloor)
                || (requestDirection == Direction.DOWN && elevator.getCurrentFloor() >= requestedFloor));

        if (movingTowardsRequest) {
            return distance;
        }

        return distance + 1000;
    }
}
