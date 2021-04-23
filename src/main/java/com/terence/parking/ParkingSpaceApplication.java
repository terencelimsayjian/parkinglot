package com.terence.parking;

import com.terence.parking.command.Command;
import com.terence.parking.command.CommandValidationException;
import com.terence.parking.command.EnterCommand;
import com.terence.parking.command.ExitCommand;
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

      int carCapacity = Integer.parseInt(firstLineArray[0]);
      int motorcycleCapacity = Integer.parseInt(firstLineArray[1]);

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

      reader.lines().map(ParkingSpaceApplication::processLine).forEach(System.out::println);

    } catch (IOException e) {
      System.out.println("Something went wrong. Enter absolute path to file.");
    }
  }

  private static String processLine(String input) {
    String[] s = input.split(" ");
    String userInstruction = s[0];

    Command command = null;
    if (userInstruction.equalsIgnoreCase(ENTER)) {
      command = new EnterCommand();
    } else if (userInstruction.equalsIgnoreCase(EXIT)) {
      command = new ExitCommand();
    }

    try {
      command.validate(s);
      return command.execute(s);
    } catch (CommandValidationException e) {
      return e.getMessage();
    }
  }
}
