package com.vendorlion.library;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateHelper {
  public static LocalDateTime createLocalDateTimeFrom(Date date) {
    return LocalDateTime.from(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
  }
}
