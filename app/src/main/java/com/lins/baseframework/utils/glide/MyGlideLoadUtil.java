package com.lins.baseframework.utils.glide;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

/**
 * Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
 * Created by Li_Xavier on 2017/6/20 0020.
 */
public class MyGlideLoadUtil {
    private String TAG = "ImageLoader";

    /**
     * 借助内部类 实现线程安全的单例模式
     * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()
     * 方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的。
     * 内部类加载的时候实例化一次instance。
     */
    public MyGlideLoadUtil() {
    }

    private static class GlideLoadUtilsHolder {
        private final static MyGlideLoadUtil INSTANCE = new MyGlideLoadUtil();
    }

    public static MyGlideLoadUtil getInstance() {
        return GlideLoadUtilsHolder.INSTANCE;
    }

    /**
     * Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
     *
     * @param context
     * @param url       加载图片的url地址  String
     * @param imageView 加载图片的ImageView 控件
     */
    public void glideLoad(Context context, String url, ImageView imageView) {
        if (context != null) {
            Glide.with(context).load(url).into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,context is null");
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void glideLoad(Activity activity, String url, ImageView imageView) {
        if (!activity.isDestroyed()) {
            Glide.with(activity).load(url).into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,activity is Destroyed");
        }
    }

    public void glideLoad(Fragment fragment, String url, ImageView imageView) {
        if (fragment != null && fragment.getActivity() != null) {
            Glide.with(fragment).load(url).into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,fragment is null");
        }
    }

    public void glideLoad(android.app.Fragment fragment, String url, ImageView imageView) {
        if (fragment != null && fragment.getActivity() != null) {
            Glide.with(fragment).load(url).into(imageView);
        } else {
            Log.i(TAG, "Picture loading failed,android.app.Fragment is null");
        }
    }
}