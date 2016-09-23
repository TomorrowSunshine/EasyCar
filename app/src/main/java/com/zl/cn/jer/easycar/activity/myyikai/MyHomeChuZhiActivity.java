package com.zl.cn.jer.easycar.activity.myyikai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.MyHomeActivity;
import com.zl.cn.jer.easycar.utils.HtmlWebURL;
import com.zl.cn.jer.easycar.utils.IntentUtils;

/*
* @储值
* @赵雷
*
* */

public class MyHomeChuZhiActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView userhomechuzhiback;
    private EditText receditmoney;
    private WebView chuzhiwb;
    private Button userhomechuzhizhifu;
    private ImageView chuzhiivjiaoyijilu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_chu_zhi);
        initialize();
        WebSettings webSettings = chuzhiwb.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        chuzhiwb.loadUrl(HtmlWebURL.WEB_SAVINGS);


        }

    private void initialize() {
        userhomechuzhiback = (ImageView) findViewById(R.id.user_home_chuzhi_back);
        receditmoney = (EditText) findViewById(R.id.rec_edit_money);
        chuzhiwb = (WebView) findViewById(R.id.chuzhi_wb);
        userhomechuzhizhifu = (Button) findViewById(R.id.user_home_chuzhi_zhifu);
        chuzhiivjiaoyijilu = (ImageView) findViewById(R.id.chuzhi_iv_jiaoyijilu);
        userhomechuzhiback.setOnClickListener(this);
        chuzhiivjiaoyijilu.setOnClickListener(this);
        userhomechuzhizhifu.setOnClickListener(this);
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && chuzhiwb.canGoBack()) {
            chuzhiwb.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        finish();//结束退出程序
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_home_chuzhi_back:
                IntentUtils.intentClass(this, MyHomeActivity.class);
                break;
            case R.id.chuzhi_iv_jiaoyijilu:
                IntentUtils.intentClass(this, JiaoYiJiluActivity.class);
                break;
            case R.id.user_home_chuzhi_zhifu:
                Toast.makeText(this, "功能未实现", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
