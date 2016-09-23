package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by wanghongra on 2016/9/8.
 */

public class StopParking {


    /**
     * info : 查询成功
     * code : 0
     * success : true
     */

    private MsgBean msg;
    /**
     * site_code : 165
     * num : 5
     * address : 安徽省芜湖市弋江区利民东路
     * siteName : 松韵园小区（自助）
     * lat : 31.322453
     * lng : 118.41831
     * distance : 0.0
     */

    private List<ListBean> list;

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
        private int site_code;
        private int num;
        private String address;
        private String siteName;
        private double lat;
        private double lng;
        private double distance;

        public int getSite_code() {
            return site_code;
        }

        public void setSite_code(int site_code) {
            this.site_code = site_code;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
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

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }
    }
}
