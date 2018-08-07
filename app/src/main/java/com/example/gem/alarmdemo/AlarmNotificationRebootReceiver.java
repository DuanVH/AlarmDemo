package com.example.gem.alarmdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by gem on 7/26/18.
 */

public class AlarmNotificationRebootReceiver extends BroadcastReceiver {

  private static final String TAG = AlarmNotificationRebootReceiver.class.getName();
  private final String fileName = "note.txt";

  @Override
  public void onReceive(Context context, Intent intent) {
    Log.i("REBOOT_COMPLETED", "reboot - " + intent.getAction());
    Toast.makeText(context, "re boot xxx", Toast.LENGTH_SHORT).show();
    if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
      writeFile();
      NotificationHelper.postNotifyReboot(context);
    }
  }

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
