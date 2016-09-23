package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by lenovo on 2016/9/8.
 */

public class MyTingche {
    /**
     * info : 查询成功
     * code : 0
     * success : true
     */

    private MsgEntity msg;
    /**
     * site_code : 59
     * num : 0
     * address : 马鞍山雨山区太白大道与梅山路交叉口
     * siteName : 创业园(不提供充电和租车服务)
     * lat : 31.649294
     * lng : 118.51219
     * distance : 0.0
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
        private int site_code;
        private int num;
        private String address;
        private String siteName;
        private double lat;
        private double lng;
        private double distance;

        public void setSite_code(int site_code) {
            this.site_code = site_code;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public int getSite_code() {
            return site_code;
        }

        public int getNum() {
            return num;
        }

        public String getAddress() {
            return address;
        }

        public String getSiteName() {
            return siteName;
        }

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }

        public double getDistance() {
            return distance;
        }
    }

    /**
     * info : sign签名验证成功.
     * code : 0
     * success : true
     */



}
