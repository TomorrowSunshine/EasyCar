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

import com.SearchXiangQing.ParkingXiangQing;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.bean.ListAddressParkingBean;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.MyGetPost;
import com.zl.cn.jer.easycar.view.xlistview.XListView;

import java.util.List;
import java.util.Map;

public class ListAddressParking extends AppCompatActivity implements XListView.IXListViewListener {
    private List<ListAddressParkingBean.ListBean> pk_list;
    private ImageView pk_back;
    private XListView pk_lv;
    private TextView pk_name;
    Handler handler=new Handler();
    private String city_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_address_parking);
        pk_back = (ImageView) findViewById(R.id.address_parking_back);
        pk_lv = (XListView) findViewById(R.id.address_parking_lv);
        pk_name = (TextView) findViewById(R.id.address_parking_name);
        Intent intent = getIntent();
        String pk_address=intent.getStringExtra("pkname");


        getData();
        backOnclik();
        listonclick();
        //直接粘
        pk_lv.setPullLoadEnable(true);//设置下拉刷新
        pk_lv.setPullRefreshEnable(true);//设置监听事件，重写两个方法
        pk_lv.setXListViewListener(this);//设置上拉刷新
    }

    private void listonclick() {
        pk_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListAddressParkingBean.ListBean pklist = (ListAddressParkingBean.ListBean) adapterView.getItemAtPosition(i);

                Intent intent =new Intent(ListAddressParking.this, ParkingXiangQing.class);
                int site_code = pklist.getSite_code();

                int spaceId = pklist.getSpaceId();

                int merchantId = pklist.getMerchantId();
                intent.putExtra("site_code",site_code);
                intent.putExtra("spaceId",spaceId);
                intent.putExtra("merchantId",merchantId);
                intent.putExtra("city_name",city_name);
                startActivity(intent);
            }
        });
    }

    private void backOnclik() {
        pk_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //listView的适配器
    private void initData() {
        // TODO Auto-generated method stub
        pk_lv.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view=View.inflate(ListAddressParking.this, R.layout.addressparkingitem, null);
                TextView pk_num= (TextView) view.findViewById(R.id.ad_park_num);
                TextView pk_ting= (TextView) view.findViewById(R.id.ad_park_ting);
                final ImageView pk_bg= (ImageView) view.findViewById(R.id.pk_bg);
                ImageView pk_if= (ImageView) view.findViewById(R.id.pk_if);
                String pStatus = pk_list.get(position).getPStatus();
                if (pStatus.equals("空闲")){
                    pk_if.setImageResource(R.drawable.iv_free);

                }else{
                    pk_if.setImageResource(R.drawable.iv_already_full);
                }
                RequestQueue mQueue = Volley.newRequestQueue(ListAddressParking.this);
                ImageRequest imageRequest = new ImageRequest(
                        pk_list.get(position).getImgUrl(),
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                pk_bg.setImageBitmap(response);
                            }
                        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pk_bg.setImageResource(R.drawable.default_placeholder_img);
                    }
                });
                mQueue.add(imageRequest);

                pk_num.setText(pk_list.get(position).getSpaceNO());
                pk_ting.setText(pk_list.get(position).getParkingType()+"");
                pk_name.setText(pk_list.get(position).getSiteName());
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
                return pk_list.get(position);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return pk_list.size();
            }
        });
    }
    public void  getData() {
        int page=1;
        Intent intent = getIntent();
        int Id = intent.getIntExtra("city",0);
        city_name = intent.getStringExtra("city_name");
        String httpurl = "https://api.eakay.cn/order-api/api/park/parkLockList.htm";
        Map<String, String> mMap = MyGetPost.getMap("site_code",String.valueOf(Id),"customerId",String.valueOf(App.Cus),"currentPage", String.valueOf(page),"phone",App.Phone,"city", city_name);


        HttpNet.postObjectMinongApi(httpurl, mMap, new ResponseListener() {




            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Tag","请求失败");
                Toast.makeText(ListAddressParking.this, "请求失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                Log.d("Tag",s.toString());
                ListAddressParkingBean listAddressBean = gson.fromJson(s, ListAddressParkingBean.class);
                pk_list = listAddressBean.getList();

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
        pk_lv.stopLoadMore();
        pk_lv.stopRefresh();
        pk_lv.setRefreshTime("刚刚");
    }

}
