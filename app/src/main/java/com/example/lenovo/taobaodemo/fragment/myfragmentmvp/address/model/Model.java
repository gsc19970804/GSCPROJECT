package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.model;

import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.AddAddress;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.Address;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.retrofit.ReqestApi;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.retrofit.RetrofitManager;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * author:Created by WangZhiQiang on 2018/1/8.
 */

public class Model implements AddressModel {


    @Override
    public Observable<Address> onLoad() {
        return RetrofitManager.newInstance().creat(ReqestApi.class).reqAddress("android", "10822");
    }

    @Override
    public Call<AddAddress> onAddLoad(String addr, String name, String phone) {
        return RetrofitManager.newInstance().creat(ReqestApi.class).addAddress("android", "10822", addr, phone, name);
    }

    //添加默认地址
    @Override
    public Call<AddAddress> setmorenAddLoad(int addrid, int staus) {
        return RetrofitManager.newInstance().creat(ReqestApi.class).setmorenAddress("android", "10822", addrid + "", staus + "");

    }


}
