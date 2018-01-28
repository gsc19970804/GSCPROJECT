package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean.DingDanBean;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/1/5.
 */

public class B2Adapter extends RecyclerView.Adapter<B2Adapter.MH> {

    private List<DingDanBean.DataBean> list;
    private Context context;
    private setOnclicke setOnclicke;

    public B2Adapter(List<DingDanBean.DataBean> list, Context context, setOnclicke setOnclicke) {
        this.list = list;
        this.context = context;
        this.setOnclicke = setOnclicke;
    }


    public interface setOnclicke {
        void onClick(int postion);

        void onLongClick(int postion);
    }

    @Override
    public MH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MH(View.inflate(context, R.layout.b2adapter, null));
    }

    @Override
    public void onBindViewHolder(MH holder, final int position) {

        //点击支付监听
        holder.pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setOnclicke != null) {
                    setOnclicke.onClick(position);
                }
            }
        });

        holder.quxiao.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (setOnclicke != null) {
                    setOnclicke.onLongClick(position);
                }
                return true;
            }
        });


        holder.time.setText(list.get(position).getCreatetime() + "");
        holder.price.setText(list.get(position).getPrice() + "");
        holder.ddnum.setText(list.get(position).getOrderid() + "");
        holder.statues.setText("待支付");
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
        Button quxiao;

        public MH(View itemView) {
            super(itemView);
            pay = itemView.findViewById(R.id.pay);
            time = itemView.findViewById(R.id.times);
            price = itemView.findViewById(R.id.pricess);
            ddnum = itemView.findViewById(R.id.ddnum);
            statues = itemView.findViewById(R.id.sataus);
            quxiao = itemView.findViewById(R.id.qvxiao);
        }
    }

}
