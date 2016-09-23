package com.zl.cn.jer.easycar.activity;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zl.cn.jer.easycar.net.VolleyUtil;
import com.zl.cn.jer.easycar.utils.CheckNet;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by lenovo on 2016/9/2.
 */

public class App extends Application {
    public static boolean FLAG=false;
    public  static  String USERTOKEN=null;//token
    public  static  int Cus=1;//id
    public  static String Phone;
    public static String state = "未认证";
    public static String carStatus = "未认证";
    public static int mVpId;
    public static String mVpName;
    @Override
    public void onCreate() {
        super.onCreate();


        FLAG=CheckNet.checkNetworkInfo(this);

        SDKInitializer.initialize(getApplicationContext());
        VolleyUtil.initialize(this);
        getLoader();
            //极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(getApplicationContext());

    }

 public void getLoader() {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(this);
        builder.memoryCacheExtraOptions(480, 800);
        builder.threadPoolSize(3);
        ImageLoaderConfiguration ic = builder.build();
        ImageLoader loader = ImageLoader.getInstance();
        loader.init(ic);

    }

}
