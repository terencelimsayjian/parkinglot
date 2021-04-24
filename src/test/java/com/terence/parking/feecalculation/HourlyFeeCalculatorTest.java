package com.terence.parking.feecalculation;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HourlyFeeCalculatorTest {

  @Test
  void shouldNotRoundExactlyOnTheHour() {
    HourlyFeeCalculator calculator = new HourlyFeeCalculator(new BigDecimal("1.5"));

    BigDecimal result = calculator.calculate(0, 3600);

    assertEquals(0, result.compareTo(new BigDecimal("1.5")));
  }

  @Test
  void shouldNotBeSubjectedToLongDivisionTruncation() {
    HourlyFeeCalculator calculator = new HourlyFeeCalculator(new BigDecimal("1.5"));

    BigDecimal result = calculator.calculate(0, 3601);

    assertEquals(0, result.compareTo(new BigDecimal("3")));
  }

  @Test
  void shouldCharge1HourEvenIfDurationIs1Second() {
    HourlyFeeCalculator calculator = new HourlyFeeCalculator(new BigDecimal("1.3"));

    BigDecimal result = calculator.calculate(0, 1);

    assertEquals(0, result.compareTo(new BigDecimal("1.3")));
  }

  @Test
  void shouldRoundUpToNearestHour() {
    HourlyFeeCalculator calculator = new HourlyFeeCalculator(new BigDecimal("1.5"));

    BigDecimal result = calculator.calculate(0, 3660);

    assertEquals(0, result.compareTo(new BigDecimal("3")));
  }
}
