package com.example.administrator.qqdemo.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.qqdemo.R;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMMessageBody;
import com.hyphenate.chat.EMTextMessageBody;
import com.hyphenate.util.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：国富小哥
 * 日期：2017/6/23
 * Created by Administrator
 */

public class SendMessageItemView extends RelativeLayout {

    @BindView(R.id.timestamp)
    TextView mTimestamp;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.content)
    TextView mContent;
    @BindView(R.id.send_message_progress)
    ImageView mSendMessageProgress;

    public SendMessageItemView(Context context) {
        this(context, null);
    }

    public SendMessageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_send_message, this);
        ButterKnife.bind(this, this);
    }

    /**
     * 绑定itemView
     */
    public void bindView(EMMessage emMessage) {
        //消息时间
        long msgTime=emMessage.getMsgTime();
        mTimestamp.setText(DateUtils.getTimestampString(new Date(msgTime)));
        //消息体
        EMMessageBody body=emMessage.getBody();
        if (body instanceof EMTextMessageBody){
            //如果消息体是文本消息
            mContent.setText(((EMTextMessageBody) body).getMessage());
        }else {
            mContent.setText("非文本消息");
        }


        switch (emMessage.status()) {
            case INPROGRESS:
                //开始发送的状态
                mSendMessageProgress.setVisibility(VISIBLE);
                mSendMessageProgress.setImageResource(R.drawable.send_message_progress);
                //启动帧动画
                AnimationDrawable drawable = (AnimationDrawable) mSendMessageProgress.getDrawable();
                //mProgress.getBackground();
                drawable.start();
                break;
            case SUCCESS:
                //发送成功的状态
                mSendMessageProgress.setVisibility(GONE);
                break;
            case FAIL:
                //发送失败的状态
                mSendMessageProgress.setImageResource(R.mipmap.msg_error);
                break;
        }
    }
}
