package com.example.keymystery.ui;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.keymystery.R;


public class
SoundService extends Service {

MediaPlayer mediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer=MediaPlayer.create(getBaseContext(), R.raw.sound);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
         super.onStartCommand(intent, flags, startId);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        return START_STICKY;

    }


}
