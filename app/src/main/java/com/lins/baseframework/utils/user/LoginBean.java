package com.lins.baseframework.utils.user;

/**
 * Created by Administrator on 2018/2/28.
 */

public class LoginBean {

    private String uid = "";
    private String username = "";
    private String token = "";
    private boolean has_preference;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getHas_preference() {
        return has_preference;
    }

    public void setHas_preference(boolean has_preference) {
        this.has_preference = has_preference;
    }
}
