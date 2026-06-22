package elevator.observer;

import elevator.model.ElevatorStatus;

public interface ElevatorObserver {
    void onElevatorStatusChanged(ElevatorStatus status);
}
