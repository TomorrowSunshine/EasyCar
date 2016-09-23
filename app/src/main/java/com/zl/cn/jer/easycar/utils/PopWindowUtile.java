package com.zl.cn.jer.easycar.utils;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.cn.jer.easycar.R;

/*
* @popwin弹出菜单
* @赵雷
*
* */
public class PopWindowUtile {

        public PopWindowUtile(){

        }

        private static PopupWindow mPopWindow;


        public static void showPopupWindow(String pop_name, String pop_value, final Context context, ImageView btn, int w, final Class c1, final Class c2) {
                //设置contentView
                View contentView = LayoutInflater.from(context).inflate(R.layout.popwindow, null);
                mPopWindow = new PopupWindow(contentView,
                        LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
                mPopWindow.setHeight(LayoutParams.WRAP_CONTENT);

                mPopWindow.setWidth(w);
                mPopWindow.setFocusable(true);
                mPopWindow.setBackgroundDrawable(new BitmapDrawable());
                mPopWindow.setContentView(contentView);
                //设置各个控件的点击响应
                TextView tv1 = (TextView)contentView.findViewById(R.id.pop_computer);
                TextView tv2 = (TextView)contentView.findViewById(R.id.pop_financial);
                tv1.setText(pop_name);
                tv2.setText(pop_value);
                tv1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                                mPopWindow.dismiss();
                                IntentUtils.intentClass(context,c1);
                        }
                });
                tv2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
                                mPopWindow.dismiss();
                                IntentUtils.intentClass(context,c2);

                        }
                });

                mPopWindow.showAsDropDown(btn, 0,-btn.getHeight());

        }
}
