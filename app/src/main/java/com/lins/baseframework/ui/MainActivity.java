package com.lins.baseframework.ui;

import com.lins.baseframework.R;
import com.lins.baseframework.base.mvp.BaseMvpActivity;
import com.lins.baseframework.base.mvp.BasePresenter;

public class MainActivity extends BaseMvpActivity {
    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int initLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initLoad() {

    }
}
