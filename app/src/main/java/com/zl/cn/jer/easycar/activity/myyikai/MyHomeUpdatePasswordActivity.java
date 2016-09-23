package com.zl.cn.jer.easycar.activity.myyikai;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.App;
import com.zl.cn.jer.easycar.activity.LoginActivity;
import com.zl.cn.jer.easycar.bean.UpdatePasswordBean;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.IntentUtils;
import com.zl.cn.jer.easycar.utils.MyGetPost;
import com.zl.cn.jer.easycar.utils.SecurityUtils;

import java.util.Map;

public class MyHomeUpdatePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView userhomeupdateback;
    private EditText etuserhomeupdateold;
    private LinearLayout userhomeupdatepassword;
    private EditText etuserhomeupdatenew;
    private EditText etuserhomeupdatenewquding;
    private Button btnupdatecomit;
    private RelativeLayout activitymyhomeupdatepassword;
    private StringRequest request;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_update_password);
        initialize();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void initData() {
        final String oldps = etuserhomeupdateold.getText().toString();
        final String newps = etuserhomeupdatenew.getText().toString();

        //私有
        String url = "https://api.eakay.cn/user-api/api/user/changePassword";

        Map<String, String> map = MyGetPost.getMap("password", SecurityUtils.MD516(newps), "oldPassword", SecurityUtils.MD516(oldps), "userType", "1", "customerId", String.valueOf(App.Cus), "phone", App.Phone, "user_token", App.USERTOKEN);
        HttpNet.postObjectMinongApi(url, map, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MyHomeUpdatePasswordActivity.this, "请求错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String s) {
                Toast.makeText(MyHomeUpdatePasswordActivity.this, "s", Toast.LENGTH_SHORT).show();
                Log.d("TAG", s);
                Gson g = new Gson();
                if (g.fromJson(s, UpdatePasswordBean.class).getMsg().getCode() == 0) {
                    Toast.makeText(MyHomeUpdatePasswordActivity.this, g.fromJson(s, UpdatePasswordBean.class).getMsg().getInfo(), Toast.LENGTH_SHORT).show();
                    IntentUtils.intentClass(MyHomeUpdatePasswordActivity.this, LoginActivity.class);
                } else {
                    Toast.makeText(MyHomeUpdatePasswordActivity.this, "原密码输入有误", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void initialize() {

        userhomeupdateback = (ImageView) findViewById(R.id.user_home_update_back);
        etuserhomeupdateold = (EditText) findViewById(R.id.et_user_home_update_old);
        userhomeupdatepassword = (LinearLayout) findViewById(R.id.user_home_update_password);
        etuserhomeupdatenew = (EditText) findViewById(R.id.et_user_home_update_new);
        etuserhomeupdatenewquding = (EditText) findViewById(R.id.et_user_home_update_new_quding);
        btnupdatecomit = (Button) findViewById(R.id.btn_update_comit);
        activitymyhomeupdatepassword = (RelativeLayout) findViewById(R.id.activity_my_home_update_password);
        userhomeupdateback.setOnClickListener(this);
        btnupdatecomit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_home_update_back:
                IntentUtils.intentClass(this, MyHomeMoreActivity.class);
                break;
            case R.id.btn_update_comit:
                if (etuserhomeupdateold.getText().equals("")) {
                    Toast.makeText(this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                } else if (etuserhomeupdatenew.getText().toString().equals("")) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (etuserhomeupdatenew.getText().toString().equals("")) {
                    Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
                } else if (etuserhomeupdatenew.getText().toString().equals(etuserhomeupdatenewquding.getText().toString())) {
                    initData();
                } else {
                    Toast.makeText(this, "新密码与确认密码不一致", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("MyHomeUpdatePassword Page") // TODO: Define a title for the content shown.
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
}
