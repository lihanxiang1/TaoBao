package com.bwie.lonely.taobao.classify.model;

/**
 * Created by Lonely on 2017/12/7.
 */

public interface ISortModel {

    // 请求数据的接口
    void GetData();

    // 请求右侧数据的接口
    void GetRightData(int cid);

}
