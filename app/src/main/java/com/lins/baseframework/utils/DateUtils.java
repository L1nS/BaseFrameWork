package com.lins.baseframework.utils;

public class DateUtils {

    /**
     * 获取当前系统时间戳,精确到秒
     *
     * @return
     */
    public static long getCurTimeLong() {
        long time = System.currentTimeMillis() / 1000;
        return time;
    }
}
