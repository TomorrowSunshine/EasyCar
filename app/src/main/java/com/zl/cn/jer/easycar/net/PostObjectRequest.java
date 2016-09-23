package com.zl.cn.jer.easycar.net;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by lenovo on 2016/9/6.
 */

public class PostObjectRequest extends Request<String> {
    private ResponseListener mListener;
    private Map<String, String> mParams;

    public PostObjectRequest(String url, Map<String, String> params, ResponseListener listener) {
        super(Method.POST, url, listener);
        this.mListener = listener;
        setShouldCache(false);
        mParams = params;
    }

    /**
     * 这里开始解析数据
     *
     * @param networkResponse Response from the network
     * @return
     */
    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String result;

            String jsonString =
                    new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            Log.v("zgy", "====jsonString===" + jsonString);
            result = jsonString;
            return Response.success(result,
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {

            return Response.error(new ParseError(e));
        }
    }


      


    @Override
    protected void deliverResponse(String s) {
        mListener.onResponse(s);
    }

    //关键代码就在这里，在 Volley 的网络操作中，如果判断请求方式为 Post 则会通过此方法来获取 param，所以在这里返回我们需要的参数，
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return mParams;
    }
}
