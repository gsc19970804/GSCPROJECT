package com.example.lenovo.taobaodemo.fragment.myfragmentmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.taobaodemo.ActivityManager;
import com.example.lenovo.taobaodemo.MainActivity;
import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.login.Api.Url;
import com.example.lenovo.taobaodemo.login.loginbean.QQUser;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelfActivity extends AppCompatActivity {

    @Bind(R.id.grtx)
    SimpleDraweeView grtx;
    @Bind(R.id.grnc)
    TextView grnc;
    @Bind(R.id.sex)
    TextView sex;
    @Bind(R.id.qqaddr)
    TextView qqaddr;
    @Bind(R.id.clear)
    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);
        ButterKnife.bind(this);

        if (!Url.qquser.equals("QQUSER")) {
            Gson gson = new Gson();
            QQUser qqUser = gson.fromJson(Url.qquser, QQUser.class);
            grtx.setImageURI(qqUser.getFigureurl_qq_2());
            grnc.setText(qqUser.getNickname());
            sex.setText(qqUser.getGender());
            qqaddr.setText(qqUser.getProvince() + "-" + qqUser.getCity());
        }

    }

    //注销当前用户
    @OnClick(R.id.clear)
    public void onViewClicked() {
        startActivity(new Intent(SelfActivity.this, MainActivity.class));
        finish();
        ActivityManager.finishs();

    }
}
