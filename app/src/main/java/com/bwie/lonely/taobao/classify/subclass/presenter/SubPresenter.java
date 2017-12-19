package com.bwie.lonely.taobao.classify.subclass.presenter;

import com.bwie.lonely.taobao.classify.subclass.bean.SubBean;
import com.bwie.lonely.taobao.classify.subclass.model.SubModel;
import com.bwie.lonely.taobao.classify.subclass.view.ISubView;

/**
 * Created by Lonely on 2017/12/7.
 */

public class SubPresenter implements SubModel.GetPscidData {

    private final ISubView iSubView;
    private final SubModel subModel;

    public SubPresenter(ISubView iSubView) {
        this.iSubView = iSubView;
        subModel = new SubModel(this);
    }

    @Override
    public void getPscidData(SubBean bean) {
        iSubView.ShowData(bean);
    }

    public void GetData(String pscid){
        subModel.GetData(pscid);
    }

}
