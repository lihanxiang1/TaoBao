package com.bwie.lonely.taobao.my.model;

import android.util.Log;

import com.bwie.lonely.taobao.my.bean.LoginBean;
import com.bwie.lonely.taobao.my.bean.UserBean;
import com.bwie.lonely.taobao.other.Field;
import com.bwie.lonely.taobao.other.RetroFactory;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lonely on 2017/12/8.
 */

public class MineModel implements IMineModel {
    // 定义类接收请求下来的数据
    LoginBean loginBean;

    private onLogin onlogin;

    public MineModel(onLogin onlogin) {
        this.onlogin = onlogin;
    }

    public interface onLogin {
        void onlogin(LoginBean loginbean);
    }

    @Override
    public void OnLogin(UserBean userBean) {
        loginBean = new LoginBean();
        final Observable<LoginBean> login = RetroFactory.getInstance().getLogin(Field.LOGIN_URL_PATH + "?mobile=" + userBean.getMobile() + "&password=" + userBean.getPassword());
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("mine", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("mine", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(LoginBean data) {
                        Log.d("mine", "LoginBean onNext: " + data.getMsg());
                        loginBean = data;
                        onlogin.onlogin(loginBean);
                    }
                });
    }
}
