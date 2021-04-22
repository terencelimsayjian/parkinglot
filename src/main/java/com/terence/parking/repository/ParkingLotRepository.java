package com.terence.parking.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLotRepository {
  private static List<ParkingSpot> carLots;
  private static List<ParkingSpot> motorcycleLots;

  private ParkingLotRepository() {}

  public static void initialise(int carCapacity, int motorcycleCapacity) {

    carLots = new ArrayList<>(carCapacity);
    motorcycleLots = new ArrayList<>(motorcycleCapacity);

    for (int i = 0; i < carCapacity; i++) {
      carLots.add(new ParkingSpot(Integer.toString(i + 1)));
    }

    for (int i = 0; i < motorcycleCapacity; i++) {
      motorcycleLots.add(new ParkingSpot(Integer.toString(i + 1)));
    }
  }

  public static String park(String vehicleType, String vehicleNumber, String timestamp)
      throws ParkingLotException {

    String prefix = "";
    int nextAvailableLot = 0;
    if (vehicleType.equals("car")) {
      nextAvailableLot = getNextAvailableLot("car");

      ParkingSpot availableParkingSpot = carLots.get(nextAvailableLot);
      availableParkingSpot.park(vehicleNumber, timestamp);
      prefix = "CarLot";
    } else if (vehicleType.equals("motorcycle")) {
      nextAvailableLot = getNextAvailableLot("motorcycle");

      ParkingSpot availableParkingSpot = motorcycleLots.get(nextAvailableLot);
      availableParkingSpot.park(vehicleNumber, timestamp);
      prefix = "MotorcycleLot";
    }

    int lotId = nextAvailableLot + 1;
    return prefix + lotId;
  }

  private static int getNextAvailableLot(String vehicleType) throws ParkingLotException {
    List<ParkingSpot> carparkLots = carLots;
    if (vehicleType.equals("car")) {
      carparkLots = carLots;
    } else if (vehicleType.equals("motorcycle")) {
      carparkLots = motorcycleLots;
    }

    for (int i = 0; i < carparkLots.size(); i++) {

      if (carparkLots.get(i).isVacant()) {
        return i;
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
      ParkingSpotInfo parkingSpotInfo = new ParkingSpotInfo(parkingSpot.getId(), parkingSpot.getTimestamp());
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
      ParkingSpotInfo parkingSpotInfo = new ParkingSpotInfo(parkingSpot.getId(), parkingSpot.getTimestamp());
      parkingSpot.leave();
      return parkingSpotInfo;
    }

    return null;
  }
}
