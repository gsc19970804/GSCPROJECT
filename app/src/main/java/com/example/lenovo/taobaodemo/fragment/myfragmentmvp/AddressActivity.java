package com.example.lenovo.taobaodemo.fragment.myfragmentmvp;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lenovo.taobaodemo.R;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.adapter.AddressList;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.AddAddress;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.bean.Address;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.presenter.Passer;
import com.example.lenovo.taobaodemo.fragment.myfragmentmvp.address.view.AddressIView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddressActivity extends AppCompatActivity implements AddressIView {

    @Bind(R.id.addresslist)
    RecyclerView addresslist;
    @Bind(R.id.addadress)
    Button addadress;
    private Passer passer;
    private AddressList addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        //请求数据
        passer = new Passer(this);
        passer.onLoad();


        //添加收货地址
        addadress.setOnClickListener(new View.OnClickListener() {

            private AlertDialog show;

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AddressActivity.this);
                View alertview = View.inflate(AddressActivity.this, R.layout.newaddress, null);
                final EditText address_address = alertview.findViewById(R.id.address_address);
                final EditText address_name = alertview.findViewById(R.id.address_name);
                final EditText address_phone = alertview.findViewById(R.id.address_phone);
                Button qvxiao = alertview.findViewById(R.id.qvxiao);
                Button save = alertview.findViewById(R.id.save);
                //点击保存地址做判空，传入服务端
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(address_address.getText().toString()) || TextUtils.isEmpty(address_phone.getText().toString()) || TextUtils.isEmpty(address_name.getText().toString())) {
                            Toast.makeText(AddressActivity.this, "输入项不能为空", Toast.LENGTH_SHORT).show();
                        } else {

                            //添加信息
                            if (passer != null) {
                                passer.onAddLoad(address_address.getText().toString(), address_phone.getText().toString(), address_name.getText().toString());
                            }
                            show.dismiss();
                        }
                    }
                });

                //点击取消
                qvxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        show.dismiss();
                    }
                });

                builder.setView(alertview);
                //显示弹出框
                show = builder.show();

            }
        });
    }


    //请求地址数据
    @Override
    public void onAddressSuceess(Address address) {
        if (address.getCode().equals("0")) {
            Toast.makeText(this, address.getMsg(), Toast.LENGTH_SHORT).show();
            List<Address.DataBean> data = address.getData();
            addressList = new AddressList(data, this);
            addresslist.setLayoutManager(new LinearLayoutManager(this));
            addresslist.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            addresslist.setAdapter(addressList);

            //设置默认地址
            addressList.setMoren(new AddressList.setMorenDizhi() {
                @Override
                public void onMoren(int addrid, int status) {
                    Log.e("sss", status + "");
                    if (passer != null) {
                        passer.onsetmorenAddr(addrid, status);
                        addressList.notifyDataSetChanged();
                    }

                }
            });

        } else {
            Toast.makeText(this, address.getMsg(), Toast.LENGTH_SHORT).show();

        }

    }

    //地址添加成功
    @Override
    public void onAddSuceess(AddAddress addAddress) {

        if (addAddress.getCode().equals("0")) {
            Toast.makeText(this, addAddress.getMsg(), Toast.LENGTH_SHORT).show();
            //添加后再次请求数据
            if (passer != null) {
                passer.onLoad();
            }
        } else {
            Toast.makeText(this, addAddress.getMsg(), Toast.LENGTH_SHORT).show();
        }

    }


    //默认地址结果
    @Override
    public void onAddmorenSuceess(AddAddress addAddress) {
        Toast.makeText(this, addAddress.getMsg(), Toast.LENGTH_SHORT).show();
    }
}
