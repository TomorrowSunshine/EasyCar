package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by wanghongra on 2016/9/14.
 */

public class ChongDianXiangQingBean {

    /**
     * portList : [{"currents":"32A","id":8130,"memo":"七星","status":"空闲","name":"左1","portorder":"1","grooveNo":"0","pos":"左"},{"currents":"16A","id":8131,"memo":"三插","status":"空闲","name":"左2","portorder":"2","grooveNo":"2","pos":"左"},{"currents":"32A","id":8132,"memo":"七星","status":"充电","name":"右1","portorder":"3","grooveNo":"1","pos":"右"},{"currents":"16A","id":8133,"memo":"三插","status":"充电","name":"右2","portorder":"4","grooveNo":"3","pos":"右"}]
     * chargeMoney : 50
     * stakeInfo : {"imgUrl":"http://manager.eakay.cn/upload/image/2016-04-19/7126922b-b3c3-4ba8-a59a-5c19ba98e825.jpg","fastdfsUrl":"http://img.eakay.cn/EAKAYORDER/M00/00/35/CqzZvlcxW5OAWtP5AAEn20O0K7k326.jpg","reservedMinute":30,"price":0,"deviceNo":"1","serverPrice":1.6,"merchantId":"1"}
     * msg : {"info":"查询成功","code":0,"success":true}
     */

    private String chargeMoney;
    /**
     * imgUrl : http://manager.eakay.cn/upload/image/2016-04-19/7126922b-b3c3-4ba8-a59a-5c19ba98e825.jpg
     * fastdfsUrl : http://img.eakay.cn/EAKAYORDER/M00/00/35/CqzZvlcxW5OAWtP5AAEn20O0K7k326.jpg
     * reservedMinute : 30
     * price : 0.0
     * deviceNo : 1
     * serverPrice : 1.6
     * merchantId : 1
     */

    private StakeInfoBean stakeInfo;
    /**
     * info : 查询成功
     * code : 0
     * success : true
     */

    private MsgBean msg;
    /**
     * currents : 32A
     * id : 8130
     * memo : 七星
     * status : 空闲
     * name : 左1
     * portorder : 1
     * grooveNo : 0
     * pos : 左
     */

    private List<PortListBean> portList;

    public String getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(String chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public StakeInfoBean getStakeInfo() {
        return stakeInfo;
    }

    public void setStakeInfo(StakeInfoBean stakeInfo) {
        this.stakeInfo = stakeInfo;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public List<PortListBean> getPortList() {
        return portList;
    }

    public void setPortList(List<PortListBean> portList) {
        this.portList = portList;
    }

    public static class StakeInfoBean {
        private String imgUrl;
        private String fastdfsUrl;
        private int reservedMinute;
        private double price;
        private String deviceNo;
        private double serverPrice;
        private String merchantId;

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

        public int getReservedMinute() {
            return reservedMinute;
        }

        public void setReservedMinute(int reservedMinute) {
            this.reservedMinute = reservedMinute;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getDeviceNo() {
            return deviceNo;
        }

        public void setDeviceNo(String deviceNo) {
            this.deviceNo = deviceNo;
        }

        public double getServerPrice() {
            return serverPrice;
        }

        public void setServerPrice(double serverPrice) {
            this.serverPrice = serverPrice;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }
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

    public static class PortListBean {
        private String currents;
        private int id;
        private String memo;
        private String status;
        private String name;
        private String portorder;
        private String grooveNo;
        private String pos;

        public String getCurrents() {
            return currents;
        }

        public void setCurrents(String currents) {
            this.currents = currents;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPortorder() {
            return portorder;
        }

        public void setPortorder(String portorder) {
            this.portorder = portorder;
        }

        public String getGrooveNo() {
            return grooveNo;
        }

        public void setGrooveNo(String grooveNo) {
            this.grooveNo = grooveNo;
        }

        public String getPos() {
            return pos;
        }

        public void setPos(String pos) {
            this.pos = pos;
        }
    }
}
