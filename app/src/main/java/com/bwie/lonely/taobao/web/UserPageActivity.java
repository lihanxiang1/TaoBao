package com.bwie.lonely.taobao.web;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.my.bean.LoginBean;
import com.bwie.lonely.taobao.my.bean.UserBean;
import com.bwie.lonely.taobao.my.presenter.MinePresenter;
import com.bwie.lonely.taobao.my.reg.RegUserActivity;
import com.bwie.lonely.taobao.my.view.IMineView;
import com.bwie.lonely.taobao.other.SharedPreferencesUtils;
import com.bwie.lonely.taobao.other.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserPageActivity extends AppCompatActivity implements IMineView {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.user_page_edit_user_name)
    EditText userPageEditUserName;
    @BindView(R.id.user_page_edit_user_pass)
    EditText userPageEditUserPass;
    @BindView(R.id.newUserReg)
    TextView newUserReg;
    @BindView(R.id.btn_login)
    Button btnLogin;

    MinePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        presenter = new MinePresenter(this);

        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*加载登陆页面设置登陆标志为false*/
        SharedPreferencesUtils.setParam(UserPageActivity.this, "isLogin", false);
    }

    @OnClick({R.id.img_back, R.id.newUserReg, R.id.btn_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:     // 返回键
                finish();
                break;
            case R.id.newUserReg:   // 注册页面
                Intent intent = new Intent(getApplicationContext(), RegUserActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:    // 登陆判断用户信息
                String mobile = userPageEditUserName.getText().toString().trim();
                String password = userPageEditUserPass.getText().toString().trim();
                presenter.getLogin(new UserBean(mobile, password));
                break;
        }
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {

        // 登陆成功，保存用户信息
        SharedPreferencesUtils.setParam(UserPageActivity.this, "mobile", loginBean.getData().getMobile());
        SharedPreferencesUtils.setParam(UserPageActivity.this, "isLogin", true);
        SharedPreferencesUtils.setParam(UserPageActivity.this, "uid", loginBean.getData().getUid());
        if (loginBean.getData().getNickname() == null){
            SharedPreferencesUtils.setParam(UserPageActivity.this, "nickname", "");
        }else{
            SharedPreferencesUtils.setParam(UserPageActivity.this, "nickname", loginBean.getData().getNickname());
        }
        // 友情提示 登陆成功
        ToastUtil.show(UserPageActivity.this, loginBean.getMsg(), Toast.LENGTH_SHORT);
        // 返回
        finish();

    }

    @Override
    public void onLoginFailed(String error) {
        ToastUtil.show(UserPageActivity.this, error, Toast.LENGTH_SHORT);
    }
}
