package com.example.administrator.qqdemo.ui;


import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.qqdemo.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：国富小哥
 * 日期：2017/6/23
 * Created by Administrator
 */

public class ChatActivity extends BaseActivity {
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

    @Override
    public int getLayoutResId() {
        return R.layout.activity_chat;
    }


    @Override
    protected void init() {
        super.init();
        mBack.setVisibility(View.VISIBLE);
        mContact = getIntent().getStringExtra("contact");
        String title=String.format(getString(R.string.contact),mContact);
        mTitle.setText(title);
        //监听文本输入的变化
        mMessage.addTextChangedListener(mTextWatcher);

    }

    @OnClick({R.id.back, R.id.send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:

                break;
            case R.id.send:
                finish();
                break;
        }
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
}
