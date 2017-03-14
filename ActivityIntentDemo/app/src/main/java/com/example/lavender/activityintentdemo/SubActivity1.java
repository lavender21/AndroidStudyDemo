package com.example.lavender.activityintentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SubActivity1 extends AppCompatActivity {
    TextView textView;
    TextView randtxt;
    Button btn;
    public int r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        final Bundle bundle = this.getIntent().getExtras();
        String result = bundle.getString("data");
        textView = (TextView)findViewById(R.id.textView4);
        textView.setText(result);

        // 生成随机数
        Random rand = new Random();
        r = rand.nextInt(100);
        randtxt = (TextView)findViewById(R.id.textView3);
        randtxt.setText(r+"");

        btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                // 准备Bundle对象返回数据
                Bundle bundle1 = new Bundle();
                bundle1.putInt("random",r);
                Intent intent3 = new Intent();
                intent3.putExtras(bundle1);
                // 设置返回结果intent3, 并且其中包含传递的参数
                setResult(RESULT_OK,intent3);
                finish();
            }
        });
    }
}
