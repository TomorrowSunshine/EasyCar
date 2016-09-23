package com.zl.cn.jer.easycar.activity.myyikai;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.App;
import com.zl.cn.jer.easycar.activity.LoginActivity;
import com.zl.cn.jer.easycar.activity.MyHomeActivity;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.DataCleanManager;
import com.zl.cn.jer.easycar.utils.IntentUtils;
import com.zl.cn.jer.easycar.utils.MyGetPost;
import com.zl.cn.jer.easycar.view.CustomDialog;

import java.util.Map;
/*
* @更多
* @赵雷
*
* */

public class MyHomeMoreActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView userhomemoreback;
    private TextView etuserhomemoreuppass;
    private TextView etuserhomemorefapiao;
    private LinearLayout userhomefuwu;
    private LinearLayout userhomeabout;
    private LinearLayout userhomeclen;
    private RelativeLayout activitymyhomemore;
    private Button bttuichu;
    private LinearLayout userhomeupdatepassword;
    private LinearLayout dainziitem;
    private TextView tvmorecatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_more);
        try {
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws Exception {

        userhomemoreback = (ImageView) findViewById(R.id.user_home_more_back);
        etuserhomemoreuppass = (TextView) findViewById(R.id.et_user_home_more_uppass);
        etuserhomemorefapiao = (TextView) findViewById(R.id.et_user_home_more_fapiao);
        userhomefuwu = (LinearLayout) findViewById(R.id.user_home_fuwu);
        userhomeabout = (LinearLayout) findViewById(R.id.user_home_about);
        userhomeclen = (LinearLayout) findViewById(R.id.user_home_clen);
        activitymyhomemore = (RelativeLayout) findViewById(R.id.activity_my_home_more);
        bttuichu = (Button) findViewById(R.id.button);
        userhomeupdatepassword = (LinearLayout) findViewById(R.id.user_home_update_password);
        dainziitem = (LinearLayout) findViewById(R.id.dianzi_item);
        tvmorecatch = (TextView) findViewById(R.id.tv_more_catch);
        tvmorecatch.setText(DataCleanManager.getTotalCacheSize(MyHomeMoreActivity.this).toString());
        userhomemoreback.setOnClickListener(this);
        bttuichu.setOnClickListener(this);
        userhomeupdatepassword.setOnClickListener(this);
        userhomeabout.setOnClickListener(this);
        dainziitem.setOnClickListener(this);
        userhomefuwu.setOnClickListener(this);
        userhomeclen.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_home_more_back:
                IntentUtils.intentClass(this, MyHomeActivity.class);
                break;
            case R.id.button:
                IntentUtils.intentClass(this, LoginActivity.class);
                initHttpData();
                finish();
                break;
            case R.id.user_home_update_password:
                IntentUtils.intentClass(this, MyHomeUpdatePasswordActivity.class);
                break;
            case R.id.user_home_about:
                IntentUtils.intentClass(this, MyHomeMyYiKaiActivity.class);
                break;
            case R.id.user_home_fuwu:
                IntentUtils.intentClass(this, MyHomeFuwuActivity.class);
                break;
            case R.id.dianzi_item:
                IntentUtils.intentClass(this, MyHomeDianziActivity.class);
                break;
            case R.id.user_home_clen:
                CustomDialog.Builder builder = new CustomDialog.Builder(this);
                builder.setMessage("是否清除缓存");
                builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("确定",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                try {
                                    DataCleanManager.clearAllCache(MyHomeMoreActivity.this);
                                    tvmorecatch.setText(DataCleanManager.getTotalCacheSize(MyHomeMoreActivity.this).toString());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                dialog.dismiss();

                            }
                        });

                builder.create().show();

                break;
        }
    }
    //退出请求
    private void initHttpData() {
        String urls = "https://api.eakay.cn/user-api/api/user/logout";
        Map<String, String> map = MyGetPost.getMap("customerId", String.valueOf(App.Cus), "phone", App.Phone,"user_token", App.USERTOKEN);
        HttpNet.postObjectMinongApi(urls, map, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MyHomeMoreActivity.this, "请求错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s) {
                Toast.makeText(MyHomeMoreActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                Log.d("TAG", s);

            }
        });
    }
}
