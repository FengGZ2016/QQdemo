package com.example.administrator.qqdemo.ui.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qqdemo.R;
import com.example.administrator.qqdemo.ui.LoginActivity;
import com.example.administrator.qqdemo.util.ThreadUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/23.
 */

public class DynamicFragment extends BaseFragment {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.logout)
    Button mLogout;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void init() {
        super.init();
        mTitle.setText(R.string.dynamic);
        String logout=String.format(getString(R.string.logout), EMClient.getInstance().getCurrentUser());
        mLogout.setText(logout);
    }

    @OnClick({R.id.back, R.id.add, R.id.logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:

                break;
            case R.id.add:

                break;
            case R.id.logout:
                //异步方法，在子线程中回调
                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        //退出登录成功,切换主线程
                        ThreadUtil.runOnMainThread(new Runnable() {
                            @Override
                            public void run() {
                                //跳转到登录页面
                                goTo(LoginActivity.class);
                            }
                        });

                    }

                    @Override
                    public void onError(int i, String s) {
                        //退出登录失败
                        ThreadUtil.runOnMainThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getContext(), "退出失败！", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onProgress(int i, String s) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
