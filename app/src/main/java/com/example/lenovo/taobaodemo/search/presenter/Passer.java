package com.example.lenovo.taobaodemo.search.presenter;

import com.example.lenovo.taobaodemo.search.bean.Bean;
import com.example.lenovo.taobaodemo.search.model.Model;
import com.example.lenovo.taobaodemo.search.view.IView;

/**
 * author:Created by WangZhiQiang on 2017/12/31.
 */

public class Passer implements Model.SetOnFinshing {
    private IView iView;
    private Model model;

    public Passer(IView iView) {
        this.iView = iView;
        model = new Model();
    }


    public void onLoad(String url, int i) {
        model.setOnFinshing(this);
        model.onLoad(url, i);
    }

    //数据回调
    @Override
    public void onFinsh(Bean bean) {
        if (iView != null) {
            if (bean.getCode().equals("0")) {
                iView.onSearchSuceese(bean);
            } else {
                iView.onSearchErreo(bean.getMsg());
            }
        }

    }
}
