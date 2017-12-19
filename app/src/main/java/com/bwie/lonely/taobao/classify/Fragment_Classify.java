package com.bwie.lonely.taobao.classify;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.classify.adapter.LeftAdapter;
import com.bwie.lonely.taobao.classify.adapter.RightAdapter;
import com.bwie.lonely.taobao.classify.bean.LeftBean;
import com.bwie.lonely.taobao.classify.bean.RightBean;
import com.bwie.lonely.taobao.classify.presenter.SortPresenter;
import com.bwie.lonely.taobao.classify.view.ISortView;
import com.bwie.lonely.taobao.other.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lonely on 2017/12/6.
 */

public class Fragment_Classify extends Fragment implements ISortView {

    @BindView(R.id.classify_left_lsitview)
    ListView classifyLeftLsitview;
    @BindView(R.id.classify_right_listview)
    ListView classifyRightListview;
    Unbinder unbinder;

    LeftAdapter leftAdapter;
    RightAdapter right_adapter;

    SortPresenter presenter;

    private boolean isLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.classify, null);

        init();
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    private void init() {
        isLogin = (boolean) SharedPreferencesUtils.getParam(getActivity(), "isLogin", false);
        if (isLogin) {
            presenter = new SortPresenter(this);
        /*初始化 默认展示左侧数据*/
            presenter.GetData();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        isLogin = (boolean) SharedPreferencesUtils.getParam(getActivity(), "isLogin", false);
        if (isLogin) {
            presenter = new SortPresenter(this);
        /*初始化 默认展示左侧数据*/
            presenter.GetData();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ShowLeftData(LeftBean leftBean) {
         /*配置左侧适配器你*/
        leftAdapter = new LeftAdapter(leftBean, getActivity());
        classifyLeftLsitview.setAdapter(leftAdapter);
        /*左侧的监听事件*/
        leftClick();

    }
    @Override
    public void ShowRightData(RightBean rightBean) {
        /*展示右侧数据*/
        right_adapter = new RightAdapter(rightBean, getActivity());
        classifyRightListview.setAdapter(right_adapter);
    }

    private void leftClick() {
        leftAdapter.setClickName(new LeftAdapter.ClickName() {
            @Override
            public void Clickname(int cid) {
                /*请求右侧数据*/
                presenter.GetRightData(cid);
            }
        });
    }
}
