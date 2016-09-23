package com.SearchXiangQing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.App;
import com.zl.cn.jer.easycar.bean.ChongDianXiangQingBean;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.MyGetPost;

import java.util.List;
import java.util.Map;

public class ChongDianXiang extends AppCompatActivity {

    private ImageView im;
    private TextView dianfei;
    private TextView fuwufei;
    private TextView ts1;
    private TextView ts2;
    private TextView z1;
    private TextView z2;
    private TextView y1;
    private TextView y2;
    private TextView number;
    private RadioButton im1;
    private RadioButton im2;
    private RadioButton im3;
    private RadioButton im4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chong_dian_xiang);
        //获得下标
        getIndex();

        //获得数据
        getData();
    }

    public void getIndex() {
        im = (ImageView) findViewById(R.id.cd_im);
        number = (TextView) findViewById(R.id.cd_number);
        ImageView back= (ImageView) findViewById(R.id.cd_xiangqing_back);

        dianfei = (TextView) findViewById(R.id.dianfei_qian);
        fuwufei = (TextView) findViewById(R.id.fuwu_qian);
        ts1 = (TextView) findViewById(R.id.cd_ts1);
        ts2 = (TextView) findViewById(R.id.cd_ts2);
        z1 = (TextView) findViewById(R.id.cd_z1);
        z2 = (TextView) findViewById(R.id.cd_z2);
        y1 = (TextView) findViewById(R.id.cd_y1);
        y2 = (TextView) findViewById(R.id.cd_y2);
        im1 = (RadioButton) findViewById(R.id.cdimageview1);
        im2 = (RadioButton) findViewById(R.id.cdimageview1);
        im3 = (RadioButton) findViewById(R.id.cdimageview1);
        im4 = (RadioButton) findViewById(R.id.cdimageview1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void getData() {

        String httpurl = "https://api.eakay.cn/order-api/api/charge/orderForStakeList.htm";
        int page=1;
        Intent intent = getIntent();
        String factoryNo = intent.getStringExtra("factoryNo");
        String city_name = intent.getStringExtra("city_name");

        Map<String, String> mMap = MyGetPost.getMap("factoryNo",factoryNo,"customerId",String.valueOf(App.Cus),"currentPage", String.valueOf(++page),"phone",App.Phone,"city",city_name);
        //Map<String, String> mMap = MyGetPost.getMap("carId",String.valueOf(carid));

        HttpNet.postObjectMinongApi(httpurl, mMap, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Tag", "请求失败");
                Toast.makeText(ChongDianXiang.this, "请求失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                ChongDianXiangQingBean chongDianXiangQingBean = gson.fromJson(s, ChongDianXiangQingBean.class);
                List<ChongDianXiangQingBean.PortListBean> portList = chongDianXiangQingBean.getPortList();

                   //设置图片
                   RequestQueue mQueue = Volley.newRequestQueue(ChongDianXiang.this);
                   ImageRequest imageRequest = new ImageRequest(
                        chongDianXiangQingBean.getStakeInfo().getImgUrl() ,
                           new Response.Listener<Bitmap>() {
                               @Override
                               public void onResponse(Bitmap response) {
                                   im.setImageBitmap(response);
                               }
                           }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                       @Override
                       public void onErrorResponse(VolleyError error) {
                           im.setImageResource(R.drawable.default_placeholder_img);
                       }
                   });
                   mQueue.add(imageRequest);
                   number.setText(chongDianXiangQingBean.getStakeInfo().getDeviceNo());
                   dianfei.setText(chongDianXiangQingBean.getStakeInfo().getPrice()+"元/度");
                   fuwufei.setText(chongDianXiangQingBean.getStakeInfo().getServerPrice()+"元/度");
                    ts1.setText("1.易开租车用户可免费为所租车充电，其他用户需缴纳"+chongDianXiangQingBean.getChargeMoney()+"元押金");
                    ts2.setText("2.系统将为您预留该服务"+chongDianXiangQingBean.getStakeInfo().getReservedMinute()+"分钟");

                for (int i=0;i<portList.size();i++){

                    if (portList.get(i).getStatus().equals("空闲")){
                        im1.setEnabled(true);
                        im2.setEnabled(true);
                        im3.setEnabled(true);
                        im4.setEnabled(true);
                    }else{
                        im1.setEnabled(false);
                        im2.setEnabled(false);
                        im3.setEnabled(false);
                        im4.setEnabled(false);
                    }

                }
            }
        });
    }
}
