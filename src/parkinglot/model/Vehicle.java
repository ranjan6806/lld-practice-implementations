package parkinglot.model;

public class Vehicle {
    private final String licensePlate;
    private final VehicleType type;
    private final VehicleSize size;

    public Vehicle(String licensePlate, VehicleType type, VehicleSize size) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.size = size;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public VehicleSize getSize() {
        return size;
    }
}
