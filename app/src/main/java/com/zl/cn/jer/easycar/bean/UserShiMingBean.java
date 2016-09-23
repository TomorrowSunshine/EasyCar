package com.zl.cn.jer.easycar.bean;

/**
 * Created by JER on 2016/9/9.
 */

public class UserShiMingBean {
    private String name = "";
    private String userid = "";
    private String imgURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public String toString() {
        return "UserShiMingBean{" +
                "name='" + name + '\'' +
                ", userid='" + userid + '\'' +
                ", imgURL='" + imgURL + '\'' +
                '}';
    }
}
