package com.SearchXiangQing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.zl.cn.jer.easycar.bean.ParkingXiangQingBean;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.MyGetPost;

import java.util.List;
import java.util.Map;

public class ParkingXiangQing extends AppCompatActivity {


    private ImageView pk_back;
    private ImageView pk_image;
    private TextView sanshi;
    private TextView pk_code;
    private TextView siwai;
    private TextView sinei;
    private TextView ershisi;
    private TextView car_leixing;
    private TextView san_qian;
    private TextView sinei_qian;
    private TextView ershisi_qian;
    private TextView xuzhi1;
    private TextView xuzhi2;
    private TextView siwai_qian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_xiang_qing);
        getIndex();
        getData();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    public void getData() {


        String httpurl = "https://api.eakay.cn/order-api/api/park/parkInfor.htm";
        int page = 1;
        Intent intent = getIntent();

        int site_code = intent.getIntExtra("site_code", 0);
        int spaceId = intent.getIntExtra("spaceId", 0);
        int merchantId = intent.getIntExtra("merchantId", 0);
        //String city_name = intent.getStringExtra("city_name");

        Map<String, String> mMap = MyGetPost.getMap( "customerId", String.valueOf(App.Cus), "currentPage", String.valueOf(++page), "phone", App.Phone, "site_code", String.valueOf(site_code),"spaceId", String.valueOf(spaceId),"merchantId",String.valueOf(merchantId));
      //  Map<String, String> mMap = MyGetPost.getMap(String.valueOf(merchantId), "customerId", String.valueOf(App.Cus), "site_code", String.valueOf(site_code),"spaceId", String.valueOf(spaceId),"merchantId");

        HttpNet.postObjectMinongApi(httpurl, mMap, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Tag", "请求失败");
                Toast.makeText(ParkingXiangQing.this, "请求失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                ParkingXiangQingBean pkbean = gson.fromJson(s, ParkingXiangQingBean.class);
                List<ParkingXiangQingBean.PriceListBean> portList = pkbean.getPriceList();
                for (int i = 0; i < portList.size(); i++) {
                //设置图片
                RequestQueue mQueue = Volley.newRequestQueue(ParkingXiangQing.this);
                ImageRequest imageRequest = new ImageRequest(
                        pkbean.getMap().getImgUrl(),
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                pk_image.setImageBitmap(response);
                            }
                        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pk_image.setImageResource(R.drawable.default_placeholder_img);
                    }
                });
                mQueue.add(imageRequest);
                    san_qian.setText(portList.get(i).getHourHalfCost());
                    pk_code.setText(pkbean.getMap().getSpaceNO());
                    car_leixing.setText(pkbean.getPriceList().get(i).getCarModel());
                    sinei_qian.setText(portList.get(i).getHour4Cost()+"元/小时");
                    siwai_qian.setText(portList.get(i).getHourOtherCost()+"元/小时");
                    ershisi_qian.setText(portList.get(i).getHourDayCost());
                    xuzhi1.setText("1.停车需缴纳"+pkbean.getMinSure()+"元保证金");
                    xuzhi2.setText("2.预约该停车位系统将为您自动保留"+pkbean.getRetainBook()+
                    "分钟");
            }


        }
        });
    }

    public void getIndex() {

        pk_back = (ImageView) findViewById(R.id.pk_back);
        pk_image = (ImageView) findViewById(R.id.pk_image);

        sanshi = (TextView) findViewById(R.id.sanshi);
        pk_code = (TextView) findViewById(R.id.pk_code);

        siwai = (TextView) findViewById(R.id.siwai);

        sinei = (TextView) findViewById(R.id.sinei);
        ershisi = (TextView) findViewById(R.id.ershisi);
        car_leixing = (TextView) findViewById(R.id.car_leixing);
        san_qian = (TextView) findViewById(R.id.san_qian);
        sinei_qian = (TextView) findViewById(R.id.sinei_qian);
        siwai_qian = (TextView) findViewById(R.id.siwai_qian);

        ershisi_qian = (TextView) findViewById(R.id.ershisi_qian);
        xuzhi1 = (TextView) findViewById(R.id.xuzhi1);
        xuzhi2 = (TextView) findViewById(R.id.xuzhi2);

        pk_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
