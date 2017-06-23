package com.example.administrator.qqdemo.presenter;

import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * 作者：国富小哥
 * 日期：2017/6/23
 * Created by Administrator
 */

public interface ChatPresenter {
    void sendMessage(String content, String contact);
    List<EMMessage> getEMMessageList();
}
