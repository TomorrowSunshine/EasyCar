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
import com.zl.cn.jer.easycar.bean.StopParking;
import com.zl.cn.jer.easycar.utils.BaiduDw;

import java.text.DecimalFormat;
import java.util.List;

import static com.baidu.navisdk.adapter.BNRoutePlanNode.CoordinateType.WGS84;

/**
 * Created by wanghongra on 2016/9/8.
 */

public class SearchParking extends android.widget.BaseAdapter {


    private final Context context;
    private final List<StopParking.ListBean> list;
    private final String str;
    private final double lon;
    private final double lat;
    int leng;
    int index;
    int type;
    private double latend;
    private double lngend;

    public SearchParking(Context context, List<StopParking.ListBean> list,String str,double lat,double lon) {
        this.context=context;
        this.list=list;
        this.str=str;
        this.lon=lon;
        this.lat=lat;
    }

    @Override
    public int getCount() {
        return list.size();
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
        view = View.inflate(context, R.layout.searchitem3, null);
        TextView address= (TextView) view.findViewById(R.id.parking_name);
        TextView  kestop = (TextView) view.findViewById(R.id.parking_stop);

        TextView  km = (TextView) view.findViewById(R.id.parking_km);
        TextView  juti = (TextView) view.findViewById(R.id.parking_juti);

        Button daohang= (Button) view.findViewById(R.id.daohang);



        daohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BaiduDw bd=new BaiduDw(context);
                bd.routeplanToNavi(lon,lat,lngend,latend,WGS84);

                //  routeplanToNavi(lon,lat,lngend,latend,WGS84);
            }
        });

        latend = list.get(i).getLat();
        lngend = list.get(i).getLng();
        address.setText(list.get(i).getSiteName());
        kestop.setText(list.get(i).getNum()+"可停车");
        DecimalFormat df = new DecimalFormat("###.0");

        km.setText(df.format(list.get(i).getDistance()/1000)+"km");
        if (list.get(i).getAddress().length()>16){
            juti.setText(list.get(i).getAddress().replace(list.get(i).getAddress().substring(16),"..."));
        }else {
            juti.setText(list.get(i).getAddress());
        }

        SpannableStringBuilder builder = new SpannableStringBuilder(list.get(i).getSiteName());
        ForegroundColorSpan blueSpan = new ForegroundColorSpan(Color.BLUE);


        if (str != null) {
            index = list.get(i).getSiteName().indexOf(str);
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
