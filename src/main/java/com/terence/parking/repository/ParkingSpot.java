package com.terence.parking.repository;

public class ParkingSpot {

  private String vehicleNumber;
  private String timestamp;

  public ParkingSpot() {
    vehicleNumber = null;
    timestamp = null;
  }

  public String getVehicleNumber() {
    return vehicleNumber;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void park(String vehicleNumber, String timestamp) {
    this.vehicleNumber = vehicleNumber;
    this.timestamp = timestamp;
  }

  public boolean isVacant() {
    return vehicleNumber == null;
  }

  public void leave() {
    vehicleNumber = null;
    timestamp = null;
  }
}
