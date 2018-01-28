package com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.model;

import android.util.Log;

import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.bean.CheckShoppingCardBean;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.bean.ShengConngDingDan;

import java.io.IOException;

import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 *
 */

public class Model implements IModel {

    private setOnFinish setonfinish;

    public interface setOnFinish {
        void onFinish(CheckShoppingCardBean shoppingCardBean);

        void onDingDanFinish(ShengConngDingDan shengConngDingDan);
    }

    public void setFinsh(setOnFinish setonfinish) {
        this.setonfinish = setonfinish;
    }

    //向服务器请求数据
    @Override
    public void onLoad(final String url, int i) {
        if (i == 0) {

            OkHttp3Utils.doGet(url, new GsonObjectCallback<CheckShoppingCardBean>() {
                @Override
                public void onUi(CheckShoppingCardBean checkShoppingCardBean) {
                    if (setonfinish != null) {
                        Log.e("Url", url);
                        if (checkShoppingCardBean != null) {
                            setonfinish.onFinish(checkShoppingCardBean);
                        }

                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });
        } else if (i == 1) {

            OkHttp3Utils.doGet(url, new GsonObjectCallback<ShengConngDingDan>() {
                @Override
                public void onUi(ShengConngDingDan shengConngDingDan) {
                    if (setonfinish != null & shengConngDingDan != null) {
                        setonfinish.onDingDanFinish(shengConngDingDan);
                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

        }
    }
}
