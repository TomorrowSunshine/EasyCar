package com.zl.cn.jer.easycar.utils;

import com.zl.cn.jer.easycar.activity.App;

/**
 * Created by JER on 2016/9/5.
 * html页面地址
 */

public class HtmlWebURL {
    //H5地址-------------------
    public static final String HOST=  "https://api.eakay.cn/";
    public static final String WEB_PREFIX = "http://h5.eakay.cn/";
    public static final String WEB_SHOP = HOST + "order-api/web/" + "activity/mall.htm?customerId="+App.Cus+"&userToken="+App.USERTOKEN; // 易开商城
    public static final String WEB_MERCHANT_CONTRACT = WEB_PREFIX + "eakayApp/views/agreement/merchantsDeal.html";//租车时商家协议
    public static final String WEB_TERM_OF_SERVICE = WEB_PREFIX + "eakayApp/views/agreement/useTerms.html";//“易开租车”使用条款 (在注册页中)
    public static final String WEB_REWARDS_FOR_INVITATION = WEB_PREFIX + "eakayApp/yqyj.html?customerId="+App.Cus; // 邀请有奖
    public static final String WEB_SEARCH_VIOLATION = WEB_PREFIX + "eakayApp/peccancyList.html?customerId="+App.Cus+"&userToken="+App.USERTOKEN+"&userType=1"; // 违章查询
    public static final String WEB_MESSAGE_CENTER = WEB_PREFIX + "eakayApp/hdmessage.html"; // 消息中心
    public static final String WEB_COUPON_RULES = WEB_PREFIX + "eakayApp/couponRules.html";//优惠劵使用规则


    public static final String WEB_INVOICE = HOST + "order-api/web/" + "invoice/home.htm?customerId="+ App.Cus;//电子发票
    public static final String WEB_SERVICE = WEB_PREFIX + "eakayApp/views/service/index.html";//服务专区
    public static final String WEB_ABOUT_EAKAY = WEB_PREFIX + "eakayApp/index.html";//关于易开
    public static final String WEB_SAVINGS = WEB_PREFIX + "eakayApp/savings.html";//储蓄信息
    public static final String WEB_MARGIN_TIPS = WEB_PREFIX + "eakayApp/topup.html";//押金信息
    public static final String JSON_PRICE = HOST + "order-api/api/Account/findNewAllPrePaidDeposit.htm";

}
