package com.terence.parking.command;

import com.terence.parking.parkinglot.ParkingLot;
import com.terence.parking.parkinglot.ParkingLotFullException;
import com.terence.parking.parkinglot.VehicleType;
import com.terence.parking.validator.Validator;
import com.terence.parking.validator.ValidationException;

public class EnterCommand implements Command {
  @Override
  public void validate(String[] args) throws CommandValidationException {
    try {
      Validator.validateArgsLength(args, 4);
      Validator.validateTimestamp(args[3]);
    } catch (ValidationException e) {
      throw new CommandValidationException(e.getMessage());
    }
  }

  @Override
  public String execute(String[] args) {
    String vehicleType = args[1];
    String vehicleNumber = args[2];
    String timestamp = args[3];

    VehicleType vehicle = VehicleType.valueOf(vehicleType.toUpperCase());

    try {
      String lotId = ParkingLot.park(vehicleNumber, timestamp, vehicle);
      return "Accept " + lotId;
    } catch (ParkingLotFullException e) {
      return "Reject";
    }
  }
}
