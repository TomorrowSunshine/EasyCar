package com.com.ortiz.touch;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.App;
import com.zl.cn.jer.easycar.bean.ZuCheXiangqingBean;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.MyGetPost;

import java.util.List;
import java.util.Map;

public class ViewPagerExampleActivity extends AppCompatActivity {
    private List<ZuCheXiangqingBean.ListBean> list;
    private Handler handler =new Handler() {
        @Override
        public void handleMessage(Message msg) {
            list= (List<ZuCheXiangqingBean.ListBean>) msg.obj;
            mViewPager.setAdapter(new TouchImageAdapter());
        }
    };
    private ExtendedViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_example);
        mViewPager = (ExtendedViewPager) findViewById(R.id.view_pager);
       getData();

    }

    public void getData() {
        Intent intent = getIntent();
        int carid = intent.getIntExtra("carid", 0);
        int id = intent.getIntExtra("id", 0);

        String httpurl = "https://api.eakay.cn/order-api/api/carInfo/getCarImagesOrder.htm";
        int page=1;
        Map<String, String> mMap = MyGetPost.getMap("carId",String.valueOf(App.mVpId),"customerId",String.valueOf(App.Cus),"currentPage", String.valueOf(++page),"phone",App.Phone,"city", App.mVpName);
//    Map<String, String> mMap = MyGetPost.getMap("carId",String.valueOf(carid),"customerId",String.valueOf(App.Cus),"currentPage", String.valueOf(++page),"phone",App.Phone,"city", city_name);


        HttpNet.postObjectMinongApi(httpurl, mMap, new ResponseListener() {



            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Tag","请求****************8失败");
                Toast.makeText(ViewPagerExampleActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                Log.d("Tag", s.toString());
                ZuCheXiangqingBean zuCheXiangqingBean2 = gson.fromJson(s, ZuCheXiangqingBean.class);
                list = zuCheXiangqingBean2.getList();
                Message msg=new Message();
                msg.obj=list;
                handler.sendMessage(msg);


            }


           });

    }





    class TouchImageAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            final TouchImageView img = new TouchImageView(container.getContext());

            RequestQueue mQueue = Volley.newRequestQueue(ViewPagerExampleActivity.this);
            ImageRequest imageRequest = new ImageRequest(
                    list.get(position).getFastdfsUrl(),
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            img.setImageBitmap(response);
                        }
                    }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    img.setImageResource(R.drawable.default_placeholder_img);
                }
            });
            mQueue.add(imageRequest);
            container.addView(img, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            return img;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
