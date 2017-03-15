package com.example.lavender.servicedemo_lifecycle_bind;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button button_bind, button_unbind, button_clr;
     public static TextView  textView;
     public static String text="";
     private MyService myService;
     private boolean isBind = false;  //标识服务的状态

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyService.MyBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 关联控件
        button_bind = (Button)findViewById(R.id.button_bind);
        button_unbind = (Button)findViewById(R.id.button_unbind);
        button_clr = (Button)findViewById(R.id.button_clr);
        textView = (TextView)findViewById(R.id.textView);



        // 绑定服务
        button_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(MainActivity.this, MyService.class);
                bindService(serviceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
                isBind = true;
            }
        });

        // 解除绑定
        button_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBind)
                {
                    unbindService(mServiceConnection);
                    isBind = false;
                }
            }
        });

        button_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                text = "";
            }
        });

    }
}
