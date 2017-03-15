package com.example.lavender.luckdrawgame;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Ref;

public class MainActivity extends AppCompatActivity {
    private static Button start, stop;
    private static  TextView textView,resultTxt;
    private static Handler handler = new Handler();
    private static int random1,random2,random3;
    private static String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button)findViewById(R.id.startGame);
        stop = (Button)findViewById(R.id.GetResult);
        textView = (TextView)findViewById(R.id.textView);
        resultTxt = (TextView)findViewById(R.id.textView3);

        final Intent intent = new Intent(MainActivity.this, RandomService.class);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });
    }

    public static void UpdateGUI(int randomDouble, int randomDouble1, int randomDouble2) {
        random1 = randomDouble;
        random2 = randomDouble1;
        random3 = randomDouble2;
        handler.post(RefreshLabe);
    }


    private static Runnable RefreshLabe = new Runnable() {
        @Override
        public void run() {
            textView.setText(String.valueOf("20170315"+random1+random2+random3));
        }
    };

    public static void showResult(String resultStr) {
        result = resultStr;
        handler.post(GetResult);
    }

    private  static Runnable GetResult = new Runnable() {
        @Override
        public void run() {
            resultTxt.setText("中奖号码："+result);
        }
    };
}
