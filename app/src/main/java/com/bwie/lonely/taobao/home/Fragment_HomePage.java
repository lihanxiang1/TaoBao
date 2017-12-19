package com.bwie.lonely.taobao.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.home.adapter.MyHomeXrAdapter;
import com.bwie.lonely.taobao.home.bean.HomeBean;
import com.bwie.lonely.taobao.home.presenter.HomePresenter;
import com.bwie.lonely.taobao.home.sousuo.SearchGoodsPage;
import com.bwie.lonely.taobao.home.view.IHomeView;
import com.bwie.lonely.taobao.other.ToastUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Lonely on 2017/12/6.
 */

public class Fragment_HomePage extends Fragment implements IHomeView {

    Unbinder unbinder;
    HomePresenter presenter;
    MyHomeXrAdapter adapter;
    @BindView(R.id.title_zxing)
    ImageView titleZxing;
    @BindView(R.id.title_edit)
    EditText titleEdit;
    @BindView(R.id.title_search_img)
    ImageView titleSearchImg;
    @BindView(R.id.title_user_content)
    ImageView titleUserContent;
    @BindView(R.id.home_xrecyclerview)
    XRecyclerView homeXrecyclerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home, null);

//        homeXrecyclerview = view.findViewById(R.id.home_xrecyclerview);
        unbinder = ButterKnife.bind(this, view);

        initView();

        return view;
    }

    private void initView() {
        presenter = new HomePresenter(this);
        presenter.getData();
        homeXrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        titleEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchGoodsPage.class));
            }
        });
    }

    @Override
    public void ShowHomeData(HomeBean homeBean) {
        homeXrecyclerview.setAdapter(adapter = new MyHomeXrAdapter(homeBean, getActivity()));

        // 上拉刷新和下拉刷新的监听事件
        homeXrecyclerview.setPullRefreshEnabled(true);
        homeXrecyclerview.setLoadingMoreEnabled(true);
        homeXrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                // 下拉刷新
                homeXrecyclerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                // 上拉加载
                homeXrecyclerview.loadMoreComplete();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.title_zxing, R.id.title_search_img, R.id.title_user_content})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_zxing:  // 二维码
                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 0);
                break;
            case R.id.title_search_img: // 跳转到搜索页面
                ToastUtil.show(getActivity(), "跳转到搜索页面(未完善)", 2000);
                break;
            case R.id.title_user_content:   // 消息列表
                ToastUtil.show(getActivity(), "消息列表(未完善)", 2000);
                break;
        }
    }


    // 二维码回调处理
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getExtras().getString("result");
            ToastUtil.show(getActivity(), "扫描结果：" + result, 2000);
        }
    }
}
