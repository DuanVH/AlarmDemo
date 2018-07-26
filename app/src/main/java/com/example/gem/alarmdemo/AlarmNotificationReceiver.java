package com.example.gem.alarmdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by gem on 7/25/18.
 */

public class AlarmNotificationReceiver extends BroadcastReceiver {

  private static final String CHANNEL_ID = "CHANNEL_ID";

  @Override
  public void onReceive(Context context, Intent intent) {
    postNotify(context);
  }

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


}
