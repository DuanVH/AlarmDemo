package com.example.gem.alarmdemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by gem on 8/7/18.
 */

public class AlarmUtils {
  private static final String sTagAlarms = ":alarms";

  public static void addAlarm(Context context, Intent intent, int notificationId, Calendar calendar) {

    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    } else {
      alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    saveAlarmId(context, notificationId);
  }

  /*DuanVh Start Add*/
  public static void addAlarm(Context context, Intent intent, int notificationId, String hour, String min) {

    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
    calendar.set(Calendar.MINUTE, Integer.parseInt(min));

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    } else {
      alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    saveAlarmId(context, notificationId);
  }

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  public static void addAlarm(Context context, Intent intent, int notificationId, long timeMillions) {

    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);

//    Calendar calendar = Calendar.getInstance();
//    calendar.setTimeInMillis(System.currentTimeMillis());
//    calendar.setTimeInMillis(timeMillions);
    alarmManager.setTime(timeMillions);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmManager.getNextAlarmClock().getTriggerTime(), pendingIntent);
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      alarmManager.setExact(AlarmManager.RTC_WAKEUP, alarmManager.getNextAlarmClock().getTriggerTime(), pendingIntent);
    } else {
      alarmManager.set(AlarmManager.RTC_WAKEUP, alarmManager.getNextAlarmClock().getTriggerTime(), pendingIntent);
    }

    saveAlarmId(context, notificationId);
  }
  /*DuanVh End Add*/


  public static void cancelAlarm(Context context, Intent intent, int notificationId) {
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    alarmManager.cancel(pendingIntent);
    pendingIntent.cancel();

    removeAlarmId(context, notificationId);
  }

  public static void cancelAllAlarms(Context context, Intent intent) {
    for (int idAlarm : getAlarmIds(context)) {
      cancelAlarm(context, intent, idAlarm);
    }
  }

  public static boolean hasAlarm(Context context, Intent intent, int notificationId) {
    return PendingIntent.getBroadcast(context, notificationId, intent, PendingIntent.FLAG_NO_CREATE) != null;
  }

  private static void saveAlarmId(Context context, int id) {
    List<Integer> idsAlarms = getAlarmIds(context);

    if (idsAlarms.contains(id)) {
      return;
    }

    idsAlarms.add(id);

    saveIdsInPreferences(context, idsAlarms);
  }

  private static void removeAlarmId(Context context, int id) {
    List<Integer> idsAlarms = getAlarmIds(context);

    for (int i = 0; i < idsAlarms.size(); i++) {
      if (idsAlarms.get(i) == id)
        idsAlarms.remove(i);
    }

    saveIdsInPreferences(context, idsAlarms);
  }

  private static List<Integer> getAlarmIds(Context context) {
    List<Integer> ids = new ArrayList<>();
    try {
      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
      JSONArray jsonArray2 = new JSONArray(prefs.getString(context.getPackageName() + sTagAlarms, "[]"));

      for (int i = 0; i < jsonArray2.length(); i++) {
        ids.add(jsonArray2.getInt(i));
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

    return ids;
  }

  private static void saveIdsInPreferences(Context context, List<Integer> lstIds) {
    JSONArray jsonArray = new JSONArray();
    for (Integer idAlarm : lstIds) {
      jsonArray.put(idAlarm);
    }

    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString(context.getPackageName() + sTagAlarms, jsonArray.toString());

    editor.apply();
  }

}
