package com.zl.cn.jer.easycar.bean;

import java.util.List;

/**
 * Created by wanghongra on 2016/9/13.
 */

public class ZuCheXiangqingBean {


    /**
     * showPath : http://manager.eakay.cn
     * list : [{"id":6254,"fastdfsUrl":"http://img.eakay.cn/EAKAYORDER/M00/00/24/Cqsfq1ciTouANL3GAAfFxWyAbKE519.JPG","fileUrl":"/upload/image/2016-02-16/801282e5-198d-499b-8973-c7111ebb9668.JPG"}]
     */

    private String showPath;
    /**
     * id : 6254
     * fastdfsUrl : http://img.eakay.cn/EAKAYORDER/M00/00/24/Cqsfq1ciTouANL3GAAfFxWyAbKE519.JPG
     * fileUrl : /upload/image/2016-02-16/801282e5-198d-499b-8973-c7111ebb9668.JPG
     */

    private List<ListBean> list;

    public String getShowPath() {
        return showPath;
    }

    public void setShowPath(String showPath) {
        this.showPath = showPath;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private int id;
        private String fastdfsUrl;
        private String fileUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFastdfsUrl() {
            return fastdfsUrl;
        }

        public void setFastdfsUrl(String fastdfsUrl) {
            this.fastdfsUrl = fastdfsUrl;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }
    }
}
