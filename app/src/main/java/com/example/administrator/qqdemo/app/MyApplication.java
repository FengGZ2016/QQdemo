package com.example.administrator.qqdemo.app;

import android.app.Application;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BuildConfig;

/**
 * Created by Administrator on 2017/5/21.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化全局变量，第三方包

        initBmob();
        initEaseMob();
    }

    /**
     * 初始化Bmob
     * */
    private void initBmob() {
        Bmob.initialize(getApplicationContext(),"1483657134359457ad9d152ac18ca6cd ");
    }

    /**
     * 初始化环信
     * */
    private void initEaseMob() {
        //创建环信配置
        EMOptions options = new EMOptions();
        //是否默认接受好友请求, true表示默认同意接受好友请求
        options.setAcceptInvitationAlways(true);
        //初始化
        EMClient.getInstance().init(getApplicationContext(), options);

        //如果是debug版本的apk,打开debug模式
           if (BuildConfig.DEBUG){
            EMClient.getInstance().setDebugMode(true);
        }
    }
}
