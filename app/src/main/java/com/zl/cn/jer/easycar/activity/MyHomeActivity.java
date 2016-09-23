package com.zl.cn.jer.easycar.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.myyikai.MyHomeCheZhuActivity;
import com.zl.cn.jer.easycar.activity.myyikai.MyHomeChuZhiActivity;
import com.zl.cn.jer.easycar.activity.myyikai.MyHomeGeRenActivity;
import com.zl.cn.jer.easycar.activity.myyikai.MyHomeMessageActivity;
import com.zl.cn.jer.easycar.activity.myyikai.MyHomeMoreActivity;
import com.zl.cn.jer.easycar.activity.myyikai.MyHomeShiMingActivity;
import com.zl.cn.jer.easycar.activity.myyikai.MyHomeWeiZhangActivity;
import com.zl.cn.jer.easycar.activity.myyikai.MyHomeYaJinActivity;
import com.zl.cn.jer.easycar.activity.myyikai.MyHomeYaoQingActivity;
import com.zl.cn.jer.easycar.activity.myyikai.MyHomeYiKaiShopActivity;
import com.zl.cn.jer.easycar.activity.myyikai.MyHomeYouHuiJuanActivity;
import com.zl.cn.jer.easycar.bean.UserShimingRz;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.IntentUtils;
import com.zl.cn.jer.easycar.utils.MyGetPost;

import java.util.Map;

//个人中心
//赵雷
public class MyHomeActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView userhomeback;
    private ImageView userhomemore;
    private ImageView userhomegeren;
    private Button userhomeyajin;
    private LinearLayout userhomecuzhi;
    private LinearLayout userhomeyouhuijuaan;
    private TextView userhomeshimingrz;
    private LinearLayout userhomeshiming;
    private TextView userhomechezhurz;
    private LinearLayout userhomechezhu;
    private LinearLayout userhomeyikai;
    private LinearLayout userhomeyaoqing;
    private LinearLayout userhomeweizhang;
    private LinearLayout userhomexiaoxi;
    private TextView userphone;
    private UserShimingRz.MapBean map1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);
        //获取控件
        initialize();
        if (userhomechezhurz.getText().toString().equals("未认证")){
            userhomechezhurz.setText(App.carStatus);
        }else if(userhomechezhurz.getText().toString().equals("待认证")){
            userhomechezhurz.setText(App.carStatus);
            userhomeshimingrz.setTextColor(Color.BLUE);
        }else if(userhomechezhurz.getText().toString().equals("未通过")){
            userhomechezhurz.setText(App.carStatus);
            userhomeshimingrz.setTextColor(Color.BLUE);
        }
        initNetRZzhuangtai();
        String num = App.Phone;
        String str=num.substring(0,num.length()-(num.substring(3)).length())+"****"+num.substring(7);
        userphone.setText(str);
    }


    private void initialize() {
        userhomeback = (ImageView) findViewById(R.id.user_home_back);
        userhomemore = (ImageView) findViewById(R.id.user_home_more);
        userhomegeren = (ImageView) findViewById(R.id.user_home_geren);
        userhomeyajin = (Button) findViewById(R.id.user_home_yajin);
        userhomecuzhi = (LinearLayout) findViewById(R.id.user_home_cuzhi);
        userhomeyouhuijuaan = (LinearLayout) findViewById(R.id.user_home_youhuijuaan);
        userhomeshimingrz = (TextView) findViewById(R.id.user_home_shiming_rz);
        userhomeshiming = (LinearLayout) findViewById(R.id.user_home_shiming);
        userhomechezhurz = (TextView) findViewById(R.id.user_home_chezhu_rz);
        userhomechezhu = (LinearLayout) findViewById(R.id.user_home_chezhu);
        userhomeyikai = (LinearLayout) findViewById(R.id.user_home_yikai);
        userhomeyaoqing = (LinearLayout) findViewById(R.id.user_home_yaoqing);
        userhomeweizhang = (LinearLayout) findViewById(R.id.user_home_weizhang);
        userhomexiaoxi = (LinearLayout) findViewById(R.id.user_home_xiaoxi);
        userphone = (TextView) findViewById(R.id.user_phone);
        userhomeback.setOnClickListener(this);
        userhomemore.setOnClickListener(this);
        userhomegeren.setOnClickListener(this);
        userhomeyajin.setOnClickListener(this);
        userhomecuzhi.setOnClickListener(this);
        userhomeyouhuijuaan.setOnClickListener(this);
        userhomeshimingrz.setOnClickListener(this);
        userhomechezhurz.setOnClickListener(this);
        userhomeshiming.setOnClickListener(this);
        userhomechezhu.setOnClickListener(this);
        userhomeyikai.setOnClickListener(this);
        userhomeyaoqing.setOnClickListener(this);
        userhomeweizhang.setOnClickListener(this);
        userhomexiaoxi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.user_home_back://返回
                IntentUtils.intentClass(MyHomeActivity.this,MainActivity.class);
                finish();
            break;
            case R.id.user_home_more://更多
                IntentUtils.intentClass(MyHomeActivity.this,MyHomeMoreActivity.class);
                finish();
                break;
            case R.id.user_home_geren://个人
                IntentUtils.intentClass(MyHomeActivity.this, MyHomeGeRenActivity.class);
                break;
            case R.id.user_home_yajin://押金
                IntentUtils.intentClass(MyHomeActivity.this, MyHomeYaJinActivity.class);
                break;
            case R.id.user_home_cuzhi://储值
                IntentUtils.intentClass(MyHomeActivity.this, MyHomeChuZhiActivity.class);
                break;
            case R.id.user_home_youhuijuaan://优惠券
                IntentUtils.intentClass(MyHomeActivity.this, MyHomeYouHuiJuanActivity.class);
                break;
            case R.id.user_home_shiming://实名认证
                if (userhomeshimingrz.getText().toString().equals("未认证")||userhomeshimingrz.getText().toString().equals("注册中")){
                Intent inent = new Intent(MyHomeActivity.this, MyHomeShiMingActivity.class);
                inent.putExtra("code",0);
                startActivity(inent);
            }else if (userhomeshimingrz.getText().toString().equals("审核中")){
                Intent inent = new Intent(MyHomeActivity.this, MyHomeShiMingActivity.class);
                inent.putExtra("code",1);
                startActivity(inent);
            }else{
                Intent inent = new Intent(MyHomeActivity.this, MyHomeShiMingActivity.class);
                inent.putExtra("code",2);
                startActivity(inent);
            }

                break;
            case R.id.user_home_chezhu://车主认证
                if (userhomechezhurz.getText().toString().equals("未认证")||userhomeshimingrz.getText().toString().equals("注册中")){
                    Intent inent = new Intent(MyHomeActivity.this, MyHomeCheZhuActivity.class);
                    inent.putExtra("code",0);
                    startActivity(inent);
                }else if (userhomechezhurz.getText().toString().equals("待认证")){
                    Intent inent = new Intent(MyHomeActivity.this, MyHomeCheZhuActivity.class);
                    inent.putExtra("code",1);
                    startActivity(inent);
                }else if (map1.getApplyType().equals("未通过")){

                    Intent inent = new Intent(MyHomeActivity.this, MyHomeCheZhuActivity.class);
                    inent.putExtra("code",2);
                    startActivity(inent);
                }
                break;
            case R.id.user_home_yikai://易开商城
                IntentUtils.intentClass(MyHomeActivity.this, MyHomeYiKaiShopActivity.class);
                break;
            case R.id.user_home_yaoqing://邀请有奖
                IntentUtils.intentClass(MyHomeActivity.this, MyHomeYaoQingActivity.class);
                break;
            case R.id.user_home_weizhang://违章查询
                IntentUtils.intentClass(MyHomeActivity.this, MyHomeWeiZhangActivity.class);
                break;
            case R.id.user_home_xiaoxi://消息中心
                IntentUtils.intentClass(MyHomeActivity.this, MyHomeMessageActivity.class);
                break;
        }
    }
    //实名认证状态获取
    private void initNetRZzhuangtai() {
        String urls = "https://api.eakay.cn/order-api/api/user/findUserInfo.htm";
        Map<String, String> map = MyGetPost.getMap("customerId", String.valueOf(App.Cus), "phone", App.Phone,"user_token", App.USERTOKEN);
        HttpNet.postObjectMinongApi(urls, map, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }

            @Override
            public void onResponse(String s) {
                Log.d("TAG", s);
                Gson g = new Gson();
                map1 = g.fromJson(s, UserShimingRz.class).getMap();
                if (map1.getApplyType().toString().equals("未认证")){
                    userhomeshimingrz.setText(App.state);
                    userhomeshimingrz.setTextColor(Color.BLACK);
                }else if (map1.getApplyType().toString().equals("审核中")){
                    userhomeshimingrz.setText(App.state);
                    userhomeshimingrz.setTextColor(Color.BLUE);
                }else  if(map1.getApplyType().toString().equals("未通过")){
                    userhomeshimingrz.setText(App.state);
                    userhomeshimingrz.setTextColor(Color.BLUE);
                    userhomechezhurz.setText("未通过");
                    userhomechezhurz.setTextColor(Color.BLUE);
                }
                userhomeshimingrz.setText(map1.getApplyType().toString());

            }
        });
    }

}
