package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.goodsxiangqing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.adapter.GoodsListAdapter;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.api.Url;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.GoodsList;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortRithtChildBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.presenter.Passer;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view.GoodsListIView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class GoodsDetailsActivity extends AppCompatActivity implements GoodsListIView {

    private SortRithtChildBean.DataBean.ListBean listbean;
    private TextView listtitles;
    private XRecyclerView goodslist;
    private Passer passer;
    private boolean style = true;
    private GoodsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        Intent intent = getIntent();
        listbean = (SortRithtChildBean.DataBean.ListBean) intent.getSerializableExtra("listBean");

        passer = new Passer();
        passer.setGoodsListIView(this);
        passer.onLoad(Url.GOODSDDETAILS + "?source=android&pscid=" + listbean.getPscid(), 2);
        Log.e("aaa", listbean.getPscid() + "");

        //初始化控件
        initView();
    }

    //初始化控件
    private void initView() {
        listtitles = (TextView) this.findViewById(R.id.listtitles);
        listtitles.setText("[" + listbean.getName() + "]，商品列表");
        goodslist = (XRecyclerView) this.findViewById(R.id.goodslist);
        goodslist.setLayoutManager(new LinearLayoutManager(this));

        listtitles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (style) {
                    goodslist.setLayoutManager(new GridLayoutManager(GoodsDetailsActivity.this, 2, GridLayoutManager.VERTICAL, false));
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                        style = false;
                    }
                } else {
                    goodslist.setLayoutManager(new LinearLayoutManager(GoodsDetailsActivity.this));
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                        style = true;
                    }
                }
            }
        });


    }

    //获取商品列表数据
    @Override
    public void getGoodsList(GoodsList goodsList) {

        if (goodsList.getCode().equals("0")) {

            if (goodsList.getData().size() > 0) {
                adapter = new GoodsListAdapter(goodsList.getData(), GoodsDetailsActivity.this);
                goodslist.setAdapter(adapter);
                //点击商品条目跳转到商品详情
                adapter.setOnClickCener(new GoodsListAdapter.setOnClick() {
                    @Override
                    public void onClick(GoodsList.DataBean databean, int postion) {
                        Intent intent = new Intent(GoodsDetailsActivity.this, GoodsActivity.class);
                        intent.putExtra("ID", "0");
                        intent.putExtra("DataBean", databean);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongClick(GoodsList.DataBean databean, int postion) {
                        Toast.makeText(GoodsDetailsActivity.this, databean.getPid() + "" + postion, Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "抱歉！服务器没有此类商品数据", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(GoodsDetailsActivity.this, "获取数据失败", Toast.LENGTH_SHORT).show();

        }


    }
}
