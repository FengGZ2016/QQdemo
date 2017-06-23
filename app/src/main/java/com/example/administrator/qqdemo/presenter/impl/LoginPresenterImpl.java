package com.example.administrator.qqdemo.presenter.impl;

import android.util.Log;

import com.example.administrator.qqdemo.presenter.LoginPresenter;
import com.example.administrator.qqdemo.util.StringUtil;
import com.example.administrator.qqdemo.util.ThreadUtil;
import com.example.administrator.qqdemo.view.LoginView;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by Administrator on 2017/5/23.
 */

public class LoginPresenterImpl implements LoginPresenter{
private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView=loginView;
    }

    @Override
    public void login(String username, String password) {
        if (StringUtil.isValidUserName(username)) {
            if (StringUtil.isValidPassword(password)) {
                loginView.startLogin();
                loginEaseMob(username,password);
            } else {
                loginView.onPasswordError();

            }
        } else {
            loginView.onUserNameError();
        }
    }


    /**
     * 登录到环信
     *
     * @param username
     * @param password*/
    private void loginEaseMob(final String username, final String password) {
        //在子线程上登录
        ThreadUtil.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                EMClient.getInstance().login(username, password, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        Log.d("main", "登录聊天服务器成功！");
                        //通知view层
                        ThreadUtil.runOnMainThread(new Runnable() {
                            @Override
                            public void run() {
                                loginView.loginSuccess();
                            }
                        });
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.d("main", "登录聊天服务器失败！");
                     ThreadUtil.runOnMainThread(new Runnable() {
                         @Override
                         public void run() {
                             loginView.loginError();
                         }
                     });
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
            }
        });


    }
}
