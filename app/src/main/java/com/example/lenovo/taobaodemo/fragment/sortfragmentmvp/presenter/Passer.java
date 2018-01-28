package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.presenter;

import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.AddShoppingCardBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.Goods;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.GoodsList;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortRithtChildBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.model.Model;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view.AddShoppingCardIView;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view.GoodsIView;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view.GoodsListIView;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view.IView;

/**
 * author:Created by WangZhiQiang on 2017/12/13.
 */

public class Passer implements Model.onFinishListenner {
    private GoodsIView goodsIView;
    private IView iView;
    private Model model;
    private GoodsListIView goodsListIView;
    private AddShoppingCardIView addShoppingCardIView;

    //传入商品子分类接口引用
    public Passer(IView iView) {
        this.iView = iView;
        if (model == null) {
            model = new Model();
        }
    }

    public Passer() {
        if (model == null) {
            model = new Model();
        }
    }

    //传入商品列表接口引用
    public void setGoodsListIView(GoodsListIView goodsListIView) {
        this.goodsListIView = goodsListIView;
    }

    //传入商品详情接口引用
    public void setGoodsIView(GoodsIView goodsIView) {
        this.goodsIView = goodsIView;
    }

    //传入加入购物车接口引用
    public void setAddShoppingCardIView(AddShoppingCardIView addShoppingCardIView) {
        this.addShoppingCardIView = addShoppingCardIView;
    }

    public void onLoad(String url, int i) {
        model.setOnFinishListenner(this);
        model.onLoad(url, i);
    }


    //分类结果回传
    @Override
    public void onFinsh(SortBean sortBean, int i) {

        if (iView != null) {
            if (sortBean != null) {
                iView.getSortBean(sortBean);
            }
        }

    }

    //子类结果回传
    @Override
    public void onChildFinsh(SortRithtChildBean sortRithtChildBean, int i) {

        if (iView != null) {
            if (sortRithtChildBean != null) {
                iView.getSortChildBean(sortRithtChildBean);
            }
        }

    }

    //获取商品列表数据
    @Override
    public void onGoodsListFinsh(GoodsList goodsList, int i) {
        if (goodsListIView != null) {
            if (goodsList != null) {
                goodsListIView.getGoodsList(goodsList);
            }
        }
    }

    //获取商品详情数据
    @Override
    public void onGoodsFinsh(Goods goods, int i) {
        if (goodsIView != null) {
            goodsIView.getGoods(goods);
        }
    }

    //添加购物车结果回传
    @Override
    public void onAddSPCardFinsh(AddShoppingCardBean addShoppingCardBean, int i) {
        //添加成功
        if (addShoppingCardBean.getCode().equals("0")) {
            if (addShoppingCardIView != null) {
                addShoppingCardIView.addSPCardSucessec(addShoppingCardBean.getMsg());
            }
        } else {
            //添加失败
            if (addShoppingCardIView != null) {
                addShoppingCardIView.addSPCardErroe(addShoppingCardBean.getMsg());
            }

        }

    }

}
