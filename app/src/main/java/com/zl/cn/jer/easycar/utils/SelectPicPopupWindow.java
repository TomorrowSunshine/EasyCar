package com.zl.cn.jer.easycar.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.cn.jer.easycar.R;

import java.io.File;
import java.net.URISyntaxException;

/**
 *
 */

public class SelectPicPopupWindow extends PopupWindow {
    private TextView tvBaidu, tvGaode, tvCancel;
    private View mMenuView;

    public SelectPicPopupWindow(final Activity context, int id, final String startLat, final String startLng, final String endLat, final String endLng) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popupwindow2, null);
        tvBaidu = (TextView) mMenuView.findViewById(R.id.pop_tv_baidu);
        tvGaode = (TextView) mMenuView.findViewById(R.id.pop_tv_gaode);
        tvCancel = (TextView) mMenuView.findViewById(R.id.pop_tv_cancel);
        //取消按钮
        tvCancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //销毁弹出框
                dismiss();
            }
        });
        //设置按钮监听
        tvBaidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isInstallByread("com.baidu.BaiduMap")) {
                    Intent intent = null;
                    try {
                        intent = Intent.getIntent("intent://map/direction?origin=latlng:" + startLat + "," + startLng + "|name:我的位置&destination=latlng:" + endLat + "," + endLng + "|name:目的地&mode=driving&src=易开租车#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                    } catch (URISyntaxException e) {

                    }
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "您还没有安装百度地图", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvGaode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isInstallByread("com.autonavi.minimap")) {
                    Intent intent = null;
                    try {
                        intent = Intent.getIntent("androidamap://navi?sourceApplication=易开租车&lat=" + endLat + "&lon=" + endLng + "&dev=0");

                    } catch (URISyntaxException e) {

                    }
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "您还没有安装高德地图", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
       // this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //显示窗口
        this.showAtLocation(context.findViewById(id), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0,
                0); //设置layout在PopupWindow中显示的位置
    }

    public boolean isInstallByread(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }
}
