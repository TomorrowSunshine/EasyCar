package com.zl.cn.jer.easycar.activity.myyikai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.utils.IntentUtils;

public class MyHomeShiXiaoActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textView4;
    private LinearLayout l1;
    private ImageView ivpopwin;
    private ImageView imgbackshixiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_shi_xiao);
        initialize();
    }

    private void initialize() {

        l1 = (LinearLayout) findViewById(R.id.l1);
        ivpopwin = (ImageView) findViewById(R.id.iv_popwin);
        imgbackshixiao = (ImageView) findViewById(R.id.img_back_shixiao);
        imgbackshixiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_back_shixiao:
                IntentUtils.intentClass(MyHomeShiXiaoActivity.this,MyHomeYouHuiJuanActivity.class);
                break;
        }
    }
}
