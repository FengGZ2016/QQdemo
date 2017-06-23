package com.example.administrator.qqdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.qqdemo.R;
import com.hyphenate.chat.EMMessage;

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

    }
}
