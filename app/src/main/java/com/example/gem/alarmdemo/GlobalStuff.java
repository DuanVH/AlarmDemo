package com.example.gem.alarmdemo;

import android.graphics.Color;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by gem on 8/7/18.
 */

public class GlobalStuff {
  private static final AtomicInteger seed = new AtomicInteger();

  public static int getFreshInt() {
    return seed.incrementAndGet();
  }

  public static final int CALL_PHONE_PERMISSION_REQUEST_CODE = 100;

  public static final int DEFAULT_TASK_TAG_COLOR = Color.TRANSPARENT;

  private static String createRandomString(int lengthMin, int lengthMax) {
    if (lengthMin == 0 && Math.random() < 0.5) {
      return null;
    }
    int length = lengthMin + (int) (Math.random() * (lengthMax - lengthMin));
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      double a = Math.random();
      if (a > 0.95) {
        stringBuilder.append((char) ('#' + (int) (Math.random() * 10)));
      } else if (a > 0.5) {
        stringBuilder.append((char) ('A' + (int) (Math.random() * 26)));
      } else if (a > 0.05) {
        stringBuilder.append((char) ('a' + (int) (Math.random() * 26)));
      } else {
        stringBuilder.append(" ");
      }
    }
    return stringBuilder.toString();
  }

  public static List<String> getListOpportunities() {
    List<String> opportunities = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      opportunities.add("");
    }
    return opportunities;
  }

  public static List<String> getListAppointment() {
    List<String> appointments = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      appointments.add("");
    }
    return appointments;
  }

  public static final String COMMON_DATE_TIME_FORMAT = "MMM d, yyyy, h:mm a";

  public static String getRandomDateTimeString() {
    SimpleDateFormat sdf = new SimpleDateFormat(COMMON_DATE_TIME_FORMAT, Locale.getDefault());
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, (int) (Math.random() * 1000));
    return sdf.format(calendar.getTime());
  }
}
