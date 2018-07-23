package com.example.gem.alarmdemo;

/**
 * Created by gem on 7/23/18.
 */

public class ItemAlarm {

  private int id;
  private long time;
  private String content;

  public ItemAlarm() {
  }

  public ItemAlarm(int id, long time, String content) {
    this.id = id;
    this.time = time;
    this.content = content;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
