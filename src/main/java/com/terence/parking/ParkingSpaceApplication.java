package com.terence.parking;

import com.terence.parking.command.CommandExecutor;
import com.terence.parking.command.CommandValidationException;
import com.terence.parking.command.EnterCommand;
import com.terence.parking.command.ExitCommand;
import com.terence.parking.command.InitialiseParkingLotCommand;
import com.terence.parking.feecalculation.HourlyFeeCalculator;
import com.terence.parking.parkinglot.ParkingLotNotInitialisedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class ParkingSpaceApplication {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Invalid input. Only one argument expected.");
      return;
    }

    CommandExecutor commandExecutor = buildCommandExecutor();

    String filePath = args[0];
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

      String line;
      int lineNumber = 1;
      while ((line = reader.readLine()) != null) {

        try {
          String output = commandExecutor.execute(line);

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

  private static CommandExecutor buildCommandExecutor() {
    InitialiseParkingLotCommand initialiseParkingLotCommand = new InitialiseParkingLotCommand(
        new HourlyFeeCalculator(BigDecimal.valueOf(2)),
        new HourlyFeeCalculator(BigDecimal.valueOf(1)));

    return new CommandExecutor(
        initialiseParkingLotCommand,
        new EnterCommand(),
        new ExitCommand());
  }

  private static String displayLine(int lineNumber) {
    return "Line " + lineNumber + ": ";
  }
}
