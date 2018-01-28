package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.presenter;

import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.AddAddress;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.Address;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.model.Model;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.view.AddressIView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author:Created by WangZhiQiang on 2018/1/8.
 */

public class Passer {

    private AddressIView addressIView;
    private Model model;

    public Passer(AddressIView addressIView) {
        this.addressIView = addressIView;
        model = new Model();
    }

    //请求地址
    public void onLoad() {
        Observable<Address> addressObservable = model.onLoad();
        addressObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Address>() {
                    @Override
                    public void accept(Address address) throws Exception {
                        if (addressIView != null) {
                            addressIView.onAddressSuceess(address);
                        }
                    }
                });
    }

    //添加地址
    public void onAddLoad(String addr, String name, String phone) {
        if (model != null) {

            Call<AddAddress> addAddressCall = model.onAddLoad(addr, name, phone);
            addAddressCall.enqueue(new Callback<AddAddress>() {
                @Override
                public void onResponse(Call<AddAddress> call, Response<AddAddress> response) {
                    if (addressIView != null) {
                        addressIView.onAddSuceess(response.body());
                    }
                }

                @Override
                public void onFailure(Call<AddAddress> call, Throwable t) {
                }
            });

        }
    }

    //添加默认地址
    public void onsetmorenAddr(int addrid, int status) {
        Call<AddAddress> addAddressCall = model.setmorenAddLoad(addrid, status);
        addAddressCall.enqueue(new Callback<AddAddress>() {
            @Override
            public void onResponse(Call<AddAddress> call, Response<AddAddress> response) {
                if (addressIView != null) {
                    addressIView.onAddmorenSuceess(response.body());
                }
            }

            @Override
            public void onFailure(Call<AddAddress> call, Throwable t) {
            }
        });

    }
}


