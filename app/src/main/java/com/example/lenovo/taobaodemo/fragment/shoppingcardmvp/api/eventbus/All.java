package com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.eventbus;

/**
 * author:Created by WangZhiQiang on 2017/12/21.
 */

public class All {

    private boolean isall;

    public All(boolean isall) {
        this.isall = isall;
    }

    public All() {
    }

    public boolean isall() {
        return isall;
    }

    public void setIsall(boolean isall) {
        this.isall = isall;
    }
}
