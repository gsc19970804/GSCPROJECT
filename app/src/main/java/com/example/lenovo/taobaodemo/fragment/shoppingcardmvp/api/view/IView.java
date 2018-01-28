package com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.view;

import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.bean.CheckShoppingCardBean;

/**
 * author:Created by WangZhiQiang on 2017/12/16.
 */

public interface IView {

    void getShoppingCardSucceed(CheckShoppingCardBean checkShoppingCardBean);

    void getShoppingCardERROR(String msg);
}
