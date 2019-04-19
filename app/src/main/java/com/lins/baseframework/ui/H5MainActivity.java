package com.lins.baseframework.ui;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.hjq.toast.ToastUtils;
import com.lins.baseframework.R;
import com.lins.baseframework.base.MyApp;
import com.lins.baseframework.base.mvp.BaseMvpActivity;
import com.lins.baseframework.base.mvp.BasePresenter;
import com.orhanobut.logger.Logger;

public class H5MainActivity extends BaseMvpActivity {

    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

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
        initProgressBar();
        webView.getSettings().setUserAgentString("APP_WEBVIEW");
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);

                }
                super.onProgressChanged(view, newProgress);
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setDomStorageEnabled(true);
        Logger.d("UA:" + webSettings.getUserAgentString());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }

    @Override
    public void initView() {

    }

    @Override
    public void initLoad() {

        webView.loadUrl("http://www.jxyundong.com");
//        OkGo.<String>get("https://down.amzclouds.com/config/config.xml")
//                .headers(OkGoUtil.getHttpHeaders())
//                .converter(new StringConvert())
//                .adapt(new ObservableResponse<String>())
//                .subscribeOn(Schedulers.io())
//                .doOnSubscribe(new Consumer<Disposable>() {
//                    @Override
//                    public void accept(Disposable disposable) throws Exception {
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Response<String>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
////                        addDisposable(d);
//                    }
//
//                    @Override
//                    public void onNext(Response<String> response) {
//                        String result = response.body();
//                        if (!MyValidator.isEmpty(result) && result.length() > 9) {
//                            String xml = new String(Base64.decode(result.substring(9).getBytes(), Base64.DEFAULT));
//                            Logger.d("onSuccess" + xml);
//                            Gson gson = new Gson();
//                            ProtocolBean bean = gson.fromJson(xml, ProtocolBean.class);
////                            ApiConfig.init(bean);
//                            webView.loadUrl("http://www.jxyundong.com");
//
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable exception) {
//                        exception.printStackTrace();
//                        OkGoUtil.exception(exception);
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });

    }

    private void initProgressBar() {
        progressBar.setMax(100);
        progressBar.setProgressDrawable(this.getResources().getDrawable(R.drawable.color_progressbar));
    }

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

}
