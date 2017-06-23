package com.example.administrator.qqdemo.widget;

import android.content.Context;
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
 * Created by Leon on 2016/12/30.
 */

public class ReceiveMessageItemView extends RelativeLayout {


    @BindView(R.id.timestamp)
    TextView mTimestamp;
    @BindView(R.id.avatar)
    ImageView mAvatar;
    @BindView(R.id.content)
    TextView mContent;

    public ReceiveMessageItemView(Context context) {
        this(context, null);
    }

    public ReceiveMessageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_receive_message, this);
        ButterKnife.bind(this, this);
    }


    /**
     * 绑定itemView
     */
    public void bindView(EMMessage emMessage) {
        long msgTime = emMessage.getMsgTime();
        mTimestamp.setText(DateUtils.getTimestampString(new Date(msgTime)));
        EMMessageBody body = emMessage.getBody();
        if (body instanceof EMTextMessageBody) {
            mContent.setText(((EMTextMessageBody) body).getMessage());
        } else {
            mContent.setText("非文本消息");
        }
    }
}
