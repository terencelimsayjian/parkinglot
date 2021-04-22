package com.terence.parking.repository;

class ParkingSpot {

  private String vehicleNumber;
  private String timestamp;
  private String id;

  ParkingSpot(String id) {
    vehicleNumber = null;
    timestamp = null;
    this.id = id;
  }

  String getVehicleNumber() {
    return vehicleNumber;
  }

  String getTimestamp() {
    return timestamp;
  }

  public String getId() {
    return id;
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
