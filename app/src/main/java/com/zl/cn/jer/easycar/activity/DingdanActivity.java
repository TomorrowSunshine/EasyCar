package com.zl.cn.jer.easycar.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.fragment.DingDan_chongdian_fragment;
import com.zl.cn.jer.easycar.fragment.DingDan_tingche_fragment;
import com.zl.cn.jer.easycar.fragment.DingDan_zuche_fragment;

import java.util.ArrayList;
import java.util.List;

//订单
//赵雷

public class DingdanActivity extends FragmentActivity {

    private ViewPager vp_id;
    private LinearLayout l1;
    private RadioGroup rg_id;
    private RadioButton rb_1;
    private RadioButton rb_2;
    private RadioButton rb_3;
    private List<Fragment> list;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);
        //查找视图
        initView();
        //RadioButton的循环点击切换ViewPager
        initEvent();
        //VeiwPager切换
        initViewPagerListener();

    }

    private void initViewPagerListener() {
        //监听ViewPager
        vp_id.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        rb_1.setChecked(true);
                        rb_1.setTextColor(Color.parseColor("#6DD1FF"));
                        rb_2.setTextColor(Color.parseColor("#9499AC"));
                        rb_3.setTextColor(Color.parseColor("#9499AC"));

                        break;
                    case 1:
                        rb_2.setChecked(true);
                        rb_2.setTextColor(Color.parseColor("#6DD1FF"));
                        rb_1.setTextColor(Color.parseColor("#9499AC"));
                        rb_3.setTextColor(Color.parseColor("#9499AC"));
                        break;
                    case 2:
                        rb_3.setChecked(true);
                        rb_3.setTextColor(Color.parseColor("#6DD1FF"));
                        rb_2.setTextColor(Color.parseColor("#9499AC"));
                        rb_1.setTextColor(Color.parseColor("#9499AC"));
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initEvent() {
        int gg = rg_id.getChildCount();
        for (int i = 0; i < gg; i++) {
            final int index = i;
            View child = rg_id.getChildAt(i);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecct(index);
                    vp_id.setCurrentItem(index);
                }
            });
        }
    }

    private void selecct(int index) {
        int count = rg_id.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = rg_id.getChildAt(i);
            //璁剧疆閫変腑鐘舵€?
            child.setSelected(i == index);
        }
    }

    private void initView() {
        rg_id = (RadioGroup) findViewById(R.id.rg_id);
        rb_1 = (RadioButton) findViewById(R.id.rb_1);
        rb_2 = (RadioButton) findViewById(R.id.rb_2);
        rb_3 = (RadioButton) findViewById(R.id.rb_3);
        vp_id = (ViewPager) findViewById(R.id.vp_id);
        img_back = (ImageView) findViewById(R.id.img_back);

        //添加Fragment
        initData();
        vp_id.setAdapter(new MyAdapter(getSupportFragmentManager()));
        //返回主页面
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DingdanActivity.this,MainActivity.class);
                startActivity(intent);
                DingdanActivity.this.overridePendingTransition(R.anim.enter_anim2,R.anim.exit_anim2);
            }
        });
    }

    private void initData() {
        list = new ArrayList<Fragment>();
        DingDan_zuche_fragment frag_zuche = new DingDan_zuche_fragment();
        list.add(frag_zuche);
        DingDan_chongdian_fragment frag_chongdian = new DingDan_chongdian_fragment();
        list.add(frag_chongdian);
        DingDan_tingche_fragment frag_tingche = new DingDan_tingche_fragment();
        list.add(frag_tingche);
    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
