package com.terence.parking;

import com.terence.parking.parkinglot.BaseVehicleParkingLot;
import com.terence.parking.parkinglot.ParkingLotFullException;
import com.terence.parking.parkinglot.ParkingLot;
import com.terence.parking.parkinglot.ParkingSpotInfo;
import com.terence.parking.parkinglot.VehicleType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParkingSpaceApplication {

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
          new BaseVehicleParkingLot(Integer.parseInt(firstLineArray[0]), "CarLot", VehicleType.CAR),
          new BaseVehicleParkingLot(Integer.parseInt(firstLineArray[1]), "MotorcycleLot", VehicleType.MOTORCYCLE));

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

    if (command.equals("Enter")) {
      String vehicleType = s[1];
      String vehicleNumber = s[2];
      String timestamp = s[3];

      VehicleType vehicle = VehicleType.valueOf(vehicleType.toUpperCase());

      String lotId = null;
      try {
        lotId = ParkingLot.park(vehicleNumber, timestamp, vehicle);
        output = "Accept " + lotId;
      } catch (ParkingLotFullException e) {
        output = "Reject";
      }

    } else if (command.equals("Exit")) {
      String vehicleNumber = s[1];
      String timestamp = s[2];

      ParkingSpotInfo parkingSpotInfo = ParkingLot.exit(vehicleNumber);
      String startinTimestamp = parkingSpotInfo.getTimestamp();
      String lotId = parkingSpotInfo.getLotId();
      VehicleType vehicleTypeEnum = parkingSpotInfo.getVehicleTypeEnum();

      long startingTimestamp = Long.parseLong(startinTimestamp);
      long endingTimestamp = Long.parseLong(timestamp);
      long numberOfHours = ((endingTimestamp - startingTimestamp) / 60) / 60 + 1;
      long charge = 0;

      switch (vehicleTypeEnum) {
        case CAR:
          charge = numberOfHours * 2;
          break;
        case MOTORCYCLE:
          charge = numberOfHours * 1;
          break;
        default:
          // Throw Unsupported vehicle exception
      }

      output = lotId + " " + charge;
    }

    return output;
  }
}
