package elevator.model;

public class Floor {
    private final int floorNumber;
    private final FloorDisplay display;

    private boolean upButtonPressed;
    private boolean downButtonPressed;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.display = new FloorDisplay(floorNumber);
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public FloorDisplay getDisplay() {
        return display;
    }

    public void pressUpButton() {
        upButtonPressed = true;
    }

    public void pressDownButton() {
        downButtonPressed = true;
    }

    public void resetButton() {
        upButtonPressed = false;
        downButtonPressed = false;
    }
}
