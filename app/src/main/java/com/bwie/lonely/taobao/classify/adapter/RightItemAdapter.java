package com.bwie.lonely.taobao.classify.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.classify.bean.RightBean;
import com.bwie.lonely.taobao.classify.subclass.SubClassification;
import com.bwie.lonely.taobao.other.ToastUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by Lonely on 2017/12/7.
 */

public class RightItemAdapter extends BaseAdapter {
    // 数据源
    ArrayList<RightBean.DataBean.ListBean> lists;
    // 上下文
    Context context;

    public RightItemAdapter(ArrayList<RightBean.DataBean.ListBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        MyHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.classify_right_grid_item, null);
            holder = new MyHolder();
            holder.tv = view.findViewById(R.id.sort_right_grid_item_tv);
            holder.img = view.findViewById(R.id.sort_right_grid_item_img);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }

        Uri uri = Uri.parse(lists.get(i).getIcon());
        holder.img.setImageURI(uri);

        holder.tv.setText(lists.get(i).getName());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtil.show(context,lists.get(i).getName()+"+++"+lists.get(i).getPscid(),2000);
                Intent intent = new Intent(context, SubClassification.class);
                intent.putExtra("pscid",lists.get(i).getPscid()+"");
                Toast.makeText(context,""+lists.get(i).getPscid(),Toast.LENGTH_LONG).show();
                context.startActivity(intent);
//                Toast.makeText(context,"准备跳转",Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    class MyHolder {
        TextView tv;
        SimpleDraweeView img;
    }
}
