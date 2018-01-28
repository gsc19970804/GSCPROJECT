package com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.presenter;

import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.bean.CheckShoppingCardBean;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.bean.ShengConngDingDan;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.model.Model;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.view.DingDan;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.view.IView;

/**
 * author:Created by WangZhiQiang on 2017/12/16.
 */

public class Passer implements Model.setOnFinish {

    private IView iView;
    private Model model;
    private DingDan dingDan;

    public Passer(IView iView) {
        this.iView = iView;
        model = new Model();
    }

    public Passer() {
    }


    public void setDingDan(DingDan dingDan) {
        this.dingDan = dingDan;
        if (model == null) {
            model = new Model();
        }
    }


    public void onLoad(String url, int i) {
        model.setFinsh(this);
        model.onLoad(url, i);
    }

    //结果回传
    @Override
    public void onFinish(CheckShoppingCardBean shoppingCardBean) {

        if (shoppingCardBean.getCode().equals("0")) {
            if (iView != null) {
                iView.getShoppingCardSucceed(shoppingCardBean);
            }
        } else {
            if (iView != null) {
                // Log.e("Code", shoppingCardBean.getCode());
                iView.getShoppingCardERROR(shoppingCardBean.getMsg());
            }

        }

    }


    //生成订单结果回调
    @Override
    public void onDingDanFinish(ShengConngDingDan shegConngDingDan) {

        if (dingDan != null) {
            if (shegConngDingDan.getCode().equals("0")) {
                dingDan.creatDinganSucceed(shegConngDingDan.getMsg());
            } else {
                dingDan.creatDingDanERROR(shegConngDingDan.getMsg());

            }
        }


    }
}
