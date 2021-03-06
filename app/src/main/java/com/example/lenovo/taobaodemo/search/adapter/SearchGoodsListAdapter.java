package com.example.lenovo.taobaodemo.search.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.search.bean.Bean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/14.
 */

public class SearchGoodsListAdapter extends XRecyclerView.Adapter<SearchGoodsListAdapter.MVH> {

    private List<Bean.DataBean> list;
    private Context context;
    private setOnClick setonclick;

    public interface setOnClick {
        void onClick(Bean.DataBean databean, int postion);

        void onLongClick(Bean.DataBean databean, int postion);
    }

    public void setOnClickCener(setOnClick setonclick) {
        this.setonclick = setonclick;
    }

    public SearchGoodsListAdapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.searchgoodslistitem, null);
        MVH mvh = new MVH(view);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MVH holder, final int position) {
        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage(list.get(position).getImages().split("!")[0], holder.goodstupian);
        holder.titles.setText(list.get(position).getTitle());
        holder.subhead.setText(list.get(position).getSubhead());
        holder.newprice.setText(list.get(position).getBargainPrice() + "");
        holder.oldprice.setText(list.get(position).getPrice() + "");

        //条目点击
        holder.itemll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (setonclick != null) {
                    setonclick.onClick(list.get(position), position);
                }

            }
        });
        //条目长按
        holder.itemll.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                if (setonclick != null) {
                    setonclick.onLongClick(list.get(position), position);
                }
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MVH extends XRecyclerView.ViewHolder {

        private ImageView goodstupian;
        private TextView titles;
        private TextView subhead;
        private TextView newprice;
        private TextView oldprice;
        private LinearLayout itemll;

        public MVH(View itemView) {
            super(itemView);
            goodstupian = (ImageView) itemView.findViewById(R.id.goodstupian);
            titles = (TextView) itemView.findViewById(R.id.settitles);
            subhead = (TextView) itemView.findViewById(R.id.subhead);
            newprice = (TextView) itemView.findViewById(R.id.newprice);
            oldprice = (TextView) itemView.findViewById(R.id.oldprice);
            itemll = (LinearLayout) itemView.findViewById(R.id.itemll);
        }
    }


}
