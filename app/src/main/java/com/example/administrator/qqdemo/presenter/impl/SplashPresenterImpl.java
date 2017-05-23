package com.example.administrator.qqdemo.presenter.impl;

import com.example.administrator.qqdemo.presenter.SplashPresenter;
import com.example.administrator.qqdemo.view.SplashView;
import com.hyphenate.chat.EMClient;

/**
 * Created by Administrator on 2017/5/11.
 */

public class SplashPresenterImpl implements SplashPresenter{

    //持有一个View层的引用，用来通知view层
    private SplashView mSplashView;

    public SplashPresenterImpl(SplashView mSplashView){
        this.mSplashView=mSplashView;
    }

    @Override
    public void checkLoginStatus() {
        if (isLogin()){
            //如果已经登录，通知view层
            mSplashView.onLogin();
        }else {
            //如果没有登录,通知view层
            mSplashView.onNotLogin();
        }

    }

    /**
     * 判断是否已经登录到环信
     * */
    private boolean isLogin() {
        return EMClient.getInstance().isLoggedInBefore()&&EMClient.getInstance().isConnected();
    }
}
