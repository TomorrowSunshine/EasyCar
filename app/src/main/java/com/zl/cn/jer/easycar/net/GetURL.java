package com.zl.cn.jer.easycar.net;

import java.util.Map;

/**
 * GET请求拼接url接口
 * Created by Administrator on 2016/9/8.
 */
public class GetURL {
    public static String getUrl(String url,Map<String, String> params) {
        String gUrl=null;
        //拼装请求参数列表
        final StringBuilder sb = new StringBuilder(64);
        if (params != null) {
            sb.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
            gUrl = url + sb.toString();
        }

        return gUrl;
    }
}
