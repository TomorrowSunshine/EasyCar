package com.zl.cn.jer.easycar.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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


public class Password_forget extends AppCompatActivity implements OnClickListener {

    private String tels;
    private String forgets_yanzhengmas;
    private String newpasswords;
    private String newsecond_passwords;
    private TextView forget_returns;
    private TextView forget_get_yanzhengma;
    private EditText forget_tel;
    private EditText forgets_yanzhengma;
    private EditText newpassword;
    private EditText newsecond_password;
    private Button forget_queren;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        //找到控件
        find();
        forget_returns.setOnClickListener(this);
        forget_get_yanzhengma.setOnClickListener(this);
        forget_queren.setOnClickListener(this);
    }
    //获取验证码请求
    public void signsUp(){
        RequestQueue requestqueue = Volley.newRequestQueue(Password_forget.this);
        requestqueue.start();
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.eakay.cn/user-api/api/user/sendVerifyCodeForChangePassword", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                 System.out.println(s);
                Gson gson=new Gson();
                Loginbean loginbean=gson.fromJson(s, Loginbean.class);
               if(loginbean.getMsg().isSuccess()){
                   Toast.makeText(Password_forget.this, "验证码发送成功！", Toast.LENGTH_SHORT).show();
               }else{
                   Toast.makeText(Password_forget.this, "该手机号未进行注册", Toast.LENGTH_SHORT).show();
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Password_forget.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<String,String>();
                params.put("phone",tels);//注册电话

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
    //找回密码请求
    public void signUp(){
        RequestQueue requestqueue = Volley.newRequestQueue(Password_forget.this);
        requestqueue.start();
        StringRequest request = new StringRequest(Request.Method.POST, "https://api.eakay.cn/user-api/api/user/forgetPassword", new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                System.out.println(s);
                Gson gson=new Gson();
               Loginbean loginbean= gson.fromJson(s,Loginbean.class);
                if(loginbean.getMsg().getCode()==1){
                    Toast.makeText(Password_forget.this, "该号码未进行注册", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Password_forget.this, "修改成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Password_forget.this,LoginActivity.class));
                    finish();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Password_forget.this, volleyError.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<String,String>();
                params.put("phone",tels);//注册电话
                params.put("password", SecurityUtils.MD516(newpasswords));//密码
                params.put("verifyCode",forgets_yanzhengmas);//验证码
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
    public void find(){

        //返回按钮
        forget_returns = (TextView)findViewById(R.id.forget_returns );
        //手机号
        forget_tel = (EditText) findViewById(R.id.forget_tel);
        //验证码
        forgets_yanzhengma = (EditText) findViewById(R.id.forgets_yanzhengma);
        //新密码
        newpassword = (EditText) findViewById(R.id.newpassword);
        //确认新密码
        newsecond_password = (EditText) findViewById(R.id.newsecond_password);
        //确定按钮
        forget_queren = (Button)findViewById(R.id.forget_queren);

        //获得验证码按钮
        forget_get_yanzhengma = (TextView)findViewById(R.id.forget_get_yanzhengma);
        //设置输入时，为白色
        forget_tel.setTextColor(Color.WHITE);
        forgets_yanzhengma.setTextColor(Color.WHITE);
        newpassword.setTextColor(Color.WHITE);
        newsecond_password.setTextColor(Color.WHITE);


   }

    @Override
    public void onClick(View v) {
        //获得输入框的值
        //获得手机号
        tels = forget_tel.getText().toString().trim();
        //获得输入框验证码
        forgets_yanzhengmas = forgets_yanzhengma.getText().toString().trim();
        //获得密码
        newpasswords = newpassword.getText().toString().trim();
        //获得确认密码
        newsecond_passwords = newsecond_password.getText().toString().trim();
        switch (v.getId()){

            case R.id.forget_returns://返回按钮跳转
//                startActivity(new Intent(Password_forget.this,LoginActivity.class));
                finish();
                break;
            case R.id.forget_queren://确定按钮
                if(!tels.equals("")){
                    if(!forgets_yanzhengmas.equals("")){
                        if(!newpasswords.equals("")&&!newsecond_passwords.equals("")){
                            if(newpasswords.equals(newsecond_passwords)){
                                signUp();//回调找回密码方法
                            }else{
                                Toast.makeText(Password_forget.this,"输入密码不一致",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(Password_forget.this,"请输入密码",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(Password_forget.this,"请输入验证码",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Password_forget.this,"请输入手机号",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.forget_get_yanzhengma://获得验证码按钮
                if(!tels.equals("")){
                    signsUp();//获得验证码请求
                }else{
                    Toast.makeText(Password_forget.this,"请先输入手机号",Toast.LENGTH_LONG).show();
                }

                break;

        }
    }

}
