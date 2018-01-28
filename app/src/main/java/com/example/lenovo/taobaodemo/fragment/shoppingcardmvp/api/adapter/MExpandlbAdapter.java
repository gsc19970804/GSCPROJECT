package com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.bean.CheckShoppingCardBean;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.eventbus.All;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.presenter.MoneyAndCount;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2017/12/21.
 */

public class MExpandlbAdapter extends BaseExpandableListAdapter {

    private List<CheckShoppingCardBean.DataBean> grouplist;
    private List<List<CheckShoppingCardBean.DataBean.ListBean>> childlist;
    private Context context;
    private double money;
    private int counts;

    public MExpandlbAdapter(List<CheckShoppingCardBean.DataBean> grouplist, List<List<CheckShoppingCardBean.DataBean.ListBean>> childlist, Context context) {
        this.grouplist = grouplist;
        this.childlist = childlist;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return grouplist.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childlist.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return grouplist.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childlist.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        View view1;
        final V1 v1;
        if (view == null) {
            v1 = new V1();
            view1 = View.inflate(context, R.layout.cardshopsitem, null);
            v1.groupchecked = view1.findViewById(R.id.grouchecked);
            view1.setTag(v1);
        } else {
            view1 = view;
            v1 = (V1) view1.getTag();
        }

        v1.groupchecked.setChecked(grouplist.get(i).isGroupChecked());
        v1.groupchecked.setText(grouplist.get(i).getSellerName());


        //父列表点击选中，然后选中所有子列表
        v1.groupchecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (v1.groupchecked.isChecked()) {
                    grouplist.get(i).setGroupChecked(v1.groupchecked.isChecked());
                    isGroupAllChild(v1.groupchecked.isChecked(), i);
                    //父列表点击全选中，然后全选
                    boolean all = isAll();
                    Log.e("All", all + "");
                    if (all) {
                        Toast.makeText(context, "全选", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new All(v1.groupchecked.isChecked()));
                    }

                    //计算价格总和，和数量总和
                    allPriceAndCount();

                } else {
                    grouplist.get(i).setGroupChecked(v1.groupchecked.isChecked());
                    isGroupAllChild(v1.groupchecked.isChecked(), i);
                    EventBus.getDefault().post(new All(v1.groupchecked.isChecked()));

                    //计算价格总和，和数量总和
                    allPriceAndCount();
                }

            }
        });

        return view1;

    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        View view1;
        final V2 v2;
        if (view == null) {
            v2 = new V2();
            view1 = View.inflate(context, R.layout.goodsitem, null);
            v2.childchecked = view1.findViewById(R.id.childchecked);
            v2.goodsimg = view1.findViewById(R.id.goodsimg);
            v2.titles = view1.findViewById(R.id.titles);
            v2.price = view1.findViewById(R.id.price);
            v2.numjiajia = view1.findViewById(R.id.numjiajia);
            v2.numjianjian = view1.findViewById(R.id.numjianjian);
            v2.num = view1.findViewById(R.id.num);
            view1.setTag(v2);
        } else {
            view1 = view;
            v2 = (V2) view1.getTag();
        }

        ImageLoader instance = ImageLoader.getInstance();
        v2.childchecked.setChecked(childlist.get(i).get(i1).ischildckecked());
        instance.displayImage(childlist.get(i).get(i1).getImages().split("\\|")[0], v2.goodsimg);
        v2.titles.setText(childlist.get(i).get(i1).getTitle());
        v2.price.setText(childlist.get(i).get(i1).getPrice() + "");
        v2.num.setText(childlist.get(i).get(i1).getNum() + "");


        //数量加加减减监听
        v2.numjiajia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = childlist.get(i).get(i1).getNum();
                if (num < 9) {
                    childlist.get(i).get(i1).setNum(num + 1);
                    notifyDataSetChanged();
                    //计算价格总和，和数量总和
                    allPriceAndCount();
                } else {
                    Toast.makeText(context, "数量以到达商品库存上线", Toast.LENGTH_SHORT).show();
                }

            }
        });

        v2.numjianjian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = childlist.get(i).get(i1).getNum();
                if (num > 1) {
                    childlist.get(i).get(i1).setNum(num - 1);
                    notifyDataSetChanged();
                    //计算价格总和，和数量总和
                    allPriceAndCount();
                } else {
                    Toast.makeText(context, "商品数量已经是1了", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //子列表点击选中，判断子列表是否都选中，然后选中父列表
        v2.childchecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (v2.childchecked.isChecked()) {
                    childlist.get(i).get(i1).setIschildckecked(v2.childchecked.isChecked());
                    boolean childChecked = isChildChecked(i);
                    if (childChecked) {
                        grouplist.get(i).setGroupChecked(v2.childchecked.isChecked());
                    }
                    //刷新适配器
                    notifyDataSetChanged();
                    boolean all = isAll();
                    Log.e("All", all + "");
                    if (all) {
                        Toast.makeText(context, "全选", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new All(v2.childchecked.isChecked()));
                    }
                    //计算价格总和，和数量总和
                    allPriceAndCount();

                } else {
                    childlist.get(i).get(i1).setIschildckecked(v2.childchecked.isChecked());
                    grouplist.get(i).setGroupChecked(v2.childchecked.isChecked());
                    //刷新适配器
                    notifyDataSetChanged();
                    EventBus.getDefault().post(new All(v2.childchecked.isChecked()));

                    //计算价格总和，和数量总和
                    allPriceAndCount();
                }
            }
        });
        return view1;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


    //优化类

    class V1 {
        CheckBox groupchecked;
    }

    class V2 {
        CheckBox childchecked;
        ImageView goodsimg;
        TextView titles;
        TextView price;
        TextView numjianjian;
        TextView num;
        TextView numjiajia;
    }


    /***
     * 子列表点击选中，判断子列表是否都选中，然后选中父列表
     */
    public boolean isChildChecked(int positon) {
        List<CheckShoppingCardBean.DataBean.ListBean> listBeen = childlist.get(positon);
        for (int i = 0; i < listBeen.size(); i++) {
            if (!listBeen.get(i).ischildckecked()) {
                return false;
            }
        }

        return true;
    }

    /***
     * 父列表点击选中，然后选中所有子列表
     */
    public void isGroupAllChild(boolean b, int positon) {
        List<CheckShoppingCardBean.DataBean.ListBean> listBeen = childlist.get(positon);
        if (b) {
            for (int i = 0; i < listBeen.size(); i++) {
                listBeen.get(i).setIschildckecked(b);
            }
        } else {
            for (int i = 0; i < listBeen.size(); i++) {
                listBeen.get(i).setIschildckecked(b);
            }
        }

        notifyDataSetChanged();

    }

    /***
     * 父列表点击全选中，然后全选
     */

    public boolean isAll() {
        for (int i = 0; i < grouplist.size(); i++) {
            if (!grouplist.get(i).isGroupChecked()) {
                return false;
            }
        }
        return true;
    }

    /***
     * 点击全选父列表，子列表全选
     */

    public void setAll(boolean b) {
        for (int i = 0; i < grouplist.size(); i++) {
            grouplist.get(i).setGroupChecked(b);
        }
        for (int i = 0; i < childlist.size(); i++) {
            List<CheckShoppingCardBean.DataBean.ListBean> listBeen = childlist.get(i);
            for (int j = 0; j < listBeen.size(); j++) {
                listBeen.get(j).setIschildckecked(b);
            }
        }
        //刷新适配器
        notifyDataSetChanged();

        //计算价格总和，和数量总和
        allPriceAndCount();

    }

    /***
     * 计算价格总和，和数量总和
     */
    public void allPriceAndCount() {
        money = 0;
        counts = 0;
        for (int i = 0; i < childlist.size(); i++) {
            List<CheckShoppingCardBean.DataBean.ListBean> listBeen = childlist.get(i);
            for (int j = 0; j < listBeen.size(); j++) {
                if (listBeen.get(j).ischildckecked()) {
                    money += listBeen.get(j).getPrice() * listBeen.get(j).getNum();
                    counts += listBeen.get(j).getNum();
                }
            }

        }
        //发送价格数量进行显示
        EventBus.getDefault().post(new MoneyAndCount(money, counts));
    }

}
