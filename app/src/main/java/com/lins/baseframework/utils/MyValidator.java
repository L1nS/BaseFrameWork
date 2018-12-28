package com.lins.baseframework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 2017/5/8.
 */

public class MyValidator {
    public static boolean isEmpty(String Value) {
        return (Value == null || Value.trim().equals(""));
    }

    public static boolean isEmpty(Object Value) {
        if (Value == null)
            return true;
        else
            return false;
    }

    /**
     * 是否需要更新版本。
     *
     * @param v
     * @return
     */
//    public static boolean isUpdate(String v) {
//        if (MyValidator.isEmpty(v))
//            return false;
//        boolean isUpdate = false;
//        String[] versionName = AppHelper.getPackageVersionName().split("\\.");
//        String[] version = v.split("\\.");
//        for (int i = 0; i < version.length; i++) {
//            if (Integer.parseInt(version[i]) > Integer.parseInt(versionName[i])) {
//                isUpdate = true;
//                break;
//            }
//        }
//
//        return isUpdate;
//    }

    /**
     * @param str 账号规则：数字和字母及中文。
     * @return
     */
    public static boolean isAccountLogin(String str) {
        Pattern pattern = Pattern.compile("[\\da-zA-Z\\u4e00-\\u9fa5]{3,15}");
        Matcher matcher = pattern.matcher(str);
        boolean res = matcher.matches();
        return res;
    }
    /**
     * @param str 账号规则：数字和字母及中文。
     * @return
     */
    public static boolean isAccountRegister(String str) {
        Pattern pattern = Pattern.compile("[\\da-zA-Z]{3,15}");
        Matcher matcher = pattern.matcher(str);
        boolean res = matcher.matches();
        return res;
    }

    /**
     * @param str 密码规则：数字和字母，不限长度
     * @return
     */
    public static boolean isPassword(String str) {
        Pattern pattern = Pattern.compile("[\\da-zA-Z]{1,}");
        Matcher matcher = pattern.matcher(str);
        boolean res = matcher.matches();
        return res;
    }

    /**
     * 手机号
     *
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {
        Pattern pattern = Pattern.compile("[1][3456789]\\d{9}");
        Matcher matcher = pattern.matcher(str);
        boolean res = matcher.matches();
        return res;
    }
}
