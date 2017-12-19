package com.bwie.lonely.taobao.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.home.bean.HomeBean;
import com.bwie.lonely.taobao.other.GlideImaGlideImageLoader;
import com.bwie.lonely.taobao.web.DataWebViewActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

/**
 * Created by Lonely on 2017/12/7.
 */

public class MyHomeXrAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // 数据源
    HomeBean homeBean;
    // 上下文
    Context context;

    // 创建集合 用于保存banner图片
    ArrayList<String> banner_lists;
    ArrayList mlist;
    ArrayList hot_lists;   // 热门活动的图片
    ArrayList special_lists;   // 热门专题的图片
    //ArrayList<MySupperClass.DataBean.DefaultGoodsListBean> special_lists2;   // 热门专题的图片2
    ArrayList<HomeBean.DataBean.DefaultGoodsListBean> special_lists2;   // 热门专题的图片2
    private Intent intent;

    //定义三种常量  表示三种条目类型  // 枚举类型
    public enum Item_Type {
        TYPE_ONE, TYPE_TWO, TYPE_THREE, TYPE_FOUR
    }

    public MyHomeXrAdapter(HomeBean homeBean, Context context) {
        this.homeBean = homeBean;
        this.context = context;
        banner_lists = new ArrayList<>();
        intent = new Intent(context, DataWebViewActivity.class);
    }

    //重写getItemViewType方法 根据条件返回条目的类型
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return Item_Type.TYPE_ONE.ordinal();
        } else if (position == 1) {
            return Item_Type.TYPE_TWO.ordinal();
        } else if (position == 2) {
            return Item_Type.TYPE_THREE.ordinal();
        } else if (position == 3) {
            return Item_Type.TYPE_FOUR.ordinal();
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return 4;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == Item_Type.TYPE_ONE.ordinal()) {
            view = LayoutInflater.from(context).inflate(R.layout.home_xrecy_item1, null);
            return new ViewHolderA(view);
        } else if (viewType == Item_Type.TYPE_TWO.ordinal()) {
            view = LayoutInflater.from(context).inflate(R.layout.home_xrecy_item2,parent, false);
            return new ViewHolderB(view);
        } else if (viewType == Item_Type.TYPE_THREE.ordinal()) {
            view = LayoutInflater.from(context).inflate(R.layout.home_xrecy_item3, null);
            return new ViewHolderC(view);
        } else if (viewType == Item_Type.TYPE_FOUR.ordinal()) {
            view = LayoutInflater.from(context).inflate(R.layout.home_xrecy_item4, null);
            return new ViewHolderD(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolderA) {
            for (int i = 0; i < homeBean.getData().getAd1().size(); i++) {
                String image = homeBean.getData().getAd1().get(i).getImage();
                banner_lists.add(image);
            }
            ((ViewHolderA) holder).banner.setImageLoader(new GlideImaGlideImageLoader())
                    .setImages(banner_lists)
                    .start();
            // 点击事件
            ((ViewHolderA) holder).banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    intent.putExtra("url", homeBean.getData().getAd1().get(position).getAd_type_dynamic_data());
                    context.startActivity(intent);
                }
            });
        } else if (holder instanceof ViewHolderB) {
            ViewHolderB vb = (ViewHolderB) holder;
            vb.one_tile.setText(homeBean.getData().getAd5().get(0).getTitle());
            vb.two_title.setText(homeBean.getData().getAd5().get(1).getTitle());
            vb.three_title.setText(homeBean.getData().getAd5().get(2).getTitle());
            vb.four_title.setText(homeBean.getData().getAd5().get(3).getTitle());

            Uri uri = Uri.parse(homeBean.getData().getAd5().get(0).getImage());
            vb.one_img.setImageURI(uri);
            Uri uri2 = Uri.parse(homeBean.getData().getAd5().get(1).getImage());
            vb.two_img.setImageURI(uri2);
            Uri uri3 = Uri.parse(homeBean.getData().getAd5().get(2).getImage());
            vb.three_img.setImageURI(uri3);
            Uri uri4 = Uri.parse(homeBean.getData().getAd5().get(3).getImage());
            vb.four_img.setImageURI(uri4);

            vb.one_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("url", homeBean.getData().getAd5().get(0).getUrl());
                    context.startActivity(intent);
                }
            });
            vb.two_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("url", homeBean.getData().getAd5().get(1).getUrl());
                    context.startActivity(intent);
                }
            });
            vb.three_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("url", homeBean.getData().getAd5().get(2).getUrl());
                    context.startActivity(intent);
                }
            });
            vb.four_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent.putExtra("url", homeBean.getData().getAd5().get(3).getUrl());
                    context.startActivity(intent);
                }
            });
        } else if (holder instanceof ViewHolderC) {
            hot_lists = new ArrayList();
            AcitivityAdapter adapter;
            // 热门活动和倒计时
            ViewHolderC vb = (ViewHolderC) holder;
            for (int i = 0; i < homeBean.getData().getActivityInfo().getActivityInfoList().size(); i++) {
                HomeBean.DataBean.ActivityInfoBean.ActivityInfoListBean listBean = homeBean.getData().getActivityInfo().getActivityInfoList().get(i);
                hot_lists.add(listBean.getActivityImg());
            }
            vb.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
            vb.recyclerView.setAdapter(adapter = new AcitivityAdapter(hot_lists, context));

            adapter.setOnItemClickLitener(new AcitivityAdapter.OnItemClickLitener() {
                @Override
                public void onItemClick(View view, int position) {
                    if (position != 0) {
                        intent.putExtra("url", homeBean.getData().getActivityInfo().getActivityInfoList().get(position).getActivityData());
                        context.startActivity(intent);
                    }
                }
            });
        } else if (holder instanceof ViewHolderD) {
            special_lists = new ArrayList();
            special_lists2 = new ArrayList();
            AcitivityAdapter adapter1;
            HomeGoodsAdapter adapter2;
            // 热门专题的布局
            ViewHolderD vb = (ViewHolderD) holder;
            for (int i = 0; i < homeBean.getData().getSubjects().size(); i++) {
                HomeBean.DataBean.SubjectsBean sb = homeBean.getData().getSubjects().get(i);
                special_lists.add(sb.getImage());
            }
            for (int i = 0; i < homeBean.getData().getDefaultGoodsList().size(); i++) {
                HomeBean.DataBean.DefaultGoodsListBean bean = homeBean.getData().getDefaultGoodsList().get(i);
                special_lists2.add(new HomeBean.DataBean.DefaultGoodsListBean(bean.getGoods_name(), bean.getGoods_img()));
            }
            vb.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
            vb.recyclerView.setAdapter(adapter1 = new AcitivityAdapter(special_lists, context));
            vb.recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
            vb.recyclerView2.setAdapter(adapter2 = new HomeGoodsAdapter(homeBean, context));

        }
    }
}

class ViewHolderA extends RecyclerView.ViewHolder {

    Banner banner;

    public ViewHolderA(View view) {
        super(view);
        banner = view.findViewById(R.id.home_xrecy_item_banner);
    }
}

class ViewHolderB extends RecyclerView.ViewHolder {

    public TextView one_tile, two_title, three_title, four_title;
    public SimpleDraweeView one_img, two_img, three_img, four_img;

    public ViewHolderB(View itemView) {
        super(itemView);
        one_tile = itemView.findViewById(R.id.nav_one_title);
        two_title = itemView.findViewById(R.id.nav_two_title);
        three_title = itemView.findViewById(R.id.nav_three_title);
        four_title = itemView.findViewById(R.id.nav_four_title);
        one_img = itemView.findViewById(R.id.nav_one_img);
        two_img = itemView.findViewById(R.id.nav_two_img);
        three_img = itemView.findViewById(R.id.nav_three_img);
        four_img = itemView.findViewById(R.id.nav_four_img);
    }
}

class ViewHolderC extends RecyclerView.ViewHolder {

    public RecyclerView recyclerView;

    public ViewHolderC(View itemView) {
        super(itemView);
        recyclerView = itemView.findViewById(R.id.activity_recyclerview);
    }
}

class ViewHolderD extends RecyclerView.ViewHolder {

    public RecyclerView recyclerView, recyclerView2;

    public ViewHolderD(View itemView) {
        super(itemView);
        recyclerView = itemView.findViewById(R.id.special_recyclerview);
        recyclerView2 = itemView.findViewById(R.id.home_goods_recyclerView);
    }
}