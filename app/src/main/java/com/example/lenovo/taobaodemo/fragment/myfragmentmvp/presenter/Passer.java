package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.presenter;

import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean.DingDanBean;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean.UpdateDingDan;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.model.Model;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.view.IView;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.view.UpdateIView;

/**
 * author:Created by WangZhiQiang on 2018/1/5.
 */

public class Passer implements Model.setOnfinshing {

    private IView iView;
    private Model model;
    private UpdateIView updateIView;

    public Passer(IView iView) {
        this.iView = iView;
        model = new Model();
    }

    public void upDateDingDan(UpdateIView updateIView) {
        this.updateIView = updateIView;
    }

    public Passer() {
        model = new Model();
    }


    public void onLoad(String url, int i) {
        model.setJiekou(this);
        model.onLoad(url, i);
    }


    //结果回调
    @Override
    public void onFinsh(DingDanBean dingDanBean) {

        if (iView != null) {
            if (dingDanBean.getCode().equals("0")) {
                iView.onReqSuceese(dingDanBean);
            } else {
                iView.onReqErrro(dingDanBean.getMsg());
            }

        }


    }

    //修改订单结果回调
    @Override
    public void onUpdateFinsh(UpdateDingDan updateDingDan) {
        if (updateIView != null) {
            if (updateDingDan.getCode().equals("0")) {
                updateIView.onUpdateSuceese(updateDingDan);
            } else {
                updateIView.onUpdateErrro(updateDingDan.getMsg());
            }
        }
    }
}
