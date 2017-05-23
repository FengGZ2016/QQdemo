package com.example.administrator.qqdemo.ui;

import android.os.Handler;

import com.example.administrator.qqdemo.MainActivity;
import com.example.administrator.qqdemo.R;
import com.example.administrator.qqdemo.presenter.SplashPresenter;
import com.example.administrator.qqdemo.presenter.impl.SplashPresenterImpl;
import com.example.administrator.qqdemo.view.SplashView;

/**
 * Created by Administrator on 2017/5/10.
 * 欢迎界面的activity
 *
 * MVP模式：将Activity当作一个View
 */

public class SplashActivity extends BaseActivity implements SplashView{
    private Handler mhandler=new Handler();
    private static final int DELAY=3000;//3秒

    //持有一个Presenter层的引用 来调用P层业务逻辑的代码
    private SplashPresenter mSplashPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }


    @Override
    protected void init() {
        super.init();
        //初始Presenter
        mSplashPresenter = new SplashPresenterImpl(this);
        //检查登陆状态
        mSplashPresenter.checkLoginStatus();

    }


    /**
     * 跳转到登录界面
     * */
    private void navigateToLogin() {
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent intent=new Intent(SplashActivity.this,LoginActivity.class);
//                startActivity(intent);
                goTo(LoginActivity.class);
                finish();
            }
        },DELAY);
    }

    /**
     * 跳转到主界面
     * */
    private void navigateToMain() {
//        Intent intent=new Intent(SplashActivity.this, MainActivity.class);
//        startActivity(intent);
        goTo(MainActivity.class);
        finish();
    }




    /**
     * 登录状态
     * */
    @Override
    public void onLogin() {
        navigateToMain();
    }


    /**
     * 没有登录状态
     * */
    @Override
    public void onNotLogin() {
        navigateToLogin();
    }
}
