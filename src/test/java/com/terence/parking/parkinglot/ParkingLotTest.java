package com.terence.parking.parkinglot;

import com.terence.parking.feecalculation.HourlyFeeCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {

  @Test
  void shouldHandleMultipleVehiclesParkingAndExiting() throws Exception {
    ParkingLot.initialise(
        new BaseVehicleParkingLot(
            3, "CarLot", VehicleType.CAR, new HourlyFeeCalculator(BigDecimal.valueOf(2))),
        new BaseVehicleParkingLot(
            3,
            "MotorcycleLot",
            VehicleType.MOTORCYCLE,
            new HourlyFeeCalculator(BigDecimal.valueOf(1))));

    String carpark1 = ParkingLot.park("V1", "1613541001", VehicleType.CAR);
    String motorcyclePark3 = ParkingLot.park("V3", "1613541003", VehicleType.MOTORCYCLE);

    ParkingSummary v1Exit = ParkingLot.exit("V1", 1613541902);
    ParkingSummary v3Exit = ParkingLot.exit("V3", 1613541902);

    String carpark2 = ParkingLot.park("V2", "1613541002", VehicleType.CAR);
    String motorcyclePark4 = ParkingLot.park("V4", "1613541004", VehicleType.MOTORCYCLE);

    assertEquals("CarLot1", carpark1);
    assertEquals("MotorcycleLot1", motorcyclePark3);

    assertParkingSummaryCorrect(v1Exit, "CarLot1", "1613541001", VehicleType.CAR);
    assertParkingSummaryCorrect(v3Exit, "MotorcycleLot1", "1613541003", VehicleType.MOTORCYCLE);

    assertEquals("CarLot1", carpark2);
    assertEquals("MotorcycleLot1", motorcyclePark4);
  }

  @Test
  void shouldBeAbleToParkCarsEvenIfMotorcycleCarParkIsFull() throws Exception {
    ParkingLot.initialise(
        new BaseVehicleParkingLot(
            3, "CarLot", VehicleType.CAR, new HourlyFeeCalculator(BigDecimal.valueOf(2))),
        new BaseVehicleParkingLot(
            3,
            "MotorcycleLot",
            VehicleType.MOTORCYCLE,
            new HourlyFeeCalculator(BigDecimal.valueOf(1))));

    String motorcyclePark1 = ParkingLot.park("V1", "1613541001", VehicleType.MOTORCYCLE);
    String motorcyclePark2 = ParkingLot.park("V2", "1613541002", VehicleType.MOTORCYCLE);
    String motorcyclePark3 = ParkingLot.park("V3", "1613541003", VehicleType.MOTORCYCLE);
    assertThrows(
        ParkingLotFullException.class,
        () -> ParkingLot.park("V3", "timestamp3", VehicleType.MOTORCYCLE));

    assertEquals("MotorcycleLot1", motorcyclePark1);
    assertEquals("MotorcycleLot2", motorcyclePark2);
    assertEquals("MotorcycleLot3", motorcyclePark3);

    String carpark1 = ParkingLot.park("V1", "1613541001", VehicleType.CAR);
    String carpark2 = ParkingLot.park("V2", "1613541002", VehicleType.CAR);

    assertEquals("CarLot1", carpark1);
    assertEquals("CarLot2", carpark2);
  }

  private void assertParkingSummaryCorrect(
      ParkingSummary v1Exit, String carLot1, String timestamp1, VehicleType car) {
    assertEquals(carLot1, v1Exit.getLotId());
    assertEquals(timestamp1, v1Exit.getTimestamp());
    assertEquals(car, v1Exit.getVehicleTypeEnum());
  }
}
