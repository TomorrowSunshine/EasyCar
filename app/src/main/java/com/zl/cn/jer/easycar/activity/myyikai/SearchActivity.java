package com.zl.cn.jer.easycar.activity.myyikai;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.App;
import com.zl.cn.jer.easycar.activity.ListAddreaaActivity;
import com.zl.cn.jer.easycar.activity.ListAddressChongDian;
import com.zl.cn.jer.easycar.activity.ListAddressParking;
import com.zl.cn.jer.easycar.adapter.SearchAdapter;
import com.zl.cn.jer.easycar.adapter.SearchChongDian;
import com.zl.cn.jer.easycar.adapter.SearchParking;
import com.zl.cn.jer.easycar.bean.ChongDian;
import com.zl.cn.jer.easycar.bean.CityFindList;
import com.zl.cn.jer.easycar.bean.StopParking;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.MyGetPost;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchActivity extends AppCompatActivity{

    private ImageView back;
    private TextView search_address;
    private EditText search_context;
    private ListView search_list;
    private SearchAdapter myCityFindList;
    private List<CityFindList.ListBean> list;
    private List<CityFindList.ListBean> lists;
    private CityFindList cityFindList;
    private List<StopParking.ListBean> parking_list;
    private SearchParking parkingadapter;
    private StopParking stopParking;
    private ChongDian chongDian;
    private Intent intent;
    private SearchChongDian chongAdapter;
    private List<ChongDian.SiteListBean> siteList;

    private GoogleApiClient client;
    private int type  ;
    private List<ChongDian.SiteListBean> chong_lists;
    private List<StopParking.ListBean> parking_lists;

    private String city_name;
    private double lat;
    private double lon;
    private TextView hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        intent = getIntent();
        type = intent.getIntExtra("type", 1);
        lat = intent.getDoubleExtra("lat",0);
        lon = intent.getDoubleExtra("lon",0);
        System.out.println("lat%%%"+lat+"lon))))))"+lon);
        city_name = intent.getStringExtra("city");
        //控件
        initView();
        //返回键的点击事件
        Backonclick();
        search_context.addTextChangedListener(textWatcher);
        //获取数据
        switch (type) {
            case 1:

                search_address.setText("租赁点列表 ");
               String  url= "https://api.eakay.cn/order-api/api/MiteSite/findMiteSite.htm";
                getData(url);

                break;

            case 2:
                search_address.setText("充电点列表 ");
                String ur = "https://api.eakay.cn/order-api/api/charge/siteList.htm";
                getData(ur);
                break;
            case 3:
                search_address.setText("停车点列表 ");
               String  u = "https://api.eakay.cn/order-api/api/park/parkList.htm";
                getData(u);
                break;
        }

    }

    private void initView() {
        //标题地址
        search_address = (TextView) findViewById(R.id.search_address);
        //返回键按钮
        back = (ImageView) findViewById(R.id.search_back);
        //輸入框
        search_context = (EditText) findViewById(R.id.search_context);
        search_list = (ListView) findViewById(R.id.search_listVeiw);
        lists = new ArrayList<CityFindList.ListBean>();
        chong_lists = new ArrayList<ChongDian.SiteListBean>();
        parking_lists = new ArrayList<StopParking.ListBean>();
        hide = (TextView) findViewById(R.id.hide);


    }

    private void Backonclick() {

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    private void getData(String url) {


        //String name = "芜湖市";


        Map<String, String> mMap = MyGetPost.getMap("phone", App.Phone, "city", city_name, "customerId", String.valueOf(App.Cus),"lng",lon+"","lat",lat+"");
       //Log.e("token", App.USERTOKEN + "phone" + App.Phone + "customerId" + String.valueOf(App.Cus) + "city" + name);

        HttpNet.postObjectMinongApi(url, mMap, new ResponseListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(SearchActivity.this, "请求失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                switch (type) {
                    case 1:
                        cityFindList = gson.fromJson(s.toString(), CityFindList.class);
                        list = cityFindList.getList();

                        myCityFindList = new SearchAdapter(SearchActivity.this, list,null,lat,lon);
                        search_list.setAdapter(myCityFindList);
                        search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent intent=new Intent(SearchActivity.this, ListAddreaaActivity.class);
                                int  name=list.get(i).getId();
                                intent.putExtra("city",name);
                                intent.putExtra("city_name",city_name);
                                startActivity(intent);


                            }
                        });
                        break;
                    case 2:
                        chongDian = gson.fromJson(s.toString(), ChongDian.class);
                        siteList = chongDian.getSiteList();
                        chongAdapter = new SearchChongDian(SearchActivity.this, siteList,null,lat,lon);
                        search_list.setAdapter(chongAdapter);

                        search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent=new Intent(SearchActivity.this, ListAddressChongDian.class);
                                int  name=siteList.get(i).getId();
                                intent.putExtra("sitename",siteList.get(i).getSiteName());
                                intent.putExtra("city",name);
                                intent.putExtra("city_name",city_name);
                                startActivity(intent);
                            }
                        });
                        break;

                    case 3:

                        stopParking = gson.fromJson(s.toString(), StopParking.class);
                        parking_list = stopParking.getList();
                        parkingadapter = new SearchParking(SearchActivity.this, parking_list,null,lat,lon);
                        search_list.setAdapter(parkingadapter);

                        search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent=new Intent(SearchActivity.this, ListAddressParking.class);
                                int  name=parking_list.get(i).getSite_code();
                               intent.putExtra("pkname",parking_list.get(i).getSiteName());
                                intent.putExtra("city",name);
                                intent.putExtra("city_name",city_name);
                                startActivity(intent);
                            }
                        });
                        break;
                }


            }
        });


    }


    private TextWatcher textWatcher = new TextWatcher() {

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

            switch (type){
                case 1:
                    lists.clear();
                    if (s.length() > 0) {
                        for (CityFindList.ListBean lb : list) {
                            if (lb.getSite_name().contains(s)) {

                                lists.add(lb);
                                SearchAdapter myCityFindList = new SearchAdapter(SearchActivity.this, lists,s.toString(),lat,lon);
                                search_list.setAdapter(myCityFindList);
                                myCityFindList.notifyDataSetChanged();

                            }
                        }
                        if (lists.size() == 0) {
                            search_list.setVisibility(View.INVISIBLE);
                            hide.setVisibility(View.VISIBLE);
                        } else {
                            search_list.setVisibility(View.VISIBLE);
                            hide.setVisibility(View.INVISIBLE);
                        }
                    }
                    if (s.length() == 0) {
                        search_list.setAdapter(myCityFindList);
                    }
                    break;

                case 2:
                    chong_lists.clear();
                    if (s.length() > 0) {
                        for (ChongDian.SiteListBean lb : siteList) {
                            if (lb.getSiteName().contains(s)) {

                                chong_lists.add(lb);
                                SearchChongDian myCityFindList = new SearchChongDian(SearchActivity.this, chong_lists,s.toString(),lat,lon);
                                search_list.setAdapter(myCityFindList);
                                myCityFindList.notifyDataSetChanged();

                            }
                        }
                        if (chong_lists.size() == 0) {
                            search_list.setVisibility(View.INVISIBLE);
                            hide.setVisibility(View.VISIBLE);
                        } else {
                            search_list.setVisibility(View.VISIBLE);
                            hide.setVisibility(View.INVISIBLE);
                        }
                    }
                    if (s.length() == 0) {
                        search_list.setAdapter(chongAdapter);
                    }
                    break;

                case 3:


                    parking_lists.clear();
                    if (s.length() > 0) {
                        for (StopParking.ListBean lb : parking_list) {
                            if (lb.getSiteName().contains(s)) {

                                parking_lists.add(lb);
                                SearchParking myCityFindList = new SearchParking(SearchActivity.this, parking_lists,s.toString(),lat,lon);
                                search_list.setAdapter(myCityFindList);
                                myCityFindList.notifyDataSetChanged();

                            }
                        }
                        if (parking_lists.size() == 0) {
                            search_list.setVisibility(View.INVISIBLE);
                            hide.setVisibility(View.VISIBLE);
                        } else {
                            search_list.setVisibility(View.VISIBLE);
                            hide.setVisibility(View.INVISIBLE);
                        }
                    }
                    if (s.length() == 0) {
                        search_list.setAdapter(parkingadapter);
                    }
                    break;
            }

        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        public void afterTextChanged(Editable s) {


        }
    };


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Search Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }




}
