package com.bwie.lonely.taobao.my.bean;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class UserBean {
    private String mobile;
    private String password;

    public UserBean(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }
}
