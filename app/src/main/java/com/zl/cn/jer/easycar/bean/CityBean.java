package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by lenovo on 2016/9/7.
 */

public class CityBean {

    /**
     * info : sign签名验证成功.
     * code : 0
     * success : true
     */

    private MsgEntity msg;
    /**
     * city : 芜湖市
     */

    private List<ListEntity> list;

    public void setMsg(MsgEntity msg) {
        this.msg = msg;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public MsgEntity getMsg() {
        return msg;
    }

    public List<ListEntity> getList() {
        return list;
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

    public static class ListEntity {
        private String city;

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity() {
            return city;
        }
    }
}
