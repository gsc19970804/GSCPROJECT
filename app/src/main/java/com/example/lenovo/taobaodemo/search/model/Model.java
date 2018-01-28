package com.example.lenovo.taobaodemo.search.model;

import android.util.Log;

import com.example.lenovo.taobaodemo.search.bean.Bean;

import java.io.IOException;

import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * author:Created by WangZhiQiang on 2017/12/31.
 */

public class Model implements IModel {

    private SetOnFinshing setOnFinshing;

    public interface SetOnFinshing {
        void onFinsh(Bean bean);
    }

    public void setOnFinshing(SetOnFinshing setOnFinshing) {
        this.setOnFinshing = setOnFinshing;
    }

    //向后台请求数据
    @Override
    public void onLoad(final String url, int i) {
        OkHttp3Utils.doGet(url, new GsonObjectCallback<Bean>() {
            @Override
            public void onUi(Bean bean) {
                if (setOnFinshing != null) {
                    setOnFinshing.onFinsh(bean);
                }
                Log.e("URL", url);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }
}
