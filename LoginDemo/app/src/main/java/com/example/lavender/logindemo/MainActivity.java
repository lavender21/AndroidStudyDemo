package com.example.lavender.logindemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {
    private EditText txt_username, txt_password;
    private CheckBox chk;
    private Button btn_login;
    private final static String USERNAME = "uname";
    private final static String USERPASS = "upass";
    private final static String LOGIN = "login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_username = (EditText)findViewById(R.id.txt_username);
        txt_password = (EditText)findViewById(R.id.txt_password);
        chk = (CheckBox)findViewById(R.id.checkBox);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_username.setText("");
                txt_password.setText("");
                chk.setChecked(false);
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,subActivity.class);
                startActivity(intent);
            }
        });
    }

    // 用sharedPreferences进行存储
    public void RemembererMe (String uname, String upass) {
        // 创建实例
        SharedPreferences sp = getSharedPreferences(LOGIN, MODE_PRIVATE);
        // 需要调用edit()方法才可以处于编辑状态
        SharedPreferences.Editor editor = sp.edit();
        // 保存数据
        editor.putString(USERNAME,uname);
        editor.putString(USERPASS,upass);
        // 提交数据
        editor.commit();
    }


    // 从sharedPreferences里面取数据
    public void GetRememberData(){
        SharedPreferences sp = getSharedPreferences(LOGIN,MODE_PRIVATE);
        // 取出存放的数据，如果没有用默认值null代替。
        String username = sp.getString(USERNAME, null);
        String password = sp.getString(USERPASS, null);
        if (username != null && password != null)
        {
            txt_username.setText(username);
            txt_password.setText(password);
            chk.setChecked(true);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        // 启动的时候查看是否保存了，如果保存了显示。
        GetRememberData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // 停止的时候看是否选中保存，如果选择了就保存。
        if (chk.isChecked())
        {
            this.RemembererMe(txt_username.getText().toString(), txt_password.getText().toString());
        }
    }

}
