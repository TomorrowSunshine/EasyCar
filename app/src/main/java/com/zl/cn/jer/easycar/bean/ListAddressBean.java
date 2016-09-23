package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by wanghongra on 2016/9/12.
 */

public class ListAddressBean {


    /**
     * pageCount : 2
     * list : [{"seatCount":4,"status":"空闲","littleIcon":"http://manager.eakay.cn/upload/image/0d0f5984-6af5-4330-b041-3a39d462a418.png","surplusKms":160,"id":557,"fastdfsUrl":"http://img.eakay.cn/EAKAYORDER/M00/00/02/CqzZvlciH5eASRHMAAE7YJ41r3I216.png","deviceNO":"DD00123","chargeDuration":0,"color":"白色","carNumber":"皖BY1510","describe":" 车辆定位错误","merchantId":1,"manufacturerName":"奇瑞","carTypeName":"S15","littleImgs":"http://manager.eakay.cn/upload/image/0d0f5984-6af5-4330-b041-3a39d462a418.png","energy":"电动","siteName":"芜湖市汽车站（自助）"}]
     */

    private int pageCount;
    /**
     * seatCount : 4
     * status : 空闲
     * littleIcon : http://manager.eakay.cn/upload/image/0d0f5984-6af5-4330-b041-3a39d462a418.png
     * surplusKms : 160.0
     * id : 557
     * fastdfsUrl : http://img.eakay.cn/EAKAYORDER/M00/00/02/CqzZvlciH5eASRHMAAE7YJ41r3I216.png
     * deviceNO : DD00123
     * chargeDuration : 0
     * color : 白色
     * carNumber : 皖BY1510
     * describe :  车辆定位错误
     * merchantId : 1
     * manufacturerName : 奇瑞
     * carTypeName : S15
     * littleImgs : http://manager.eakay.cn/upload/image/0d0f5984-6af5-4330-b041-3a39d462a418.png
     * energy : 电动
     * siteName : 芜湖市汽车站（自助）
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
        private int seatCount;
        private String status;
        private String littleIcon;
        private double surplusKms;
        private int id;
        private String fastdfsUrl;
        private String deviceNO;
        private int chargeDuration;
        private String color;
        private String carNumber;
        private String describe;
        private int merchantId;
        private String manufacturerName;
        private String carTypeName;
        private String littleImgs;
        private String energy;
        private String siteName;

        public int getSeatCount() {
            return seatCount;
        }

        public void setSeatCount(int seatCount) {
            this.seatCount = seatCount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLittleIcon() {
            return littleIcon;
        }

        public void setLittleIcon(String littleIcon) {
            this.littleIcon = littleIcon;
        }

        public double getSurplusKms() {
            return surplusKms;
        }

        public void setSurplusKms(double surplusKms) {
            this.surplusKms = surplusKms;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFastdfsUrl() {
            return fastdfsUrl;
        }

        public void setFastdfsUrl(String fastdfsUrl) {
            this.fastdfsUrl = fastdfsUrl;
        }

        public String getDeviceNO() {
            return deviceNO;
        }

        public void setDeviceNO(String deviceNO) {
            this.deviceNO = deviceNO;
        }

        public int getChargeDuration() {
            return chargeDuration;
        }

        public void setChargeDuration(int chargeDuration) {
            this.chargeDuration = chargeDuration;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public int getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public String getManufacturerName() {
            return manufacturerName;
        }

        public void setManufacturerName(String manufacturerName) {
            this.manufacturerName = manufacturerName;
        }

        public String getCarTypeName() {
            return carTypeName;
        }

        public void setCarTypeName(String carTypeName) {
            this.carTypeName = carTypeName;
        }

        public String getLittleImgs() {
            return littleImgs;
        }

        public void setLittleImgs(String littleImgs) {
            this.littleImgs = littleImgs;
        }

        public String getEnergy() {
            return energy;
        }

        public void setEnergy(String energy) {
            this.energy = energy;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }
    }
}
