package com.bwie.lonely.taobao.home.sousuo.presenter;

import com.bwie.lonely.taobao.home.sousuo.bean.GoodsBean;
import com.bwie.lonely.taobao.home.sousuo.bean.SearchBean;
import com.bwie.lonely.taobao.home.sousuo.model.SearchModel;
import com.bwie.lonely.taobao.home.sousuo.view.ISearchView;

/**
 * Created by Lonely on 2017/12/7.
 */

public class SearchPresenter implements SearchModel.OnFinish {
    private final ISearchView iSearchView;
    private final SearchModel searchModel;

    public SearchPresenter(ISearchView iSearchView) {
        this.iSearchView = iSearchView;
        searchModel = new SearchModel(this);
    }

    @Override
    public void onfinish(GoodsBean bean) {
        iSearchView.ShowSearchData(bean);
    }

    public void GetData(SearchBean bean) {
        searchModel.GetData(bean);
    }
}
