package com.zl.cn.jer.easycar.net;

import com.android.volley.Request;

import java.util.Map;

/**
 * Created by lenovo on 2016/9/6.
 */

public class HttpNet {

    public static void postObjectMinongApi(String url, Map<String,String> param, ResponseListener listener){

        Request request = new PostObjectRequest(url,param,listener);
        VolleyUtil.getRequestQueue().add(request) ;
    }
}
