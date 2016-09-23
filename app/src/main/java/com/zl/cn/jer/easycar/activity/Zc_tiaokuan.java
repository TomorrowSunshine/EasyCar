package com.zl.cn.jer.easycar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.zl.cn.jer.easycar.R;

public class Zc_tiaokuan extends AppCompatActivity {

    private WebView webView;
    private TextView tongyi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zc_tiaokuan);

        init();
    }
    private void init(){
        webView = (WebView) findViewById(R.id.webView);
        tongyi = (TextView) findViewById(R.id.tongyi);
        tongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //WebView加载web资源
        webView.loadUrl("http://h5.eakay.cn/eakayApp/views/agreement/useTerms.html");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}
