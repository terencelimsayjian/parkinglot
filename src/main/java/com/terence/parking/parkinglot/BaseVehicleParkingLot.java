package com.terence.parking.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseVehicleParkingLot implements VehicleParkingLot {
  private List<ParkingSpot> parkingSpots;
  private final VehicleType vehicleType;

  public BaseVehicleParkingLot(int carCapacity, String idPrefix, VehicleType vehicleType) {
    parkingSpots = new ArrayList<>(carCapacity);
    this.vehicleType = vehicleType;

    for (int i = 0; i < carCapacity; i++) {
      parkingSpots.add(new ParkingSpot(idPrefix + Integer.toString(i + 1)));
    }
  }

  @Override
  public String park(String vehicleNumber, String timestamp) throws CarParkFullException {
    try {
      return getNextAvailableLot().park(vehicleNumber, timestamp);
    } catch (ParkingLotException e) {
      throw new CarParkFullException();
    }
  }

  private ParkingSpot getNextAvailableLot() throws ParkingLotException {
    for (int i = 0; i < parkingSpots.size(); i++) {
      if (parkingSpots.get(i).isVacant()) {
        return parkingSpots.get(i);
      }
    }

    throw new ParkingLotException();
  }

  @Override
  public ParkingSpotInfo exit(String vehicleNumber) {
    Optional<ParkingSpot> optionalCarParkingSpot =
        parkingSpots.stream()
            .filter(ps -> !ps.isVacant())
            .filter(s -> s.getVehicleNumber().equals(vehicleNumber))
            .findFirst();

    if (optionalCarParkingSpot.isPresent()) {
      ParkingSpot parkingSpot = optionalCarParkingSpot.get();
      ParkingSpotInfo parkingSpotInfo =
          new ParkingSpotInfo(parkingSpot.getId(), parkingSpot.getTimestamp(), vehicleType);
      parkingSpot.leave();

      return parkingSpotInfo;
    }

    return null;
  }
}
