package com.example.administrator.qqdemo.presenter.impl;

import android.util.Log;

import com.example.administrator.qqdemo.presenter.RegisterPresenter;
import com.example.administrator.qqdemo.util.StringUtil;
import com.example.administrator.qqdemo.view.RegisterView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2017/5/21.
 */

public class RegisterPresenterImpl implements RegisterPresenter{
    private RegisterView registerView;
    private final String TAG="RegisterPresenterImpl";

    public RegisterPresenterImpl(RegisterView registerView){
        this.registerView=registerView;
    }

    @Override
    public void register(String userName, String password, String confirmPassword) {
        //检查用户名密码
        //检查用户是否符合要求
        if (StringUtil.isValidUserName(userName)) {
            //继续检查密码
            if (StringUtil.isValidPassword(password)) {
                //密码正确
                //检查密码和确认密码是否一致
                if (password.equals(confirmPassword)) {
                    //完全ok
                    registerBmob(userName, password);
                } else {
                    //确认密码出错
                    //通知view层确认密码有问题
                    registerView.onConfirmPasswordError();
                }

            } else {
                //不是有效的密码
                //通知view层密码不符合规范
                registerView.onPasswordError();
            }
        } else {
            //不是有效用户名
            //通知view层用户名出错
            registerView.onUserNameError();
        }

    }

    /**
     * 注册到Bmob
     * */
    private void registerBmob(final String userName, final String password) {
        BmobUser user=new BmobUser();
        user.setUsername(userName);
        user.setPassword(password);
        user.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e==null){
                    //注册成功,通知view层
                   // registerView.onRegisterSuccess();
                    //注册环信
                    registerEaseMob(userName,password);
                }else {
                    //注册失败，通知view层
                    registerView.onRegisterFailed();
                }
            }
        });
    }

    private void registerEaseMob(final String userName, final String password) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //注册失败会抛出HyphenateException
                try {
                    EMClient.getInstance().createAccount(userName, password);//同步方法
                    //不抛异常表示注册成功
                    Log.d(TAG, "run: 注册成功" );
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    //注册失败
                    Log.d(TAG, "run: 注册失败");
                }
            }
        }).start();
    }
}
