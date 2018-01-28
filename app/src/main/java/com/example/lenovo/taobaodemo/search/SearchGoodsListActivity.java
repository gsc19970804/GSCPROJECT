package com.example.lenovo.taobaodemo.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.goodsxiangqing.GoodsActivity;
import com.example.lenovo.taobaodemo.search.adapter.SearchGoodsListAdapter;
import com.example.lenovo.taobaodemo.search.api.Url;
import com.example.lenovo.taobaodemo.search.bean.Bean;
import com.example.lenovo.taobaodemo.search.presenter.Passer;
import com.example.lenovo.taobaodemo.search.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SearchGoodsListActivity extends AppCompatActivity implements IView {

    @Bind(R.id.searchxrlv)
    XRecyclerView searchxrlv;
    private String goodsname;
    private SearchGoodsListAdapter searchGoodsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods_list);
        ButterKnife.bind(this);


        //获取搜索关键字
        Intent intent = getIntent();
        goodsname = intent.getStringExtra("goodsname");
        //http://120.27.23.105/product/searchProducts?keywords=笔记本
        //请求数据
        Passer passer = new Passer(this);
        String s = Url.SEARCHURL + "?source=android&keywords=" + goodsname + "&page=1";
        passer.onLoad(s, 0);
    }

    //搜索成功
    @Override
    public void onSearchSuceese(Bean bean) {
        Toast.makeText(this, bean.getMsg() + "", Toast.LENGTH_SHORT).show();
        final List<Bean.DataBean> data = bean.getData();
        Log.e("DADA", data.size() + "");
        searchxrlv.setLayoutManager(new LinearLayoutManager(this));
        searchGoodsListAdapter = new SearchGoodsListAdapter(data, SearchGoodsListActivity.this);
        searchxrlv.setAdapter(searchGoodsListAdapter);


        //条目点击长按事件
        searchGoodsListAdapter.setOnClickCener(new SearchGoodsListAdapter.setOnClick() {
            @Override
            public void onClick(Bean.DataBean databean, int postion) {
                Intent intent = new Intent(SearchGoodsListActivity.this, GoodsActivity.class);
                intent.putExtra("ID", "1");
                intent.putExtra("searchpid", data.get(postion).getPid());
                startActivity(intent);

            }

            @Override
            public void onLongClick(Bean.DataBean databean, int postion) {
                Toast.makeText(SearchGoodsListActivity.this, "你长按了" + databean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //搜索失败
    @Override
    public void onSearchErreo(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
