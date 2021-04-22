package com.terence.parking.parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ParkingLot {
  private static List<ParkingSpot> carLots;
  private static List<ParkingSpot> motorcycleLots;
  private static Map<String, VehicleType> vehicleLookup;

  private ParkingLot() {}

  public static void initialise(int carCapacity, int motorcycleCapacity) {

    carLots = new ArrayList<>(carCapacity);
    motorcycleLots = new ArrayList<>(motorcycleCapacity);

    vehicleLookup = new HashMap<>();

    for (int i = 0; i < carCapacity; i++) {
      carLots.add(new ParkingSpot("CarLot" + Integer.toString(i + 1)));
    }

    for (int i = 0; i < motorcycleCapacity; i++) {
      motorcycleLots.add(new ParkingSpot("MotorcycleLot" + Integer.toString(i + 1)));
    }
  }

  public static String park(String vehicleNumber, String timestamp, VehicleType vehicleTypeEnum)
      throws ParkingLotException {

    String carparkId = getNextAvailableLot(vehicleTypeEnum).park(vehicleNumber, timestamp);

    vehicleLookup.put(vehicleNumber, vehicleTypeEnum);

    return carparkId;
  }

  private static ParkingSpot getNextAvailableLot(VehicleType vehicleTypeEnum)
      throws ParkingLotException {
    List<ParkingSpot> parkingSpots = carLots;
    switch (vehicleTypeEnum) {
      case CAR:
        parkingSpots = carLots;
        break;
      case MOTORCYCLE:
        parkingSpots = motorcycleLots;
        break;
      default:
        // Throw Unsupported vehicle exception
    }

    for (int i = 0; i < parkingSpots.size(); i++) {

      if (parkingSpots.get(i).isVacant()) {
        return parkingSpots.get(i);
      }
    }

    throw new ParkingLotException();
  }

  public static ParkingSpotInfo exit(String vehicleNumber) {
    VehicleType vehicleType = vehicleLookup.get(vehicleNumber);

    List<ParkingSpot> parkingSpots = carLots;

    switch (vehicleType) {
      case CAR:
        parkingSpots = carLots;
        break;
      case MOTORCYCLE:
        parkingSpots = motorcycleLots;
        break;
      default:
        // throw Exception
    }

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

      vehicleLookup.remove(vehicleNumber);
      return parkingSpotInfo;
    }

    return null;
  }
}
