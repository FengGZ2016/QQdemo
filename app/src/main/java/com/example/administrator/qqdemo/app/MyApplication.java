package com.example.administrator.qqdemo.app;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/5/21.
 */

public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化全局变量，第三方包
        Bmob.initialize(getApplicationContext(),"1483657134359457ad9d152ac18ca6cd ");
    }
}
