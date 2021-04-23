package com.terence.parking.command;

import com.terence.parking.parkinglot.ParkingLot;
import com.terence.parking.parkinglot.ParkingSummary;

public class ExitCommand implements Command {
  @Override
  public void validate(String[] args) throws CommandValidationException {}

  @Override
  public String execute(String[] args) {
    String vehicleNumber = args[1];
    String timestamp = args[2];


    long endingTimestamp = Long.parseLong(timestamp);
    ParkingSummary parkingSummary = ParkingLot.exit(vehicleNumber, endingTimestamp);

    return parkingSummary.getLotId() + " " + parkingSummary.getParkingFee().setScale(0);
  }
}
