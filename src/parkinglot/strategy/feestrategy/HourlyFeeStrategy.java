package parkinglot.strategy.feestrategy;

import parkinglot.model.ParkingTicket;

import java.time.Duration;

public class HourlyFeeStrategy implements FeeStrategy {
    private final double hourlyRate;

    public HourlyFeeStrategy(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public double calculateFee(ParkingTicket ticket) {
        Duration duration = ticket.getDuration();
        long hours = Math.max(1, (long) Math.ceil(duration.toMinutes() / 60.0));

        return hours * hourlyRate;
    }

}
