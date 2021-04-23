package com.terence.parking;

import com.terence.parking.command.Command;
import com.terence.parking.command.CommandValidationException;
import com.terence.parking.command.EnterCommand;
import com.terence.parking.command.ExitCommand;
import com.terence.parking.command.InitialiseParkingLotCommand;
import com.terence.parking.parkinglot.ParkingLotNotInitialisedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ParkingSpaceApplication {

  public static final String ENTER = "ENTER";
  public static final String EXIT = "EXIT";

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Invalid input. Only one argument expected.");
      return;
    }

    String filePath = args[0];
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

      String line;
      int lineNumber = 1;
      while ((line = reader.readLine()) != null) {

        try {
          String output = processLine(line);

          if (!output.isBlank()) {
            System.out.println(output);
          }

          lineNumber++;
        } catch (CommandValidationException e) {
          System.out.println(displayLine(lineNumber) + e.getMessage());
          break;
        } catch (ParkingLotNotInitialisedException e) {
          System.out.println(displayLine(lineNumber) + "Parking Lot not initialised.");
          break;
        }
      }

    } catch (IOException e) {
      System.out.println("Something went wrong. Enter absolute path to file.");
    }
  }

  private static String processLine(String input) throws CommandValidationException {
    String[] split = input.split(" ");
    String userInstruction = split[0];

    Command command = null;
    if (isInt(userInstruction)) {
      command = new InitialiseParkingLotCommand();
    } else if (userInstruction.equalsIgnoreCase(ENTER)) {
      command = new EnterCommand();
    } else if (userInstruction.equalsIgnoreCase(EXIT)) {
      command = new ExitCommand();
    }

    command.validate(split);
    return command.execute(split);
  }

  private static boolean isInt(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  private static String displayLine(int lineNumber) {
    return "Line " + lineNumber + ": ";
  }
}
