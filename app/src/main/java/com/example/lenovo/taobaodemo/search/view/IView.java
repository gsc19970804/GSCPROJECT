package com.example.lenovo.taobaodemo.search.view;

import com.example.lenovo.taobaodemo.search.bean.Bean;

/**
 * author:Created by WangZhiQiang on 2017/12/31.
 */

public interface IView {

    void onSearchSuceese(Bean bean);

    void onSearchErreo(String msg);

}
