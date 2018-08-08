package com.example.gem.alarmdemo;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.example.gem.alarmdemo.Constants.END_OF_DAY_HOUR;
import static com.example.gem.alarmdemo.Constants.END_OF_DAY_MINUTES;
import static com.example.gem.alarmdemo.Constants.END_OF_DAY_SECONDS;

/**
 * DateTimeUtils
 * Created by Administrator on 3/14/2017.
 */

public class DateTimeUtils {
  private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd MMM, yyyy HH:mm", Locale.getDefault());
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM, yyyy", Locale.getDefault());
  private static final DateFormat CONTRACT_DATE_FORMAT = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
  private static final DateFormat DAY_MONTH_FORMAT = new SimpleDateFormat("dd MMM", Locale.getDefault());
  private static final DateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm", Locale.getDefault());
  private static final DateFormat TIME_FORMAT_12 = new SimpleDateFormat("HH:mm a", Locale.getDefault());

  public static String displayTimeZone(TimeZone tz) {

    long hours = TimeUnit.MILLISECONDS.toHours(tz.getRawOffset());
    long minutes = TimeUnit.MILLISECONDS.toMinutes(tz.getRawOffset())
        - TimeUnit.HOURS.toMinutes(hours);
    // avoid -4:-30 issue
    minutes = Math.abs(minutes);

    String result;
    if (hours > 0) {
      result = String.format(Locale.getDefault(), "+%02d%02d", hours, minutes);
    } else {
      result = String.format(Locale.getDefault(), "%02d%02d", hours, minutes);
    }

    return result;

  }

  public static String dateTime(Long dateMillis) {
    if (dateMillis == null) return "";

    return getDateTimeFormat().format(new Date(dateMillis));
  }

  private static DateFormat getDateTimeFormat() {
    DATE_TIME_FORMAT.setTimeZone(TimeZone.getDefault());
    return DATE_TIME_FORMAT;
  }

  private static DateFormat getDateFormat() {
    DATE_FORMAT.setTimeZone(TimeZone.getDefault());
    return DATE_FORMAT;
  }

  private static DateFormat getContractDateFormat() {
    CONTRACT_DATE_FORMAT.setTimeZone(TimeZone.getDefault());
    return CONTRACT_DATE_FORMAT;
  }

  private static DateFormat getDayMonthFormat() {
    DAY_MONTH_FORMAT.setTimeZone(TimeZone.getDefault());
    return DAY_MONTH_FORMAT;
  }

  private static DateFormat getTimeFormat() {
    TIME_FORMAT.setTimeZone(TimeZone.getDefault());
    return TIME_FORMAT;
  }

  private static DateFormat getTimeFormat12() {
    TIME_FORMAT_12.setTimeZone(TimeZone.getDefault());
    return TIME_FORMAT_12;
  }

  public static String getDateTimeString(Long timeInMillis) {
    if (timeInMillis != null && timeInMillis > 0) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(timeInMillis);
      return getDateTimeString(calendar);
    } else {
      return Constants.EMPTY_STRING;
    }
  }

  public static String getDateString(Long timeInMillis) {
    if (timeInMillis != null && timeInMillis > 0) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(timeInMillis);
      return getDateString(calendar);
    } else {
      return Constants.EMPTY_STRING;
    }
  }

  public static String getDayMonthString(Long timeInMillis) {
    if (timeInMillis != null && timeInMillis > 0) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(timeInMillis);
      return getDayMonthString(calendar);
    } else {
      return Constants.EMPTY_STRING;
    }
  }

  public static String getTimeString(Long timeInMillis) {
    if (timeInMillis != null && timeInMillis > 0) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(timeInMillis);
      return getTimeString(calendar);
    } else {
      return Constants.EMPTY_STRING;
    }
  }

  public static String getTimeFormat12String(Long timeInMillis) {
    if (timeInMillis != null && timeInMillis > 0) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTimeInMillis(timeInMillis);
      return getTimeFormat12String(calendar);
    } else {
      return Constants.EMPTY_STRING;
    }
  }

  public static String getDateTimeString(Calendar calendar) {
    SimpleDateFormat sdf = new SimpleDateFormat(GlobalStuff.COMMON_DATE_TIME_FORMAT,
        Locale.getDefault());
    sdf.setTimeZone(TimeZone.getDefault());
    return sdf.format(calendar.getTime());
  }

  public static String getDateString(Calendar calendar) {
    return getDateFormat().format(calendar.getTime());
  }

  public static String getContractDateString(Calendar calendar) {
    return getContractDateFormat().format(calendar.getTime());
  }

  public static String getDayMonthString(Calendar calendar) {
    return getDayMonthFormat().format(calendar.getTime());
  }

  public static String getTimeString(Calendar calendar) {
    return getTimeFormat().format(calendar.getTime());
  }

  public static String getTimeFormat12String(Calendar calendar) {
    return getTimeFormat12().format(calendar.getTime());
  }

  public static String getCallDurationStringFormat(Long duration, Context context) {
    long second = (duration / 1000) % 60;
    long minute = (duration / (1000 * 60)) % 60;
    long hour = (duration / (1000 * 60 * 60)) % 24;

    return String.format("%1$02d:%2$02d:%3$02d", hour, minute, second);
  }

  public static Calendar getEndOfDay() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, END_OF_DAY_HOUR);
    calendar.set(Calendar.MINUTE, END_OF_DAY_MINUTES);
    calendar.set(Calendar.MILLISECOND, END_OF_DAY_SECONDS);
    return calendar;
  }

  // get start of this week in milliseconds
  public static long getStartOfThisWeek(Calendar calendar) {
    calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
    return calendar.getTimeInMillis();
  }

  // get end of this week in milliseconds
  public static long getEndOfThisWeek(Calendar calendar) {
    calendar.add(Calendar.WEEK_OF_YEAR, 1);
    long endWeek = calendar.getTimeInMillis() - 1000;
    return endWeek;
  }

  public static String getDocumentCreateDay(Date date) {
    DateFormat dateFormat = getContractDateFormat();
    return dateFormat.format(date);
  }
}
