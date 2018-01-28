package com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.presenter;

/**
 * author:Created by WangZhiQiang on 2017/12/21.
 */

public class MoneyAndCount {

    private double price;
    private int count;


    public MoneyAndCount(double price, int count) {
        this.price = price;
        this.count = count;
    }

    public MoneyAndCount() {

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
