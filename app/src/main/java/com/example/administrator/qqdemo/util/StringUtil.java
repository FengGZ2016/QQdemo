package com.example.administrator.qqdemo.util;

/**
 * Created by Administrator on 2017/5/21.
 */

public class StringUtil {
    //长度3-20，首字符英文字符，其他除了英文还可以数字，下划线
    public static final String REGEX_USER_NAME = "^[a-zA-Z]\\w{2,19}$";
    //3-20位的数字
    public static final String REGEX_PASSWORD = "^[0-9]{3,20}$";

    public static boolean isValidUserName(String userName) {
        return userName.matches(REGEX_USER_NAME);
    }

    public static boolean isValidPassword(String password) {
        return password.matches(REGEX_PASSWORD);
    }
}
