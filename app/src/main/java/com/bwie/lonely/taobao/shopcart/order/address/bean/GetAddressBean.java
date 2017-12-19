package com.bwie.lonely.taobao.shopcart.order.address.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/21 0021.
 */

public class GetAddressBean {

    /**
     * msg : 地址列表请求成功
     * code : 0
     * data : [{"addr":"北京市海淀区上地七街1508A班","addrid":623,"mobile":18519327770,"name":"史壮壮","status":0,"uid":903}]
     */

    private String msg;
    private String code;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * addr : 北京市海淀区上地七街1508A班
         * addrid : 623
         * mobile : 18519327770
         * name : 史壮壮
         * status : 0
         * uid : 903
         */

        private String addr;
        private String addrid;
        private String mobile;
        private String name;
        private String status;
        private String uid;
        private boolean isSelected;

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getAddrid() {
            return addrid;
        }

        public void setAddrid(String addrid) {
            this.addrid = addrid;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }
    }
}
