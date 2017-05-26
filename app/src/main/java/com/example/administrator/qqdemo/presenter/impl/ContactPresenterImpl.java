package com.example.administrator.qqdemo.presenter.impl;

import com.example.administrator.qqdemo.model.ContactListItem;
import com.example.administrator.qqdemo.presenter.ContactPresenter;
import com.example.administrator.qqdemo.util.ThreadUtil;
import com.example.administrator.qqdemo.view.ContactView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */

public class ContactPresenterImpl implements ContactPresenter{
    private List<ContactListItem> mContactListItems;
    private ContactView mContactView;
    public ContactPresenterImpl(ContactView mContactView){
        mContactListItems=new ArrayList<>();
        this.mContactView=mContactView;
    }

    @Override
    public List<ContactListItem> getDataList() {
        return mContactListItems;
    }

    /**
     * 加载数据
     * */
    @Override
    public void loadContacts() {
        //同步方法，要在子线程上做
        ThreadUtil.runOnBackgroundThread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<String> usernames= EMClient.getInstance().contactManager().getAllContactsFromServer();
                    //如果没有异常，就加载联系人成功
                    for (int i=0;i<usernames.size();i++){
                        ContactListItem contactListItem=new ContactListItem();
                        contactListItem.contact=usernames.get(i);
                        mContactListItems.add(contactListItem);
                    }

                    //对联系人进行排序，根据首字符
                    Collections.sort(mContactListItems, new Comparator<ContactListItem>() {
                        @Override
                        public int compare(ContactListItem o1, ContactListItem o2) {
                            //升序排序
                            return o1.contact.charAt(0) - o2.contact.charAt(0);
                        }
                    });
                    //判断后一个item跟前一个item的首字符是否一致，一致则不显示首字符
                    for(int i=0;i<mContactListItems.size();i++){
                        ContactListItem item=mContactListItems.get(i);
                        if (i>0&&item.getFirstLetter().equals(mContactListItems.get(i-1).getFirstLetter())){
                            item.showFirstLetter=false;//不显示首字符
                        }
                    }

                    //通知view数据加载成功
                    ThreadUtil.runOnMainThread(new Runnable() {
                        @Override
                        public void run() {
                            mContactView.onLoadContactsSuccess();
                        }
                    });


                } catch (HyphenateException e) {
                    e.printStackTrace();
                    //如果出现异常，加载失败
              ThreadUtil.runOnMainThread(new Runnable() {
                  @Override
                  public void run() {
                      mContactView.onLoadContactsFailed();
                  }
              });
                }
            }
        });

    }

    /**
     * 重新加载数据
     * */
    @Override
    public void refresh() {
        //清空老数据
        mContactListItems.clear();
        loadContacts();
    }
}
