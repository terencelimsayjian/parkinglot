package com.terence.parking.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLot {
  private static List<ParkingSpot> carLots;
  private static List<ParkingSpot> motorcycleLots;

  private ParkingLot() {}

  public static void initialise(int carCapacity, int motorcycleCapacity) {

    carLots = new ArrayList<>(carCapacity);
    motorcycleLots = new ArrayList<>(motorcycleCapacity);

    for (int i = 0; i < carCapacity; i++) {
      carLots.add(new ParkingSpot("CarLot" + Integer.toString(i + 1)));
    }

    for (int i = 0; i < motorcycleCapacity; i++) {
      motorcycleLots.add(new ParkingSpot("MotorcycleLot" + Integer.toString(i + 1)));
    }
  }

  public static String park(String vehicleNumber, String timestamp, VehicleType vehicleTypeEnum)
      throws ParkingLotException {

    return getNextAvailableLot(vehicleTypeEnum).park(vehicleNumber, timestamp);
  }

  private static ParkingSpot getNextAvailableLot(VehicleType vehicleTypeEnum)
      throws ParkingLotException {
    List<ParkingSpot> carparkLots = carLots;
    switch (vehicleTypeEnum) {
      case CAR:
        carparkLots = carLots;
        break;
      case MOTORCYCLE:
        carparkLots = motorcycleLots;
        break;
      default:
        // Throw Unsupported vehicle exception
    }

    for (int i = 0; i < carparkLots.size(); i++) {

      if (carparkLots.get(i).isVacant()) {
        return carparkLots.get(i);
      }
    }

    throw new ParkingLotException();
  }

  public static ParkingSpotInfo exit(String vehicleNumber) {
    Optional<ParkingSpot> optionalCarParkingSpot =
        carLots.stream()
            .filter(ps -> !ps.isVacant())
            .filter(s -> s.getVehicleNumber().equals(vehicleNumber))
            .findFirst();

    if (optionalCarParkingSpot.isPresent()) {
      ParkingSpot parkingSpot = optionalCarParkingSpot.get();
      ParkingSpotInfo parkingSpotInfo =
          new ParkingSpotInfo(parkingSpot.getId(), parkingSpot.getTimestamp(), VehicleType.CAR);
      parkingSpot.leave();
      return parkingSpotInfo;
    }

    Optional<ParkingSpot> optionalMotorcycleParkingSpot =
        motorcycleLots.stream()
            .filter(ps -> !ps.isVacant())
            .filter(s -> s.getVehicleNumber().equals(vehicleNumber))
            .findFirst();

    if (optionalMotorcycleParkingSpot.isPresent()) {
      ParkingSpot parkingSpot = optionalMotorcycleParkingSpot.get();
      ParkingSpotInfo parkingSpotInfo =
          new ParkingSpotInfo(
              parkingSpot.getId(), parkingSpot.getTimestamp(), VehicleType.MOTORCYCLE);
      parkingSpot.leave();
      return parkingSpotInfo;
    }

    return null;
  }
}
