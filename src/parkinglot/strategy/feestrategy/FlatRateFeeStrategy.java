package parkinglot.strategy.feestrategy;

import parkinglot.model.ParkingTicket;

public class FlatRateFeeStrategy implements FeeStrategy {
    private final double flatFee;

    public FlatRateFeeStrategy(double flatFee) {
        this.flatFee = flatFee;
    }

    public double calculateFee(ParkingTicket ticket) {
        return flatFee;
    }
}
