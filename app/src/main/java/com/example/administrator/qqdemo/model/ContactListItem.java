package com.example.administrator.qqdemo.model;

/**
 * Created by Administrator on 2017/5/26.
 */
public class ContactListItem {
    public String url;//图片的url
    public String contact;//联系人的名字

    public boolean showFirstLetter = true;

    public String getFirstLetter() {
        return String.valueOf(contact.charAt(0)).toUpperCase();
    }
}
