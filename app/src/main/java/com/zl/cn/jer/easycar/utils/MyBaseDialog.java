package com.zl.cn.jer.easycar.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by lenovo on 2016/9/7.
 */

public class MyBaseDialog extends Dialog {
    private static int default_width = 320; // 默认宽度
    private static int default_height = 480;// 默认高度
    public MyBaseDialog(Context context, View layout, int style) {
        this(context, default_width, default_height, layout, style);
    }
    public MyBaseDialog(Context context, int width, int height, View layout,
                      int style) {

        super(context, style);
        setContentView(layout);
        float density = getDensity(context);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = (int) (width * density);
        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);

    }

    private float getDensity(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.density;
    }
}
