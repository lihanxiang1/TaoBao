package com.bwie.lonely.taobao.other;

import com.bwie.lonely.taobao.classify.bean.LeftBean;
import com.bwie.lonely.taobao.classify.bean.RightBean;
import com.bwie.lonely.taobao.classify.subclass.bean.DetailsBean;
import com.bwie.lonely.taobao.classify.subclass.bean.SubBean;
import com.bwie.lonely.taobao.home.bean.HomeBean;
import com.bwie.lonely.taobao.home.sousuo.bean.GoodsBean;
import com.bwie.lonely.taobao.my.bean.LoginBean;
import com.bwie.lonely.taobao.my.reg.bean.RegBean;
import com.bwie.lonely.taobao.shopcart.bean.AddCartBean;
import com.bwie.lonely.taobao.shopcart.bean.DelCartItemBean;
import com.bwie.lonely.taobao.shopcart.bean.SearchCartBean;
import com.bwie.lonely.taobao.shopcart.order.address.bean.GetAddressBean;
import com.bwie.lonely.taobao.shopcart.order.address.bean.SetAddressBean;
import com.bwie.lonely.taobao.shopcart.order.bean.AddOrderBean;
import com.bwie.lonely.taobao.shopcart.order.bean.SearchOrderBean;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Lonely on 2017/12/7.
 */

public interface ApiServer {
    // 首页数据接口
    @GET
    Observable<HomeBean> getHome(@Url String url);

    // 搜索关键字商品接口
    @POST
    Observable<GoodsBean> getSearch(@Url String url, @QueryMap Map<String, String> maps);

    // 分类 左侧接口
    @GET
    Observable<LeftBean> getLeftData(@Url String url);

    // 分类 右侧接口
    @POST
    Observable<RightBean> getRightData(@Url String url, @QueryMap HashMap<String, String> map);

    // 子分类下的商品列表接口
    @POST
    Observable<SubBean> getRightSubItem(@Url String url, @QueryMap HashMap<String, String> map);

    // 详情页的接口
    @POST
    Observable<DetailsBean> getDetailsData(@Url String url, @QueryMap HashMap<String, String> map);

    // 添加购物车的接口
    @POST
    Observable<AddCartBean> AddCart(@Url String url, @QueryMap HashMap<String, String> map);

    // 登陆接口
    @GET
    Observable<LoginBean> getLogin(@Url String url);

    // 注册接口
    @POST
    Observable<RegBean> getReg(@Url String url, @QueryMap Map<String, String> maps);

    // 查询购物车的接口
    @POST
    Observable<SearchCartBean> SearchCart(@Url String url, @QueryMap HashMap<String, String> map);

    // 删除购物车条目的接口
    @POST
    Observable<DelCartItemBean> DelCartItem(@Url String url, @QueryMap HashMap<String, String> map);

    // 用户创建订单接口
    @POST
    Observable<AddOrderBean> Create_Order(@Url String url, @QueryMap HashMap<String, String> map);

    // 用户查询订单接口@POST
    @POST
    Observable<SearchOrderBean> Get_Order(@Url String url, @QueryMap HashMap<String, String> map);

    // 用户收货地址接口@POST
    @POST
    Observable<GetAddressBean> Get_Address(@Url String url, @QueryMap HashMap<String, String> map);

    // 修改收货地址的接口
    @POST
    Observable<SetAddressBean> SetAddress(@Url String url, @QueryMap HashMap<String, String> map);

}
