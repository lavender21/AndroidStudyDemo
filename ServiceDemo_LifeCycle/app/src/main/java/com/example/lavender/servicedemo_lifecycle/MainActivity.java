package com.example.lavender.servicedemo_lifecycle;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button button_start, button_end, button_clr;
    public static TextView txt;
    public static String text="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_start = (Button)findViewById(R.id.button_start);
        button_end = (Button)findViewById(R.id.button_end);
        button_clr = (Button)findViewById(R.id.button_clr);
        txt = (TextView)findViewById(R.id.txt);

        // 启动服务
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(getApplicationContext(), MyService.class);
                startService(serviceIntent);
            }
        });

        // 停止服务
        button_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent serviceIntent = new Intent(getApplicationContext(), MyService.class);
                stopService(serviceIntent);
            }
        });

        // 清空内容
        button_clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("");
                text = "";
            }
        });
    }
}


