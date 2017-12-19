package com.bwie.lonely.taobao.shopcart.order.address;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.other.Field;
import com.bwie.lonely.taobao.other.RetroFactory;
import com.bwie.lonely.taobao.other.SharedPreferencesUtils;
import com.bwie.lonely.taobao.other.ToastUtil;
import com.bwie.lonely.taobao.shopcart.order.address.adapter.MyGetAddressAdapter;
import com.bwie.lonely.taobao.shopcart.order.address.bean.GetAddressBean;
import com.bwie.lonely.taobao.shopcart.order.address.bean.SetAddressBean;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ReceivingAddressActivity extends AppCompatActivity {

    @BindView(R.id.receiving_listView)
    ListView receivingListView;
    @BindView(R.id.addNewAddress)
    LinearLayout addNewAddress;
    GetAddressBean GetaddressBean;
    MyGetAddressAdapter adapter;
    @BindView(R.id.receiving_title_back)
    TextView receivingTitleBack;
    @BindView(R.id.receiving_linear1)
    LinearLayout receivingLinear1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiving_address);

        init();

        ButterKnife.bind(this);
    }

    private void init() {
        String uid = (String) SharedPreferencesUtils.getParam(ReceivingAddressActivity.this, "uid", "1");
        // 设置listview数据(收货地址列表)
        GetAddress(uid);
    }

    public void GetAddress(String uid) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        Observable<GetAddressBean> address = RetroFactory.getInstance().Get_Address(Field.GET_ADDRESS, map);
        address.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GetAddressBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GetAddressBean getAddressBean) {
                        GetaddressBean = getAddressBean;
                        adapter = new MyGetAddressAdapter(GetaddressBean, ReceivingAddressActivity.this);
                        receivingListView.setAdapter(adapter);
                    }
                });
    }

    @OnClick({R.id.receiving_linear1, R.id.addNewAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.receiving_linear1:
                // 返回
                finish();
                break;
            case R.id.addNewAddress:
                ToastUtil.show(ReceivingAddressActivity.this, "添加新地址", 2000);
                View view1 = LayoutInflater.from(ReceivingAddressActivity.this).inflate(R.layout.new_address_dialog, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(ReceivingAddressActivity.this);
                builder.setView(view1)
                        .create();
                final AlertDialog dialog = builder.show();
                final EditText name = view1.findViewById(R.id.new_address_name);
                final EditText mobile = view1.findViewById(R.id.new_address_mobile);
                final EditText addr = view1.findViewById(R.id.new_address_addr);
                Button btn_yes = view1.findViewById(R.id.new_address_btn);
                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String nam1 = name.getText().toString();
                        String mobile1 = mobile.getText().toString();
                        String addr1 = addr.getText().toString();
                        String uid = (String) SharedPreferencesUtils.getParam(ReceivingAddressActivity.this, "uid", "1");

                        // 调用添加地址的方法
                        SetAddress(uid,nam1,mobile1,addr1);
                        GetAddress(uid);
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                break;
        }
    }

    /*添加收货地址的方法*/
    public void SetAddress(String uid,String name,String mobile,String addr){
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("addr",addr);
        map.put("mobile",mobile);
        map.put("name",name);
        Observable<SetAddressBean> address = RetroFactory.getInstance().SetAddress(Field.SET_ADDRESS, map);
        address.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SetAddressBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("address", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("address", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(SetAddressBean setAddressBean) {
                        ToastUtil.show(ReceivingAddressActivity.this,setAddressBean.getMsg(),2000);
                    }
                });
    }
}
