package com.terence.parking.command;

import com.terence.parking.feecalculation.HourlyFeeCalculator;
import com.terence.parking.parkinglot.BaseVehicleParkingLot;
import com.terence.parking.parkinglot.ParkingLot;
import com.terence.parking.parkinglot.VehicleType;

import java.math.BigDecimal;

public class InitialiseParkingLotCommand implements Command {
  public static final String CAR_LOT_ID_PREFIX = "CarLot";
  public static final String MOTORCYCLE_LOT_ID_PREFIX = "MotorcycleLot";

  @Override
  public void validate(String[] args) throws CommandValidationException {}

  @Override
  public String execute(String[] args) {
    int carCapacity = Integer.parseInt(args[0]);
    int motorcycleCapacity = Integer.parseInt(args[1]);

    ParkingLot.initialise(
        new BaseVehicleParkingLot(
            carCapacity,
            CAR_LOT_ID_PREFIX,
            VehicleType.CAR,
            new HourlyFeeCalculator(BigDecimal.valueOf(2))),
        new BaseVehicleParkingLot(
            motorcycleCapacity,
            MOTORCYCLE_LOT_ID_PREFIX,
            VehicleType.MOTORCYCLE,
            new HourlyFeeCalculator(BigDecimal.ONE)));

    return "";
  }
}