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

public class MyHomeDianziActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView userhomedianziback;
    private WebView dainziwb;
    private RelativeLayout activitymyhomedianzi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_dianzi);
        initialize();
        WebSettings webSettings = dainziwb.getSettings();

        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        dainziwb.loadUrl(HtmlWebURL.WEB_INVOICE);
    }

    private void initialize() {

        userhomedianziback = (ImageView) findViewById(R.id.user_home_dianzi_back);
        dainziwb = (WebView) findViewById(R.id.dainzi_wb);
        activitymyhomedianzi = (RelativeLayout) findViewById(R.id.activity_my_home_dianzi);
        userhomedianziback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_home_dianzi_back:
                IntentUtils.intentClass(this, MyHomeMoreActivity.class);
                break;
        }
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && dainziwb.canGoBack()) {
            dainziwb.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        finish();//结束退出程序
        return false;
    }

}
