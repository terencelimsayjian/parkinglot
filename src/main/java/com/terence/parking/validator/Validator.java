package com.terence.parking.validator;

public class Validator {
  public static void validateArgsLength(String[] args, int expected) throws ValidationException {
    if (args.length != expected) {
      throw new ValidationException(
          "Invalid number of arguments. Expected " + expected + " but got " + (args.length) + ".");
    }
  }

  public static void validateTimestamp(String timestampSecondsFromEpoch) throws ValidationException {
    try {
      Long.valueOf(timestampSecondsFromEpoch);
    } catch (NumberFormatException e) {
      throw new ValidationException("Invalid timestamp format.");
    }
  }

  public static void validateInteger(String intString) throws ValidationException {
    try {
      Integer.valueOf(intString);
    } catch (NumberFormatException e) {
      throw new ValidationException("Invalid integer.");
    }
  }
}
