package com.zl.cn.jer.easycar.activity.myyikai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.MyHomeActivity;
import com.zl.cn.jer.easycar.utils.HtmlWebURL;
import com.zl.cn.jer.easycar.utils.IntentUtils;

/*
*
* @易开商城
* @赵雷
*
* */
public class MyHomeYiKaiShopActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView userhomeyikaiback;
    private WebView yikaiwb;
    private RelativeLayout activitymyhomeyikaishop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_yi_kai_shop);
        initialize();
        WebSettings webSettings = yikaiwb.getSettings();

        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        yikaiwb.loadUrl(HtmlWebURL.WEB_SHOP);
        //设置Web视图
        yikaiwb.setWebViewClient(new webViewClient());
    }

    private void initialize() {

        userhomeyikaiback = (ImageView) findViewById(R.id.user_home_yikai_back);
        yikaiwb = (WebView) findViewById(R.id.yikai_wb);
        activitymyhomeyikaishop = (RelativeLayout) findViewById(R.id.activity_my_home_yi_kai_shop);
        userhomeyikaiback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_home_yikai_back:
                IntentUtils.intentClass(this, MyHomeActivity.class);
                break;
        }

    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && yikaiwb.canGoBack()) {
            yikaiwb.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        finish();//结束退出程序
        return false;
    }

    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
