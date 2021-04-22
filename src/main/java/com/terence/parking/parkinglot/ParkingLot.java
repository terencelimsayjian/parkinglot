package com.terence.parking.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
  private static Map<String, VehicleType> vehicleLookup;

  private static VehicleParkingLot carParkingLot;
  private static VehicleParkingLot motorCycleParkingLot;

  private ParkingLot() {}

  public static void initialise(int carCapacity, int motorcycleCapacity) {
    vehicleLookup = new HashMap<>();

    carParkingLot = new BaseVehicleParkingLot(carCapacity, "CarLot", VehicleType.CAR);
    motorCycleParkingLot =
        new BaseVehicleParkingLot(motorcycleCapacity, "MotorcycleLot", VehicleType.MOTORCYCLE);
  }

  public static String park(String vehicleNumber, String timestamp, VehicleType vehicleTypeEnum)
      throws ParkingLotException {
    if (vehicleTypeEnum == VehicleType.CAR) {
      try {
        String park = carParkingLot.park(vehicleNumber, timestamp);
        vehicleLookup.put(vehicleNumber, VehicleType.CAR);
        return park;
      } catch (CarParkFullException e) {
        throw new ParkingLotException();
      }
    } else {
      try {
        String park = motorCycleParkingLot.park(vehicleNumber, timestamp);
        vehicleLookup.put(vehicleNumber, VehicleType.MOTORCYCLE);
        return park;
      } catch (CarParkFullException e) {
        throw new ParkingLotException();
      }
    }
  }

  public static ParkingSpotInfo exit(String vehicleNumber) {
    VehicleType vehicleType = vehicleLookup.get(vehicleNumber);

    if (vehicleType == VehicleType.CAR) {
      return carParkingLot.exit(vehicleNumber);
    } else {
      return motorCycleParkingLot.exit(vehicleNumber);
    }
  }
}
