package com.lins.baseframework.base.mvp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.lins.baseframework.base.MyApp;
import com.lins.baseframework.utils.screenUtil.StatusBarUtil;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    protected Unbinder unbinder;

    @LayoutRes
    public abstract int initLayoutResID();

    public abstract void initData();

    public abstract void initView();

    public abstract void initListener();

    public abstract void initLoad();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ((MyApp) this.getApplication()).getActivityManager()
                .pushActivity(this); // 将activity推入管理栈
        super.onCreate(savedInstanceState);
        StatusBarUtil.statusBarTransparent(this);
        StatusBarUtil.statusBarHide(this);
        StatusBarUtil.statusBarTextColor(this, false);
        setContentView(initLayoutResID());
        unbinder = ButterKnife.bind(this);
        mContext = this;

        initData();
        initView();
        initListener();
        initLoad();
    }

    protected void finishThis() {
        MyApp.getInstance().getActivityManager().finishThis();
    }

    @Override
    protected void onDestroy() {
        // 销毁时activity出栈
        ((MyApp) this.getApplication()).getActivityManager()
                .popActivity(this);
        super.onDestroy();
    }
}
