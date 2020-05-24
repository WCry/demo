package com.cdt.pojo;

/**
 * Created by root on 17-5-25.
 */
public class District {
    // 最小经度
    private String minx;

    // 最大经度
    private String maxx;

    // 最小维度
    private String miny;

    // 最大纬度
    private String maxy;

    // 名称
    private String name;

    // 行政编码
    private String admincode;

    // 简称
    private String abbreviation;

    // 服务地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMinx() {
        return minx;
    }

    public void setMinx(String minx) {
        this.minx = minx;
    }

    public String getMaxx() {
        return maxx;
    }

    public void setMaxx(String maxx) {
        this.maxx = maxx;
    }

    public String getMiny() {
        return miny;
    }

    public void setMiny(String miny) {
        this.miny = miny;
    }

    public String getMaxy() {
        return maxy;
    }

    public void setMaxy(String maxy) {
        this.maxy = maxy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmincode() {
        return admincode;
    }

    public void setAdmincode(String admincode) {
        this.admincode = admincode;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
