package com.zl.cn.jer.easycar.activity.myyikai;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.MyHomeActivity;
import com.zl.cn.jer.easycar.fragment.DingDan_chongdian_fragment;
import com.zl.cn.jer.easycar.fragment.DingDan_tingche_fragment;
import com.zl.cn.jer.easycar.fragment.DingDan_zuche_fragment;
import com.zl.cn.jer.easycar.utils.IntentUtils;
import com.zl.cn.jer.easycar.utils.PopWindowUtile;

import java.util.ArrayList;
import java.util.List;

/*
*
* @优惠券
* @赵雷
*
* */

public class MyHomeYouHuiJuanActivity extends AppCompatActivity implements View.OnClickListener{


    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioGroup rgid;
    private LinearLayout l2;
    private ImageView imgbackyouhuiquan;
    private TextView textView4;
    private ImageView ivpopwin;
    private LinearLayout l1;
    private ViewPager vpidyouhuijuan;
    private RelativeLayout orderid;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private List<Fragment> list;
    private TextView shixiao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_you_hui_juan);
        initialize();
        //查找视图
        initView();
        //RadioButton的循环点击切换ViewPager
        initEvent();
        //VeiwPager切换
        initViewPagerListener();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initViewPagerListener() {
        //监听ViewPager
        vpidyouhuijuan.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rb1.setChecked(true);
                        rb1.setTextColor(Color.parseColor("#6DD1FF"));
                        rb2.setTextColor(Color.parseColor("#9499AC"));
                        rb3.setTextColor(Color.parseColor("#9499AC"));

                        break;
                    case 1:
                        rb2.setChecked(true);
                        rb2.setTextColor(Color.parseColor("#6DD1FF"));
                        rb1.setTextColor(Color.parseColor("#9499AC"));
                        rb3.setTextColor(Color.parseColor("#9499AC"));
                        break;
                    case 2:
                        rb3.setChecked(true);
                        rb3.setTextColor(Color.parseColor("#6DD1FF"));
                        rb2.setTextColor(Color.parseColor("#9499AC"));
                        rb1.setTextColor(Color.parseColor("#9499AC"));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initEvent() {
        int gg = rgid.getChildCount();
        for (int i = 0; i < gg; i++) {
            final int index = i;
            View child = rgid.getChildAt(i);
            child.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selecct(index);
                    vpidyouhuijuan.setCurrentItem(index);
                }
            });
        }
    }

    private void selecct(int index) {
        int count = rgid.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = rgid.getChildAt(i);
            //璁剧疆閫変腑鐘舵€?
            child.setSelected(i == index);
        }
    }

    private void initView() {

        //添加Fragment
        initDatas();
        vpidyouhuijuan.setAdapter(new MyAdapter(getSupportFragmentManager()));
        //返回主页面
        imgbackyouhuiquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyHomeYouHuiJuanActivity.this, MyHomeActivity.class);
                startActivity(intent);
            }
        });
        ivpopwin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopWindowUtile.showPopupWindow("使用规则", "优惠券激活", MyHomeYouHuiJuanActivity.this, ivpopwin, getWindowManager().getDefaultDisplay().getWidth() / 2, YouhuiquanShiYongActivity.class, YouhuiquanJiHuoActivity.class);
            }
        });

    }

    private void initDatas() {
        list = new ArrayList<Fragment>();
        DingDan_zuche_fragment frag_zuche = new DingDan_zuche_fragment();
        list.add(frag_zuche);
        DingDan_chongdian_fragment frag_chongdian = new DingDan_chongdian_fragment();
        list.add(frag_chongdian);
        DingDan_tingche_fragment frag_tingche = new DingDan_tingche_fragment();
        list.add(frag_tingche);
    }

    private void initialize() {
        shixiao = (TextView) findViewById(R.id.shixiao);
        rb1 = (RadioButton) findViewById(R.id.rb_1);
        rb2 = (RadioButton) findViewById(R.id.rb_2);
        rb3 = (RadioButton) findViewById(R.id.rb_3);
        rgid = (RadioGroup) findViewById(R.id.rg_id);
        l2 = (LinearLayout) findViewById(R.id.l2);
        imgbackyouhuiquan = (ImageView) findViewById(R.id.img_back_youhuiquan);
        ivpopwin = (ImageView) findViewById(R.id.iv_popwin);
        l1 = (LinearLayout) findViewById(R.id.l1);
        vpidyouhuijuan = (ViewPager) findViewById(R.id.vp_id_youhuijuan);
        orderid = (RelativeLayout) findViewById(R.id.order_id);
        shixiao.setOnClickListener(MyHomeYouHuiJuanActivity.this);
    }
    
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MyHomeYouHuiJuan Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.shixiao:
                IntentUtils.intentClass(MyHomeYouHuiJuanActivity.this,MyHomeShiXiaoActivity.class);
                break;
        }
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
