package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.model;

import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.AddAddress;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.Address;

import io.reactivex.Observable;
import retrofit2.Call;

/**
 * author:Created by WangZhiQiang on 2018/1/8.
 */

public interface AddressModel {

    Observable<Address> onLoad();

    Call<AddAddress> onAddLoad(String addr, String phone, String name);

    Call<AddAddress> setmorenAddLoad(int addrid, int staus);

}
