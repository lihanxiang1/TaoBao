package com.bwie.lonely.taobao.shopcart.order.address.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.other.ToastUtil;
import com.bwie.lonely.taobao.shopcart.order.address.bean.GetAddressBean;

/**
 * Created by Lonely on 2017/12/8.
 */

public class MyGetAddressAdapter extends BaseAdapter {
    // 数据源
    GetAddressBean addressBean;
    // 上下文
    Context context;

    public MyGetAddressAdapter(GetAddressBean addressBean, Context context) {
        this.addressBean = addressBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return addressBean.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return addressBean.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.receiving_address_item, null);
            holder = new MyHolder();
            holder.name = view.findViewById(R.id.receiving_name);
            holder.mobile = view.findViewById(R.id.receiving_mobile);
            holder.address = view.findViewById(R.id.receiving_address);
            holder.write = view.findViewById(R.id.receiving_write);
            holder.del = view.findViewById(R.id.receiving_del);
            holder.cb = view.findViewById(R.id.receiving_check);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }
        /*赋值*/
        holder.name.setText(addressBean.getData().get(i).getName());
        holder.mobile.setText(addressBean.getData().get(i).getMobile() + "");
        holder.address.setText(addressBean.getData().get(i).getAddr());
        holder.cb.setChecked(addressBean.getData().get(i).isSelected());

        holder.write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.new_address_dialog, null);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(inflate)
                        .create();
                final AlertDialog show = builder.show();
                inflate.findViewById(R.id.new_address_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ToastUtil.show(context, "接口出现问题", 2000);
                        show.dismiss();
                    }
                });
            }
        });
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.show(context, "没有删除接口", 2000);
            }
        });

        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.show(context, "设为默认", 2000);
            }
        });
        return view;
    }

    class MyHolder {
        TextView name, mobile, address, write, del;
        CheckBox cb;
    }
}
