package com.terence.parking.parkinglot;

public interface VehicleParkingLot {
  public String park(String vehicleNumber, String timestamp) throws CarParkFullException;

  public ParkingSpotInfo exit(String vehicleNumber);
}
