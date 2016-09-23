package com.zl.cn.jer.easycar.activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.bean.Loginbean;
import com.zl.cn.jer.easycar.utils.SecurityUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//注册页面
//周辉
public class ZhuCeActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText tel;
    private EditText yanzhengma;
    private EditText password;
    private EditText second_password;
    private EditText yaoqingma;
    private TextView returns;
    private Button zhuce_s;
    private TextView get_yanzhengma;
    private TextView tiaokuan;
    private CheckBox ck_3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        //找到控件
        find();
        returns.setOnClickListener(this);
        zhuce_s.setOnClickListener(this);
        get_yanzhengma.setOnClickListener(this);
        tiaokuan.setOnClickListener(this);
        ck_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!ck_3.isChecked()){
                    startActivity(new Intent(ZhuCeActivity.this,Zc_tiaokuan.class));
                }
            }
        });
    }

    //注册请求
    public void signUp(){
        RequestQueue requestqueue = Volley.newRequestQueue(ZhuCeActivity.this);
        requestqueue.start();
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.eakay.cn/order-api/api/user/newRegisterOne.htm", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                System.out.println(s);
                Gson gson=new Gson();
                Loginbean loginbean=gson.fromJson(s,Loginbean.class);
                if(loginbean.getMsg().getInfo().equals("手机号码已重复")){
                    Toast.makeText(ZhuCeActivity.this,"该手机号已经被注册，请勿重复注册", Toast.LENGTH_SHORT).show();

                    //startActivity(new Intent(ZhuCeActivity.this,LoginActivity.class));
                    finish();
                }else{
                    Toast.makeText(ZhuCeActivity.this,"注册成功", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ZhuCeActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<String,String>();
                params.put("phone",tel.getText().toString());//注册电话
                params.put("loginPassword", SecurityUtils.MD516(password.getText().toString()));//注册密码
                params.put("invitationCode",yaoqingma.getText().toString());
                params.put("key",yanzhengma.getText().toString());
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String hehe = dateFormat.format(now);
                params.put("timestamp", hehe);
                params.put("format", "json");
                params.put("app_key", "car");
                params.put("sign_method", "md5");
                params.put("signMethod", "md5");
                params.put("appKey", "car");
                params.put("ver", "2.4.1");
                params.put("client_type", "android");
                params.put("clientType", "android");
                Object[] objects = params.keySet().toArray();
                Arrays.sort(objects);
                StringBuilder sb = new StringBuilder();
                for (int i = 0 ; i <objects.length;i++){
                    sb.append(objects[i].toString()+params.get(objects[i].toString()));
                }
                String s = SecurityUtils.MD5(sb.toString()+"car");
                params.put("sign",s);
                return params;
            }
        };
        requestqueue.add(request);
    }
    //获取验证码请求
    public void signsUp(){
        RequestQueue requestqueue = Volley.newRequestQueue(ZhuCeActivity.this);
        requestqueue.start();
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.eakay.cn/user-api/api/user/sendVerifyCodeForRegister", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //System.out.println(s);
                Gson gson=new Gson();
                Loginbean loginbean=gson.fromJson(s, Loginbean.class);
                if(loginbean.getMsg().isSuccess()){    //判断手机号是否被注册
                    if(loginbean.getMsg().getCode()==1){
                        Toast.makeText(ZhuCeActivity.this,"获取验证码成功",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(ZhuCeActivity.this,"该号码已经被注册",Toast.LENGTH_SHORT).show();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ZhuCeActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<String,String>();
                params.put("phone",tel.getText().toString());//注册电话
//                params.put("loginPassword", SecurityUtils.MD516(password.getText().toString()));//注册密码
//                params.put("invitationCode",yaoqingma.getText().toString());
//                params.put("key",yanzhengma.getText().toString());
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String hehe = dateFormat.format(now);
                params.put("timestamp", hehe);
                params.put("format", "json");
                params.put("app_key", "car");
                params.put("sign_method", "md5");
                params.put("signMethod", "md5");
                params.put("appKey", "car");
                params.put("ver", "2.4.1");
                params.put("client_type", "android");
                params.put("clientType", "android");
                Object[] objects = params.keySet().toArray();
                Arrays.sort(objects);
                StringBuilder sb = new StringBuilder();
                for (int i = 0 ; i <objects.length;i++){
                    sb.append(objects[i].toString()+params.get(objects[i].toString()));
                }
                String s = SecurityUtils.MD5(sb.toString()+"car");
                params.put("sign",s);
                return params;
            }
        };
        requestqueue.add(request);
    }
    //找到控件
    public void find(){
        //手机号
        tel = (EditText) findViewById(R.id.tel);
        //验证码
        yanzhengma = (EditText) findViewById(R.id.yanzhengma);
        //密码
        password = (EditText) findViewById(R.id.password);
        //确认密码
        second_password = (EditText) findViewById(R.id.second_password);
        //邀请码
        yaoqingma = (EditText) findViewById(R.id.yaoqingma);
        //返回按钮
        returns = (TextView) findViewById(R.id.returns);
        //注册按钮
        zhuce_s = (Button) findViewById(R.id.zhuce_s);
        //按钮——获得验证码
        get_yanzhengma = (TextView) findViewById(R.id.get_yanzhengma);
        //注册条款
        tiaokuan = (TextView) findViewById(R.id.tiaokuan);
        //复选框
        ck_3 = (CheckBox)findViewById(R.id.ck_3);

        //修改输入时，字体的颜色为白色
        tel.setTextColor(Color.WHITE);
        yanzhengma.setTextColor(Color.WHITE);
        password.setTextColor(Color.WHITE);
        second_password.setTextColor(Color.WHITE);
        yaoqingma.setTextColor(Color.WHITE);
    }

    @Override
    public void onClick(View v) {
        //获得输入框信息
        String str_tel= tel.getText().toString();
        String str_yanzhengma= yanzhengma.getText().toString();
        String str_password= password.getText().toString();
        String str_second_password= second_password.getText().toString();
        String str_yaoqingma= yaoqingma.getText().toString();




        switch (v.getId()){
            case R.id.returns://返回按钮
                //startActivity(new Intent(ZhuCeActivity.this,LoginActivity.class));
                finish();
                break;
            case R.id.zhuce_s://注册按钮
                if(!str_tel.equals("")){
                    if(!str_yanzhengma.equals("")){
                        if(!str_password.equals("")&&!second_password.equals("")){
                            if(str_password.equals(str_second_password)){
                                 signUp();

                            }else {Toast.makeText(ZhuCeActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(ZhuCeActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(ZhuCeActivity.this,"请先输入验证码",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ZhuCeActivity.this,"请先输入手机号",Toast.LENGTH_SHORT).show();
                }


                break;
            case R.id.get_yanzhengma://验证码按钮
                if(!str_tel.equals("")){
                    signsUp();
                }else{
                    Toast.makeText(ZhuCeActivity.this,"请先输入手机号",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tiaokuan://注册条款
                //点击跳转H5
                startActivity(new Intent(ZhuCeActivity.this,Zc_tiaokuan.class));

                break;
        }
    }
}
