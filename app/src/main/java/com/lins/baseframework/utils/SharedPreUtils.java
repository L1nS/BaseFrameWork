package com.lins.baseframework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.lins.baseframework.base.Constants;
import com.lins.baseframework.base.MyApp;


/**
 * Created by Admin on 2017/5/10.
 */

public class SharedPreUtils {
    private static final String SHARED_NAME = "KuaiYan_SP";
    private static SharedPreUtils sInstance;
    private static SharedPreferences sharedReadable;
    private static SharedPreferences.Editor sharedWritable;

    private SharedPreUtils() {
        sharedReadable = MyApp.getAppContext()
                .getSharedPreferences(SHARED_NAME, Context.MODE_MULTI_PROCESS);
        sharedWritable = sharedReadable.edit();
    }

    public static SharedPreUtils getInstance() {
        if (sInstance == null) {
            synchronized (SharedPreUtils.class) {
                if (sInstance == null) {
                    sInstance = new SharedPreUtils();
                }
            }
        }
        return sInstance;
    }

    public String getString(String key, String defValue) {
        return sharedReadable.getString(key, defValue);
    }

    public void putString(String key, String value) {
        sharedWritable.putString(key, value);
        sharedWritable.apply();
    }

    public void putInt(String key, int value) {
        sharedWritable.putInt(key, value);
        sharedWritable.apply();
    }

    public void putBoolean(String key, boolean value) {
        sharedWritable.putBoolean(key, value);
        sharedWritable.apply();
    }

    public int getInt(String key, int def) {
        return sharedReadable.getInt(key, def);
    }

    public boolean getBoolean(String key, boolean def) {
        return sharedReadable.getBoolean(key, def);
    }

    public static void putValue(Context context, String key, int value) {
        SharedPreferences.Editor sp = context.getSharedPreferences(Constants.USER_SP, Context.MODE_PRIVATE).edit();
        sp.putInt(key, value);
        sp.commit();
    }

    public static void putValue(Context context, String key, boolean value) {
        SharedPreferences.Editor sp = context.getSharedPreferences(Constants.USER_SP, Context.MODE_PRIVATE).edit();
        sp.putBoolean(key, value);
        sp.commit();
    }

    public static void putValue(Context context, String key, String value) {
        SharedPreferences.Editor sp = context.getSharedPreferences(Constants.USER_SP, Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.commit();
    }

    public static int getValue(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constants.USER_SP, Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);
        return value;
    }

    public static boolean getValue(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constants.USER_SP, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    public static String getValue(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(Constants.USER_SP, Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }
}
