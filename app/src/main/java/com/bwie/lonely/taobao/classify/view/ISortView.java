package com.bwie.lonely.taobao.classify.view;

import com.bwie.lonely.taobao.classify.bean.LeftBean;
import com.bwie.lonely.taobao.classify.bean.RightBean;

/**
 * Created by Lonely on 2017/12/7.
 */

public interface ISortView {

    // 展示左侧数据
    void ShowLeftData(LeftBean leftBean);

    // 展示右侧数据
    void ShowRightData(RightBean rightBean);
}
