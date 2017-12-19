package com.bwie.lonely.taobao.shopcart;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.other.ApiServer;
import com.bwie.lonely.taobao.other.Field;
import com.bwie.lonely.taobao.other.RetroFactory;
import com.bwie.lonely.taobao.other.SharedPreferencesUtils;
import com.bwie.lonely.taobao.other.ToastUtil;
import com.bwie.lonely.taobao.shopcart.adapter.IMyCartExpandChange;
import com.bwie.lonely.taobao.shopcart.adapter.MyCartExpandAdapter;
import com.bwie.lonely.taobao.shopcart.bean.DelCartItemBean;
import com.bwie.lonely.taobao.shopcart.bean.SearchCartBean;
import com.bwie.lonely.taobao.shopcart.order.OrderPageActivity;
import com.bwie.lonely.taobao.shopcart.order.bean.AddOrderBean;
import com.bwie.lonely.taobao.shopcart.presenter.UserCartPresenter;
import com.bwie.lonely.taobao.shopcart.view.ICartView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Lonely on 2017/12/6.
 */

public class Fragment_GoShoppingCart extends Fragment implements ICartView, IMyCartExpandChange {

    UserCartPresenter presenter;
//    @BindView(R.id.btn_back)
    Button btnBack;
//    @BindView(R.id.tv_title)
    TextView tvTitle;
//    @BindView(R.id.bt_header_right)
    TextView btHeaderRight;
//    @BindView(R.id.cart_expand_listView)
    ExpandableListView cartExpandListView;
//    @BindView(R.id.ck_all)
    CheckBox ckAll;
//    @BindView(R.id.tv_show_price)
    TextView tvShowPrice;
//    @BindView(R.id.tv_settlement)
    TextView tvSettlement;
    Unbinder unbinder;

    private String uid;
    private boolean isLogin;
    MyCartExpandAdapter adapter;

    // 创建一个bean类接收请求下的bean类 进行相关的操作
    SearchCartBean cartBean;

    /*标记 用于存储编辑和完成的状态*/
    boolean flag = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.goshopping_fragment, null);

        unbinder = ButterKnife.bind(this, view);

        btnBack = view.findViewById(R.id.btn_back);
        tvTitle = view.findViewById(R.id.tv_title);
        btHeaderRight = view.findViewById(R.id.bt_header_right);
        cartExpandListView = view.findViewById(R.id.cart_expand_listView);
        ckAll = view.findViewById(R.id.ck_all);
        tvShowPrice = view.findViewById(R.id.tv_show_price);
        tvSettlement = view.findViewById(R.id.tv_settlement);;

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        isLogin = (boolean) SharedPreferencesUtils.getParam(getActivity(), "isLogin", false);
        uid = (String) SharedPreferencesUtils.getParam(getActivity(), "uid", "0");
        init();
    }

    private void init() {
        presenter = new UserCartPresenter(this);
        int UidInt = Integer.parseInt(uid);
        if (UidInt != 0) {
            presenter.GetCartData(UidInt + "");
        }
    }


    @Override
    public void ShowUserCartData(SearchCartBean Beans) {
        Log.d("cart", "ShowUserCartData: " + Beans.getMsg());
        cartBean = Beans;
        if (cartBean.getData().size() > 0) {
            adapter = new MyCartExpandAdapter(cartBean, getActivity());
            cartExpandListView.setAdapter(adapter);
            // 默认展示第一行商品信息
            cartExpandListView.expandGroup(0);
            // 调用接口
            adapter.MyCartExpandChange(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_back, R.id.bt_header_right, R.id.ck_all})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:// 返回
                break;
            case R.id.bt_header_right:  // 编辑
                if (!flag) {
                    btHeaderRight.setText("完成");
                    flag = true;
                    // 点击编辑之后，将子条目里的删除和操作数量的布局
                    adapter.flag = View.VISIBLE;
                    adapter.notifyDataSetChanged();
                } else {
                    btHeaderRight.setText("编辑");
                    flag = false;
                    adapter.flag = View.GONE;
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.ck_all:
                boolean checked = ckAll.isChecked();

                /*选中全部条目*/
                for (int i = 0; i < cartBean.getData().size(); i++) {
                    cartBean.getData().get(i).setSelected(checked ? true : false);
                    for (int j = 0; j < cartBean.getData().get(i).getList().size(); j++) {
                        cartBean.getData().get(i).getList().get(j).setChildSelected(checked ? true : false);
                    }
                }

                // 修改按钮数量
                ChangeButtonNum();

                adapter.notifyDataSetChanged();
                // 这里也要捎带修改总金额(未完善 11.18)
                notifyMoney();
                break;
        }
    }

    /*修改按钮数量的方法*/
    public int ChangeButtonNum() {
        int flag = 0;
        for (int i = 0; i < cartBean.getData().size(); i++) {
            if (cartBean.getData().get(i).isSelected()) {
                flag++;
                tvSettlement.setText("结算(" + flag + ")");
            } else {
                tvSettlement.setText("结算(" + flag + ")");
            }
        }
        return flag;
    }

    /*组视图全选*/
    @Override
    public void CheckGroup(int GroupPosition) {
        cartBean.getData().get(GroupPosition).setSelected(!cartBean.getData().get(GroupPosition).isSelected());
        for (int j = 0; j < cartBean.getData().get(GroupPosition).getList().size(); j++) {
            boolean selected = cartBean.getData().get(GroupPosition).isSelected();
            cartBean.getData().get(GroupPosition).getList().get(j).setChildSelected(selected);
        }

        notifyCheckAll();
        adapter.notifyDataSetChanged();
    }

    /*子视图的选中状态*/
    @Override
    public void CheckChild(int GroupPosition, int ChildPosition) {
        boolean childSelected = !(cartBean.getData().get(GroupPosition).getList().get(ChildPosition).isChildSelected());
        cartBean.getData().get(GroupPosition).getList().get(ChildPosition).setChildSelected(childSelected);

        int flag = 0;
        for (int i = 0; i < cartBean.getData().get(GroupPosition).getList().size(); i++) {
            if (cartBean.getData().get(GroupPosition).getList().get(i).isChildSelected()) {
                flag++;
            }
        }
        if (flag == cartBean.getData().get(GroupPosition).getList().size()) {
            cartBean.getData().get(GroupPosition).setSelected(true);
        } else {
            cartBean.getData().get(GroupPosition).setSelected(false);
        }

        // 调用监听bottom全选按钮的方法 内带刷新适配器的方法
        notifyCheckAll();
    }

    /*增加商品数量的方法*/
    @Override
    public void AddNum(int GroupPosition, int ChildPosition) {
        int num = cartBean.getData().get(GroupPosition).getList().get(ChildPosition).getNum();
        // 修改bean类里的数量
        cartBean.getData().get(GroupPosition).getList().get(ChildPosition).setNum(++num);
        // 修改金额
        notifyMoney();
        adapter.notifyDataSetChanged();
    }

    /*减少商品数量的方法*/
    @Override
    public void JianNum(int GroupPosition, int ChildPosition) {
        int num = cartBean.getData().get(GroupPosition).getList().get(ChildPosition).getNum();
        // 如果数量大于1
        if (num > 1) {
            // 修改bean类里的数量
            cartBean.getData().get(GroupPosition).getList().get(ChildPosition).setNum(--num);
            // 修改金额
            notifyMoney();
        } else {
            ToastUtil.show(getActivity(), "不能再少了", 2000);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void DelItem(final int GroupPosition, final int ChildPosition) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setPositiveButton("就要删", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // 得到商品的pid
                String pid = cartBean.getData().get(GroupPosition).getList().get(ChildPosition).getPid();
                // 移除bean类视图
                cartBean.getData().get(GroupPosition).getList().remove(ChildPosition);
                for (int j = 0; j < cartBean.getData().size(); j++) {
                    int bean = cartBean.getData().get(GroupPosition).getList().size();
                    if (bean == 0) {
                        cartBean.getData().remove(GroupPosition);
                    }
                }
                // 删除数据库
                DelCartData(uid, pid);
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton("算了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).create().show();
    }

    //监听bottom全选按钮的方法
    public void notifyCheckAll() {
        int flag = ChangeButtonNum();

        if (flag == cartBean.getData().size()) {
            ckAll.setChecked(true);
        } else {
            ckAll.setChecked(false);
        }

        adapter.notifyDataSetChanged();
        // 这里也要捎带修改总金额
        notifyMoney();
    }

    /*删除商品数据库的方法*/
    public void DelCartData(String uid, String pid) {
        ApiServer builder = new Retrofit.Builder()
                .baseUrl(Field.IP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(ApiServer.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("pid", pid);
        Observable<DelCartItemBean> DelcartItem = builder.DelCartItem(Field.DELCAR_TITEM, map);
        DelcartItem.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DelCartItemBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("delcart", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("delcart", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DelCartItemBean delCartItemBean) {
                        Log.d("delcart", "onNext: " + delCartItemBean.getMsg());
                        ToastUtil.show(getActivity(), delCartItemBean.getMsg(), 2000);
                    }
                });

    }

    /*修改总金额的方法*/
    public void notifyMoney() {
        float money = 0;
        for (int i = 0; i < cartBean.getData().size(); i++) {
            for (int j = 0; j < cartBean.getData().get(i).getList().size(); j++) {
                // 遍历子条目
                if (cartBean.getData().get(i).getList().get(j).isChildSelected()) {
                    // 如果选中状态，则++金额
                    float listBean = cartBean.getData().get(i).getList().get(j).getPrice() * cartBean.getData().get(i).getList().get(j).getNum();
                    money += listBean;
                }
            }
        }
        tvShowPrice.setText("合计：" + money);

        final float finalMoney = money;
        /*如果选中的数量大于0 再弹出创建订单 否则提示*/

        tvSettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    /*计算选中条目的数量*/
                int isno = 0;
                for (int i = 0; i < cartBean.getData().size(); i++) {
                    for (int j = 0; j < cartBean.getData().get(i).getList().size(); j++) {
                        if (cartBean.getData().get(i).getList().get(j).isChildSelected()) {
                            isno++;
                        }
                    }
                }
                if (isno > 0) {
                    // 创建订单
                    CreateOrder(uid, finalMoney);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("是否跳转到用户订单详情页?")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(getActivity(), OrderPageActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).create().show();
                } else {
                    ToastUtil.show(getActivity(), "至少选中一项结算，亲！", 2000);
                }
            }
        });

    }

    // 创建订单的方法
    public void CreateOrder(String uid, float price) {
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("price", price + "");
        Observable<AddOrderBean> createOrder = RetroFactory.getInstance().Create_Order(Field.CREATE_ORDER, map);

        createOrder.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddOrderBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("order", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("order", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(AddOrderBean addOrderBean) {
                        ToastUtil.show(getActivity(), addOrderBean.getMsg(), 2000);
                    }
                });
    }
}
