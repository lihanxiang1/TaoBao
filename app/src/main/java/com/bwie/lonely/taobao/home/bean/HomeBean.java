package com.bwie.lonely.taobao.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/8 0008.
 */

public class HomeBean {

    /**
     * code : 200
     * msg : success
     */
    private String code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private ActivityInfoBean activityInfo;
        private boolean creditRecived;
        private GoodsSpreeActivityBean goodsSpreeActivity;
        private List<SubjectsBean> subjects;
        private List<Ad1Bean> ad1;
        private List<Ad5Bean> ad5;
        private List<Ad8Bean> ad8;
        private List<DefaultGoodsListBean> defaultGoodsList;

        public ActivityInfoBean getActivityInfo() {
            return activityInfo;
        }

        public void setActivityInfo(ActivityInfoBean activityInfo) {
            this.activityInfo = activityInfo;
        }

        public boolean isCreditRecived() {
            return creditRecived;
        }

        public void setCreditRecived(boolean creditRecived) {
            this.creditRecived = creditRecived;
        }

        public GoodsSpreeActivityBean getGoodsSpreeActivity() {
            return goodsSpreeActivity;
        }

        public void setGoodsSpreeActivity(GoodsSpreeActivityBean goodsSpreeActivity) {
            this.goodsSpreeActivity = goodsSpreeActivity;
        }

        public List<SubjectsBean> getSubjects() {
            return subjects;
        }

        public void setSubjects(List<SubjectsBean> subjects) {
            this.subjects = subjects;
        }

        public List<Ad1Bean> getAd1() {
            return ad1;
        }

        public void setAd1(List<Ad1Bean> ad1) {
            this.ad1 = ad1;
        }

        public List<Ad5Bean> getAd5() {
            return ad5;
        }

        public void setAd5(List<Ad5Bean> ad5) {
            this.ad5 = ad5;
        }

        public List<Ad8Bean> getAd8() {
            return ad8;
        }

        public void setAd8(List<Ad8Bean> ad8) {
            this.ad8 = ad8;
        }

        public List<DefaultGoodsListBean> getDefaultGoodsList() {
            return defaultGoodsList;
        }

        public void setDefaultGoodsList(List<DefaultGoodsListBean> defaultGoodsList) {
            this.defaultGoodsList = defaultGoodsList;
        }

        public static class ActivityInfoBean {
            /**
             * activityAreaDisplay : 1
             * activityInfoList : [{"id":"60","activityImg":"https://image.yunifang.com/yunifang/images/goods/temp/170527155491221343694704636.jpg","activityType":"60","activityData":"69","activityDataDetail":"69","activityAreaDisplay":"1","countDownEnable":"0","remark":"搭配购买立减","sort":0},{"id":"21","activityImg":"https://image.yunifang.com/yunifang/images/goods/temp/17051718251658680692616281.jpg","activityType":"1","activityData":"http://h.yunifang.com/invite/invite.html?login_check=2","activityDataDetail":"http%3A%2F%2Fh.yunifang.com%2Finvite%2Finvite.html%3Flogin_check%3D2","activityAreaDisplay":"1","countDownEnable":"0","sort":0}]
             */

            private String activityAreaDisplay;
            private List<ActivityInfoListBean> activityInfoList;

            public String getActivityAreaDisplay() {
                return activityAreaDisplay;
            }

            public void setActivityAreaDisplay(String activityAreaDisplay) {
                this.activityAreaDisplay = activityAreaDisplay;
            }

            public List<ActivityInfoListBean> getActivityInfoList() {
                return activityInfoList;
            }

            public void setActivityInfoList(List<ActivityInfoListBean> activityInfoList) {
                this.activityInfoList = activityInfoList;
            }

            public static class ActivityInfoListBean {
                /**
                 * id : 60
                 * activityImg : https://image.yunifang.com/yunifang/images/goods/temp/170527155491221343694704636.jpg
                 * activityType : 60
                 * activityData : 69
                 * activityDataDetail : 69
                 * activityAreaDisplay : 1
                 * countDownEnable : 0
                 * remark : 搭配购买立减
                 * sort : 0
                 */

                private String id;
                private String activityImg;
                private String activityType;
                private String activityData;
                private String activityDataDetail;
                private String activityAreaDisplay;
                private String countDownEnable;
                private String remark;
                private String sort;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getActivityImg() {
                    return activityImg;
                }

                public void setActivityImg(String activityImg) {
                    this.activityImg = activityImg;
                }

                public String getActivityType() {
                    return activityType;
                }

                public void setActivityType(String activityType) {
                    this.activityType = activityType;
                }

                public String getActivityData() {
                    return activityData;
                }

                public void setActivityData(String activityData) {
                    this.activityData = activityData;
                }

                public String getActivityDataDetail() {
                    return activityDataDetail;
                }

                public void setActivityDataDetail(String activityDataDetail) {
                    this.activityDataDetail = activityDataDetail;
                }

                public String getActivityAreaDisplay() {
                    return activityAreaDisplay;
                }

                public void setActivityAreaDisplay(String activityAreaDisplay) {
                    this.activityAreaDisplay = activityAreaDisplay;
                }

                public String getCountDownEnable() {
                    return countDownEnable;
                }

                public void setCountDownEnable(String countDownEnable) {
                    this.countDownEnable = countDownEnable;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getSort() {
                    return sort;
                }

                public void setSort(String sort) {
                    this.sort = sort;
                }
            }
        }

        public static class GoodsSpreeActivityBean {
            /**
             * id : 327
             * name : 10.13早场
             * startDate : 2017.10.13 10:00:00
             * endDate : 2017.10.13 16:00:00
             * status : 1
             * startSeconds : -12118
             * endSeconds : 9481
             * isChecked : 0
             * goodsList : [{"id":"997","goodsSpreeId":"327","goodsId":"340","goodsName":"【使用期一年以上介意慎拍】黑玫瑰矿物柔肤乳液120ml","goodsImg":"https://image.yunifang.com/yunifang/images/goods/340/goods_img/170626103460417447209262417.jpg","marketPrice":169,"activityPrice":39.9,"salesRatio":0,"stockNumber":30,"releaseNumber":40},{"id":"998","goodsSpreeId":"327","goodsId":"1324","goodsName":"鲜气少女肌|水光轻灵焕彩面膜5片","goodsImg":"https://image.yunifang.com/yunifang/images/goods/1324/goods_img/170626102366110382576889824.jpg","marketPrice":129,"activityPrice":59.9,"salesRatio":0,"stockNumber":30,"releaseNumber":30},{"id":"999","goodsSpreeId":"327","goodsId":"903","goodsName":"绿豆原浆泥面膜100g","goodsImg":"https://image.yunifang.com/yunifang/images/goods/903/goods_img/170627180542017051678666200.jpg","marketPrice":99,"activityPrice":49.9,"salesRatio":0,"stockNumber":28,"releaseNumber":30}]
             */

            private String id;
            private String name;
            private String startDate;
            private String endDate;
            private String status;
            private String startSeconds;
            private String endSeconds;
            private String isChecked;
            private List<GoodsListBean> goodsList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStartDate() {
                return startDate;
            }

            public void setStartDate(String startDate) {
                this.startDate = startDate;
            }

            public String getEndDate() {
                return endDate;
            }

            public void setEndDate(String endDate) {
                this.endDate = endDate;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getStartSeconds() {
                return startSeconds;
            }

            public void setStartSeconds(String startSeconds) {
                this.startSeconds = startSeconds;
            }

            public String getEndSeconds() {
                return endSeconds;
            }

            public void setEndSeconds(String endSeconds) {
                this.endSeconds = endSeconds;
            }

            public String getIsChecked() {
                return isChecked;
            }

            public void setIsChecked(String isChecked) {
                this.isChecked = isChecked;
            }

            public List<GoodsListBean> getGoodsList() {
                return goodsList;
            }

            public void setGoodsList(List<GoodsListBean> goodsList) {
                this.goodsList = goodsList;
            }

            public static class GoodsListBean {
                /**
                 * id : 997
                 * goodsSpreeId : 327
                 * goodsId : 340
                 * goodsName : 【使用期一年以上介意慎拍】黑玫瑰矿物柔肤乳液120ml
                 * goodsImg : https://image.yunifang.com/yunifang/images/goods/340/goods_img/170626103460417447209262417.jpg
                 * marketPrice : 169
                 * activityPrice : 39.9
                 * salesRatio : 0
                 * stockNumber : 30
                 * releaseNumber : 40
                 */

                private String id;
                private String goodsSpreeId;
                private String goodsId;
                private String goodsName;
                private String goodsImg;
                private String marketPrice;
                private double activityPrice;
                private String salesRatio;
                private String stockNumber;
                private String releaseNumber;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getGoodsSpreeId() {
                    return goodsSpreeId;
                }

                public void setGoodsSpreeId(String goodsSpreeId) {
                    this.goodsSpreeId = goodsSpreeId;
                }

                public String getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(String goodsId) {
                    this.goodsId = goodsId;
                }

                public String getGoodsName() {
                    return goodsName;
                }

                public void setGoodsName(String goodsName) {
                    this.goodsName = goodsName;
                }

                public String getGoodsImg() {
                    return goodsImg;
                }

                public void setGoodsImg(String goodsImg) {
                    this.goodsImg = goodsImg;
                }

                public String getMarketPrice() {
                    return marketPrice;
                }

                public void setMarketPrice(String marketPrice) {
                    this.marketPrice = marketPrice;
                }

                public double getActivityPrice() {
                    return activityPrice;
                }

                public void setActivityPrice(double activityPrice) {
                    this.activityPrice = activityPrice;
                }

                public String getSalesRatio() {
                    return salesRatio;
                }

                public void setSalesRatio(String salesRatio) {
                    this.salesRatio = salesRatio;
                }

                public String getStockNumber() {
                    return stockNumber;
                }

                public void setStockNumber(String stockNumber) {
                    this.stockNumber = stockNumber;
                }

                public String getReleaseNumber() {
                    return releaseNumber;
                }

                public void setReleaseNumber(String releaseNumber) {
                    this.releaseNumber = releaseNumber;
                }
            }
        }

        public static class SubjectsBean {

            private String id;
            private String title;
            private String detail;
            private String image;
            private String start_time;
            private String end_time;
            private String show_number;
            private String state;
            private String sort;
            private String descImage;
            private String template;
            private String url;
            private String wapUrl;
            private List<GoodsListBeanX> goodsList;
            private List<String> goodsIdsList;
            private List<GoodsRelationListBean> goodsRelationList;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getShow_number() {
                return show_number;
            }

            public void setShow_number(String show_number) {
                this.show_number = show_number;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getDescImage() {
                return descImage;
            }

            public void setDescImage(String descImage) {
                this.descImage = descImage;
            }

            public String getTemplate() {
                return template;
            }

            public void setTemplate(String template) {
                this.template = template;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getWapUrl() {
                return wapUrl;
            }

            public void setWapUrl(String wapUrl) {
                this.wapUrl = wapUrl;
            }

            public List<GoodsListBeanX> getGoodsList() {
                return goodsList;
            }

            public void setGoodsList(List<GoodsListBeanX> goodsList) {
                this.goodsList = goodsList;
            }

            public List<String> getGoodsIdsList() {
                return goodsIdsList;
            }

            public void setGoodsIdsList(List<String> goodsIdsList) {
                this.goodsIdsList = goodsIdsList;
            }

            public List<GoodsRelationListBean> getGoodsRelationList() {
                return goodsRelationList;
            }

            public void setGoodsRelationList(List<GoodsRelationListBean> goodsRelationList) {
                this.goodsRelationList = goodsRelationList;
            }

            public static class GoodsListBeanX {
                /**
                 * id : 492
                 * goods_name : 盈透美肌黑膜套装（插画版）
                 * shop_price : 99.9
                 * market_price : 298
                 * goods_img : https://image.yunifang.com/yunifang/images/goods/492/goods_img/171011191068814258195256706.jpg
                 * reservable : false
                 * efficacy : 以黑吸黑 润透亮颜
                 * stock_number : 0
                 * restrict_purchase_num : 0
                 * goodsName : PG one热荐：★★★★★
                 * goodsImage : https://image.yunifang.com/yunifang/images/goods/temp/171011192212214258195254179.jpg
                 * description : 三重植物精粹，三重水润膜力，美时美刻，水润透亮~
                 */

                private String id;
                private String goods_name;
                private double shop_price;
                private String market_price;
                private String goods_img;
                private boolean reservable;
                private String efficacy;
                private String stock_number;
                private String restrict_purchase_num;
                private String goodsName;
                private String goodsImage;
                private String description;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public double getShop_price() {
                    return shop_price;
                }

                public void setShop_price(double shop_price) {
                    this.shop_price = shop_price;
                }

                public String getMarket_price() {
                    return market_price;
                }

                public void setMarket_price(String market_price) {
                    this.market_price = market_price;
                }

                public String getGoods_img() {
                    return goods_img;
                }

                public void setGoods_img(String goods_img) {
                    this.goods_img = goods_img;
                }

                public boolean isReservable() {
                    return reservable;
                }

                public void setReservable(boolean reservable) {
                    this.reservable = reservable;
                }

                public String getEfficacy() {
                    return efficacy;
                }

                public void setEfficacy(String efficacy) {
                    this.efficacy = efficacy;
                }

                public String getStock_number() {
                    return stock_number;
                }

                public void setStock_number(String stock_number) {
                    this.stock_number = stock_number;
                }

                public String getRestrict_purchase_num() {
                    return restrict_purchase_num;
                }

                public void setRestrict_purchase_num(String restrict_purchase_num) {
                    this.restrict_purchase_num = restrict_purchase_num;
                }

                public String getGoodsName() {
                    return goodsName;
                }

                public void setGoodsName(String goodsName) {
                    this.goodsName = goodsName;
                }

                public String getGoodsImage() {
                    return goodsImage;
                }

                public void setGoodsImage(String goodsImage) {
                    this.goodsImage = goodsImage;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }
            }

            public static class GoodsRelationListBean {
                /**
                 * id : 14815
                 * subject_id : 84
                 * goods_id : 492
                 * goodsName : PG one热荐：★★★★★
                 * goodsImage : https://image.yunifang.com/yunifang/images/goods/temp/171011192212214258195254179.jpg
                 * description : 三重植物精粹，三重水润膜力，美时美刻，水润透亮~
                 */

                private String id;
                private String subject_id;
                private String goods_id;
                private String goodsName;
                private String goodsImage;
                private String description;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getSubject_id() {
                    return subject_id;
                }

                public void setSubject_id(String subject_id) {
                    this.subject_id = subject_id;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoodsName() {
                    return goodsName;
                }

                public void setGoodsName(String goodsName) {
                    this.goodsName = goodsName;
                }

                public String getGoodsImage() {
                    return goodsImage;
                }

                public void setGoodsImage(String goodsImage) {
                    this.goodsImage = goodsImage;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }
            }
        }

        public static class Ad1Bean {
            /**
             * id : 1147
             * createtime : 2017.10.09 08:33:42
             * lastupdatetime : 2017.10.09 08:33:46
             * image : https://image.yunifang.com/yunifang/images/goods/ad0/17100908319495742677657462.jpg
             * ad_type : 0
             * sort : 1414
             * position : 0
             * enabled : 1
             * createuser : leiqi
             * lastupdateuser : leiqi
             * ad_type_dynamic : 1
             * ad_type_dynamic_data : http://h.yunifang.com/h/comment.html
             * ad_type_dynamic_detail : http%3A%2F%2Fh.yunifang.com%2Fh%2Fcomment.html
             * title : 10月商品好评有礼
             * channelType : 0
             * show_channel : 1,2,3,4
             */

            private String id;
            private String createtime;
            private String lastupdatetime;
            private String image;
            private String ad_type;
            private String sort;
            private String position;
            private String enabled;
            private String createuser;
            private String lastupdateuser;
            private String ad_type_dynamic;
            private String ad_type_dynamic_data;
            private String ad_type_dynamic_detail;
            private String title;
            private String channelType;
            private String show_channel;

            // banner图片+详情页链接
            public Ad1Bean(String image, String ad_type_dynamic_data) {
                this.image = image;
                this.ad_type_dynamic_data = ad_type_dynamic_data;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getLastupdatetime() {
                return lastupdatetime;
            }

            public void setLastupdatetime(String lastupdatetime) {
                this.lastupdatetime = lastupdatetime;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getAd_type() {
                return ad_type;
            }

            public void setAd_type(String ad_type) {
                this.ad_type = ad_type;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getEnabled() {
                return enabled;
            }

            public void setEnabled(String enabled) {
                this.enabled = enabled;
            }

            public String getCreateuser() {
                return createuser;
            }

            public void setCreateuser(String createuser) {
                this.createuser = createuser;
            }

            public String getLastupdateuser() {
                return lastupdateuser;
            }

            public void setLastupdateuser(String lastupdateuser) {
                this.lastupdateuser = lastupdateuser;
            }

            public String getAd_type_dynamic() {
                return ad_type_dynamic;
            }

            public void setAd_type_dynamic(String ad_type_dynamic) {
                this.ad_type_dynamic = ad_type_dynamic;
            }

            public String getAd_type_dynamic_data() {
                return ad_type_dynamic_data;
            }

            public void setAd_type_dynamic_data(String ad_type_dynamic_data) {
                this.ad_type_dynamic_data = ad_type_dynamic_data;
            }

            public String getAd_type_dynamic_detail() {
                return ad_type_dynamic_detail;
            }

            public void setAd_type_dynamic_detail(String ad_type_dynamic_detail) {
                this.ad_type_dynamic_detail = ad_type_dynamic_detail;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getChannelType() {
                return channelType;
            }

            public void setChannelType(String channelType) {
                this.channelType = channelType;
            }

            public String getShow_channel() {
                return show_channel;
            }

            public void setShow_channel(String show_channel) {
                this.show_channel = show_channel;
            }
        }

        public static class Ad5Bean {
            /**
             * id : 359
             * image : https://image.yunifang.com/yunifang/images/goods/ad0/170516143575610973073847273.png
             * ad_type : 4
             * sort : 295
             * position : 5
             * enabled : 0
             * ad_type_dynamic : 1
             * ad_type_dynamic_data : http://h.yunifang.com/sign/sign.html?login_check=2
             * ad_type_dynamic_detail : http%3A%2F%2Fh.yunifang.com%2Fsign%2Fsign.html%3Flogin_check%3D2
             * show_channel : 1,2
             * title : 每日签到
             * url : http://mobile.hmeili.com/yunifang/web/member/gift
             */

            private String id;
            private String image;
            private String ad_type;
            private String sort;
            private String position;
            private String enabled;
            private String ad_type_dynamic;
            private String ad_type_dynamic_data;
            private String ad_type_dynamic_detail;
            private String show_channel;
            private String title;
            private String url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getAd_type() {
                return ad_type;
            }

            public void setAd_type(String ad_type) {
                this.ad_type = ad_type;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getEnabled() {
                return enabled;
            }

            public void setEnabled(String enabled) {
                this.enabled = enabled;
            }

            public String getAd_type_dynamic() {
                return ad_type_dynamic;
            }

            public void setAd_type_dynamic(String ad_type_dynamic) {
                this.ad_type_dynamic = ad_type_dynamic;
            }

            public String getAd_type_dynamic_data() {
                return ad_type_dynamic_data;
            }

            public void setAd_type_dynamic_data(String ad_type_dynamic_data) {
                this.ad_type_dynamic_data = ad_type_dynamic_data;
            }

            public String getAd_type_dynamic_detail() {
                return ad_type_dynamic_detail;
            }

            public void setAd_type_dynamic_detail(String ad_type_dynamic_detail) {
                this.ad_type_dynamic_detail = ad_type_dynamic_detail;
            }

            public String getShow_channel() {
                return show_channel;
            }

            public void setShow_channel(String show_channel) {
                this.show_channel = show_channel;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class Ad8Bean {
            /**
             * id : 1056
             * image : https://image.yunifang.com/yunifang/images/goods/ad0/170719150582816742818457761.png
             * ad_type : 0
             * sort : 57
             * position : 8
             * enabled : 0
             * description : 人气好物超值推荐
             * ad_type_dynamic : 1
             * ad_type_dynamic_data : http://vip.yunifang.com/goods/recommend.html?id=87
             * ad_type_dynamic_detail : http%3A%2F%2Fh.yunifang.com%2Fgoods%2Frecommend.html%3Fid%3D87
             * show_channel : 1,2,3,4
             * title : 新鲜每一天
             * goods : {"collect_count":0,"reservable":false,"restriction":0,"restrict_purchase_num":0,"is_coupon_allowed":false,"allocated_stock":0,"is_gift":0}
             */

            private String id;
            private String image;
            private String ad_type;
            private String sort;
            private String position;
            private String enabled;
            private String description;
            private String ad_type_dynamic;
            private String ad_type_dynamic_data;
            private String ad_type_dynamic_detail;
            private String show_channel;
            private String title;
            private GoodsBean goods;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getAd_type() {
                return ad_type;
            }

            public void setAd_type(String ad_type) {
                this.ad_type = ad_type;
            }

            public String getSort() {
                return sort;
            }

            public void setSort(String sort) {
                this.sort = sort;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getEnabled() {
                return enabled;
            }

            public void setEnabled(String enabled) {
                this.enabled = enabled;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getAd_type_dynamic() {
                return ad_type_dynamic;
            }

            public void setAd_type_dynamic(String ad_type_dynamic) {
                this.ad_type_dynamic = ad_type_dynamic;
            }

            public String getAd_type_dynamic_data() {
                return ad_type_dynamic_data;
            }

            public void setAd_type_dynamic_data(String ad_type_dynamic_data) {
                this.ad_type_dynamic_data = ad_type_dynamic_data;
            }

            public String getAd_type_dynamic_detail() {
                return ad_type_dynamic_detail;
            }

            public void setAd_type_dynamic_detail(String ad_type_dynamic_detail) {
                this.ad_type_dynamic_detail = ad_type_dynamic_detail;
            }

            public String getShow_channel() {
                return show_channel;
            }

            public void setShow_channel(String show_channel) {
                this.show_channel = show_channel;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public GoodsBean getGoods() {
                return goods;
            }

            public void setGoods(GoodsBean goods) {
                this.goods = goods;
            }

            public static class GoodsBean {
                /**
                 * collect_count : 0
                 * reservable : false
                 * restriction : 0
                 * restrict_purchase_num : 0
                 * is_coupon_allowed : false
                 * allocated_stock : 0
                 * is_gift : 0
                 */

                private String collect_count;
                private boolean reservable;
                private String restriction;
                private String restrict_purchase_num;
                private boolean is_coupon_allowed;
                private String allocated_stock;
                private String is_gift;

                public String getCollect_count() {
                    return collect_count;
                }

                public void setCollect_count(String collect_count) {
                    this.collect_count = collect_count;
                }

                public boolean isReservable() {
                    return reservable;
                }

                public void setReservable(boolean reservable) {
                    this.reservable = reservable;
                }

                public String getRestriction() {
                    return restriction;
                }

                public void setRestriction(String restriction) {
                    this.restriction = restriction;
                }

                public String getRestrict_purchase_num() {
                    return restrict_purchase_num;
                }

                public void setRestrict_purchase_num(String restrict_purchase_num) {
                    this.restrict_purchase_num = restrict_purchase_num;
                }

                public boolean isIs_coupon_allowed() {
                    return is_coupon_allowed;
                }

                public void setIs_coupon_allowed(boolean is_coupon_allowed) {
                    this.is_coupon_allowed = is_coupon_allowed;
                }

                public String getAllocated_stock() {
                    return allocated_stock;
                }

                public void setAllocated_stock(String allocated_stock) {
                    this.allocated_stock = allocated_stock;
                }

                public String getIs_gift() {
                    return is_gift;
                }

                public void setIs_gift(String is_gift) {
                    this.is_gift = is_gift;
                }
            }
        }

        public static class DefaultGoodsListBean {
            /**
             * id : 121
             * goods_name : 镇店之宝丨美白嫩肤面膜7片
             * shop_price : 49.9
             * market_price : 99
             * goods_img : https://image.yunifang.com/yunifang/images/goods/121/goods_img/17062610568378169043195978.jpg
             * reservable : false
             * efficacy : 镇店之宝 美白爆款
             * stock_number : 0
             * restrict_purchase_num : 0
             */

            private String id;
            private String goods_name;
            private double shop_price;
            private String market_price;
            private String goods_img;
            private boolean reservable;
            private String efficacy;
            private String stock_number;
            private String restrict_purchase_num;

            public DefaultGoodsListBean(String goods_img, String goods_name) {
                this.goods_img = goods_img;
                this.goods_name = goods_name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public double getShop_price() {
                return shop_price;
            }

            public void setShop_price(double shop_price) {
                this.shop_price = shop_price;
            }

            public String getMarket_price() {
                return market_price;
            }

            public void setMarket_price(String market_price) {
                this.market_price = market_price;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public boolean isReservable() {
                return reservable;
            }

            public void setReservable(boolean reservable) {
                this.reservable = reservable;
            }

            public String getEfficacy() {
                return efficacy;
            }

            public void setEfficacy(String efficacy) {
                this.efficacy = efficacy;
            }

            public String getStock_number() {
                return stock_number;
            }

            public void setStock_number(String stock_number) {
                this.stock_number = stock_number;
            }

            public String getRestrict_purchase_num() {
                return restrict_purchase_num;
            }

            public void setRestrict_purchase_num(String restrict_purchase_num) {
                this.restrict_purchase_num = restrict_purchase_num;
            }
        }
    }
}
