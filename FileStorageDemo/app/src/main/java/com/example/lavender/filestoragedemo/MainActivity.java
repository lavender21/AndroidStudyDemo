package com.example.lavender.filestoragedemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_input,btn_output,btn_add;
    private TextView show;
    private EditText inputData;
    public final static String FILENAME = "myfile.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_input = (Button)findViewById(R.id.inputdata);
        btn_output = (Button)findViewById(R.id.output);
        btn_add = (Button)findViewById(R.id.adddata);
        show = (TextView)findViewById(R.id.show);
        inputData = (EditText)findViewById(R.id.editText);

        btn_input.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_output.setOnClickListener(this);

    }
    // btn点击事件触发
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inputdata:
                InputFile(Context.MODE_PRIVATE, "文件写入成功!");
                break;
            case R.id.adddata:
                InputFile(Context.MODE_APPEND, "文件追加成功!");
                break;
            case R.id.output:
                outputFile();
                break;
        }
    }

    // 写入数据至文件
    public  void InputFile(int mode, String message){
        FileOutputStream fos = null;
        try{
            fos = openFileOutput(FILENAME, mode);
            String text = inputData.getText().toString();
            fos.write(text.getBytes());
            inputData.setText("");
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fos != null)
            {
                try{
                    fos.flush();
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    // 从文件读取数据
    public  void outputFile (){
        FileInputStream fis = null;
        try{
            fis = openFileInput(FILENAME);
            if (fis.available() == 0)
            {
                show.setText("对不起，该文件内容为空！");
                return;
            }
            byte[] bytes = new byte[fis.available()];
            while(fis.read(bytes) != -1);
            String text = new String(bytes);
            show.setText(text);
            Toast.makeText(MainActivity.this, "文件读取成功", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
