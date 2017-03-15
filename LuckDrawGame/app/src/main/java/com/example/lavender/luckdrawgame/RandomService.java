package com.example.lavender.luckdrawgame;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class RandomService extends Service {
    private Thread luckyThread;
    private static String text = "";
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "抽奖开始", Toast.LENGTH_SHORT).show();
        luckyThread = new Thread(null, backgroudWork, "luckThread");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "正在抽奖", Toast.LENGTH_SHORT).show();
        // 启动线程
        if (!luckyThread.isAlive())
        {
            luckyThread.start();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        int randomDouble = (int)Math.round(Math.random()*2+1);
        int randomDouble1 = (int)Math.round(Math.random()*2);
        int randomDouble2 = (int)Math.round(Math.random()*9);
        String Result = "20170315"+randomDouble+randomDouble1+randomDouble2;
        if (text == Result)
        {
            Toast.makeText(this, "恭喜你中奖了", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "很遗憾，你没有中奖", Toast.LENGTH_SHORT).show();
        }
        MainActivity.showResult(Result);
        // 通知线程准备终止
        luckyThread.interrupt();
    }

    public RandomService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
    private Runnable backgroudWork = new Runnable() {
        @Override
        public void run() {
            try
            {
                // 不断查看线程是否结束
                while(!luckyThread.isInterrupted())
                {
                    int randomDouble = (int)Math.round(Math.random()*2+1);
                    int randomDouble1 = (int)Math.round(Math.random()*2);
                    int randomDouble2 = (int)Math.round(Math.random()*9);
                    text  = "20170315"+randomDouble+randomDouble1+randomDouble2;
                    MainActivity.UpdateGUI(randomDouble,randomDouble1,randomDouble2);
                    Thread.sleep(500);
                    if (randomDouble1 == randomDouble2 && randomDouble1 == 0)
                    {
                        luckyThread.interrupt();
                    }
                }
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }


        }
    };
}
