package elevator;

import elevator.enums.Direction;
import elevator.model.Elevator;
import elevator.model.Floor;
import elevator.strategy.DispatchStrategy;
import elevator.strategy.NearestElevatorStrategy;
import elevator.system.ElevatorSystem;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        DispatchStrategy strategy = new NearestElevatorStrategy();
        ElevatorSystem elevatorSystem = new ElevatorSystem(3, 20, strategy);

        elevatorSystem.requestElevator(5, Direction.UP);
        Thread.sleep(2000);

        System.out.println("\n===== CABIN DISPLAYS =====");
        for (Elevator elevator : elevatorSystem.getElevators()) {
            elevator.getCabinDisplay().show();
        }

        System.out.println("\n===== FLOOR DISPLAYS =====");

        for (Floor floor : elevatorSystem.getFloors()) {
            floor.getDisplay().show();
        }

        elevatorSystem.selectFloor(1, 15);
        Thread.sleep(5000);


        System.out.println("\n===== AFTER INTERNAL REQUEST =====");

        for (Elevator elevator : elevatorSystem.getElevators()) {
            elevator.getCabinDisplay().show();
        }

        elevatorSystem.requestElevator(12, Direction.DOWN);
        Thread.sleep(5000);

        elevatorSystem.shutdown();
    }
}
