package com.terence.parking.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest {


  @Test
  void shouldThrowValidationExceptionIfLessThanThreeArguments() {

    Command exitCommand = new ExitCommand();

    String[] args = {"EXIT"};
    CommandValidationException e =
        assertThrows(CommandValidationException.class, () -> exitCommand.validate(args));
    assertEquals("Invalid number of arguments. Expected 3 but got 1.", e.getMessage());
  }

  @Test
  void shouldThrowValidationExceptionIfMoreThanThreeArguments() {
    Command exitCommand = new ExitCommand();

    String[] args = {"EXIT", "motorcycle", "SGX1234A", "1613541902"};
    CommandValidationException e =
        assertThrows(CommandValidationException.class, () -> exitCommand.validate(args));
    assertEquals("Invalid number of arguments. Expected 3 but got 4.", e.getMessage());
  }

  @Test
  void shouldThrowValidationErrorIfInvalidTimestampFormat() {
    Command exitCommand = new ExitCommand();
    String[] args = {"EXIT", "SGX1234A", "invalid-timestamp"};

    CommandValidationException e =
        assertThrows(CommandValidationException.class, () -> exitCommand.validate(args));
    assertEquals("Invalid timestamp format.", e.getMessage());
  }

}
