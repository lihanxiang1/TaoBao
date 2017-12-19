package com.bwie.lonely.taobao.my.bean;

/**
 * Created by Administrator on 2017/11/9 0009.
 */

public class LoginBean {

    /**
     * msg : 登录成功
     * code : 0
     * data : {"age":null,"appkey":null,"appsecret":null,"createtime":"2017-11-09T22:37:02","email":null,"gender":0,"icon":null,"mobile":"18529327770","money":0,"nickname":"sss","password":"123123","token":"F5962BEE97164D1509A586F2DE21C0FD","uid":903,"username":"18529327770"}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "data=" + data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * age : null
         * appkey : null
         * appsecret : null
         * createtime : 2017-11-09T22:37:02
         * email : null
         * gender : 0
         * icon : null
         * mobile : 18529327770
         * money : 0
         * nickname : sss
         * password : 123123
         * token : F5962BEE97164D1509A586F2DE21C0FD
         * uid : 903
         * username : 18529327770
         */

        private String age;
        private String appkey;
        private String appsecret;
        private String createtime;
        private String email;
        private String gender;
        private String icon;
        private String mobile;
        private String money;
        private String nickname;
        private String password;
        private String token;
        private String uid;
        private String username;

        @Override
        public String toString() {
            return "age:'" + age + ",appkey:" + appkey +
                    ",appsecret:" + appsecret +
                    ",createtime:" + createtime +
                    ", email:" + email +
                    "mobile:" + mobile +
                    "nickname:" + nickname +
                    "password:" + password +
                    "uid:" + uid +
                    "username:" + username;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAppkey() {
            return appkey;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public String getAppsecret() {
            return appsecret;
        }

        public void setAppsecret(String appsecret) {
            this.appsecret = appsecret;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
