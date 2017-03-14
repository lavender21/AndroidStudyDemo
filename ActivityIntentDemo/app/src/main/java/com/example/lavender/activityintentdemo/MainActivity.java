package com.example.lavender.activityintentdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // 显示启动
                Bundle bundle = new Bundle();
                bundle.putString("data","test");
                Intent intent1 = new Intent(MainActivity.this, SubActivity1.class);
                // 传递数据给子页面
                intent1.putExtras(bundle);
                // 获取从子页面返回的数据
                startActivityForResult(intent1,1);
                //startActivity(intent1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 隐式启动
                Intent intent2 = new Intent();
                // 设置action响应方式
                intent2.setAction(Intent.ACTION_VIEW);
                // Data内容
                intent2.setData(Uri.parse("intentdemo://cn.edu.neusoft"));
                startActivityForResult(intent2,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                Bundle random = data.getExtras();
                Toast.makeText(MainActivity.this, random.getInt("random")+"",Toast.LENGTH_LONG).show();
            }
        }
        else if (requestCode == 2)
        {
            if (resultCode == RESULT_OK)
            {
                Bundle Dev = data.getExtras();
                Toast.makeText(MainActivity.this, Dev.getString("DevName"),Toast.LENGTH_LONG).show();
            }
        }
    }
}
