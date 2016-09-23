package com.zl.cn.jer.easycar.bean;

/**
 * Created by JER on 2016/9/13.
 */

public class CheZhuComitBean {

    /**
     * id : 735
     * carStatus : 待认证
     * msg : {"info":"操作成功!","code":0,"success":true}
     */

    private String id;
    private String carStatus;
    /**
     * info : 操作成功!
     * code : 0
     * success : true
     */

    private MsgBean msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
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
}
