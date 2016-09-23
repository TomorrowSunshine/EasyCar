package com.zl.cn.jer.easycar.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.umeng.analytics.MobclickAgent;
import com.zl.cn.jer.easycar.R;

import cn.jpush.android.api.JPushInterface;

//启动页面
public class StartActivity extends AppCompatActivity {

    private ImageView welcomeImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start);
        SharedPreferences preferences = getSharedPreferences("count",MODE_PRIVATE);
        int count = preferences.getInt("count",0);


        //判断程序是否是第一次运行
        if (count == 0){
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(),GuideActivity.class);
            startActivity(intent);
            finish();
        }
        SharedPreferences.Editor editor = preferences.edit();
        //存入数据
        editor.putInt("count",++count);
        editor.commit();

        //开机动画
        welcomeImg = (ImageView) this.findViewById(R.id.welcome_img);
        AlphaAnimation anima = new AlphaAnimation(0.3f, 1.0f);
        anima.setDuration(4000);// 设置动画显示时间
        welcomeImg.startAnimation(anima);
        anima.setAnimationListener((Animation.AnimationListener) new AnimationImpl());


    }

    @Override
    protected void onPause() {
        super.onPause();
       JPushInterface.onPause(this);//推送
        MobclickAgent.onPause(this);//统计
    }

    @Override
    protected void onResume() {
        super.onResume();
       JPushInterface.onResume(this);//推送
        MobclickAgent.onResume(this);//统计
    }

    private class AnimationImpl implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            welcomeImg.setBackgroundResource(R.drawable.bg_account);
        }

        @Override
        public void onAnimationEnd(Animation animation) {

            skip(); // 动画结束后跳转到别的页面
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }

    }

    private void skip() {
        startActivity(new Intent(StartActivity.this, MainActivity.class));
        finish();

    }
}
