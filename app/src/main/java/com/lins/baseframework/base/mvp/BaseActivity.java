package com.lins.baseframework.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.lins.baseframework.base.MyApp;
import com.lins.baseframework.utils.screenUtils.StatusBarUtils;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected Unbinder unbinder;

    @LayoutRes
    public abstract int initLayoutResID();

    public abstract void initData();

    public abstract void initView();

    public abstract void initLoad();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ((MyApp) this.getApplication()).getActivityManager()
                .pushActivity(this); // 将activity推入管理栈
        super.onCreate(savedInstanceState);
        StatusBarUtils.statusBarTransparent(this);
        StatusBarUtils.statusBarHide(this);
        StatusBarUtils.statusBarTextColor(this, false);
        setContentView(initLayoutResID());
        unbinder = ButterKnife.bind(this);
        mContext = this;

        initData();
        initView();
        initLoad();
    }
}
