package com.terence.parking.command;

public class CommandExecutor {
  public static final String ENTER = "ENTER";
  public static final String EXIT = "EXIT";
  private Command initialiseParkingLotCommand;
  private Command enterCommand;
  private Command exitCommand;

  public CommandExecutor(
      Command initialiseParkingLotCommand, Command enterCommand, Command exitCommand) {
    this.initialiseParkingLotCommand = initialiseParkingLotCommand;
    this.enterCommand = enterCommand;
    this.exitCommand = exitCommand;
  }

  public String execute(String input) throws CommandValidationException {
    String[] split = input.split(" ");
    String userInstruction = split[0];

    Command command;
    if (isInt(userInstruction)) {
      command = initialiseParkingLotCommand;
    } else if (userInstruction.equalsIgnoreCase(ENTER)) {
      command = enterCommand;
    } else if (userInstruction.equalsIgnoreCase(EXIT)) {
      command = exitCommand;
    } else {
      throw new CommandValidationException("Invalid command.");
    }

    command.validate(split);
    return command.execute(split);
  }

  private boolean isInt(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
