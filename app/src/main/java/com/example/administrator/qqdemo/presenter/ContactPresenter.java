package com.example.administrator.qqdemo.presenter;

import com.example.administrator.qqdemo.model.ContactListItem;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public interface ContactPresenter {

    List<ContactListItem> getDataList();

    void loadContacts();

    void refresh();
}
