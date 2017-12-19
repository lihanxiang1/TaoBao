package com.bwie.lonely.taobao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bwie.lonely.taobao.classify.Fragment_Classify;
import com.bwie.lonely.taobao.home.Fragment_HomePage;
import com.bwie.lonely.taobao.my.Fragment_My;
import com.bwie.lonely.taobao.other.FullScreenUtils;
import com.bwie.lonely.taobao.other.SharedPreferencesUtils;
import com.bwie.lonely.taobao.shopcart.Fragment_GoShoppingCart;
import com.bwie.lonely.taobao.web.UserPageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragme_layout)
    FrameLayout fragmeLayout;
    @BindView(R.id.main_btn_home)
    RadioButton btnHome;
    @BindView(R.id.main_btn_class)
    RadioButton btnClass;
    @BindView(R.id.main_btn_buy)
    RadioButton btnBuy;
    @BindView(R.id.main_btn_my)
    RadioButton btnMy;
//    @BindView(R.id.radio_group)
//    RadioGroup radioGroup;
//    @BindView(R.id.radio_group)
//    RadioGroup radioGroup;

    private RadioGroup radioGroup;

    // fragment 开启事务
    private FragmentTransaction transaction;
    // fragment管理器
    private FragmentManager manager;
    private Fragment_Classify fragment_classify;
    private Fragment_GoShoppingCart fragment_goShoppingCart;
    private Fragment_My fragment_my;
    private Fragment_HomePage fragment_homePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        /*沉浸式*/
        FullScreenUtils.fullScreen(MainActivity.this);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        initData();
    }

    private void initData() {

        // 初始化fragment管理器
        manager = getSupportFragmentManager();

        fragment_homePage = new Fragment_HomePage();
        fragment_classify = new Fragment_Classify();
        fragment_goShoppingCart = new Fragment_GoShoppingCart();
        fragment_my = new Fragment_My();

        // 开启事务
        transaction = manager.beginTransaction();

        transaction.add(R.id.fragme_layout, fragment_homePage);
        transaction.add(R.id.fragme_layout, fragment_classify);
        transaction.add(R.id.fragme_layout, fragment_goShoppingCart);
        transaction.add(R.id.fragme_layout, fragment_my);
        // 默认打开首页页面
        transaction.show(fragment_homePage);
        transaction.hide(fragment_classify);
        transaction.hide(fragment_goShoppingCart);
        transaction.hide(fragment_my);
        transaction.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        boolean isLogin = (boolean) SharedPreferencesUtils.getParam(MainActivity.this, "isLogin", false);
        if (!isLogin) {
            // 默认打开首页页面
            btnHome.setChecked(true);
            transaction = manager.beginTransaction();
            transaction.show(fragment_homePage);
            transaction.hide(fragment_classify);
            transaction.hide(fragment_goShoppingCart);
            transaction.hide(fragment_my);
            transaction.commitAllowingStateLoss();
        }
    }

    @OnClick({R.id.main_btn_home, R.id.main_btn_class, R.id.main_btn_buy, R.id.main_btn_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_btn_home:
                transaction = manager.beginTransaction();
                transaction.show(fragment_homePage);
                transaction.hide(fragment_classify);
                transaction.hide(fragment_goShoppingCart);
                transaction.hide(fragment_my);
                transaction.commit();
                break;
            case R.id.main_btn_class:
                boolean isLogin = (boolean) SharedPreferencesUtils.getParam(MainActivity.this, "isLogin", false);
                // 如果没有登陆 则跳转到登陆页面
                if (!isLogin) {
                    startActivity(new Intent(MainActivity.this, UserPageActivity.class));
                }
                transaction = manager.beginTransaction();
                transaction.show(fragment_classify);
                transaction.hide(fragment_homePage);
                transaction.hide(fragment_goShoppingCart);
                transaction.hide(fragment_my);
                transaction.commit();
                break;
            case R.id.main_btn_buy:
                // 如果没有登陆 则跳转到登陆页面
                boolean isLogin2 = (boolean) SharedPreferencesUtils.getParam(MainActivity.this, "isLogin", false);
                if (!isLogin2) {
                    startActivity(new Intent(MainActivity.this, UserPageActivity.class));
                }
                transaction = manager.beginTransaction();
                transaction.show(fragment_goShoppingCart);
                transaction.hide(fragment_classify);
                transaction.hide(fragment_homePage);
                transaction.hide(fragment_my);
                transaction.commit();
                break;
            case R.id.main_btn_my:
                transaction = manager.beginTransaction();
                transaction.show(fragment_my);
                transaction.hide(fragment_classify);
                transaction.hide(fragment_goShoppingCart);
                transaction.hide(fragment_homePage);
                transaction.commit();
                break;
        }
    }
    //        manager.beginTransaction().add(R.id.fragme_layout , fragment_homePage).commit();
        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                if (fragment_homePage != null){
                    transaction.hide(fragment_homePage);
                }
                if (fragment_classify != null){
                    transaction.hide(fragment_classify);
                }
                if (fragment_goShoppingCart  != null){
                    transaction.hide(fragment_goShoppingCart);
                }
                if (fragment_my != null){
                    transaction.hide(fragment_my);
                }

                switch (i){
                    case R.id.main_btn_home:
                        if (fragment_homePage == null){


                        }else{
                            transaction.show(fragment_homePage);
                            transaction.hide(fragment_classify);
                            transaction.hide(fragment_goShoppingCart);
                            transaction.hide(fragment_my);
                        }
                        break;
                    case R.id.main_btn_class:
                        boolean isLogin = (boolean) SharedPreferencesUtils.getParam(MainActivity.this, "isLogin", false);
                        // 如果没有登陆 则跳转到登陆页面
                        if (!isLogin) {
                            startActivity(new Intent(MainActivity.this, UserPageActivity.class));
                        }else{
                            if (fragment_classify == null){


                            }else{
                                transaction.show(fragment_classify);
                                transaction.hide(fragment_homePage);
                                transaction.hide(fragment_goShoppingCart);
                                transaction.hide(fragment_my);
                            }
                        }

                        break;
                    case R.id.main_btn_buy:
                        // 如果没有登陆 则跳转到登陆页面
                        boolean isLogin2 = (boolean) SharedPreferencesUtils.getParam(MainActivity.this, "isLogin", false);
                        if (!isLogin2) {
                            startActivity(new Intent(MainActivity.this, UserPageActivity.class));
                        }else{
                            if (fragment_goShoppingCart == null){

                            }else{
                                transaction.show(fragment_goShoppingCart);
                                transaction.hide(fragment_classify);
                                transaction.hide(fragment_homePage);
                                transaction.hide(fragment_my);
                            }
                        }

                        break;
                    case R.id.main_btn_my:
                        if (fragment_my == null){

                        }else{
                            transaction.show(fragment_my);
                            transaction.hide(fragment_classify);
                            transaction.hide(fragment_goShoppingCart);
                            transaction.hide(fragment_homePage);
                            transaction.commit();
                        }
                        break;

                    default:
                        break;
                }

            }
        });*/
}
