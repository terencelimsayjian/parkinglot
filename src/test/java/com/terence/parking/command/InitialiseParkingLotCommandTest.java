package com.terence.parking.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitialiseParkingLotCommandTest {

  @Test
  void shouldThrowValidationExceptionIfLessThanTwoArguments() {

    Command initialiseParkingLotCommand = new InitialiseParkingLotCommand();

    String[] args = {"1"};
    CommandValidationException e =
        assertThrows(
            CommandValidationException.class, () -> initialiseParkingLotCommand.validate(args));
    assertEquals("Invalid number of arguments. Expected 2 but got 1.", e.getMessage());
  }

  @Test
  void shouldThrowValidationExceptionIfMoreThanTwoArguments() {
    Command initialiseParkingLotCommand = new InitialiseParkingLotCommand();

    String[] args = {"1", "1", "1"};
    CommandValidationException e =
        assertThrows(
            CommandValidationException.class, () -> initialiseParkingLotCommand.validate(args));
    assertEquals("Invalid number of arguments. Expected 2 but got 3.", e.getMessage());
  }

  @Test
  void shouldThrowValidationErrorIfFirstInputIsNotInteger() {
    Command initialiseParkingLotCommand = new InitialiseParkingLotCommand();
    String[] args = {"a", "b"};

    CommandValidationException e =
        assertThrows(
            CommandValidationException.class, () -> initialiseParkingLotCommand.validate(args));
    assertEquals("Invalid integer.", e.getMessage());
  }

  @Test
  void shouldThrowValidationErrorIfSecondInputIsNotInteger() {
    Command initialiseParkingLotCommand = new InitialiseParkingLotCommand();
    String[] args = {"1", "b"};

    CommandValidationException e =
        assertThrows(
            CommandValidationException.class, () -> initialiseParkingLotCommand.validate(args));
    assertEquals("Invalid integer.", e.getMessage());
  }
}
