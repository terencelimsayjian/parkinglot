package com.terence.parking.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EnterCommandTest {

  @Test
  void shouldThrowValidationExceptionIfLessThanFourArguments() {

    Command enterCommand = new EnterCommand();

    String[] args = {"ENTER"};
    CommandValidationException e =
        assertThrows(CommandValidationException.class, () -> enterCommand.validate(args));
    assertEquals("Invalid number of arguments. Expected 4 but got 1.", e.getMessage());
  }

  @Test
  void shouldThrowValidationExceptionIfMoreThanFourArguments() {
    Command enterCommand = new EnterCommand();

    String[] args = {"ENTER", "motorcycle", "SGX1234A", "1613541902", "extra argument"};
    CommandValidationException e =
        assertThrows(CommandValidationException.class, () -> enterCommand.validate(args));
    assertEquals("Invalid number of arguments. Expected 4 but got 5.", e.getMessage());
  }

  @Test
  void shouldThrowValidationErrorIfInvalidTimestampFormat() {
    Command enterCommand = new EnterCommand();
    String[] args = {"ENTER", "motorcycle", "SGX1234A", "invalid-timestamp"};

    CommandValidationException e =
        assertThrows(CommandValidationException.class, () -> enterCommand.validate(args));
    assertEquals("Invalid timestamp format.", e.getMessage());
  }
}
