package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by wanghongra on 2016/9/13.
 */

public class ListAddressChongDianBean {


    /**
     * pageCount : 2
     * list : [{"position":"右","imgUrl":"http://manager.eakay.cn/upload/image/2016-02-20/48a2ae49-718b-4241-8cd8-fa807dcf885e.jpg","fastdfsUrl":"http://img.eakay.cn/EAKAYORDER/M00/00/32/CqzZvlcxWwWAE86UAAEn20O0K7k903.jpg","deviceNo":"9","memo":"    ","status":"空闲","factoryNo":"QX150207009","devicePower":"220","deviceKind":"交","grooveNo":"-1","fixedVoltage":"202"}]
     */

    private int pageCount;
    /**
     * position : 右
     * imgUrl : http://manager.eakay.cn/upload/image/2016-02-20/48a2ae49-718b-4241-8cd8-fa807dcf885e.jpg
     * fastdfsUrl : http://img.eakay.cn/EAKAYORDER/M00/00/32/CqzZvlcxWwWAE86UAAEn20O0K7k903.jpg
     * deviceNo : 9
     * memo :
     * status : 空闲
     * factoryNo : QX150207009
     * devicePower : 220
     * deviceKind : 交
     * grooveNo : -1
     * fixedVoltage : 202
     */

    private List<ListBean> list;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String position;
        private String imgUrl;
        private String fastdfsUrl;
        private String deviceNo;
        private String memo;
        private String status;
        private String factoryNo;
        private String devicePower;
        private String deviceKind;
        private String grooveNo;
        private String fixedVoltage;

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getFastdfsUrl() {
            return fastdfsUrl;
        }

        public void setFastdfsUrl(String fastdfsUrl) {
            this.fastdfsUrl = fastdfsUrl;
        }

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getFactoryNo() {
            return factoryNo;
        }

        public void setFactoryNo(String factoryNo) {
            this.factoryNo = factoryNo;
        }

        public String getDevicePower() {
            return devicePower;
        }

        public void setDevicePower(String devicePower) {
            this.devicePower = devicePower;
        }

        public String getDeviceKind() {
            return deviceKind;
        }

        public void setDeviceKind(String deviceKind) {
            this.deviceKind = deviceKind;
        }

        public String getGrooveNo() {
            return grooveNo;
        }

        public void setGrooveNo(String grooveNo) {
            this.grooveNo = grooveNo;
        }

        public String getFixedVoltage() {
            return fixedVoltage;
        }

        public void setFixedVoltage(String fixedVoltage) {
            this.fixedVoltage = fixedVoltage;
        }
    }
}
