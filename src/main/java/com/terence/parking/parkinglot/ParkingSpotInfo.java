package com.terence.parking.parkinglot;

public class ParkingSpotInfo {
  private final String lotId;
  private final String timestamp;
  private final VehicleType vehicleTypeEnum;

  public ParkingSpotInfo(String lotId, String timestamp, VehicleType vehicleTypeEnum) {
    this.lotId = lotId;
    this.timestamp = timestamp;
    this.vehicleTypeEnum = vehicleTypeEnum;
  }

  public String getLotId() {
    return lotId;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public VehicleType getVehicleTypeEnum() {
    return vehicleTypeEnum;
  }
}
