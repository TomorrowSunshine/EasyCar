package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by wanghongra on 2016/9/8.
 */

public class ChongDian {

    /**
     * info : sign签名验证成功.
     * code : 0
     * success : true
     */

    private MsgBean msg;
    /**
     * id : 1
     * address : 安徽省芜湖市瑞祥路，接近芜湖皖江财富广场、芜湖市民服务中心
     * siteName : 芜湖市政务中心（半自助）
     * freeCounts : 9
     * lat : 31.360989
     * lng : 118.43934
     * distance : 0.0
     */

    private List<SiteListBean> siteList;

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public List<SiteListBean> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<SiteListBean> siteList) {
        this.siteList = siteList;
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

    public static class SiteListBean {
        private int id;
        private String address;
        private String siteName;
        private int freeCounts;
        private double lat;
        private double lng;
        private double distance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getFreeCounts() {
            return freeCounts;
        }

        public void setFreeCounts(int freeCounts) {
            this.freeCounts = freeCounts;
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
