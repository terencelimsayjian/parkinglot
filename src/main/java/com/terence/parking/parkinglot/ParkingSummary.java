package com.terence.parking.parkinglot;

import java.math.BigDecimal;

public class ParkingSummary {
  private final String lotId;
  private final String timestamp;
  private final VehicleType vehicleTypeEnum;
  private final BigDecimal parkingFee;

  public ParkingSummary(
      String lotId, String timestamp, VehicleType vehicleTypeEnum, BigDecimal parkingFee) {
    this.lotId = lotId;
    this.timestamp = timestamp;
    this.vehicleTypeEnum = vehicleTypeEnum;
    this.parkingFee = parkingFee;
  }

  public BigDecimal getParkingFee() {
    return parkingFee;
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
