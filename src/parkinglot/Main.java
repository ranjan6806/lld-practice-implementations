package parkinglot;

import parkinglot.model.*;
import parkinglot.service.ParkingService;
import parkinglot.strategy.feestrategy.FeeStrategy;
import parkinglot.strategy.feestrategy.VehicleBasedFeeStrategy;
import parkinglot.strategy.spotallocationstrategy.BestFitStrategy;
import parkinglot.strategy.spotallocationstrategy.SpotAllocationStrategy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<ParkingSpot> floor1Spots = new ArrayList<>();
        floor1Spots.add(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        floor1Spots.add(new ParkingSpot("F1-S2", VehicleSize.MEDIUM));
        floor1Spots.add(new ParkingSpot("F1-S3", VehicleSize.LARGE));

        List<ParkingSpot> floor2Spots = new ArrayList<>();
        floor2Spots.add(new ParkingSpot("F2-S1", VehicleSize.SMALL));
        floor2Spots.add(new ParkingSpot("F2-S2", VehicleSize.MEDIUM));
        floor2Spots.add(new ParkingSpot("F2-S3", VehicleSize.LARGE));

        ParkingFloor floor1 = new ParkingFloor(1, floor1Spots);

        ParkingFloor floor2 = new ParkingFloor(2, floor2Spots);

        ParkingLot parkingLot = new ParkingLot(List.of(floor1, floor2));

        Map<VehicleType, Double> rates = new EnumMap<>(VehicleType.class);
        rates.put(VehicleType.BIKE, 10.0);
        rates.put(VehicleType.CAR, 20.0);
        rates.put(VehicleType.TRUCK, 50.0);

        FeeStrategy feeStrategy = new VehicleBasedFeeStrategy(rates);
        SpotAllocationStrategy spotAllocationStrategy = new BestFitStrategy();

        ParkingService parkingService = new ParkingService(parkingLot, feeStrategy, spotAllocationStrategy);

        Vehicle bike = new Vehicle("BIKE-101", VehicleType.BIKE, VehicleSize.SMALL);
        Vehicle car = new Vehicle("CAR-101", VehicleType.CAR, VehicleSize.MEDIUM);
        Vehicle truck = new Vehicle("TRUCK-101", VehicleType.TRUCK, VehicleSize.LARGE);

        ParkingTicket bikeTicket = parkingService.parkVehicle(bike);
        ParkingTicket carTicket = parkingService.parkVehicle(car);
        ParkingTicket truckTicket = parkingService.parkVehicle(truck);

        System.out.println("Availability after parking");
        parkingService.displayAvailability();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        double bikeFee = parkingService.unparkVehicle(bikeTicket.getTicketId());
        System.out.println("Bike Fee = " + bikeFee);

        System.out.println("\nAvailability after unparking bike");
        parkingService.displayAvailability();

        double truckFee = parkingService.unparkVehicle(truckTicket.getTicketId());
        System.out.println("Truck Fee = " + truckFee);

        System.out.println("\nAvailability after unparking truck");
        parkingService.displayAvailability();
    }
}
