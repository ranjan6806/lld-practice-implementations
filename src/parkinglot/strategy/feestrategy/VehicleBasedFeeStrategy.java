package parkinglot.strategy.feestrategy;

import parkinglot.model.ParkingTicket;
import parkinglot.model.VehicleType;

import java.time.Duration;
import java.util.Map;

public class VehicleBasedFeeStrategy implements FeeStrategy {
    private final Map<VehicleType, Double> hourlyRates;

    public VehicleBasedFeeStrategy(Map<VehicleType, Double> hourlyRates) {
        this.hourlyRates = hourlyRates;
    }

    public double calculateFee(ParkingTicket ticket) {
        Duration duration = ticket.getDuration();
        long hours = Math.max(1, (long) Math.ceil(duration.toMinutes() / 60.0));

        VehicleType vehicleType = ticket.getVehicle().getType();
        double rate = hourlyRates.get(vehicleType);
        return hours * rate;
    }
}
