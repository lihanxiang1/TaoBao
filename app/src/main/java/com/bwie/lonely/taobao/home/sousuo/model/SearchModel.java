package com.bwie.lonely.taobao.home.sousuo.model;

import android.util.Log;

import com.bwie.lonely.taobao.home.sousuo.bean.GoodsBean;
import com.bwie.lonely.taobao.home.sousuo.bean.SearchBean;
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

public class SearchModel implements ISearchModel {
    // 调用接口接收数据
    GoodsBean Goodsbean;

    // 创建接口
    public interface OnFinish {
        void onfinish(GoodsBean bean);
    }

    private OnFinish onfinish;

    public SearchModel(OnFinish onfinish) {
        this.onfinish = onfinish;
    }

    @Override
    public void GetData(SearchBean bean) {
        HashMap<String, String> map = new HashMap<>();
        map.put("keywords", bean.getKeywords());
        map.put("page", bean.getPage());
        map.put("sort", bean.getSort());
        Observable<GoodsBean> search = RetroFactory.getInstance().getSearch(Field.SEARCHE_GOODS_PATH, map);
        search.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("search", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("search", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GoodsBean goods) {
                        Log.d("search", "onNext: " + goods.getData().get(0).getTitle());
                        Goodsbean = goods;
                        onfinish.onfinish(Goodsbean);
                    }
                });
    }
}
