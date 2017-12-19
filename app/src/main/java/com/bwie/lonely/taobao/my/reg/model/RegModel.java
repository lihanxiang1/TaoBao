package com.bwie.lonely.taobao.my.reg.model;

import android.util.Log;

import com.bwie.lonely.taobao.my.bean.UserBean;
import com.bwie.lonely.taobao.my.reg.bean.RegBean;
import com.bwie.lonely.taobao.other.Field;
import com.bwie.lonely.taobao.other.RetroFactory;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lonely on 2017/12/8.
 */

public class RegModel implements IRegMode {
    // 创建一个接口 保存注册状态
    String flag = "";

    private isReg isReg;

    public RegModel(RegModel.isReg isReg) {
        this.isReg = isReg;
    }

    public interface isReg{
        void isreg(String flag);
    }

    @Override
    public void onRegister(UserBean userBean) {

        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", userBean.getMobile());
        map.put("password", userBean.getPassword());
        Observable<RegBean> reg = RetroFactory.getInstance().getReg(Field.REG_URL_PATH, map);
        reg.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegBean regBean) {
                        String msg = regBean.getMsg();
                        flag = msg;
                        isReg.isreg(flag);
                        Log.d("reg", "RegBean onNext: "+regBean.getMsg());
                    }
                });
    }
}
