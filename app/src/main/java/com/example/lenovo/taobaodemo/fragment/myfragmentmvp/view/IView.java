package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.view;

import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean.DingDanBean;

/**
 * author:Created by WangZhiQiang on 2018/1/5.
 */

public interface IView {


    void onReqSuceese(DingDanBean dingDanBean);

    void onReqErrro(String msg);

}
