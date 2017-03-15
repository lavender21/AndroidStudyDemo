package com.example.lavender.musicplayerdemo;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {
    private MediaPlayer mPlayer;

    @Override
    public void onCreate() {
        Toast.makeText(this, "MusicService onCreate()", Toast.LENGTH_SHORT).show();
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.guyu);
        if (mPlayer == null)
        {
            Log.e("MusiceService","---------null");
        }
        mPlayer.setLooping(true);
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "MusicService onDestroy()", Toast.LENGTH_SHORT).show();
        mPlayer.stop();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "MusicService onStart()", Toast.LENGTH_SHORT).show();
        mPlayer.start();
        return super.onStartCommand(intent, flags, startId);
    }

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
