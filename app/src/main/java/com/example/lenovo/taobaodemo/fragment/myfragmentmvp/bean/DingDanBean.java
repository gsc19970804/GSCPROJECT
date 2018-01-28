package com.example.lenovo.taobaodemo.fragment.myfragmentmvp.bean;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/1/5.
 */

public class DingDanBean {


    /**
     * code : 0
     * data : [{"createtime":"2018-01-05T12:19:12","orderid":6523,"price":3875,"status":0,"title":"订单测试标题10822","uid":10822},{"createtime":"2018-01-05T13:36:52","orderid":6524,"price":5612,"status":0,"title":"订单测试标题10822","uid":10822}]
     * msg : 请求成功
     * page : 1
     */

    private String code;
    private String msg;
    private String page;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * createtime : 2018-01-05T12:19:12
         * orderid : 6523
         * price : 3875.0
         * status : 0
         * title : 订单测试标题10822
         * uid : 10822
         */

        private String createtime;
        private int orderid;
        private double price;
        private int status;
        private String title;
        private int uid;

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public int getOrderid() {
            return orderid;
        }

        public void setOrderid(int orderid) {
            this.orderid = orderid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }
    }
}
