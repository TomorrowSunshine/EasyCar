package com.zl.cn.jer.easycar.utils;

import com.zl.cn.jer.easycar.activity.App;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lenovo on 2016/9/6.
 */

public class MyGetPost {

    public  static Map<String,String>  getMap(String...parmars){
        Map<String, String> mMap = new HashMap<String, String>();
        for (int i = 0; i < parmars.length; i += 2) {
            mMap.put(parmars[i], parmars[i + 1]);
        }

        mMap.put("ver", "2.4.1");
        mMap.put("client_type", "android");
        mMap.put("clientType", "android");
        mMap.put("format", "json");

        mMap.put("app_key", "car");
        mMap.put("sign_method", "md5");
        mMap.put("signMethod", "md5");
        mMap.put("appkey", "car");
        mMap.put("timestamp", CurrentTime.getNowTime());
        mMap.put("user_token", App.USERTOKEN);
        getSign(mMap);
        return mMap;
    }

    public static String getSign(Map<String, String> mMap) {
        StringBuilder sb = new StringBuilder();
        Object[] obj = mMap.keySet().toArray();
        Arrays.sort(obj);
        for (int i = 0; i < obj.length; i++) {
            sb.append(obj[i].toString() + mMap.get(obj[i].toString()));
        }
        String sign = SecurityUtils.MD5(sb.toString() + "car");
        mMap.put("sign", sign);
        return sign;
    }



}
