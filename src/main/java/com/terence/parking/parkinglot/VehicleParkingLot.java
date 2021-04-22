package com.terence.parking.parkinglot;

public interface VehicleParkingLot {
  String park(String vehicleNumber, String timestamp) throws CarParkFullException;

  ParkingSpotInfo exit(String vehicleNumber);
}
