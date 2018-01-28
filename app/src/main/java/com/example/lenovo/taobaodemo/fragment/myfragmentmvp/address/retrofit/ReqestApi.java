package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.retrofit;

import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.AddAddress;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.Address;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author:Created by WangZhiQiang on 2018/1/8.
 */

public interface ReqestApi {
    //.常用收货地址列表
    @GET("user/getAddrs")
    Observable<Address> reqAddress(@Query("source") String source, @Query("uid") String uid);

    //添加常用收获地址
    @GET("user/addAddr")
    Call<AddAddress> addAddress(@Query("source") String source, @Query("uid") String uid, @Query("addr") String addr, @Query("mobile") String mobile, @Query("name") String name);

    //设置默认地址
    @GET("user/setAddr")
    Call<AddAddress> setmorenAddress(@Query("source") String source, @Query("uid") String uid, @Query("addrid") String addrid, @Query("status") String status);

}


