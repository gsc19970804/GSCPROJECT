package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.goodsxiangqing;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.api.Url;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.Goods;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.GoodsList;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.presenter.Passer;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view.AddShoppingCardIView;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view.GoodsIView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;

/***
 * 用户：15212345678
 * 密码：123456
 * uid：10822
 */
public class GoodsActivity extends AppCompatActivity implements GoodsIView, AddShoppingCardIView {

    private XBanner xqimg;
    private TextView xqtitles;
    private TextView xqmiaoshu;
    private TextView xqoldprice;
    private TextView xqnewprice;
    private TextView shoucang;
    private Button sale;
    private Button inshopping;
    private Passer passer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        //初始化Presenter
        passer = new Passer();
        //初始化组件
        initView();

        //获取商品详情参数
        Intent intent = getIntent();
        String id = intent.getStringExtra("ID");
        if (id.equals("0")) {
            GoodsList.DataBean dataBean = (GoodsList.DataBean) intent.getSerializableExtra("DataBean");
            //向服务器请求数据
            passer.setGoodsIView(this);
            passer.onLoad(Url.GOODSURL + "?source=android&pid=" + dataBean.getPid(), 3);
            Log.e("DataBean", dataBean.getPid() + "");
        } else if (id.equals("1")) {
            //向服务器请求数据
            int searchpid = intent.getIntExtra("searchpid", 1);
            passer.setGoodsIView(this);
            passer.onLoad(Url.GOODSURL + "?source=android&pid=" + searchpid, 3);
            Log.e("searchpid", searchpid + "");
        } else if (id.equals("2")) {
            int tuijianpid = intent.getIntExtra("tuijianpid", 2);
            passer.setGoodsIView(this);
            passer.onLoad(Url.GOODSURL + "?source=android&pid=" + tuijianpid, 3);
            Log.e("tuijianpid", tuijianpid + "");

        }


    }

    //初始化组件

    private void initView() {
        xqimg = (XBanner) findViewById(R.id.xqimg);
        xqtitles = (TextView) findViewById(R.id.xqtitles);
        xqmiaoshu = (TextView) findViewById(R.id.xqmiaoshu);
        xqoldprice = (TextView) findViewById(R.id.xqoldprice);
        xqoldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        xqnewprice = (TextView) findViewById(R.id.xqnewprice);
        shoucang = (TextView) findViewById(R.id.shoucang);
        sale = (Button) findViewById(R.id.sale);
        inshopping = (Button) findViewById(R.id.inshopping);

    }

    @Override
    protected void onResume() {
        super.onResume();
        xqimg.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        xqimg.stopAutoPlay();
    }


    //获取服务器返回的商品
    @Override
    public void getGoods(final Goods goods) {
        if (goods.getCode().equals("0")) {
            Toast.makeText(this, "数据获取成功", Toast.LENGTH_SHORT).show();
            xqtitles.setText(goods.getData().getTitle());
            xqmiaoshu.setText(goods.getData().getSubhead());
            xqoldprice.setText("￥" + goods.getData().getPrice() + "");
            xqnewprice.setText("￥" + goods.getData().getBargainPrice() + "");
            String[] split = goods.getData().getImages().split("\\|");
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < split.length; i++) {
                list.add(split[i]);

            }
            xqimg.setData(list, null);
            xqimg.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(GoodsActivity.this).load(list.get(position)).into((ImageView) view);
                }
            });


            //点击加入购物车监听
            inshopping.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    passer.setAddShoppingCardIView(GoodsActivity.this);
                    passer.onLoad(Url.ADDSHOOPINGCARD + "?source=android&uid=10822&pid=" + goods.getData().getPid(), 4);

                }
            });

        } else {
            Toast.makeText(this, "数据获取失败", Toast.LENGTH_SHORT).show();
        }
    }


    //加入购物车成功
    @Override
    public void addSPCardSucessec(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //加入购物车失败
    @Override
    public void addSPCardErroe(String msg) {
        Toast.makeText(this, "加入失败！" + msg, Toast.LENGTH_SHORT).show();
    }
}
