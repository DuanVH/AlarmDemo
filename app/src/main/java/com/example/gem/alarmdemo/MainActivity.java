package com.example.gem.alarmdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private RecyclerView mRvListAlarm;
    private List<ItemAlarm> mAlarms;
    private AlarmAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

//        for (int i = 0; i < mAlarms.size(); i++) {
////            SystemClock.sleep(mAlarms.get(i).getTime());
//            final long time = mAlarms.get(i).getTime();
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    SystemClock.sleep(time);
//                }
//            }, mAlarms.get(i).getTime());
//            postNotification(mAlarms.get(i).getId() + "", mAlarms.get(i).getContent() + "");
//        }
    }

    private void initViews() {
        mRvListAlarm = findViewById(R.id.rv_alarm);
        addListAlarm();
    }

    private void postNotification(String contentTitle, String contentText) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, ResultActivity.class);


        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(ResultActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());
        Log.e(TAG, "postNotification: " );
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
