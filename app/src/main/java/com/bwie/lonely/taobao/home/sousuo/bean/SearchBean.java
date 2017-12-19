package com.bwie.lonely.taobao.home.sousuo.bean;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class SearchBean {
    private String keywords;
    private String page;
    private String sort;

    public SearchBean(String keywords, String page, String sort) {
        this.keywords = keywords;
        this.page = page;
        this.sort = sort;
    }

    public SearchBean() {
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getPage() {
        return page;
    }

    public String getSort() {
        return sort;
    }
}
