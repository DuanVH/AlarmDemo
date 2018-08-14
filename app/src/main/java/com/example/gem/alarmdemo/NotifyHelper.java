package com.example.gem.alarmdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import java.security.PublicKey;

/**
 * Created by gem on 8/14/18.
 */

public class NotifyHelper extends ContextWrapper {

  private static final String CHANNEL_ID = "1304";
  private static final String CHANNEL_NAME = "DUAN_DUAN";
  private NotificationManager manager;

  public NotifyHelper(Context base) {
    super(base);
    createChannels();
  }

  private void createChannels() {

    //  IMPORTANCE_DEFAULT: ...
    //  IMPORTANCE_HIGH: ...
    //  IMPORTANCE_LOW: ...
    //  IMPORTANCE_MIN: ...
    //  IMPORTANCE_NONE: ...

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
      channel.enableLights(true);
      channel.enableVibration(true);
      channel.setLightColor(Color.GREEN);
      channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
      getManager().createNotificationChannel(channel);

    }
  }

  public NotificationManager getManager() {
    if (manager == null) {
      manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
    return manager;
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public Notification.Builder getChannelNotification (String title, String body) {
    return new Notification.Builder(getApplicationContext(), CHANNEL_ID)
        .setAutoCancel(true)
        .setContentTitle(title)
        .setContentText(body)
        .setSmallIcon(R.mipmap.ic_launcher_round);
  }
}
