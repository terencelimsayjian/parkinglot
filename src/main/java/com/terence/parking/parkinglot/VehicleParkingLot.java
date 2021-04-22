package com.terence.parking.parkinglot;

public interface VehicleParkingLot {
  String park(String vehicleNumber, String timestamp) throws ParkingLotFullException;

  ParkingSpotInfo exit(String vehicleNumber);
}
