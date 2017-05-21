package com.example.administrator.qqdemo.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.qqdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/17.
 */
public class RegisterActivity extends BaseActivity {

    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.confirm_password)
    EditText mConfirmPassword;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                register();//注册
                return true;//处理事件
            }
            return false;
        }
    };

    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected void init() {
        super.init();
        //设置软件盘ACTION键的监听器，当用户输入完确认密码后，点击软件盘的完成按钮，同样触发注册
        mConfirmPassword.setOnEditorActionListener(mOnEditorActionListener);
    }

    @OnClick(R.id.btn_register)
    public void onClick() {
        register();

    }

    private void register() {
        String username=mUserName.getText().toString().trim();
    }
}
