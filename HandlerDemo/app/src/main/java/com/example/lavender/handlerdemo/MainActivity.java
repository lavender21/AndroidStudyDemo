package com.example.lavender.handlerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btn_start,btn_stop;
    private TextView showResult;
    private Handler handler;
    private Thread thread;
    public boolean flag = true;
    public String luckman = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        // 子线程产生获奖者人名单
        thread = new Thread(){
            @Override
            public  void run(){
                luckyGame();
            }
        };

        // 监听按钮点击事件
        btn_start.setOnClickListener(new View.OnClickListener() {
            // 开始抽奖
            @Override
            public void onClick(View v) {
                if (thread == null )
                {
                    thread = new Thread(){
                        @Override
                        public  void run(){
                            luckyGame();
                        }
                    };
                    flag = true;
                }
                if (!thread.isAlive())
                {
                    thread.start();
                }
            }
        });
        // 揭秘中奖者
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thread != null && thread.isAlive())
                {
                    thread.interrupt();
                    flag = false;
                    thread = null;
                    Toast.makeText(MainActivity.this,luckman + " 恭喜你，中奖了", Toast.LENGTH_SHORT).show();
                }
            }
        });

        handler = new Handler(){
            @Override
            public  void handleMessage(Message msg){
                super.handleMessage(msg);
                luckman = msg.obj.toString();
                showResult.setText(msg.obj.toString());
            }
        };

    }
    // 初始化控件
    public  void init(){
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        showResult = (TextView)findViewById(R.id.textView);
    }

    // 中奖进程
    public void luckyGame(){
        String[] name = {"宁润婷","方增强", "高凡", "谭艺冰", "王梦佳", "史雨", "赵榆姝","薰薰",
                "啾啾","凡逗比","仔仔","夹夹","王改娟","小改","姝儿","雨雨","高社长","宁猫咪","谭喜碧",
                "王神秘","王娱乐","赵群姝"};
        try{
            while(flag){
                Thread.sleep(200);
                // 产生随机幸运者
                Random rand = new Random();
                int r = rand.nextInt(name.length);
                String luckname = name[r];
                // 发送消息
                Message msg = new Message();
                msg.obj = luckname;
                handler.sendMessage(msg);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
