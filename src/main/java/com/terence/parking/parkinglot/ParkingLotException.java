package com.terence.parking.parkinglot;

public class ParkingLotException extends RuntimeException {
  public static final String PARKING_LOT_NOT_INITIALISED = "Parking Lot not initialised.";
  public static final String VEHICLE_NUMBER_ALREADY_EXISTS = "Vehicle number already exists.";
  public static final String VEHICLE_NUMBER_DOES_NOT_EXIST = "Vehicle number does not exist.";


  public ParkingLotException(String message) {
    super(message);
  }
}
