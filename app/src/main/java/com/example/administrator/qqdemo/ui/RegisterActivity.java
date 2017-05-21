package com.example.administrator.qqdemo.ui;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qqdemo.R;
import com.example.administrator.qqdemo.presenter.RegisterPresenter;
import com.example.administrator.qqdemo.presenter.impl.RegisterPresenterImpl;
import com.example.administrator.qqdemo.view.RegisterView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/17.
 */
public class RegisterActivity extends BaseActivity implements RegisterView{

    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.confirm_password)
    EditText mConfirmPassword;
    @BindView(R.id.btn_register)
    Button mBtnRegister;

    private RegisterPresenter registerPresenter;



    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }



    @Override
    protected void init() {
        super.init();
        registerPresenter=new RegisterPresenterImpl(this);
        //设置软件盘ACTION键的监听器，当用户输入完确认密码后，点击软件盘的完成按钮，同样触发注册
        mConfirmPassword.setOnEditorActionListener(mOnEditorActionListener);

        mConfirmPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    register();
                    return true;
                }
                return false;
            }
        });
    }
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

    @OnClick(R.id.btn_register)
    public void onClick() {
        register();

    }

    private void register() {
        String username=mUserName.getText().toString().trim();
        String pass=mPassword.getText().toString().trim();
        String confirmPass=mConfirmPassword.getText().toString().trim();
        registerPresenter.register(username,pass,confirmPass);


    }

    /**
     * 注册成功
     * */
    @Override
    public void onRegisterSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 注册失败
     * */
    @Override
    public void onRegisterFailed() {
        Toast.makeText(this, "注册失败！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordError() {
      mPassword.setError(getString(R.string.password_error));
    }

    @Override
    public void onUserNameError() {
        mUserName.setError(getString(R.string.user_name_error));
    }

    @Override
    public void onConfirmPasswordError() {
        mConfirmPassword.setError(getString(R.string.confirm_password_error));
    }
}
