package com.example.administrator.qqdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.administrator.qqdemo.widget.ReceiveMessageItemView;
import com.example.administrator.qqdemo.widget.SendMessageItemView;
import com.hyphenate.chat.EMMessage;

import java.util.List;

/**
 * 作者：国富小哥
 * 日期：2017/6/23
 * Created by Administrator
 */

public class MessageListAdapter extends RecyclerView.Adapter{

   private Context mContext;//上下文
    private List<EMMessage> mEMMessages;//消息列表
    public static final int ITEM_TYPE_SEND = 0;//发送
    public static final int ITEM_TYPE_RECEIVE = 1;//接收

    public MessageListAdapter(Context mContext,List<EMMessage> mEMMessage){
        this.mContext=mContext;
        this.mEMMessages=mEMMessage;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据消息类型创建不同的ViewHolder
        if (viewType==ITEM_TYPE_SEND){
            //发送的消息类型
            return new sendMessageListViewHolder(new SendMessageItemView(mContext));
        }else {
            //接收的消息类型
           return new recieverMessageListHolder(new ReceiveMessageItemView(mContext));
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof sendMessageListViewHolder) {
            ((sendMessageListViewHolder) holder).mSendMessageItemView.bindView(mEMMessages.get(position));
        } else if (holder instanceof recieverMessageListHolder) {
            ((recieverMessageListHolder) holder).mReceiveMessageItemView.bindView(mEMMessages.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mEMMessages.size();
    }

    /**
     * 返回item的类型
     * */
    @Override
    public int getItemViewType(int position) {
        EMMessage emMessage=mEMMessages.get(position);
        return emMessage.direct()==EMMessage.Direct.SEND?ITEM_TYPE_SEND:ITEM_TYPE_RECEIVE;
    }


    /**
     * 发送消息的ViewHolder
     * */
    public class sendMessageListViewHolder extends RecyclerView.ViewHolder{

        private SendMessageItemView mSendMessageItemView;

        public sendMessageListViewHolder(SendMessageItemView itemView) {
            super(itemView);
            mSendMessageItemView=itemView;
        }
    }

    /**
     * 接收消息的ViewHolder
     * */
    public class recieverMessageListHolder extends RecyclerView.ViewHolder{

        private ReceiveMessageItemView mReceiveMessageItemView;

        public recieverMessageListHolder(ReceiveMessageItemView itemView) {
            super(itemView);
            mReceiveMessageItemView=itemView;
        }
    }
}
