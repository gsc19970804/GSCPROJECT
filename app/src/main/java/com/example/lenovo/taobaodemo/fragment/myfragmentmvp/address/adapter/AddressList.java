package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.Address;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/1/8.
 */

public class AddressList extends RecyclerView.Adapter<AddressList.MyViewHodler2> {


    private List<Address.DataBean> data;
    private Context context;
    private setMorenDizhi morenDizhi;

    public AddressList(List<Address.DataBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public interface setMorenDizhi {
        //设置默认地址
        void onMoren(int addrid, int status);
    }


    public void setMoren(setMorenDizhi setMorenDizhi) {
        this.morenDizhi = setMorenDizhi;
    }

    @Override
    public AddressList.MyViewHodler2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.addressitem, null, false);

        MyViewHodler2 myViewHodler2 = new MyViewHodler2(view);

        return myViewHodler2;
    }

    @Override
    public void onBindViewHolder(final MyViewHodler2 holder, final int position) {

        holder.takegoods_address.setText(data.get(position).getAddr());
        holder.takegoods_name.setText(data.get(position).getName());
        holder.takegoods_phone.setText(data.get(position).getMobile() + "");
        if (data.get(position).getStatus() == 0) {
            holder.takegoods_checked.setChecked(false);
        } else {
            holder.takegoods_checked.setChecked(true);
        }

        holder.takegoods_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选中唯一性
                isno();

                data.get(position).setStatus(holder.takegoods_checked.isChecked() ? 1 : 0);
                if (morenDizhi != null) {
                    morenDizhi.onMoren(data.get(position).getAddrid(), data.get(position).getStatus());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHodler2 extends RecyclerView.ViewHolder {

        private final TextView takegoods_name;
        private final TextView takegoods_phone;
        private final TextView takegoods_address;
        private final CheckBox takegoods_checked;

        public MyViewHodler2(View itemView) {
            super(itemView);
            takegoods_name = itemView.findViewById(R.id.takegoods_name);
            takegoods_phone = itemView.findViewById(R.id.takegoods_phone);
            takegoods_address = itemView.findViewById(R.id.takegoods_address);
            takegoods_checked = itemView.findViewById(R.id.takegoods_checked);
        }
    }


    //选中唯一性
    public void isno() {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getStatus() == 1) {
                data.get(i).setStatus(0);
            }
        }
    }

}
