package com.lins.baseframework.ui;

import android.Manifest;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.google.gson.Gson;
import com.hjq.toast.ToastUtils;
import com.lins.baseframework.R;
import com.lins.baseframework.base.MyApp;
import com.lins.baseframework.base.mvp.BaseMvpActivity;
import com.lins.baseframework.base.mvp.BasePresenter;
import com.lins.baseframework.utils.MyValidator;
import com.lins.baseframework.utils.manager.AppVersionManager;
import com.lins.baseframework.utils.okgo.OkGoUtil;
import com.lins.baseframework.utils.okgo.adapt.ObservableResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class H5MainActivity extends BaseMvpActivity {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int initLayoutResID() {
        return R.layout.webview_h5_main;
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
//        initProgressBar();
        webView.requestFocus();
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        WebSettings web = webView.getSettings();
        web.setJavaScriptEnabled(true);
        web.setAllowFileAccess(true);
//        web.setSupportMultipleWindows(true);//加入这行，无法跳转播放视频
        //加了这行代码，允许出现广告
        web.setDomStorageEnabled(true);
        web.setBuiltInZoomControls(true);
        web.setSupportZoom(true);
        web.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        web.setUseWideViewPort(true);
        web.setLoadWithOverviewMode(true);
        web.setSavePassword(true);
        web.setSaveFormData(true);
//        web.setDomStorageEnabled(true);
//        web.setSupportMultipleWindows(true);// 新加
        //web.setBlockNetworkImage(true);// 把图片加载放在最后来加载渲染

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;

            }


//            @Override
//            public void onReceivedSslError(WebView view,
//                                           SslErrorHandler handler, SslError error) {
//                // 重写此方法可以让webview处理https请求
//                handler.proceed();
//            }
        });
        //这行很关键

        webView.getSettings().setUserAgentString("APP_WEBVIEW");
        requestPermissions();
    }

    private void requestPermissions() {
        new RxPermissions(this).request(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        loadWebview();
                    }
                });
    }

    private void loadWebview() {
        webView.loadUrl("http://www.qsptv.com/");
        OkGo.<String>get("https://qspapp.oss-cn-shenzhen.aliyuncs.com/n7hjVLaBY2QgVlti.xml")
                .headers(OkGoUtil.getHttpHeaders())
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Response<String> response) {
                        String result = response.body();
                        if (!MyValidator.isEmpty(result) && result.length() > 9) {
                            String xml = new String(Base64.decode(result.substring(9).getBytes(), Base64.DEFAULT));
                            Logger.d("onSuccess" + xml);
                            Gson gson = new Gson();
//                            VersionBean bean = gson.fromJson(xml, VersionBean.class);
//                            AppVersionManager.showUpdateDialog(H5MainActivity.this, bean);
                        }
                    }

                    @Override
                    public void onError(Throwable exception) {
                        exception.printStackTrace();
                        OkGoUtil.exception(exception);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

//    private void initProgressBar() {
//        progressBar.setMax(100);
//        progressBar.setProgressDrawable(this.getResources().getDrawable(R.drawable.color_progressbar));
//    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    ToastUtils.show("再按一次退出程序");
                    mExitTime = System.currentTimeMillis();
                } else {
                    MyApp.getInstance().getActivityManager().finishAllActivity();
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //退出webview时销毁当前的链接：访问一个空的链接
        if (webView != null)
            webView.loadUrl("about:blank");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
