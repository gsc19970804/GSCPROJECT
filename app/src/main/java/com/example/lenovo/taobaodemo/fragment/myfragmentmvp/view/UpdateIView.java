package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.view;

import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean.UpdateDingDan;

/**
 * author:Created by WangZhiQiang on 2018/1/7.
 */

public interface UpdateIView {

    void onUpdateSuceese(UpdateDingDan updateDingDan);

    void onUpdateErrro(String msg);

}
