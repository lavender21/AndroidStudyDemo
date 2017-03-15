package com.example.lavender.servicedemo_lifecycle_bind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
    public static int cnt;
    public IBinder iBinder;
    public class MyBinder extends Binder {
        MyService getService() {
            return MyService.this;
        }
    }
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        cnt++;
        MainActivity.text = MainActivity.text + "Service onBind:" + "cnt=" + cnt + "\n";
        MainActivity.textView.setText(MainActivity.text);
        iBinder = new MyBinder();
        return iBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        cnt = 0;
        MainActivity.text = MainActivity.text + "Service onCreate:" + "cnt=" + cnt + "\n";
        MainActivity.textView.setText(MainActivity.text);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cnt = 0;
        MainActivity.text = MainActivity.text + "Service onDestroy:" + "cnt=" + cnt + "\n";
        MainActivity.textView.setText(MainActivity.text);
    }

    @Override
    public boolean onUnbind(Intent intent) {

        MainActivity.text = MainActivity.text + "Service onUnBind:" + "cnt=" + cnt + "\n";
        MainActivity.textView.setText(MainActivity.text);
        return super.onUnbind(intent);
    }
}

