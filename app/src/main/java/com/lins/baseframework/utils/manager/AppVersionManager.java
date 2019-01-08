package com.lins.baseframework.utils.manager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.FileProvider;
import com.hjq.toast.ToastUtils;
import com.lins.baseframework.base.Constants;
import com.lins.baseframework.base.MyApp;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import java.io.File;

public class AppVersionManager {



    /**
     * 检测APP版本更新
     */
    public static void checkAppVersion(final Activity act) {

        String url = "https://kuaiyanapps.oss-cn-shenzhen.aliyuncs.com/appdown/apk/kyks-v1.0.apk";
    }

    /**
     * 显示版本更新dialog
     */
//    public static void showUpdateDialog(final Activity act, final VersionBean appVersionInfo) {
//        if (act == null || act.isFinishing()) {
//            return;
//        }
//
//        final GeneralDialog dialog = new GeneralDialog();
//        //非强制更新
//        if (appVersionInfo.getForced_updating() == 0) {
//            dialog.showDialog(act, "发现新版本", appVersionInfo.getContent(), "稍后更新", "立即更新", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    new RxPermissions(act).request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                            .subscribe(new Consumer<Boolean>() {
//                                @Override
//                                public void accept(Boolean aBoolean) throws Exception {
//                                    if (aBoolean) {
//                                        dialog.dismiss();
//                                        boolean startDownload = startDownload(act, appVersionInfo);
//                                        if (startDownload) {
//                                            mDialog = new DownloadAppDialog(act);
//                                            mDialog.show();
//                                            mDialog.setCancelable(false);
//                                        }
//                                    }
//                                }
//                            });
//                }
//            });
//        } else {
//            dialog.showOneButtonDialog(act, "发现新版本", appVersionInfo.getContent(), "立即更新", new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    new RxPermissions(act).request(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                            .subscribe(new Consumer<Boolean>() {
//                                @Override
//                                public void accept(Boolean aBoolean) throws Exception {
//                                    if (aBoolean) {
//                                        dialog.dismiss();
//                                        boolean startDownload = startDownload(act, appVersionInfo);
//                                        if (startDownload) {
//                                            mDialog = new DownloadAppDialog(act);
//                                            mDialog.show();
//                                            mDialog.setCancelable(false);
//                                        }
//                                    }
//                                }
//                            });
//                }
//            });
//            dialog.setKeyCancle();
//            dialog.setOutsideCancle(false);
//        }
//
//    }

    public static void download(final String url, String version) {
        final Handler handler = new Handler(Looper.myLooper());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
            }
        };

        final String targetDir = MyStorageManager.getAppRootDir();
        final String fileName = Constants.DOWNLOAD_FOLDER + "_v" + version + "_" + System.currentTimeMillis() + ".apk";
        Observable.create(new ObservableOnSubscribe<Progress>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Progress> e) throws Exception {
                OkGo.<File>get(url)
                        .execute(new FileCallback(targetDir, fileName) {
                            @Override
                            public void onSuccess(Response<File> response) {
                                e.onComplete();
                            }

                            @Override
                            public void onError(Response<File> response) {
                                e.onError(response.getException());
                            }

                            @Override
                            public void downloadProgress(Progress progress) {
                                e.onNext(progress);
                            }
                        });
            }
        }).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
//                fileDownload2.setText("正在下载中...");
                //在此处初始化进度条弹窗
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Progress>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
//                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull Progress progress) {
                        //在此处更新弹窗内容。
//                        String downloadLength = Formatter.formatFileSize(MyApp.getAppContext(), progress.currentSize);
//                        String totalLength = Formatter.formatFileSize(MyApp.getAppContext(), progress.totalSize);
//                        downloadSize.setText(downloadLength + "/" + totalLength);
//                        String speed = Formatter.formatFileSize(MyApp.getAppContext(), progress.speed);
//                        netSpeed.setText(String.format("%s/s", speed));
//                        tvProgress.setText(numberFormat.format(progress.fraction));
//                        pbProgress.setMax(10000);
//                        pbProgress.setProgress((int) (progress.fraction * 10000));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        ToastUtils.show("下载出错");
                    }

                    @Override
                    public void onComplete() {
//                        fileDownload2.setText("下载完成");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                // 下载完成自动打开安装器
                                Intent installIntent = new Intent(Intent.ACTION_VIEW);
                                installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                if (Build.VERSION.SDK_INT >= 24) {//7.0以上
                                    Uri bmpUri = FileProvider.getUriForFile(MyApp.getAppContext(), Constants.FILE_PROVIDER, new File(targetDir + File.separator + fileName));
                                    installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    installIntent.setDataAndType(bmpUri, "application/vnd.android.package-archive");
                                } else {
                                    installIntent.setDataAndType(Uri.fromFile(new File(targetDir + File.separator + fileName)), "application/vnd.android.package-archive");
                                }
                                MyApp.getAppContext().startActivity(installIntent);
                            }
                        });
                        handler.removeCallbacks(runnable);
                    }
                });
    }

}
