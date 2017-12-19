package com.bwie.lonely.taobao.shopcart.order;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.other.SharedPreferencesUtils;
import com.bwie.lonely.taobao.other.ToastUtil;
import com.bwie.lonely.taobao.shopcart.order.adapter.OrderRecyclerViewAdapter;
import com.bwie.lonely.taobao.shopcart.order.address.ReceivingAddressActivity;
import com.bwie.lonely.taobao.shopcart.order.bean.SearchOrderBean;
import com.bwie.lonely.taobao.shopcart.order.pay.OrderInfoUtil2_0;
import com.bwie.lonely.taobao.shopcart.order.pay.PayResult;
import com.bwie.lonely.taobao.shopcart.order.presenter.OrderPresenter;
import com.bwie.lonely.taobao.shopcart.order.view.IOrderView;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderPageActivity extends AppCompatActivity implements IOrderView {

    @BindView(R.id.order_recyclerview)
    RecyclerView orderRecyclerview;
    @BindView(R.id.order_page_linear1)
    LinearLayout orderPageLinear1;
    private String uid;
    private OrderPresenter presenter;
    OrderRecyclerViewAdapter adapter;

    /*支付配置*/
    private static final int SDK_PAY_FLAG = 1001;
    private String RSA_PRIVATE ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCXq3g2JhA4I5IZrEvD5oKgfF6o+xr0TSIp2qjTFG0dj7OCmK4HXg/j6v561jWhrbvzdx3xpswRJXOx8gLQio8OofBfAjX/ZSHFKvhXxj1TZQ5CwEsJeQ+XV9X6s8nvgoZOm/zE6LlaZqlX1DQq6ItfK78LuEYeU4vclOklulrCNRf6rzdnRLCDIFApisO6WXK1BBBz1SxrCLlGzHslZL32FhwRsFCtYmJbKSsRWYldbyq+lY32Znke7u+ZxPDmFuiW5j3p7vusoGqWH/tFDmwDsuZobqiyM6rYviQqxO2e9gCveIVE7Nsh/5mrtC7Hx263E3xXcI2d5echqEai7U4zAgMBAAECggEAIk+uMEHIJYsWgCxUL4s0Pe4k1AGKhX31npZS3v2d+bQKnh7AL/Z9jcgZrdxL4bmV7WwjIAnl3HMmizj+ngLafirwX+32HULZsusXp3O3Z+hSo5Mw5oG2nzxFsiN+KWrTy3DKbBlh5SuYX8An1RUqgms5GwOjCTVJTKt7WwefhJN5HKfzthWnd7VPsyldGruoqdun7fAP3Jr8O2GvC9CutKFegs8HiWWImkS1jyEpgYq54YTW5dSFoFJUTJSO9YjrjE71AucakjdcZRQimparW4H1p/oqUAbTYkhbYFNMvBxNtT4OuUvVUZCdaZmYBIQUUDkk7guojauCubYLNDMI8QKBgQDKADZY2MTJsK+marVDmjetHSH7+V3uedGnTRtziIE2/lBeT+KWnhuQkSVfSF80KxjMa8ZG6xHXnQWy3sD65NUe8dPEqx/uhRWhCLtWpNxww2J4yu6kZmJoEcWJA2FHnfldDMynhLbzW8PMV234VCsnjq7qj+KEZfgTyhFexRKzpQKBgQDANuNdmzgULRQEzaF0CXbojMrOy8JAzAq/rLeDxV4Ck59sZFzdLF7JOmZti3XiW/JeQAuZ4Sy4E/2IT0E7ap9Al81q4bQOEGfUE9vtwZFLyAJ/4oB+yOEw8Ec2D6/zO12l7Cnhj4rq0t/JbFO42Y2NXCsndqqM+hy3PrY2UtDy9wKBgQCq4rcXPnpr7a8K5+bkg8hqCOVBFtph7mhwUgjRGj/F2CsBPi+AH81N5ZxqGP2BUpwuA1a/lAmKD+pMQamcFo49GXN/Qw1GaQu9KXfieqGKO6BIc9Si+4Jwa+hiWpunTHsyPqu7Y8ip442mhqgfloTTeB99i6+jNehCRYWgWLPTiQKBgBQ5slL3Mj9tPt4ChSqglFKD/hMkfZwz3C2vDuBetDWxMuVE6kCbmp2x93UvZ4gaBLbGJnJar0nvUI43SLXn0joJvyOv/DI+p645KLsr0w4WTa0HOF4e5RMxNU0K+YiuhnwfDYkegY+UANhA/c5Flg9501Cju+55ouvlPWcZ78KhAoGAWyTN7i8iuh+CT0L9ztmtb4k5EwbQRRn09hFksgOfvqXbUDVdByXe/drtFl2nih3QYmSXpA7KYU5hT3dgsTN/F6NyBtZpCtRW+oFNNZoiVCdx5kgC5EZ8fwLVGQIAolWmQH8UfOMpMr5aqnJ/e2pA6xeQz6I2JmwJnbxu6jJHKlk=";
    public static final String APPID = "2088022135280410";
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(OrderPageActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(OrderPageActivity.this, "因个体商户支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        init();

        ButterKnife.bind(this);
    }

    private void init() {
        presenter = new OrderPresenter(this);
        uid = (String) SharedPreferencesUtils.getParam(OrderPageActivity.this, "uid", "1");
        presenter.GetData(uid);
    }

    @Override
    public void ShowData(final SearchOrderBean bean) {
        orderRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderRecyclerViewAdapter(bean, OrderPageActivity.this);
        orderRecyclerview.setAdapter(adapter);

        /*点击事件*/
        adapter.SetOnItemLinister(new OrderRecyclerViewAdapter.SetOnItemLinister() {
            @Override
            public void SetonItemLinister(int postion) {
                ToastUtil.show(OrderPageActivity.this, "跳转到订单详情页去支付：" + bean.getData().get(postion).getPrice(), 2000);
                /*这里进入支付页面*/
                //秘钥验证的类型 true:RSA2 false:RSA
                boolean rsa = false;
                //构造支付订单参数列表
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa);
                //构造支付订单参数信息
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
                //对支付参数信息进行签名
                String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, rsa);
                //订单信息
                final String orderInfo = orderParam + "&" + sign;
                //异步处理
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        //新建任务
                        PayTask alipay = new PayTask(OrderPageActivity.this);
                        //获取支付结果
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });
    }

    /*管理收货地址监听事件*/
    @OnClick(R.id.order_page_linear1)
    public void onViewClicked() {
        /*跳转到收货地址*/
        startActivity(new Intent(OrderPageActivity.this, ReceivingAddressActivity.class));
    }
}
