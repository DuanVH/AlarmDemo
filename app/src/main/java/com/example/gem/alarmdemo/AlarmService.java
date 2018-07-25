package com.example.gem.alarmdemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import static com.example.gem.alarmdemo.AlarmAdapter.TAG;

/**
 * Created by gem on 7/25/18.
 */

public class AlarmService extends Service {

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    Log.e(TAG, "onBind: " );
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    Log.e(TAG, "onStartCommand: " );

    IntentFilter intentFilterTimeChange = new IntentFilter();
    intentFilterTimeChange.addAction(Intent.ACTION_TIME_TICK);
    this.registerReceiver(mReceiverTimeChange, intentFilterTimeChange);

    return super.onStartCommand(intent, flags, startId);
  }

  @Override
  public void onCreate() {
    Log.e(TAG, "onCreate: " );
    super.onCreate();
  }

  private BroadcastReceiver mReceiverTimeChange = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
      if (action.equals(Intent.ACTION_TIME_TICK)) {
        Toast.makeText(AlarmService.this, "time changed", Toast.LENGTH_SHORT).show();
        Date date = Calendar.getInstance().getTime();
        Log.e(TAG, "onReceive: " + date.getTime() );
      }
    }
  };

}
