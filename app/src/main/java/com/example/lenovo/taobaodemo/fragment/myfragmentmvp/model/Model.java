package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.model;

import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean.DingDanBean;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean.UpdateDingDan;

import java.io.IOException;

import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * author:Created by WangZhiQiang on 2018/1/5.
 */

public class Model implements IModel {

    private setOnfinshing setOnfinshing;

    public interface setOnfinshing {
        void onFinsh(DingDanBean dingDanBean);

        void onUpdateFinsh(UpdateDingDan updateDingDan);
    }

    public void setJiekou(setOnfinshing setOnfinshing) {
        this.setOnfinshing = setOnfinshing;
    }

    //请求数据
    @Override
    public void onLoad(final String url, int i) {


        if (i == 0) {
            OkHttp3Utils.doGet(url, new GsonObjectCallback<DingDanBean>() {
                @Override
                public void onUi(DingDanBean dingDanBean) {
                    if (dingDanBean != null && setOnfinshing != null) {
                        setOnfinshing.onFinsh(dingDanBean);
                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

        } else if (i == 1) {
            OkHttp3Utils.doGet(url, new GsonObjectCallback<UpdateDingDan>() {
                @Override
                public void onUi(UpdateDingDan updateDingDan) {
                    if (setOnfinshing != null) {
                        setOnfinshing.onUpdateFinsh(updateDingDan);
                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

        }


    }
}
