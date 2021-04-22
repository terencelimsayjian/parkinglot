package com.terence.parking.feecalculation;

import java.math.BigDecimal;

public interface FeeCalculator {
  BigDecimal MINUTES_IN_HOUR = BigDecimal.valueOf(60);
  BigDecimal SECONDS_IN_MINUTE = BigDecimal.valueOf(60);

  BigDecimal calculate(long startTimeEpochSeconds, long endTimeEpochSeconds);
}
