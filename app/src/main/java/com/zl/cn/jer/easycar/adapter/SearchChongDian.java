package com.zl.cn.jer.easycar.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.bean.ChongDian;
import com.zl.cn.jer.easycar.utils.BaiduDw;

import java.text.DecimalFormat;
import java.util.List;

import static com.baidu.navisdk.adapter.BNRoutePlanNode.CoordinateType.WGS84;

/**
 * Created by wanghongra on 2016/9/8.
 */

public class SearchChongDian extends android.widget.BaseAdapter {

    private final Context context;
    private final List<ChongDian.SiteListBean> siteList;
    private final String str;
    private final double lat;
    private final double lon;
    int index;
    int leng;
    int type;
    private double lngend;
    private double latend;

    public SearchChongDian(Context context, List<ChongDian.SiteListBean> siteList, String str,double lat,double lon) {
        this.context = context;
        this.siteList = siteList;
        this.str = str;
        this.lat=lat;
        this.lon=lon;

    }


    @Override
    public int getCount() {
        return siteList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.searchitem2, null);
        TextView address = (TextView) view.findViewById(R.id.chongdian_name);
        TextView kechong = (TextView) view.findViewById(R.id.search_chong);

        TextView km = (TextView) view.findViewById(R.id.chong_km);
        TextView juti = (TextView) view.findViewById(R.id.chongdian_juti);

        Button daohang = (Button) view.findViewById(R.id.daohang);
        daohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaiduDw bd=new BaiduDw(context);
                bd.routeplanToNavi(lon,lat,lngend,latend,WGS84);
            }
        });

        lngend = siteList.get(i).getLng();
        latend = siteList.get(i).getLat();

        address.setText(siteList.get(i).getSiteName());
        kechong.setText(siteList.get(i).getFreeCounts() + "辆充电");

        DecimalFormat df = new DecimalFormat("###.0");

        km.setText(df.format(siteList.get(i).getDistance()/1000)+"km");
        if (siteList.get(i).getAddress().length()>16){
            juti.setText(siteList.get(i).getAddress().replace(siteList.get(i).getAddress().substring(14),"..."));
        }else{
            juti.setText(siteList.get(i).getAddress());

        }



        SpannableStringBuilder builder = new SpannableStringBuilder(siteList.get(i).getSiteName());
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);


        if (str != null) {
            index = siteList.get(i).getSiteName().indexOf(str);
            if (index == -1) {
                index = 0;
            }
            leng = str.length();
        }
        builder.setSpan(blueSpan, index, (index + leng), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        address.setText(builder);

        return view;
    }

}
