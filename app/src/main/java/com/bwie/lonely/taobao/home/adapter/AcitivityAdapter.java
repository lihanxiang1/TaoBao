package com.bwie.lonely.taobao.home.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.lonely.taobao.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by Lonely on 2017/12/7.
 */

public class AcitivityAdapter extends RecyclerView.Adapter<AcitivityAdapter.MyViewHolder> {
    // 数据源
    ArrayList<String> lists;
    //HomeBean homeBean;
    // 上下文
    Context context;

    public AcitivityAdapter(ArrayList<String> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.home_activity_item, parent,
                false));
        return holder;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Uri uri = Uri.parse(lists.get(position));

        holder.img.setImageURI(uri);

        // 点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        //return homeBean.getData().getActivityInfo().getActivityInfoList().size();
        return lists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img;

        public MyViewHolder(View view) {
            super(view);
            img = view.findViewById(R.id.activity_item_img);
        }
    }
}
