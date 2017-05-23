package com.example.administrator.qqdemo;

import android.support.annotation.IdRes;
import android.widget.FrameLayout;

import com.example.administrator.qqdemo.ui.BaseActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    //创建BottomBar监听器
    private OnTabSelectListener mOnTabSelectListener=new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {

        }
    };

    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        initBadge();
        //给bottomBar设置监听器
        mBottomBar.setOnTabSelectListener(mOnTabSelectListener);
    }

    /**
     * 初始化未读消息
     * */
    private void initBadge() {
        BottomBarTab nearby = mBottomBar.getTabWithId(R.id.tab_conversation);
        //设置未读消息5条
        nearby.setBadgeCount(5);
    }

}
