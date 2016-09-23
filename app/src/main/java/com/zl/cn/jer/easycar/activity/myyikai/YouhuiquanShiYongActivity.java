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
import com.zl.cn.jer.easycar.utils.HtmlWebURL;
import com.zl.cn.jer.easycar.utils.IntentUtils;

public class YouhuiquanShiYongActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView userhomeyouhuiquanshiyongback;
    private WebView yajinwb;
    private RelativeLayout activityyouhuiquanshiyong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youhuiquan_shi_yong);
        initialize();
        WebSettings webSettings = yajinwb.getSettings();

        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        yajinwb.loadUrl(HtmlWebURL.WEB_COUPON_RULES);
    }

    private void initialize() {

        userhomeyouhuiquanshiyongback = (ImageView) findViewById(R.id.user_home_youhuiquan_shiyong_back);
        yajinwb = (WebView) findViewById(R.id.yajin_wb);
        activityyouhuiquanshiyong = (RelativeLayout) findViewById(R.id.activity_youhuiquan_shi_yong);
        userhomeyouhuiquanshiyongback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_home_youhuiquan_shiyong_back:
                IntentUtils.intentClass(this, MyHomeYouHuiJuanActivity.class);
                break;
        }

    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && yajinwb.canGoBack()) {
            yajinwb.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        finish();//结束退出程序
        return false;
    }
}
