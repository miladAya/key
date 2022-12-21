package com.example.keymystery.ui;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.keymystery.R;

public class SoundService extends Service {
    MediaPlayer player;

    public SoundService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player=MediaPlayer.create(this, R.raw.sound);
        if(player!= null)
            player.setLooping(true);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        //حتى يكون في حاله الاستعداد قبل او بعد التوقف
        player.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         player.start();
         return START_STICKY;
//        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}