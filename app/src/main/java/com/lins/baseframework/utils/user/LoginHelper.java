package com.lins.baseframework.utils.user;

import android.content.Context;
import com.lins.baseframework.utils.SharedPreUtils;
import com.lins.baseframework.utils.Validator;


/**
 * Created by Admin on 2017/5/11.
 */

public class LoginHelper {

    /**
     * 判断用户是否已经登录
     *
     * @return
     */
    public static boolean isLogin(Context context) {
        String uid = SharedPreUtils.getValue(context, "uid", "");
        if (Validator.isEmpty(uid))
            return false;
        else
            return true;
    }

    public static void setUserInfo(Context context, LoginBean bean) {
        SharedPreUtils.putValue(context, "uid", bean.getUid());
        SharedPreUtils.putValue(context, "token", bean.getToken());
        SharedPreUtils.putValue(context, "username", bean.getUsername());
//        UserBean userBean = UserHelper.getsInstance().findUserByName(bean.getUsername());
//        if (userBean != null) {
//            userBean.setIsFirst(false);
//            UserHelper.getsInstance().updateUser(userBean);
//            RxBus.$().post(RxBusTag.RXBUS_LOGIN_WITH_SYNC_SHELF, false);
//        } else {
//            userBean = new UserBean();
//            userBean.setName(bean.getUsername());
//            userBean.setIsFirst(true);
//            UserHelper.getsInstance().saveUser(userBean);
//            RxBus.$().post(RxBusTag.RXBUS_LOGIN_WITH_SYNC_SHELF, true);
//        }
    }

    /**
     * 清除账号信息
     *
     * @param context
     */
    public static void clearUserInfo(Context context) {
        SharedPreUtils.putValue(context, "uid", "");
        SharedPreUtils.putValue(context, "token", "");
        SharedPreUtils.putValue(context, "username", "");
    }

    public static String getUserId(Context context) {
        return SharedPreUtils.getValue(context, "uid", "");
    }

    public static String getUserToken(Context context) {
        return SharedPreUtils.getValue(context, "token", "");
    }

    public static String getUsername(Context context) {
        return SharedPreUtils.getValue(context, "username", "");
    }

}
