package com.example.lenovo.taobaodemo.fragment.myfragmentmvp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.fragment.Blank1Fragment;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.fragment.Blank2Fragment;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.fragment.Blank3Fragment;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.fragment.Blank4Fragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShowDingDanActivity extends AppCompatActivity {
    private List<String> titleslist = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();
    private Blank1Fragment fragment1;
    private Blank2Fragment fragment2;
    private Blank3Fragment fragment3;
    private Blank4Fragment fragment4;

    @Bind(R.id.titless)
    TabLayout titless;
    @Bind(R.id.view_page)
    ViewPager viewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ding_dan);
        ButterKnife.bind(this);
        //初始化数据
        initData();

        //设置标题栏标题展示的模式（每个标题在标题栏的摆放位置）
        titless.setTabMode(TabLayout.MODE_FIXED);
        titless.setTabTextColors(Color.BLACK, Color.RED);
        //为标题设置对应显示的ViewPager界面
        titless.setupWithViewPager(viewPage);
        //实例化ViewPager适配器（传入Frament管理对象）
        MAdapter mAdapter = new MAdapter(getSupportFragmentManager());
        //ViewPager添加适配器
        viewPage.setAdapter(mAdapter);
        //定位到未付款界面
        Intent intent = getIntent();
        if (intent != null) {
            String weifukuan = intent.getStringExtra("weifukuan");
            if (weifukuan != null) {
                if (weifukuan.equals("nopay")) {
                    viewPage.setCurrentItem(1);
                }
            }
        }
    }


    private void initData() {
        titleslist.add("全部");
        titleslist.add("待支付");
        titleslist.add("已支付");
        titleslist.add("已取消");
        fragment1 = new Blank1Fragment();
        fragment2 = new Blank2Fragment();
        fragment3 = new Blank3Fragment();
        fragment4 = new Blank4Fragment();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
    }

    // 定义ViewPager的适配器的类
    class MAdapter extends FragmentPagerAdapter {
        public MAdapter(FragmentManager fm) {
            super(fm);
        }

        //此方法用于返回，容器中装有的Fragment
        @Override
        public Fragment getItem(int i) {
            return fragmentList.get(i);
        }

        //此方法根据Fragment的数量去创建Viewpager的界面
        @Override
        public int getCount() {
            return fragmentList.size();
        }

        //此方法用于返回装有标题容器中的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titleslist.get(position);
        }
    }

}
