package com.zl.cn.jer.easycar.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by lenovo on 2016/9/19.
 */

public class CheckNet {
    public Context context;

    public static  boolean checkNetworkInfo(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (mobile ==  NetworkInfo.State.CONNECTED || mobile ==  NetworkInfo.State.CONNECTING)
            return true;
        if (wifi ==  NetworkInfo.State.CONNECTED || wifi ==  NetworkInfo.State.CONNECTING)
            return true;
        return false;
    }


}
