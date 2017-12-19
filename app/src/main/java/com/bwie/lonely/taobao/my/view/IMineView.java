package com.bwie.lonely.taobao.my.view;

import com.bwie.lonely.taobao.my.bean.LoginBean;

/**
 * Created by Lonely on 2017/12/7.
 */

public interface IMineView {

    // 登陆成功
    void onLoginSuccess(LoginBean loginBean);

    // 登陆失败
    void onLoginFailed(String error);

}
