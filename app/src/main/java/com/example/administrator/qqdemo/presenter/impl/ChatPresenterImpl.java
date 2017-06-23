package com.example.administrator.qqdemo.presenter.impl;

import com.example.administrator.qqdemo.presenter.ChatPresenter;
import com.example.administrator.qqdemo.view.ChatView;

/**
 * 作者：国富小哥
 * 日期：2017/6/23
 * Created by Administrator
 */

public class ChatPresenterImpl implements ChatPresenter{

    //View层的引用
    private ChatView mChatView;

    public ChatPresenterImpl(ChatView mChatView){
        this.mChatView=mChatView;
    }
    /**
     * 发送消息
     * */
    @Override
    public void sendMessage(String content, String contact) {

    }
}
