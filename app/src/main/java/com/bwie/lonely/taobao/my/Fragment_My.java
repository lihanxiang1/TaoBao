package com.bwie.lonely.taobao.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.other.SharedPreferencesUtils;
import com.bwie.lonely.taobao.shopcart.order.OrderPageActivity;
import com.bwie.lonely.taobao.shopcart.order.address.ReceivingAddressActivity;
import com.bwie.lonely.taobao.web.DetailsPage;
import com.bwie.lonely.taobao.web.UserPageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Lonely on 2017/12/6.
 */

public class Fragment_My extends Fragment {

    @BindView(R.id.my_user_image)
    ImageView myUserImage;
    Unbinder unbinder;
    @BindView(R.id.mine_username)
    TextView mineUsername;
    @BindView(R.id.userOrderLayout)
    LinearLayout userOrderLayout;
    @BindView(R.id.userAddressLayout)
    LinearLayout userAddressLayout;
    @BindView(R.id.userSettingsLayout)
    LinearLayout userSettingsLayout;
    private boolean isLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment, null);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("mine", "onResume: ");
        isLogin = (boolean) SharedPreferencesUtils.getParam(getActivity(), "isLogin", false);
        String mobile = (String) SharedPreferencesUtils.getParam(getActivity(), "mobile", "未登录");
        String uid = (String) SharedPreferencesUtils.getParam(getActivity(), "uid", "");
        // 如果登陆则传入用户的用户名
        if (isLogin) {
            mineUsername.setText(mobile + " UID:" + uid);
        } else {
            mineUsername.setText("未登录");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.my_user_image, R.id.userOrderLayout,R.id.userAddressLayout, R.id.userSettingsLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_user_image:
                // 登陆页面
                if (!isLogin) {  // 如果没有登陆则跳转到登陆页面
                    startActivity(new Intent(getActivity(), UserPageActivity.class));
                } else {
            /*跳转到用户详情页*/
                    startActivity(new Intent(getActivity(), DetailsPage.class));
                }
                break;
            case R.id.userOrderLayout:
                if (!isLogin) {  // 如果没有登陆则跳转到登陆页面
                    startActivity(new Intent(getActivity(), UserPageActivity.class));
                } else {         // 跳转到订单页
                    startActivity(new Intent(getActivity(), OrderPageActivity.class));
                }
                break;
            case R.id.userAddressLayout:// 地址页面
                if (!isLogin) {  // 如果没有登陆则跳转到登陆页面
                    startActivity(new Intent(getActivity(), UserPageActivity.class));
                } else {         // 跳转到收货地址页
                    startActivity(new Intent(getActivity(), ReceivingAddressActivity.class));
                }
                break;
            case R.id.userSettingsLayout:// 设置页面
                if (!isLogin) {  // 如果没有登陆则跳转到登陆页面
                    startActivity(new Intent(getActivity(), UserPageActivity.class));
                } else {         // 跳转到详情页
                    startActivity(new Intent(getActivity(), DetailsPage.class));
                }
                break;
        }
    }
}
