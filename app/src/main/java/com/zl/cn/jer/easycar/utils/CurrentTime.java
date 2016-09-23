package com.zl.cn.jer.easycar.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2016/9/6.
 */

public class CurrentTime {
    // 获取当天时间
    public final static String getNowTime() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
        String timer = dateFormat.format(now);
        return timer;
    }
}
