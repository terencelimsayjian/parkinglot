package com.terence.parking.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CommandExecutorTest {

  CommandExecutor commandExecutor;
  Command initialiseParkingLotCommand;
  Command enterCommand;
  Command exitCommand;

  @BeforeEach
  void setUp() {
    initialiseParkingLotCommand = mock(Command.class);
    enterCommand = mock(Command.class);
    exitCommand = mock(Command.class);
    commandExecutor = new CommandExecutor(initialiseParkingLotCommand, enterCommand, exitCommand);
  }

  @Test
  void shouldCallInitialiseParkingLotCommand() throws Exception {
    String input = "4 5";
    commandExecutor.execute(input);

    String[] args = input.split(" ");

    verify(initialiseParkingLotCommand, times(1)).validate(args);
    verify(initialiseParkingLotCommand, times(1)).execute(args);

    verify(enterCommand, never()).validate(any());
    verify(enterCommand, never()).execute(any());
    verify(exitCommand, never()).validate(any());
    verify(exitCommand, never()).execute(any());
  }

  @Test
  void shouldCallEnterCommand() throws Exception {
    String input = "Enter Something";
    commandExecutor.execute(input);

    String[] args = input.split(" ");

    verify(enterCommand, times(1)).validate(args);
    verify(enterCommand, times(1)).execute(args);

    verify(initialiseParkingLotCommand, never()).validate(any());
    verify(initialiseParkingLotCommand, never()).execute(any());
    verify(exitCommand, never()).validate(any());
    verify(exitCommand, never()).execute(any());
  }

  @Test
  void shouldCallExitCommand() throws Exception {
    String input = "Exit Something";
    commandExecutor.execute(input);

    String[] args = input.split(" ");

    verify(exitCommand, times(1)).validate(args);
    verify(exitCommand, times(1)).execute(args);

    verify(initialiseParkingLotCommand, never()).validate(any());
    verify(initialiseParkingLotCommand, never()).execute(any());
    verify(enterCommand, never()).validate(any());
    verify(enterCommand, never()).execute(any());
  }

  @Test
  void shouldThrowExceptionIfUnrecognisedCommand() throws Exception {
    String input = "Unrecognised";

    CommandValidationException e = assertThrows(CommandValidationException.class, () -> commandExecutor.execute(input));
    assertEquals("Invalid command.", e.getMessage());

    verify(exitCommand, never()).validate(any());
    verify(exitCommand, never()).execute(any());
    verify(initialiseParkingLotCommand, never()).validate(any());
    verify(initialiseParkingLotCommand, never()).execute(any());
    verify(enterCommand, never()).validate(any());
    verify(enterCommand, never()).execute(any());
  }
}
