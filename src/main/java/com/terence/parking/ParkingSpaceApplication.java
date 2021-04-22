package com.terence.parking;

import com.terence.parking.repository.ParkingLotException;
import com.terence.parking.repository.ParkingLotRepository;

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
      ParkingLotRepository.initialise(
          Integer.parseInt(firstLineArray[0]), Integer.parseInt(firstLineArray[1]));

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

      String lotId = null;
      try {
        lotId = ParkingLotRepository.park(vehicleType, vehicleNumber, timestamp);
        output = "Accept " + lotId;
      } catch (ParkingLotException e) {
        output = "Reject";
      }

    } else if (command.equals("Exit")) {

    }

    return output;
  }
}
