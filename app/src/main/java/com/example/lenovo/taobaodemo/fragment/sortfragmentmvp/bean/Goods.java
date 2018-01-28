package com.example.lenovo.taobaodemo.fragment.sortfragmentmvp.bean;

/**
 * author:Created by WangZhiQiang on 2017/12/15.
 */

public class Goods {


    /**
     * code : 0
     * data : {"bargainPrice":1999,"createtime":"2017-10-10T16:09:02","detailUrl":"https://item.m.jd.com/product/5025971.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t7210/232/3738666823/232298/9004583e/59c3a9a7N8de42e15.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t8356/82/2107423621/109733/c019b8c6/59c3a9a6Ne9a4bdd7.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t10219/74/25356012/171379/7d55e296/59c3a9a8N82fa6e02.jpg!q70.jpg","itemtype":0,"pid":49,"price":333,"pscid":39,"salenum":123,"sellerid":5,"subhead":"vivo X20 带你开启全面屏时代！逆光也清晰，照亮你的美！","title":"vivo X20 全面屏手机 全网通 4GB+64GB 金色 移动联通电信4G手机 双卡双待"}
     * msg :
     * seller : {"description":"我是商家5","icon":"http://120.27.23.105/images/icon.png","name":"商家5","productNums":999,"score":5,"sellerid":5}
     */

    private String code;
    private DataBean data;
    private String msg;
    private SellerBean seller;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SellerBean getSeller() {
        return seller;
    }

    public void setSeller(SellerBean seller) {
        this.seller = seller;
    }

    public static class DataBean {
        /**
         * bargainPrice : 1999.0
         * createtime : 2017-10-10T16:09:02
         * detailUrl : https://item.m.jd.com/product/5025971.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends
         * images : https://m.360buyimg.com/n0/jfs/t7210/232/3738666823/232298/9004583e/59c3a9a7N8de42e15.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t8356/82/2107423621/109733/c019b8c6/59c3a9a6Ne9a4bdd7.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t10219/74/25356012/171379/7d55e296/59c3a9a8N82fa6e02.jpg!q70.jpg
         * itemtype : 0
         * pid : 49
         * price : 333.0
         * pscid : 39
         * salenum : 123
         * sellerid : 5
         * subhead : vivo X20 带你开启全面屏时代！逆光也清晰，照亮你的美！
         * title : vivo X20 全面屏手机 全网通 4GB+64GB 金色 移动联通电信4G手机 双卡双待
         */

        private double bargainPrice;
        private String createtime;
        private String detailUrl;
        private String images;
        private int itemtype;
        private int pid;
        private double price;
        private int pscid;
        private int salenum;
        private int sellerid;
        private String subhead;
        private String title;

        public double getBargainPrice() {
            return bargainPrice;
        }

        public void setBargainPrice(double bargainPrice) {
            this.bargainPrice = bargainPrice;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(String detailUrl) {
            this.detailUrl = detailUrl;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getItemtype() {
            return itemtype;
        }

        public void setItemtype(int itemtype) {
            this.itemtype = itemtype;
        }

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getPscid() {
            return pscid;
        }

        public void setPscid(int pscid) {
            this.pscid = pscid;
        }

        public int getSalenum() {
            return salenum;
        }

        public void setSalenum(int salenum) {
            this.salenum = salenum;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }

        public String getSubhead() {
            return subhead;
        }

        public void setSubhead(String subhead) {
            this.subhead = subhead;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    public static class SellerBean {
        /**
         * description : 我是商家5
         * icon : http://120.27.23.105/images/icon.png
         * name : 商家5
         * productNums : 999
         * score : 5.0
         * sellerid : 5
         */

        private String description;
        private String icon;
        private String name;
        private int productNums;
        private double score;
        private int sellerid;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProductNums() {
            return productNums;
        }

        public void setProductNums(int productNums) {
            this.productNums = productNums;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getSellerid() {
            return sellerid;
        }

        public void setSellerid(int sellerid) {
            this.sellerid = sellerid;
        }
    }
}
