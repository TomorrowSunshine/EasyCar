package com.zl.cn.jer.easycar.bean;

/**
 * Created by JER on 2016/9/13.
 */

public class ChezhuBean {

    /**
     * carBrand : 12345
     * carNumber : 1234
     * carType : 燃油
     * checkRemark :
     * fastdfsUrl : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=364904791,404746197&fm=116&gp=0.jpg
     * icon : https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=364904791,404746197&fm=116&gp=0.jpg
     * littleIcon : http://manager.eakay.cnhttps://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=364904791,404746197&fm=116&gp=0.jpg
     * status : 待认证
     */

    private CarInfoBean carInfo;
    /**
     * code : 0
     * info : 操作成功!
     * success : true
     */

    private MsgBean msg;

    public CarInfoBean getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfoBean carInfo) {
        this.carInfo = carInfo;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class CarInfoBean {
        private String carBrand;
        private String carNumber;
        private String carType;
        private String checkRemark;
        private String fastdfsUrl;
        private String icon;
        private String littleIcon;
        private String status;

        public String getCarBrand() {
            return carBrand;
        }

        public void setCarBrand(String carBrand) {
            this.carBrand = carBrand;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getCarType() {
            return carType;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

        public String getCheckRemark() {
            return checkRemark;
        }

        public void setCheckRemark(String checkRemark) {
            this.checkRemark = checkRemark;
        }

        public String getFastdfsUrl() {
            return fastdfsUrl;
        }

        public void setFastdfsUrl(String fastdfsUrl) {
            this.fastdfsUrl = fastdfsUrl;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getLittleIcon() {
            return littleIcon;
        }

        public void setLittleIcon(String littleIcon) {
            this.littleIcon = littleIcon;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class MsgBean {
        private int code;
        private String info;
        private boolean success;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }
    }
}
