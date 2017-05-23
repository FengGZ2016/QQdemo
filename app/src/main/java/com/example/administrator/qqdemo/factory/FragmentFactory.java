package com.example.administrator.qqdemo.factory;

import android.support.v4.app.Fragment;

import com.example.administrator.qqdemo.R;
import com.example.administrator.qqdemo.ui.fragment.ContactFragment;
import com.example.administrator.qqdemo.ui.fragment.ConversationFragment;
import com.example.administrator.qqdemo.ui.fragment.DynamicFragment;

/**
 * Created by Administrator on 2017/5/23.
 * 工厂模式,工厂类
 */

public class FragmentFactory {
    private static FragmentFactory sFragmentFactory;
    private Fragment mConversationFragment;
    private Fragment mContactFragment;
    private Fragment mDynamicFragment;

    /**
     * 单例模式
     * @return
     */
    public static FragmentFactory getInstance() {
        if (sFragmentFactory == null) {
            synchronized (FragmentFactory.class) {
                if (sFragmentFactory == null) {
                    sFragmentFactory = new FragmentFactory();
                }
            }
        }
        return sFragmentFactory;
    }

    /**
     * 根据不同的tabId来生成不同fragment
     *
     * @param tabId
     * @return
     */
    public Fragment getFragment(int tabId) {
        switch (tabId) {
            case R.id.tab_conversation:
                return getConversationFragment();
            case R.id.tab_contacts:
                return getContactFragment();
            case R.id.tab_dynamic:
                return getDynamicFragment();
        }
        return null;
    }


    public Fragment getConversationFragment() {
        if (mConversationFragment == null) {
            mConversationFragment = new ConversationFragment();
        }
        return mConversationFragment;
    }

    public Fragment getContactFragment() {
        if (mContactFragment == null) {
            mContactFragment = new ContactFragment();
        }
        return mContactFragment;
    }

    public Fragment getDynamicFragment() {
        if (mDynamicFragment == null) {
            mDynamicFragment = new DynamicFragment();
        }
        return mDynamicFragment;
    }
}
