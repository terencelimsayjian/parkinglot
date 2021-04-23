package com.terence.parking.command;

public interface Command {
  void validate(String[] args) throws CommandValidationException;

  String execute(String[] args);
}
