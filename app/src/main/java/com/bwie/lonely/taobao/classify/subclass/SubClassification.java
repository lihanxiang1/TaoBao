package com.bwie.lonely.taobao.classify.subclass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.classify.subclass.adpater.MySubClassRecyAdapter;
import com.bwie.lonely.taobao.classify.subclass.bean.SubBean;
import com.bwie.lonely.taobao.classify.subclass.presenter.SubPresenter;
import com.bwie.lonely.taobao.classify.subclass.view.ISubView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lonely on 2017/12/7.
 */

public class SubClassification extends AppCompatActivity implements ISubView {

    SubPresenter presenter;
    MySubClassRecyAdapter adapter;
    @BindView(R.id.sub_classification_recyclerview)
    RecyclerView subClassificationRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_classification);
        ButterKnife.bind(this);

//        Toast.makeText(SubClassification.this,"来了1",Toast.LENGTH_LONG).show();

//        String pscid = getIntent().getStringExtra("pscid");
//        System.out.println("点击了  pscid = " + pscid);
//        presenter = new SubPresenter(this);
//        presenter.GetData(pscid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String pscid = getIntent().getStringExtra("pscid");
        System.out.println("pscid = " + pscid);
        presenter = new SubPresenter(this);
        presenter.GetData(pscid);
    }

    @Override
    public void ShowData(SubBean subBean) {
        List<SubBean.DataBean> data = subBean.getData();
        System.out.println("subClassificationRecyclerview点击了1 = " + subBean.getData());
        adapter = new MySubClassRecyAdapter(subBean, SubClassification.this);
        subClassificationRecyclerview.setLayoutManager(new GridLayoutManager(SubClassification.this, 1));
        subClassificationRecyclerview.setAdapter(adapter);
    }

}
