package com.example.lavender.httpconnectiondemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    public  TextView text;
    public   Button httpget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView)findViewById(R.id.text);
        httpget = (Button)findViewById(R.id.btn_httpget);

        httpget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 执行异步任务 发送网络请求
                new MyTask().execute();
            }
        });
    }
    public class MyTask extends AsyncTask{
        @Override
        protected void onPreExecute() {
            httpget.setEnabled(false);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Object o) {
            text.setText(o.toString());
            httpget.setEnabled(true);
            super.onPostExecute(o);
        }

        @Override
        protected Object doInBackground(Object[] params) {
            HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();
            try{
                Log.i("Tag","new  url");
                URL url = new URL("http://10.101.102.253:8080/api/student");
                connection = (HttpURLConnection)url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(6000);
                connection.setReadTimeout(6000);

                Log.i("Tag","connection");
                // 获取输入流
                InputStream in = connection.getInputStream();
                // 对服务器返回的流进行读取
                Log.i("Tag","read data from inputStream");
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                Log.i("Tag","add data in response");
                while((line = reader.readLine()) != null )
                {
                    // 一行一行添加
                    response.append(line);
                }
                Log.i("Tag","set response");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                // 释放连接
                if (connection != null){
                    connection.disconnect();
                }
            }
            return response;
        }
    }
}






