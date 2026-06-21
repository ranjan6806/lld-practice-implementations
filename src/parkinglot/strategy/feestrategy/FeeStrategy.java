package parkinglot.strategy.feestrategy;

import parkinglot.model.ParkingTicket;

public interface FeeStrategy {
    double calculateFee(ParkingTicket ticket);
}
