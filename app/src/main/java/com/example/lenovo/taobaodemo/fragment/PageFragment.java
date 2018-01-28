package com.example.lenovo.taobaodemo.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.adapter.Head3Adapter;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.adapter.ItemDivder;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.adapter.Jianjv;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.adapter.PageAdapter;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.api.PageUrl;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.bean.HeadbannerImg;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.presenter.Passer;
import com.example.lenovo.taobaodemo.fragment.pagefragmentmvp.view.IView;
import com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.goodsxiangqing.GoodsActivity;
import com.example.lenovo.taobaodemo.search.SearchActivity;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PageFragment extends Fragment implements IView {

    @Bind(R.id.searchgoods)
    EditText searchgoods;
    private static final int REQUEST_CODE = 0;
    private XBanner hxbn;
    private ArrayList<String> listxbn;
    private ArrayList<String> listtitle;
    private XRecyclerView xRecyclerView;
    private View headbanner;
    private View head3;
    private ImageView h2;
    private RecyclerView gridview;
    private ArrayList<String> listmiaoshu = new ArrayList<>();
    private ArrayList<Integer> listimg = new ArrayList<>();
    private Head3Adapter adapter1;
    private ViewFlipper pmd;
    private PageAdapter pageAdapter;
    private ImageView richscan;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, null);
        richscan = (ImageView) view.findViewById(R.id.richscan);

        listxbn = new ArrayList<>();
        listtitle = new ArrayList<>();

        listmiaoshu.add("京东超市");
        listmiaoshu.add("全球购");
        listmiaoshu.add("京东服饰");
        listmiaoshu.add("京东生鲜");
        listmiaoshu.add("京东到家");
        listmiaoshu.add("充值缴费");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");

        listimg.add(R.drawable.g1);
        listimg.add(R.drawable.g2);
        listimg.add(R.drawable.g3);
        listimg.add(R.drawable.g4);
        listimg.add(R.drawable.g1);
        listimg.add(R.drawable.g2);
        listimg.add(R.drawable.g3);
        listimg.add(R.drawable.g4);
        listimg.add(R.drawable.g6);
        listimg.add(R.drawable.shop);

        listmiaoshu.add("京东超市");
        listmiaoshu.add("全球购");
        listmiaoshu.add("京东服饰");
        listmiaoshu.add("京东生鲜");
        listmiaoshu.add("京东到家");
        listmiaoshu.add("充值缴费");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");
        listmiaoshu.add("京东超市");

        listimg.add(R.drawable.g1);
        listimg.add(R.drawable.g2);
        listimg.add(R.drawable.g3);
        listimg.add(R.drawable.g4);
        listimg.add(R.drawable.g1);
        listimg.add(R.drawable.g2);
        listimg.add(R.drawable.g3);
        listimg.add(R.drawable.g4);
        listimg.add(R.drawable.g6);
        listimg.add(R.drawable.shop);


        //打开相机扫一扫二维码
        richscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转打开相机，调取扫描二维码功能
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                //跳转
                startActivityForResult(intent, REQUEST_CODE);

            }
        });


        //加载头布局xbanner
        headbanner = View.inflate(getActivity(), R.layout.headbanner, null);

        head3 = View.inflate(getActivity(), R.layout.head3, null);
        gridview = (RecyclerView) head3.findViewById(R.id.gridviews);
        pmd = (ViewFlipper) head3.findViewById(R.id.pmd);
        pmd.startFlipping();
        //京东服务
        gridview.addItemDecoration(new Jianjv(10));
        gridview.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, true));
        adapter1 = new Head3Adapter(getActivity(), listmiaoshu, listimg);
        gridview.setAdapter(adapter1);

        //初始化控件
        xRecyclerView = (XRecyclerView) view.findViewById(R.id.xrlv);
        xRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        xRecyclerView.addItemDecoration(new ItemDivder(getActivity(), 2, Color.WHITE));
        xRecyclerView.setRefreshProgressStyle(ProgressStyle.LineScalePulseOutRapid);
        xRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);

        //上下拉加载更多
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                Toast.makeText(getActivity(), "刷新", Toast.LENGTH_SHORT).show();
                //xRecyclerView.refreshComplete();

            }

            //上拉加载更多
            @Override
            public void onLoadMore() {
                Toast.makeText(getActivity(), "更多", Toast.LENGTH_SHORT).show();
                // xRecyclerView.loadMoreComplete();
            }
        });


        //设置XBanner数据
        hxbn = headbanner.findViewById(R.id.headxbanner);
        hxbn.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getActivity()).load(listxbn.get(position)).into((ImageView) view);
            }
        });

        //添加头布局
        xRecyclerView.addHeaderView(headbanner);
        xRecyclerView.addHeaderView(head3);
        new Passer(this).onLoad(PageUrl.HEAD_XBANNER);

        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        hxbn.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        hxbn.stopAutoPlay();
    }

    //请求返回的数据
    @Override
    public void getData(HeadbannerImg headbannerImg) {
        List<HeadbannerImg.DataBean> data = headbannerImg.getData();
        //得到推荐数据
        HeadbannerImg.TuijianBean tuijian = headbannerImg.getTuijian();
        final List<HeadbannerImg.TuijianBean.ListBean> list = tuijian.getList();
        for (int i = 0; i < data.size(); i++) {
            listxbn.add(data.get(i).getIcon());
            listtitle.add(data.get(i).getTitle());
        }
        hxbn.setData(listxbn, listtitle);
        pageAdapter = new PageAdapter(getActivity(), list);
        xRecyclerView.setAdapter(pageAdapter);


        //条目点击事件
        pageAdapter.setOnckilciener(new PageAdapter.setOnClick() {
            @Override
            public void onClick(int postion) {
                Intent intent = new Intent(getActivity(), GoodsActivity.class);
                intent.putExtra("ID", "2");
                intent.putExtra("tuijianpid", list.get(postion).getPid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.searchgoods)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        getActivity().startActivity(intent);
    }

    //接受扫描结果
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {

            if (data != null) {
                //得到结果集
                Bundle bundle = data.getExtras();
                //判断bundle是否为空
                if (bundle != null) {
                    //判断结果的结果码是否和二维码工具类的成功结果码一致获取结果
                    if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {

                        String string = bundle.getString(CodeUtils.RESULT_STRING);
                        Toast.makeText(getActivity(), "扫描结果" + string, Toast.LENGTH_SHORT).show();

                    } else {

                        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {

                            Toast.makeText(getActivity(), "二维码解析失败", Toast.LENGTH_SHORT).show();

                        }

                    }

                }

            }
        }
    }


}
