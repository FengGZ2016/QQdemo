package com.example.administrator.qqdemo.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.qqdemo.R;
import com.example.administrator.qqdemo.view.AddFriendView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/26.
 */

public class AddActivity extends BaseActivity implements AddFriendView {
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.user_name)
    EditText mUserName;
    @BindView(R.id.search)
    ImageView mSearch;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.friend_not_found)
    TextView mFriendNotFound;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_add;
    }



    @OnClick({R.id.back, R.id.add, R.id.search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;

            case R.id.search:
                break;
        }
    }
}
