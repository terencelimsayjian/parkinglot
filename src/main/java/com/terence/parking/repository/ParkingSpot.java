package com.terence.parking.repository;

class ParkingSpot {

  private String vehicleNumber;
  private String timestamp;

  ParkingSpot() {
    vehicleNumber = null;
    timestamp = null;
  }

  String getVehicleNumber() {
    return vehicleNumber;
  }

  String getTimestamp() {
    return timestamp;
  }

  void park(String vehicleNumber, String timestamp) {
    this.vehicleNumber = vehicleNumber;
    this.timestamp = timestamp;
  }

  boolean isVacant() {
    return vehicleNumber == null;
  }

  void leave() {
    vehicleNumber = null;
    timestamp = null;
  }
}
