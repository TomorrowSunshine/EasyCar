package com.SearchXiangQing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.App;
import com.zl.cn.jer.easycar.adapter.ZcxqAdapter;
import com.zl.cn.jer.easycar.bean.ZuCheXiangqingBean;
import com.zl.cn.jer.easycar.bean.ZucheXinangQingBen2;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.MyGetPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZucheXiangqing extends AppCompatActivity {
    private ArrayList<ImageView> list_dot=new ArrayList<ImageView>();
    private LinearLayout line;
    private ZucheXinangQingBen2 zucheXinangQingBen2;
    private List<ZuCheXiangqingBean.ListBean> zu_xq_list;
    private ViewPager vp;
    private int id;
    private List<ZucheXinangQingBen2.ListBean> zu_che_list2;
    private Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {

            int num=msg.what;

            switch (num) {

                //num==0
                case 0:

                    //给ViewPager创建适配器

                        vp.setAdapter(new ZcxqAdapter(ZucheXiangqing.this,zu_xq_list,handler,carid,city_name));



                    //创建圆点
                    initdot();
                    //是图片可以左右滑动
                    vp.setCurrentItem(100);

                    //ViewPager监听事件
                    ViewPagerListener();
                    //调用发送延迟消息的方法
                    //sendMsg();
                    break;

                case 1:
                    //得到图片的索引
                    int currentItem = vp.getCurrentItem();

                    //图片的索引加一
                    currentItem++;
                    //执行下一张图片
                    //vp.setCurrentItem(currentItem);

                    //调出发送空消息的方法   一直循环播放
                    //sendMsg();

                    break;

            }

        }




    };
    private TextView shizu;
    private TextView everyday;
    private TextView allday;
    private TextView nes;
    private TextView ti1;
    private TextView ti2;
    private TextView ti3;
    private String carnumber;
    private TextView carPai;
    private ImageView back;
    private int carid;
    private String city_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zuche_xiangqing);
        getindex();


        line = (LinearLayout) findViewById(R.id.line);

        getData();
    }

    private  void getindex(){
        vp = (ViewPager) findViewById(R.id.vp);
        shizu = (TextView) findViewById(R.id.shizu);
        everyday = (TextView)findViewById(R.id.everyday);
        allday = (TextView)findViewById(R.id.Allday);
        nes = (TextView) findViewById(R.id.news);
        ti1 = (TextView) findViewById(R.id.tishi1);
        ti2 = (TextView) findViewById(R.id.tishi2);
        ti3 = (TextView) findViewById(R.id.tishi3);
        back = (ImageView) findViewById(R.id.zuche_xiangqing_back);

        carPai = (TextView) findViewById(R.id.carcode);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //实现无限轮播
    //发送延迟消息的方法
    private void sendMsg() {
        // TODO Auto-generated method stub

        //发送每隔一秒的消息
        handler.sendEmptyMessageDelayed(1, 1000);
    }


    //设置ViewPager的监听事件

    private void ViewPagerListener() {

        //ViewPager的发生变动的方法
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub

                for (int i = 0; i < list_dot.size(); i++) {
                    if(i==position%zu_xq_list.size()){

                        //选中  红色
                        list_dot.get(i).setImageResource(R.drawable.dot_proce);
                    }else{

                        //未选中  蓝色
                        list_dot.get(i).setImageResource(R.drawable.dot_normal);

                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {


            }
        });


    }

    //绘制圆点

    private void initdot() {

        for (int i = 0; i < zu_xq_list.size(); i++) {


            ImageView im=new ImageView(ZucheXiangqing.this);

            if(i==0){

                //默认被选中  呈现红色
                im.setImageResource(R.drawable.dot_proce);

            }else{
                //没有被选中  呈现蓝色
                im.setImageResource(R.drawable.dot_normal);

            }

            LayoutParams params=new LayoutParams(20, 20);

            //设置点与点之间的间距
            params.setMargins(10, 0, 10, 0);
            //加入到线性布局中
            line.addView(im, params);

            //把圆点加载到一个集合中
            list_dot.add(im);
        }
    };









    public void getData() {

        Intent intent = getIntent();
        carid = intent.getIntExtra("id", 0);
        App.mVpId=carid;
        carnumber = intent.getStringExtra("carnumber");

        carPai.setText(carnumber);
        int merchantId = intent.getIntExtra("merchantId", 0);

        city_name = intent.getStringExtra("city_name");
        App.mVpName=city_name;
        String httpurl = "https://api.eakay.cn/order-api/api/carInfo/getCarImagesOrder.htm";
        int page=1;
        Map<String, String> mMap = MyGetPost.getMap("carId",String.valueOf(carid),"customerId",String.valueOf(App.Cus),"currentPage", String.valueOf(++page),"phone",App.Phone,"city", city_name);
       //Map<String, String> mMap = MyGetPost.getMap("carId",String.valueOf(carid));

        HttpNet.postObjectMinongApi(httpurl, mMap, new ResponseListener() {



            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Tag","请求失败");
                Toast.makeText(ZucheXiangqing.this, "请求失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                Log.d("Tag",s.toString());
                ZuCheXiangqingBean zuCheXiangqingBean = gson.fromJson(s, ZuCheXiangqingBean.class);
                zu_xq_list = zuCheXiangqingBean.getList();
                for (int ii=0; ii<zu_xq_list.size();ii++){
                    id = zu_xq_list.get(ii).getId();

                }
               //System.out.println(chongdian_list_bean.toString());
                /*NotificationCompat.MessagingStyle.Message msg=new NotificationCompat.MessagingStyle.Message();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                    }
                });*/
                //创建消息池
                Message msg= Message.obtain();

                msg.obj=zu_xq_list;
                msg.what=0;
                //把参数传过去
                handler.sendMessage(msg);

            }
        });


        String mhttpurl = "https://api.eakay.cn/order-api/api/price/getPrice.htm";
        Map<String, String> mmMap = MyGetPost.getMap("carId",String.valueOf(carid),"merchantId",String.valueOf(merchantId), "customerId", String.valueOf(App.Cus),"currentPage", String.valueOf(++page),"phone",App.Phone,"city", city_name);
        //Map<String, String> mMap = MyGetPost.getMap("carId",String.valueOf(carid));

        HttpNet.postObjectMinongApi(mhttpurl, mmMap, new ResponseListener() {




            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Tag","请求失败");
                Toast.makeText(ZucheXiangqing.this, "暂无数据", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                Log.d("Tag",s.toString());
                zucheXinangQingBen2 = gson.fromJson(s, ZucheXinangQingBen2.class);
                zu_che_list2 = zucheXinangQingBen2.getList();
                for (int i=0; i<zu_che_list2.size();i++){
                    shizu.setText(zu_che_list2.get(i).getOneHoursCost()+"元/时");
                    allday.setText(zu_che_list2.get(i).getMaxCostForDay()+"元/天");
                    everyday.setText(zucheXinangQingBen2.getMap().getInsuranceValue()+"/日");
                    nes.setText(zucheXinangQingBen2.getMap().getInsuranceMessage());
                    ti1.setText("1.工作时间("+zu_che_list2.get(i).getStartTimeForWork()+"-"+zu_che_list2.get(i).getEndTimeForWork()+"）预约，系统将为您预留该车"+zu_che_list2.get(i).getReservedMinute()+"分钟，最迟不超过"+zu_che_list2.get(i).getEndTimeForWork()+"取车");
                    ti2.setText("2.非工作时间预约，系统将为您预留该车至早上"+zu_che_list2.get(i).getStartTimeForWork());
                    ti3.setText("3.用户通过手机自助取还车辆时间为"+zu_che_list2.get(i).getStartTimeForWork()+"-"+zu_che_list2.get(i).getEndTimeForWork()+"，其他时间段不能通过手机自助去还车。");
                }



            }
        });



    }


}
