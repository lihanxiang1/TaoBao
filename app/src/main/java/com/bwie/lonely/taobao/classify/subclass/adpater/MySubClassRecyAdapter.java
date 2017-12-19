package com.bwie.lonely.taobao.classify.subclass.adpater;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.classify.subclass.DetailsGoodsPageActivity;
import com.bwie.lonely.taobao.classify.subclass.bean.SubBean;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Lonely on 2017/12/7.
 */

public class MySubClassRecyAdapter extends RecyclerView.Adapter<MySubClassRecyAdapter.MyViewHolder> {

    // 数据源
    SubBean subBean;
    // 上下文
    Context context;

    public MySubClassRecyAdapter(SubBean subBean, Context context) {
        Log.d(subBean.getCode() , "sdsdsds");
        this.subBean = subBean;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.subclass_goods_item, null));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        SubBean.DataBean bean = subBean.getData().get(position);

        String images = bean.getImages();
        String[] split = images.split("\\|");

        Uri uri = Uri.parse(split[0]);
        holder.img.setImageURI(uri);
        holder.title.setText(bean.getTitle());
        holder.subhead.setText(bean.getSubhead().substring(0,20)+"……");
        holder.bargain.setText("商品原价 " + bean.getBargainPrice());
        holder.price.setText("￥商品现价"+bean.getPrice());
        holder.salenum.setText(bean.getSalenum() + "人已付款");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ToastUtil.show(context,subBean.getData().get(position).getPid(),2000);

                Intent intent = new Intent(context, DetailsGoodsPageActivity.class);
                intent.putExtra("pid",subBean.getData().get(position).getPid());
                Toast.makeText(context,""+subBean.getData().get(position).getPid(),Toast.LENGTH_LONG).show();
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return subBean.getData().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        // 图片
        SimpleDraweeView img;
        //       标题   商品介绍 商品原价 商品现价￥   xxx人付款
        TextView title, subhead, bargain, price, salenum;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.search_goods_img);
            title = itemView.findViewById(R.id.search_goods_title);
            subhead = itemView.findViewById(R.id.search_goods_subhead);
            bargain = itemView.findViewById(R.id.search_goods_bargainPrice);
            price = itemView.findViewById(R.id.search_goods_price);
            salenum = itemView.findViewById(R.id.search_goods_salenum);
        }
    }
}
