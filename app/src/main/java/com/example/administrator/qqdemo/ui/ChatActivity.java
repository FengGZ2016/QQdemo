package com.example.administrator.qqdemo.ui;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qqdemo.R;
import com.example.administrator.qqdemo.presenter.ChatPresenter;
import com.example.administrator.qqdemo.presenter.impl.ChatPresenterImpl;
import com.example.administrator.qqdemo.view.ChatView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：国富小哥
 * 日期：2017/6/23
 * Created by Administrator
 */

public class ChatActivity extends BaseActivity implements ChatView{
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.message)
    EditText mMessage;
    @BindView(R.id.send)
    Button mSend;

    private String mContact;//联系人的名字
    private ChatPresenter mChatPresenter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_chat;
    }


    @Override
    protected void init() {
        super.init();
        mChatPresenter=new ChatPresenterImpl(this);
        mBack.setVisibility(View.VISIBLE);
        mContact = getIntent().getStringExtra("contact");
        String title=String.format(getString(R.string.contact),mContact);
        mTitle.setText(title);
        //监听文本输入的变化
        mMessage.addTextChangedListener(mTextWatcher);
        //设置软键盘的监听事件
        mMessage.setOnEditorActionListener(mOnEditorActionListener);
        initRecyclerview();
    }

    /**
     * 初始化recyclerview
     * */
    private void initRecyclerview() {
        //设置recyclerview根据用户输入的消息固定大小
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * 软键盘的监听器
     * */
    private TextView.OnEditorActionListener mOnEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage();
                return true;
            }
            return false;
        }
    };

    @OnClick({R.id.back, R.id.send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.send:
               //发送消息
                sendMessage();
                break;
        }
    }

    /**
     * 发送消息
     * */
    private void sendMessage() {
        //隐藏软键盘
        hideKeyboard();
        //消息内容
        String content=mMessage.getText().toString();
        //调用presenter层方法发送消息
        mChatPresenter.sendMessage(content,mContact);//消息内容+好友昵称
    }

    /**
     * 监听文本变化的
     * */
    private TextWatcher mTextWatcher = new TextWatcher() {
        //变化之前
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        //正在变化
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
        //变化之后
        @Override
        public void afterTextChanged(Editable s) {
            //如果用户输入的文本长度不大于0，则发送按钮应该是disable
            //大于0 则是enbale
            mSend.setEnabled(s.toString().length() > 0);
        }
    };

    /**
     * 开始发送消息了
     * */
    @Override
    public void startSendMessage() {

    }

    /**
     * 发送消息成功了
     * */
    @Override
    public void sendMessageSuccess() {
        Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 发送消息失败了
     * */
    @Override
    public void sendMessageFailed() {
        Toast.makeText(this, "发送失败", Toast.LENGTH_SHORT).show();
    }
}
