package com.lins.baseframework.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;

    @LayoutRes
    public abstract int initLayoutResID();

    public abstract void initData();

    public abstract void initView();

    public abstract void initLoad();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayoutResID());

        mContext = this;

        initData();
        initView();
        initLoad();
    }
}
