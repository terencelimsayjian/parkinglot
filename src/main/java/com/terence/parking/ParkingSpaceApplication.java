package com.terence.parking;

import com.terence.parking.feecalculation.FeeCalculator;
import com.terence.parking.feecalculation.HourlyFeeCalculator;
import com.terence.parking.parkinglot.BaseVehicleParkingLot;
import com.terence.parking.parkinglot.ParkingLotFullException;
import com.terence.parking.parkinglot.ParkingLot;
import com.terence.parking.parkinglot.ParkingSummary;
import com.terence.parking.parkinglot.VehicleType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class ParkingSpaceApplication {

  public static final String ENTER = "ENTER";
  public static final String EXIT = "EXIT";
  public static final String CAR_LOT_ID_PREFIX = "CarLot";
  public static final String MOTORCYCLE_LOT_ID_PREFIX = "MotorcycleLot";

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Invalid input. Only one argument expected.");
      return;
    }

    String filePath = args[0];
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

      String firstLine = reader.readLine();
      String[] firstLineArray = firstLine.split(" ");

      ParkingLot.initialise(
          new BaseVehicleParkingLot(
              Integer.parseInt(firstLineArray[0]),
              CAR_LOT_ID_PREFIX,
              VehicleType.CAR,
              new HourlyFeeCalculator(BigDecimal.valueOf(2))),
          new BaseVehicleParkingLot(
              Integer.parseInt(firstLineArray[1]),
              MOTORCYCLE_LOT_ID_PREFIX,
              VehicleType.MOTORCYCLE,
              new HourlyFeeCalculator(BigDecimal.ONE)));

      reader.lines().map(ParkingSpaceApplication::processLine).forEach(System.out::println);

    } catch (IOException e) {
      System.out.println("Something went wrong. Enter absolute path to file.");
      return;
    }
  }

  private static String processLine(String input) {
    String[] s = input.split(" ");
    String command = s[0];
    String output = "";

    if (command.equalsIgnoreCase(ENTER)) {
      String vehicleType = s[1];
      String vehicleNumber = s[2];
      String timestamp = s[3];

      VehicleType vehicle = VehicleType.valueOf(vehicleType.toUpperCase());

      try {
        String lotId = ParkingLot.park(vehicleNumber, timestamp, vehicle);
        output = "Accept " + lotId;
      } catch (ParkingLotFullException e) {
        output = "Reject";
      }

    } else if (command.equalsIgnoreCase(EXIT)) {
      String vehicleNumber = s[1];
      String timestamp = s[2];

      long endingTimestamp = Long.parseLong(timestamp);
      ParkingSummary parkingSummary = ParkingLot.exit(vehicleNumber, endingTimestamp);

      output = parkingSummary.getLotId() + " " + parkingSummary.getParkingFee().setScale(0);
    }

    return output;
  }
}
