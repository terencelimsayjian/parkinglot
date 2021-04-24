package com.terence.parking.command;

import com.terence.parking.parkinglot.ParkingLot;
import com.terence.parking.parkinglot.ParkingSummary;
import com.terence.parking.validator.ValidationException;
import com.terence.parking.validator.Validator;

import java.math.RoundingMode;

public class ExitCommand implements Command {
  @Override
  public void validate(String[] args) throws CommandValidationException {
    try {
      Validator.validateArgsLength(args, 3);
      Validator.validateTimestamp(args[2]);
    } catch (ValidationException e) {
      throw new CommandValidationException(e.getMessage());
    }
  }

  @Override
  public String execute(String[] args) {
    String vehicleNumber = args[1];
    String timestamp = args[2];

    long endingTimestamp = Long.parseLong(timestamp);
    ParkingSummary parkingSummary = ParkingLot.exit(vehicleNumber, endingTimestamp);
    return parkingSummary.getLotId() + " " + parkingSummary.getParkingFee().setScale(0, RoundingMode.HALF_UP);
  }
}
