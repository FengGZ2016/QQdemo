package com.example.administrator.qqdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/5/10.
 * Fragment的基类
 */

public abstract class BaseFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(getLayoutResId(),null);
        init();
        return view;
    }


    /**
     * 抽象方法
     * 子类必须实现，返回一个activity布局
     * */
    public abstract int getLayoutResId();



    /**
     * 初始化方法，初始化公共的功能，子类也可以复写，实现自己的初始化功能
     * */
    protected void init() {

    }
}
