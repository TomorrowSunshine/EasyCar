package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by lenovo on 2016/9/7.
 */

public class MyZuche {


    /**
     * info : sign签名验证成功.
     * code : 0
     * success : true
     */

    private MsgEntity msg;
    /**
     * id : 214
     * site_name : 北京易开租赁点(暂不开放)
     * address : 北京市朝阳区八里庄东里1号莱锦文化创意产业园
     * lat : 39.920815
     * lng : 116.5046
     * merchantId : 17
     * isNightReturnCar : 0
     * isNightGetCar : 0
     * startTimeForWork : 00:00
     * endTimeForWork : 23:59
     * allow : 0
     * distance : 0.0
     * useable : 2
     * chargeNum : 0
     * parkingNum : 0
     */

    private List<ListEntity> list;

    public void setMsg(MsgEntity msg) {
        this.msg = msg;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public MsgEntity getMsg() {
        return msg;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class MsgEntity {
        private String info;
        private int code;
        private boolean success;

        public void setInfo(String info) {
            this.info = info;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getInfo() {
            return info;
        }

        public int getCode() {
            return code;
        }

        public boolean isSuccess() {
            return success;
        }
    }

    public static class ListEntity {
        private int id;
        private String site_name;
        private String address;
        private double lat;
        private double lng;
        private int merchantId;
        private int isNightReturnCar;
        private int isNightGetCar;
        private String startTimeForWork;
        private String endTimeForWork;
        private int allow;
        private double distance;
        private int useable;
        private int chargeNum;
        private int parkingNum;

        public void setId(int id) {
            this.id = id;
        }

        public void setSite_name(String site_name) {
            this.site_name = site_name;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public void setIsNightReturnCar(int isNightReturnCar) {
            this.isNightReturnCar = isNightReturnCar;
        }

        public void setIsNightGetCar(int isNightGetCar) {
            this.isNightGetCar = isNightGetCar;
        }

        public void setStartTimeForWork(String startTimeForWork) {
            this.startTimeForWork = startTimeForWork;
        }

        public void setEndTimeForWork(String endTimeForWork) {
            this.endTimeForWork = endTimeForWork;
        }

        public void setAllow(int allow) {
            this.allow = allow;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public void setUseable(int useable) {
            this.useable = useable;
        }

        public void setChargeNum(int chargeNum) {
            this.chargeNum = chargeNum;
        }

        public void setParkingNum(int parkingNum) {
            this.parkingNum = parkingNum;
        }

        public int getId() {
            return id;
        }

        public String getSite_name() {
            return site_name;
        }

        public String getAddress() {
            return address;
        }

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }

        public int getMerchantId() {
            return merchantId;
        }

        public int getIsNightReturnCar() {
            return isNightReturnCar;
        }

        public int getIsNightGetCar() {
            return isNightGetCar;
        }

        public String getStartTimeForWork() {
            return startTimeForWork;
        }

        public String getEndTimeForWork() {
            return endTimeForWork;
        }

        public int getAllow() {
            return allow;
        }

        public double getDistance() {
            return distance;
        }

        public int getUseable() {
            return useable;
        }

        public int getChargeNum() {
            return chargeNum;
        }

        public int getParkingNum() {
            return parkingNum;
        }
    }
}
