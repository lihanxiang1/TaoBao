package com.bwie.lonely.taobao.my.reg.view;

/**
 * Created by Lonely on 2017/12/8.
 */

public interface IRegView {

    // 注册成功
    void onRegisterSuccess(String ok);

    // 注册失败
    void onRegisterFailed(String error);

}
