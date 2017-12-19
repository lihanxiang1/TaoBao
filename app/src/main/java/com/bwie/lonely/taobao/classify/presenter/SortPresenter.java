package com.bwie.lonely.taobao.classify.presenter;

import com.bwie.lonely.taobao.classify.bean.LeftBean;
import com.bwie.lonely.taobao.classify.bean.RightBean;
import com.bwie.lonely.taobao.classify.model.SortModel;
import com.bwie.lonely.taobao.classify.view.ISortView;

/**
 * Created by Lonely on 2017/12/7.
 */

public class SortPresenter implements SortModel.OnLeftFinish, SortModel.OnRightFinish {
    private final ISortView iSortView;
    private final SortModel sortModel;

    public SortPresenter(ISortView iSortView) {
        this.iSortView = iSortView;
        sortModel = new SortModel(this,this);
    }

    @Override
    public void onFinish(LeftBean bean) {
        iSortView.ShowLeftData(bean);
    }


    public void GetData(){
        sortModel.GetData();
    }
    public void GetRightData(int cid){
        sortModel.GetRightData(cid);
    }

    @Override
    public void onRightFinish(RightBean bean) {
        iSortView.ShowRightData(bean);
    }
}
