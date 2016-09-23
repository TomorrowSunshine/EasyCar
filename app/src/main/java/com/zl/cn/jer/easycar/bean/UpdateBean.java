package com.zl.cn.jer.easycar.bean;

/**
 * Created by lenovo on 2016/9/8.
 */

public class UpdateBean {

    /**
     * downAddress : http://eakay.cn/apk/car.apk
     * id : 37
     * isFocused : 0
     * releaseTime : 2016-09-02 11:03:11
     * type : 1
     * updateDesc : 租车红包上线，分享给小伙伴共享优惠
     * version : 3.1.2
     */

    private MapBean map;
    /**
     * code : 0
     * info : 获取成功
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
        private String downAddress;
        private int id;
        private int isFocused;
        private String releaseTime;
        private int type;
        private String updateDesc;
        private String version;

        public String getDownAddress() {
            return downAddress;
        }

        public void setDownAddress(String downAddress) {
            this.downAddress = downAddress;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsFocused() {
            return isFocused;
        }

        public void setIsFocused(int isFocused) {
            this.isFocused = isFocused;
        }

        public String getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(String releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdateDesc() {
            return updateDesc;
        }

        public void setUpdateDesc(String updateDesc) {
            this.updateDesc = updateDesc;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
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
