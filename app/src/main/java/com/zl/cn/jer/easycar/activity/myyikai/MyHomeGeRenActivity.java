package com.zl.cn.jer.easycar.activity.myyikai;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.App;
import com.zl.cn.jer.easycar.activity.MyHomeActivity;
import com.zl.cn.jer.easycar.net.HttpNet;
import com.zl.cn.jer.easycar.net.ResponseListener;
import com.zl.cn.jer.easycar.utils.IntentUtils;
import com.zl.cn.jer.easycar.utils.MyGetPost;
import com.zl.cn.jer.easycar.utils.YanZhengUtile;

import java.util.Map;

/*
* @个人中心
* @赵雷
*
* */

public class MyHomeGeRenActivity extends AppCompatActivity implements View.OnClickListener,TextWatcher {


    private ImageView usergerenback;
    private TextView userhomesave;
    private EditText etuserhomegerenemal;
    private EditText etuserhomegerenhangye;
    private EditText etuserhomegerebjiuzhi;
    private EditText etuserhomegerenadds;
    private TextView usergerenphone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_ge_ren);

        initialize();
        String num = App.Phone;
        String str=num.substring(0,num.length()-(num.substring(3)).length())+"****"+num.substring(7);
        usergerenphone.setText(str);
        //设置输入框监听
        etuserhomegerenemal.addTextChangedListener(this);
        etuserhomegerenhangye.addTextChangedListener(this);
        etuserhomegerebjiuzhi.addTextChangedListener(this);
        etuserhomegerenadds.addTextChangedListener(this);
        //取值
        SharedPreferences  spff=getSharedPreferences("value",MODE_PRIVATE);
        String user_email = spff.getString("email", "");
        String user_hang = spff.getString("hang", "");
        String user_gong = spff.getString("gong", "");
        String user_address = spff.getString("uaddress", "");
        //为控件赋值
        etuserhomegerenemal.setText(user_email);
        etuserhomegerenhangye.setText(user_hang);
        etuserhomegerebjiuzhi.setText(user_gong);
        etuserhomegerenadds.setText(user_address);
        userhomesave.setVisibility(View.INVISIBLE);
    }

    private void initialize() {

        usergerenback = (ImageView) findViewById(R.id.user_geren_back);
        userhomesave = (TextView) findViewById(R.id.user_home_save);
        etuserhomegerenemal = (EditText) findViewById(R.id.et_user_home_geren_emal);
        etuserhomegerenhangye = (EditText) findViewById(R.id.et_user_home_geren_hangye);
        etuserhomegerenadds = (EditText) findViewById(R.id.et_user_home_geren_adds);
        etuserhomegerebjiuzhi = (EditText) findViewById(R.id.et_user_home_gereb_jiuzhi);
        usergerenphone = (TextView) findViewById(R.id.user_geren_phone);
        usergerenback.setOnClickListener(this);
        userhomesave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_geren_back:
                IntentUtils.intentClass(this, MyHomeActivity.class);
                break;
            case R.id.user_home_save:
                if (etuserhomegerenemal.getText().toString().isEmpty()){
                    Toast.makeText(this, "请输入电子邮件", Toast.LENGTH_SHORT).show();
                }else if (etuserhomegerenhangye.getText().toString().isEmpty()){
                    Toast.makeText(this, "请输入所在的行业", Toast.LENGTH_SHORT).show();
                }else if (etuserhomegerenhangye.getText().toString().isEmpty()){
                    Toast.makeText(this, "请输入就职公司", Toast.LENGTH_SHORT).show();
                }else if (etuserhomegerenhangye.getText().toString().isEmpty()){
                    Toast.makeText(this, "请输入就职公司", Toast.LENGTH_SHORT).show();
                }
                if (YanZhengUtile.isEmail(etuserhomegerenemal.getText().toString())){
                    getMessageData();
                    String useremail = etuserhomegerenemal.getText().toString();
                    String hang = etuserhomegerenhangye.getText().toString();
                    String gong = etuserhomegerebjiuzhi.getText().toString();
                    String uaddress = etuserhomegerenadds.getText().toString();
                    //获得SharedPreferences对象
                    SharedPreferences spf = getSharedPreferences("value", MODE_PRIVATE);
                    //获得编辑器
                    SharedPreferences.Editor edit = spf.edit();
                    //保存数据
                    edit.putString("email", useremail);
                    edit.putString("hang", hang);
                    edit.putString("gong", gong);
                    edit.putString("uaddress", uaddress);
                    edit.commit();
                    //跳转
                    IntentUtils.intentClass(this, MyHomeActivity.class);
                }else {
                    Toast.makeText(this, "电子邮件格式有误", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        userhomesave.setVisibility(View.VISIBLE);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    //数据
    private void getMessageData() {
        String url = "https://api.eakay.cn/user-api/api/user/info/updateUserCommonInfo";
        Map<String, String> map = MyGetPost.getMap("userType", "1","customerId", String.valueOf(App.Cus), "phone", App.Phone,"user_token", App.USERTOKEN,"email",etuserhomegerenemal.getText().toString(),"industry",etuserhomegerenhangye.getText().toString(),"workUnit",etuserhomegerebjiuzhi.getText().toString(),
                "address",etuserhomegerenadds.getText().toString());
        HttpNet.postObjectMinongApi(url, map, new ResponseListener() {
            @Override
            public void onErrorResponse(VolleyError volleyEror) {
                Toast.makeText(MyHomeGeRenActivity.this, volleyEror.toString(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onResponse(String s) {
                Toast.makeText(MyHomeGeRenActivity.this,"修改成功",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
