package com.bwie.lonely.taobao.other;

/**
 * Created by Lonely on 2017/12/7.
 */

public class Field {

    public static final String LOGIN_URL_PATH = "user/login"; // 拼接：mobile =?&password=?;
    public static final String REG_URL_PATH = "user/reg"; // 拼接：mobile =?&password=?;

    // 主页数据
    //public static final String HOME_PATH = "http://result.eolinker.com/umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage";
    public static final String HOME_PATH = "http://result.eolinker.com/";
    public static final String HOME_PATH_DATA = "umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage";

    // 搜索框输入搜索的商品
    public static final String SEARCHE_GOODS = "http://120.27.23.105/"; // 拼接?keywords=笔记本&page=1
    public static final String SEARCHE_GOODS_PATH = "product/searchProducts"; // 拼接?keywords=笔记本&page=1

    // 删除购物车条目的接口
    public static final String IP = "http://120.27.23.105/";
    public static final String DELCAR_TITEM = "product/deleteCart?source=android&";

    // 分类左侧接口
    public static final String SORT_LEFT_PATH = "product/getCatagory";

    // 分类右侧接口
    public static final String SORT_RIGHT_PATH = "product/getProductCatagory";

    // 子分类下的商品列表接口
    public static final String SORT_RIGHT_ITEM_PATH = "product/getProducts";

    // 详情页单条目的接口
    public static final String SORT_DETAILS_PATH = "product/getProductDetail?source=android&";

    // 添加购物车的接口
    public static final String ADD_CART_PATH = "product/addCart?source=android&";

    // 查询购物车的接口
    public static final String SEARCH_CART_PATH = "product/getCarts?source=android&";

    // 用户查询订单接口
    public static final String GET_ORDER = "product/getOrders";

    // 用户查询订单接口
    public static final String GET_ADDRESS = "user/getAddrs";

    // 添加收货地址接口
    public static final String SET_ADDRESS = "user/addAddr";

    // 修改收货地址接口
    public static final String CHANGE_ADDRESS = "user/updateAddr";

    // 用户创建订单接口
    public static final String CREATE_ORDER = "product/createOrder";

}
