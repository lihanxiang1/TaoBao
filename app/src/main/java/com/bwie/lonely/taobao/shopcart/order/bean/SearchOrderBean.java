package com.bwie.lonely.taobao.shopcart.order.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21 0021.
 */

public class SearchOrderBean {

    /**
     * msg : 请求成功
     * code : 0
     * data : [{"createtime":"2017-11-21T13:02:29","orderid":2548,"price":99,"status":0,"title":null,"uid":903},{"createtime":"2017-11-21T13:02:39","orderid":2549,"price":99.99,"status":0,"title":null,"uid":903},{"createtime":"2017-11-21T13:02:49","orderid":2550,"price":99.99,"status":0,"title":null,"uid":903}]
     * page : 1
     */

    private String msg;
    private String code;
    private String page;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
         * createtime : 2017-11-21T13:02:29
         * orderid : 2548
         * price : 99
         * status : 0
         * title : null
         * uid : 903
         */

        private String createtime;
        private int orderid;
        private float price;
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

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
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
