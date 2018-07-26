package com.example.gem.alarmdemo;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by gem on 7/26/18.
 */

public class MyService extends Service {

  private static final String TAG = MyService.class.getName();
  private final String fileName = "note.txt";

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {

    Log.e(TAG, "onStartCommand: " );

    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
    registerReceiver(mReceiverSystem, intentFilter);

    return START_NOT_STICKY;
  }

  private BroadcastReceiver mReceiverSystem = new BroadcastReceiver() {
    @Override
    public void onReceive(Context context, Intent intent) {
      String action = intent.getAction();
      if (action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
        NotificationHelper.postNotifyReboot(context);
        Toast.makeText(context, "ACTION_TIME_TICK", Toast.LENGTH_SHORT).show();
      }
    }
  };

  private void writeFile() {
    File extStore = Environment.getExternalStorageDirectory();
    // -> /storage/emulated/0/note.txt
    String path = extStore.getAbsolutePath() + "/" + fileName;
    Log.i("ExternalStorageDemo", "Save to: " + path);

    String data = "Vu Huu Duan";

    try {
      File myFile = new File(path);
      myFile.createNewFile();
      FileOutputStream fOut = new FileOutputStream(myFile);
      OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
      myOutWriter.append(data);
      myOutWriter.close();
      fOut.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
