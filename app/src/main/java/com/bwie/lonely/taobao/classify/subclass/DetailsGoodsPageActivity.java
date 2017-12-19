package com.bwie.lonely.taobao.classify.subclass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bwie.lonely.taobao.R;
import com.bwie.lonely.taobao.classify.subclass.bean.DetailsBean;
import com.bwie.lonely.taobao.classify.subclass.view.CustomerVideoView;
import com.bwie.lonely.taobao.other.ApiServer;
import com.bwie.lonely.taobao.other.Field;
import com.bwie.lonely.taobao.other.GlideImaGlideImageLoader;
import com.bwie.lonely.taobao.other.SharedPreferencesUtils;
import com.bwie.lonely.taobao.other.ToastUtil;
import com.bwie.lonely.taobao.shopcart.bean.AddCartBean;
import com.bwie.lonely.taobao.web.UserPageActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailsGoodsPageActivity extends AppCompatActivity {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.details_linear1)
    LinearLayout detailsLinear1;
//    @BindView(R.id.video_view)
//    CustomerVideoView videoView;
    @BindView(R.id.goods_details_banner)
    Banner goodsDetailsBanner;
    @BindView(R.id.search_goods_title)
    TextView searchGoodsTitle;
    @BindView(R.id.search_goods_subhead)
    TextView searchGoodsSubhead;
    @BindView(R.id.search_goods_bargainPrice)
    TextView searchGoodsBargainPrice;
    @BindView(R.id.search_goods_price)
    TextView searchGoodsPrice;
    @BindView(R.id.search_goods_salenum)
    TextView searchGoodsSalenum;
    @BindView(R.id.kefu)
    RadioButton kefu;
    @BindView(R.id.dianpu)
    RadioButton dianpu;
    @BindView(R.id.shoucang)
    RadioButton shoucang;
    @BindView(R.id.add_cart)
    Button addCart;
    @BindView(R.id.add_buy)
    Button addBuy;
    @BindView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    @BindView(R.id.add_cart_page_img)
    SimpleDraweeView addCartPageImg;
    @BindView(R.id.add_cart_page_title)
    TextView addCartPageTitle;
    @BindView(R.id.add_cart_page_price)
    TextView addCartPagePrice;
    @BindView(R.id.add_cart_page_jian)
    Button addCartPageJian;
    @BindView(R.id.add_cart_page_num)
    EditText addCartPageNum;
    @BindView(R.id.add_cart_page_jia)
    Button addCartPageJia;
    @BindView(R.id.add_cart_page_yes)
    Button addCartPageYes;
    @BindView(R.id.add_cart_bottom_layout)
    LinearLayout addCartBottomLayout;
//    @BindView(R.id.activity_details_page2)
//    ConstraintLayout activityDetailsPage2;

    /*banner轮播图集合*/
    ArrayList<String> banner_lists;

//    private PlayerView playerView;
    private boolean isLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_goods_page);

        ButterKnife.bind(this);

        init();
    }

    private void init() {
        banner_lists = new ArrayList<>();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isLogin = (boolean) SharedPreferencesUtils.getParam(DetailsGoodsPageActivity.this, "isLogin", false);
        String pid = getIntent().getStringExtra("pid");
        /*传入pid查询详情页的方法*/
        GetData(pid);
    }

    public void GetData(String pid) {
        // 存取pid值
        SharedPreferencesUtils.setParam(DetailsGoodsPageActivity.this, "pid", pid);
        ApiServer retrofit = new Retrofit.Builder()
                .baseUrl(Field.IP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(ApiServer.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("pid", pid);
        Observable<DetailsBean> data = retrofit.getDetailsData(Field.SORT_DETAILS_PATH, map);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DetailsBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("details", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("details", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        DetailsBean.DataBean data = detailsBean.getData();
                        searchGoodsTitle.setText(data.getTitle());
                        searchGoodsSubhead.setText(data.getSubhead().substring(0,20)+"……");
                        searchGoodsBargainPrice.setText("商品原价 " + data.getBargainPrice());
                        searchGoodsPrice.setText("￥商品现价" + data.getPrice());
                        searchGoodsSalenum.setText(data.getSalenum() + "人付款");

                        /*配置banner轮播图*/
                        String[] split = data.getImages().split("\\|");
                        for (int i = 0; i < split.length; i++) {
                            banner_lists.add(split[i]);
                        }
                        goodsDetailsBanner.setImages(banner_lists);
                        goodsDetailsBanner.setImageLoader(new GlideImaGlideImageLoader()).isAutoPlay(false).start();

                        // 播放视频
                        //ShowMovie();
//                        ShowMovie_VideoView();

                        // 加入购物车弹出框赋值
                        ShowAddCartData(split[0], data.getTitle(), data.getPrice());
                    }
                });
    }

    /*加入购物车弹出框信息赋值*/
    public void ShowAddCartData(String img, String title, String price) {
        addCartPageImg.setImageURI(Uri.parse(img));
        addCartPageTitle.setText(title.substring(0, 10) + "……");
        addCartPagePrice.setText("单价 " + price);

    }

    /*利用videoView播放视频*/
//    public void ShowMovie_VideoView() {
//        VideoView mVv = (VideoView) findViewById(R.id.video_view);
//        mVv.setMediaController(new MediaController(this));
//        // 播放在线视频
//        mVv.setVideoPath(String.valueOf(Uri.parse("http://9890.vod.myqcloud.com/9890_9c1fa3e2aea011e59fc841df10c92278.f20.mp4")));
//        mVv.start();
//    }

    /*播放视频的方法 ijkplayer*/
    /*public void ShowMovie() {
        View view = getLayoutInflater().from(this).inflate(R.layout.simple_player_view_player, null);
        String url = "http://9890.vod.myqcloud.com/9890_9c1fa3e2aea011e59fc841df10c92278.f20.mp4";
        playerView = new PlayerView(this)
                .setTitle("返回")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(DetailsGoodsPageActivity.this)
                                .load(R.mipmap.bajie_3)
                                .placeholder(R.color.black)
                                .error(R.color.red)
                                .into(ivThumbnail);
                    }
                })
                .setShowSpeed(true) // 显示网速
                .setPlaySource(url)
                .startPlay();
    }
*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (playerView != null) {
//            playerView.onDestroy();
//        }
    }

    @OnClick({R.id.btn_back, R.id.kefu, R.id.dianpu, R.id.shoucang, R.id.add_cart, R.id.add_buy, R.id.add_cart_page_jian, R.id.add_cart_page_jia, R.id.add_cart_page_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:// 返回
                finish();
                break;
            case R.id.kefu:
                ToastUtil.show(DetailsGoodsPageActivity.this, "亲，咱们暂时招聘不起客服", 2000);
                break;
            case R.id.dianpu:
                ToastUtil.show(DetailsGoodsPageActivity.this, "亲，咱们的店铺暂时未开放", 2000);
                break;
            case R.id.shoucang:
                ToastUtil.show(DetailsGoodsPageActivity.this, "收藏成功", 2000);
                break;
            case R.id.add_cart: // 加入购物车 弹出框的按钮
                addCartBottomLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.add_buy:
                ToastUtil.show(DetailsGoodsPageActivity.this, "弹出购买页(未完善)", 2000);
                break;
            case R.id.add_cart_page_jian:// 减
                String jian_num = addCartPageNum.getText().toString();
                int jian_number = Integer.parseInt(jian_num);
                if (jian_number > 1) {
                    addCartPageNum.setText(--jian_number + "");
                }
                break;
            case R.id.add_cart_page_jia: // 加
                String jia_num = addCartPageNum.getText().toString();
                int number2 = Integer.parseInt(jia_num);
                addCartPageNum.setText(++number2 + "");
                break;
            case R.id.add_cart_page_yes: // 确定
                if (isLogin) {
                    // 接收商品数量 uid pid
                    String uid = (String) SharedPreferencesUtils.getParam(DetailsGoodsPageActivity.this, "uid", "0");
                    String pid = (String) SharedPreferencesUtils.getParam(DetailsGoodsPageActivity.this, "pid", "1");
                    String num = addCartPageNum.getText().toString();

                    /*调用添加购物车的方法*/
                    int number = Integer.parseInt(num);
                    /*判断商品数量*/
                    /*if (number > 1) {
                        for (int i = 1; i <= number; i++) {
                            AddCart(uid, pid);
                        }
                    } else {
                        AddCart(uid, pid);
                    }*/
                    AddCart(uid, pid);

                    // 隐藏加入购物车布局
                    addCartBottomLayout.setVisibility(View.GONE);
                    ToastUtil.show(DetailsGoodsPageActivity.this, "添加购物车成功！", 2000);
                } else {
                    ToastUtil.show(DetailsGoodsPageActivity.this, "请先登录", 2000);
                    startActivity(new Intent(DetailsGoodsPageActivity.this, UserPageActivity.class));
                }
                break;
        }
    }

    /*加入购物车的方法*/
    public void AddCart(String uid, String pid) {
        ApiServer retrofit = new Retrofit.Builder()
                .baseUrl(Field.IP)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(ApiServer.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("pid", pid);
        Observable<AddCartBean> data = retrofit.AddCart(Field.ADD_CART_PATH, map);
        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<AddCartBean>() {
                    @Override
                    public void onCompleted() {
                        Log.d("addcart", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("addcart", "onError: ");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(AddCartBean addBean) {
                        Log.d("addcart", "onNext: " + addBean.getMsg());
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        addCartBottomLayout.setVisibility(View.GONE);
    }
}
