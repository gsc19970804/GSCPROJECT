package com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.bean.HeadbannerImg;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/13.
 */

public class PageAdapter extends XRecyclerView.Adapter<PageAdapter.MVH> {


    private Context context;
    private List<HeadbannerImg.TuijianBean.ListBean> list;
    private setOnClick setOnClick;

    public PageAdapter(Context context, List<HeadbannerImg.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }


    //点击接口回调
    public interface setOnClick {
        void onClick(int postion);
    }

    //传入接口实现类引用
    public void setOnckilciener(setOnClick setOnClick) {
        this.setOnClick = setOnClick;
    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.tuijian, null);
        MVH mvh = new MVH(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MVH holder, final int position) {


        //条目点击事件
        holder.tujians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setOnClick != null) {
                    setOnClick.onClick(position);
                }
            }
        });

        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage(list.get(position).getImages().split("!")[0], holder.img);
        holder.titles.setText(list.get(position).getTitle());
        holder.price.setText("￥" + list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MVH extends XRecyclerView.ViewHolder {
        LinearLayout tujians;
        ImageView img;
        TextView titles;
        TextView price;
        Button button;

        public MVH(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.tuijianimg);
            titles = itemView.findViewById(R.id.tujiantitles);
            price = itemView.findViewById(R.id.tujianprice);
            button = itemView.findViewById(R.id.kanxiangsi);
            tujians = itemView.findViewById(R.id.tuijians);
        }
    }

}
