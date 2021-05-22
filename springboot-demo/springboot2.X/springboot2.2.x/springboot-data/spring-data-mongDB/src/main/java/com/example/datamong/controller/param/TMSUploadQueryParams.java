package com.example.datamong.controller.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Map;


@ApiModel(description = "upload Tms 对象参数")
public class TMSUploadQueryParams implements Serializable {

	private static final long serialVersionUID = -1173825495171251303L;

	@NotNull(message ="ftpServerUrl不为空")
    @URL
    @ApiModelProperty(name="ftpServerUrl",value="ftp需要上传tms的父目录地址")
    String ftpServerUrl;

    @ApiModelProperty(name="domainName",value="数据域名称")
    @NotNull(message ="domainName不为空")
    @Pattern(regexp = "^[a-z\\d][a-z\\d\\-_]*$",message = "domainName必须是小写与_组成")
    String domainName;

    @ApiModelProperty(name="serviceName",value="服务名")
    @NotNull(message ="serviceName不为空")
    @Pattern(regexp = "^[a-z\\d][a-z\\d\\-_]*$",message = "serviceName必须是小写与_组成")
    String serviceName;

    @ApiModelProperty(name="srid",value="坐标系编号")
    @NotNull(message ="srid不为空")
    int srid;

    @ApiModelProperty(name="userName",value="ftp的账号的用户名")
    private String userName;

    @ApiModelProperty(name="password",value="ftp的账号密码")
    private String password;

    @ApiModelProperty(name="originX",value="原点坐标X")
    private double originX;

    @ApiModelProperty(name="originY",value="原点坐标Y")
    private double originY;

    @ApiModelProperty(name="minX",value="最小X")
    private double minX;

    @ApiModelProperty(name="maxX",value="最大X")
    private double maxX;

    @ApiModelProperty(name="minY",value="最小Y")
    private double minY;

    @ApiModelProperty(name="maxY",value="最大Y")
    private double maxY;

    @NotNull(message ="imgType不为空")
    private String imgType;

    @NotNull(message ="imgSize不为空")
    @ApiModelProperty(name="imgSize",value="图片大小")
    private int imgSize;

    @ApiModelProperty(name="resolutionMap",value="分辨率map")
    private Map<Integer,Double> resolutionMap;

    @ApiModelProperty(name="description",value="描述")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOriginX() {
        return originX;
    }

    public void setOriginX(double originX) {
        this.originX = originX;
    }

    public double getOriginY() {
        return originY;
    }

    public void setOriginY(double originY) {
        this.originY = originY;
    }

    public double getMinX() {
        return minX;
    }

    public void setMinX(double minX) {
        this.minX = minX;
    }

    public double getMaxX() {
        return maxX;
    }

    public void setMaxX(double maxX) {
        this.maxX = maxX;
    }

    public double getMinY() {
        return minY;
    }

    public void setMinY(double minY) {
        this.minY = minY;
    }

    public double getMaxY() {
        return maxY;
    }

    public void setMaxY(double maxY) {
        this.maxY = maxY;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }

    public int getImgSize() {
        return imgSize;
    }

    public void setImgSize(int imgSize) {
        this.imgSize = imgSize;
    }

    public Map<Integer, Double> getResolutionMap() {
        return resolutionMap;
    }

    public void setResolutionMap(Map<Integer, Double> resolutionMap) {
        this.resolutionMap = resolutionMap;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getFtpServerUrl() {
        return ftpServerUrl;
    }

    public void setFtpServerUrl(String ftpServerUrl) {
        this.ftpServerUrl = ftpServerUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getSrid() {
        return srid;
    }

    public void setSrid(int srid) {
        this.srid = srid;
    }

    @Override
    public String toString() {
        return "TMSUploadQueryParam{" +
                "ftpServerUrl='" + ftpServerUrl + '\'' +
                ", domainName='" + domainName + '\'' +
                ", serverName='" + serviceName + '\'' +
                ", srid=" + srid +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", originX=" + originX +
                ", originY=" + originY +
                ", minX=" + minX +
                ", maxX=" + maxX +
                ", minY=" + minY +
                ", maxY=" + maxY +
                ", imgType='" + imgType + '\'' +
                ", imgSize=" + imgSize +
                ", resolutionMap=" + resolutionMap +
                '}';
    }
}
