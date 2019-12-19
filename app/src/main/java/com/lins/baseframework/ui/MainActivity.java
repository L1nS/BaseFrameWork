package com.lins.baseframework.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.lins.baseframework.R;
import com.lins.baseframework.base.mvp.BaseMvpActivity;
import com.lins.baseframework.base.mvp.BasePresenter;

public class MainActivity extends BaseMvpActivity {
    @BindView(R.id.id_tv)
    TextView idTv;

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
    public void initListener() {

    }

    @Override
    public void initLoad() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.id_tv)
    public void onViewClicked() {
        Intent intent = new Intent(this,TempActivity.class);
        startActivity(intent);
    }
}
