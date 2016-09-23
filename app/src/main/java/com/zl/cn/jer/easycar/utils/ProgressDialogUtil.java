package com.zl.cn.jer.easycar.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.zl.cn.jer.easycar.R;


/**
 * Created by Administrator on 2016/9/2.
 */

public class ProgressDialogUtil {

    private static AlertDialog dia;

    //show出弹出框
    public static void show(Context context) {

        dia = new AlertDialog.Builder(context).create();
        dia.setCanceledOnTouchOutside(false);
        View view = View.inflate(context, R.layout.alertdialog, null);
        dia.show();
        dia.getWindow().setContentView(view);


        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();

        Window alertWindow = dia.getWindow();
        WindowManager.LayoutParams lParams = alertWindow.getAttributes();
        lParams.height = (int) (width * 0.5);
        lParams.width = (int) (width * 0.75);
        lParams.alpha = 1.0f;      //设置本身透明度
        lParams.dimAmount = 0.6f;      //设置黑暗度

        alertWindow.setAttributes(lParams);


        ImageView iv = (ImageView) view.findViewById(R.id.alertdialog_iv_progress);
        iv.setImageResource(R.drawable.progressdialog);
        AnimationDrawable animationDrawable = (AnimationDrawable) iv.getDrawable();
        animationDrawable.start();


    }

    //关闭弹出框
    public static void dismiss() {
        dia.dismiss();
    }
}
