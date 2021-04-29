package com.example.hokiefit;

import android.media.MediaPlayer;

public class AlarmPlayer implements MediaPlayer.OnCompletionListener {

    MediaPlayer player;
    public static final int[] ALARM_PATH = {R.raw.restAlarm};
    @Override
    public void onCompletion(MediaPlayer mp) {

    }
}
