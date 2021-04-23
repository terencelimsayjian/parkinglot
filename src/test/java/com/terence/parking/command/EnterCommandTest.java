package com.terence.parking.command;

import com.terence.parking.parkinglot.ParkingLot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnterCommandTest {

  @Nested
  class Validate {
    @Test
    void shouldThrowValidationExceptionIfLessThanThreeArguments() {

      Command enterCommand = new EnterCommand();

      String[] args = { "ENTER" };
      CommandValidationException e = assertThrows(CommandValidationException.class, () -> enterCommand.validate(args));
      assertEquals("Invalid number of arguments. Expected 4 but got 1.", e.getMessage());
    }

    @Test
    void shouldThrowValidationExceptionIfMoreThanThreeArguments() {
      Command enterCommand = new EnterCommand();

      String[] args = { "ENTER", "motorcycle", "SGX1234A", "1613541902", "extra argument" };
      CommandValidationException e = assertThrows(CommandValidationException.class, () -> enterCommand.validate(args));
      assertEquals("Invalid number of arguments. Expected 4 but got 5.", e.getMessage());
    }

    @Test
    void shouldThrowValidationErrorIfInvalidTimestampFormat() {
      Command enterCommand = new EnterCommand();
      String[] args = { "ENTER", "motorcycle", "SGX1234A", "invalid-timestamp" };

      CommandValidationException e = assertThrows(CommandValidationException.class, () -> enterCommand.validate(args));
      assertEquals("Invalid timestamp format.", e.getMessage());
    }


  }

}
