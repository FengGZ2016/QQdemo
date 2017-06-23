package com.example.administrator.qqdemo.ui;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.qqdemo.MainActivity;
import com.example.administrator.qqdemo.R;
import com.example.administrator.qqdemo.presenter.LoginPresenter;
import com.example.administrator.qqdemo.presenter.impl.LoginPresenterImpl;
import com.example.administrator.qqdemo.view.LoginView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/10.
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements LoginView{
    private LoginPresenter loginPresenter;


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

    @Override
    protected void init() {
        super.init();
        loginPresenter=new LoginPresenterImpl(this);
        //设置软件盘ACTION键的监听器，当用户输入完确认密码后，点击软件盘的完成按钮，同样触发注册
        mPassword.setOnEditorActionListener(mOnEditorActionListener);


    }
    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login();//注册
                return true;//处理事件
            }
            return false;
        }
    };

    @OnClick({R.id.btn_login, R.id.new_user})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.new_user:
//                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
//                startActivity(intent);
                goTo(RegisterActivity.class);
                break;
        }
    }

    private void login() {
        String username=mUserName.getText().toString().trim();
        String password=mPassword.getText().toString().trim();
        //隐藏软件盘
        hideKeyboard();
        loginPresenter.login(username,password);

    }

    /**
     * 开始登录
     * */
    @Override
    public void startLogin() {
        //弹出进度条
        showProgressDialog(getString(R.string.logining));

    }


    @Override
    public void loginSuccess() {
        hideProgressDialog();
        //登录成功跳转到主界面
        goTo(MainActivity.class);
    }

    @Override
    public void loginError() {
        hideProgressDialog();
    }

    /**
     * 密码输入不合法
     * */
    @Override
    public void onPasswordError() {
        mPassword.setError(getString(R.string.password_error));
    }

    /**
     * 用户名输入不合法
     * */
    @Override
    public void onUserNameError() {
        mUserName.setError(getString(R.string.user_name_error));
    }
}
