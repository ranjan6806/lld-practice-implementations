package parkinglot.service;

import parkinglot.model.ParkingLot;
import parkinglot.model.ParkingSpot;
import parkinglot.model.ParkingTicket;
import parkinglot.model.Vehicle;
import parkinglot.strategy.feestrategy.FeeStrategy;
import parkinglot.strategy.spotallocationstrategy.SpotAllocationStrategy;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ParkingService {
    private final ParkingLot parkingLot;
    private final FeeStrategy feeStrategy;
    private final SpotAllocationStrategy spotAllocationStrategy;

    private Map<String, ParkingTicket> activeTickets;

    public ParkingService(ParkingLot parkingLot, FeeStrategy feeStrategy, SpotAllocationStrategy spotAllocationStrategy) {
        this.parkingLot = parkingLot;
        this.feeStrategy = feeStrategy;
        this.spotAllocationStrategy = spotAllocationStrategy;
        activeTickets = new ConcurrentHashMap<>();
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = spotAllocationStrategy.findSpot(vehicle, parkingLot.getFloors());
        if (spot == null) {
            throw new RuntimeException("No parking spot available");
        }

        boolean parked = spot.parkVehicle(vehicle);
        if (!parked) {
            throw new RuntimeException("Failed to park vehicle");
        }

        ParkingTicket ticket = new ParkingTicket(UUID.randomUUID().toString(),
                vehicle, spot, LocalDateTime.now());

        activeTickets.put(ticket.getTicketId(), ticket);
        return ticket;
    }

    public double unparkVehicle(String ticketId) {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket == null) {
            throw new RuntimeException("Invalid ticket");
        }

        ticket.setExitTime(LocalDateTime.now());
        ticket.getSpot().unparkVehicle();
        double fee = feeStrategy.calculateFee(ticket);
        activeTickets.remove(ticketId);
        return fee;
    }

    public void displayAvailability() {
        parkingLot.displayAvailability();
    }
}
