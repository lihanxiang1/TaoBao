package com.bwie.lonely.taobao.shopcart.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.shopcart.order.bean.SearchOrderBean;

/**
 * Created by Lonely on 2017/12/8.
 */

public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.MyHolder> {
    // 数据源
    SearchOrderBean orderBean;
    // 上下文
    Context context;

    public OrderRecyclerViewAdapter(SearchOrderBean orderBean, Context context) {
        this.orderBean = orderBean;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyHolder holder = new MyHolder(LayoutInflater.from(context).inflate(R.layout.order_recyclerview_item, null));
        return holder;
    }

    /*点击事件*/
    public interface SetOnItemLinister {
        void SetonItemLinister(int postion);
    }

    private SetOnItemLinister setOnItemLinister;

    public void SetOnItemLinister(SetOnItemLinister setOnItemLinister) {
        this.setOnItemLinister = setOnItemLinister;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.cretetime.setText("第" + (position + 1) + "单" + " 创建时间:" + orderBean.getData().get(position).getCreatetime());
        holder.price.setText("应付金额:" + orderBean.getData().get(position).getPrice());
        if (orderBean.getData().get(position).getStatus() == 1) {
            holder.status.setText("已支付");
        } else {
            holder.status.setText("待支付");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnItemLinister.SetonItemLinister(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderBean.getData().size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView cretetime, price, status;

        public MyHolder(View itemView) {
            super(itemView);
            cretetime = itemView.findViewById(R.id.order_recyclerview_item_createtime);
            price = itemView.findViewById(R.id.order_recyclerview_item_price);
            status = itemView.findViewById(R.id.order_recyclerview_item_status);
        }
    }
}
