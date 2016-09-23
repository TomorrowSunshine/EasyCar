package com.zl.cn.jer.easycar.activity.myyikai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.MyHomeActivity;
import com.zl.cn.jer.easycar.bean.YajinBean;
import com.zl.cn.jer.easycar.utils.HtmlWebURL;
import com.zl.cn.jer.easycar.utils.IntentUtils;
import com.zl.cn.jer.easycar.utils.PopWindowUtile;

import java.util.List;

/*
*
* @交押金
* @赵雷
*
* */

public class MyHomeYaJinActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView yajinwb;
    private RelativeLayout activitymyhomeyajin;
    private ImageView userhomeyajinback;
    private ImageView btnyajinmore;
    private LinearLayout linearLayout;
    private View depositdivider1;
    private Button recbtnpayment;
    private TextView deptvname1;
    private TextView deptvprice1;
    private TextView deptvdesc;
    private TextView deptvname2;
    private TextView deptvprice2;
    private TextView deptvname3;
    private TextView deptvprice3;
    private LinearLayout deplinearselect1;
    private LinearLayout deplinearselect2;
    private LinearLayout deplinearselect3;
    private LinearLayout deplinear;
    private YajinBean yajinBean;
    private List<YajinBean.ListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_ya_jin);
        initialize();
        deplinearselect1.setSelected(true);

        initDate();
        initUI();

    }

    private void initUI() {

    }

    //请求数据
    private void initDate() {

        // 1 创建RequestQueue对象
        RequestQueue mRequestQueue;

        StringRequest mStringRequest;
        mRequestQueue = Volley.newRequestQueue(MyHomeYaJinActivity.this);

        // 2 创建StringRequest对象

        mStringRequest = new StringRequest(HtmlWebURL.JSON_PRICE,
                new Response.Listener<String>() {

                    @Override

                    public void onResponse(String response) {
                        System.out.println("请求结果:" + response);
                        Gson g = new Gson();
                        list = g.fromJson(response, YajinBean.class).getList();
                        deplinear.setVisibility(View.VISIBLE);
                        for (int i = 0; i < 3; i++) {
                            if (i == 0) {
                                deptvname1.setText(list.get(i).getName() + "\n" + list.get(i).getDescription());
                                deptvprice1.setText("￥" + list.get(i).getPrice().substring(0, 4));
                            } else if (i == 1) {
                                deptvname2.setText(list.get(i).getName());
                                deptvprice2.setText("￥" + list.get(i).getPrice().substring(0, 4));
                            } else {
                                deptvname3.setText(list.get(i).getName());
                                deptvprice3.setText("￥" + list.get(i).getPrice().substring(0, 2));
                            }
                        }
                    }
                }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

                System.out.println("请求错误:" + error.toString());

            }

        });

        mRequestQueue.add(mStringRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_home_yajin_back:
                IntentUtils.intentClass(this, MyHomeActivity.class);
                break;
            case R.id.btn_yajin_more:
                PopWindowUtile.showPopupWindow("押金明细", "押金返还", MyHomeYaJinActivity.this, btnyajinmore, getWindowManager().getDefaultDisplay().getWidth() / 2, JiaoYiJiluActivity.class, YaJinBackActivity.class);
                break;
            case R.id.dep_linear_select1:
                Toast.makeText(this, list.get(0).getName(), Toast.LENGTH_SHORT).show();
                deplinearselect1.setSelected(true);
                deplinearselect2.setSelected(false);
                deplinearselect3.setSelected(false);

                break;
            case R.id.dep_linear_select2:
                Toast.makeText(this, list.get(1).getName(), Toast.LENGTH_SHORT).show();
                deplinearselect2.setSelected(true);
                deplinearselect1.setSelected(false);
                deplinearselect3.setSelected(false);
                break;
            case R.id.dep_linear_select3:
                Toast.makeText(this, list.get(2).getName(), Toast.LENGTH_SHORT).show();
                deplinearselect3.setSelected(true);
                deplinearselect2.setSelected(false);
                deplinearselect1.setSelected(false);
                break;
            case R.id.rec_btn_payment:
                Toast.makeText(this, "功能未实现~", Toast.LENGTH_SHORT).show();
                break;
        }

    }


    private void initialize() {

        userhomeyajinback = (ImageView) findViewById(R.id.user_home_yajin_back);
        btnyajinmore = (ImageView) findViewById(R.id.btn_yajin_more);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        depositdivider1 = (View) findViewById(R.id.deposit_divider1);
        recbtnpayment = (Button) findViewById(R.id.rec_btn_payment);
        deptvname1 = (TextView) findViewById(R.id.dep_tv_name1);
        deptvprice1 = (TextView) findViewById(R.id.dep_tv_price1);
        deptvdesc = (TextView) findViewById(R.id.dep_tv_desc);
        deptvname2 = (TextView) findViewById(R.id.dep_tv_name2);
        deptvprice2 = (TextView) findViewById(R.id.dep_tv_price2);
        deptvname3 = (TextView) findViewById(R.id.dep_tv_name3);
        deptvprice3 = (TextView) findViewById(R.id.dep_tv_price3);
        deplinearselect1 = (LinearLayout) findViewById(R.id.dep_linear_select1);
        deplinearselect2 = (LinearLayout) findViewById(R.id.dep_linear_select2);
        deplinearselect3 = (LinearLayout) findViewById(R.id.dep_linear_select3);

        deplinear = (LinearLayout) findViewById(R.id.dep_linear);
        deplinearselect1.setOnClickListener(this);
        deplinearselect2.setOnClickListener(this);
        deplinearselect3.setOnClickListener(this);
        btnyajinmore.setOnClickListener(this);
        recbtnpayment.setOnClickListener(this);

    }
}
