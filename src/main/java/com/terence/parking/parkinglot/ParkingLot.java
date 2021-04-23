package com.terence.parking.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
  private static Map<String, VehicleType> vehicleLookup;

  private static VehicleParkingLot carParkingLot;
  private static VehicleParkingLot motorCycleParkingLot;
  private static boolean isInitialised = false;

  private ParkingLot() {}

  public static void initialise(VehicleParkingLot carLot, VehicleParkingLot motorCycleLot) {
    vehicleLookup = new HashMap<>();
    carParkingLot = carLot;
    motorCycleParkingLot = motorCycleLot;
    isInitialised = true;
  }

  public static String park(String vehicleNumber, String timestamp, VehicleType vehicleTypeEnum)
      throws ParkingLotFullException, ParkingLotNotInitialisedException {
    if (!isInitialised) {
      throw new ParkingLotNotInitialisedException();
    }

    String park = getVehicleParkingLot(vehicleTypeEnum).park(vehicleNumber, timestamp);
    vehicleLookup.put(vehicleNumber, vehicleTypeEnum);
    return park;
  }

  public static ParkingSummary exit(String vehicleNumber, long endTimeEpochSeconds)
      throws ParkingLotNotInitialisedException {
    if (!isInitialised) {
      throw new ParkingLotNotInitialisedException();
    }

    VehicleType vehicleType = vehicleLookup.get(vehicleNumber);
    return getVehicleParkingLot(vehicleType).exit(vehicleNumber, endTimeEpochSeconds);
  }

  public static void resetInstance() {
    vehicleLookup = new HashMap<>();
    carParkingLot = null;
    motorCycleParkingLot = null;
    isInitialised = false;
  }

  private static VehicleParkingLot getVehicleParkingLot(VehicleType vehicleTypeEnum) {
    switch (vehicleTypeEnum) {
      case CAR:
        return carParkingLot;
      case MOTORCYCLE:
        return motorCycleParkingLot;
      default:
        return carParkingLot;
        // TODO: Throw new exception
    }
  }
}
