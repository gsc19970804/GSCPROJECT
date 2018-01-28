package com.example.lenovo.taobaodemo.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.AddressActivity;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.SelfActivity;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.ShowDingDanActivity;
import com.example.lenovo.taobaodemo.login.Api.Url;
import com.example.lenovo.taobaodemo.login.loginbean.QQUser;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyFragment extends Fragment {

    @Bind(R.id.chakandingdan)
    LinearLayout chakandingdan;
    @Bind(R.id.address)
    LinearLayout address;
    @Bind(R.id.kk)
    ImageView kk;
    @Bind(R.id.head)
    SimpleDraweeView head;
    @Bind(R.id.phone)
    TextView phone;
    @Bind(R.id.My_weifukuan)
    ImageView MyWeifukuan;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Fresco.initialize(getActivity());
        View view = inflater.inflate(R.layout.fragment_my, null);
        ButterKnife.bind(this, view);


        //点击未付款定位到未付款界面
        MyWeifukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), ShowDingDanActivity.class).putExtra("weifukuan", "nopay"));
            }
        });

        //点击查看订单
        chakandingdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), ShowDingDanActivity.class));
            }
        });

        //点击查看收货地址
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), AddressActivity.class));
            }
        });

        //获取QQ用户信息
        if (!Url.qquser.equals("QQUSER")) {
            Log.e("Gsc", "规定的");
            Gson gson = new Gson();
            QQUser qqUser = gson.fromJson(Url.qquser, QQUser.class);
            head.setImageURI(Uri.parse(qqUser.getFigureurl_qq_2()));
            phone.setText(qqUser.getNickname());
        }


        //点击跳转到个人中心
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startActivity(new Intent(getActivity(), SelfActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
