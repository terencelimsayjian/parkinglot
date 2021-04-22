package com.terence.parking.parkinglot;

import com.terence.parking.feecalculation.FeeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BaseVehicleParkingLotTest {

  private FeeCalculator mockFeeCalculator;

  @BeforeEach
  void setUp() {
    mockFeeCalculator =
        new FeeCalculator() {
          @Override
          public BigDecimal calculate(long startTimeEpochSeconds, long endTimeEpochSeconds) {
            return BigDecimal.TEN;
          }
        };
  }

  @Test
  void shouldReturnIdOfCarWhenParkedSuccessfully() throws Exception {
    BaseVehicleParkingLot carVehicleParkingLot =
        new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR, mockFeeCalculator);

    String firstParkedCarSpace = carVehicleParkingLot.park("SGX1234A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
  }

  @Test
  void shouldReturnIdOfSecondCarWhenParkedSuccessfully() throws Exception {
    BaseVehicleParkingLot carVehicleParkingLot =
        new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR, mockFeeCalculator);

    String firstParkedCarSpace = carVehicleParkingLot.park("SGX1234A", "1613541902");
    String secondParkedCarSpace = carVehicleParkingLot.park("WWW5555A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
    assertEquals("CarLot2", secondParkedCarSpace);
  }

  @Test
  void shouldThrowCarParkFullExceptionIfCarLotsAreFull() throws Exception {
    BaseVehicleParkingLot carVehicleParkingLot =
        new BaseVehicleParkingLot(1, "CarLot", VehicleType.CAR, mockFeeCalculator);

    String firstParkedCarSpace = carVehicleParkingLot.park("SGX1234A", "1613541902");
    assertThrows(
        ParkingLotFullException.class, () -> carVehicleParkingLot.park("WWW5555A", "1613541902"));

    assertEquals("CarLot1", firstParkedCarSpace);
  }

  @Test
  void shouldReturnIdIdOfSecondCarWhenParkedSuccessfully() throws Exception {
    BaseVehicleParkingLot carVehicleParkingLot =
        new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR, mockFeeCalculator);

    String firstParkedCarSpace = carVehicleParkingLot.park("SGX1234A", "1613541902");
    String secondParkedCarSpace = carVehicleParkingLot.park("WWW5555A", "1613541902");

    assertEquals("CarLot1", firstParkedCarSpace);
    assertEquals("CarLot2", secondParkedCarSpace);
  }

  @Test
  void shouldReturnTimestampWhenExitingCarpark() throws Exception {
    BaseVehicleParkingLot carVehicleParkingLot =
        new BaseVehicleParkingLot(3, "CarLot", VehicleType.CAR, mockFeeCalculator);

    String firstParkedCarSpace = carVehicleParkingLot.park("SGX1234A", "1613541902");
    assertEquals("CarLot1", firstParkedCarSpace);

    ParkingSummary parkingSummary = carVehicleParkingLot.exit("SGX1234A", 1613541902);
    assertEquals("1613541902", parkingSummary.getTimestamp());
    assertEquals("CarLot1", parkingSummary.getLotId());
    assertEquals(0, parkingSummary.getParkingFee().compareTo(BigDecimal.TEN));
  }
}
