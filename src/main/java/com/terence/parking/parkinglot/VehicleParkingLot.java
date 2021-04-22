package com.terence.parking.parkinglot;

public interface VehicleParkingLot {
  String park(String vehicleNumber, String timestamp) throws ParkingLotFullException;

  ParkingSummary exit(String vehicleNumber, long endTimeEpochSeconds);
}
