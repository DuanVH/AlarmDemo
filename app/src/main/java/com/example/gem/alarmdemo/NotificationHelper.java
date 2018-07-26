package com.example.gem.alarmdemo;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.time.temporal.JulianFields;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by gem on 7/25/18.
 */

public class NotificationHelper {

  private static final String TAG = NotificationHelper.class.getName();
  private static AlarmManager alarmManager;
  private static PendingIntent pendingIntent;

  private static AlarmManager alarmManagerReboot;
  private static PendingIntent pendingIntentReboot;

  private static final String CHANNEL_ID = "CHANNEL_ID";


  public static void postNotify(Context context) {
    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
    builder.setAutoCancel(true)
        .setDefaults(Notification.DEFAULT_ALL)
        .setSmallIcon(R.mipmap.ic_launcher_round)
        .setContentTitle("Demo Alarm")
        .setContentText("Hello girl")
        .setContentInfo("info");

    NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    notificationManager.notify(0, builder.build());
  }

  public static void postNotify(Context context, String h, String m) {
    alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, AlarmNotificationReceiver.class);
    pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(h));
    calendar.set(Calendar.MINUTE, Integer.parseInt(m));

    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
        calendar.getTimeInMillis(),
        AlarmManager.INTERVAL_DAY, pendingIntent);

    Log.e(TAG, "postNotify: " + SystemClock.elapsedRealtime() );
  }

  public static void test() {
    Log.e(TAG, "test: " + SystemClock.elapsedRealtime() );;
  }

  public static void postNotifyReboot(Context context, String h, String m) {
    alarmManagerReboot = (AlarmManager) context.getSystemService(ALARM_SERVICE);
    Intent intent = new Intent(context, AlarmNotificationReceiver.class);
    pendingIntentReboot = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(System.currentTimeMillis());
    calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(h));
    calendar.set(Calendar.MINUTE, Integer.parseInt(m));

    alarmManagerReboot.setInexactRepeating(AlarmManager.RTC_WAKEUP,
        calendar.getTimeInMillis(),
        AlarmManager.INTERVAL_DAY, pendingIntentReboot);
  }


  public static void postNotifyReboot(Context context) {
    Intent intent = new Intent(context, AlarmNotificationReceiver.class);
    pendingIntentReboot = PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    alarmManagerReboot = (AlarmManager)context.getSystemService(ALARM_SERVICE);

    alarmManagerReboot.setInexactRepeating(AlarmManager.ELAPSED_REALTIME,
        SystemClock.elapsedRealtime(),
        AlarmManager.INTERVAL_DAY, pendingIntentReboot);
  }

  public static void enableReboot(Context context) {
    ComponentName receiver = new ComponentName(context, AlarmNotificationRebootReceiver.class);
    PackageManager pm = context.getPackageManager();
    pm.setComponentEnabledSetting(receiver,
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
        PackageManager.DONT_KILL_APP);
  }

  public static void cancelAlarm() {
    if (alarmManager != null) {
      alarmManager.cancel(pendingIntent);
    }
  }

  public static void cancelRebootAlarm() {
    if (alarmManagerReboot != null) {
      alarmManagerReboot.cancel(pendingIntentReboot);
    }
  }

}
