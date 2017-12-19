package com.bwie.lonely.taobao.my.reg.presenter;

import com.bwie.lonely.taobao.my.bean.UserBean;
import com.bwie.lonely.taobao.my.reg.model.RegModel;
import com.bwie.lonely.taobao.my.reg.view.IRegView;

/**
 * Created by Lonely on 2017/12/8.
 */

public class RegPresenter implements RegModel.isReg {
    private final IRegView iRegView;
    private final RegModel regModel;

    public RegPresenter(IRegView iRegView) {
        this.iRegView = iRegView;
        regModel = new RegModel(this);
    }

    public void Reg(UserBean userBean){
        regModel.onRegister(userBean);
    }

    @Override
    public void isreg(String flag) {
        if (flag.equals("注册成功")){
            iRegView.onRegisterSuccess(flag);
        }else{
            iRegView.onRegisterFailed(flag);
        }
    }
}
