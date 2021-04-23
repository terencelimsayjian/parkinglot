package com.terence.parking.command;

import com.terence.parking.parkinglot.ParkingLot;
import com.terence.parking.parkinglot.ParkingLotFullException;
import com.terence.parking.parkinglot.VehicleType;

public class EnterCommand implements Command {

  @Override
  public void validate(String[] args) throws CommandValidationException {
    if (args.length != 4) {
      throw new CommandValidationException("Invalid number of arguments. Expected 4 but got " + (args.length) + ".");
    }

    try {
      String timestamp = args[3];
      Long.valueOf(timestamp);
    } catch (NumberFormatException e) {
      throw new CommandValidationException("Invalid timestamp format.");
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
