package com.terence.parking.command;

import com.terence.parking.feecalculation.FeeCalculator;
import com.terence.parking.parkinglot.BaseVehicleParkingLot;
import com.terence.parking.parkinglot.ParkingLot;
import com.terence.parking.parkinglot.VehicleType;
import com.terence.parking.validator.ValidationException;
import com.terence.parking.validator.Validator;

public class InitialiseParkingLotCommand implements Command {
  public static final String CAR_LOT_ID_PREFIX = "CarLot";
  public static final String MOTORCYCLE_LOT_ID_PREFIX = "MotorcycleLot";
  private final FeeCalculator carFeeCalculator;
  private final FeeCalculator motorcycleFeeCalculator;

  public InitialiseParkingLotCommand(FeeCalculator carFeeCalculator, FeeCalculator motorcycleFeeCalculator) {
    this.carFeeCalculator = carFeeCalculator;
    this.motorcycleFeeCalculator = motorcycleFeeCalculator;
  }

  @Override
  public void validate(String[] args) throws CommandValidationException {
    try {
      Validator.validateArgsLength(args, 2);
      Validator.validateInteger(args[0]);
      Validator.validateInteger(args[1]);
    } catch (ValidationException e) {
      throw new CommandValidationException(e.getMessage());
    }
  }

  @Override
  public String execute(String[] args) {
    int carCapacity = Integer.parseInt(args[0]);
    int motorcycleCapacity = Integer.parseInt(args[1]);

    ParkingLot.initialise(
        new BaseVehicleParkingLot(
            carCapacity,
            CAR_LOT_ID_PREFIX,
            VehicleType.CAR,
            carFeeCalculator),
        new BaseVehicleParkingLot(
            motorcycleCapacity,
            MOTORCYCLE_LOT_ID_PREFIX,
            VehicleType.MOTORCYCLE,
            motorcycleFeeCalculator));

    return "";
  }
}
