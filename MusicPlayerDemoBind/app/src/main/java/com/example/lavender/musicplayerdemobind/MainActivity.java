package com.example.lavender.musicplayerdemobind;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start, pause, restart, stop;
    private MusicService musicService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button)findViewById(R.id.start);
        pause = (Button)findViewById(R.id.puase);
        restart = (Button)findViewById(R.id.goon);
        stop = (Button)findViewById(R.id.stop);

        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        restart.setOnClickListener(this);
        stop.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, MusicService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

        switch (v.getId())
        {
            case R.id.start:
                try{
                    musicService.playMusic();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.puase:
                musicService.pauseMusic();
                break;
            case R.id.goon:
                musicService.restartMusic();
                break;
            case R.id.stop:
                musicService.stopMusic();
                break;
        }

    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicService = ((MusicService.MyBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicService = null;
        }
    };
}
