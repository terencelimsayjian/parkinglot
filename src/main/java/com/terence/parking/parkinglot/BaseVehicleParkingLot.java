package com.terence.parking.parkinglot;

import com.terence.parking.feecalculation.FeeCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BaseVehicleParkingLot implements VehicleParkingLot {
  private List<ParkingSpot> parkingSpots;
  private final VehicleType vehicleType;
  private final FeeCalculator feeCalculator;

  public BaseVehicleParkingLot(
      int capacity, String idPrefix, VehicleType vehicleType, FeeCalculator feeCalculator) {
    parkingSpots = new ArrayList<>(capacity);
    this.vehicleType = vehicleType;
    this.feeCalculator = feeCalculator;

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
  public ParkingSummary exit(String vehicleNumber, long endTimeEpochSeconds) {
    Optional<ParkingSpot> optionalParkingSpot =
        parkingSpots.stream()
            .filter(ps -> !ps.isVacant())
            .filter(s -> s.getVehicleNumber().equals(vehicleNumber))
            .findFirst();

    if (optionalParkingSpot.isPresent()) {
      ParkingSpot parkingSpot = optionalParkingSpot.get();

      BigDecimal parkingFee =
          feeCalculator.calculate(Long.valueOf(parkingSpot.getTimestamp()), endTimeEpochSeconds);

      ParkingSummary parkingSummary =
          new ParkingSummary(
              parkingSpot.getId(), parkingSpot.getTimestamp(), vehicleType, parkingFee);
      parkingSpot.leave();

      return parkingSummary;
    }

    return null;
  }
}
