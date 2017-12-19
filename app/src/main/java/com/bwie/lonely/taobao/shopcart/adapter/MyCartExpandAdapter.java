package com.bwie.lonely.taobao.shopcart.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.shopcart.bean.SearchCartBean;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Lonely on 2017/12/8.
 */

public class MyCartExpandAdapter extends BaseExpandableListAdapter {
    // 数据源
    SearchCartBean cartBean;
    // 上下文
    Context context;

    /*设置显示隐藏 操作条目的布局*/
    public int flag = View.GONE;

    public MyCartExpandAdapter(SearchCartBean cartBean, Context context) {
        this.cartBean = cartBean;
        this.context = context;
    }

    /*调用一个接口 用于在条目的点击事件的各个功能模块里*/
    private IMyCartExpandChange myCartExpandChange;
    /*构造方法*/
    public void MyCartExpandChange(IMyCartExpandChange myCartExpandChange) {
        this.myCartExpandChange = myCartExpandChange;
    }

    @Override
    public int getGroupCount() {
        return cartBean.getData().size();
    }

    @Override
    public int getChildrenCount(int i) {
        return cartBean.getData().get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return cartBean.getData().get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return cartBean.getData().get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        //设置为true表示数据源刷新
        return true;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        final MyGroupHolder holder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.cart_group_item,null);
            holder = new MyGroupHolder();
            holder.cb = view.findViewById(R.id.cart_group_check);
            holder.title = view.findViewById(R.id.cart_group_name);
            view.setTag(holder);
        }else{
            holder = (MyGroupHolder) view.getTag();
        }
        // 赋值
        holder.cb.setChecked(cartBean.getData().get(i).isSelected());
        holder.title.setText(cartBean.getData().get(i).getSellerName());

        /*holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartBean.getData().get(i).setSelected(holder.cb.isChecked());
                for (int j = 0 ; j < cartBean.getData().get(i).getList().size();j++){
                    cartBean.getData().get(i).getList().get(j).setChildSelected(cartBean.getData().get(i).isSelected());
                }
                notifyDataSetChanged();
            }
        });*/
        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCartExpandChange.CheckGroup(i);
            }
        });

        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        final MyChildHolder holder;
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.cart_child_item,null);
            holder = new MyChildHolder();
            holder.cb = view.findViewById(R.id.cart_child_checkbox);
            holder.goods_img = view.findViewById(R.id.cart_child_img);
            holder.del_img = view.findViewById(R.id.cart_child_del);
            holder.jia= view.findViewById(R.id.cart_child_jia);
            holder.jian= view.findViewById(R.id.cart_child_jian);
            holder.num = view.findViewById(R.id.cart_child_num);
            holder.edit_num = view.findViewById(R.id.cart_child_Edit_num);
            holder.title = view.findViewById(R.id.cart_child_title);
            holder.price = view.findViewById(R.id.cart_child_price);
            holder.rl_edit = view.findViewById(R.id.rl_edit);
            view.setTag(holder);
        }else{
            holder = (MyChildHolder) view.getTag();
        }

        SearchCartBean.DataBean.ListBean listBean = cartBean.getData().get(i).getList().get(i1);
        holder.cb.setChecked(listBean.isChildSelected());
        String img = listBean.getImages();
        String[] split = img.split("\\|");
        Uri uri = Uri.parse(split[0]);
        holder.goods_img.setImageURI(uri);
        holder.title.setText(listBean.getTitle());
        holder.price.setText("￥"+(listBean.getPrice()));
        holder.num.setText("x"+listBean.getNum());
        holder.edit_num.setText(listBean.getNum()+"");

        /*显示隐藏布局*/
        holder.rl_edit.setVisibility(flag);
        holder.del_img.setVisibility(flag);

        // 子条目的复选框
        holder.cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCartExpandChange.CheckChild(i,i1);
            }
        });

        // 子条目的减法监听事件
        holder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCartExpandChange.JianNum(i,i1);
            }
        });
        // 子条目的加法监听事件
        holder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCartExpandChange.AddNum(i,i1);
            }
        });

        // 删除按钮的监听事件
        holder.del_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCartExpandChange.DelItem(i,i1);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /*组控件*/
    class MyGroupHolder{
        CheckBox cb;
        TextView title;
    }

    /*子控件*/
    class MyChildHolder{
        CheckBox cb;
        SimpleDraweeView goods_img;
        ImageView del_img;
        //            ￥xxx  x1
        TextView title,price,num,jian,jia,edit_num;
        LinearLayout rl_edit;
    }
}
