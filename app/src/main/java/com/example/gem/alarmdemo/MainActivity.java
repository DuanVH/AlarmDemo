package com.example.gem.alarmdemo;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.icu.util.EthiopicCalendar;
import android.nfc.Tag;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = MainActivity.class.getName();
  private RecyclerView mRvListAlarm;
  private List<ItemAlarm> mAlarms;
  private AlarmAdapter mAdapter;


  private final String fileName = "note1.txt";

  @RequiresApi(api = Build.VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initViews();

//    NotificationHelper.postNotify(getApplicationContext(), "10", "40");
    AlarmUtils.addAlarm(getApplicationContext(), new Intent(getApplicationContext(), AlarmNotificationReceiver.class), 1, "14", "24");
    AlarmUtils.addAlarm(getApplicationContext(), new Intent(getApplicationContext(), AlarmNotificationReceiver.class), 2, "14", "27");

//    NotificationHelper.enableReboot(getApplicationContext());
  }

  private void initViews() {
    mRvListAlarm = findViewById(R.id.rv_alarm);
    addListAlarm();
//    writeFile();
  }

  private void writeFile() {
    File extStore = Environment.getExternalStorageDirectory();
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

      Toast.makeText(getApplicationContext(), fileName + " saved", Toast.LENGTH_LONG).show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void addListAlarm() {
    mAlarms = new ArrayList<>();
    mAlarms.add(new ItemAlarm(0, 5000, "DuanVH"));
    mAlarms.add(new ItemAlarm(1, 10000, "CongPV"));
    mAlarms.add(new ItemAlarm(2, 15000, "GiangDD"));
    mAlarms.add(new ItemAlarm(3, 3000, "DungNB"));
    mAlarms.add(new ItemAlarm(4, 10000, "DatTT"));
    mAlarms.add(new ItemAlarm(5, 5000, "ChiNBH"));

    mAdapter = new AlarmAdapter(mAlarms, this);
    mRvListAlarm.setAdapter(mAdapter);

    LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    mRvListAlarm.setLayoutManager(layoutManager);
  }
}
