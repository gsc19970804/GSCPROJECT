package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.view;

import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.AddAddress;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.Address;

/**
 * author:Created by WangZhiQiang on 2018/1/8.
 */

public interface AddressIView {

    void onAddressSuceess(Address address);

    void onAddSuceess(AddAddress addAddress);

    //设置默认地址
    void onAddmorenSuceess(AddAddress addAddress);

}
