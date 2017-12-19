package com.bwie.lonely.taobao.my.presenter;

import com.bwie.lonely.taobao.my.bean.LoginBean;
import com.bwie.lonely.taobao.my.bean.UserBean;
import com.bwie.lonely.taobao.my.model.MineModel;
import com.bwie.lonely.taobao.my.view.IMineView;

/**
 * Created by Lonely on 2017/12/8.
 */

public class MinePresenter implements MineModel.onLogin {
    private final IMineView iMineView;
    private final MineModel mineModel;

    public MinePresenter(IMineView iMineView) {
        this.iMineView = iMineView;
        mineModel = new MineModel(this);
    }

    public void getLogin(UserBean userBean){
        mineModel.OnLogin(userBean);
    }

    @Override
    public void onlogin(LoginBean loginbean) {
        if (loginbean.getCode().equals("0")) {
            iMineView.onLoginSuccess(loginbean);
        }else{
            iMineView.onLoginFailed(loginbean.getMsg());
        }
    }
}
