package com.terence.parking.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

  @Test
  void shouldReturnIdOfCarWhenParkedSuccessfully() throws Exception {
    ParkingLot.initialise(3, 3);

    String firstParkedCarSpace = ParkingLot.park("car", "SGX1234A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
  }

  @Test
  void shouldReturnIdOfSecondCarWhenParkedSuccessfully() throws Exception {
    ParkingLot.initialise(3, 3);

    String firstParkedCarSpace = ParkingLot.park("car", "SGX1234A", "1613541902");
    String secondParkedCarSpace = ParkingLot.park("car", "WWW5555A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
    assertEquals("CarLot2", secondParkedCarSpace);
  }

  @Test
  void shouldThrowParkingLotExceptionIfCarLotsAreFull() throws Exception {
    ParkingLot.initialise(1, 1);

    String firstParkedCarSpace = ParkingLot.park("car", "SGX1234A", "1613541902");
    assertThrows(
        ParkingLotException.class,
        () -> ParkingLot.park("car", "WWW5555A", "1613541902"));

    assertEquals("CarLot1", firstParkedCarSpace);
  }

  @Test
  void shouldReturnIdIdOfSecondCarWhenParkedSuccessfully() throws Exception {
    ParkingLot.initialise(3, 3);

    String firstParkedCarSpace = ParkingLot.park("car", "SGX1234A", "1613541902");
    String secondParkedCarSpace = ParkingLot.park("car", "WWW5555A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
    assertEquals("CarLot2", secondParkedCarSpace);
  }

  @Test
  void shouldReturnTimestampWhenExitingCarpark() throws Exception {
    ParkingLot.initialise(1, 1);

    String firstParkedCarSpace = ParkingLot.park("car", "SGX1234A", "1613541902");
    assertEquals("CarLot1", firstParkedCarSpace);

    ParkingSpotInfo parkingSpotInfo = ParkingLot.exit("SGX1234A");
    assertEquals("1613541902", parkingSpotInfo.getTimestamp());
    assertEquals("CarLot1", parkingSpotInfo.getLotId());
  }


  @Test
  void shouldReturnIdOfMotorcycleWhenParkedSuccessfully() throws Exception {
    ParkingLot.initialise(3, 3);

    String firstParkedSpace = ParkingLot.park("motorcycle", "SGX1234A", "1613541902");

    assertEquals("MotorcycleLot1", firstParkedSpace);
  }

  @Test
  void shouldReturnIdOfSecondMotorcycleWhenParkedSuccessfully() throws Exception {
    ParkingLot.initialise(3, 3);

    String firstParkedSpace = ParkingLot.park("motorcycle", "SGX1234A", "1613541902");
    String secondParkedSpace = ParkingLot.park("motorcycle", "WWW5555A", "1613541902");

    assertEquals("MotorcycleLot1", firstParkedSpace);
    assertEquals("MotorcycleLot2", secondParkedSpace);
  }

  @Test
  void shouldThrowParkingLotExceptionIfMotorcycleLotsAreFull() throws Exception {
    ParkingLot.initialise(1, 1);

    String firstParkedSpace = ParkingLot.park("motorcycle", "SGX1234A", "1613541902");
    assertThrows(
        ParkingLotException.class,
        () -> ParkingLot.park("motorcycle", "WWW5555A", "1613541902"));

    assertEquals("MotorcycleLot1", firstParkedSpace);
  }

  @Test
  void shouldReturnTimestampWhenMotorcycleExitsCarpark() throws Exception {
    ParkingLot.initialise(1, 1);

    String firstParkedCarSpace = ParkingLot.park("motorcycle", "SGX1234A", "1613541902");
    assertEquals("MotorcycleLot1", firstParkedCarSpace);

    ParkingSpotInfo parkingSpotInfo = ParkingLot.exit("SGX1234A");
    assertEquals("1613541902", parkingSpotInfo.getTimestamp());
    assertEquals("MotorcycleLot1", parkingSpotInfo.getLotId());
    assertEquals("motorcycle", parkingSpotInfo.getVehicleType());
  }
}
