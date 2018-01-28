package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean.DingDanBean;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/1/5.
 */

public class B4Adapter extends RecyclerView.Adapter<B4Adapter.MH> {

    private List<DingDanBean.DataBean> list;
    private Context context;

    public B4Adapter(List<DingDanBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MH(View.inflate(context, R.layout.b1adapter, null));
    }

    @Override
    public void onBindViewHolder(MH holder, final int position) {
        holder.pay.setText("商品" + (position + 1));
        holder.time.setText(list.get(position).getCreatetime() + "");
        holder.price.setText(list.get(position).getPrice() + "");
        holder.ddnum.setText(list.get(position).getOrderid() + "");
        holder.statues.setText("已取消");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MH extends RecyclerView.ViewHolder {

        TextView pay;
        TextView time;
        TextView price;
        TextView ddnum;
        TextView statues;

        public MH(View itemView) {
            super(itemView);
            pay = itemView.findViewById(R.id.pay);
            time = itemView.findViewById(R.id.times);
            price = itemView.findViewById(R.id.pricess);
            ddnum = itemView.findViewById(R.id.ddnum);
            statues = itemView.findViewById(R.id.sataus);
        }
    }

}
