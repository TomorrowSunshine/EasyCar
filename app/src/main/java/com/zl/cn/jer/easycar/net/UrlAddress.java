package com.zl.cn.jer.easycar.net;

/**
 * Created by Administrator on 2016/9/4.
 */

public class UrlAddress {
    public static final String HOST = "https://api.eakay.cn";
    //首页活动
    public static final String  CAMPAIGN= "web/activity/activityTop.htm";
    //验证码
    public static final String AUTHCODE = "user-api/api/user/sendVerifyCodeForRegister";
    //城市索引
    public static final String CITY="/order-api/api/MiteSite/findAllCity.htm";

    public static final String ZUCHE="/order-api/api/MiteSite/findMiteSite.htm";
    public static final String TINGCHE="/order-api/api/park/parkList.htm";
    public static final String CHONGDIAN="/order-api/api/charge/siteList.htm";

    //气泡点击
    public  static final String QP="/order-api/api/MiteSite/findMiteSiteById.htm";
}
