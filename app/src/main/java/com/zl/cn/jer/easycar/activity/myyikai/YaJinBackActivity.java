package com.zl.cn.jer.easycar.activity.myyikai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.activity.MyHomeActivity;
import com.zl.cn.jer.easycar.utils.IntentUtils;

public class YaJinBackActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView userhomeyajinbackback;
    private CheckBox yabjinbackcheliang;
    private CheckBox yabjinbackchongdian;
    private CheckBox yabjinbackzishen;
    private CheckBox yabjinbackohter;
    private CheckBox yabjinbackxuhang;
    private CheckBox yabjinbackzuhuo;
    private CheckBox yabjinbackfuwu;
    private EditText yanjinbackyijian;
    private Button yajinbackcomti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ya_jin_back);
        initialize();
    }

    private void initialize() {

        userhomeyajinbackback = (ImageView) findViewById(R.id.user_home_yajin_back_back);
        yabjinbackcheliang = (CheckBox) findViewById(R.id.yabjin_back_cheliang);
        yabjinbackchongdian = (CheckBox) findViewById(R.id.yabjin_back_chongdian);
        yabjinbackzishen = (CheckBox) findViewById(R.id.yabjin_back_zishen);
        yabjinbackohter = (CheckBox) findViewById(R.id.yabjin_back_ohter);
        yabjinbackxuhang = (CheckBox) findViewById(R.id.yabjin_back_xuhang);
        yabjinbackzuhuo = (CheckBox) findViewById(R.id.yabjin_back_zuhuo);
        yabjinbackfuwu = (CheckBox) findViewById(R.id.yabjin_back_fuwu);
        yanjinbackyijian = (EditText) findViewById(R.id.yanjin_back_yijian);
        yajinbackcomti = (Button) findViewById(R.id.yajin_back_comti);
        userhomeyajinbackback.setOnClickListener(this);
        yajinbackcomti.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_home_yajin_back_back:
                IntentUtils.intentClass(this, MyHomeActivity.class);
                break;
            case R.id.yajin_back_comti:
                Toast.makeText(this, "已提交", Toast.LENGTH_SHORT).show();
                IntentUtils.intentClass(this, MyHomeActivity.class);
                break;
        }
    }
}
