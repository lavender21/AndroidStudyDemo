package com.example.lavender.asynctaskdemo;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button start;
    private ProgressBar pg;
    public final static int NUM = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button)findViewById(R.id.start);
        pg = (ProgressBar)findViewById(R.id.progressBar5);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开始执行异步任务
                new MyTask().execute(1,500);
            }
        });

    }
    public  class MyTask extends AsyncTask{
        @Override
        // 在执行后台任务前给前台UI做标记
        protected void onPreExecute() {
            // 进度条从0开始
            pg.setProgress(0);
            start.setEnabled(false);
            super.onPreExecute();
        }

        @Override
        // 将执行结果返回
        protected void onPostExecute(Object o) {
            start.setEnabled(true);
            Toast.makeText(MainActivity.this, o.toString(), Toast.LENGTH_SHORT).show();
        }

        @Override
        // 将后台执行的进度实时更新到前台UI上
        protected void onProgressUpdate(Object[] values) {
            int p  = pg.getMax()/NUM*Integer.parseInt(values[0].toString());
            pg.setProgress(p);
            super.onProgressUpdate(values);
        }

        @Override
        // 在后台执行异步任务
        protected Object doInBackground(Object[] params) {
            String ret = null;
            Integer step,sleeptime;
            step = Integer.parseInt(params[0].toString());
            sleeptime = Integer.parseInt(params[1].toString());
            for (int i = 0; i <= NUM; i+=step)
            {
                // 执行前台UI更新任务
                publishProgress(i);
                SystemClock.sleep(sleeptime);
            }
            ret = "更新完毕...";
            return ret;
        }
    }
}
