package com.example.lenovo.taobaodemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.adapter.SortLeftAdapter;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.adapter.SortRightAdapter;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.api.Url;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean.SortRithtChildBean;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.presenter.Passer;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.view.IView;

import java.util.List;

public class SortFragment extends Fragment implements IView, SortLeftAdapter.setonclick {


    private View view;
    private RecyclerView sortleft;
    private RecyclerView sortright;
    private SortLeftAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_sort, null);
        initView();
        return view;
    }

    //初始化控件
    private void initView() {
        sortright = (RecyclerView) view.findViewById(R.id.sortright);
        sortright.setLayoutManager(new LinearLayoutManager(getActivity()));

        sortleft = (RecyclerView) view.findViewById(R.id.sortleft);
        sortleft.setLayoutManager(new LinearLayoutManager(getActivity()));
        new Passer(this).onLoad(Url.SORTGOODSURL, 0);
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    //获取分类数据
    @Override
    public void getSortBean(SortBean sortBean) {
        if (sortBean.getCode().equals("0")) {
            List<SortBean.DataBean> data = sortBean.getData();
            adapter = new SortLeftAdapter(data, getActivity(), this);
            sortleft.setAdapter(adapter);
            //sortBean.getData().get(0).getCid();
            new Passer(this).onLoad(Url.SORTGOODSCHILDURL + "?source=android&cid=1", 1);
        } else {
            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
        }

    }

    //点击事件
    @Override
    public void onclick(View view, int positon, String str) {
        //makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
        new Passer(this).onLoad(Url.SORTGOODSCHILDURL + "?source=android&cid=" + str, 1);
        adapter.setpostin(positon);
        //刷新适配器
        adapter.notifyDataSetChanged();

    }

    //获取子分类数据
    @Override
    public void getSortChildBean(SortRithtChildBean sortRithtChildBean) {
        if (sortRithtChildBean.getCode().equals("0")) {

            if (sortRithtChildBean.getData().size() > 0) {
                SortRightAdapter sortRightAdapter = new SortRightAdapter(sortRithtChildBean.getData(), getActivity());
                sortright.setAdapter(sortRightAdapter);
                //刷新Right适配器
                sortRightAdapter.notifyDataSetChanged();

            }

        } else {
            Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
        }

    }


}
