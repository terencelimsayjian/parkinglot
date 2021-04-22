package com.terence.parking.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseVehicleParkingLot implements VehicleParkingLot {
  private List<ParkingSpot> parkingSpots;
  private final VehicleType vehicleType;

  public BaseVehicleParkingLot(int capacity, String idPrefix, VehicleType vehicleType) {
    parkingSpots = new ArrayList<>(capacity);
    this.vehicleType = vehicleType;

    for (int i = 0; i < capacity; i++) {
      parkingSpots.add(new ParkingSpot(idPrefix + (i + 1)));
    }
  }

  @Override
  public String park(String vehicleNumber, String timestamp) throws ParkingLotFullException {
      return getNextAvailableLot().park(vehicleNumber, timestamp);
  }

  private ParkingSpot getNextAvailableLot() throws ParkingLotFullException {
    for (int i = 0; i < parkingSpots.size(); i++) {
      if (parkingSpots.get(i).isVacant()) {
        return parkingSpots.get(i);
      }
    }

    throw new ParkingLotFullException();
  }

  @Override
  public ParkingSummary exit(String vehicleNumber) {
    Optional<ParkingSpot> optionalParkingSpot =
        parkingSpots.stream()
            .filter(ps -> !ps.isVacant())
            .filter(s -> s.getVehicleNumber().equals(vehicleNumber))
            .findFirst();

    if (optionalParkingSpot.isPresent()) {
      ParkingSpot parkingSpot = optionalParkingSpot.get();
      ParkingSummary parkingSummary =
          new ParkingSummary(parkingSpot.getId(), parkingSpot.getTimestamp(), vehicleType);
      parkingSpot.leave();

      return parkingSummary;
    }

    return null;
  }
}
