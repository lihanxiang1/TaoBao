package com.bwie.lonely.taobao.home.presenter;

import com.bwie.lonely.taobao.home.bean.HomeBean;
import com.bwie.lonely.taobao.home.model.HomeModel;
import com.bwie.lonely.taobao.home.view.IHomeView;

/**
 * Created by Lonely on 2017/12/7.
 */

public class HomePresenter implements HomeModel.OnHomeFinish {
    private final IHomeView homeView;
    private final HomeModel homeModel;

    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
        homeModel = new HomeModel(this);
    }

    // 调用model请求数据的方法
    public void getData(){
        homeModel.GetHomeData();
    }

    @Override
    public void onhomeFinish(HomeBean homeBean) {
        homeView.ShowHomeData(homeBean);
    }
}
