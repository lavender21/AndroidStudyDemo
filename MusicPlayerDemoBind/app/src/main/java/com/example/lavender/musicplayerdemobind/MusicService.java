package com.example.lavender.musicplayerdemobind;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

public class MusicService extends Service {
    private MyBinder iBinder;
    private MediaPlayer mPlayer;
    public class MyBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        iBinder = new MyBinder();
        Toast.makeText(this, "MusicService onBind", Toast.LENGTH_SHORT).show();
        return iBinder;
    }

    // 播放音乐
    public void playMusic() throws IOException{
        Toast.makeText(this, "MusicService play", Toast.LENGTH_SHORT).show();
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.guyu);
        mPlayer.setLooping(true);
        mPlayer.start();
    }

    // 停止播放
    public void stopMusic() {
        if (mPlayer == null)
        {
            return;
        }
        if (mPlayer.isPlaying())
        {
            Toast.makeText(this, "MusicService stop", Toast.LENGTH_SHORT).show();
            mPlayer.stop();
        }
    }

    // 暂停播放
    public  void pauseMusic() {
        if (mPlayer == null )
        {
            return;
        }
        if (mPlayer.isPlaying())
        {
            Toast.makeText(this, "MusicService pause", Toast.LENGTH_SHORT).show();
            mPlayer.pause();
        }
    }

    // 继续播放
    public void restartMusic() {
        if(mPlayer == null)
        {
            return;
        }
        if (!mPlayer.isPlaying())
        {
            Toast.makeText(this, "MusicService restart", Toast.LENGTH_SHORT).show();
            mPlayer.start();
        }
    }
}
