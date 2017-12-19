package com.bwie.lonely.taobao.shopcart.model;

import android.util.Log;

import com.bwie.lonely.taobao.other.Field;
import com.bwie.lonely.taobao.other.RetroFactory;
import com.bwie.lonely.taobao.shopcart.bean.SearchCartBean;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lonely on 2017/12/8.
 */

public class UserCartModel implements ICartModel {
    SearchCartBean cartBean;

    public interface OnFinishCartData {
        void onFinishCartdata(SearchCartBean bean);
    }

    private OnFinishCartData onCartData;

    public UserCartModel(OnFinishCartData onCartData) {
        this.onCartData = onCartData;
    }

    @Override
    public void GetCartData(String uid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        Observable<SearchCartBean> cart = RetroFactory.getInstance().SearchCart(Field.SEARCH_CART_PATH, map);
        cart.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchCartBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("cart", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("cart", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SearchCartBean bean) {
                        Log.d("cart", "onNext: "+bean.getData().get(0).getSellerName());
                        cartBean = bean;
                        onCartData.onFinishCartdata(cartBean);
                    }
                });
    }
}
