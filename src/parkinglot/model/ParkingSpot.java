package parkinglot.model;

public class ParkingSpot {
    private final String spotId;
    private final VehicleSize size;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, VehicleSize size) {
        this.spotId = spotId;
        this.size = size;
    }

    public String getSpotId() {
        return spotId;
    }

    public VehicleSize getSize() {
        return size;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public boolean isAvailable() {
        return parkedVehicle == null;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return isAvailable() && size == vehicle.getSize();
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (!canFitVehicle(vehicle)) {
            return false;
        }

        parkedVehicle = vehicle;
        return true;
    }

    public Vehicle unparkVehicle() {
        Vehicle vehicle = parkedVehicle;
        parkedVehicle = null;
        return vehicle;
    }
}
