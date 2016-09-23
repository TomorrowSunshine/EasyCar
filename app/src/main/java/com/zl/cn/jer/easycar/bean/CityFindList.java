package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by wanghongra on 2016/9/7.
 */

public class CityFindList {


    /**
     * id : 7
     * site_name : 芜湖市汽车站（自助）
     * address : 安徽省芜湖市长途客运总站对面
     * lat : 31.353674
     * lng : 118.39539
     * merchantId : 1
     * isNightReturnCar : 1
     * isNightGetCar : 1
     * startTimeForWork : 00:00
     * endTimeForWork : 23:59
     * allow : 1
     * distance : 0.0
     * useable : 6
     * chargeNum : 0
     * parkingNum : 0
     *
     * 租车搜索
     */

    private List<ListBean> list;



    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSite_name() {
            return site_name;
        }

        public void setSite_name(String site_name) {
            this.site_name = site_name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public int getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public int getIsNightReturnCar() {
            return isNightReturnCar;
        }

        public void setIsNightReturnCar(int isNightReturnCar) {
            this.isNightReturnCar = isNightReturnCar;
        }

        public int getIsNightGetCar() {
            return isNightGetCar;
        }

        public void setIsNightGetCar(int isNightGetCar) {
            this.isNightGetCar = isNightGetCar;
        }

        public String getStartTimeForWork() {
            return startTimeForWork;
        }

        public void setStartTimeForWork(String startTimeForWork) {
            this.startTimeForWork = startTimeForWork;
        }

        public String getEndTimeForWork() {
            return endTimeForWork;
        }

        public void setEndTimeForWork(String endTimeForWork) {
            this.endTimeForWork = endTimeForWork;
        }

        public int getAllow() {
            return allow;
        }

        public void setAllow(int allow) {
            this.allow = allow;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getUseable() {
            return useable;
        }

        public void setUseable(int useable) {
            this.useable = useable;
        }

        public int getChargeNum() {
            return chargeNum;
        }

        public void setChargeNum(int chargeNum) {
            this.chargeNum = chargeNum;
        }

        public int getParkingNum() {
            return parkingNum;
        }

        public void setParkingNum(int parkingNum) {
            this.parkingNum = parkingNum;
        }
    }
}
