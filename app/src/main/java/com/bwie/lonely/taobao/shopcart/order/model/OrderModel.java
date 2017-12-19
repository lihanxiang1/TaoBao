package com.bwie.lonely.taobao.shopcart.order.model;

import android.util.Log;

import com.bwie.lonely.taobao.other.Field;
import com.bwie.lonely.taobao.other.RetroFactory;
import com.bwie.lonely.taobao.shopcart.order.bean.SearchOrderBean;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lonely on 2017/12/8.
 */

public class OrderModel implements IOrderModel {
    SearchOrderBean orderBean;
    public interface GetOrder{
        void GetOrder(SearchOrderBean bean);
    }
    private GetOrder getOrderBean;

    public OrderModel(GetOrder getOrderBean) {
        this.getOrderBean = getOrderBean;
    }

    @Override
    public void GetData(String uid) {
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        Observable<SearchOrderBean> getOrder = RetroFactory.getInstance().Get_Order(Field.GET_ORDER, map);
        getOrder.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchOrderBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("order2", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("order2", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SearchOrderBean Bean) {
                        Log.d("order2", "onNext: "+Bean.getData().get(0).getPrice());
                        orderBean = Bean;
                        getOrderBean.GetOrder(orderBean);
                    }
                });
    }
}
