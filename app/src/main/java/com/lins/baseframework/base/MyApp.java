package com.lins.baseframework.base;

import android.app.Application;
import android.content.Context;
import com.hjq.toast.ToastUtils;
import com.lins.baseframework.utils.crash.CrashHandler;
import com.lins.baseframework.utils.manager.MyActivityManager;
import com.lins.baseframework.utils.okgo.OkGoUtil;
import com.lins.baseframework.utils.user.LoginBean;
import com.lins.baseframework.utils.user.LoginHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MyApp extends Application {
    private static MyApp instance;
    private MyActivityManager activityManager = null; // activity管理类

    private static MyApp app;
    public static LoginBean user;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        activityManager = MyActivityManager.getInstance(); // 获得实例
        super.onCreate();
        instance = this;
        MyApp.app = this;
        OkGoUtil.initOkGo(this);
        ToastUtils.init(this);
        initUserData();
        Logger.addLogAdapter(new AndroidLogAdapter());
        CrashHandler.getInstance().init(this);//初始化崩溃日志工具
    }

    public void initUserData() {
        //初始化用户信息
        user = new LoginBean();
        if (LoginHelper.isLogin(app)) {
            user.setUid(LoginHelper.getUserId(app));
            user.setToken(LoginHelper.getUserToken(app));
            user.setUsername(LoginHelper.getUsername(app));
        }
    }


    public static Context getAppContext() {
        return app;
    }

    public MyActivityManager getActivityManager() {
        return activityManager;
    }

}
