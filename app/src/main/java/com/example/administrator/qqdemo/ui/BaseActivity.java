package com.example.administrator.qqdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/5/10.
 * 基类:主要用来封装一些公共的功能
 */

public abstract class BaseActivity extends AppCompatActivity{

    /**
     * 注意：要选择带一个参数的方法
     * */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //由子类传一个布局
        setContentView(getLayoutResId());
        //初始化方法
        init();
    }


    /**
     * 初始化方法，初始化公共的功能，子类也可以复写，实现自己的初始化功能
     * */
    protected void init() {

    }

    /**
     * 抽象方法
     * 子类必须实现，返回一个activity布局
     * */
    public abstract int getLayoutResId();


}
