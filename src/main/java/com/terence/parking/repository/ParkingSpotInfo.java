package com.terence.parking.repository;

public class ParkingSpotInfo {
  private final String lotId;
  private final String timestamp;

  public ParkingSpotInfo(String lotId, String timestamp) {
    this.lotId = lotId;
    this.timestamp = timestamp;
  }

  public String getLotId() {
    return lotId;
  }

  public String getTimestamp() {
    return timestamp;
  }
}
