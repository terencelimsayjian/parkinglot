package com.terence.parking.repository;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotRepository {
  private static int CAR_CAPACITY;
  private static int MOTORCYCLE_CAPACITY;

  private static List<String> carLots;
  private static List<String> motorcycleLots;

  private ParkingLotRepository() {}

  public static void initialise(int carCapacity, int motorcycleCapacity) {
    CAR_CAPACITY = carCapacity;
    MOTORCYCLE_CAPACITY = carCapacity;

    carLots = new ArrayList<>(carCapacity);
    motorcycleLots = new ArrayList<>(motorcycleCapacity);

    for (int i = 0; i < carCapacity; i++) {
      carLots.add(null);
    }

    for (int i = 0; i < motorcycleCapacity; i++) {
      motorcycleLots.add(null);
    }
  }

  public static String park(String vehicleType, String vehicleNumber, String timestamp) {



    String prefix = "";
    int nextAvailableLot = 0;
    if (vehicleType.equals("car")) {
      nextAvailableLot = getNextAvailableLot("car");
      carLots.set(nextAvailableLot, vehicleNumber);
      prefix = "CarLot";
    } else if (vehicleType.equals("motorcycle")) {
      nextAvailableLot = getNextAvailableLot("motorcycle");
      motorcycleLots.set(nextAvailableLot, vehicleNumber);
      prefix = "MotorcycleLot";
    }

    int lotId = nextAvailableLot + 1;
    return prefix + lotId;
  }

  private static int getNextAvailableLot(String vehicleType) {
    List<String> carparkLots = carLots;
    if (vehicleType.equals("car")) {
      carparkLots = carLots;
    } else if (vehicleType.equals("motorcycle")) {
      carparkLots = motorcycleLots;
    }

    for (int i = 0; i < carparkLots.size(); i++) {

      if (carparkLots.get(i) == null) {
        return i;
      }
    }

    return 0;
  }


}
