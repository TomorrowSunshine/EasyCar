package com.zl.cn.jer.easycar.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.SearchXiangQing.ChongDianXiang;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.bean.ListAddressChongDianBean;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.MyGetPost;
import com.zl.cn.jer.easycar.view.xlistview.XListView;

import java.util.List;
import java.util.Map;

public class ListAddressChongDian extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView cd_list;
    private List<ListAddressChongDianBean.ListBean> chongdian_list_bean;
    private TextView cd_name;
    Handler handler=new Handler();
    private ImageView cd_back;
    private String cd_address_name;
    private String city_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_address_chong_dian);
        cd_list = (XListView) findViewById(R.id.address_chongdian_lv);
        cd_name = (TextView) findViewById(R.id.address_chongdian_name);
        cd_back = (ImageView) findViewById(R.id.address_chongdiane_back);
        Intent intent=getIntent();
        cd_address_name = intent.getStringExtra("sitename");

        getData();
        backOnclik();
        listonclick();
        //直接粘
        cd_list.setPullLoadEnable(true);//设置下拉刷新
        cd_list.setPullRefreshEnable(true);//设置监听事件，重写两个方法
        cd_list.setXListViewListener(this);//设置上拉刷新
    }

    private void listonclick() {
        cd_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListAddressChongDianBean.ListBean  chongdianlistbean= (ListAddressChongDianBean.ListBean) adapterView.getItemAtPosition(i);

            Intent intent=new Intent(ListAddressChongDian.this, ChongDianXiang.class);
                String factoryNo = chongdianlistbean.getFactoryNo();
                intent.putExtra("factoryNo",factoryNo);
                intent.putExtra("city_name",city_name);
                startActivity(intent);
            }
        });

    }

    private void backOnclik() {
        cd_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //listView的适配器
    private void initData() {
        // TODO Auto-generated method stub
        cd_list.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view=View.inflate(ListAddressChongDian.this, R.layout.addresslist_item, null);
                TextView number= (TextView) view.findViewById(R.id.car_number);
                TextView xinxi= (TextView) view.findViewById(R.id.car_xinxi);
                TextView man= (TextView) view.findViewById(R.id.car_man);
                final ImageView car_bg= (ImageView) view.findViewById(R.id.car_bg);
                ImageView car_if= (ImageView) view.findViewById(R.id.car_if);
                String status = chongdian_list_bean.get(position).getStatus();
                if (status.equals("空闲")){
                    car_if.setImageResource(R.drawable.iv_free);
                }else{

                    car_if.setImageResource(R.drawable.iv_already_full);
                }


                RequestQueue mQueue = Volley.newRequestQueue(ListAddressChongDian.this);
                ImageRequest imageRequest = new ImageRequest(
                        chongdian_list_bean.get(position).getImgUrl(),
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                car_bg.setImageBitmap(response);
                            }
                        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        car_bg.setImageResource(R.drawable.default_placeholder_img);
                    }
                });
                mQueue.add(imageRequest);


                cd_name.setText(cd_address_name);
                number.setText(chongdian_list_bean.get(position).getDeviceNo());
                xinxi.setText(chongdian_list_bean.get(position).getDevicePower()+"km"+chongdian_list_bean.get(position).getFixedVoltage());
                return view;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return position;
            }

            @Override
            public Object getItem(int position) {
                // TODO Auto-generated method stub
                return chongdian_list_bean.get(position);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub

                //固定的条目数
                return chongdian_list_bean.size();
            }
        });
    }
    public void  getData() {
        int page=1;
        Intent intent = getIntent();
        int Id = intent.getIntExtra("city",0);
        city_name = intent.getStringExtra("city_name");
        String httpurl = "https://api.eakay.cn/order-api/api/charge/stakeList.htm";
        Map<String, String> mMap = MyGetPost.getMap("site_code",String.valueOf(Id),"customerId",String.valueOf(App.Cus),"currentPage", String.valueOf(page),"phone",App.Phone,"city", city_name);
        HttpNet.postObjectMinongApi(httpurl, mMap, new ResponseListener() {



            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Tag","请求失败");
                Toast.makeText(ListAddressChongDian.this, "请求失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                Log.d("Tag",s.toString());
                ListAddressChongDianBean listAddressBean = gson.fromJson(s, ListAddressChongDianBean.class);
                chongdian_list_bean = listAddressBean.getList();
                System.out.println(chongdian_list_bean.toString());
                Message msg=new Message();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                    }
                });

            }
        });

    }
    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
                load();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
                load();
            }
        }, 2000);
    }
    public void load(){
        cd_list.stopLoadMore();
        cd_list.stopRefresh();
        cd_list.setRefreshTime("刚刚");
    }

}
