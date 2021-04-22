package com.terence.parking.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseVehicleParkingLotTest {

  @Test
  void shouldReturnIdOfCarWhenParkedSuccessfully() throws Exception {
    BaseVehicleParkingLot carVehicleParkingLot = new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR);

    String firstParkedCarSpace = carVehicleParkingLot.park("SGX1234A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
  }

  @Test
  void shouldReturnIdOfSecondCarWhenParkedSuccessfully() throws Exception {
    BaseVehicleParkingLot carVehicleParkingLot = new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR);

    String firstParkedCarSpace = carVehicleParkingLot.park("SGX1234A", "1613541902");
    String secondParkedCarSpace = carVehicleParkingLot.park("WWW5555A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
    assertEquals("CarLot2", secondParkedCarSpace);
  }

  @Test
  void shouldThrowCarParkFullExceptionIfCarLotsAreFull() throws Exception {
    BaseVehicleParkingLot carVehicleParkingLot = new BaseVehicleParkingLot(1, "CarLot", VehicleType.CAR);

    String firstParkedCarSpace = carVehicleParkingLot.park("SGX1234A", "1613541902");
    assertThrows(
        ParkingLotFullException.class,
        () -> carVehicleParkingLot.park("WWW5555A", "1613541902"));

    assertEquals("CarLot1", firstParkedCarSpace);
  }

  @Test
  void shouldReturnIdIdOfSecondCarWhenParkedSuccessfully() throws Exception {
    BaseVehicleParkingLot carVehicleParkingLot = new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR);

    String firstParkedCarSpace = carVehicleParkingLot.park("SGX1234A", "1613541902");
    String secondParkedCarSpace = carVehicleParkingLot.park("WWW5555A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
    assertEquals("CarLot2", secondParkedCarSpace);
  }

  @Test
  void shouldReturnTimestampWhenExitingCarpark() throws Exception {
    BaseVehicleParkingLot carVehicleParkingLot = new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR);

    String firstParkedCarSpace = carVehicleParkingLot.park("SGX1234A", "1613541902");
    assertEquals("CarLot1", firstParkedCarSpace);

    ParkingSpotInfo parkingSpotInfo = carVehicleParkingLot.exit("SGX1234A");
    assertEquals("1613541902", parkingSpotInfo.getTimestamp());
    assertEquals("CarLot1", parkingSpotInfo.getLotId());
  }

}
