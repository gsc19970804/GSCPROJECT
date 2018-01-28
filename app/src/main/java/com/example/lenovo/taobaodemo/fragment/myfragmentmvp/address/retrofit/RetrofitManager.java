package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求用OKhttp+Retrofit+Rxjava实现（自己封装，加单例模式，（评分重点））完成商品详情页
 */

public class RetrofitManager {
    private static final String BASEURL = "http://120.27.23.105/";
    private static Retrofit retrofit;

    public static RetrofitManager newInstance() {

        return new RetrofitManager();
    }

    private RetrofitManager() {

        retrofit = getRetrofit();
    }

    private static OkHttpClient getOkhttp() {
        return new OkHttpClient.Builder().connectTimeout(10000, TimeUnit.MILLISECONDS).build();
    }


    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .client(getOkhttp())
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T creat(Class<T> t) {
        return retrofit.create(t);
    }

}
