package com.example.gem.alarmdemo;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by gem on 7/25/18.
 */

public class TimeChangeReceiver extends BroadcastReceiver {
  private static final String TAG = TimeChangeReceiver.class.getName();

  @Override
  public void onReceive(Context context, Intent intent) {
    Intent intentRepeat = new Intent(context, MainActivity.class);
    intentRepeat.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

    PendingIntent pendingIntent =
        PendingIntent.getActivity(context, NotificationHelper.ALARM_TYPE_RTC, intentRepeat, PendingIntent.FLAG_UPDATE_CURRENT);

    Notification repeatNotification = buildLocalNotification(context, pendingIntent).build();
    NotificationHelper.getNotificationManager(context);

  }

  public NotificationCompat.Builder buildLocalNotification(Context context, PendingIntent pendingIntent) {
    NotificationCompat.Builder builder =
        (NotificationCompat.Builder) new NotificationCompat.Builder(context)
            .setContentIntent(pendingIntent)
            .setSmallIcon(android.R.drawable.arrow_up_float)
            .setContentTitle("Morning Notification")
            .setAutoCancel(true);

    return builder;
  }
}
