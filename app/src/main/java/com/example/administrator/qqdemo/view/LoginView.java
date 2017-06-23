package com.example.administrator.qqdemo.view;

/**
 * Created by Administrator on 2017/5/23.
 */

public interface LoginView {
    void startLogin();

    void loginSuccess();

    void loginError();

    void onPasswordError();

    void onUserNameError();
}
