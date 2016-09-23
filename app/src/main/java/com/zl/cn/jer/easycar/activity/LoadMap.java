package com.zl.cn.jer.easycar.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.zl.cn.jer.easycar.R;

public class LoadMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_map);
        int type = getIntent().getIntExtra("type", 1);
        WebView wb= (WebView) findViewById(R.id.wb);

        if (type==1){

            wb.loadUrl("http://map.baidu.com/");
        }else {
            wb.loadUrl("http://ditu.amap.com/");
        }

    }


}
