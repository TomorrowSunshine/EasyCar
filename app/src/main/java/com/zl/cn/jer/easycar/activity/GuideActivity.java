package com.zl.cn.jer.easycar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.adapter.MainPagerAdapter;

import java.util.ArrayList;
import java.util.List;
//引导页面
//周辉
public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    private List<View> list = new ArrayList<View>();
    private Button btnstart;
    private ViewPager vpstart;
    private TextView tiaoguo1;
    private TextView tiaoguo2;
    private TextView tiaoguo3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        //找到控件
        initialize();
//        //添加视图
        initView();
        //设置适配器
        //tiaoguo1.setOnClickListener(this);
        //tiaoguo2.setOnClickListener(this);
        //tiaoguo3.setOnClickListener(this);
        MainPagerAdapter main_vp_adapter = new MainPagerAdapter(GuideActivity.this,list);
        vpstart.setAdapter(main_vp_adapter);
        vpstart.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){
                    btnstart.setVisibility(View.VISIBLE);
                }else{
                    btnstart.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //按钮跳转
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void initView() {
        LayoutInflater mli = LayoutInflater.from(this);
        View view1 = mli.inflate(R.layout.main_vp1_layout,null);
        View view2 = mli.inflate(R.layout.main_vp2_layout,null);
        View view3 = mli.inflate(R.layout.main_vp3_layout,null);
        list.add(view1);
        list.add(view2);
        list.add(view3);
    }

    private void initialize() {
        btnstart = (Button) findViewById(R.id.btn_start);
        vpstart = (ViewPager) findViewById(R.id.vp_start);
        //tiaoguo1 = (TextView)findViewById(R.id.tiaoguo1);
        //tiaoguo2 = (TextView)findViewById(R.id.tiaoguo2);
        //tiaoguo3 = (TextView)findViewById(R.id.tiaoguo3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tiaoguo1:
                Intent intent = new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tiaoguo2:
                Intent intent2 = new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.tiaoguo3:
                Intent intent3 = new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(intent3);
                finish();
                break;
        }
    }
}
