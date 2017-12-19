package com.bwie.lonely.taobao.classify.subclass.model;

import android.util.Log;

import com.bwie.lonely.taobao.classify.subclass.bean.SubBean;
import com.bwie.lonely.taobao.other.ApiServer;
import com.bwie.lonely.taobao.other.Field;

import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lonely on 2017/12/7.
 */

public class SubModel implements ISubModel {
    SubBean subBeans;

    public interface GetPscidData{
        void getPscidData(SubBean bean);
    }
    private GetPscidData getpscidData;

    public SubModel(GetPscidData getpscidData) {
        this.getpscidData = getpscidData;
    }

    @Override
    public void GetData(String pscid) {
        ApiServer apiServer = new Retrofit.Builder()
                .baseUrl(Field.IP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(ApiServer.class);

        HashMap<String, String> map = new HashMap<>();
        map.put("pscid", pscid);
        Observable<SubBean> observable = apiServer.getRightSubItem(Field.SORT_RIGHT_ITEM_PATH, map);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SubBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("sub", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("sub", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SubBean subBean) {
                        subBeans = subBean;
                        getpscidData.getPscidData(subBeans);
                    }
                });
    }
}
