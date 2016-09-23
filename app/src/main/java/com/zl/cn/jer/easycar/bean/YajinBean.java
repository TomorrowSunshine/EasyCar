package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by JER on 2016/9/8.
 */

public class YajinBean {

    /**
     * info : 获取成功
     * code : 0
     * success : true
     */

    private MsgBean msg;
    /**
     * id : 5
     * name : 押金＋储值 超值套餐
     * price : 1500.0
     * originPrice : 0.0
     * description : 押金1000元＋储值500元
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
        private int id;
        private String name;
        private String price;
        private double originPrice;
        private String description;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public double getOriginPrice() {
            return originPrice;
        }

        public void setOriginPrice(double originPrice) {
            this.originPrice = originPrice;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
