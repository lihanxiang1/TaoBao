package com.bwie.lonely.taobao.classify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.classify.bean.RightBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lonely on 2017/12/7.
 */

public class RightAdapter extends BaseAdapter {
    // 数据源
    RightBean rightBean;
    // 上下文
    Context context;
    ArrayList<RightBean.DataBean.ListBean> right_lists;
    // 用于展示无图gridView
    //ArrayList<String> right_lists;

    public RightAdapter(RightBean rightBean, Context context) {
        this.rightBean = rightBean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return rightBean.getData().size();
    }

    @Override
    public Object getItem(int i) {
        return rightBean.getData().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.classify_right_item, null);
            holder = new MyHolder();
            holder.tv = view.findViewById(R.id.sort_right_title);
            holder.gridView = view.findViewById(R.id.sort_right_grid);
            view.setTag(holder);
        } else {
            holder = (MyHolder) view.getTag();
        }

        holder.tv.setText(rightBean.getData().get(i).getName());

        right_lists = new ArrayList<>();

        for (int j = 0; j < rightBean.getData().get(i).getList().size(); j++) {
            List<RightBean.DataBean.ListBean> list = rightBean.getData().get(i).getList();
            RightBean.DataBean.ListBean listBean = list.get(j);
            right_lists.add(new RightBean.DataBean.ListBean(listBean.getIcon(), listBean.getName(), listBean.getPcid(), listBean.getPscid()));
        }
//        System.out.println("---right_lists.toString() = " + right_lists.toString());

        // 无图的gridview
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,right_lists);

        /*有图的gridview*/
        final RightItemAdapter adapter = new RightItemAdapter(right_lists, context);
        holder.gridView.setAdapter(adapter);

        return view;
    }

    class MyHolder {
        TextView tv;
        GridView gridView;
    }
}
