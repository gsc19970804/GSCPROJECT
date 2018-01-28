package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortRithtChildBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.goodsxiangqing.GoodsDetailsActivity;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/14.
 */

public class ChildssAdapter extends BaseAdapter {

    private Context context;
    private List<SortRithtChildBean.DataBean.ListBean> list;

    public ChildssAdapter(Context context, List<SortRithtChildBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view1;
        MyViewHolder holder;
        if (view == null) {
            holder = new MyViewHolder();
            view1 = View.inflate(context, R.layout.childssitem, null);
            holder.goodsname = (Button) view1.findViewById(R.id.goodsname);
            view1.setTag(holder);
        } else {
            view1 = view;
            holder = (MyViewHolder) view1.getTag();
        }
        holder.goodsname.setText(list.get(i).getName());


        holder.goodsname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SortRithtChildBean.DataBean.ListBean listBean = list.get(i);
                Intent intent = new Intent(context, GoodsDetailsActivity.class);
                intent.putExtra("listBean", listBean);
                context.startActivity(intent);
            }
        });

        return view1;
    }


    class MyViewHolder {
        Button goodsname;
    }

}
