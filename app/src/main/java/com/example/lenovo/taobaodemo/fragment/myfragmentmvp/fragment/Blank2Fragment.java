package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.alipay.AuthResult;
import com.example.lenovo.taobaodemo.alipay.PayResult;
import com.example.lenovo.taobaodemo.alipay.util.OrderInfoUtil2_0;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.adapter.B2Adapter;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.api.Url;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean.DingDanBean;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean.UpdateDingDan;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.presenter.Passer;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.view.IView;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.view.UpdateIView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


public class Blank2Fragment extends Fragment implements IView, B2Adapter.setOnclicke, UpdateIView {


    /**
     * 支付宝支付业务：入参app_id
     */
    public static final String APPID = "2018";

    /**
     * 支付宝账户登录授权业务：入参pid值
     */
    public static final String PID = "";
    /**
     * 支付宝账户登录授权业务：入参target_id值
     */
    public static final String TARGET_ID = "";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /**
     * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM" +
            "/KCxg/OIj6er2GEig0DOkHqBSzEPHGigMbTXP1k2nrxEHeE59xOOuy" +
            "ovQH/A1LgbuVKyOac3uAN4GXIBEoozRVzDhu5dobeNm48BPcpYSAfvN3K" +
            "/5GLacvJeENqsiBx8KufM/9inlHaDRQV7WhX1Oe2ckat1EkdHwxxQgc" +
            "36NhAgMBAAECgYEAwn3sWpq6cUR65LD8h9MIjopTImTlpFjgz72bhsHD" +
            "ZK6A+eJDXcddrwh7DI34t/0IBqu+QEoOc/f0fIEXS9hMwTvFY59XG7M8" +
            "M6SdeaAbemrGmZ1IdD6YDmpbQFHn2ishaYF0YDZIkBS3WLDFrtk/efaar" +
            "BCpGAVXeEiVQE4LewECQQD5W1rpkq+dHDRzzdtdi1bJ479wun5CfmVDV" +
            "b2CJH7Iz0t8zyp/iEVV2QELnxZMphmoSzKaLXutTTj2OImpzCtRAkEA1" +
            "VMxG6nQq9NkU51H1+I3mlUXRZ0XeFA1GFJ7xWpNRAVhEWlDz2zy9v/g" +
            "X+RFpNC3f5uznycas70Xp78ew43TEQJAZFFqi9mlqTF1sLk67bFnIyX" +
            "rGPEOZrXvC13tNfR0xVkQZ4/46wHp0xXQo9pG4GNaoyhNnVV7EkelCPn" +
            "J+HPZYQJAQh6T9QgQZoGR8hyovPAf3dUL7oa/VIo/urcuJ8VIB5JHQNdI" +
            "rk0NjaNHj1E4iNosVgATj3vWWel9IIArb99QkQJAKvfm78lwnImtg5IM6" +
            "04hdn/Wu1XF8tpxsKLWcnfchMr0bM9rCmKmhAY+wdmqSyPZRiNb1QaaaD" +
            "TqJxLy6AnQ+Q==";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getActivity(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }

                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(getActivity(),
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(getActivity(),
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };


    @Bind(R.id.rlv)
    RecyclerView rlv;
    private B2Adapter b2Adapter;
    private List<DingDanBean.DataBean> filterdingdan;
    private Passer passer;
    private AlertDialog show;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_blank2, container, false);
        ButterKnife.bind(this, inflate);
        return inflate;
    }


    //可见状态下请求数据
    @Override
    public void onResume() {
        super.onResume();
        passer = new Passer(this);
        passer.onLoad(Url.CKDN + "uid=10822&page=2", 0);
    }


    //请求订单成功
    @Override
    public void onReqSuceese(DingDanBean dingDanBean) {
        Toast.makeText(getActivity(), dingDanBean.getMsg(), Toast.LENGTH_SHORT).show();
        //过滤未支付订单
        List<DingDanBean.DataBean> data = dingDanBean.getData();
        filterdingdan = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getStatus() == 0) {
                filterdingdan.add(data.get(i));
            }
        }

        b2Adapter = new B2Adapter(filterdingdan, getActivity(), this);
        rlv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv.setAdapter(b2Adapter);
    }

    //请求订单失败
    @Override
    public void onReqErrro(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    //条目点击修改订单状态//http://120.27.23.105/product/updateOrder?source=android&uid=71&status=1&orderId=232
    // http://120.27.23.105/product/updateOrder?source=android&
    @Override
    public void onClick(final int postion) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View payview = View.inflate(getActivity(), R.layout.pay, null);
        ImageView nopay = (ImageView) payview.findViewById(R.id.nopay);

        //取消支付
        nopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show.dismiss();
            }
        });


        ImageView alipay = (ImageView) payview.findViewById(R.id.alipay);
        //支付宝支付
        alipay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                show.dismiss();

                if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
                    new AlertDialog.Builder(getActivity()).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialoginterface, int i) {
                                    //
                                }
                            }).show();
                    return;
                }

                /**
                 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
                 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
                 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
                 *
                 * orderInfo的获取必须来自服务端；
                 */
                //服务端拼接订单，并且对订单加密
                boolean rsa2 = (RSA2_PRIVATE.length() > 0);
                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

                String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
                String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
                final String orderInfo = orderParam + "&" + sign;

                //客户端直接拿到加密后的orderInfo
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        //两行代码去支付
                        PayTask alipay = new PayTask(getActivity());
                        Map<String, String> result = alipay.payV2(orderInfo, true);

                        Message msg = new Message();
                        msg.what = SDK_PAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                Thread payThread = new Thread(payRunnable);
                payThread.start();

            }
        });
        Button putongzhifu = (Button) payview.findViewById(R.id.putongzhifu);
        //普通支付
        putongzhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //去后台改变状态
                Passer passer = new Passer();
                passer.upDateDingDan(Blank2Fragment.this);
                passer.onLoad(Url.UPDATEDINGDAN + "uid=10822&status=1&orderId=" + filterdingdan.get(postion).getOrderid(), 1);
                Log.e("SSA", postion + "");
                show.dismiss();
            }
        });

        builder.setView(payview);
        show = builder.show();

    }

    //条目长按
    @Override
    public void onLongClick(int postion) {
        Passer passer = new Passer();
        passer.upDateDingDan(this);
        passer.onLoad(Url.UPDATEDINGDAN + "uid=10822&status=2&orderId=" + filterdingdan.get(postion).getOrderid(), 1);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    //订单修改成功
    @Override
    public void onUpdateSuceese(UpdateDingDan updateDingDan) {
        Toast.makeText(getActivity(), updateDingDan.getMsg() + "请在相应界面查看结果", Toast.LENGTH_SHORT).show();
        //再次请求数据
        passer = new Passer(this);
        passer.onLoad(Url.CKDN + "uid=10822&page=2", 0);
    }

    //订单修改失败
    @Override
    public void onUpdateErrro(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
