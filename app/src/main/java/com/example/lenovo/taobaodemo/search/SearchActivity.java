package com.example.lenovo.taobaodemo.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.database.GoodsDao;
import com.example.lenovo.taobaodemo.greendao.Goods;
import com.example.lenovo.taobaodemo.greendao.GreenDaoUtil;
import com.example.lenovo.taobaodemo.search.adapter.SearchAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity {

    @Bind(R.id.searchgoods)
    EditText searchgoods;
    @Bind(R.id.searchlist)
    ListView searchlist;
    @Bind(R.id.tosearch)
    ImageView tosearch;
    @Bind(R.id.lisijilu)
    TextView lisijilu;
    @Bind(R.id.clerns)
    Button clerns;
    private GoodsDao goodsDao;
    private List<Goods> list;
    private SearchAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        //得到表操作对象
        goodsDao = GreenDaoUtil.getDaoSession().getGoodsDao();

        //清除搜索记录
        clerns.setOnClickListener(new View.OnClickListener() {
            private AlertDialog show;
            private Button yes;
            private Button no;

            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(SearchActivity.this);
                View inflate = View.inflate(SearchActivity.this, R.layout.clern, null);

                no = (Button) inflate.findViewById(R.id.no);
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        show.dismiss();
                    }
                });
                yes = (Button) inflate.findViewById(R.id.yes);
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        list.clear();
                        searchAdapter.notifyDataSetChanged();
                        delet();
                        querySearchData();
                        show.dismiss();
                    }
                });
                alert.setView(inflate);
                show = alert.show();
            }
        });
    }


    //当页面可见状态下查询搜索记录
    @Override
    protected void onResume() {
        super.onResume();
        querySearchData();
    }


    //点击搜索图标跳转到商品列表
    @OnClick(R.id.tosearch)
    public void onViewClicked() {

        if (!TextUtils.isEmpty(searchgoods.getText().toString())) {
            Intent intent = new Intent(SearchActivity.this, SearchGoodsListActivity.class);
            intent.putExtra("goodsname", searchgoods.getText().toString());
            startActivity(intent);

            //搜索记录保存SQLite
            saveSearchData(searchgoods.getText().toString());
            searchgoods.setText("");

        } else {
            Toast.makeText(this, "请输入商品名称", Toast.LENGTH_SHORT).show();
        }

    }

    //搜索记录保存SQLite
    private void saveSearchData(String goodsname) {
        //判断商品记录是否存在
        List<Goods> list = goodsDao.queryBuilder().where(GoodsDao.Properties.Goodsname.eq(goodsname)).list();
        Log.e("RE",list.size()+"");
        if (list.size()==0){
            Goods goods = new Goods(null, goodsname);
            goodsDao.insert(goods);
        }

    }


    //查询搜索记录
    private void querySearchData() {
        list = goodsDao.queryBuilder().list();
        Log.e("List", list.size() + "");
        //判断查询结果
        if (list.size() > 0) {
            searchAdapter = new SearchAdapter(list, SearchActivity.this);
            searchlist.setAdapter(searchAdapter);
            lisijilu.setVisibility(View.VISIBLE);
            clerns.setVisibility(View.VISIBLE);
        } else {
            lisijilu.setVisibility(View.INVISIBLE);
            clerns.setVisibility(View.INVISIBLE);
        }

    }

    //删除所有商品记录
    private void delet() {
        List<Goods> list = goodsDao.queryBuilder().list();
        for (int i = 0; i < list.size(); i++) {
            goodsDao.delete(list.get(i));
        }
    }


}
