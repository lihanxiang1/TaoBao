package com.bwie.lonely.taobao.my.reg;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.my.bean.UserBean;
import com.bwie.lonely.taobao.my.reg.presenter.RegPresenter;
import com.bwie.lonely.taobao.my.reg.view.IRegView;
import com.bwie.lonely.taobao.other.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegUserActivity extends AppCompatActivity implements IRegView {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.reg_user_name)
    EditText regUserName;
    @BindView(R.id.reg_user_pass)
    EditText regUserPass;
    @BindView(R.id.nextreg_user_pass)
    EditText nextregUserPass;
    @BindView(R.id.reg_user_email)
    EditText regUserEmail;
    @BindView(R.id.btn_reg)
    Button btnReg;

    // 注册逻辑处理
    RegPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_user);

        ButterKnife.bind(this);

        presenter = new RegPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*当销毁页面的时候隐藏软键盘*/
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @OnClick({R.id.img_back, R.id.btn_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.btn_reg:
                // 接收输入的信息
                String mobile = regUserName.getText().toString().trim();
                String pass = regUserPass.getText().toString().trim();
                String pass2 = nextregUserPass.getText().toString().trim();
                String email = regUserEmail.getText().toString().trim();
                if ( pass != "" && pass2 != "" && pass.equals(pass2)){
                    if (mobile != "" && email != ""){
                        presenter.Reg(new UserBean(mobile,pass2));
                    }else{
                        ToastUtil.show(RegUserActivity.this,"亲，全部为必填项",2000);
                    }
                }else{
                    ToastUtil.show(RegUserActivity.this,"两次密码不匹配",2000);
                }
                break;
        }
    }

    @Override
    public void onRegisterSuccess(String ok) {
        ToastUtil.show(RegUserActivity.this,ok,2000);
        finish();
    }

    @Override
    public void onRegisterFailed(String error) {
        ToastUtil.show(RegUserActivity.this,error,2000);
    }
}
