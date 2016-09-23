package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by wanghongra on 2016/9/14.
 */

public class ZucheXinangQingBen2 {

    /**
     * calInfo : null
     * map : {"InsuranceOrigin":"12.20","disclaimerUrl":"http://eakay.cn/bxsm.html","riseInPriceMsg":"","isHadNightCost":false,"dayPriceRuleId":0,"hourPriceRange":0,"dayPriceRange":0,"insuranceValue":"9.00","isRiseInPrice":false,"hourPriceRuleId":0,"isInsurance":false,"InsuranceMessage":"您的驾龄未满半年，为您提供此项保险服务；您不必承担保险范围内的任何赔偿，助您安全出行！","isInsuranceShow":true}
     * list : [{"reservedMinute":30,"priceTypeTableName":"wh_hours_price_type","maxKmsForNight":"0","perHoursKmsForDay":"0","oneHourDes":"不限公里数","oneHoursCost":"15.00","endTimeForWork":"18:30:00","startTimeForDay":"00:00:00","nightDes":"不限公里数","startTimeForWork":"07:00:00","maxKmsForDay":"0","id":33,"typeName":"易开畅行","maxDays":"999","dayDes":"不限公里数","perKmsPrice":"0.00","endTimeForDay":"23:59:00","maxCostForDay":"110.00","nightCost":"0.00","minDays":"0"}]
     * isIcCalCar : false
     * msg : {"info":"获取成功!","code":0,"success":true}
     */

    private Object calInfo;
    /**
     * InsuranceOrigin : 12.20
     * disclaimerUrl : http://eakay.cn/bxsm.html
     * riseInPriceMsg :
     * isHadNightCost : false
     * dayPriceRuleId : 0
     * hourPriceRange : 0
     * dayPriceRange : 0
     * insuranceValue : 9.00
     * isRiseInPrice : false
     * hourPriceRuleId : 0
     * isInsurance : false
     * InsuranceMessage : 您的驾龄未满半年，为您提供此项保险服务；您不必承担保险范围内的任何赔偿，助您安全出行！
     * isInsuranceShow : true
     */

    private MapBean map;
    private boolean isIcCalCar;
    /**
     * info : 获取成功!
     * code : 0
     * success : true
     */

    private MsgBean msg;
    /**
     * reservedMinute : 30
     * priceTypeTableName : wh_hours_price_type
     * maxKmsForNight : 0
     * perHoursKmsForDay : 0
     * oneHourDes : 不限公里数
     * oneHoursCost : 15.00
     * endTimeForWork : 18:30:00
     * startTimeForDay : 00:00:00
     * nightDes : 不限公里数
     * startTimeForWork : 07:00:00
     * maxKmsForDay : 0
     * id : 33
     * typeName : 易开畅行
     * maxDays : 999
     * dayDes : 不限公里数
     * perKmsPrice : 0.00
     * endTimeForDay : 23:59:00
     * maxCostForDay : 110.00
     * nightCost : 0.00
     * minDays : 0
     */

    private List<ListBean> list;

    public Object getCalInfo() {
        return calInfo;
    }

    public void setCalInfo(Object calInfo) {
        this.calInfo = calInfo;
    }

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public boolean isIsIcCalCar() {
        return isIcCalCar;
    }

    public void setIsIcCalCar(boolean isIcCalCar) {
        this.isIcCalCar = isIcCalCar;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class MapBean {
        private String InsuranceOrigin;
        private String disclaimerUrl;
        private String riseInPriceMsg;
        private boolean isHadNightCost;
        private int dayPriceRuleId;
        private int hourPriceRange;
        private int dayPriceRange;
        private String insuranceValue;
        private boolean isRiseInPrice;
        private int hourPriceRuleId;
        private boolean isInsurance;
        private String InsuranceMessage;
        private boolean isInsuranceShow;

        public String getInsuranceOrigin() {
            return InsuranceOrigin;
        }

        public void setInsuranceOrigin(String InsuranceOrigin) {
            this.InsuranceOrigin = InsuranceOrigin;
        }

        public String getDisclaimerUrl() {
            return disclaimerUrl;
        }

        public void setDisclaimerUrl(String disclaimerUrl) {
            this.disclaimerUrl = disclaimerUrl;
        }

        public String getRiseInPriceMsg() {
            return riseInPriceMsg;
        }

        public void setRiseInPriceMsg(String riseInPriceMsg) {
            this.riseInPriceMsg = riseInPriceMsg;
        }

        public boolean isIsHadNightCost() {
            return isHadNightCost;
        }

        public void setIsHadNightCost(boolean isHadNightCost) {
            this.isHadNightCost = isHadNightCost;
        }

        public int getDayPriceRuleId() {
            return dayPriceRuleId;
        }

        public void setDayPriceRuleId(int dayPriceRuleId) {
            this.dayPriceRuleId = dayPriceRuleId;
        }

        public int getHourPriceRange() {
            return hourPriceRange;
        }

        public void setHourPriceRange(int hourPriceRange) {
            this.hourPriceRange = hourPriceRange;
        }

        public int getDayPriceRange() {
            return dayPriceRange;
        }

        public void setDayPriceRange(int dayPriceRange) {
            this.dayPriceRange = dayPriceRange;
        }

        public String getInsuranceValue() {
            return insuranceValue;
        }

        public void setInsuranceValue(String insuranceValue) {
            this.insuranceValue = insuranceValue;
        }

        public boolean isIsRiseInPrice() {
            return isRiseInPrice;
        }

        public void setIsRiseInPrice(boolean isRiseInPrice) {
            this.isRiseInPrice = isRiseInPrice;
        }

        public int getHourPriceRuleId() {
            return hourPriceRuleId;
        }

        public void setHourPriceRuleId(int hourPriceRuleId) {
            this.hourPriceRuleId = hourPriceRuleId;
        }

        public boolean isIsInsurance() {
            return isInsurance;
        }

        public void setIsInsurance(boolean isInsurance) {
            this.isInsurance = isInsurance;
        }

        public String getInsuranceMessage() {
            return InsuranceMessage;
        }

        public void setInsuranceMessage(String InsuranceMessage) {
            this.InsuranceMessage = InsuranceMessage;
        }

        public boolean isIsInsuranceShow() {
            return isInsuranceShow;
        }

        public void setIsInsuranceShow(boolean isInsuranceShow) {
            this.isInsuranceShow = isInsuranceShow;
        }
    }

    public static class MsgBean {
        private String info;
        private int code;
        private boolean success;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }

    public static class ListBean {
        private int reservedMinute;
        private String priceTypeTableName;
        private String maxKmsForNight;
        private String perHoursKmsForDay;
        private String oneHourDes;
        private String oneHoursCost;
        private String endTimeForWork;
        private String startTimeForDay;
        private String nightDes;
        private String startTimeForWork;
        private String maxKmsForDay;
        private int id;
        private String typeName;
        private String maxDays;
        private String dayDes;
        private String perKmsPrice;
        private String endTimeForDay;
        private String maxCostForDay;
        private String nightCost;
        private String minDays;

        public int getReservedMinute() {
            return reservedMinute;
        }

        public void setReservedMinute(int reservedMinute) {
            this.reservedMinute = reservedMinute;
        }

        public String getPriceTypeTableName() {
            return priceTypeTableName;
        }

        public void setPriceTypeTableName(String priceTypeTableName) {
            this.priceTypeTableName = priceTypeTableName;
        }

        public String getMaxKmsForNight() {
            return maxKmsForNight;
        }

        public void setMaxKmsForNight(String maxKmsForNight) {
            this.maxKmsForNight = maxKmsForNight;
        }

        public String getPerHoursKmsForDay() {
            return perHoursKmsForDay;
        }

        public void setPerHoursKmsForDay(String perHoursKmsForDay) {
            this.perHoursKmsForDay = perHoursKmsForDay;
        }

        public String getOneHourDes() {
            return oneHourDes;
        }

        public void setOneHourDes(String oneHourDes) {
            this.oneHourDes = oneHourDes;
        }

        public String getOneHoursCost() {
            return oneHoursCost;
        }

        public void setOneHoursCost(String oneHoursCost) {
            this.oneHoursCost = oneHoursCost;
        }

        public String getEndTimeForWork() {
            return endTimeForWork;
        }

        public void setEndTimeForWork(String endTimeForWork) {
            this.endTimeForWork = endTimeForWork;
        }

        public String getStartTimeForDay() {
            return startTimeForDay;
        }

        public void setStartTimeForDay(String startTimeForDay) {
            this.startTimeForDay = startTimeForDay;
        }

        public String getNightDes() {
            return nightDes;
        }

        public void setNightDes(String nightDes) {
            this.nightDes = nightDes;
        }

        public String getStartTimeForWork() {
            return startTimeForWork;
        }

        public void setStartTimeForWork(String startTimeForWork) {
            this.startTimeForWork = startTimeForWork;
        }

        public String getMaxKmsForDay() {
            return maxKmsForDay;
        }

        public void setMaxKmsForDay(String maxKmsForDay) {
            this.maxKmsForDay = maxKmsForDay;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getMaxDays() {
            return maxDays;
        }

        public void setMaxDays(String maxDays) {
            this.maxDays = maxDays;
        }

        public String getDayDes() {
            return dayDes;
        }

        public void setDayDes(String dayDes) {
            this.dayDes = dayDes;
        }

        public String getPerKmsPrice() {
            return perKmsPrice;
        }

        public void setPerKmsPrice(String perKmsPrice) {
            this.perKmsPrice = perKmsPrice;
        }

        public String getEndTimeForDay() {
            return endTimeForDay;
        }

        public void setEndTimeForDay(String endTimeForDay) {
            this.endTimeForDay = endTimeForDay;
        }

        public String getMaxCostForDay() {
            return maxCostForDay;
        }

        public void setMaxCostForDay(String maxCostForDay) {
            this.maxCostForDay = maxCostForDay;
        }

        public String getNightCost() {
            return nightCost;
        }

        public void setNightCost(String nightCost) {
            this.nightCost = nightCost;
        }

        public String getMinDays() {
            return minDays;
        }

        public void setMinDays(String minDays) {
            this.minDays = minDays;
        }
    }
}
