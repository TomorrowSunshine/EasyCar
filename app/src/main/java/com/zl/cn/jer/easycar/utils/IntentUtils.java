package com.zl.cn.jer.easycar.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by admin on 2016/9/1.
 * Intent跳转工具类
 */
public class IntentUtils {

    public static void intentClass(Context context,Class c){
        Intent intent = new Intent(context,c);
        context.startActivity(intent);
    }


}
