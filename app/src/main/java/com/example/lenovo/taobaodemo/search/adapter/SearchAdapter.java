package com.example.lenovo.taobaodemo.search.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.greendao.Goods;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/31.
 */

public class SearchAdapter extends BaseAdapter {

    private List<Goods> list;
    private Context context;

    public SearchAdapter(List<Goods> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View itemView;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            itemView = View.inflate(context, R.layout.searchitem, null);
            viewHolder.goodsname = itemView.findViewById(R.id.searchname);
            itemView.setTag(viewHolder);
        } else {
            itemView = view;
            viewHolder = (ViewHolder) itemView.getTag();
        }


        viewHolder.goodsname.setText(list.get(i).getGoodsname());
        return itemView;
    }

    //优化类
    class ViewHolder {

        TextView goodsname;

    }
}
