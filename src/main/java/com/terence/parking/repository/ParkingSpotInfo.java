package com.terence.parking.repository;

public class ParkingSpotInfo {
  private final String lotId;
  private final String timestamp;
  private final String vehicleType;

  public ParkingSpotInfo(String lotId, String timestamp, String vehicleType) {
    this.lotId = lotId;
    this.timestamp = timestamp;
    this.vehicleType = vehicleType;
  }

  public String getLotId() {
    return lotId;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getVehicleType() {
    return vehicleType;
  }
}
