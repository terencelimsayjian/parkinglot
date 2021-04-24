package com.terence.parking.validator;

public class Validator {
  private Validator() {
  }

  public static void validateArgsLength(String[] args, int expected) throws ValidationException {
    if (args == null) {
      throw new ValidationException("Arguments cannot be null");
    }

    if (args.length != expected) {
      throw new ValidationException(
          "Invalid number of arguments. Expected " + expected + " but got " + (args.length) + ".");
    }
  }

  public static void validateTimestamp(String timestampSecondsFromEpoch) throws ValidationException {
    if (timestampSecondsFromEpoch == null) {
      throw new ValidationException("Timestamp cannot be null.");
    }

    try {
      Long.valueOf(timestampSecondsFromEpoch);
    } catch (NumberFormatException e) {
      throw new ValidationException("Invalid timestamp format.");
    }
  }

  public static void validateInteger(String intString) throws ValidationException {
    if (intString == null) {
      throw new ValidationException("Integer cannot be null.");
    }

    try {
      Integer.valueOf(intString);
    } catch (NumberFormatException e) {
      throw new ValidationException("Invalid integer.");
    }
  }
}
