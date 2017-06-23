package com.example.administrator.qqdemo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.example.administrator.qqdemo.R;
import com.hyphenate.chat.EMMessage;

/**
 * Created by Leon on 2016/12/30.
 */

public class ReceiveMessageItemView extends RelativeLayout {


    public ReceiveMessageItemView(Context context) {
        this(context, null);
    }

    public ReceiveMessageItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_receive_message,this);
    }


    /**
     * 绑定itemView
     * */
    public void bindView(EMMessage emMessage) {

    }
}
