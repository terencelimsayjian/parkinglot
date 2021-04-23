package com.terence.parking.parkinglot;

import com.terence.parking.feecalculation.HourlyFeeCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ParkingLotTest {

  VehicleParkingLot carLot;
  VehicleParkingLot motorcycleLot;

  @BeforeEach
  void setUp() {
    carLot = mock(VehicleParkingLot.class);
    motorcycleLot = mock(VehicleParkingLot.class);
  }

  @AfterEach
  void tearDown() {
    ParkingLot.resetInstance();
  }

  @Test
  void shouldCallParkOnCorrectVehicleParkingLot() throws Exception {
    ParkingLot.initialise(carLot, motorcycleLot);

    ParkingLot.park("carNumber", "carTimestamp", VehicleType.CAR);
    ParkingLot.park("motorcycleNumber", "motorcycleTimestamp", VehicleType.MOTORCYCLE);

    verify(carLot, times(1)).park("carNumber", "carTimestamp");
    verify(motorcycleLot, times(1)).park("motorcycleNumber", "motorcycleTimestamp");
  }

  @Test
  void shouldCallExitOnCorrectVehicleParkingLot() throws Exception {
    ParkingLot.initialise(carLot, motorcycleLot);

    ParkingLot.park("carNumber", "carTimestamp", VehicleType.CAR);
    ParkingLot.exit("carNumber", 88888888L);

    verify(carLot, times(1)).park("carNumber", "carTimestamp");
    verify(carLot, times(1)).exit("carNumber", 88888888L);

    verify(motorcycleLot, never()).park(any(), any());
    verify(motorcycleLot, never()).exit(anyString(), anyLong());
  }

  @Test
  void shouldThrowParkingLotNotInitialisedExceptionWhenParkingInUninitialisedParkingLot() {
    assertThrows(
        ParkingLotNotInitialisedException.class,
        () -> ParkingLot.park("carNumber", "carTimestamp", VehicleType.CAR));
  }

  @Test
  void shouldThrowParkingLotNotInitialisedExceptionWhenExitingUninitialisedParkingLot() {
    assertThrows(
        ParkingLotNotInitialisedException.class, () -> ParkingLot.exit("carNumber", 88888888L));
  }
}
