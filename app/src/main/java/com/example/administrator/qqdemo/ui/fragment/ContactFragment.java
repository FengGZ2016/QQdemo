package com.example.administrator.qqdemo.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qqdemo.R;
import com.example.administrator.qqdemo.adapter.ContactAdapter;
import com.example.administrator.qqdemo.model.ContactListItem;
import com.example.administrator.qqdemo.presenter.ContactPresenter;
import com.example.administrator.qqdemo.presenter.impl.ContactPresenterImpl;
import com.example.administrator.qqdemo.ui.AddActivity;
import com.example.administrator.qqdemo.view.ContactView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/23.
 */

public class ContactFragment extends BaseFragment implements ContactView {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.add)
    ImageView mAdd;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;


    private ContactAdapter mContactAdapter;
    private ContactPresenter mContactPresenter;
    private List<ContactListItem> mContactListItems;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_contact;
    }

    @Override
    protected void init() {
        super.init();
        mContactPresenter = new ContactPresenterImpl(this);
        mTitle.setText(R.string.contacts);
        mAdd.setVisibility(View.VISIBLE);
        mContactPresenter.loadContacts();
        initRecyclerView();
        mSwipeRefreshLayout.setColorSchemeResources(R.color.qq_blue, R.color.colorPrimary);
        //刷新的响应事件
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
    }

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            //重新加载数据
            mContactPresenter.refresh();
        }
    };

    private void initRecyclerView() {
        mContactListItems = mContactPresenter.getDataList();
        mContactAdapter = new ContactAdapter(getContext(), mContactListItems);
        mRecyclerView.setHasFixedSize(true);//设置RecyclerView有固定的大小
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mContactAdapter);

    }

    @OnClick({R.id.back, R.id.add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add:
                goTo(AddActivity.class);
                break;
        }
    }

    /**
     * 数据加载成功
     */
    @Override
    public void onLoadContactsSuccess() {
        Toast.makeText(getContext(), getString(R.string.load_contacts_success), Toast.LENGTH_SHORT).show();
        //刷新列表
        mContactAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 数据加载失败
     */
    @Override
    public void onLoadContactsFailed() {
        Toast.makeText(getContext(), getString(R.string.load_contacts_failed), Toast.LENGTH_SHORT).show();
        mSwipeRefreshLayout.setRefreshing(false);
    }


}
