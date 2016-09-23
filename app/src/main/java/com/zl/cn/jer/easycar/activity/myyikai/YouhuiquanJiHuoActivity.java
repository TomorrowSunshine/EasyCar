package com.zl.cn.jer.easycar.activity.myyikai;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zl.cn.jer.easycar.R;
import com.zl.cn.jer.easycar.utils.IntentUtils;

public class YouhuiquanJiHuoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView userhomeyouhuiquanjihuoback;
    private EditText etyouhuiquanjihuo;
    private Button btnyuohuoquanjihuo;
    private RelativeLayout activityyouhuiquanjihuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youhuiquan_ji_huo);
        initialize();
    }

    private void initialize() {

        userhomeyouhuiquanjihuoback = (ImageView) findViewById(R.id.user_home_youhuiquan_jihuo_back);
        etyouhuiquanjihuo = (EditText) findViewById(R.id.et_youhuiquan_jihuo);
        btnyuohuoquanjihuo = (Button) findViewById(R.id.btn_yuohuoquan_jihuo);
        activityyouhuiquanjihuo = (RelativeLayout) findViewById(R.id.activity_youhuiquan_ji_huo);
        userhomeyouhuiquanjihuoback.setOnClickListener(this);
        btnyuohuoquanjihuo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_home_youhuiquan_jihuo_back:
                IntentUtils.intentClass(this, MyHomeYouHuiJuanActivity.class);
                break;
            case R.id.btn_yuohuoquan_jihuo:
                if (etyouhuiquanjihuo.getText().toString().equals("")) {
                    Toast.makeText(this, "请输入优惠券编码", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "优惠券不存在", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
