package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.adapter.B4Adapter;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.api.Url;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean.DingDanBean;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.presenter.Passer;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.view.IView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Blank4Fragment extends Fragment implements IView {

    @Bind(R.id.qvrlv)
    RecyclerView qvrlv;
    private B4Adapter b4Adapter;
    private List<DingDanBean.DataBean> data;
    private List<DingDanBean.DataBean> filter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_blank4, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    //可见状态下请求数据
    @Override
    public void onResume() {
        super.onResume();
        Passer passer = new Passer(this);
        passer.onLoad(Url.CKDN + "uid=10822&page=2", 0);
    }

    //请求订单成功
    @Override
    public void onReqSuceese(DingDanBean dingDanBean) {
        Toast.makeText(getActivity(), dingDanBean.getMsg(), Toast.LENGTH_SHORT).show();
        //已取消订单过滤
        data = dingDanBean.getData();
        filter = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getStatus() == 2) {
                filter.add(data.get(i));
            }
            Log.e("data.get(i).getStatus()", data.get(i).getStatus() + "");
        }

        Log.e("onReqSuceesefilter", filter.size() + "");


        b4Adapter = new B4Adapter(filter, getActivity());
        if (qvrlv != null) {
            qvrlv.setLayoutManager(new LinearLayoutManager(getActivity()));
            qvrlv.setAdapter(b4Adapter);
        }

    }

    //请求订单失败
    @Override
    public void onReqErrro(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
