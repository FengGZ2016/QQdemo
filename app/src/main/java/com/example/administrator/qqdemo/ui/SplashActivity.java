package com.example.administrator.qqdemo.ui;

import android.content.Intent;
import android.os.Handler;

import com.example.administrator.qqdemo.MainActivity;
import com.example.administrator.qqdemo.R;

/**
 * Created by Administrator on 2017/5/10.
 * 欢迎界面的activity
 */

public class SplashActivity extends BaseActivity{
    private Handler mhandler=new Handler();
    private static final int DELAY=3000;//3秒

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }


    @Override
    protected void init() {
        super.init();
        //检查登陆状态
        if(checkLoginStatus()){
            //已经登录，跳转到主界面
            navigateToMain();

        }else {
            //没有登录，延时3秒，跳转到登录界面
            navigateToLogin();
        }
    }


    /**
     * 跳转到登录界面
     * */
    private void navigateToLogin() {
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },DELAY);
    }

    /**
     * 跳转到主界面
     * */
    private void navigateToMain() {
        Intent intent=new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    /**
     * 检查登录状态
     * @return  true:已经登录，跳转到主界面   false:没有登录，跳转到登录界面
     * */
    private boolean checkLoginStatus() {
        return false;
    }
}
