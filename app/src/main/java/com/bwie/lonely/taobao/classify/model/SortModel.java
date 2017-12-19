package com.bwie.lonely.taobao.classify.model;

import android.util.Log;

import com.bwie.lonely.taobao.classify.bean.LeftBean;
import com.bwie.lonely.taobao.classify.bean.RightBean;
import com.bwie.lonely.taobao.other.Field;
import com.bwie.lonely.taobao.other.RetroFactory;

import java.util.HashMap;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lonely on 2017/12/7.
 */

public class SortModel implements ISortModel  {
    // 定义接口 用于保存数据
    LeftBean bean;

    // 保存右侧数据
    RightBean rightBean;

    public interface OnLeftFinish {
        void onFinish(LeftBean bean);
    }

    private OnLeftFinish onLeftfinish;

    public SortModel(OnLeftFinish onLeftfinish, OnRightFinish onrightfinish) {
        this.onLeftfinish = onLeftfinish;
        this.onrightfinish = onrightfinish;
    }

    public interface OnRightFinish {
        void onRightFinish(RightBean bean);
    }

    private OnRightFinish onrightfinish;

    @Override
    public void GetData() {
        Observable<LeftBean> leftData = RetroFactory.getInstance().getLeftData(Field.SORT_LEFT_PATH);
        leftData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LeftBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("sort", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("sort", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(LeftBean leftBean) {
                        Log.d("sort", "onNext: " + leftBean.getMsg());
                        bean = leftBean;
                        onLeftfinish.onFinish(bean);
                    }
                });
    }

    @Override
    public void GetRightData(int cid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cid", cid + "");
        final Observable<RightBean> rightData = RetroFactory.getInstance().getRightData(Field.SORT_RIGHT_PATH, map);
        rightData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RightBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("sort", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("sort", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(RightBean bean) {
                        Log.d("sort", "onNextRight: " + bean.getData().get(0).getName() + "--");
                        rightBean = bean;
                        onrightfinish.onRightFinish(rightBean);
                    }
                });
    }
}
