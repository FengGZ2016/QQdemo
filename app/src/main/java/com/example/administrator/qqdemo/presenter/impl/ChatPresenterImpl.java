package com.example.administrator.qqdemo.presenter.impl;

import com.example.administrator.qqdemo.presenter.ChatPresenter;
import com.example.administrator.qqdemo.util.ThreadUtil;
import com.example.administrator.qqdemo.view.ChatView;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：国富小哥
 * 日期：2017/6/23
 * Created by Administrator
 */

public class ChatPresenterImpl implements ChatPresenter{

    //View层的引用
    private ChatView mChatView;
    //消息列表
    private List<EMMessage> mEMMessages;

    public ChatPresenterImpl(ChatView mChatView){
        this.mChatView=mChatView;
        mEMMessages=new ArrayList<>();
    }
    /**
     * 发送消息
     * */
    @Override
    public void sendMessage(final String content, final String userName) {
        ThreadUtil.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
                EMMessage message = EMMessage.createTxtSendMessage(content, userName);
                //给消息设置发送状态的监听回调
                message.setMessageStatusCallback(mEMCallBack);
                mEMMessages.add(message);
                ThreadUtil.runOnMainThread(new Runnable() {
                    @Override
                    public void run() {
                        //当发送一条消息时，通知view层刷新列表，显示一条正在发送的消息
                        mChatView.startSendMessage();
                    }
                });
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
            }
        });
    }

    /**
     * 获取消息列表
     * */
    @Override
    public List<EMMessage> getEMMessageList() {
        return mEMMessages;
    }

    /**
     * 监听发送状态的回调
     * */
    EMCallBack mEMCallBack=new EMCallBack() {
        @Override
        public void onSuccess() {
            ThreadUtil.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    //告诉View层发送消息成功
                    mChatView.sendMessageSuccess();
                }
            });
        }

        @Override
        public void onError(int i, String s) {
            ThreadUtil.runOnMainThread(new Runnable() {
                @Override
                public void run() {
                    //告诉View层发送消息失败
                    mChatView.sendMessageFailed();
                }
            });
        }

        @Override
        public void onProgress(int i, String s) {

        }
    };
}
