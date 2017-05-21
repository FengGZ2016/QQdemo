package com.example.administrator.qqdemo.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.qqdemo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/10.
 * 登录界面
 */
public class LoginActivity extends BaseActivity {


    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.new_user)
    TextView mNewUser;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }



    @OnClick({R.id.btn_login, R.id.new_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                break;
            case R.id.new_user:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
