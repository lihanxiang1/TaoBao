package com.bwie.lonely.taobao.shopcart.order.presenter;

import com.bwie.lonely.taobao.shopcart.order.bean.SearchOrderBean;
import com.bwie.lonely.taobao.shopcart.order.model.OrderModel;
import com.bwie.lonely.taobao.shopcart.order.view.IOrderView;

/**
 * Created by Lonely on 2017/12/8.
 */

public class OrderPresenter implements OrderModel.GetOrder  {
    private final IOrderView iOrderView;
    private final OrderModel orderModel;

    public OrderPresenter(IOrderView iOrderView) {
        this.iOrderView = iOrderView;
        orderModel = new OrderModel(this);
    }

    @Override
    public void GetOrder(SearchOrderBean bean) {
        iOrderView.ShowData(bean);
    }

    public void GetData(String uid){
        orderModel.GetData(uid);
    }

}
