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

import com.SearchXiangQing.ZucheXiangqing;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.bean.ListAddressBean;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.MyGetPost;
import com.zl.cn.jer.easycar.view.xlistview.XListView;

import java.util.List;
import java.util.Map;

//租车列表
//王红冉
public class ListAddreaaActivity extends AppCompatActivity implements XListView.IXListViewListener {

    private XListView add_list;
    private int pageIndex = 10;
    private int n=10;
    Handler handler=new Handler();
    private ImageView back;
    private ImageView im_back;
    private List<ListAddressBean.ListBean> list_bean;
    private TextView add_name;
    private int carid;
    private String city_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zu_che);
        im_back = (ImageView) findViewById(R.id.address_back);
        add_list = (XListView) findViewById(R.id.address_lv);
        add_name = (TextView) findViewById(R.id.address_name);
        getData();
        //返回上一级菜单
        backonclick();
        //直接粘
        add_list.setPullLoadEnable(true);//设置下拉刷新
        add_list.setPullRefreshEnable(true);//设置监听事件，重写两个方法
        add_list.setXListViewListener(this);//设置上拉刷新
        listOnclik();


       //initData();
    }

    private void listOnclik() {
        add_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListAddressBean.ListBean listBean = (ListAddressBean.ListBean) adapterView.getItemAtPosition(i);
                Intent intent=new Intent(ListAddreaaActivity.this, ZucheXiangqing.class);
                intent.putExtra("id",listBean.getId());
                intent.putExtra("carnumber",listBean.getCarNumber());
                intent.putExtra("city_name",city_name);
                intent.putExtra("merchantId",listBean.getMerchantId());
                startActivity(intent);
            }
        });

    }


    private void backonclick() {

        im_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    //listView的适配器
    private void initData() {
        // TODO Auto-generated method stub
        add_list.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                View view=View.inflate(ListAddreaaActivity.this, R.layout.addresslist_zuche_item, null);
                TextView pai= (TextView) view.findViewById(R.id.zucar_pai);
                TextView zuowei= (TextView) view.findViewById(R.id.zucar_zuowei);
                TextView km= (TextView) view.findViewById(R.id.zucar_km);
                TextView newcar= (TextView) view.findViewById(R.id.zucar_new);
                ImageView zu_if= (ImageView) view.findViewById(R.id.zuche_if);
                carid = list_bean.get(position).getId();

                final ImageView zu_im= (ImageView) view.findViewById(R.id.zuche_image);
                RequestQueue mQueue = Volley.newRequestQueue(ListAddreaaActivity.this);
                String status = list_bean.get(position).getStatus();
                if (status.equals("空闲")){
                    zu_if.setImageResource(R.drawable.iv_free);
                }else {

                    zu_if.setImageResource(R.drawable.iv_have_rent);
                }


                ImageRequest imageRequest = new ImageRequest(
                      list_bean.get(position).getLittleImgs(),
                        new Response.Listener<Bitmap>() {
                            @Override
                            public void onResponse(Bitmap response) {
                                zu_im.setImageBitmap(response);
                            }
                        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        zu_im.setImageResource(R.drawable.default_placeholder_img);
                    }
                });
                mQueue.add(imageRequest);



                pai.setText(list_bean.get(position).getCarNumber());
                zuowei.setText(list_bean.get(position).getManufacturerName()+list_bean.get(position).getCarTypeName()+"   "+list_bean.get(position).getSeatCount()+"座");
                km.setText("当前续航里程约"+list_bean.get(position).getSurplusKms()+"Km");
                newcar.setText(list_bean.get(position).getDescribe());
                add_name.setText(list_bean.get(position).getSiteName());
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
                return list_bean.get(position);
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub

                return list_bean.size();
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
        add_list.stopLoadMore();
        add_list.stopRefresh();
        add_list.setRefreshTime("刚刚");
    }

    public void  getData() {
        int page=1;
        Intent intent = getIntent();
        int Id = intent.getIntExtra("city",0);
        city_name = intent.getStringExtra("city_name");

        String httpurl = "https://api.eakay.cn/order-api/api/MiteSite/findCarBySiteCode.htm";
        Map<String, String> mMap = MyGetPost.getMap("id",String.valueOf(Id),"customerId",String.valueOf(App.Cus),"currentPage", String.valueOf(page),"phone",App.Phone,"city", city_name);
        HttpNet.postObjectMinongApi(httpurl, mMap, new ResponseListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("Tag","请求失败");
                Toast.makeText(ListAddreaaActivity.this, "请求失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                Log.d("Tag",s.toString());
                ListAddressBean listAddressBean = gson.fromJson(s, ListAddressBean.class);
                list_bean=listAddressBean.getList();

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
}
