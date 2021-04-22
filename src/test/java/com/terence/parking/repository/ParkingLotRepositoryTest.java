package com.terence.parking.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotRepositoryTest {

  @Test
  void shouldReturnIdOfCarWhenParkedSuccessfully() {
    ParkingLotRepository.initialise(3, 3);

    String firstParkedCarSpace = ParkingLotRepository.park("car", "SGX1234A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
  }

  @Test
  void shouldReturnIdOfSecondCarWhenParkedSuccessfully() {
    ParkingLotRepository.initialise(3, 3);

    String firstParkedCarSpace = ParkingLotRepository.park("car", "SGX1234A", "1613541902");
    String secondParkedCarSpace = ParkingLotRepository.park("car", "WWW5555A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
    assertEquals("CarLot2", secondParkedCarSpace);
  }

  @Test
  void shouldReturnIdOfMotorcycleWhenParkedSuccessfully() {
    ParkingLotRepository.initialise(3, 3);

    String firstParkedCarSpace = ParkingLotRepository.park("motorcycle", "SGX1234A", "1613541902");

    assertEquals("MotorcycleLot1", firstParkedCarSpace);
  }

  @Test
  void shouldReturnIdOfSecondMotorcycleWhenParkedSuccessfully() {
    ParkingLotRepository.initialise(3, 3);

    String firstParkedCarSpace = ParkingLotRepository.park("motorcycle", "SGX1234A", "1613541902");
    String secondParkedCarSpace = ParkingLotRepository.park("motorcycle", "WWW5555A", "1613541902");

    assertEquals("MotorcycleLot1", firstParkedCarSpace);
    assertEquals("MotorcycleLot2", secondParkedCarSpace);
  }


}
