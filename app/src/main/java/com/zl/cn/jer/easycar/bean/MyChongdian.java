package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by lenovo on 2016/9/8.
 */

public class MyChongdian {


    /**
     * info : sign签名验证成功.
     * code : 0
     * success : true
     */

    private MsgEntity msg;
    /**
     * id : 215
     * address : 朝阳区东四环慈云寺桥东南角住邦住邦2000一号楼地下B2停车场
     * siteName : 住邦2000一号楼
     * freeCounts : 0
     * lat : 39.920734
     * lng : 116.50138
     * distance : 0.0
     */

    private List<SiteListEntity> siteList;

    public void setMsg(MsgEntity msg) {
        this.msg = msg;
    }

    public void setSiteList(List<SiteListEntity> siteList) {
        this.siteList = siteList;
    }

    public MsgEntity getMsg() {
        return msg;
    }

    public List<SiteListEntity> getSiteList() {
        return siteList;
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

    public static class SiteListEntity {
        private int id;
        private String address;
        private String siteName;
        private int freeCounts;
        private double lat;
        private double lng;
        private double distance;

        public void setId(int id) {
            this.id = id;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }

        public void setFreeCounts(int freeCounts) {
            this.freeCounts = freeCounts;
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

        public int getId() {
            return id;
        }

        public String getAddress() {
            return address;
        }

        public String getSiteName() {
            return siteName;
        }

        public int getFreeCounts() {
            return freeCounts;
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
}
