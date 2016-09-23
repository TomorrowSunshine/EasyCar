package com.zl.cn.jer.easycar.utils;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2016/9/1.
 */
public class GsonUtils {
    private static GsonUtils gsonUtils = null;

    private GsonUtils(){

    }

    public synchronized static GsonUtils getInstance(){
        if(gsonUtils == null){
            gsonUtils = new GsonUtils();
        }
        return gsonUtils;
    }

    public static<T> T jsonToObject(String json,Class<T> t){
        if(json == null){
            return null;
        }
        return new Gson().fromJson(json,t);
    }

}
