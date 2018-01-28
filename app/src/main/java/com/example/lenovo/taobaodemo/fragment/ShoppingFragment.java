package com.example.lenovo.taobaodemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.api.Url;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.adapter.MExpandlbAdapter;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.bean.CheckShoppingCardBean;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.eventbus.All;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.presenter.MoneyAndCount;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.presenter.Passer;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.view.DingDan;
import com.example.lenovo.taobaodemo.fragment.shoppingcardmvp.api.view.IView;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment implements IView, DingDan {

    private View view;
    private ExpandableListView expandableListView;
    private CheckBox allchecked;
    private TextView contprice;
    private Button jiesuan;
    private List<List<CheckShoppingCardBean.DataBean.ListBean>> chuanru = new ArrayList<>();
    private MExpandlbAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_shopping, null);
        //注册EventBus
        EventBus.getDefault().register(this);
        initView();
        return view;
    }

    //初始化组件
    private void initView() {
        expandableListView = (ExpandableListView) view.findViewById(R.id.erji);
        allchecked = (CheckBox) view.findViewById(R.id.allchecked);
        contprice = (TextView) view.findViewById(R.id.contprice);
        jiesuan = (Button) view.findViewById(R.id.jiesuan);
        Passer passer = new Passer(this);
        passer.onLoad(Url.CHECKSHOPPINGCARD + "?source=android&uid=10822", 0);


        //结算生成订单
        jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double contprices = Double.parseDouble(contprice.getText().toString());
                if (contprices > 0) {
                    Passer passer1 = new Passer();
                    passer1.setDingDan(ShoppingFragment.this);
                    passer1.onLoad(Url.CHUANGJIANDINGDAN + "?source=android&uid=10822&price=" + contprices, 1);
                } else {
                    Toast.makeText(getActivity(), "请勾选商品", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


        //全选按钮监听
        allchecked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setAll(allchecked.isChecked());
            }
        });
    }


    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(All all) {
        Log.e("耿世聪", all.isall() + "");
        allchecked.setChecked(all.isall());
    }

    //订阅方法，当接收到事件的时候，会调用该方法
    //获取价格
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MoneyAndCount moneyAndCount) {
        //double类型保留2位小数点
        DecimalFormat df = new DecimalFormat("######0.00");
        contprice.setText(df.format(moneyAndCount.getPrice()));
        jiesuan.setText("结算(" + moneyAndCount.getCount() + ")");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //请求购物车成功结果
    @Override
    public void getShoppingCardSucceed(CheckShoppingCardBean checkShoppingCardBean) {
        Toast.makeText(getActivity(), checkShoppingCardBean.getMsg(), Toast.LENGTH_SHORT).show();
        List<CheckShoppingCardBean.DataBean> data = checkShoppingCardBean.getData();
        for (int i = 0; i < data.size(); i++) {
            chuanru.add(data.get(i).getList());
        }

        //初始化适配器
        adapter = new MExpandlbAdapter(data, chuanru, getActivity());
        expandableListView.setAdapter(adapter);
        expandableListView.setGroupIndicator(null);
        for (int i = 0; i < data.size(); i++) {
            expandableListView.expandGroup(i);
        }

    }

    //请求购物车失败结果
    @Override
    public void getShoppingCardERROR(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        Gson gson = new Gson();
        CheckShoppingCardBean checkShoppingCardBean = gson.fromJson(Url.json, CheckShoppingCardBean.class);
        List<CheckShoppingCardBean.DataBean> data = checkShoppingCardBean.getData();
        for (int i = 0; i < data.size(); i++) {
            chuanru.add(data.get(i).getList());
        }

        //初始化适配器
        adapter = new MExpandlbAdapter(data, chuanru, getActivity());
        expandableListView.setAdapter(adapter);
        expandableListView.setGroupIndicator(null);
        for (int i = 0; i < data.size(); i++) {
            expandableListView.expandGroup(i);
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //解除注册
        EventBus.getDefault().unregister(this);
    }


    //创建订单成功
    @Override
    public void creatDinganSucceed(String mgg) {
        Toast.makeText(getActivity(), mgg, Toast.LENGTH_SHORT).show();
    }

    //创建订单失败
    @Override
    public void creatDingDanERROR(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
