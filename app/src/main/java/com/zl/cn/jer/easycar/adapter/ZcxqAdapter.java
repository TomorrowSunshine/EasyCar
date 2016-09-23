package com.zl.cn.jer.easycar.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.com.ortiz.touch.ViewPagerExampleActivity;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.bean.ZuCheXiangqingBean;

import java.util.List;

import static android.R.attr.id;

/**
 * Created by wanghongra on 2016/9/13.
 */

public class ZcxqAdapter extends PagerAdapter {
    private final List<ZuCheXiangqingBean.ListBean> zu_xq_list;
    private final int carid;
    private final String city_name;
    private Context context;
    private Handler handler;

    public ZcxqAdapter(Context context, List<ZuCheXiangqingBean.ListBean> zu_xq_list, Handler handler,int carid,String city_name) {


        this.context=context;
        this.zu_xq_list=zu_xq_list;
        this.handler=handler;
        this.carid=carid;
        this.city_name=city_name;
    }

    @Override
    public int getCount() {

        //给它一个最大值
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {


        return arg0==arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        //加载布局
        View view=View.inflate(context, R.layout.zcxqitem, null);

        //找到控件
        final ImageView vp_item_image=(ImageView) view.findViewById(R.id.zcxq_im);

        //给控件添加图片
      //  ImageLoader.getInstance().displayImage(zu_xq_list.get(position%zu_xq_list.size()).img, vp_item_image);


        RequestQueue mQueue = Volley.newRequestQueue(context);
        ImageRequest imageRequest = new ImageRequest(
                zu_xq_list.get(position%zu_xq_list.size()).getFastdfsUrl(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        vp_item_image.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                vp_item_image.setImageResource(R.drawable.default_placeholder_img);
            }
        });
        mQueue.add(imageRequest);



        vp_item_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"图片点击事件",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, ViewPagerExampleActivity.class);
                        intent.putExtra("id", id);
                        intent.putExtra("carid", carid);
                        intent.putExtra("city_name", city_name);
                        context.startActivity(intent);
            }
        });


        //加载view
        container.addView(view);

        return view;
    }

}
