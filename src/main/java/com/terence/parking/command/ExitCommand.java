package com.terence.parking.command;

public class ExitCommand implements Command {
  @Override
  public void validate(String[] args) throws CommandValidationException {}

  @Override
  public String execute(String[] args) {
    return null;
  }
}
