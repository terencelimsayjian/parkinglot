package com.terence.parking.feecalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HourlyFeeCalculator {
  public static final BigDecimal MINUTES_IN_HOUR = BigDecimal.valueOf(60);
  public static final BigDecimal SECONDS_IN_MINUTE = BigDecimal.valueOf(60);
  private final BigDecimal hourlyRate;

  public HourlyFeeCalculator(BigDecimal hourlyRate) {
    this.hourlyRate = hourlyRate;
  }

  public BigDecimal calculate(long startTimeEpochSeconds, long endTimeEpochSeconds) {

    BigDecimal start = BigDecimal.valueOf(startTimeEpochSeconds);
    BigDecimal end = BigDecimal.valueOf(endTimeEpochSeconds);

    BigDecimal numMinutes = end.subtract(start).divide(SECONDS_IN_MINUTE, 4, RoundingMode.HALF_UP);
    BigDecimal chargeableHours =
        numMinutes.divide(MINUTES_IN_HOUR, 4, RoundingMode.HALF_UP).setScale(0, RoundingMode.UP);

    return chargeableHours.multiply(hourlyRate);
  }
}
