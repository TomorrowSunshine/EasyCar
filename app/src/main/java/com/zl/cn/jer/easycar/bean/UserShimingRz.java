package com.zl.cn.jer.easycar.bean;

/**
 * Created by JER on 2016/9/10.
 */

public class UserShimingRz {

    /**
     * applyType : 未通过
     * checkRemarks : 身份证和驾照信息不匹配
     * fastdfsUrl : http://www.93.gov.cn/11002/upload/webcms/content/image/2016/09/04/16_42_11_086_33624_11_15_20_546_33414_16083001.jpg
     * idCard : 130426199508310313
     * idCardImg : http://manager.eakay.cnhttp://www.93.gov.cn/11002/upload/webcms/content/image/2016/09/04/16_42_11_086_33624_11_15_20_546_33414_16083001.jpg
     * idCardImgPath : http://www.93.gov.cn/11002/upload/webcms/content/image/2016/09/04/16_42_11_086_33624_11_15_20_546_33414_16083001.jpg
     * isCanRental : 0
     * name : 刘瑞宁
     * phone : 13552967803
     */

    private MapBean map;
    /**
     * code : 0
     * info : 查找成功
     * success : true
     */

    private MsgBean msg;

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public static class MapBean {
        private String applyType;
        private String checkRemarks;
        private String fastdfsUrl;
        private String idCard;
        private String idCardImg;
        private String idCardImgPath;
        private int isCanRental;
        private String name;
        private String phone;

        public String getApplyType() {
            return applyType;
        }

        public void setApplyType(String applyType) {
            this.applyType = applyType;
        }

        public String getCheckRemarks() {
            return checkRemarks;
        }

        public void setCheckRemarks(String checkRemarks) {
            this.checkRemarks = checkRemarks;
        }

        public String getFastdfsUrl() {
            return fastdfsUrl;
        }

        public void setFastdfsUrl(String fastdfsUrl) {
            this.fastdfsUrl = fastdfsUrl;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getIdCardImg() {
            return idCardImg;
        }

        public void setIdCardImg(String idCardImg) {
            this.idCardImg = idCardImg;
        }

        public String getIdCardImgPath() {
            return idCardImgPath;
        }

        public void setIdCardImgPath(String idCardImgPath) {
            this.idCardImgPath = idCardImgPath;
        }

        public int getIsCanRental() {
            return isCanRental;
        }

        public void setIsCanRental(int isCanRental) {
            this.isCanRental = isCanRental;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
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
