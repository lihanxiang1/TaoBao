package com.bwie.lonely.taobao.classify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.classify.bean.LeftBean;

/**
 * Created by Lonely on 2017/12/7.
 */

public class LeftAdapter extends BaseAdapter {
    // 数据源
    LeftBean leftBean;
    // 上下文
    Context context;

    public LeftAdapter(LeftBean leftBean, Context context) {
        this.leftBean = leftBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return leftBean.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return leftBean.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /*接口回调*/
    public interface ClickName {
        void Clickname(int cid);
    }

    private ClickName clickName;

    public void setClickName(ClickName clickName) {
        this.clickName = clickName;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.classify_left_item, null);
            holder = new MyHolder();
            holder.tv = view.findViewById(R.id.sort_left_tv);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }

        holder.tv.setText(leftBean.getData().get(i).getName());

        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickName.Clickname(leftBean.getData().get(i).getCid());
            }
        });

        return view;
    }

    class MyHolder {
        TextView tv;
    }
}
