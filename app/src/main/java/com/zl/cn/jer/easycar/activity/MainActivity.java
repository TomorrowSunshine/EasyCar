package com.zl.cn.jer.easycar.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviParaOption;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.navisdk.adapter.BNOuterLogUtil;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.ta.utdid2.android.utils.StringUtils;
import com.zbar.lib.CaptureActivity;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.myyikai.SearchActivity;
import com.zl.cn.jer.easycar.bean.Loginbean;
import com.zl.cn.jer.easycar.bean.MyChongdian;
import com.zl.cn.jer.easycar.bean.MyDialog;
import com.zl.cn.jer.easycar.bean.MyQiPao;
import com.zl.cn.jer.easycar.bean.MyTingche;
import com.zl.cn.jer.easycar.bean.MyZuche;
import com.zl.cn.jer.easycar.net.GetURL;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.net.UrlAddress;
import com.zl.cn.jer.easycar.utils.BaiduDw;
import com.zl.cn.jer.easycar.utils.CheckNet;
import com.zl.cn.jer.easycar.utils.IntentUtils;
import com.zl.cn.jer.easycar.utils.MyGetPost;
import com.zl.cn.jer.easycar.utils.ProgressDialogUtil;
import com.zl.cn.jer.easycar.utils.SecurityUtils;
import com.zl.cn.jer.easycar.utils.SelectPicPopupWindow;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.baidu.navisdk.adapter.BNRoutePlanNode.CoordinateType.WGS84;


/*
主页面
李大鹏*/
public class MainActivity extends AppCompatActivity implements OnGetGeoCoderResultListener, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final int MY_PERMISSIONS_CAMERA = 1;


    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 1;
    private ImageView ivuser;
    private TextView tvcity;
    private ImageView ivsearch;
    private ImageView ivscan;
    private RadioButton rbzuche;
    private RadioButton rbchongdian;
    private RadioButton rbtingche;
    private RadioButton rbdingdan;
    private LinearLayout lycity;
    private ImageView start_img;
    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListenner();
    private MapView mMapView;
    private String name;
    private double lat;
    private double lon;
    private double lo;
    private double la;
    private List<MyZuche.ListEntity> mlist;
    private BitmapDescriptor bitmap;
    Dialog dia;
    //地图相关
    boolean isFirstLoc = true;//是否首次定位
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    LocationClient mLocClient;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private GeoCoder mSearch = null;
    private String imgUrlExt1;
    private String linkUrl;
    private String subject;
    private SharedPreferences sp;
    int search_type = 1;
    int num=0;


    private  Loginbean loginbean;;

    public static List<Activity> activityList = new LinkedList<Activity>();

    private static final String APP_FOLDER_NAME = "BNSDKSimpleDemo";
    private String mSDCardPath = null;

    public static final String ROUTE_PLAN_NODE = "routePlanNode";
    private MyQiPao.DataEntity d;
    private ImageView iv_map;



    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!StringUtils.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
                Log.e("MainActivity", showMsg.toString());
            }
        }
    }

    //for receive customer msg from jpush server
    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if(CheckNet.checkNetworkInfo(this)) {
     registerMessageReceiver();  // used for receive msg
     sp = getSharedPreferences("City", MODE_PRIVATE);
     // 初始化搜索模块，注册事件监听
     mSearch = GeoCoder.newInstance();
     mSearch.setOnGetGeoCodeResultListener(MainActivity.this);
     //接收传过来的数据
     initData();
     initialize();
     if (num == 0) {
         initDialog();
         getMinMeg();
     }
        BNOuterLogUtil.setLogSwitcher(true);
        //初始化导航
        BaiduDw dw = new BaiduDw(this);
        if (dw.initDirs()) {
            dw. initNavi();
        }

     //地图标识的点击
     mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
         @Override
         public boolean onMarkerClick(Marker marker) {
             //获取点击经纬度
             lo = marker.getPosition().longitude;
             la = marker.getPosition().latitude;
             //进行定位
             //进行定位
             LatLng l = new LatLng(la, lo);
             //设置定位到屏幕中心
             MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(l, 15);
             mBaiduMap.setMapStatus(mapStatusUpdate);
             Bundle info = marker.getExtraInfo();
             final int id = info.getInt("ID");
             infowindow(id);
             return true;
         }
     });
     //地图点击事件
     mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
         @Override
         public void onMapClick(LatLng latLng) {
             //隐藏信息框
             mBaiduMap.hideInfoWindow();
         }

         @Override
         public boolean onMapPoiClick(MapPoi mapPoi) {
             return false;
         }
     });
        //地图拖动监听
     mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
         @Override
         public void onMapStatusChangeStart(MapStatus mapStatus) {
         }

         @Override
         public void onMapStatusChange(MapStatus mapStatus) {
             //拖动地图的时候隐藏信息框
             mBaiduMap.hideInfoWindow();
         }

         @Override
         public void onMapStatusChangeFinish(MapStatus mapStatus) {
         }
     });
     // ATTENTION: This was auto-generated to implement the App Indexing API.
     // See https://g.co/AppIndexing/AndroidStudio for more information.
     client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
 }else{
     Toast.makeText(this, "暂无网络。。。", Toast.LENGTH_SHORT).show();
 }
    }

    //get请求数据
    //Get请求需要的参数集合
    protected Map<String, String> getParams(int id) {
        Log.e("sss", "getParams");
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String hehe = dateFormat.format(now);
        HashMap<String, String> params = new HashMap<String, String>();
        //公共的
        params.put("lat", lat + "".trim());
        params.put("lng", lon + "".trim());
        params.put("format", "json");
        params.put("ver", "2.4.1");
        params.put("client_type", "android");
        params.put("clientType", "android");
        params.put("app_key", "car");
        params.put("sign_method", "md5");
        params.put("signMethod", "md5");
        params.put("appKey", "car");
        params.put("type", "2");
        params.put("customerId", App.Cus + "".trim());
        params.put("phone", App.Phone);
        params.put("miteSiteId", id + "".trim());
        params.put("user_token", App.USERTOKEN);
        params.put("timestamp", hehe);

        Object[] keys = params.keySet().toArray();
        Arrays.sort(keys);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            builder.append(keys[i].toString() + params.get(keys[i].toString()));
        }
        String result = builder.toString() + "car";
        String strSign = SecurityUtils.MD5(result);
        params.put("sign", strSign);

        return params;
    }

    //Infowindow所需要的网络请求的数据
    private void infowindow(final int id) {
        Log.e("sss", id + " ----id");
        if (loginbean == null) {
            startActivity(new Intent(this, LoginActivity.class));
            Log.d("*******","===============");
        } else {
            if (CheckNet.checkNetworkInfo(this)) {
                ProgressDialogUtil.show(MainActivity.this);
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                Map<String, String> params = getParams(id);
                String url = GetURL.getUrl(UrlAddress.HOST + UrlAddress.QP, params);
                Log.e("sss", url);
                StringRequest request = new StringRequest(url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        ProgressDialogUtil.dismiss();
                        Log.e("sss", s + "infowindow");
                        // 创建InfoWindow
                        Gson gson = new Gson();
                        MyQiPao myQiPao = gson.fromJson(s, MyQiPao.class);
                        d = myQiPao.getData();

                        LinearLayout linear = new LinearLayout(MainActivity.this);
                        linear.setLayoutParams(new LinearLayout.LayoutParams(mMapView.getWidth(), mMapView.getHeight()));
                        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.bubble, null); //自定义气泡形状
                        double width = mMapView.getWidth() * 0.92;
                        Log.e("width", width + "");
                        double height = width * 0.6;
                        view.setLayoutParams(new LinearLayout.LayoutParams((int) width, (int) height));
                        TextView name_info = (TextView) view.findViewById(R.id.name_info);
                        TextView distance_info = (TextView) view.findViewById(R.id.distance_info);
                        TextView rentable_info = (TextView) view.findViewById(R.id.rentable_info);
                        TextView address_info = (TextView) view.findViewById(R.id.address_info);
                        TextView time_info = (TextView) view.findViewById(R.id.time_info);
                        Button daohang_info = (Button) view.findViewById(R.id.daohang_info);
                        daohang_info.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               // Toast.makeText(MainActivity.this, "=================", Toast.LENGTH_SHORT).show();
                                //浏览器地图
                                // startWebNavi();
                                if (Build.VERSION.SDK_INT >= 23){
                                    new SelectPicPopupWindow(MainActivity.this, R.id.activity_main, String.valueOf(lat), String.valueOf(lon), String.valueOf(d.getLat()), String.valueOf(d.getLng()));
                                }else {
                                    BaiduDw dw = new BaiduDw(MainActivity.this);
                                    dw.routeplanToNavi(lon, lat, d.getLng(), d.getLat(), WGS84);
                                }
                            }
                        });

                        Button yuyue_info = (Button) view.findViewById(R.id.yuyue_info);
                        yuyue_info.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (rbzuche.isChecked()) {
                                    Intent i = new Intent(MainActivity.this, ListAddreaaActivity.class);
                                    i.putExtra("city_name", tvcity.getText().toString());
                                    i.putExtra("city", d.getId());
                                    startActivity(i);
                                } else if (rbchongdian.isChecked()) {
                                    Intent i = new Intent(MainActivity.this, ListAddressChongDian.class);
                                    i.putExtra("city_name", tvcity.getText().toString());
                                    i.putExtra("city", d.getId());
                                    i.putExtra("sitename", d.getSite_name());
                                    startActivity(i);
                                } else if (rbtingche.isChecked()) {
                                    Intent i = new Intent(MainActivity.this, ListAddressParking.class);
                                    i.putExtra("city_name", tvcity.getText().toString());
                                    i.putExtra("city", d.getId());
                                    i.putExtra("pkname", d.getSite_name());
                                    startActivity(i);
                                }


                            }
                        });
                        name_info.setText(d.getSite_name());
                        double km = d.getDistance() / 1000;
                        BigDecimal b = new BigDecimal(km);
                        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        distance_info.setText("距离" + f1 + "km");
                        if (rbzuche.isChecked()) {
                            rentable_info.setText(d.getChargeNum() + "可租");
                        } else if (rbchongdian.isChecked()) {
                            rentable_info.setText(d.getChargeNum() + "可充电");
                        } else if (rbtingche.isChecked()) {
                            rentable_info.setText(d.getChargeNum() + "可停车");
                        }

                        address_info.setText(d.getAddress());
                        time_info.setText("营业：" + d.getStartTimeForWork() + "-" + d.getEndTimeForWork());
                        linear.addView(view);
                        LatLng pt = new LatLng(la + 0.0004, lo + 0.00005);
                        InfoWindow mInfoWindow = new InfoWindow(linear, pt, -60);
                        Log.e("mInfoWindow", mInfoWindow.toString() + "=============");
                        mBaiduMap.showInfoWindow(mInfoWindow); //显示气泡
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("sss", volleyError.toString() + "aaaa");
                    }
                });
                requestQueue.add(request);

            } else {
                Toast.makeText(this, "暂无网络", Toast.LENGTH_SHORT).show();
            }
        }

    }
    /**
     * 启动百度地图导航(Web)
     */
    public void startWebNavi(){
        LatLng pt1 = new LatLng(lat, lon);
        LatLng pt2 = new LatLng(la, lo);
        // 构建 导航参数
        NaviParaOption para = new NaviParaOption()
                .startPoint(pt1).endPoint(pt2);

        BaiduMapNavigation.openWebBaiduMapNavi(para, MainActivity.this);
    }

    //活动模块   请求数据
    private void getMinMeg() {
        if(CheckNet.checkNetworkInfo(this))  {
            String url = "https://api.eakay.cn/order-api/web/activity/activityTop.htm";
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.start();
            StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Gson gson = new Gson();
                    MyDialog myDialog = gson.fromJson(s, MyDialog.class);
                    imgUrlExt1 = myDialog.getData().getImgUrlExt1();
                    linkUrl = myDialog.getData().getLinkUrl();
                    subject = myDialog.getData().getSubject();
                    ImageLoader loader = ImageLoader.getInstance();
                    loader.displayImage(imgUrlExt1, start_img);
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError volleyError) {

                }
            });
            requestQueue.add(request);
            num = 1;
        }else{
            Toast.makeText(this, "暂无网络。。。", Toast.LENGTH_SHORT).show();
        }
    }

    //活动模块
    private void initDialog() {
        Context context = MainActivity.this;
        dia = new Dialog(context, R.style.edit_AlertDialog_style);
        dia.setContentView(R.layout.dialog_item);
        ImageView mClose_btn = (ImageView) dia.findViewById(R.id.iv_close);
        start_img = (ImageView) dia.findViewById(R.id.start_img);
        mClose_btn.setOnClickListener(this);
        dia.show();
        dia.setCanceledOnTouchOutside(true); // Sets whether this dialog is
        // canceled when touched outside
        // the window's bounds.
        Window w = dia.getWindow();
        WindowManager.LayoutParams lp = w.getAttributes();
        lp.x = 0;
        lp.y = 40;
        start_img.setOnClickListener(this);
        dia.onWindowAttributesChanged(lp);

    }

    //请求租车数据
    public void getZucheMsg() {
        if(CheckNet.checkNetworkInfo(this)) {
        ProgressDialogUtil.show(MainActivity.this);
        name = tvcity.getText().toString().trim();
        Log.d("******1", name + "**************");
        Log.d("******2", App.Cus + "**************");
        Log.d("******3", App.Phone + "**************");
        mBaiduMap.clear();
        Log.e("MainActivity", "getZucheMsg clear");
        Map<String, String> mMap = MyGetPost.getMap("customerId", String.valueOf(App.Cus), "city", name, "phone", App.Phone);
        HttpNet.postObjectMinongApi(UrlAddress.HOST + UrlAddress.ZUCHE, mMap, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }

            @Override
            public void onResponse(String s) {
                ProgressDialogUtil.dismiss();
                Gson gson = new Gson();
                Log.d("******", s + "**************");
                MyZuche myZuche = gson.fromJson(s, MyZuche.class);
                mlist = myZuche.getList();
                for (MyZuche.ListEntity ml : mlist
                        ) {
                    Log.i("zzzzzzzzzzzzzzz", ml.getLat() + "*******************");
                    Log.i("zzzzzzzzzzzzzzz", ml.getLng() + "*******************");
                    baiduMap(ml.getLat(), ml.getLng(), ml.getIsNightReturnCar(), ml.getIsNightGetCar(), ml.getUseable(), ml.getId());

                }
            }
        });
    }else

    {
        Toast.makeText(this, "暂无网络。。。", Toast.LENGTH_SHORT).show();
    }
    }

    //请求充电数据
    public void getChongdianMsg() {
        if(CheckNet.checkNetworkInfo(this))  {
            ProgressDialogUtil.show(MainActivity.this);
            name = tvcity.getText().toString().trim();
            Map<String, String> mMap = MyGetPost.getMap("customerId", String.valueOf(App.Cus), "city", name, "phone", App.Phone);
            Log.d("TAG", mMap.toString());
            HttpNet.postObjectMinongApi(UrlAddress.HOST + UrlAddress.CHONGDIAN, mMap, new ResponseListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }

                @Override
                public void onResponse(String s) {
                    ProgressDialogUtil.dismiss();
                    mBaiduMap.clear();
                    Log.e("MainActivity", "getChongdianMsg clear");
                    Gson gson = new Gson();
                    MyChongdian myChongdian = gson.fromJson(s, MyChongdian.class);
                    List<MyChongdian.SiteListEntity> siteList = myChongdian.getSiteList();
                    Log.i("111111111", s.toString() + "=====");
                    if (siteList.size() == 0) {
                      //  Toast.makeText(MainActivity.this, "暂时没有充电数据", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (MyChongdian.SiteListEntity ml : siteList
                            ) {
                        baiduMapChong(ml.getLat(), ml.getLng(), ml.getFreeCounts(), ml.getId());
                    }
                }
            });
        }else{
            Toast.makeText(this, "暂无网络。。。", Toast.LENGTH_SHORT).show();
        }
    }

    //请求停车数据
    public void getTingcheMsg() {
        if(CheckNet.checkNetworkInfo(this)) {
            ProgressDialogUtil.show(MainActivity.this);
            name = tvcity.getText().toString().trim();
            Map<String, String> mMap = MyGetPost.getMap("customerId", String.valueOf(App.Cus), "city", name, "phone", App.Phone);
            HttpNet.postObjectMinongApi(UrlAddress.HOST + UrlAddress.TINGCHE, mMap, new ResponseListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                }


                @Override
                public void onResponse(String s) {
                    ProgressDialogUtil.dismiss();
                    mBaiduMap.clear();
                    Log.e("MainActivity","getTingcheMsg clear");
                    Gson gson = new Gson();
                    MyTingche myTingche = gson.fromJson(s, MyTingche.class);
                    List<MyTingche.ListEntity> list = myTingche.getList();
                    if (list.size() == 0) {
                       // Toast.makeText(MainActivity.this, "本城市暂时没有停车点", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (MyTingche.ListEntity ml : list
                            ) {
                        baiduMapTing(ml.getLat(), ml.getLng(), ml.getNum(),ml.getSite_code());
                    }

                }
            });
        }else{
            Toast.makeText(this, "暂无网络。。。", Toast.LENGTH_SHORT).show();
        }
    }

    //租车气泡
    private void baiduMap(double lat, double lng, int isNightReturnCar, int isNightGetCar, int useable,int id) {
        //定义Maker坐标点
        LatLng point = new LatLng(lat, lng);
        //只白天营业  非24小时
        if (isNightReturnCar == 0 && isNightGetCar == 0) {
            if (useable != 0) {
                bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_lease_point_has_car);
            } else {
                bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_lease_point_no_car);
            }
        } else if (isNightReturnCar == 1 && isNightGetCar == 1) {
            if (useable != 0) {
                bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.select_24hours_s);
            } else {
                bitmap = BitmapDescriptorFactory
                        .fromResource(R.drawable.select_24hours_u);
            }
        }
//构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
//在地图上添加Marker，并显示
        Marker maker = (Marker) mBaiduMap.addOverlay(option);
        Log.e("MainActivity","气泡添加成功");
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        maker.setExtraInfo(bundle);
    }

    //充电气泡
    private void baiduMapChong(double lat, double lng, int freCounts,int id) {
        //定义Maker坐标点
        LatLng point = new LatLng(lat, lng);
        //可充电的
        if (freCounts > 0) {
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.charge_has_net);
        } else {
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.charge_no_net);
        }
        //构建MarkerOption,用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        //在地图上添加Marker。并显示
        Marker maker = (Marker) mBaiduMap.addOverlay(option);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        maker.setExtraInfo(bundle);
    }

    //停车气泡
    private void baiduMapTing(double lat, double lng, int num,int id) {
        //定义Maker坐标点
        LatLng point = new LatLng(lat, lng);

        if (num > 0) {
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_park_point_has_free);
        } else {
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_park_point_no_free);
        }
        //构建MarkerOption,用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        //在地图上添加Marker。并显示
        Marker maker = (Marker) mBaiduMap.addOverlay(option);
        Bundle bundle = new Bundle();
        bundle.putInt("ID", id);
        maker.setExtraInfo(bundle);

    }

    //接收登陆界面传值
    private void initData() {
        if(CheckNet.checkNetworkInfo(this))  {
            SharedPreferences login = getSharedPreferences("Login", MODE_PRIVATE);
            String loginBean = login.getString("LoginBean", "");
            Gson g = new Gson();
            loginbean = g.fromJson(loginBean, Loginbean.class);
            App.Cus = loginbean.getData().getId();
            App.USERTOKEN = loginbean.getData().getUserToken();
            App.Phone = loginbean.getData().getPhone();
        }else{
            Toast.makeText(this, "暂无网络", Toast.LENGTH_SHORT).show();
        }
    }

    //初始化定位地图
    private void baidu() {
        if(CheckNet.checkNetworkInfo(this))  {
            mBaiduMap = mMapView.getMap();
            ProgressDialogUtil.show(MainActivity.this);
            mLocationClient = new LocationClient(getApplicationContext()); // 声明LocationClient类
            mLocationClient.registerLocationListener(myListener); // 注册监听函数

            // 普通地图

            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
            // 开启定位图层
            mBaiduMap.setMyLocationEnabled(true);
            mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
            mBaiduMap
                    .setMyLocationConfigeration(new MyLocationConfiguration(
                            mCurrentMode, true, mCurrentMarker));
            mBaiduMap.setMyLocationEnabled(true);
            // 定位初始化
            mLocClient = new LocationClient(this);
            mLocClient.registerLocationListener(myListener);
            LocationClientOption option = new LocationClientOption();
            option.setIsNeedAddress(true);
            option.setOpenGps(true); // 打开gps
            option.setCoorType("bd09ll"); // 设置坐标类型
            option.setScanSpan(1000);
            mLocClient.setLocOption(option);
            mLocClient.start();
            ProgressDialogUtil.dismiss();
        }else {
            Toast.makeText(this, "暂无网络", Toast.LENGTH_SHORT).show();
        }
    }

    //初始化
    private void initialize() {
        ivuser = (ImageView) findViewById(R.id.iv_user);//个人中心
        tvcity = (TextView) findViewById(R.id.tv_city);//城市名
        lycity = (LinearLayout) findViewById(R.id.ly_city);//城市布局
        ivsearch = (ImageView) findViewById(R.id.iv_search);//搜索图标
        ivscan = (ImageView) findViewById(R.id.iv_scan);//二维码图标
        rbzuche = (RadioButton) findViewById(R.id.rb_zuche);//租车图标
        rbchongdian = (RadioButton) findViewById(R.id.rb_chongdian);//充电图标
        rbtingche = (RadioButton) findViewById(R.id.rb_tingche);//停车图标
        rbdingdan = (RadioButton) findViewById(R.id.rb_dingdan);//订单图标
        iv_map=(ImageView) findViewById(R.id.iv_map);

        mMapView = (MapView) findViewById(R.id.bmapView);

        //初始化定位地图
        baidu();

        // 城市点击
        lycity.setOnClickListener(this);
        //用户中心
        ivuser.setOnClickListener(this);
        //订单
        rbdingdan.setOnClickListener(this);
        //二维码
        ivscan.setOnClickListener(this);
        iv_map.setOnClickListener(this);
        //租车
        rbzuche.setOnCheckedChangeListener(this);
        //充电
        rbchongdian.setOnCheckedChangeListener(this);
        //停车
        rbtingche.setOnCheckedChangeListener(this);
        //搜索
        ivsearch.setOnClickListener(this);


    }

    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_user:
                Intent mintent1 = new Intent(this, MyHomeActivity.class);
                startActivity(mintent1);
                break;
            case R.id.ly_city:
                Intent mintent2 = new Intent(this, CityActivity.class);
                String st = tvcity.getText().toString();
                mintent2.putExtra("city", st);
                startActivityForResult(mintent2, 1);
                break;
            case R.id.iv_search:

                Intent intent3 = new Intent(this, SearchActivity.class);
                name = tvcity.getText().toString();
                intent3.putExtra("type", search_type);
                intent3.putExtra("lat",lat);
                intent3.putExtra("lon",lon);
                System.out.println("类型" + search_type+"lat"+lat+"lon"+lon);
                intent3.putExtra("city", name);

                startActivity(intent3);
                break;
            case R.id.iv_scan:
                checkPression();
                break;
            case R.id.iv_map:
                LatLng l = new LatLng(lat, lon);
                //设置定位到屏幕中心
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(l, 15);
                mBaiduMap.setMapStatus(mapStatusUpdate);
                break;
            case R.id.rb_dingdan:
                rbzuche.setChecked(true);
                IntentUtils.intentClass(MainActivity.this, DingdanActivity.class);
                break;
            case R.id.start_img:
                Intent i = new Intent(this, DiaAcativity.class);
                i.putExtra("linkUrl", linkUrl);
                i.putExtra("subject", subject);
                dia.dismiss();
                startActivity(i);
                break;
            case R.id.iv_close:
                dia.dismiss();
                break;
        }
    }

    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
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
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_zuche:

                if (rbzuche.isChecked()) {
                    search_type=1;
                    getZucheMsg();
                }
                break;
            case R.id.rb_chongdian:

                if (rbchongdian.isChecked()) {
                    search_type=2;
                    getChongdianMsg();
                }
                break;
            case R.id.rb_tingche:

                if (rbtingche.isChecked()) {
                    search_type=3;
                    getTingcheMsg();
                }
                break;
        }
    }

    //百度地图定位配置
    public class MyLocationListenner implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {

            lat = location.getLatitude();
            lon = location.getLongitude();
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            Log.d("name:", location.getCity() + "===");
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                name = location.getCity();
                tvcity.setText(name);
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(14.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                getZucheMsg();
            }


        }

    }


    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(MainActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        Log.e("MainActivity","onGetGeoCodeResult clear");
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
                .getLocation()));
        String strInfo = String.format("纬度：%f 经度：%f",
                result.getLocation().latitude, result.getLocation().longitude);
        Toast.makeText(MainActivity.this, strInfo, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(MainActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                    .show();
            return;
        }


        Log.e("MainActivity","onGetReverseGeoCodeResult clear");
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
                .getLocation()));
        Toast.makeText(MainActivity.this, result.getAddress(),
                Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case 2:
                if(CheckNet.checkNetworkInfo(this))  {
                    name = data.getStringExtra("city");
                    tvcity.setText(name);
                    // Geo搜索
                    mSearch.geocode(new GeoCodeOption().city(name).address(name));
                    //判断状态
                    if (rbzuche.isChecked()) {
                        getZucheMsg();
                    } else if (rbchongdian.isChecked()) {
                        getChongdianMsg();
                    } else if (rbtingche.isChecked()) {
                        getTingcheMsg();
                    }
                }else {
                    Toast.makeText(this, "暂无网络。。。", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    //检查权限（赵雷）
    public void checkPression(){

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.CAMERA)) {

                Toast.makeText(this, "请开启权限", Toast.LENGTH_SHORT).show();
                checkPression();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_CAMERA);

            }
        }else {
            IntentUtils.intentClass(MainActivity.this, CaptureActivity.class);
        }
    }

    //检查权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
    {

        if (requestCode == MY_PERMISSIONS_CAMERA)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                IntentUtils.intentClass(MainActivity.this, CaptureActivity.class);

            } else
            {
                Toast.makeText(MainActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}