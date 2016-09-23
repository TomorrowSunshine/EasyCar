package com.zl.cn.jer.easycar.bean;

/**
 * Created by JER on 2016/9/9.
 */

public class ShiMInBean {

    /**
     * status : 审核中
     * msg : {"info":"完善成功.","code":0,"success":true}
     */

    private String status;
    /**
     * info : 完善成功.
     * code : 0
     * success : true
     */

    private MsgBean msg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
