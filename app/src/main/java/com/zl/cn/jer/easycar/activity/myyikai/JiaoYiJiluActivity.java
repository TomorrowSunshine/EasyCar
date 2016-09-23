package com.zl.cn.jer.easycar.activity.myyikai;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.utils.IntentUtils;
import com.zl.cn.jer.easycar.view.xlistview.XListView;

import java.text.SimpleDateFormat;
import java.util.Date;
/*
* @交易记录
* @赵雷
* */

public class JiaoYiJiluActivity extends AppCompatActivity implements View.OnClickListener,XListView.IXListViewListener {

    private ImageView userhomejiaoyijiluback;
    private ImageView imageView2;
    private RelativeLayout transationrelative;
    private RelativeLayout activityjiaoyijilu;
    private XListView jiluxlv;
    Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiao_yi_jilu);
        initialize();
    }

    private void initialize() {
        userhomejiaoyijiluback = (ImageView) findViewById(R.id.user_home_jiaoyijilu_back);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        activityjiaoyijilu = (RelativeLayout) findViewById(R.id.activity_jiao_yi_jilu);
        jiluxlv = (XListView) findViewById(R.id.jilu_xlv);
        // 支持刷新
        jiluxlv.setPullRefreshEnable(true);
        // 能够加载
        jiluxlv.setPullLoadEnable(true);
        // 监听
        jiluxlv.setXListViewListener(this);
        userhomejiaoyijiluback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_home_jiaoyijilu_back:
                IntentUtils.intentClass(this, getIntent().getAction().getClass());
                break;
        }
    }

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                load();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                load();
            }
        }, 2000);

    }
    public void load(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm:ss");
        jiluxlv.stopLoadMore();
        jiluxlv.stopRefresh();
        jiluxlv.setRefreshTime("刷新时间:"+dateFormat);
    }
}
