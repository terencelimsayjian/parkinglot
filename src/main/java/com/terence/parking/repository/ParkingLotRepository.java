package com.terence.parking.repository;

public class ParkingLotRepository {
  private ParkingLotRepository() {}

  public static void initialise(int parseInt, int parseInt1) {}

  public static String park(String vehicleType, String vehicleNumber, String timestamp) {

    String prefix = "";
    if (vehicleType.equals("car")) {
      prefix = "CarLot";
    } else if (vehicleType.equals("motorcycle")) {
      prefix = "MotorcycleLot";
    }

    return prefix + "1";
  }
}
