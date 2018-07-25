package com.example.gem.alarmdemo;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by gem on 7/25/18.
 */

public class NotificationHelper {

  public static int ALARM_TYPE_RTC = 100;
  private static AlarmManager alarmManager;
  private static PendingIntent pendingIntent;

  public static void postNotify(Context context, String h, String m) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.set(Calendar.HOUR_OF_DAY,
        Integer.getInteger(h, 0),
        Integer.getInteger(m, 0));

    Intent intent = new Intent(context, TimeChangeReceiver.class);
  }

  public static NotificationManager getNotificationManager(Context context) {
    return (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
  }

}
