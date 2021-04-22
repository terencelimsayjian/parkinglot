package com.terence.parking.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

  @Test
  void shouldReturnIdOfCarWhenParkedSuccessfully() throws Exception {
    ParkingLot.initialise(
        new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR),
        new BaseVehicleParkingLot(3, "MotorcycleLot", VehicleType.MOTORCYCLE));

    String firstParkedCarSpace = ParkingLot.park("SGX1234A", "1613541902", VehicleType.CAR);

    assertEquals("CarLot1", firstParkedCarSpace);
  }

  @Test
  void shouldReturnIdOfSecondCarWhenParkedSuccessfully() throws Exception {
    ParkingLot.initialise(new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR),
        new BaseVehicleParkingLot(3, "MotorcycleLot", VehicleType.MOTORCYCLE));

    String firstParkedCarSpace = ParkingLot.park("SGX1234A", "1613541902", VehicleType.CAR);
    String secondParkedCarSpace = ParkingLot.park("WWW5555A", "1613541902", VehicleType.CAR);

    assertEquals("CarLot1", firstParkedCarSpace);
    assertEquals("CarLot2", secondParkedCarSpace);
  }

}
