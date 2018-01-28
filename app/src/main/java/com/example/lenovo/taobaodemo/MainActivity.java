package com.example.lenovo.taobaodemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.taobaodemo.login.Api.Url;
import com.example.lenovo.taobaodemo.login.loginbean.User;
import com.example.lenovo.taobaodemo.login.presenter.Passer;
import com.example.lenovo.taobaodemo.login.view.Iview;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Iview {

    private static final String APP_ID = "1105602574";
    private Tencent tencent;
    private BaseUiLi baseUiLi;

    @Bind(R.id.qqlogin)
    ImageView qqlogin;
    private TextView sign_in;
    private ImageView back;
    private EditText name;
    private EditText pwd;
    private ImageView login;
    private SharedPreferences user;
    private CheckBox zdlogin;
    private CheckBox jzpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //传入APP_ID和全局上下文
        tencent = Tencent.createInstance(APP_ID, MainActivity.this.getApplicationContext());
        ButterKnife.bind(this);
        initView();
        //获取SharPrefences
        user = getSharedPreferences("USER", MODE_PRIVATE);


        //去除自动登入
        SharedPreferences.Editor edit = user.edit();
        edit.putBoolean("isno", false);
        edit.commit();

        //第三方登入QQ
        qqlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baseUiLi = new BaseUiLi();
                tencent.login(MainActivity.this, "all", baseUiLi);

            }
        });

    }


    //获取焦点自动登入
    @Override
    protected void onResume() {
        super.onResume();

        boolean isno = user.getBoolean("isno", false);
        if (isno) {
            Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }

        boolean jzmm = user.getBoolean("jzmm", false);
        if (jzmm) {
            jzpwd.setChecked(jzmm);
            name.setText(user.getString("name", "耿世聪"));
            pwd.setText(user.getString("pwd", "耿世聪"));
        } else {
            jzpwd.setChecked(jzmm);
        }

    }

    private void initView() {

        //初始化输入框
        name = (EditText) findViewById(R.id.name);
        pwd = (EditText) findViewById(R.id.pwd);
        zdlogin = (CheckBox) findViewById(R.id.zdlogin);
        jzpwd = (CheckBox) findViewById(R.id.jzpwd);


        //记住密码
        jzpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(pwd.getText().toString())) {
                    Toast.makeText(MainActivity.this, "输入项不能为空", Toast.LENGTH_SHORT).show();
                    jzpwd.setChecked(false);
                    return;
                }

                if (jzpwd.isChecked()) {
                    SharedPreferences.Editor edit = user.edit();
                    edit.putBoolean("jzmm", true);
                    edit.putString("name", name.getText().toString());
                    edit.putString("pwd", pwd.getText().toString());
                    edit.commit();
                } else {
                    SharedPreferences.Editor edit = user.edit();
                    edit.putBoolean("jzmm", false);
                    edit.commit();
                }

            }
        });

        //自动登入
        zdlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = user.edit();
                edit.putBoolean("isno", true);
                edit.commit();
            }
        });


        //登入
        login = (ImageView) findViewById(R.id.login);

        //新用户注册
        sign_in = (TextView) this.findViewById(R.id.sign_in);

        //新用户注册
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signinintent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(signinintent);
            }
        });

        //关闭程序
        back = (ImageView) this.findViewById(R.id.back_app);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //用户登入
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(pwd.getText().toString())) {
                    Toast.makeText(MainActivity.this, "输入项不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = new User(name.getText().toString(), pwd.getText().toString());
                Passer passer = new Passer(MainActivity.this);
                passer.login(user);

            }
        });


    }


    //登入失败
    @Override
    public void onFailed(String codestate) {

        Toast.makeText(MainActivity.this, codestate, Toast.LENGTH_SHORT).show();
    }

    //登入成功
    @Override
    public void onSucceed(String codestate) {
        Toast.makeText(MainActivity.this, codestate + ",欢迎使用☺", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }


    //QQ登入用到的内部类，响应登入状态
    private class BaseUiLi implements IUiListener {

        private UserInfo userInfo;

        //成功
        @Override
        public void onComplete(Object o) {
            Toast.makeText(MainActivity.this, "授权成功", Toast.LENGTH_SHORT).show();


            JSONObject obj = (JSONObject) o;
            try {
                String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                tencent.setOpenId(openID);
                tencent.setAccessToken(accessToken, expires);
                QQToken qqToken = tencent.getQQToken();
                userInfo = new UserInfo(getApplicationContext(), qqToken);
                userInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        Toast.makeText(MainActivity.this, "欢迎使用☺", Toast.LENGTH_SHORT).show();
                        //获取QQ用户登入信息，并解析通过EventBus发送到个人中心
                        String qqmessage = response.toString();
                        Url.qquser = qqmessage;
                        Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(UiError uiError) {
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        //错误
        @Override
        public void onError(UiError uiError) {
            Toast.makeText(MainActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
        }

        //取消
        @Override
        public void onCancel() {
            Toast.makeText(MainActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 在调用Login的Activity或者Fragment中重写onActivityResult方法
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, baseUiLi);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
