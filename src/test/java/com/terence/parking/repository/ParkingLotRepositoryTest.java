package com.terence.parking.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotRepositoryTest {

  @Test
  void shouldReturnIdOfCarWhenParkedSuccessfully() throws Exception {
    ParkingLotRepository.initialise(3, 3);

    String firstParkedCarSpace = ParkingLotRepository.park("car", "SGX1234A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
  }

  @Test
  void shouldReturnIdOfSecondCarWhenParkedSuccessfully() throws Exception {
    ParkingLotRepository.initialise(3, 3);

    String firstParkedCarSpace = ParkingLotRepository.park("car", "SGX1234A", "1613541902");
    String secondParkedCarSpace = ParkingLotRepository.park("car", "WWW5555A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
    assertEquals("CarLot2", secondParkedCarSpace);
  }

  @Test
  void shouldThrowParkingLotExceptionIfCarLotsAreFull() throws Exception {
    ParkingLotRepository.initialise(1, 1);

    String firstParkedCarSpace = ParkingLotRepository.park("car", "SGX1234A", "1613541902");
    assertThrows(
        ParkingLotException.class,
        () -> ParkingLotRepository.park("car", "WWW5555A", "1613541902"));

    assertEquals("CarLot1", firstParkedCarSpace);
  }

  @Test
  void shouldReturnIdIdOfSecondCarWhenParkedSuccessfully() throws Exception {
    ParkingLotRepository.initialise(3, 3);

    String firstParkedCarSpace = ParkingLotRepository.park("car", "SGX1234A", "1613541902");
    String secondParkedCarSpace = ParkingLotRepository.park("car", "WWW5555A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
    assertEquals("CarLot2", secondParkedCarSpace);
  }

  @Test
  void shouldReturnTimestampWhenExitingCarpark() throws Exception {
    ParkingLotRepository.initialise(1, 1);

    String firstParkedCarSpace = ParkingLotRepository.park("car", "SGX1234A", "1613541902");
    assertEquals("CarLot1", firstParkedCarSpace);

    ParkingSpotInfo parkingSpotInfo = ParkingLotRepository.exit("SGX1234A");
    assertEquals("1613541902", parkingSpotInfo.getTimestamp());
    assertEquals("CarLot1", parkingSpotInfo.getLotId());
  }


  @Test
  void shouldReturnIdOfMotorcycleWhenParkedSuccessfully() throws Exception {
    ParkingLotRepository.initialise(3, 3);

    String firstParkedSpace = ParkingLotRepository.park("motorcycle", "SGX1234A", "1613541902");

    assertEquals("MotorcycleLot1", firstParkedSpace);
  }

  @Test
  void shouldReturnIdOfSecondMotorcycleWhenParkedSuccessfully() throws Exception {
    ParkingLotRepository.initialise(3, 3);

    String firstParkedSpace = ParkingLotRepository.park("motorcycle", "SGX1234A", "1613541902");
    String secondParkedSpace = ParkingLotRepository.park("motorcycle", "WWW5555A", "1613541902");

    assertEquals("MotorcycleLot1", firstParkedSpace);
    assertEquals("MotorcycleLot2", secondParkedSpace);
  }

  @Test
  void shouldThrowParkingLotExceptionIfMotorcycleLotsAreFull() throws Exception {
    ParkingLotRepository.initialise(1, 1);

    String firstParkedSpace = ParkingLotRepository.park("motorcycle", "SGX1234A", "1613541902");
    assertThrows(
        ParkingLotException.class,
        () -> ParkingLotRepository.park("motorcycle", "WWW5555A", "1613541902"));

    assertEquals("MotorcycleLot1", firstParkedSpace);
  }

  @Test
  void shouldReturnTimestampWhenMotorcycleExitsCarpark() throws Exception {
    ParkingLotRepository.initialise(1, 1);

    String firstParkedCarSpace = ParkingLotRepository.park("motorcycle", "SGX1234A", "1613541902");
    assertEquals("MotorcycleLot1", firstParkedCarSpace);

    ParkingSpotInfo parkingSpotInfo = ParkingLotRepository.exit("SGX1234A");
    assertEquals("1613541902", parkingSpotInfo.getTimestamp());
    assertEquals("MotorcycleLot1", parkingSpotInfo.getLotId());
  }
}
