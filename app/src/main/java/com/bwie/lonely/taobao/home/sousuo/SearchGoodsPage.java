package com.bwie.lonely.taobao.home.sousuo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.home.sousuo.bean.GoodsBean;
import com.bwie.lonely.taobao.home.sousuo.bean.SearchBean;
import com.bwie.lonely.taobao.home.sousuo.presenter.SearchPresenter;
import com.bwie.lonely.taobao.home.sousuo.view.ISearchView;
import com.bwie.lonely.taobao.other.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lonely on 2017/12/7.
 */

public class SearchGoodsPage extends AppCompatActivity implements ISearchView {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    SearchPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods_page);
        ButterKnife.bind(this);

        init();

    }

    private void init() {
        presenter = new SearchPresenter(this);
        searchClick();
    }

    private void searchClick() {

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER) {
                    String search_name = etSearch.getText().toString().trim();
                    ToastUtil.show(SearchGoodsPage.this, "输入的内容为：" + search_name, 2000);
                    /*这里进行传值搜索商品*/
                    presenter.GetData(new SearchBean(search_name,"1","0"));
                    return true;
                }
                return false;
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:// 返回
                finish();
                break;
            case R.id.tv_cancel:// 取消
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                etSearch.setText("");
                break;
        }
    }

    @Override
    public void ShowSearchData(GoodsBean bean) {
        Log.d("search", "ShowSearchData: "+bean.getData().get(0).getTitle()+"---");
        /*还差一个listview的适配器 即可展示搜索的商品数据*/
    }
}
