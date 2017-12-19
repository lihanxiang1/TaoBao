package com.bwie.lonely.taobao.web;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.other.SharedPreferencesUtils;
import com.bwie.lonely.taobao.other.ToastUtil;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lonely on 2017/12/8.
 */

public class DetailsPage extends AppCompatActivity {
    @BindView(R.id.user_data_back)
    ImageView userDataBack;
    @BindView(R.id.user_data_title_content)
    ImageView userDataTitleContent;
    @BindView(R.id.user_data_title)
    RelativeLayout userDataTitle;
    @BindView(R.id.user_data_img)
    RelativeLayout userDataImg;
    @BindView(R.id.user_data_vipname)
    TextView userDataVipname;
    @BindView(R.id.user_data_vipname_layout)
    RelativeLayout userDataVipnameLayout;
    @BindView(R.id.user_data_username)
    TextView userDataUsername;
    @BindView(R.id.user_data_name)
    RelativeLayout userDataName;
    @BindView(R.id.user_data_zxing)
    RelativeLayout userDataZxing;
    @BindView(R.id.user_data_sex)
    RelativeLayout userDataSex;
    @BindView(R.id.user_data_cancel)
    Button userDataCancel;
    @BindView(R.id.activity_details_page)
    RelativeLayout activityDetailsPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*相关信息设置*/
        String mobile = (String) SharedPreferencesUtils.getParam(DetailsPage.this, "mobile", "未登录");
        userDataVipname.setText(mobile);

        String nickname = (String) SharedPreferencesUtils.getParam(DetailsPage.this, "nickname", "默认名");
        if (nickname.equals("")) {
            nickname = "默认名";
        }
        userDataUsername.setText(nickname);
    }

    @OnClick({R.id.user_data_back, R.id.user_data_title_content, R.id.user_data_img, R.id.user_data_vipname_layout, R.id.user_data_name, R.id.user_data_zxing, R.id.user_data_sex, R.id.user_data_cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_data_back:
                finish();
                break;
            case R.id.user_data_title_content:
                ToastUtil.show(DetailsPage.this, "待配置(alertActivity)", 2000);
                break;
            case R.id.user_data_img:// 修改淘宝头像
                ToastUtil.show(DetailsPage.this, "淘宝头像", 2000);
                break;
            case R.id.user_data_vipname_layout:// 修改会员名 (默认不可以修改)
                Toast.makeText(this, "亲，用户的唯一标识不能修改", Toast.LENGTH_SHORT).show();
                break;
            case R.id.user_data_name:// 修改别名 nickname
                break;
            case R.id.user_data_zxing:
                // 根据用户手机号生成zxing二维码
                String mobile = (String) SharedPreferencesUtils.getParam(DetailsPage.this, "mobile", "哈哈");
                ImageView imageView = new ImageView(DetailsPage.this);
                /**
                 * 参数：1.文本 2 3.二维码的宽高 4.二维码中间的那个logo
                 */
                Bitmap bitmap = EncodingUtils.createQRCode("会员名：" + mobile, 500, 500, null);
                // 设置图片
                imageView.setImageBitmap(bitmap);
                AlertDialog.Builder zxing_dialog = new AlertDialog.Builder(DetailsPage.this);
                AlertDialog dialog = zxing_dialog.setView(imageView).create();
                dialog.show();
                dialog.getWindow().setLayout(200, 200);
                break;
            case R.id.user_data_sex: // 设置性别(无接口)
                ToastUtil.show(DetailsPage.this, "性别", 2000);
                break;
            case R.id.user_data_cancel: // 注销 退出账号
                // 注销用户
                SharedPreferencesUtils.setParam(getApplicationContext(), "isLogin", false);
                SharedPreferencesUtils.setParam(getApplicationContext(), "mobile","未登录");
                SharedPreferencesUtils.setParam(getApplicationContext(), "nickname","默认用户名");
                SharedPreferencesUtils.setParam(getApplicationContext(), "uid","0");
                finish();
                break;
        }
    }
}
