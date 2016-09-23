package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by wanghongra on 2016/9/13.
 */

public class ListAddressParkingBean {

    /**
     * pageCount : 2
     * list : [{"imgUrl":"","memo":"","isPrice":1,"spaceNO":"3","site_code":165,"parkingType":"地上","isCharge":"是","merchantId":1,"spaceId":1507,"PStatus":"空闲","siteName":"松韵园小区（自助）"}]
     */

    private int pageCount;
    /**
     * imgUrl :
     * memo :
     * isPrice : 1
     * spaceNO : 3
     * site_code : 165
     * parkingType : 地上
     * isCharge : 是
     * merchantId : 1
     * spaceId : 1507
     * PStatus : 空闲
     * siteName : 松韵园小区（自助）
     */

    private List<ListBean> list;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String imgUrl;
        private String memo;
        private int isPrice;
        private String spaceNO;
        private int site_code;
        private String parkingType;
        private String isCharge;
        private int merchantId;
        private int spaceId;
        private String PStatus;
        private String siteName;

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public int getIsPrice() {
            return isPrice;
        }

        public void setIsPrice(int isPrice) {
            this.isPrice = isPrice;
        }

        public String getSpaceNO() {
            return spaceNO;
        }

        public void setSpaceNO(String spaceNO) {
            this.spaceNO = spaceNO;
        }

        public int getSite_code() {
            return site_code;
        }

        public void setSite_code(int site_code) {
            this.site_code = site_code;
        }

        public String getParkingType() {
            return parkingType;
        }

        public void setParkingType(String parkingType) {
            this.parkingType = parkingType;
        }

        public String getIsCharge() {
            return isCharge;
        }

        public void setIsCharge(String isCharge) {
            this.isCharge = isCharge;
        }

        public int getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(int merchantId) {
            this.merchantId = merchantId;
        }

        public int getSpaceId() {
            return spaceId;
        }

        public void setSpaceId(int spaceId) {
            this.spaceId = spaceId;
        }

        public String getPStatus() {
            return PStatus;
        }

        public void setPStatus(String PStatus) {
            this.PStatus = PStatus;
        }

        public String getSiteName() {
            return siteName;
        }

        public void setSiteName(String siteName) {
            this.siteName = siteName;
        }
    }
}
