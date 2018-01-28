package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.model;

import android.util.Log;

import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.AddShoppingCardBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.Goods;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.GoodsList;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortRithtChildBean;

import java.io.IOException;

import okhttp3.Call;
import utils.GsonObjectCallback;
import utils.OkHttp3Utils;

/**
 * author:Created by WangZhiQiang on 2017/12/13.
 */

public class Model implements IModel {

    private onFinishListenner onFinishListenner;

    public interface onFinishListenner {
        void onFinsh(SortBean sortBean, int i);

        void onGoodsListFinsh(GoodsList goodsList, int i);

        void onChildFinsh(SortRithtChildBean sortRithtChildBean, int i);

        void onGoodsFinsh(Goods goods, int i);

        void onAddSPCardFinsh(AddShoppingCardBean addShoppingCardBean, int i);
    }

    public void setOnFinishListenner(onFinishListenner onFinishListenner) {
        this.onFinishListenner = onFinishListenner;

    }

    ;

    //获取数据
    @Override
    public void onLoad(final String url, int i) {
        if (i == 0) {
            //获取商品分类数据
            OkHttp3Utils.doGet(url, new GsonObjectCallback<SortBean>() {
                @Override
                public void onUi(SortBean sortBean) {
                    if (onFinishListenner != null) {
                        if (sortBean != null) {
                            onFinishListenner.onFinsh(sortBean, 0);
                        }

                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });
        } else if (i == 1) {
            //请求商品子分类数据
            OkHttp3Utils.doGet(url, new GsonObjectCallback<SortRithtChildBean>() {
                @Override
                public void onUi(SortRithtChildBean sortRithtChildBean) {
                    if (onFinishListenner != null) {
                        if (sortRithtChildBean != null) {
                            onFinishListenner.onChildFinsh(sortRithtChildBean, 1);
                        }
                    }

                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

        } else if (i == 2) {
            //请求商品列表数据
            OkHttp3Utils.doGet(url, new GsonObjectCallback<GoodsList>() {
                @Override
                public void onUi(GoodsList goodsList) {

                    if (onFinishListenner != null) {
                        if (goodsList != null) {
                            onFinishListenner.onGoodsListFinsh(goodsList, 2);
                        }

                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

        } else if (i == 3) {
            //获取商品详情数据
            OkHttp3Utils.doGet(url, new GsonObjectCallback<Goods>() {
                @Override
                public void onUi(Goods goods) {
                    if (onFinishListenner != null) {
                        if (goods != null) {
                            onFinishListenner.onGoodsFinsh(goods, 3);
                        }
                    }
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

        } else if (i == 4) {
            //获取添加购物车结果
            OkHttp3Utils.doGet(url, new GsonObjectCallback<AddShoppingCardBean>() {
                @Override
                public void onUi(AddShoppingCardBean addShoppingCardBean) {

                    if (onFinishListenner != null) {
                        if (addShoppingCardBean != null) {
                            onFinishListenner.onAddSPCardFinsh(addShoppingCardBean, 4);
                            Log.e("URL", url);
                            Log.e("URL", addShoppingCardBean.getCode());
                        }
                    }


                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });

        }

    }
}
