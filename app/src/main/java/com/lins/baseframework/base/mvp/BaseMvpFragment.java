package com.lins.baseframework.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseMvpFragment<V, T extends BasePresenter<V>> extends BaseFragment {
    protected T mPresenter;
    protected abstract T initPresenter();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //允许为空，不是所有都要实现MVP模式
        if (initPresenter() != null) {
            mPresenter = initPresenter();
            mPresenter.attachView((V) this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter.detachDisposable();
        }
    }
}
