package com.bwie.lonely.taobao.shopcart.adapter;

/**
 * Created by Lonely on 2017/12/8.
 */

public interface IMyCartExpandChange {
    // 组视图的点击事件
    void CheckGroup(int GroupPosition);

    // 子视图的点击事件
    void CheckChild(int GroupPosition,int ChildPosition);

    // 增加商品的数量
    void AddNum(int GroupPosition,int ChildPosition);

    // 减少商品的数量
    void JianNum(int GroupPosition,int ChildPosition);

    // 删除商品的事件
    void DelItem(int GroupPosition,int ChildPosition);
}
