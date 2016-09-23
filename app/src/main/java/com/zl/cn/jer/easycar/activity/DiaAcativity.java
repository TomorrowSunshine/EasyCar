package com.zl.cn.jer.easycar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.zl.cn.jer.easycar.R;

public class DiaAcativity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_acativity);
        TextView tv= (TextView) findViewById(R.id.tv_dia);
        ImageView iv= (ImageView) findViewById(R.id.iv_navi_back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WebView web= (WebView) findViewById(R.id.web);
        Intent i=getIntent();
        String  subject= i.getStringExtra("subject");
        String linkUrl=i.getStringExtra("linkUrl");
        tv.setText(subject);
        web.loadUrl(linkUrl);

    }
}
