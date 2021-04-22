package com.terence.parking.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

  @Test
  void shouldHandleMultipleVehiclesParkingAndExiting() throws Exception {
    ParkingLot.initialise(
        new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR),
        new BaseVehicleParkingLot(3, "MotorcycleLot", VehicleType.MOTORCYCLE));

    String carpark1 = ParkingLot.park("V1", "timestamp1", VehicleType.CAR);
    String motorcyclePark3 = ParkingLot.park("V3", "timestamp3", VehicleType.MOTORCYCLE);

    ParkingSpotInfo v1Exit = ParkingLot.exit("V1");
    ParkingSpotInfo v3Exit = ParkingLot.exit("V3");

    String carpark2 = ParkingLot.park("V2", "timestamp2", VehicleType.CAR);
    String motorcyclePark4 = ParkingLot.park("V4", "timestamp4", VehicleType.MOTORCYCLE);

    assertEquals("CarLot1", carpark1);
    assertEquals("MotorcycleLot1", motorcyclePark3);

    assertParkingLotInfoCorrect(v1Exit, "CarLot1", "timestamp1", VehicleType.CAR);
    assertParkingLotInfoCorrect(v3Exit, "MotorcycleLot1", "timestamp3", VehicleType.MOTORCYCLE);

    assertEquals("CarLot1", carpark2);
    assertEquals("MotorcycleLot1", motorcyclePark4);
  }

  @Test
  void shouldBeAbleToParkCarsEvenIfMotorcycleCarParkIsFull() throws Exception {
    ParkingLot.initialise(
        new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR),
        new BaseVehicleParkingLot(3, "MotorcycleLot", VehicleType.MOTORCYCLE));

    String motorcyclePark1 = ParkingLot.park("V1", "timestamp1", VehicleType.MOTORCYCLE);
    String motorcyclePark2 = ParkingLot.park("V2", "timestamp2", VehicleType.MOTORCYCLE);
    String motorcyclePark3 = ParkingLot.park("V3", "timestamp3", VehicleType.MOTORCYCLE);
    assertThrows(ParkingLotFullException.class, () -> ParkingLot.park("V3", "timestamp3", VehicleType.MOTORCYCLE));

    assertEquals("MotorcycleLot1", motorcyclePark1);
    assertEquals("MotorcycleLot2", motorcyclePark2);
    assertEquals("MotorcycleLot3", motorcyclePark3);

    String carpark1 = ParkingLot.park("V1", "timestamp1", VehicleType.CAR);
    String carpark2 = ParkingLot.park("V2", "timestamp2", VehicleType.CAR);

    assertEquals("CarLot1", carpark1);
    assertEquals("CarLot2", carpark2);
  }

  private void assertParkingLotInfoCorrect(ParkingSpotInfo v1Exit, String carLot1, String timestamp1, VehicleType car) {
    assertEquals(carLot1, v1Exit.getLotId());
    assertEquals(timestamp1, v1Exit.getTimestamp());
    assertEquals(car, v1Exit.getVehicleTypeEnum());
  }
}
