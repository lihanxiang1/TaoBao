package com.bwie.lonely.taobao.shopcart.presenter;

import com.bwie.lonely.taobao.shopcart.bean.SearchCartBean;
import com.bwie.lonely.taobao.shopcart.model.UserCartModel;
import com.bwie.lonely.taobao.shopcart.view.ICartView;

/**
 * Created by Lonely on 2017/12/8.
 */

public class UserCartPresenter implements UserCartModel.OnFinishCartData {
    private final ICartView iCartView;
    private final UserCartModel userCartModel;

    public UserCartPresenter(ICartView iCartView) {
        this.iCartView = iCartView;
        userCartModel = new UserCartModel(this);
    }

    @Override
    public void onFinishCartdata(SearchCartBean bean) {
        iCartView.ShowUserCartData(bean);
    }

    // 调用该方法请求网络数据
    public void GetCartData(String uid){
        userCartModel.GetCartData(uid);
    }
}
