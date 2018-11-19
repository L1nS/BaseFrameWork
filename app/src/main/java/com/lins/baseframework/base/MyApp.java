package com.lins.baseframework.base;

import android.app.Application;
import com.lins.baseframework.utils.okgo.OkGoUtil;

public class MyApp extends Application {
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        OkGoUtil.initOkGo(this);
    }
}
