package com.zl.cn.jer.easycar.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.bean.CityBean;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.net.UrlAddress;
import com.zl.cn.jer.easycar.utils.MyGetPost;

import java.util.List;
import java.util.Map;

public class CityActivity extends AppCompatActivity {

    private Button btncity;
    private GridView gvcity;
   // private String name[] = new String[]{"芜湖市", "北京市", "沧州市", "马鞍山市", "合肥市", "玉溪市", "泸州市", "武汉市", "成都市", "雅安市", "海口市", "淮南市", "鄂尔多斯市", "常州市"};
    private TextView tvcity;
    private ImageView iv_navi_back;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private List<CityBean.ListEntity> mlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        initialize();
        Intent i = getIntent();
        final String city = i.getStringExtra("city");
        tvcity.setText(city);
        btncity.setText(city);

        iv_navi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btncity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

       Map<String,String> mMap= MyGetPost.getMap("customerId",String.valueOf(App.Cus));

       HttpNet.postObjectMinongApi(UrlAddress.HOST + UrlAddress.CITY, mMap, new ResponseListener() {
           @Override
            public void onErrorResponse(VolleyError volleyError) {

               Log.d("1111",volleyError.toString());
       //        Toast.makeText(CityActivity.this, "请检查网络是否连接", Toast.LENGTH_SHORT).show();
           }

           @Override
            public void onResponse(String s) {
               Gson gson =new Gson();
               CityBean c = gson.fromJson(s, CityBean.class);
               mlist = c.getList();
               myBase();
           }
       });

    }

    private void initialize() {
        tvcity = (TextView) findViewById(R.id.tv_city1);
        btncity = (Button) findViewById(R.id.btn_city);
        gvcity = (GridView) findViewById(R.id.gv_city);
        iv_navi_back = (ImageView) findViewById(R.id.iv_navi_back);

    }

    public void myBase() {

        gvcity.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mlist.size();
            }

            @Override
            public Object getItem(int position) {
                return  mlist.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHorder horder;
                if(convertView==null) {
                    convertView = View.inflate(CityActivity.this, R.layout.item_city, null);
                    horder=new ViewHorder();
                    horder.btn=(Button)convertView.findViewById(R.id.btn_city);
                    convertView.setTag(horder);
                }else{
                    horder= (ViewHorder) convertView.getTag();
                }

              horder.btn.setText(mlist.get(position).getCity());
                horder.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mintent = new Intent();
                        mintent.putExtra("city", mlist.get(position).getCity());
                        setResult(2, mintent);
                        finish();
                    }
                });
                return convertView;
            }
        });
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("City Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
     class ViewHorder{
         Button btn;
     }
}
