package com.zl.cn.jer.easycar.bean;

/**
 * Created by lenovo on 2016/9/8.
 */

public class MyDialog {

    /**
     * expireTime : 2016-09-30 20:29:00
     * content :  易开到家，把“租赁点”搬到您家。
     上门送车，上门取车，每车次5元，每天限量20单，先预约先享受。

     * imgUrl : http://admin.eakay.cn//Upload/0c1d58e6-bff2-4898-90aa-0d777cff0fea.jpg
     * id : 63
     * linkUrl : http://mp.weixin.qq.com/s?__biz=MzI5MjAyNzAwNg==&mid=2652701734&idx=1&sn=a0d75d27312240286c2eb9a8a7e493b0&scene=1&srcid=0902jrHHUjICnefVHXSZQmiG&from=singlemessage&isappinstalled=0#wechat_redirect
     * isShowTop : 1
     * createdTime : 2016-09-02 20:29:24
     * subject : 易开到家，把“租赁点”搬到您家
     * sendTime : 2016-09-02 20:29:00
     * imgUrlExt1 : http://admin.eakay.cn//Upload/7962156f-aa93-4fa1-9bd5-6536ab58f0d3.jpg
     */

    private DataEntity data;
    /**
     * info : done
     * code : 0
     * success : true
     */

    private MsgEntity msg;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMsg(MsgEntity msg) {
        this.msg = msg;
    }

    public DataEntity getData() {
        return data;
    }

    public MsgEntity getMsg() {
        return msg;
    }

    public static class DataEntity {
        private String expireTime;
        private String content;
        private String imgUrl;
        private int id;
        private String linkUrl;
        private int isShowTop;
        private String createdTime;
        private String subject;
        private String sendTime;
        private String imgUrlExt1;

        public void setExpireTime(String expireTime) {
            this.expireTime = expireTime;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public void setIsShowTop(int isShowTop) {
            this.isShowTop = isShowTop;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public void setImgUrlExt1(String imgUrlExt1) {
            this.imgUrlExt1 = imgUrlExt1;
        }

        public String getExpireTime() {
            return expireTime;
        }

        public String getContent() {
            return content;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public int getId() {
            return id;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public int getIsShowTop() {
            return isShowTop;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public String getSubject() {
            return subject;
        }

        public String getSendTime() {
            return sendTime;
        }

        public String getImgUrlExt1() {
            return imgUrlExt1;
        }
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
}
