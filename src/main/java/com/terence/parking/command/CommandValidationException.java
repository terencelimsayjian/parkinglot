package com.terence.parking.command;

public class CommandValidationException extends Exception {
  public CommandValidationException(String message) {
    super(message);
  }
}
