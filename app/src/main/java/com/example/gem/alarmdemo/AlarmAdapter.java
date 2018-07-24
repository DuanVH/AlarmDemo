package com.example.gem.alarmdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by gem on 7/23/18.
 */

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

  public static final String TAG = AlarmAdapter.class.getName();

  private List<ItemAlarm> mAlarms;
  private Context mContext;
  private LayoutInflater mLayoutInflater;

  public AlarmAdapter(List<ItemAlarm> mAlarms, Context mContext) {
    this.mAlarms = mAlarms;
    this.mContext = mContext;
    mLayoutInflater = LayoutInflater.from(mContext);
  }

  @NonNull
  @Override
  public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View itemView = mLayoutInflater.inflate(R.layout.item_alarm, parent, false);
    return new AlarmViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
    ItemAlarm itemAlarm = mAlarms.get(position);
    holder.tvId.setText(itemAlarm.getId() + "");
    holder.tvContent.setText(itemAlarm.getContent());
    holder.tvTime.setText(itemAlarm.getTime() + "");
  }

  @Override
  public int getItemCount() {
    return mAlarms.size();
  }

  class AlarmViewHolder extends RecyclerView.ViewHolder {

    TextView tvId;
    TextView tvContent;
    TextView tvTime;

    public AlarmViewHolder(View itemView) {
      super(itemView);
      tvId = itemView.findViewById(R.id.tv_id);
      tvContent = itemView.findViewById(R.id.tv_content);
      tvTime = itemView.findViewById(R.id.tv_time);

      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          // TODO
          Date date = Calendar.getInstance().getTime();
          Log.e(TAG, "onClick: " + date.getHours() + ":" + date.getMinutes() + "; " + date.getTime());
        }
      });

    }
  }

}
