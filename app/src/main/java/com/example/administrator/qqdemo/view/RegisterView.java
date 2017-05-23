package com.example.administrator.qqdemo.view;

/**
 * Created by Administrator on 2017/5/21.
 */

public interface RegisterView {


    void onRegisterSuccess();

    void onRegisterFailed();

    void onPasswordError();

    void onUserNameError();

    void onConfirmPasswordError();

    void onStartRegister();
}
