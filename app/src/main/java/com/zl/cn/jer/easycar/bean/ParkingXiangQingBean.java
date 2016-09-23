package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by wanghongra on 2016/9/14.
 */

public class ParkingXiangQingBean {

    /**
     * minSure : 50
     * retainBook : 30
     * priceList : [{"id":1,"carModel":"小型车","hour4Cost":"5.00","hourDayCost":"免费","hourHalfCost":"0.00","hourOtherCost":"10.00"}]
     * map : {"imgUrl":"","site_code":165,"spaceNO":"6","spaceId":1510}
     * msg : {"info":"查询成功","code":0,"success":true}
     */

    private String minSure;
    private String retainBook;
    /**
     * imgUrl :
     * site_code : 165
     * spaceNO : 6
     * spaceId : 1510
     */

    private MapBean map;
    /**
     * info : 查询成功
     * code : 0
     * success : true
     */

    private MsgBean msg;
    /**
     * id : 1
     * carModel : 小型车
     * hour4Cost : 5.00
     * hourDayCost : 免费
     * hourHalfCost : 0.00
     * hourOtherCost : 10.00
     */

    private List<PriceListBean> priceList;

    public String getMinSure() {
        return minSure;
    }

    public void setMinSure(String minSure) {
        this.minSure = minSure;
    }

    public String getRetainBook() {
        return retainBook;
    }

    public void setRetainBook(String retainBook) {
        this.retainBook = retainBook;
    }

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

    public List<PriceListBean> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PriceListBean> priceList) {
        this.priceList = priceList;
    }

    public static class MapBean {
        private String imgUrl;
        private int site_code;
        private String spaceNO;
        private int spaceId;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getSite_code() {
            return site_code;
        }

        public void setSite_code(int site_code) {
            this.site_code = site_code;
        }

        public String getSpaceNO() {
            return spaceNO;
        }

        public void setSpaceNO(String spaceNO) {
            this.spaceNO = spaceNO;
        }

        public int getSpaceId() {
            return spaceId;
        }

        public void setSpaceId(int spaceId) {
            this.spaceId = spaceId;
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

    public static class PriceListBean {
        private int id;
        private String carModel;
        private String hour4Cost;
        private String hourDayCost;
        private String hourHalfCost;
        private String hourOtherCost;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public String getHour4Cost() {
            return hour4Cost;
        }

        public void setHour4Cost(String hour4Cost) {
            this.hour4Cost = hour4Cost;
        }

        public String getHourDayCost() {
            return hourDayCost;
        }

        public void setHourDayCost(String hourDayCost) {
            this.hourDayCost = hourDayCost;
        }

        public String getHourHalfCost() {
            return hourHalfCost;
        }

        public void setHourHalfCost(String hourHalfCost) {
            this.hourHalfCost = hourHalfCost;
        }

        public String getHourOtherCost() {
            return hourOtherCost;
        }

        public void setHourOtherCost(String hourOtherCost) {
            this.hourOtherCost = hourOtherCost;
        }
    }
}
