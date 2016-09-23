package com.zl.cn.jer.easycar.activity.myyikai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.MyHomeActivity;
import com.zl.cn.jer.easycar.utils.HtmlWebURL;
import com.zl.cn.jer.easycar.utils.IntentUtils;

/*
* @赵雷
* @违章查询
*
*
* */


public class MyHomeWeiZhangActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView userhomeweizhangback;
    private WebView weizhangwb;
    private RelativeLayout activitymyhomeweizhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_wei_zhang);
        initialize();
        WebSettings webSettings = weizhangwb.getSettings();

        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        weizhangwb.loadUrl(HtmlWebURL.WEB_SEARCH_VIOLATION);
    }

    private void initialize() {

        userhomeweizhangback = (ImageView) findViewById(R.id.user_home_weizhang_back);
        weizhangwb = (WebView) findViewById(R.id.weizhang_wb);
        activitymyhomeweizhang = (RelativeLayout) findViewById(R.id.activity_my_home_wei_zhang);
        userhomeweizhangback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_home_weizhang_back:
                IntentUtils.intentClass(this, MyHomeActivity.class);
                break;
        }

    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && weizhangwb.canGoBack()) {
            weizhangwb.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        finish();//结束退出程序
        return false;
    }
}
