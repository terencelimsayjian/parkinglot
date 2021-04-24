package com.terence.parking.validator;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorTest {

  @Nested
  class ArgsLengthValidator {
    @Test
    void shouldHandleNullInput() {
      ValidationException e =
          assertThrows(ValidationException.class, () -> Validator.validateArgsLength(null, 1));

      assertEquals("Arguments cannot be null", e.getMessage());
    }

    @Test
    void shouldThrowExceptionIfLessThanCorrectArgs() {
      String[] input = new String[] {"1"};
      ValidationException e =
          assertThrows(ValidationException.class, () -> Validator.validateArgsLength(input, 2));

      assertEquals("Invalid number of arguments. Expected 2 but got 1.", e.getMessage());
    }

    @Test
    void shouldThrowExceptionIfMoreThanCorrectArgs() {
      String[] input = new String[] {"1", "1", "1"};
      ValidationException e =
          assertThrows(ValidationException.class, () -> Validator.validateArgsLength(input, 2));

      assertEquals("Invalid number of arguments. Expected 2 but got 3.", e.getMessage());
    }

    @Test
    void shouldNotThrowExceptionIfCorrectArgs() {
      String[] input = new String[] {"1", "1", "1"};
      assertDoesNotThrow(() -> Validator.validateArgsLength(input, 3));
    }
  }

  @Nested
  class TimestampValidator {
    @Test
    void shouldHandleNullInput() {
      ValidationException e =
          assertThrows(ValidationException.class, () -> Validator.validateTimestamp(null));

      assertEquals("Timestamp cannot be null.", e.getMessage());
    }

    @Test
    void shouldThrowValidationExceptionIfCannotBeParsedToLong() {
      ValidationException e =
          assertThrows(ValidationException.class, () -> Validator.validateTimestamp("123456H"));

      assertEquals("Invalid timestamp format.", e.getMessage());
    }

    @Test
    void shouldNotThrowValidationExceptionIfValidLong() {
      assertDoesNotThrow(() -> Validator.validateTimestamp("12346"));
    }
  }

  @Nested
  class IntegerValidator {
    @Test
    void shouldHandleNullInput() {
      ValidationException e =
          assertThrows(ValidationException.class, () -> Validator.validateInteger(null));

      assertEquals("Integer cannot be null.", e.getMessage());
    }

    @Test
    void shouldThrowValidationExceptionIfCannotBeParsedToLong() {
      ValidationException e =
          assertThrows(ValidationException.class, () -> Validator.validateInteger("123456H"));

      assertEquals("Invalid integer.", e.getMessage());
    }

    @Test
    void shouldNotThrowValidationExceptionIfValidInteger() {
      assertDoesNotThrow(() -> Validator.validateInteger("12346"));
    }
  }
}
